package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MessageVO { // tbl_message table의 column명과 일치하는 bean-class 정의

    private int mid;
    private String targetid; // 외래키로 설정됨. tbl_user table의 uid2 column 회원 id값만 저장된다.
    private String sender; // message를 보낸 사람
    private String message; // 보낸 message
    private String senddate; // 보낸 날짜
}
