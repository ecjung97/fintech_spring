package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter // lombok.jar에 의한 setter()메서드 자동 추가
@Getter // lombok.jar에 의한 getter()메서드 자동 추가
public class BoardVO { /* Spring MVC 게시판 빈클래스이다. 빈클래스 변수명과 name parameter 이름과 해당 테이블(tbl_board) 컬럼명을 같게 한다. */

    private int bno; // 게시판 번호
    private String writer; // 작성자
    private String title; // 제목
    private String content; // 글내용
    private int viewcnt; // 조회수
    private String regdate; // 등록날짜

    // 페이징(쪽 나누니) 관련 변수
    private int startrow; // 시작행 번호
    private int endrow; // 끝행 번호

}
