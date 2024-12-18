package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardFormDataVO;

@Repository
public class ReactDAOImpl implements ReactDAO {

	@Autowired
	private BoardFormDataRepository boardFormDataRepo; // JPA를 실행할 자동의존성 주입
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문을 수행할 자동의존성 주입

	@Override
	public void reactInsert(BoardFormDataVO boardData) {
		System.out.println(" \n ==============> JPA로 react 게시판 저장~");
		int no = this.boardFormDataRepo.getNextSequenceValue(); // 다음 시퀀스 번호값을 가져온다.
		boardData.setNo(no); // 게시판 번호값 저장
		this.boardFormDataRepo.save(boardData); // JPA로 리액트 게시판 저장
	} // 리액트 게시판 저장
}
