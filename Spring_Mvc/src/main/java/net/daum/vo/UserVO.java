package net.daum.vo;

import lombok.Data;

@Data
public class UserVO { // tbl_user table과 column명과 일치하는 bean-class 변수명 정의

    private String uid2;
    private String upw;
    private String uname;
    private int upoint; // message가 저장되면 point 10점 추가
}
