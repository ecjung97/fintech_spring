package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // @Controller Annotation을 설정함으로써 Spring에서 Controller로 인식한다.
public class BoardController {

    @Autowired
    private BoardService boardService;

}
