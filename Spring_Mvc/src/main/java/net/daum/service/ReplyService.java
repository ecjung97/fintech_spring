package net.daum.service;

import net.daum.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    void addReply(ReplyVO vo);
    List<ReplyVO> listReply(String bno);

    List<ReplyVO> listReply(int bno);
}
