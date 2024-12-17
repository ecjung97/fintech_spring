package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PointDAOImpl implements PointDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void updatePoint(String sender, int point) {
        Map<String, Object> hm = new HashMap<>(); // 키, 값 쌍으로 저장시키는 사전적인 collection 자료구조(증가/감소가 가능) (<> 뒷부분 Generic type은 생략 가능)
        hm.put("sender", sender); // 보낸사람
        hm.put("point", point);
        /* 2개 이상의 인자값을 해당 mapper tag인 point.xml로 전달할 때는 주로 Java의 collection 중의 하나인 Map을 사용한다. */

        this.sqlSession.update("pointUp", hm); // mybatis mapper tag에서 pointUp은 설정할 유일아이디명
    } // Message 하나 저장(전송)시 포인트 점수 10점 업데이트
}
