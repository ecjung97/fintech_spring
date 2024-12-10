package net.daum.vo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude="member") // toString()메서드 호출 시 member 변수를 제외
@Entity
@SequenceGenerator( // 시퀀스 생성기
		name = "fno_seq_gename", // 시퀀스 제너레이터 이름
		sequenceName = "fno_seq", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 증가값 => 1씩 증가
		)
@Table(name="tbl_profile3") // tbl_profile3 이라는 테이블이 생성
@EqualsAndHashCode(of="fname")
public class Profile {

	@Id // 기본키
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
			generator="fno_seq_gename" // 시퀀스 생성기에서 설정한 시퀀스 제너레이터 이름
			)
	private int fno;
	
	private String fname;
	
	private boolean current2;
	
	@ManyToOne // 다대일 연관관계
	private MemberVO member; // 외래키로 설정
	
}
