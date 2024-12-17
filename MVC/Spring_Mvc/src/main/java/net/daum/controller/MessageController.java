package net.daum.controller;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message") // Controller 자체 mapping주소 message 등록. 이 mapping주소를 등록한 이유는 browser 주소창에 경로구분하기 위한 용도이다.
public class MessageController {

    @Autowired
    private MessageService messageService;

    // message 추가
    @RequestMapping(value = "/insertMessage", method = RequestMethod.POST) // post 로 접근하는 mapping주소를 처리, insertMessage mapping주소 등록
    // 주소 등록
    public ResponseEntity<String> insertM(@RequestBody MessageVO vo) {
        /* @RequestBody Annotation은 전송된 JSON data를 MessageVO 객체타입으로 변환을 해준다. */
        ResponseEntity<String> entity = null;

        try {
            this.messageService.addMessage(vo); // message 추가
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK); // message 추가 성공 시 SUCCESS 문자와 HttpStatus.OK상태코드인 200 정상상태코드가 반환됨.
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
