package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller
// @Controller 애노테이션을 설정함으로써 스프링에서 컨트롤러로 인식한다.
@RequestMapping("/board/*") // 컨트롤러 자체 매핑주소 등록
public class BC {
    @Autowired
    private BoardService boardService;

    // 2024-11-28 게시판 작성 코드
    // 스프링 MVC 게시판 글쓰기 폼
    @RequestMapping(value="board_write", method=RequestMethod.GET) // GET으로 접근하는 매핑주소를 처리
    public void board_write(HttpServletRequest request) {
        // 1. 메소드 반환타입이 void형이면 매핑주소가 뷰 페이지 파일명이 된다.
        // 2. 뷰 페이지 경로(view resolve)는 /WEB-INF/views/board/board_write.jsp
        // 3. HtttpServletRequest 서블릿은 클라이언트가 입력한 정보를 서버로 가져올 때 사용한다.

        // 2024-11-29 게시판리스트 책갈피 기능 추가를 위한 코드
        // 페이징에서 책갈피 기능 구현 소스
        int page = 1;
        if(request.getParameter("page") != null ) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
    }


    // 게시판 저장
    @PostMapping("/board_write") // @PostMapping은 post로 요청한 매핑 주소를 처리
    public ModelAndView board_write_ok(BoardVO b, RedirectAttributes rttr) {
        // board_write.jsp의 네임 파라미터 이름과 BoardVO.java의 빈 클래스 변수명이 같으면
        // BoardVO b라고만 해도 글쓴이, 글제목, 글내용이 한꺼번에 저장된다.

        this.boardService.insertBoard(b); // 게시판 저장
        // msg키에 SUCCESS 문자열을 저장해서 다른 매피중소로 전달한다. 주소창에 노출이 안되기 때문에 보안이 좋다
        rttr.addFlashAttribute("msg", "SUCCESS");
        // ModelAndView 스프링 api 생성자 인자값으로 "redirect:/매핑주소"를 전달하면 전달한 매핑주소로 이동하는 것이고
        // 뷰 페이지 경로가 들어가면 해당 뷰 페이지가 보인다.
        return new ModelAndView("redirect:/board/board_list");
    }

    // 페이징 없는 게시판 목록
    @RequestMapping("/board_list") // GET or POST 방식으로 요청하는 매핑주소를 처리. /board_list 매핑 주소 등록
    public String board_list(Model m, HttpServletRequest request, BoardVO b) {


        int page = 1;
        // 현재 쪽 번호
        int limit = 10;
        // 한페이지에 보여지는 목록개수

        if(request.getParameter("page") != null) {
            // get으로 전달된 쪽번호가 있는 경우 실행
            page = Integer.parseInt(request.getParameter("page"));
            // 쪽번호를 정수숫자로 변경해서 저장
        }
        b.setStartrow((page-1)*10+1);
        // 시작 행번호
        b.setEndrow(b.getStartrow()+limit-1);
        // 끝 행번호

        // 총 게시물 수
        int totalCount = this.boardService.getTotalCount();
//      // 게시물코드 작성이 완료되어 콘솔창에 불필요한 출력코드 주석처리
//      System.out.println("총 게시물 수 : " + totalCount);

        // 목록
        List<BoardVO> blist = this.boardService.getBoardList(b);
//      // 게시물코드 작성이 완료되어 콘솔창에 불필요한 출력코드 주석처리
//      System.out.println("목록 개수 : " + blist.size());

        // 총페이지 수
        int maxpage = (int)((double)totalCount/limit+0.95);
        // 현재 페이지에 보여질 시작페이지

        int startpage = (((int)((double)page/10+0.9))-1)*10+1;
        // 현재페이지에 보여질 마지막 페이지

        int endpage = maxpage;

        if(endpage > startpage+10-1) endpage = startpage+10-1;


        // totalCount 키에 총 게시물 수를 저장
        m.addAttribute("totalCount", totalCount);

        // blist 키에 목록을 저장
        request.setAttribute("blist", blist);

        m.addAttribute("startpage", startpage);
        m.addAttribute("endpage", endpage);
        m.addAttribute("maxpage", maxpage);
        m.addAttribute("page", page);


        // 뷰 페이지 경로는 View Resolve라고도 부른다. /WEB-INF/views/board/board_list.jsp
        return "board/board_list";
    }


