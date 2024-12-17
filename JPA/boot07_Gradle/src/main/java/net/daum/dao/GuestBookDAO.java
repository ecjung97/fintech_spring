package net.daum.dao;

import java.util.List;

import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

public interface GuestBookDAO {

	void insertGuestBook(GuestBookVO g);
	long getTotalCount();
	List<GuestBookVO> getGuestBookList(PageVO p);
	void updateHit(int gno);
	GuestBookVO getGCont(int gno);

}
