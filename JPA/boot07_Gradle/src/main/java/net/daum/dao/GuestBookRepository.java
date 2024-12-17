package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.GuestBookVO;

public interface GuestBookRepository extends JpaRepository<GuestBookVO, Integer> {
	/* <>제네닉 타입 첫번째 인자값을 GuestBookVO 엔티티 타입이고, 두번째 Integer 타입은 엔티티 빈 클래스 속성중에 @Id로 설정해 놓은 유일키
	 * 속성의 타입이다. 해당 타입은 기본타입으로는 제네릭 타입 설정을 못하기 때문에 참조 레퍼타입으로 설정함.
	 */

}
