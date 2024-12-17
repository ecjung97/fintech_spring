package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.daum.vo.ProductVO;

@Controller
public class Sample5Controller {

	@GetMapping("/doJSON") // doJSON 매핑주소 등록
	public @ResponseBody ProductVO doJSON() {
		/* @ResponseBody 애노테이션을 사용하면 jsp파일을 만들지 않고도 웹브라우저에 키,값쌍의 JSON데이터를 쉽게 만들 수 있다.여기서 메서드
		 * 반환타입이 ProductVO빈타입이면 해당클래스의 변수명이 json데이터의 키이름이 된다.
		 */
		ProductVO p=new ProductVO("LG tv",1500000);
		return p;
	}
}
