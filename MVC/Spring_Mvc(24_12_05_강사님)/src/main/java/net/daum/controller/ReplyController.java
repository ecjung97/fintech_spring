package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.ReplyService;
import net.daum.vo.ReplyVO;

@RestController //@RestController 추가하면 해당 컨트롤러는 REST api 서비스 프로그램을 개발 가능한 컨트롤러가 된다.
@RequestMapping("/replies") //컨트롤 자체 매핑주소 replies 등록
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//댓글 등록
	@PostMapping("/insertReply") //post로 접근하는 매핑주소를 처리, insertReply매핑주소 등록
	public ResponseEntity<String> insertReply(@RequestBody ReplyVO vo){
		//@RequestBody 애노테이션은 전송된 키,값 쌍의 JSON데이터를 ReplyVO 객체타입으로 변환시킨다.
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.addReply(vo);//댓글 등록
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);//댓글 저장 성공시 정상상태코드 200과  SUCCESS문자를
			//반환한다.
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//예외 에러가 발생했을 때 나쁜 상태코드 문자열이 반환
		}
		return entity;
	}//insertReply()
	
	//게시판 번호에 해당하는 댓글 목록
	@GetMapping("/all/{bno}") //get으로 접근하는 매핑주소를 처리
	public ResponseEntity<List<ReplyVO>> replyList(@PathVariable("bno") int bno){
		//@PathVariable("bno")는 매핑주소 {bno}로 전달된 게시판 번호값을 추출하는 용도로 사용
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity = new ResponseEntity<>(this.replyService.listReply(bno), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//replyList()
	
	//댓글 수정
	@PutMapping("/{rno}") //@PutMapping은 수정할 때 호출되는 매핑주소
	public ResponseEntity<String> replyEdit(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		/* 브라우저 주소창에 /{rno} 전달된 댓글번호를 @PathVariable("rno")로 구한다. Arc에서 전송된 JSON 데이터는 @RequestBody에 의해서
		 * ReplyVO객체타입으로 변환을 한다.하지만 주소창으로 전달된 댓글번호인 rno는 JSON 데이터가 아니기 때문에 ReplyVO 객체타입으로 변환을 못
		 * 해서 별도로 저장을 해야 한다.
		 */
		ResponseEntity<String> entity=null;
		
		try {
			vo.setRno(rno);//댓글 번호 저장
			this.replyService.updateReply(vo);//댓글 수정
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);//댓글 수정 성공시 200정상상태 코드와 SUCCESS문자 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);//예외 에러가 발생하면 나쁜상태코드가 반환
		}		
		return entity;
	}//replyEdit()
	
	//댓글 삭제
	@DeleteMapping("/{rno}") //@DeleteMapping 삭제시 호출
	public ResponseEntity<String> deleteReply(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.removeReply(rno);//댓글 삭제
			/* 문제) mybatis 매퍼태그에서 설정할 유일 아이디명을 reply_del로 해서 댓글 번호를 기준으로 삭제되게 만들어 보자. 
			 */
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//deleteReply()
}















