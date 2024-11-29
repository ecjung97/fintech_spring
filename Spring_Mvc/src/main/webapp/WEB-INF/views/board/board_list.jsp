<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <td colspan="5" align="right">총 게시물 수 : <strong>${totalCount}</strong> 개</td>
 </tr>
 <tr>
  <th>번호</th> <th>제목</th> <th>글쓴이</th> <th>조회수</th> <th>등록날짜</th>
 </tr>
 <c:if test="${!empty blist}">
  <c:forEach var="b" items="${blist}">
  <tr>
   <th>${b.bno}</th> <%--${b.bno}를 자바코드로 표현하면 b.getBno()와 기능이 같다. --%>
   <td style="padding-left:10px;">
    <b><a href="/board/board_cont?bno=${b.bno}&page=${page}">${b.title}</a></b>
    <%-- board_cont?bno=번호&page=쪽번호 &기호로 구분해서 bno와 page 각 parameter 이름에 값을 담아서 get 방식으로 전달한다.
    page=쪽번호를 전달하는 이유는 페이징 목록에서 책갈피 기능 구현때문이다. --%>
   </td>
   <th>${b.writer}</th>
   <th>${b.viewcnt}</th>
   <th>${fn:substring(b.regdate,0,10)}</th>
   <%-- 0이상 10미만 사이의 년월일만 반환 --%>
  </tr>
  </c:forEach>
 </c:if>
 <c:if test="${empty blist}">
  <tr>
   <th colspan="5">게시판 목록이 없습니다.</th>
  </tr>
 </c:if>
 
 <%-- 페이징 쪽번호 출력부분 --%>
 <tr>
  <th colspan="5">
  <c:if test="${page <= 1}">
  [이전]&nbsp;
  </c:if>
  <c:if test="${page >1}">
   <a href="/board/board_list?page=${page-1}">[이전]</a>&nbsp;
  </c:if>
  
  <%--쪽번호 출력 --%>
  <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
   <c:if test="${a == page}"> <%-- 현재 쪽번호가 선택된 경우 --%>
    <${a}>
   </c:if>
   <c:if test="${a != page}"> <%--현재 쪽번호가 선택안된 경우 --%>
   <a href="/board/board_list?page=${a}">[${a}]</a>&nbsp;
   </c:if>
  </c:forEach>
  
  <c:if test="${page >= maxpage}">
  [다음]
  </c:if>
  <c:if test="${page < maxpage}">
  <a href="/board/board_list?page=${page+1}">[다음]</a>
  </c:if>
  </th>
 </tr>
 
 <tr>
  <td colspan="5" align="right">
  <button type="button" onClick="location='/board/board_write?page=${page}';">글쓰기</button>
  <%-- 자바스크립트 location객체에 의해서 이동하는 방식은 get 이다. ?page=${page}로 get방식으로 쪽번호를 전달하는 이유는 페이지(쪽나눈기)
  목록에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능을 구현하기 위해서 이다. --%>
  </td>
 </tr>
</table>
<script type="text/javascript">
  let message = '${msg}';//자바스크립트에서 스프링 MVC 컨트롤러 클래스에서 저장된 키이름을 참조해서 EL로 가져올 수 있다. 여기서는
  //표현언어인 el로 가져옴.
  if(message === 'SUCCESS'){
     alert('게시물 처리가 완료되었습니다!');
  }
 </script>
</body>
</html>