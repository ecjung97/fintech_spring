package net.daum;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberDAOTest {

    @Autowired
    private MemberDAO memberDAO;

    @Test // JUit Test
    public void testInsertMember() throws Exception {
        MemberVO m = new MemberVO();

        m.setUserid("abc5678");
        m.setUserpw("77777");
        m.setUsername("홍길동");
        m.setEmail("hong@gmail.com");

        this.memberDAO.insertMember(m); // 회원저장
    }
}
