package net.daum.service;

import net.daum.dao.MessageDAO;
import net.daum.dao.PointDAO;
import net.daum.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // @Service Annotation을 설정함으로써 Spring에서 Service로 인식된다.
public class MeessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO; // messageDAO => message.xml => tbl_message table

    @Autowired
    private PointDAO pointDAO; // pointDAO => point.xml => tbl_user table로 연결

    // Spring의 AOP를 통한 Transaction 적용.
    @Transactional // 트랜잭션 적용.
    @Override
    public void addMessage(MessageVO vo) {
        this.messageDAO.insertMem(vo); // message 추가
        this.pointDAO.updatePoint(vo.getSender(), 10);
    } // message 추가와 message 하나 저장(전송) 시 point 점수 10점 업데이트
}
