package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service //@Service 애노테이션을 추가함으로써 스프링에서 서비스로 인식한다.
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Override
	public void insertBoard(BoardVO b) {
	  this.boardDao.insertBoard(b);
	}

	@Override
	public int getTotalCount() {
		return boardDao.getTotalCount();//this. 이 생략됨.
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.boardDao.getBoardList(b);
	}

	// Spring의 AOP를 통한 트랜잭션 적용
	@Override
	public BoardVO getBoardCont(int bno) {
		this.boardDao.updateHit(bno); // 조회수 증가
		return boardDao.getBoardCont(bno); // 번호에 해당하는 레코드 가져오기 => 내용보기
	} // 조회수 증가 + 내용보기

	@Override
	public BoardVO getBoardCont2(int bno) {
		return this.boardDao.getBoardCont(bno);
	} // 내용보기

	@Override
	public void updateBoard(BoardVO eb) {
		this.boardDao.updateBoard(eb);
	}

	@Override
	public void delBoard(int bno) {
		this.boardDao.delBoard(bno);
	}
}
