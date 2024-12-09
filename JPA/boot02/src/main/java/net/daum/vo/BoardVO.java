package net.daum.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter // getter()메서드 자동생성
@Setter // setter()메서드 자동생성
@ToString // toStirng()메서드 자동생성
@Entity // JPA를 다루는 엔티티빈 클래스
@SequenceGenerator( // 시퀀스 생성기를 설정하는 애노테이션
		name="bno_seq8_gename", // 시퀀스 제너레이터 이름
		sequenceName = "bno_seq8", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 증가값
		)
@Table(name="tbl_boards3") // tbl_boards3 테이블을 생성
public class BoardVO {
	
	@Id // 기본키 컬럼
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
			generator="bno_seq8_gename" // 시퀀스 생성기에 설정해 놓은 시퀀스 제너레이터 이름
			)
	private int bno; // 게시판 번호
	private String writer; // 글쓴이
	private String title; // 글제목

	@Column(length=4000) // 컬럼 크기를 4000으로 설정
	private String content; // 클내용

	@CreationTimestamp
	private Timestamp regdate; // 등록날짜

	@UpdateTimestamp // @CreatinoTimestamp, @UpdateTimestamp는 하이버네이트의 특별한 기능으로 엔티티빈 생성, 수정시점, 등록시점 날짜값을 기록한다.
	private Timestamp updatedate; // 수정날짜


}
