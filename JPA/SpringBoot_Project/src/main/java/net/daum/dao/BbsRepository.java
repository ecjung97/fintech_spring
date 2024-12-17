package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BbsVO;

public interface BbsRepository extends JpaRepository<BbsVO, Integer> {
	// <엔티티빈 타입, @Id 기본키로 설정된 해당 속성 기본타입의 참조레퍼타입>
	
	@Query(value="select bbs_no_seq.nextval from dual", nativeQuery=true)
	public int getNextSequenceValue(); // bbs_no_seq 시퀀스 다음번호값 가져오기
	
	@Modifying // @Modifying을 사용하면 update, delete문 사용 가능하다. @Query만 사용하면 select문 쿼리문만 실행 가능하다.
	@Query("update BbsVO b set b.bbs_level=b.bbs_level+1 where b.bbs_ref=?1 and b.bbs_level > ?2")
	// JPQL(Java Presistence Query Language의 약어)문은 실제 테이블명 대신 엔티티빈 클래스명을 사용하고 실제 테이블 컬럼명 대신 엔티티빈 클래스의 속성명을 사용한다.
	// ?1은 첫번째인자값, ?2 두번째 인자값
	public void updateLevel(int ref, int level); // 답변레벨 증가
	
	@Modifying
	@Query("update BbsVO b set b.bbs_name=?1, bbs_title=?2, bbs_cont=?3, bbs_file=?4 where b.bbs_no=?5")
	public void updateBbs(String name, String title, String cont, String file, int bbs_no);
}
