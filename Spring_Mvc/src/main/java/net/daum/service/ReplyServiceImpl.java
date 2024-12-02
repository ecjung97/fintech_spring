package net.daum.service;

import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;

    @Override
    public void addReply(ReplyVO vo) {
        this.replyDAO.addReply(vo);
    }
}
