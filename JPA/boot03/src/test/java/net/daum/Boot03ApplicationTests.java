package net.daum;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.daum.dao.BoardRepository;
import net.daum.vo.BoardVO;

@SpringBootTest
public class Boot03ApplicationTests {

	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testBoardInsert20() {
		
		for(int i=1;i<=20;i++) {
			BoardVO b = new BoardVO();
			
			b.setWriter("user0"+ (i%10));
			b.setTitle("게시판 제목...:"+ i);
			b.setContent("내용..."+ i);
			
//			this.boardRepo.save(b);
		}
	} // 샘플데이터 20개 저장

	// 쿼리메서드 중에서 제목으로 검색
	@Test
	public void testByTitle() {
		// 자바 8 이전 
		/*List<BoardVO> blist = this.boardRepo.findBoardVOByTitle("게시판 제목...:17");
		
		if (blist != null && blist.size() > 0) { // size()메서드는 컬렉션 원소개수를 반환
			for (int i=0;i<blist.size();i++) {
				System.out.println(blist.get(i)); // get() 메서드로 원소값을 가져온다.
			}
		}else {
			System.out.println("게시판 목록이 없습니다!");
		} // if else
		*/
		
		// 자바 8 이후
//		this.boardRepo.findBoardVOByTitle("게시판 제목...:17").forEach(board->System.out.println(board));
	} // testByTitle()
	
	// 글쓴이로 검색
	@Test
	public void testByWriterContaining() {
		/*Collection<BoardVO> blist = this.boardRepo.findByWriterContaining("05");
		blist.forEach(b->System.out.println(b));*/
	}
	
	// 제목 '2'가 포함되거나 내용에 '5'가 포함된 경우
	@Test
	public void testByTitleOrContentContaining() {
		
//		Collection<BoardVO> blist = this.boardRepo.findByTitleContainingOrContentContaining("2", "5");
//		blist.forEach(b -> System.out.println(b));
	}
	
	// 제목에 '5'가 포함되어 있고 게시물 번호가 5보다 큰 자료를 검색
	@Test
	public void testByTitleAndBno() {
		
//		Collection<BoardVO> blist = boardRepo.findByTitleContainingAndBnoGreaterThan("5", 5);
//		blist.forEach(b->System.out.println(b));
	}
	
	// bno가 10보다 큰 게시물 번호를 기준으로 내림차순 정렬
	@Test
	public void testBnoOrderBy() {
		
//		Collection<BoardVO> blist = this.boardRepo.findByBnoGreaterThanOrderByBnoDesc(10);
//		blist.forEach(b->System.out.println(b));
	}
	
	// 제목이 들어간 title검색
	@Test
	public void testByTitle2() {
		this.boardRepo.findByTitle("제목").forEach(b->System.out.println(b));
//		
	}
	
	// @Param 내용에 대한 검색 처리
	@Test
	public void testBYContent2() {
//		this.boardRepo.findByContent("내용").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle3() {
		this.boardRepo.findByTitle2("제목").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}














	
