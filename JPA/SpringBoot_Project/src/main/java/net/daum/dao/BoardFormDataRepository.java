package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BoardFormDataVO;

public interface BoardFormDataRepository extends JpaRepository<BoardFormDataVO, Integer> {
	// 리액트와 스프링연동 게시판에서 JPA실행 BoardFormDataRepository
	
	@Query(value="select react_no_seq.nextval from dual", nativeQuery = true)
	public int getNextSequenceValue(); // react_no_seq 시퀀스 다음 번호값
}
