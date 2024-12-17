package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller //@Controller 애노테이션을 설정함으로써 스프링에서 컨트롤러로 인식한다.
@RequestMapping("/board/*") //컨트롤러 자체 매핑주소 등록
public class BoardController {//스프링 MVC 게시판 컨트롤러 클래스

	@Autowired
	private BoardService boardService;
	
	//스프링 MVC 게시판 글쓰기 폼
	@RequestMapping(value="/board_write", method=RequestMethod.GET) //GET으로 접근하는 매핑주소를 처리, board_write매핑주소 등록
	public void board_write(HttpServletRequest request) {
		/* 1.메서드 반환타입이 void형(리턴타입이 없다)이면 매핑주소가 뷰페이지 파일명이 된다.
		 * 2.뷰페이지 경로(뷰리졸브)는 /WEB-INF/views/board/board_write.jsp
		 * 3.HttpServletRequest 서블릿은 사용자 폼에서 입력한 정보를 서버로 가져올 때 사용한다.
		 */

		// 페이징에서 책갈피 기능구현 소스
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		request.setAttribute("page", page);
		
	}//board_write()
	
	//게시판 저장
	@PostMapping("/board_write") //@PostMapping은 post로 접근하는  매핑주소를 처리
	public ModelAndView board_write_ok(BoardVO b, RedirectAttributes rttr) {
		/* board_write.jsp의 네임 피라미터 이름과 BoardVO.java의 빈클래스 변수명이 같으면 BoardVO b라고만 해도 b객체에 글쓴이,글제목,
		 * 글내용이 한꺼번에 저장된다. 
		 */
		this.boardService.insertBoard(b);//게시판 저장
		rttr.addFlashAttribute("msg", "SUCCESS");//msg키이름에 SUCCESS문자를 담아서 다른 매핑주소로 전달한다.이 방법은 주소창에 노출안된
		//다.보안상 좋다.
		return new ModelAndView("redirect:/board/board_list");
		/*ModelAndView 스프링 api 생성자 인자값으로 redirect:/매핑주소 가 들어가면 새로운 매핑주소로 이동하는 것이고, 뷰페이지 경로가
		 * 들어가면 해당 뷰페이지가 보인다.
		 */
	}//board_write_ok()
	
	//게시판 목록
	@RequestMapping("/board_list") //get or post방식으로 접근하는 매핑주소를 처리, board_list매핑주소 등록
	public String board_list(Model m, HttpServletRequest request, BoardVO b) {
		
		int page = 1;//현재 쪽 번호
		int limit = 10;//한페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
			//get으로 전달된 쪽번호가 있는 경우 실행
			page = Integer.parseInt(request.getParameter("page"));
			//쪽번호를 정수숫자로 변경해서 저장
		}
		b.setStartrow((page-1)*10+1); //시작행번호
		b.setEndrow(b.getStartrow()+limit-1);//끝행번호
		
		int totalCount = this.boardService.getTotalCount();//총게시물 수 
		System.out.println("총게시물수:"+totalCount);
		
		List<BoardVO> blist = this.boardService.getBoardList(b);//목록
		System.out.println("목록개수 : "+blist.size());
		
		//총페이지 수
		int maxpage = (int)((double)totalCount/limit+0.95);
		//현재 페이지에 보여질 시작페이지
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		//현재페이지에 보여질 마지막페이지
		int endpage = maxpage;
		
		if(endpage>startpage+10-1) endpage = startpage+10-1;		
		
		m.addAttribute("totalCount", totalCount);//totalCount키이름에 총게시물수를 저장
		request.setAttribute("blist", blist);//blist키이름에 목록을 저장
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);
		
		return "board/board_list";//뷰페이지 경로는 /WEB-INF/views/board/board_list.jsp
	}//board_list()

	// 게시판 내용보기+조회수 증가 => 스프링의 aop를 통한 트랜잭션 적용 대상
	@GetMapping("/board_cont")
	public ModelAndView board_cont(@RequestParam("bno") int bno, int page) {
		/*	@RequestParam("bno")는 request.getParameter("bno")와 기능이 같다. 즉, bno parameter 이름에 저장되어 져서 전달된
			번호값을 가져온다. int page도 page parameter 이름에 저장되어 져서 전달도ㅚㄴ 쪽번호를 가져온다. */

		BoardVO bc = this.boardService.getBoardCont(bno);
		String bcont = bc.getContent().replace("\n", "<br/>");
		// textarea에서 엔터키를 친 부분을 줄바꿈해준다.

		ModelAndView cm = new ModelAndView();
		cm.addObject("bc", bc);
		cm.addObject("page", page);
		cm.addObject("bcont", bcont);
		cm.setViewName("board/board_cont2"); // 뷰페이지 경로(뷰리졸브) => /WEB-INF/views/board/board_cont.jsp
		return cm;
	} // board_cont()

	// 게시판 수정폼
	@GetMapping("/board_edit")
	public void board_edit(Model m, int bno, int page) {
		// int bno라고 하면 bno parameter 이름에 저장되어 get으로 전달된 게시판 번호값을 받아온다.

		BoardVO eb = this.boardService.getBoardCont2(bno); // 조회수가 증가 안되는 번호에 해당하는 DB레코드를 가져온다.

		m.addAttribute("eb", eb);
		m.addAttribute("page", page);
		/* 	뷰페이지 경로를 설정하지 않은 이유는 메서드 반환타입이 void형이면 매핑주소가 뷰페이지 파일명이 된다.
		* 	뷰리졸브 경로(뷰페이지 경로) => /WEB-INF/views/board/board-edit.jsp */
	} // board_edit()

	// 게시판 수정완료
	@PostMapping("/board_edit")
	public String board_edit(BoardVO eb, int page, RedirectAttributes rttr) {
		/*	BoardVO eb라고 하면 빈클래스 변수명과 name parameter 이름이 같으면 eb객체에 hidden으로 전달된 번호, 수정글쓴이, 수정제목,
			수정내용이 저장되어 있다. 하지만 hidden page는 BoardVO 빈클래스에 변수로 정의 되어 있지 않아서 별도로 int page로 가져와야 한다. */
		this.boardService.updateBoard(eb);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/board_list?page="+page;
	} // board_edit() 메서드 오버로딩

	// 게시판 수정완료
	@RequestMapping(value="/board_del", method=RequestMethod.POST) // POST로 접근하는 매핑주소를 처리
	public ModelAndView board_del(int bno, int page, RedirectAttributes rttr) {

		this.boardService.delBoard(bno); // 번호를 기준으로 게시물 삭제
		rttr.addFlashAttribute("msg", "SUCCESS");

		ModelAndView dm = new ModelAndView();
		dm.setViewName("redirect:/board/board_list?page="+page);
		/*	setViewName()메서드 인자값 종류)
			1. redirect:/매핑주소가 들어가면 새로운 매핑주소로 이동
			2. 뷰페이지 경로가 들어가면 해당 뷰페이지가 보이게 된다. */
		return dm;
	}
}

















