package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageVO { //tbl_message 테이블의 컬럼명과 일치하는 빈클래스 정의

	private int mid;
	private String targetid;// 외래키로 설정됨. tbl_user테이블의 uid2컬럼 회원아이디값만 저장된다.
	private String sender;//메시지를 보낸 사람
	private String message;//보낸 메시지
	private String senddate;//보낸 날짜
	
}
