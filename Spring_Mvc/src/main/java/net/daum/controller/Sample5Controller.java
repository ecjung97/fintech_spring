package net.daum.controller;

import net.daum.vo.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample5Controller {

    @GetMapping("/doJSON") // doJSON mapping주소 등록
    public @ResponseBody ProductVO doJSON() {
        /*
            @ResponseBody Annotation을 사용하면 jsp파일을 만들지 않고도 웹브라우저에 키,값쌍의 JSON data를 쉽게 만들 수 있다.
            여기서 method 반환타입이 ProductVO bean type이면 해당 class의 변수명이 json data의 키이름이 된다.
         */
        ProductVO p = new ProductVO("LG tv", 1500000);
        return p;
    }
}
