package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter // lombok.jar 에 의한 setter() 메소드 자동 추가
@Getter // lombok.jar 에 의한 getter() 메소드 자동 추가
// Spring MVC 게시판 빈 클래스. 빈 클래스 변수명과 네임 파라미터 이름과 해당 테이블(tbl_board) 컬럼명을 같게 한다.
public class BoardVO {
	private int bno; // 게시판 번호
	private String writer; // 작성자
	private String title; // 제목
	private String content; // 내용
	private String viewcnt; // 조회수
	private String regdate; // 등록날짜
	
	// 페이지(쪽나누기) 관련 변수
	private int startrow; // 시작행 번호
	private int endrow; // 끝행 번호
	
}
