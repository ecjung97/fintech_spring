package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;

    @Override
    public void addReply(ReplyVO vo) {
        this.replyDAO.addReply(vo);
    }

    @Override
    public List<ReplyVO> listReply(int bno) {
        return this.replyDAO.listReply(bno);
    }

    @Override
    public void updateReply(ReplyVO vo) {
        this.replyDAO.updateReply(vo);
    }

    @Override
    public void removeReply(int rno) {
        this.replyDAO.removeReply(rno);
    }
}