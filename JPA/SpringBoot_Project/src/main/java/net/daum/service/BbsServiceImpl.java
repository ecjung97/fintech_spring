package net.daum.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BbsDAO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

@Service // @Service 애노테이션을 설정해야 스프링에서 서비스로 인식한다.
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsDAO bbsDao;

	@Override
	public void insertBbs(BbsVO bbs) {
		this.bbsDao.insertBbs(bbs);
	}

	@Override
	public int getRowCount(PageVO p) {
		return this.bbsDao.getRowCount(p);
	}

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.bbsDao.getBbsList(p);
	}

	// 조회수 증가 + 내용보기 => 스프링의 AOP를 통한 트랜잭션 적용대상
	@Transactional(isolation = Isolation.READ_COMMITTED) // 트랜잭션 격리(트랜잭션이 적용되는 중간에 외부 간섭을 배제)
	@Override
	public BbsVO getBbsCont(int bbs_no) {
		this.bbsDao.updateHit(bbs_no); // 조회수 증가
		BbsVO bc = this.bbsDao.getBbs_cont(bbs_no);
		return bc;
	}

	@Override
	public BbsVO getBbsCont2(int bbs_no) {
		return this.bbsDao.getBbs_cont(bbs_no);
	}

	// 답변 레벨증가+답변 저장 => 스프링의 AOP를 통한 트랜잭션을 적용
	@Transactional
	@Override
	public void replyBbs(BbsVO rb) {
		this.bbsDao.updateLevel(rb); // 답변 레벨 증가
		this.bbsDao.replyBbs(rb); // 답변 저장
		
	}

	@Override
	public void editBbs(BbsVO bbs) {
		this.bbsDao.editBbs(bbs);
	}

	@Override
	public void delBbs(int bbs_no) {
		this.bbsDao.delBbs(bbs_no);
	}
}