    // 2024-11-29 게시판 내용 보기 코드 작성
    // 게시판 내용보기 + 조회수 증가 => 스프링 AOP를 통한 트랜잭션 적용 대상
    @GetMapping("/board_cont")
    public ModelAndView board_cont(@RequestParam("bno") int bno, int page) {
        /*
         * @RequestParam("bno")는 request.getParameter("bno")와 같은 의미이다.
         * 즉 bno 파라미터 이름에 저장되어 전달된 번호값을 get방식으로 가져온다.
         * int page도 page파라미터 이름에 저장되어 전달된 쪽번호를 가져온다.
         */

        BoardVO bc = this.boardService.getBoardCont(bno);
        // 내용보기와 조회수 증가

        String bcont = bc.getContent().replace("\n", "<br/>");
        // textarea에서 엔터키 친 부분을 줄바꿈해준다.

        ModelAndView cm = new ModelAndView();
        cm.addObject("bc", bc);
        cm.addObject("page", page);
        // 페이징 목록에서 책갈피 기능때문에 쪽번호 저장
        cm.addObject("bcont", bcont);


        cm.setViewName("board/board_cont2");

//      2024-12-03 board_cont2 : 댓글관력 마무리하고 만든 cont2 로인한 주석처리
//      cm.setViewName("board/board_cont");
        // 뷰페이지 경로(뷰리졸브) => /WEB-INF/views/board/board_cont.jsp

        return cm;
    };


    // 2024-11-29 게시판 수정 폼 코드 작성
    @GetMapping("/board_edit")
    public void board_edit(Model m, int bno, int page) {
        // int bno라고 하면 bno파라미터 이름에 저장되어 get으로 전달된 게시판 번호값을 받아온다.


        BoardVO eb = this.boardService.getBoardCont2(bno);
        // 조회수가 증가 안되는 번호에 해당하는 DB레코드를 가져온다.

        m.addAttribute("eb", eb);
        m.addAttribute("page", page);
        // 뷰페이지 경로를 설정 하지 않는 이유는 메서드 반환 타입이 void형이면 매핑주소가 뷰페이지 파일명이 된다.
        // 뷰리졸브 경로(뷰페이지 경로) => /WEB-INF/views/board/board_edit.jsp
    };


    // 2024-11-29 게시판 수정 완료 코드
    @PostMapping("/board_edit")
    public String board_edit(BoardVO eb, int page, RedirectAttributes rttr) {
        /*
         * BoardVO eb라고 하면 빈클래스 변수명과 네임파라미터 이름이 같으면 eb객체에 히든으로 전달된 번호, 수정글쓴이, 수정제목, 수정내용이 저장되어 있다.
         * 하지만 히든 page는 BoardVO 빈클래스에 변수로 정의되어 있지 않아서 별도로 int page로 가져가야 한다.
         */

        this.boardService.updateBoard(eb);
        // 번호를 기준으로 수정

        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/board/board_list?page="+page;
    };

    // 2024-11-29 게시판 삭제 완료 코드
//   @PostMapping("/board_delete")
//   public String board_del(int bno, int page) {
//      this.boardService.deleteBoard(bno);
//      return "redirec:/board/board_list?page="+page;
//   }
    @RequestMapping(value="/board_del", method=RequestMethod.POST) // Post로 접근하는 매핑주소를 처리
    public ModelAndView board_del(int bno, int page, RedirectAttributes rttr) {
        this.boardService.delBoard(bno);
        rttr.addFlashAttribute("msg", "SUCCESS");

        ModelAndView dm = new ModelAndView();

        dm.setViewName("redirect:/board/board_list?page="+page);
        /*
         * setViewName() 메서드 인자값 종류
         *    1. redirect : /매핑주소 가 들어가면 새로운 매핑주소로 이동
         *    2. 뷰페이지 경로 : 해당 뷰페이지가 보이게 된다.
         */

        return dm;
    }

}

