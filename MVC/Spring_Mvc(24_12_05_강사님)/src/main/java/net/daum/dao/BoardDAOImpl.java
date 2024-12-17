package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository //@Repository 애노테이션을 추가해야 스프링에서 DAO로 인식한다.
public class BoardDAOImpl implements BoardDAO {

	@Autowired //자동 의존성 추가(DI)
	private SqlSession sqlSession; //mybatis 쿼리문 수행할 sqlSession

	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("board_in", b);
		//insert()메서드는 mybatis에서 레코드를 저장한다. board_in은 board.xml에서 설정하는 유일한 insert 아이디명이다.
	}//게시판 저장	

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("board_count");
		//mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, board_count는 board.xml에서 설정해야 하는 유일한 select 아이디명
	}//총레코드 개수

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.sqlSession.selectList("b_list",b);
		//mybatis에서 selectList()메서드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환,b_list는 board.xml에서 설정하는 유일한 select
		//아이디명
	}//게시판 목록

	@Override
	public void updateHit(int bno) {
		sqlSession.update("b_hit", bno);
		//mybatis에서 update()메서드는 레코드를 수정한다. b_hit는 board.xml에서 설정하는 유일 아이디명이다.
	}//조회수 증가

	@Override
	public BoardVO getBoardCont(int bno) {
		return this.sqlSession.selectOne("b_cont", bno);		
		//mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환하고, b_cont는 board.xml에서 설정하는 유일한 아이디명
	}//내용보기

	@Override
	public void updateBoard(BoardVO eb) {
		/* 문제)번호를 기준으로 글쓴이,글제목,글내용을 수정되게 만들어 보자. 유일 아이디명은 b_edit로 한다. 
		 */
		this.sqlSession.update("b_edit", eb);		
	}//게시물 수정

	@Override
	public void delBoard(int bno) {
		this.sqlSession.delete("b_del", bno);
		//mybatis 에서 delete() 메서드는 레코드를 삭제한다. b_del은 board.xml에서 설정할 유일 아이디명이다.
	}//게시물 삭제

	@Override
	public void updateReplyCnt(int bno, int count) {
		Map<String, Object> hm=new HashMap<>();
		
		hm.put("bno", bno);//board.xml에서 왼쪽의 bno키이름을 참조해서 게시판 번호를 가져옴.
		hm.put("count", count);
		
		this.sqlSession.update("replyCntUpdate", hm);//replyCntUpdate는 mybatis 매퍼태그에서 설정할 유일 아이디명.
	}//댓글이 추가되면 댓글수 1증가, 댓글이 삭제되면 댓글수 1감소
}







