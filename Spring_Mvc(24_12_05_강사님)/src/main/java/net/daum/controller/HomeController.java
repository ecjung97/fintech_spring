package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* ReplyController.java에서 RestController로 백엔드서버 쪽 댓글 Rest API프로그램을 개발한 것을 비동기식 jQuery 아작스 뷰페이지 
 * 개발을 위한 일반 컨트롤러 클래스이다.
 */
@Controller
public class HomeController {

	//아작스 댓글
	@RequestMapping("/test")
	public void test() {
		//리턴타입이 없는 void형이면 매핑주소인 test가 뷰페이지 파일명이 된다.
		
	}
}
