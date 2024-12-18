package net.daum.dao;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.AdminVO;

@Repository // @Repository 애너테이션을 설정해야 스프링부트에서 DAO로 인식한다.
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private AdminRepository adminRepo; // JPA를 실행하기 위한 자동의존성 주입 (DI: Dependency Injection)
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 실행하기 위한 자동의존성 주입

	@Override
	public void insertAdmin(AdminVO ab) {
		this.sqlSession.insert("admin_in", ab); // mybatis에서 insert()메서드는 레코드를 저장한다. admin_in은 admin.xml매퍼태그에서
		// 설정하는 유일 아이디명이다.
		
		System.out.println(" \n ==============> JPA로 관리자 정보 저장");
		this.adminRepo.save(ab);
	} // 관리자 정보저장

	@Override
	public AdminVO adminLogin(String admin_id) {
		//return this.sqlSession.selectOne("admin_login", admin_id); // mybatis에서 selectOne()메서드는 단 한개의 레코드만 검색
		
		System.out.println(" \n ================> JPA로 관리자 로그인 인증");
		Optional<AdminVO> result = this.adminRepo.findById(admin_id);
		AdminVO admin;
		if (result.isPresent()) { // 관리자 아이디에 해당하는 관리자 정보가 있다면 참
			admin = result.get(); // AdminVO 엔티티 타입 객체를 구함
		}else { // 관리자 정보가 없다면
			admin = null;
		}
		return admin;
	} // 관리자 로그인 인증

}
