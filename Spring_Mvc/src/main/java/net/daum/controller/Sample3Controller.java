package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.daum.vo.ProductVO;

@Controller
public class Sample3Controller {

	@RequestMapping(value="/namePrice", method=RequestMethod.GET) // namePrice mapping 주소 등록, get으로 접근하는 mapping주소 처리
	public ModelAndView namePrice() {
		ProductVO p=new ProductVO("노트북", 2500000);
		
		ModelAndView pm = new ModelAndView();
		pm.addObject("p", p); // p키이름에 p객체 저장
		pm.setViewName("shop/noteBook"); // view resolve 경로인 view page 경로는 /WEB-INF/views/shop/noteBook.jsp
		return pm;
	}
}
