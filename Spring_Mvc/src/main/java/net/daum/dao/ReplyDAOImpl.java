package net.daum.dao;

import net.daum.vo.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl {

    @Autowired // 자동 의존성 주입 (DI)
    private SqlSession sqlSession; // mybatis 쿼리문 수행할 sqlSession

    @Override
    public void addReply(ReplyVO vo) {
        this.sqlSession.insert("reply_in", vo); // reply_in은 mybatis Mapper tag에서 설정할 유일아이디명
    }
}
