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

    @Override
    public void updateReply(ReplyVO vo) {
        this.sqlSession.update("reply_edit", vo); // reply_edit는 mybatis mapper tag에서 설정하는 유일 아이디명.
    } // 댓글 수정

    @Override
    public void removeReply(int rno) {
        this.sqlSession.delete("reply_del", rno); // mybatis 에서 delete() method는 record를 삭제한다.
        // reply_del은 mybatis mapper tag에서 설정하는 유일 아이디명.
    }

    @Override
    public int getBno(int rno) {
        return this.sqlSession.selectOne("reply_bno", rno); // mybatis에서 selectOne() method는 단 한개의 레코드만 반환
    } // 댓글번호에 해당하는 게시판 번호 구하기
}
