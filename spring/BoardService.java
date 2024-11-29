package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {

	void insertBoard(BoardVO b);

	int getTotalCount();

	List<BoardVO> getBoardList();

}
