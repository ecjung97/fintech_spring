package net.daum.dao;

import java.util.List;

import net.daum.vo.Gongji7VO;

public interface Gongji7DAO {

	void insert(Gongji7VO g);
	int getTotalCount();
	List<Gongji7VO> getGongjiList(Gongji7VO g);
	void updateHit(int gno);
	Gongji7VO getGongjiCont(int gno);
	void updateGongji(Gongji7VO eg);
	void delG(int gno);

}
