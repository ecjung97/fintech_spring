package net.daum.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 람복 라이브러리에서 제공하는 애너테이션으로, 클래스에 선언된 모든 필드(속성)를 매개변수(전달인자)로 받는 생성자를 자동으로 생성.
public class MemberVO {

	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private Timestamp regdate;
	
}
