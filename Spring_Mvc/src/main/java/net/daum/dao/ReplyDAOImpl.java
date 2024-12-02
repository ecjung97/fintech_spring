package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Autowired // 자동 의존성 주입 (DI)
    private SqlSession sqlSession; // mybatis 쿼리문 수행할 sqlSession

    @Override
    public void addReply(ReplyVO vo) {
        this.sqlSession.insert("reply_in", vo); // reply_in은 mybatis Mapper tag에서 설정할 유일아이디명
    } // 댓글 추가

    @Override
    public List<ReplyVO> listReply(int bno) {
        return this.sqlSession.selectList("reply_list", bno); /* mybatis에서 selectList() method는 하나 이상의 record를 검색해서
        collection List로 반환한다. reply_list는 mybatis Mapper tag에서 설정할 유일 아이디명 */
    } // 게시판 번호에 해당하는 댓글목록
}
