package net.daum;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.daum.dao.BoardRepository;
import net.daum.vo.BoardVO;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testBoardInsert() {
		BoardVO b=new BoardVO();
		
		b.setWriter("심사임당"); // 글쓴이를 저장
		b.setTitle("게시판 글제목2 입니다."); // 글제목
		b.setContent("게시판 글내용2 입니다.");
		
//		this.boardRepo.save(b); // 게시판 저장
	} // testBoardInsert()

	@Test
	public void testBoardRead() {
//		Optional<BoardVO> b = this.boardRepo.findById(2); // 2번 레코드 검색
//		System.out.println(b);
	} // 읽기(조회)
	
	@Test
	public void testBoardUpdate() {
		/*Optional<BoardVO> eb = this.boardRepo.findById(2);
		
		eb.ifPresent(board -> {
			board.setWriter("수정 글쓴이");
			board.setTitle("수정 길제목");
			board.setContent("수정 글내용");
			
			System.out.println("\n 게시판 수정 ============================> \n");
			this.boardRepo.save(board);
		});*/
	} // 수정
	
	@Test
	public void testBoardDel() {
//		System.out.println("\n 엔티티빈 JPA 삭제 ========================> \n");
//		this.boardRepo.deleteById(2);
	}
	
	
	
	
	
	
	
}
