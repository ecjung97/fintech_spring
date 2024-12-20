package net.daum.dao;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyDAO {

	void addReply(ReplyVO vo);
	List<ReplyVO> listReply(int bno);
	void updateReply(ReplyVO vo);
	void removeReply(int rno);
	int getBno(int rno);

}
