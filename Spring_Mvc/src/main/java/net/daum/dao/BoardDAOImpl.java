package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository Annotation을 추가해야 Spring에서 DAO 인식한다.
public class BoardDAOImpl implements BoardDAO {

    @Autowired // 자동 의존성 추가(DI)
    private SqlSession sqlSession; // mybatis 쿼리문 수행할 sqlSession
}
