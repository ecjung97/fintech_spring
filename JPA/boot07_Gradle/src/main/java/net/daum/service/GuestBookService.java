package net.daum.service;

import java.util.List;

import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

public interface GuestBookService {

	void insertGuestBook(GuestBookVO g);
	long getTotalCount();
	List<GuestBookVO> getGuestBookList(PageVO p);
	GuestBookVO getGCont(int gno);

}
