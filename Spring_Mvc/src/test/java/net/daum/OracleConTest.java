package net.daum;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class OracleConTest { // 오라클 연결 테스트 코드 클래스

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // oracle.jdbc는 패키지명, OracleDriver는 오라클 jdbc 드라이버 클래스명.
    // 이것 말고 oracle.jdbc.driver.OracleDriver를 사용해도 됨.
    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // oracle 접속주소. 1521은 Oracle 연결 port번호, xe는 전역 DB명
    private static final String USER = "fintech"; // Oracle 사용자명
    private static final String PW = "56789"; // 사용자 비번

    @Test
    private void testCon() throws Exception {
        Class.forName(DRIVER); // JDBC Driver class load

        try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
            /*  Java 7버전에서 AutoCloseable 인터페이스를 구현상속받은 자손은 try() 소괄호내에서 con을 생성하면
                finally문에서 명시적으로 close() 즉, 닫지 않아도 자동으로 닫힌다. */
            System.out.println(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}