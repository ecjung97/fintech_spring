package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service // @Service 애노테이션을 추가함으로써 스프링에서 서비스로 인식한다.
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDao;

	// 게시판 저장
	@Override
	public void insertBoard(BoardVO b) {
		this.boardDao.insertBoard(b);
	}

	// 총 게시물 수
	@Override
	public int getTotalCount() {
		// this.이 생략 되어있다.
		return boardDao.getTotalCount();
	}

	// 목록
	@Override
	public List<BoardVO> getBoardList() {
		return this.boardDao.getBoardList();
	}
	
	
}
