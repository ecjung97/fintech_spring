<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 공지사항 내용보기 </title>
</head>
<body>
<table border="1">
 <tr>
  <th colspan="2">스프링 MVC 공지 내용</th>
 </tr>
 <tr>
  <th>제목</th>
  <td>${gc.gtitle}</td>
 </tr>
 <tr>
  <th>내용</th>
  <td>${gc.gcont}</td>
 </tr>
 <tr>
  <th>조회수</th>
  <td>${gc.ghit}</td>
 </tr>
 <tr>
  <th colspan="2">
  <button type="button" onclick="location='gongji7_Edit?gno=${gc.gno}&page=${page}';">수정</button>
  <button type="submit"
onclick="if(confirm('정말로 삭제할까요?') == true){location='gongji7_Del?gno=${gc.gno}&page=${page}';}else{return;}">삭제</button>
	  <%--자바스크립트에서 confirm() 내장함수는 확인/취소 버튼을 가지고 메시지를 담은 창을 만들어 준다. 확인을 클릭하면 true를 반환하고,
	  취소를 클릭하면 false를 반환한다.확인 클릭시 삭제되고, 취소 클릭시 현재창에 그대로 있게 된다. 다시 한번더 삭제 유무를 물어볼 때 많이 사용
	  한다. --%>	  
  <button type="button" onclick="location='/gongji/gongji7_list?page=${page}';">목록</button>
  </th>
 </tr>
</table>
</body>
</html>



