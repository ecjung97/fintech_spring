package net.daum.dao;

import net.daum.vo.ReplyVO;

import java.util.List;

public interface ReplyDAO {
    void addReply(ReplyVO vo);
    List<ReplyVO> listReply(int bno);
}
