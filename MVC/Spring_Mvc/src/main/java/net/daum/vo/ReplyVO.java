package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ReplyVO { // 댓글 데이터 저장bean class => TBL_REPLY table의 column명과 일치하는 bean class 변수명을 정의

    private int rno; // 댓글번호
    private int bno; // 게시판 번호
    private String replyer; // 댓글 작성자
    private String replytext; // 댓글 내용
    private String regdate; // 댓글 등록날짜
    private String updatedate; // 댓글 수정날짜
}
