package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository // @Repository 애노테이션을 추가해야 스프링에서 DAO로 인식한다.
public class BoardDAOImpl implements BoardDAO {
	// 자동 의존성 추가 (DI라고도 부름)
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 수행할 sqlSession

	// 게시판 저장
	@Override
	public void insertBoard(BoardVO b) {
		// insert() 메소드는 mybatis에서 레코드를 저장한다.
		// board_in은 board.xml에서 설정하는 유일한 insert 아이디명이다.
		this.sqlSession.insert("board_in", b);
	}

	// 총 게시물 수
	@Override
	public int getTotalCount() {
		// selectOne() 메소드는 mybatis에서 단 한 개의 레코드만 반환하는 메소드이다.
		// board_count 는 board.xml에서 selectOne 태그에 설정해야하는 ID명이다.
		return this.sqlSession.selectOne("board_count");
	}
	
	// 목록
	@Override
	public List<BoardVO> getBoardList() {
		// mybatis에서 selectList() 메소드는 하나 이상의 레코드를 검색해서 컬렉션 List로 반환
		// b_list는 board.xml에서 select 태그에 설정해야하는 ID명이다.
		return this.sqlSession.selectList("b_list");
	}
	
	
	
	
}
