package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
//내장 톰켓 WAS서버 10버전부터는 서블릿 패키지 경로가 javax.servlet이 아니고 jakarta.servlet이다.

@Controller //@Controller 애너테이션을 설정하면 스프링에서 해당 컨트롤러 클래스를 인터넷 웹에서 인식하는 스프링 컨트롤러로 인식한다.
public class SampleController {

	@RequestMapping("/doA") //doA매핑주소 등록, GET OR POST방식으로 접근하는 doA매핑주소가 웹주소창에서 실행되면 해당 메서드를 호출
	public void doA7(HttpServletRequest request, Model m) {
		//리턴타입이 없는 void형이면 해당 URL-pattern  매핑주소가 뷰페이지 파일명이 된다. 결국 매핑주소가 doA이므로 뷰페이지 경로인
		//뷰리졸브는 /WEB-INF/views/doA.jsp
		/* HttpServletRequest 서블릿은 사용자폼에서 입력한 데이터를 서버로 가져올 때 사용한다. 
		 */
		
		request.setAttribute("key_view", "doA.jsp가 실행됨");
		//m.addAttribute("key_view","doA.jsp가 실행됨");//문자열 키이름인 key_view에 값이 저장됨.
		System.out.println("doA매핑주소가 실행됨.");
	}//doA()
	
	@GetMapping("doB") //doB매핑주소 등록, @GetMapping 애너테이션은 get으로 접근하는 매핑주소를 처리
	public void doB(Model m) {//뷰리졸브 경로는 /WEB-INF/views/doB.jsp(반환타입이 없는 void형이면 매핑주소인 doB가 뷰페이지 파일명
		//doB.jsp가 된다.
		m.addAttribute("cityName", "서울시 종로구");//cityName키이름에 '서울시 종로구'값을 담는다.
	}
}
