package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.MessageDAO;
import net.daum.dao.PointDAO;
import net.daum.vo.MessageVO;

@Service //@Service 애노테이션을 설정함으로써 스프링에서 서비스로 인식된다.
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao; //messageDAO => message.xml => tbl_message테이블

	@Autowired
	private PointDAO pointDao; //pointDao => point.xml => tbl_user 테이블로 연결

	//스프링의 AOP를 통한 트랜잭션 적용.
	@Transactional //트랜잭션 적용
	@Override
	public void addMessage(MessageVO vo) {
		this.messageDao.insertMem(vo);//메시지 추가
		this.pointDao.updatePoint(vo.getSender(), 10);
	}//메시지 추가와 메시지 하나 저장(전송)시 포인트 점수 10점 업데이트	
}
