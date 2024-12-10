package net.daum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import lombok.extern.java.Log;
import net.daum.dao.MemberRepository;
import net.daum.dao.ProfileRepository;
import net.daum.vo.MemberVO;
import net.daum.vo.Profile;

@SpringBootTest
@Log // Lombok로그를 이용할 때 사용하는 애너테이션
@Commit // 테스트 결과를 데이터베이스에 commit하는 용도로 사용
public class ProlifeTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	// 20명 회원 데이터 추가
	@Test
	public void testInsertMembers() {
		
		IntStream.range(1, 21).forEach(i->{ // 1부터 20까지의 숫자를 생성
			
			MemberVO m = new MemberVO();
			
			m.setUid2("user"+i);
			m.setUpw("pw"+i);
			m.setUname("사용자"+i);
			
//			this.memberRepo.save(m);
		});
	} // testInsertMembers()
	
	// 특정회원의 프로필 사진을 추가
	@Test
	public void testInsertProfile() {
		
		MemberVO member = new MemberVO();
		member.setUid2("user1");
		
		for (int i=1;i<5;i++) {
			Profile profile01 = new Profile();
			profile01.setFname("face"+i+".jpg");
			
			if (i==1) {
				profile01.setCurrent2(true); // face1.jpg가 현재 사용중인 프로필 사진
			}
			
			profile01.setMember(member);
			
//			this.profileRepo.save(profile01);
		} // for
	} // testInsertProfile()
	
	// user1 아이디 정보와 프로필 사진 개수 => Fetch Join
	@Test
	public void testFetchJoin01() {
//		List<Object[]> result = this.memberRepo.getMemberVOWithProfileCount("user1");
		
//		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	} // testFetchJoin()
	
	// 단방향 Fetch Join2 => user1 회원아이디 정보와 현재 사용중인 프로필 사진 정보
	@Test
	public void testFetchJoin02() {
		List<Object[]> result = this.memberRepo.getMemberVOWithProfile("user1");
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}

















