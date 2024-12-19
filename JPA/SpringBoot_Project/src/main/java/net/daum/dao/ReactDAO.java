package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardFormDataVO;

public interface ReactDAO {

	void reactInsert(BoardFormDataVO boardData);
	List<BoardFormDataVO> boardFormDataList();
	BoardFormDataVO getBoardDataCont(int no);
	void updateBoard(BoardFormDataVO vo);
	void delBoard(int no);

}
