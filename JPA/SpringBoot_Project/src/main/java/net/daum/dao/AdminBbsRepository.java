package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BbsVO;

public interface AdminBbsRepository extends JpaRepository<BbsVO, Integer> {

	@Query(value="select bbs_no_seq.nextval from dual", nativeQuery=true)
	public int getNextSequenceValue(); // bbs_no_seq 시퀀스 다음번호값 가져오기
	
	@Modifying // @Query 애노테이션은 select문만 가능하지만 @Modifying을 이용해서 DML(insert, update, delete)문 작업 처리가 가능하다.
	@Query("update BbsVO b set b.bbs_name=?1, bbs_title=?2, b.bbs_cont=?3, b.bbs_file=?4 where b.bbs_no=?5")
	public void adminEditBbs(String name, String title, String cont, String file, int no);
	/* ?1은 첫번째로 전달되어진 인자값, JPQL문은 실제 테이블명 대신 엔티티빈 클래스를 사용하고, 실제 컬럼명 대신 엔티티빈의 속성을 사용한다. */
}
