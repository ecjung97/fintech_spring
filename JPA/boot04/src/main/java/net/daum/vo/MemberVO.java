package net.daum.vo;

import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.*;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

@Getter // getter()메서드 자동제공
@Setter // setter()메서드 자동제공
@ToString // toString()메서드 자동제공
@Entity // 엔티티빈 클래스
@Table(name="tbl_members6") // tbl_members6 테이블이 생성
@EqualsAndHashCode(of="uid2")
public class MemberVO {
	
	@Id // 각 엔티티빈을 식별할 수 있도록 해주는 식별키 => 기본키
	private String uid2; // 회원 아이디
	private String upw; // 비번
	private String uname; 
}
