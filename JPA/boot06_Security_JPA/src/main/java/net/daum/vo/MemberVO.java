package net.daum.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter //setter() 메서드 자동제공
@Getter //getter() 메서드 자동제공
@ToString //toString()메서드 자동제공
@Entity //엔티티빈 
@Table(name="tbl_members7") //tbl_members7 테이블 생성
@EqualsAndHashCode(of="mem_id")
/* equals(), hashCode, canEqual()메서드 자동제공 
 */
public class MemberVO { //회원 관리 엔티티빈 클래스

	@Id //유일키로 사용될 기본키 컬럼 => primary key
	private String mem_id;//회원 아이디
	
	private String mem_pwd;//비번
	private String mem_name;//회원이름
	
	@CreationTimestamp //하이버 네이트의 특별한 기능으로 등록시점 날짜/시간값을 기록
	private Timestamp mem_date;//가입날짜
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	/* 일대다 연관관계, cascade = CascadeType.ALL은 jpa에서 영속성 전이중에서 모든 변경에 대한 전이로서 모든 엔티티빈 상태 변화에 대해서 같이
	 * 처리하는 옵션, fetch=FetchType.EAGER는 tbl_members7과 tbl_member_roles7 두 테이블을 조회해야 하기 때문에 트랜잭션을 처리해 주거
	 * 나, 즉시 로딩을 이용해서 조인하는 방법으로 처리해야 한다. 권한 정보는 회원정보와 마찬가지로 필요한 경우가 많기 때문에 fetch 모드를 즉시 로딩
	 * 으로 설정한다.
 	 */
	@JoinColumn(name ="member") //이미 존재하는 tbl_member_roles7 테이블에 member 컬럼을 추가, foreign key(외래키) 추가 설정
	private List<MemberRole> roles;
}
