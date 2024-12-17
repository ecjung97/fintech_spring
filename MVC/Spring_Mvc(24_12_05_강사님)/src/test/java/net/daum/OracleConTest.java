package net.daum;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class OracleConTest {//오라클 연결 테스트 코드 클래스
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지명, OracleDriver는 오라클 jdbc
	//드라이버 클래스명이다. 이것 말고 oracle.jdbc.driver.OracleDriver를 사용해도 된다.
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속주소이다. 1521은 오라클 연결 포트번호
	//xe는 전역 데이터베이스명
	private static final String USER = "fintech";//오라클 사용자명
	private static final String PW = "56789";//사용자 비번
	
	@Test
	public void testCon() throws Exception{
		Class.forName(DRIVER);//jdbc 드라이버 클래스 로드
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			/* 자바 7버전에서 AutoCloseable 인터페이스를 구현상속받은 자손은 try() 소괄호내에서 con을 생성하면 finally문에서 명시적으로
			 * close()즉 닫지 않아도 자동으로 닫힌다.
			 */
			System.out.println("DB연결 주소:"+ con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
