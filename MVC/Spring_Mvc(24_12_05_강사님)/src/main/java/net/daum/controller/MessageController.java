package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;

@RestController
@RequestMapping("/message") //컨트롤러 자체 매핑주소 message 등록. 이 매핑주소를 등록한 이유는 브라우저 주소창에 경로구분하기 위한 용도이다.
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//메시지 추가
	@RequestMapping(value="/insertMessage", method=RequestMethod.POST) //post 로 접근하는 매핑주소를 처리, insertMessage 매핑
	//주소 등록
	public ResponseEntity<String> insertM(@RequestBody MessageVO vo){
		/* @RequestBody MessageVO vo 는 전송된 JSON 데이터를 MessageVO 객체타입으로 변환을 해준다. 
		 */
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.addMessage(vo);//메시지 추가
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);//메시지 추가가 성공하면 SUCCESS 문자와 HttpStatus.OK상태
			//코드인 200 정상상태코드가 반환된다.
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);//예외 에러 메시지와 나쁜상태코드를 반환
		}
		return entity;
	}
}
