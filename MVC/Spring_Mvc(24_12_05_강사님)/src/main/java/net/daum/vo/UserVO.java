package net.daum.vo;

import lombok.Data;

@Data
public class UserVO { //tbl_user테이블의 컬럼명과 일치하는 빈클래스 변수명을 정의

	private String uid2;
	private String upw;
	private String uname;
	private int upoint;//메시지가 저장되면 포인트 점수 10점 업데이트
	
}
