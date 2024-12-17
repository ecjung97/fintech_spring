package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

	@Query("select m from MemberVO m where m.mem_id=?1 and m.mem_name= ?2")
	public MemberVO pwdFind(String id, String name);//아이디아 회원이름을 기준으로 오라클 DB로 부터 회원 정보 검색, ?1은 첫번째로
	//전달되어지는 인자값, ?2는 두번째로 전달되어지는 인자값, JPQL(JPA에서 사용하는 Query Language이다.)은 Java Persistence Query
	//Language의 약어이다. JPQL문은 실제 테이블명 대신 엔티티빈 클래스명을 사용하고 실제 컬럼명 대신 엔티티빈의 속성명을 사용한다.
	
	@Modifying //@Query 애노테이션은 select문만 가능하지만 @Modifying을 이용해서 DML조작어인  INSERT,udpate,delete문 작업처리가 가능하
	//다.
	@Query("update MemberVO m set m.mem_pwd=?1 where m.mem_id=?2")
	public void updatePwd(String pwd, String id);
}
