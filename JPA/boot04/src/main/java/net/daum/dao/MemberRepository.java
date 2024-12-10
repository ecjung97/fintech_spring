package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

	@Query(value="select m.uid2, count(fname) from tbl_members6 m left outer join tbl_profile3 p"
			+ " on m.uid2 = p.member_uid2 where m.uid2 = ?1 group by m.uid2", nativeQuery = true)
	/* nativeQuery문은 데이터베이스에 종속적인 sql문을 그대로 사용하겠다는 의미. 복잡한 쿼리문 작성에 유리. 다만, 이 경우는 데이터베이스에 독립적이라는 장점은 어느 정도 포기해야 함.
	 * @Query에 nativeQuery속성값을 true로 주면 메서드 실행 시 value값을 실행한다. tbl_members6 테이블에는 레코드가 있으나 tbl_profile3 테이블에는 레코드가 없는 경우 
	 * left-outer-join을 한다. => 단뱡향 Fetch Join
	 */
	public List<Object[]> getMemberVOWithProfileCount(String id); // 실제 회원아이디와 프로필 사진 개수
	
	
	@Query(value="select m.uid2, m.upw, m.uname, p.current2, p.fname from tbl_members6 m left outer join tbl_profile3 p" 
	+" on m.uid2 = p.member_uid2 where m.uid2 = ?1 and p.current2 = 1",nativeQuery = true)	
	public List<Object[]> getMemberVOWithProfile(String uid); // 회원 user1 아이디의 회원정보와 현재 사용중인 프로필 사진 정보
}
