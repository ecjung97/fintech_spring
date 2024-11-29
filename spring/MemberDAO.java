package net.daum.dao;

import net.daum.vo.MemberVO;

public interface MemberDAO {
	// 회원 저장
	void insertMember(MemberVO m);
}
