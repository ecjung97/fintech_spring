package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

@RestController /* @RestController 애너테이션으로 뷰페이지를 직접 만들지 않고도 원하는 문자열 객체,JSON,XML데이터를 만들수 있다.
*/
@RequestMapping("/rest") //rest라는 컨트롤러 자체 매핑주소 등록
public class Sample6Controller {

	@GetMapping("/beginRest")
	public String begin() {
		return "Rest 시작";//문자열 반환
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		//메서드 리턴타입이 SampleVO빈타입이면 해당 클래스 변수명이 JSON데이터의 키이름이 된다.
		SampleVO vo = new SampleVO();
		vo.setFirstName("홍");
		vo.setLastName("길동");
		vo.setMno(7);
		
		return vo;
	}
	
	@GetMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<>();
		
		for(int i=1;i<=7;i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕님");
			
			list.add(vo);//컬렉션에 추가
		}
		return list;
	}//sendList()
	
	//키,값 쌍의 컬렉션 Map타입 JSON
	@GetMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i=1;i<=5;i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("이");
			vo.setLastName("길동");
			
			map.put(i, vo);//컬렉션에 키,값 쌍으로 저장
		}
		return map;
	}//sendMap()
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/* @RestController는 별도의 뷰페이지를 만들지 않고 Rest프로그램을 개발하다 보니, 결과 데이터에 대한 예외적인 오류 상황이 발생할 수 
		 * 있다. 이런 경우는 스프링에서 제공하는 ResponseEntity api를 사용하면 보다 더 개발자가 나쁜상태 코드인 404, 500도 함께 브라우저로
		 * 전송되기 때문에 좀더 세밀한 에러 제어를 할 수 있다. 여기서는 나쁜상태 코드 BAD_REQUEST인 400이 브라우저로 전송된다.
		 */
	}
	
	//정상적인 json데이터와 404(자원을 찾지 못했을 때) 나쁜 상태코드가 함께 브라우저로 전송
	@GetMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=1;i<=3;i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("다산");
			vo.setLastName("정약용");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);//NOT_FOUND는 404의미
	}//sendListNot()
}































