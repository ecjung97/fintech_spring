package net.daum.service;

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
}
