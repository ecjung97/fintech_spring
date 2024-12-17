package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDAO;
import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired
	private BoardDAO boardDao;//boardDao 의존성 주입 추가는 고객의 추가요구 사항을 반영해서 replycnt컬럼에 댓글이 추가되면 1증가,
	//댓글이 삭제되면 1감소

	//스프링의 AOP를 통한 트랜잭션 적용
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);//댓글 추가 insert
		this.boardDao.updateReplyCnt(vo.getBno(), 1);//vo.getBno()로 게시판 번호를 구함. 댓글 수 1증가
	}//댓글 추가 insert+댓글 카운터 1증가 update

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
	}

	//스프링의 AOP를 통한 트랜잭션 적용=>데이터 일관성
	@Transactional //트랜잭션 적용
	@Override
	public void removeReply(int rno) {
		//댓글이 삭제되기 전에 먼저 게시판 번호부터 구해야 함.삭제된 후에는 게시판 번호를 못구함
		int bno = this.replyDao.getBno(rno);//댓글 번호를 기준으로 게시판 번호를 구함.
		this.replyDao.removeReply(rno);
		this.boardDao.updateReplyCnt(bno, -1);//댓글 삭제후 댓글 개수 1 감소
	}	
}










