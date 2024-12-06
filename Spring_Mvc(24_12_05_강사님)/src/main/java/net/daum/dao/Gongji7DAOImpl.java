package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.Gongji7VO;

@Repository
public class Gongji7DAOImpl implements Gongji7DAO{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(Gongji7VO g) {
		this.sqlSession.insert("gongji_save", g);
	}//공지 저장

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("gongji_count");
	}//총 레코드 개수

	@Override
	public List<Gongji7VO> getGongjiList(Gongji7VO g) {
		return this.sqlSession.selectList("gongji_li", g);
	}//공지 목록

	@Override
	public void updateHit(int gno) {
		this.sqlSession.update("gongji_hit", gno);
	}//조회수 증가

	@Override
	public Gongji7VO getGongjiCont(int gno) {
		return this.sqlSession.selectOne("gongji_cont", gno);
	}//내용보기	

	@Override
	public void updateGongji(Gongji7VO eg) {
		this.sqlSession.update("gongji_edit", eg);
	}//공지수정

	@Override
	public void delG(int gno) {
		this.sqlSession.delete("gongji_del", gno);
	}//공지 삭제
}
