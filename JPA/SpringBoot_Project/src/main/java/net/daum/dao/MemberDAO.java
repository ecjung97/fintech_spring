package net.daum.dao;

import java.util.List;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipCodeVO;

public interface MemberDAO {

	MemberVO idCheck(String id);
	List<ZipCodeVO> zipFind(String dong);

}
