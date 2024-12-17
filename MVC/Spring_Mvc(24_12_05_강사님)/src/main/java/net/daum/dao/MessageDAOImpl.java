package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MessageVO;

@Repository //@Repository 애너테이션을 설정해야 스프링에서 DAO 로 인식한다.
public class MessageDAOImpl implements MessageDAO {//mybatis 매퍼태그인 message.xml로 연결된다.

	@Autowired
	private SqlSession sqlSession; //sqlSession은 mybatis 에서 쿼리문 수행

	@Override
	public void insertMem(MessageVO vo) {
		this.sqlSession.insert("message_in", vo);//mybatis 에서 insert() 메서드는 레코드를 저장한다. message_in은 mybatis매퍼
		//태그에서 설정할 유일 아이디명.
	}//tbl_message테이블에 메시지 추가	
}
