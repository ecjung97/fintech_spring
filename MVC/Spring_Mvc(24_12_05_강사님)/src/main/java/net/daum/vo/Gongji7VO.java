package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Gongji7VO {//TBL_gongji7테이블의 컬럼명,gongji7_write.jsp의 네임피라미터 이름과 빈클래스 변수명이 같은 데이터 저장빈
	//클래스를 정의한다.

	private int gno;//공지 번호
	private String gname;//공지 작성자
	private String gtitle;//공지 제목
	private String gcont;//공지 내용
	private int ghit;//조회수
	private String gdate;//등록날짜

	//페이징(쪽나누기) 관련 변수
	private int startrow;//시작행 번호
	private int endrow;//끝행 번호
}
