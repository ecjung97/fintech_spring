package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.AdminDAO;
import net.daum.vo.AdminVO;

@Service // @Service를 처리해야 스프링에서 서비스로 인식한다.
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;

	@Override
	public void insertAdmin(AdminVO ab) {
		this.adminDao.insertAdmin(ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
		return this.adminDao.adminLogin(admin_id);
	}
}
