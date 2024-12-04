package net.daum.dao;

import net.daum.vo.MessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository Annotation을 설정해야 Spring에서 DAO로 인식한다.
public class MessageDAOImpl implements MessageDAO { // mybatis mapper tag인 message.xml로 연결된다.

    @Autowired
    private SqlSession sqlSession; // sqlSession은 mybatis에서 쿼리문 수행

    @Override
    public void insertMem(MessageVO vo) {
        this.sqlSession.insert("message_in", vo); // mybatis 에서 insert() method는 record를 저장한다. message_in은 mybatis mapper tag에서 설정할 유일 아이디명.
    } // tbl_message table에 message 추가
}
