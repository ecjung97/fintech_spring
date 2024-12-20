package net.daum.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.MemberService;
import net.daum.vo.MemberVO;
import net.daum.vo.ZipCode2VO;
import net.daum.vo.ZipCodeVO;

@Controller
public class MemberController { // 사용자 회원관리 컨트롤러
	
	@Autowired
	private MemberService memberService;
	
	// 로그인 폼
	@GetMapping("/member_Login")
	public ModelAndView member_Login() {
		ModelAndView m = new ModelAndView();
		m.setViewName("member/member_login"); // 뷰페이지 경로는 /WEB-INF/views/member/member_login.jsp
		return m;
	} // member_Login()
	
	// 회원가입 폼
	@GetMapping("/member_join")
	public ModelAndView member_join() {
		String[] phone = {"010", "011", "019"};
		String[] email = {"gmail.com", "naver.com", "daum.net", "nate.com", "직접입력"};
		
		ModelAndView jm = new ModelAndView("member/member_join");
		jm.addObject("phone", phone);
		jm.addObject("email", email);
		return jm;
	} // member_join()
	
	// 아이디 중복검색
	@PostMapping("/member_idcheck")
	public ModelAndView member_idcheck(String id, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberVO dm = this.memberService.idCheck(id); // 아이디에 해당하는 회원정보 검색
		
		int re = -1; // 중복아이디가 없을 때 반환값
		
		if (dm != null) { // 중복아이디가 있는 경우
			re = 1;
		}
		out.println(re); // 값반환
		
		return null;
	} // member_idcheck()
	
	// 우편검색 검색폼 공지창
	@GetMapping("/zip_find")
	public ModelAndView zip_find() {
		ModelAndView zm = new ModelAndView();
		zm.setViewName("member/zip_Find");
		return zm;
	} // zip_find()
	
	//우편주소 검색 결과
	@PostMapping("/zip_find_ok")
	public ModelAndView zip_find_ok(String dong) {
		List<ZipCodeVO> zlist=this.memberService.zipFind("%"+dong+"%");
		//SQL문 검색에서 % 와일드 카드는 하나이상의 임의의 모르는 문자와 매핑 대응한다.
			
		List<ZipCode2VO> zlist2 = new ArrayList<>();
			
		for(ZipCodeVO z:zlist) {
			ZipCode2VO z2=new ZipCode2VO();
				
			z2.setZipcode(z.getZipcode()); // 우편번호
			z2.setAddr(z.getSido()+" "+z.getGugun()+" "+z.getDong()); // 시도 구군 동을 저장
			
			zlist2.add(z2);
		}
		
		ModelAndView zm = new ModelAndView("member/zip_Find");
		zm.addObject("zipcodelist", zlist2);
		zm.addObject("dong", dong);
		return zm;
	} // zip_find_ok()
}
