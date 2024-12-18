package net.daum.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter // setter() 메서드 자동제공
@Getter // getter() 메서드 자동제공
@ToString // toString() 메서드 자동제공
@Entity // 엔티티빈
@Table(name="admin") // admin테이블 생성
@EqualsAndHashCode(of="admin_id")
/* equals(), hashCode(), canEqual()메서드 자동제공 */
public class AdminVO { // 관리자 admin테이블을 생성하기 위한 엔티티빈 클래스, 네임파라미터 이름과 테이블 컬럼명과 빈클래스 변수명은 되도록이면 같게 한다.

	private int admin_no;
	
	@Id // 구분키(식별키) 즉 유일키로 사용될 기본키 컬럼 즉 primary key
	private String admin_id; // 관리자 아이디
	private String admin_pwd; // 관리자 비번
	private String admin_name; // 관리자 이름
	
	@CreationTimestamp // @CreationTimestamp 는 하이버네이트의 특별한 기능으로 등록시점 날짜값을 기록
	private Timestamp admin_date; // 등록날짜
}
