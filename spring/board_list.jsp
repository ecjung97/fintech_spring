<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스프링 게시판 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="5">게시판 목록</th>
		</tr>
		<tr>
			<td colspan="5" align="right">
				총 게시물 수 : <strong>${totalCount}</strong> 개
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>등록날짜</th>
		</tr>
		<c:if test="${!empty blist}">
			<c:forEach var="b" items="${blist}">
				<tr>
					<th>${b.bno}</th> <%-- ${b.bno}를 자바 코드로 표현하면 b.getBno()와 기능이 같다. --%>
					<td style="padding-left: 10px">
						<b>${b.title}</b>
					</td>
					<th>${b.writer}</th>
					<th>${b.viewcnt}</th>
					<th>${b.regdate}</th>
				</tr>
			</c:forEach>
		</c:if>
		
		<%-- 목록이 없는 경우 --%>
		<c:if test="${empty blist}">
			<tr>
				<th colspan="5">게시판 목록이 없습니다.</th>
			</tr>
		</c:if>
		<tr>
			<td colspan="5" align="right">
				<%-- 자바스크립트 location 객체에 의해서 이동하는 방식은 GET 방식이다. --%>
				<button onClick="location='/board/board_write'">글쓰기</button>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		// 자바스크립트에서 스프링 MVC 컨트롤러 클래스에서 저장된 키를 참조해서 EL로 가져올 수 있다.
		// msg는 표현언어인 EL로 MVC 컨트롤러로부터 전달받은 값을 의미
		let message = '${msg}';
		if(message === "SUCCESS") {
			alert("게시물 처리가 완료되었습니다!");
		}
	</script>
</body>
</html>