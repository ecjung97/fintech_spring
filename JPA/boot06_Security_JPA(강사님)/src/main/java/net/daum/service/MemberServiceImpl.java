package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO idCheck(String id) {
		return this.memberDao.idCheck(id);
	}

	@Override
	public void insertMember(MemberVO m) {
		this.memberDao.insertMember(m);
	}

	@Override
	public MemberVO pwdMember(MemberVO m) {
		return this.memberDao.pwdMember(m);
	}

	@Override
	public void updatePwd(MemberVO m) {
		this.memberDao.updatePwd(m);
	}
	
}
