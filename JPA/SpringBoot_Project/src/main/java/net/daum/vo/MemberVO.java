package net.daum.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="member") // member 테이블 생성
@EqualsAndHashCode(of="mem_id")
public class MemberVO { // 사용자단, 관리자단 회원관리 엔티티빈 클래스
	
	@Id // 유일키로 사용도리 기본키 컬럼 => primary key
	private String mem_id; // 회원아이디
	
	private String mem_pwd; // 비밀번호
	private String mem_name; // 회원이름
	private String mem_zip; // 우편번호
	private String mem_zip2; // 우편번호
	private String mem_addr; // 주소
	private String mem_addr2; // 나머지 주소
	private String mem_phone01; // 첫번째 자리 폰번호
	private String mem_phone02; // 두번째 자리 폰번호
	private String mem_phone03; // 세번째 자리 폰번호
	private String mail_id; // 메일 아이디
	private String mail_domain; // 메일 도메인
	
	@CreationTimestamp // 하이버네이트의 기능으로 회원가입시점의 날짜값을 기록, mybatis로 실행할 때는 구동 안함.
	private Timestamp mem_date; // 가입날짜
	
	private int mem_state; // 가입회원이면 1, 탈퇴회원이면 2
	
	@Column(length=4000) // 테이블 컬럼 크기를 4000으로 설정
	private String mem_delcont; // 탈퇴사유
	
	private Timestamp mem_deldate; // 탈퇴날짜
	
}
