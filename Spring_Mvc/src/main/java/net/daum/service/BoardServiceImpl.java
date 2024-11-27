package net.daum.service;

import net.daum.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service Annotation을 추가함으로써 Spring에서 Service로 인식한다.
public class BoardServiceImpl {

    @Autowired
    private BoardDAO boardDAO;


}
