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

@Setter // setter()메서드 자동제공
@Getter // getter()메서드 자동제공
@ToString // toString()메서드 자동제공
@Entity // 엔티티빈
@Table(name="board_form_data") // board_form_data 테이블 생성
@EqualsAndHashCode(of="no")
/* equals(), hashCode(), canEqual() 메서드 자동제공 */
public class BoardFormDataVO { // 리액트와 스프링연동 게시판 엔티티빈 클래스 정의

	@Id // 구분키(식별키) 즉 유일키로 사용될 기본키 컬럼(primary key)
	private int no; // 번호
	private String name; // 글쓴이
	private String title; // 글제목
	private String pwd; // 비번
	
	@Column(length = 4000) // 컬럼 크기를 4000으로 설정
	private String content;
	
	@CreationTimestamp // @CreationTimestamp는 하이버네이트의 특별한 기능으로 등록시점의 날짜값을 기록, mybatis로 실행할 때는 구동안됨.
	private Timestamp regdate; // 등록날짜
}
