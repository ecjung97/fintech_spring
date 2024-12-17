package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gongji/*")
public class GongjiController {

    /* 문제) 1. 다음과 같은 테이블 정보를 가진 tbl_gongji 공지 사항 테이블을 생성한다. 파일명은 tbl_gongji.sql로 한다.
            컬럼명     자료형     크기      제약조건
            gno       number    38       primary key
            gname     varchar2  50       not null
            gtitle    varchar2  200      not null
            gcont     varchar2  4000     not null
            ghit      number    38       default 0
            gdate     date               default sysdate

            2. 1부터 시작하고, 1씩 증가하고, 임시메모리를 사용하지 않고, 최대 시퀀스 번호값 생성 시 다시 처음부터 반복하지 않는 gno_seq 시퀀스를 생성한다.

            3. 테이블 컬럼명과 일치하는 GongjiVO.java 데이터 저장빈 클래스를 생성한다. 람복 라이브러리 적용한다.

            4. mybatis-config.xml에서 GongjiVO빈클래스 객체 별칭이름 gongji를 등록한다. 그리고 sql문을 담는 매퍼태그를 가진 gongji.xml을 만든다.

            5. 모델 DAO인 GongjiDAO.java 인터페이스와 이를 구현한 GongjiDAOImpl.java를 만든다.

            6. 서비스에 해당하는 GongjiService.java 인터페이스와 이를 구현한 GongjiServiceImpl.java를 만든다.

            7. GongjiController.java에서 get으로 접근하는 매핑주소가 g_write로 해서 공지작성자, 공지제목, 공지내용 입력폼을 가진
               뷰페이지 gongji_write.jsp를 생성한다. 물론 사전에 해당 컨트롤러에서 이동하게 만들어줘야 한다. 해당 뷰페이지는
               자바스크립트와 jQuery 등을 사용한 유효성 검증도 해야 한다.
     */
}
