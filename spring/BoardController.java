package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller // @Controller 애노테이션을 설정함으로써 스프링에서 컨트롤러로 인식한다.
@RequestMapping("/board/*") // 컨트롤러 자체 매핑주소 등록
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 스프링 MVC 게시판 글쓰기 폼
	@RequestMapping(value="board_write", method=RequestMethod.GET) // GET으로 접근하는 매핑주소를 처리
	// 1. 메소드 반환타입이 void형이면 매핑주소가 뷰 페이지 파일명이 된다.
	// 2. 뷰 페이지 경로(view resolve)는 /WEB-INF/views/board/board_write.jsp
	// 3. HtttpServletRequest 서블릿은 클라이언트가 입력한 정보를 서버로 가져올 때 사용한다.
	public void board_write(HttpServletRequest request) {}
	
	
	// 게시판 저장
	@PostMapping("/board_write") // @PostMapping은 post로 요청한 매핑 주소를 처리
	// board_write.jsp의 네임 파라미터 이름과 BoardVO.java의 빈 클래스 변수명이 같으면 
	// BoardVO b라고만 해도 글쓴이, 글제목, 글내용이 한꺼번에 저장된다.
	public ModelAndView board_write_ok(BoardVO b, RedirectAttributes rttr) {
		this.boardService.insertBoard(b); // 게시판 저장
		
		// msg 키에 SUCCESS 문자열을 저장해서 다른 매핑주소로 전달한다. 주소창에 노출이 안되기 때문에 보안에 좋다.
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		// ModelAndView 스프링 api 생성자 인자값으로 "redirect:/매핑주소"를 전달하면 전달한 매핑주소로 이동하는 것이고
		// 뷰 페이지 경로가 들어가면 해당 뷰 페이지가 보인다.
		return new ModelAndView("redirect:/board/board_list");
	}
	
	// 페이징 없는 게시판 목록
	@RequestMapping("/board_list") // GET or POST 방식으로 요청하는 매핑주소를 처리. /board_list 매핑 주소 등록
	public String board_list(Model m, HttpServletRequest request, BoardVO b) {
		// 총 게시물 수
		int totalCount = this.boardService.getTotalCount();
//		System.out.println("총 게시물 수 : " + totalCount);
		
		// 목록
		List<BoardVO> blist = this.boardService.getBoardList();
//		System.out.println("목록 개수 : " + blist.size());
		
		// totalCount 키에 총 게시물 수를 저장
		m.addAttribute("totalCount", totalCount);
		// blist 키에 목록을 저장
		request.setAttribute("blist", blist);
		
		// 뷰 페이지 경로는 View Resolve라고도 부른다. /WEB-INF/views/board/board_list.jsp
		return "board/board_list";
	}
	
	
	// 페이징 게시판 목록
}

