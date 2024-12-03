<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>스프링 MVC 게시판 글수정</title>
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <%-- jQuery CDN 라이브러리 --%>
  <script src="../js/b.js" type="text/javascript"></script>
</head>
<body>
<form method="post" onsubmit="return check();">
  <%-- action 매핑주소를 생략하면 이전 매핑주소인  board_edit가 action속성값이 된다.즉 이전 매핑주소가 action매핑주소가 된다.
  동일 매핑주소 구분은 메서드 방식으로 구분(get , post) 한다. --%>
  <input type="hidden" name="bno" value="${eb.bno}" />
    <input type="hidden" name="page" value="${page}" />
  <table border="1">
    <tr>
      <th colspan="2">스프링 MVC 게시판 수정폼</th>
    </tr>
    <tr>
      <th>글쓴이</th>
      <td><input name="writer" id="writer" size="14" placeholder="글쓴이 입력" value="${eb.writer}"/><br/>
        <span id="error_writer"></span>
      </td>
    </tr>
    <tr>
      <th>글제목</th>
      <td><input name="title" id="title" size="36" placeholder="글제목 입력" value="${eb.title}"/><br/>
        <span id="error_title"></span>
      </td>
    </tr>
    <tr>
      <th>글내용</th>
      <td>
        <textarea name="content" id="content" rows="10" cols="36" placeholder="글내용 입력">${eb.content}</textarea><br/>
        <span id="error_content"></span>
      </td>
    </tr>
    <tr>
      <th colspan="2">
        <input type="submit" value="글수정" />
        <input type="reset" value="취소" onclick="reSet();" />
        <button type="button" onclick="location='/board/board_list?page=${page}';">목록</button>
      </th>
    </tr>
  </table>
</form>x
</body>
</html>