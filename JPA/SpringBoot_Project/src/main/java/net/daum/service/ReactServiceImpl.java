package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReactDAO;
import net.daum.vo.BoardFormDataVO;

@Service
public class ReactServiceImpl implements ReactService {

	@Autowired
	private ReactDAO reactDao;

	@Override
	public void reactInsert(BoardFormDataVO boardData) {
		this.reactDao.reactInsert(boardData);
	}

	@Override
	public List<BoardFormDataVO> boardFormDataList() {
		return this.reactDao.boardFormDataList();
	}

	@Override
	public BoardFormDataVO getBoardDataCont(int no) {
		return this.reactDao.getBoardDataCont(no);
	}

	@Override
	public void updateBoard(BoardFormDataVO vo) {
		this.reactDao.updateBoard(vo);
	}

	@Override
	public void delBoard(int no) {
		this.reactDao.delBoard(no);
	}
}
