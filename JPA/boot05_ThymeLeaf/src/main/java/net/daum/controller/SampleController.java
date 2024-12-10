package net.daum.controller;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.vo.MemberVO;

@Controller
public class SampleController {

	@GetMapping("/start_thymeLeaf")
	public ModelAndView start_thymeLeaf() {
		
		ModelAndView tm = new ModelAndView();
		tm.addObject("greeting", "안녕하세요. 타입리프입니다.");
		tm.setViewName("./thymeLeaf/start"); // 타임리프(ThymeLeaf) 뷰페이지 경로가 src/main/resources/templates/thymeLeaf/start.html
		return tm;
	}
	
	@RequestMapping("/listTest")
	public void listTest(Model model) {
		// 리턴 타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다. listTest.html
		
		List<String> list = new ArrayList<>();
		
		for (int i=1;i<=10;i++) {
			list.add("Data : " + i); // 컬렉션에 원소값 추가
		}
		
		model.addAttribute("name", "타임리프 연습");
		model.addAttribute("list", list);
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		MemberVO vo=new MemberVO(123, "u00", "p00", "홍길동", new Timestamp(System.currentTimeMillis()));
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value="/sample3", method=RequestMethod.GET)
	   public void sample3(Model model) {
	      List<MemberVO> list = new ArrayList<>();
	      
	      for(int i = 0; i < 10; i++) {
	         list.add(new MemberVO(123 + i, "u0" + i, "p0" + i, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
	      }
	      
	      model.addAttribute("list", list);
	   }
	
	@GetMapping("/sample04")
	public void sample04(Model model) {
		
		List<MemberVO> list = new ArrayList<>();
		
		for(int i=0; i<=10; i++) {
			list.add(new MemberVO(i+1, "u000"+ i % 3, "p0000"+i%3, "홍길동"+(i+1),
					new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("list", list);
	} // sample04()
	
	@GetMapping("/sample05")
	public String sample05(HttpServletRequest request) {
		String result = "SUCCESS";
		
		request.setAttribute("result", result);
		
		return "result05";
	}

}







