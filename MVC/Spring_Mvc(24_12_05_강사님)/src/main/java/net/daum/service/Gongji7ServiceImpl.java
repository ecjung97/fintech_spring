package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.Gongji7DAO;
import net.daum.vo.Gongji7VO;

@Service
public class Gongji7ServiceImpl implements Gongji7Service {

	@Autowired
	private Gongji7DAO gongji7Dao;

	@Override
	public void insert(Gongji7VO g) {
		this.gongji7Dao.insert(g);
	}

	@Override
	public int getTotalCount() {
		return this.gongji7Dao.getTotalCount();
	}

	@Override
	public List<Gongji7VO> getGongjiList(Gongji7VO g) {
		return this.gongji7Dao.getGongjiList(g);
	}

	@Override
	public void updateHit(int gno) {
		this.gongji7Dao.updateHit(gno);
	}

	@Override
	public Gongji7VO getGongjiCont(int gno) {
		return this.gongji7Dao.getGongjiCont(gno);
	}

	@Override
	public void updateGongji(Gongji7VO eg) {
		this.gongji7Dao.updateGongji(eg);
	}

	@Override
	public void delGongji(int gno) {
		this.gongji7Dao.delG(gno);
	}	
}





