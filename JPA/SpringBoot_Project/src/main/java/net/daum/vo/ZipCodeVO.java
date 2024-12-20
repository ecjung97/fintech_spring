package net.daum.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@SequenceGenerator(// 시퀀스 생성기
			name = "zip_no_seq_gename", // 시퀀스 제너레이터 이름
			sequenceName = "zip_no_seq", // 시퀀스 이름
			initialValue = 1, // 시작값
			allocationSize = 1 // 증가값
		)
@Table(name="zipcode") // zipcode 테이블
@EqualsAndHashCode(of="no")
public class ZipCodeVO { // 우편번호, 주소를 저장할 엔티티빈 클래스

	@Id // 구분키(식별키) 즉 유일키로 사용될 기본키 컬럼(private key)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
				generator = "zip_no_seq_gename" // 시퀀스 생성기에서 설정한 시퀀스 제너레이터 이름
			)
	private int no;
	
	private String zipcode; // 우편번호
	private String sido; // 시도
	private String gugun; // 구군
	private String dong; // 도로명 또는 지번(읍면동리)
	private String bunji; // 번지

}
