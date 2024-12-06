package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gongji/*")
public class GongjiController {
    
	/* 문제) 1. 다음과 같은 테이블 정보를 가진 tbl_gongji 공지 사항 테이블을 생성한다. 파일명은 tbl_gongji.sql로 한다.
	 *    컬럼명    자료형    크기    제약조건
	 *    gno     number   38     primary key
	 *    gname   varchar2 50     not null
	 *    gtitle  varchar2 200    not null
	 *    gcont   varchar2 4000   not null
	 *    ghit    number   38     default 0
	 *    gdate   date            default sysdate
	 *    
	 *    2. 1부터 시작하고,1씩 증가하고,임시메모리를 사용하지 않고, 최대 시퀀스 번호값 생성시 다시 처음부터 반복하지 않는 gno_seq 시퀀스를 생성
	 *    한다.
	 *    
	 *    3. 테이블 컬럼명과 일치하는 GongjiVO.java 데이터 저장빈 클래스를 생성한다. 람복 라이브러리 적용한다.
	 *    4.mybatis-config.xml에서 GongjiVO빈클래스 객체 별칭이름 gongji를 등록한다.그리고 sql문을 담는 매퍼태그를 가진 gongji.xml
	 *    을 만든다.
	 *    5.모델 DAO인 GongjiDAO.java 인터페이스와 이를 구현한 GongjiDAOImpl.java를 만든다.
	 *    
	 *    6.서비스에 해당하는 GongjiService.java인터페이스와 이를 구현한 GongjiServiceImpl.java를 만든다.
	 *    
	 *    7.GongjiController.java에서 get으로 접근하는 매핑주소가 g_write로 해서 공지작성자,공지제목,공지내용입력폼을 가진  
	 *    뷰페이지 gongji_write.jsp를 생성한다.물론 사전에 해당컨트롤러에서 이동하게 만들어줘야 한다. 해당 뷰페이지는 자바스크립트와 jQuery등을
	 *    사용한 유효성 검증도 해야 한다. 뷰페이지 경로는 /WEB-INF/views/gongji로 한다.    
	 */
	
	/* 문제2) 
	 *   1. this.gongji7Service.insert(g); 공지 저장메서드로 공지사항을 저장되게 만든다. gongji7.xml에서 설정할 유일 아이디명은 gongji
	 *   _save로 한다. 저장메서드는 다음과 같이 정의한다. public String gongji_insert(Gongji7VO g){}로 정의해서 한다.
	 *   
	 *   2.public void gongji_list(Gongji7VO g,HttpServletRequest request){} 공지 리스트 메서드를 작성해 보자. 
	 *   공지 목록 뷰페이지 파일명은 gongji7_list.jsp로 한다. 페이징이 되게 만든다. gongji7.xml에서 설정할 유일 아이디명은 
	 *   gongji_li로 한다.
	 * 
	 */
	
	/* 문제3)
	 *    1. Gongji7Controller.java에서 this.gongji7Service.updateHit(gno);메서드로 조회수 증가되게 하고,
	 *    Gongji7VO gc = this.gongji7Service.getGongjiCont(gno);메서드로 번호에 해당하는 공지내용을 오라클로 부터 가져와서
	 *    ModelAndView 리턴 타입 객체 cm에 각 저장해서 해당 뷰페이지 파일명  gongji7_cont.jsp에서 제목,내용,조회수만 나오게 만들어 보자.
	 *    위의 두개의 메서드를 포함한 사용자 정의메서드는 다음과 같다.
	 *    public ModelAndView getGongjiView(적절한 전달인자를 정의한다.){}
	 *    
	 *    gongji7.xml에서 설정할 유일 아이디명은 각각 gongji_hit와 gongji_cont가 된다.    
	 */
	
	/* 문제4)
	 *  1.매핑주소가 gongji7_Edit인 public String gongji_Edit(적절한 매개변수를 정의한다.){} 메서드를 Gongji7Controller에 작성을
	 *  하고 공지 수정폼 뷰페이지 파일명을 gongji7_edit.jsp로 해서 보이게 한다. 그런다음 수정완료 매핑주소는 gongji7_Edit_ok로 하고,
	 * Gongji7Controller에 public ModelAndView gongji_Edit_ok(적절한 전달인자 정의한다.){
	 *      코드 생략..
	 *      this.gongji7Service.updateGongji(eg); 번호를 기준으로 공지작성자,공지제목,공지내용만 수정되게 하는 메서드를 작성한다.
	 * }
	 * 
	 * 2. 수정완료 gongji7.xml에 설정할 유일 아이디명은 gongji_edit로 한다.
	 */
	
	/* 문제5)
	 *  1. 매핑주소가 gongji7_Del인 public ModelAndView gong_Del(적절한 전달인자 정의){} 메서드를 작성한다.
	 *  번호를 기준으로 레코드를 학제한다. this.gongji7Service.delGongji(gno);
	 *  삭제이후 공지목록으로 이동할 때 책갈피 기능이 되게 만든다.
	 *  삭제완료후 gongji7.xml에 설정할 유일 아이디명은 gongji_del로 한다.
	 */
}















