package net.daum.dao;

import java.util.List;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

public interface BbsDAO {

	void insertBbs(BbsVO bbs);
	int getRowCount(PageVO p);
	List<BbsVO> getBbsList(PageVO p);
	void updateHit(int bbs_no);
	BbsVO getBbs_cont(int bbs_no);
	void updateLevel(BbsVO rb);
	void replyBbs(BbsVO rb);
	void editBbs(BbsVO bbs);
	void delBbs(int bbs_no);

}
