package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Sample4Controller {

	@GetMapping("/doE") // doE매핑주소 등록, GET으로 접근하는 매핑주소를 처리
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "안녕하세요.");//문자열 키이름 msg에 Object타입으로 업캐스팅 된 값 '안녕하세요.'를 담아서 전달.
		/* 다른 매핑주소로 msg 키이름에 값을 담아서 전달하는데 백엔드 서버에서 실행이 되다 보니 브라우저 노출안된다. 보안이 좋다. 
		 */
		return "redirect:/doF";// doE매핑주소가 실행이 되면 doF 다른매핑주소로 이동한다.
	}
	
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String result) {
		System.out.println("전달되어진 값 : "+ result);
	}
}
