package net.daum.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipCodeVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 수행
	
	@Autowired
	private MemberRepository memberRepo; // 회원관리 JPA
	
	@Autowired
	private ZipCodeRepository zipcodeRepo; // 우편주소 검색 JPA

	@Override
	public MemberVO idCheck(String id) {
		//return this.sqlSession.selectOne("m_idcheck", id); // mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, m_idcheck는
		// mapper xml에서 설정할 유일아이디명
		
		System.out.println(" \n ============================> JPA로 아이디 중복 검색");
		Optional<MemberVO> result = this.memberRepo.findById(id);
		MemberVO member;
		if (result.isPresent()) { // 아이디에 해당하는 회원정보가 있다면 참
			member = result.get(); // MemberVO 앤티티 객체를 구함
		}else { // 회원정보가 없는 경우
			member = null;
		}
		return member;
	} // idCheck() => 아이디 중복검색

	@Override
	public List<ZipCodeVO> zipFind(String dong) {
		//return this.sqlSession.selectList("m_zlist", dong);
		
		System.out.println(" \n =========================> JPA로 우편주소 검색");
		List<ZipCodeVO> zipList = this.zipcodeRepo.findByDong(dong);
		return zipList;
	} // 우편번호 검색
}
