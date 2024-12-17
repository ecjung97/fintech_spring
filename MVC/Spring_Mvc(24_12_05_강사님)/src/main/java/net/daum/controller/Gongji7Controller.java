package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.Gongji7Service;
import net.daum.vo.Gongji7VO;

@Controller
public class Gongji7Controller {

	@Autowired
	private Gongji7Service gongji7Service;
	
	//공지 글쓰기폼
	@GetMapping("/g7_write")
	public ModelAndView g_write(HttpServletRequest request) {
		int page = 1;//현재 쪽 번호	
		if(request.getParameter("page") != null) {
			//get으로 전달된 쪽번호가 있는 경우 실행
			page = Integer.parseInt(request.getParameter("page"));
			//쪽번호를 정수숫자로 변경해서 저장
		}
		request.setAttribute("page",page);
		
		return new ModelAndView("gongji/gongji7_write");
	}//g_write()
	
	//공지 저장
	@PostMapping("/g7_write")
	public String gongji_insert(Gongji7VO g){
		this.gongji7Service.insert(g);//공지 저장
		return "redirect:/gongji/gongji7_list";
	}//gongji_insert()
	
	//페이징 공지목록
	@GetMapping("/gongji/gongji7_list")
	public void gongji_list(Gongji7VO g,HttpServletRequest request, Model m){
		int page = 1;//현재 쪽 번호
		int limit = 5;//한페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
			//get으로 전달된 쪽번호가 있는 경우 실행
			page = Integer.parseInt(request.getParameter("page"));
			//쪽번호를 정수숫자로 변경해서 저장
		}
		g.setStartrow((page-1)*5+1); //시작행번호
		g.setEndrow(g.getStartrow()+limit-1);//끝행번호
		
		int totalCount = this.gongji7Service.getTotalCount();//총게시물 수 
		//System.out.println("총게시물수:"+totalCount);
		
		List<Gongji7VO> glist = this.gongji7Service.getGongjiList(g);//목록
		//System.out.println("목록개수 : "+blist.size());
		
		//총페이지 수
		int maxpage = (int)((double)totalCount/limit+0.95);
		//현재 페이지에 보여질 시작페이지
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		//현재페이지에 보여질 마지막페이지
		int endpage = maxpage;
		
		if(endpage>startpage+10-1) endpage = startpage+10-1;		
		
		m.addAttribute("totalCount", totalCount);//totalCount키이름에 총게시물수를 저장
		request.setAttribute("glist", glist);//blist키이름에 목록을 저장
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);
	}//gongji_list() 
	
	//조회수 증가+내용보기
	@GetMapping("/gongji7_cont")
	public ModelAndView getGongjiView(int gno, int page) {
		
		this.gongji7Service.updateHit(gno);//조회수 증가
		Gongji7VO gc = this.gongji7Service.getGongjiCont(gno);
		
		ModelAndView cm = new ModelAndView();
		cm.addObject("page", page);
		cm.addObject("gc", gc);
		cm.setViewName("gongji/gongji7_cont");//뷰페이지 경로=>/WEB-INF/views/gongji/gongji7_cont.jsp
		return cm;
	}//getGongjiView()
	
	//공지 수정폼
	@GetMapping("/gongji7_Edit")
	public String gongji_Edit(int gno,int page, Model em) {
		
		Gongji7VO gc = this.gongji7Service.getGongjiCont(gno);
		em.addAttribute("gc", gc);
		em.addAttribute("page", page);
		return "gongji/gongji7_edit";
	}//gongji_Edit()
	
	//공지 수정완료
	@PostMapping("/gongji7_Edit_ok")
	public ModelAndView gongji_Edit_ok(Gongji7VO eg, int page) {
		this.gongji7Service.updateGongji(eg);
		
		ModelAndView em=new ModelAndView();
		em.setViewName("redirect:/gongji7_cont");
		em.addObject("gno", eg.getGno());
		em.addObject("page", page);
		return em;// gongji7_cont?gno=공지번호&page=쪽번호 가 반환된다.
	}//gongji_Edit_ok()
	
	//공지 삭제
	@GetMapping("/gongji7_Del") //자바스크립트의 location 객체에 의해서 이동하는 메서드 방식은 get 이다.
	public ModelAndView gong_Del(int gno,int page) {
		this.gongji7Service.delGongji(gno);//번호를 기준으로 공지삭제
		
		ModelAndView dm=new ModelAndView("redirect:/gongji/gongji7_list");
		//dm.addObject("page", page);
		return dm;// 반환값은 gongji7_list?page=쪽번호
	}
}










