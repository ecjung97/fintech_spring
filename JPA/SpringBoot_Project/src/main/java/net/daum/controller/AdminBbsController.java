package net.daum.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.AdminBbsService;
import net.daum.vo.Bbs2VO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

@Controller
public class AdminBbsController {

	@Autowired
	private AdminBbsService adminBbsService;
	
	// 관리자 자료실 목록
	@GetMapping("/admin_bbs_list")
	public ModelAndView admin_bbs_list(BbsVO b, PageVO p, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) {
			// 자료실 페이징 목록
			int page=1;
			int limit=7; // 한 페이지에 보여지는 목록개수
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			String find_name = request.getParameter("find_name"); // 검색어
			String find_field = request.getParameter("find_field"); // 검색 필드
			p.setFind_field(find_field);
			p.setFind_name("%"+find_name+"%"); // SQL문에서 % 와일트카드문자는 검색에서 하나이상의 임의의 모르는 문자와 매핑대응
			
			int totalCount = this.adminBbsService.getRowCount(p); // 검색 전 총 레코드개수, 검색 후 레코드개수
			
			p.setStartrow((page-1)*7+1); // 시작행 번호
			p.setEndrow(p.getStartrow()+limit-1); // 끝행 번호
			
			List<BbsVO> blist = this.adminBbsService.getBbsList(p); // 검색 전후 페이징 목록
			
			// 총 페이지수
			int maxpage=(int)((double)totalCount/limit+0.95);
			// 시작페이지(1,11,21,..)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			// 현재 페이지에 보여질 마지막 페이지(10,20,...)
			int endpage=maxpage;
			if (endpage>startpage+10-1) endpage = startpage+10-1;
			
			ModelAndView listM = new ModelAndView();
			
			listM.addObject("blist", blist); // blist키이름에 검색전후 목록 저장
			listM.addObject("page", page); // 페이징목록에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능 때문에 쪽번호 저장
			listM.addObject("startpage", startpage); // 시작페이지
			listM.addObject("endpage", endpage); // 마지막페이지
			listM.addObject("maxpage", maxpage); // 최대페이지
			listM.addObject("totalCount", totalCount); // 검색전후 레코드 개수
			listM.addObject("find_field", find_field); // 검색필드
			listM.addObject("find_name", find_name); // 검색어
			
			listM.setViewName("admin/admin_bbs_list"); // 뷰페이지 경로 => /WEB-INF/views/admin/admin_bbs_list.jsp
			return listM;
		}
		return null;
	} // admin_bbs_list()
	
	// 관리자 자료실 글쓰기
	@GetMapping("/admin_bbs_write")
	public ModelAndView admin_bbs_write(HttpServletResponse response, HttpSession session, HttpServletRequest request) 
	throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) { // == true가 생략됨. true이면 관리자 로그인 된 상태
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			ModelAndView wm = new ModelAndView("admin/admin_bbs_write");
			wm.addObject("page", page); // 페이징에서 책갈피 기능 때문에 쪽번호를 page키이름에 저장
			
			return wm;
		}
		return null;
	} // admin_bbs_write()
	
	// 관리자 자료실 저장
	@PostMapping("/admin_bbs_write_ok")
	public ModelAndView admin_bbs_write_ok(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			BbsVO bbs, Bbs2VO bbs2) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) {
			String uploadFolder = request.getSession().getServletContext().getRealPath("upload"); // 첨부파일을 서버에 업로드 할 실제 경로
			MultipartFile uploadFile = bbs2.getUploadFile(); // 스프링에서 MultipartFile 타입을 적용해서 업로드 되는 파일 데이터를 쉽게 처리함. 첨부된 파일을 가져온다.
			
			if (!uploadFile.isEmpty()) { // 첨부파일이 있다면
				System.out.println("---------------------------->");
				System.out.println("Upload File Name: "+ uploadFile.getOriginalFilename()); // 업로드 된 원본파일명
				System.out.println("Upload File Size: "+ uploadFile.getSize()); // 업로드 파일크기
				
				String fileName = uploadFile.getOriginalFilename();
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR); // 년도
				int month = cal.get(Calendar.MONTH)+1; // 월값, +1을 한 이유는 1월이 0으로 반환되기 때문이다.
				int date = cal.get(Calendar.DATE); // 일값
						
				String homedir = uploadFolder + "/" + year + "-" + month + "-" + date; // 오늘날짜 폴더경로 저장
				File path01=new File(homedir);
				
				if (!(path01.exists())) {
					path01.mkdir(); // 오늘날짜 폴더 생성
				}
				Random r = new Random(); // 난수를 발생시키는 클래스
				int random = r.nextInt(100000000); // 0이상 1억미만사이의 정수숫자 난수 발생
				
				/* 첨부파일 확장자 구함 */
				int index = fileName.lastIndexOf("."); // 첨부파일에서 .를 맨오른쪽부터 찾아서 가장 먼저 나오는 해당 문자위치번호를 맨왼쪽 첫문자를 0부터 카운트해서 구한다.
				String fileExtendsion = fileName.substring(index+1); // .이후부터 마지막 문자까지 구한다. 첨부파일 확장자를 구함.
				String refileName = "bbs"+year+month+date+random+"."+fileExtendsion; // 새오룬 첨부파일명을 구함.
				String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refileName; // 데이터베이스에 저장될 값값
				
				bbs.setBbs_file(fileDBName);
				
				File saveFile = new File(homedir+"/"+refileName);
				
				try {
					uploadFile.transferTo(saveFile); // upload폴더에 오늘날짜로 새롭게 생성된 폴더에 변경된 첨부파일명으로 실제 업로드
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else { // 첨부파일이 없는 경우
				String fileDBName = "";
				bbs.setBbs_file(fileDBName);
			}
			
			this.adminBbsService.insertBbs(bbs); // 자료실 저장
			/* 문제) 사용자 자료실 저장 소스 부분을 참조해서 관리자 자료실 저장부분을 jpa로 만들어보자. */
			
			return new ModelAndView("redirect:/admin_bbs_list"); // 저장 후 관리자 자료실 목록으로 이동
		}
		return null;
	} // admin_bbs_write_ok()
	
	// 관리자 자료실 상세정보 보기 + 수정폼
	@RequestMapping("/admin_bbs_cont")
	public ModelAndView admin_bbs_cont(int no, int page, String state, HttpServletResponse response,
			HttpSession session) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) {
			BbsVO b = this.adminBbsService.getAdminBbsCont(no);
			String bbs_cont = b.getBbs_cont().replace("\n", "<br/>");
			
			ModelAndView cm = new ModelAndView();
			cm.addObject("b", b);
			cm.addObject("page", page);
			cm.addObject("bbs_cont", bbs_cont);
			
			if (state.equals("cont")) { // 관리자 자료실 상세정보 보기
				cm.setViewName("admin/admin_bbs_cont");
			}else if(state.equals("edit")) { // 관리자 자료실 수정폼
				cm.setViewName("admin/admin_bbs_edit");
			}
			return cm;
		}
		return null;
	} // admin_bbs_cont()
	
	// 관리자 자료실 수정완료
	@RequestMapping(value="/admin_bbs_edit_ok", method=RequestMethod.POST) // POST로 접근하는 매핑주소 처리
	public ModelAndView admin_bbs_edit_ok(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, BbsVO bbs, Bbs2VO bbs2) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) {
			//PrintWriter out=response.getWriter();
	         String uploadFolder=request.getSession().getServletContext().getRealPath("upload"); // 첨부된 파일 업로드 되는 실제 서버경로         

	         int page=1;
	         if(request.getParameter("page") != null) {
	            page=Integer.parseInt(request.getParameter("page"));
	         }   

	         BbsVO db_pwd=this.adminBbsService.getAdminBbsCont(bbs.getBbs_no()); // 조회수가 증가되지 않는 상태에서 오라클로 부터
	         //레코드를 가져옴.

	         MultipartFile uploadFile = bbs2.getUploadFile(); // 수정 첨부된 파일을 가져옴.

	         if(!uploadFile.isEmpty()) { // 첨부파일 있는 경우만 실행
	            String fileName=uploadFile.getOriginalFilename(); // 첨부 원본 파일명
	            //System.out.println(fileName);
	            File delFile=new File(uploadFolder+db_pwd.getBbs_file()); // 삭제할 파일객체 생성
	            if(delFile.exists()) { // 삭제할 파일이 있다면
	               delFile.delete(); // 기본 첨부파일을 삭제
	            }
	            Calendar cal=Calendar.getInstance();
	            int year=cal.get(Calendar.YEAR);
	            int month=cal.get(Calendar.MONTH)+1;
	            int date=cal.get(Calendar.DATE);

	            String homedir=uploadFolder+"/"+year+"-"+month+"-"+date;
	            File path01=new File(homedir);
	            if(!(path01.exists())) {
	               path01.mkdir(); // 오늘 날짜 폴더를 생성
	            }
	            Random r=new Random();
	            int random=r.nextInt(100000000);

	            /*첨부 파일 확장자를 구함*/
	            int index=fileName.lastIndexOf("."); // 마침표 위치번호를 구함.
	            String fileExtendsion=fileName.substring(index+1); // .이후부터 마지막 문자까지 구함->결국 
	            //첨부파일 확장자만 구함.
	            String refileName="bbs"+year+month+date+random+"."+fileExtendsion; // 새로운 파일명 저장
	            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
	            // 오라클 DB에 저장될 레코드값

	            //System.out.println(refileName);

	            bbs.setBbs_file(fileDBName);

	            File saveFile = new File(homedir+"/", refileName);
	            try {
	               uploadFile.transferTo(saveFile); // upload폴더에 오늘날짜로 새롭게 생성된 폴더에 변경된 첨부파일명으로 실제 업로드
	            } catch (Exception e) {
	               e.printStackTrace();
	            } // end catch


	         }else { // 수정 첨부파일을 하지 않았을 때
	            String fileDBName="";
	            if(db_pwd.getBbs_file() != null) { // 기존 첨부 파일이 있다면
	               bbs.setBbs_file(db_pwd.getBbs_file());
	            }else {
	               bbs.setBbs_file(fileDBName);
	            }
	         } // if else

	         this.adminBbsService.adminUpdateBbs(bbs); // 자료실 수정
	         /* 문제) 번호를 기준으로 글쓴이,글제목,글내용,첨부파일을 수정되게 한다. (개발자 테스트까지 해본다.) */

	         ModelAndView em = new ModelAndView("redirect:/admin_bbs_list?page="+page);
	         return em; // admin_bbs_list?page=쪽번호 가 get으로 전달된다.
		}
		return null;
	} // admin_bbs_edit_ok()
	
	// 관리자 자료실 삭제
	@RequestMapping(value="/admin_bbs_del", method=RequestMethod.GET)
	public String admin_bbs_del(int no, int page, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		if (isAdminLogin(session, response)) {
			String delFolder = request.getSession().getServletContext().getRealPath("upload");
			BbsVO db_file = this.adminBbsService.getAdminBbsCont(no);
			
			if (db_file.getBbs_file() != null) { // 기존 첨부파일이 있다면
				File delFile = new File(delFolder+db_file.getBbs_file()); // 삭제할 파일 객체 생성
				delFile.delete(); // 폴더는 삭제 안하고 기존 첨부파일을 삭제
			}
			
			this.adminBbsService.adminBbsDel(no);
			/* 문제) mybatis쪽은 놔두고 JPA로 삭제되게 코딩을 완성하자. 그리고 upload폴더로 부터 기존 첨부파일 삭제된 것 까지 확인해야 한다.
			 * 개발자 테스트 하고 디버깅을 해보자. */
			return "redirect:/admin_bbs_list?page="+page;
		}
		return null;
	} // admin_bbs_del()
	
	// 세션이 만료되었을 때나 로그아웃 된 이후에 반복적인 관리자 로그인 매핑주소로 이동하기 위한 코드
	public static boolean isAdminLogin(HttpSession session, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String admin_id=(String)session.getAttribute("admin_id"); // 세션 관리자 아이디를 구함
		
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_Login';");
			out.println("</script>");
			
			return false;
		}
		return true; // 관리자 로그인 된 경우
	} // isAdminLogin()
}
