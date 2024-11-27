package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Sample2Controller {

	@GetMapping("/doC") // doC mapping 주소 등록
	public String doC(@ModelAttribute("msg2") String msg) {
		/* @ModelAttribute("msg2")는 msg2 parameter 이름에 인자값을 담아서 전달한다. 웹주소차에 doC?msg2=값 형태의 주소창에 노출되는 get방식으로 전달한다. 보안성이 좋지 않다. */
		return "result"; // view page 경로는 /WEB-INF/views/result.jsp
	}
}