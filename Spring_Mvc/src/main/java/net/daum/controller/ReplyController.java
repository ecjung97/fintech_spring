package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.daum.service.ReplyService;
import net.daum.vo.ReplyVO;

@RestController // @RestController 추가하면 해당 controller는 REST api service program을 개발 가능한 controller가 된다.
@RequestMapping("/replies") // control 자체 mapping주소 replies 등록
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // 댓글 등록
    @PostMapping("/insertReply") // post로 접근하는 Mapping주소를 처리, insertReply Mapping 주소 등록
    public ResponseEntity<String> insertReply(@RequestBody ReplyVO vo) {
        // @Request Annotation은 전송된 키, 값 쌍의 JSON data를 ReplyVO 객체타입으로 변환시킨다.
        ResponseEntity<String> entity=null;

        try {
            this.replyService.addReply(vo); // 댓글 등록
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK); // 댓글 저장 성공 시 정상상태코드 200과 SUCCESS문자를 반환.
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            // 예외 에러가 발생했을 때 나쁜 상태코드 문자열이 반환
        }
        return entity;
    } // insertReply()

    // 게시판 번호에 해당하는 댓글 목록
    @GetMapping("/all/{bno}") // get으로 접근하는 매핑주소를 처리
    public ResponseEntity<List<ReplyVO>> replyList(@PathVariable("bno") int bno) {
        // @PathVariable("bno")는 매핑주소 {bno}로 전달된 게시판 번호값을 추출하는 용도로 사용
        ResponseEntity<List<ReplyVO>> entity=null;

        try {
            entity = new ResponseEntity<>(this.replyService.listReply(bno), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    } // replyList()
}
