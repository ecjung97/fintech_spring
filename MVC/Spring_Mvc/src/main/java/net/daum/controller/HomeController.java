package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* ReplyController.java에서 RestController로 Backend server 쪽 댓글 Rest API program을 개발한 것을 비동기식 jQuery ajax view page
개발을 위한 일반 controller class이다. */
@Controller
public class HomeController {

    // ajax 댓글
    @RequestMapping("/test")
    public void test() {
        // return type이 없는 void형이면 mapping주소인 test가 view page 파일명이 된다.

    }
}
