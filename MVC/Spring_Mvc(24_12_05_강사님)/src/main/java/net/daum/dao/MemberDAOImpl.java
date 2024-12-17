package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository //@Repository 이 애노테이션을 설정함으로써 스프링에서 DAO로 인식한다.
public class MemberDAOImpl implements MemberDAO {

	@Autowired //자동 의존성 주입(DI)
	private SqlSession sqlSeesion; //sqlSession은 mybatis 쿼리문 수행

	@Override
	public void insertMember(MemberVO m) {
		this.sqlSeesion.insert("member_in", m);//member_in은 member.xml에서 설정한 유일 insert 아이디명이다.
		/* mybatis 쿼리문 실행 메서드 종류)
		 *  1. insert() : 레코드 저장
		 *  2. update() : 레코드 수정
		 *  3. delete() : 레코드 삭제
		 *  4. selectOne() : 단 한개의 레코드만 검색해서 반환
		 *  5. selectList() : 하나이상의 레코드를 검색해서 컬렉션 List로 반환 
		 */
	}//회원저장		
}
