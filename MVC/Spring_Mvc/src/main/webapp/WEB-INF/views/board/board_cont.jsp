<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스프링 MVC 게시판 내용보기</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            let formObj = $("form[role='form']");

            // 수정폼으로 매핑이동
            $('.btn-edit').on("click",function(){
                formObj.attr("action","/board/board_edit"); // 액션 속성 지정
                formObj.attr("method","get"); // 메서드 속성을 지정
                formObj.submit(); // hidden 번호값 제출
            });

            // 삭제매핑으로 이동
            $('.btn-del').on('click', function() {
                formObj.attr("action", "/board/board_del"); // 액션 속성에 매핑주소 설정
                formObj.submit(); // 별도의 메서드 방식을 지정하지 않아서 폼태그에서 지정된 post방식으로 전송된다.
            });
        });
    </script>
</head>
<body>
<form method="post" role="form">
    <input type="hidden" name="bno" value="${bc.bno}" />
    <%-- hidden은 브라우저 상에서 만들어 지지 않는다. 하지만 bno parameter 이름에 value 속성값인 게시판 번호를 담아서 post방식으로 전달한다.
    hidden은 페이지 소스 보기에서는 보인다. 그러므로 보안상 중요한 비번같은 데이터는 히든으로 보내면 안된다. --%>
    <input type="hidden" name="page" value="${page}" /> <%-- 페이징에서 책갈피 기능 구현 때문에 쪽번호 전달 --%>
</form>
<table border="1">
    <tr>
        <th colspan="2">스프링 MVC 게시판 내용</th>
    </tr>
    <tr>
        <th>제목</th>
        <td>${bc.title}</td>
    </tr>
    <tr>
        <th>내용</th>
        <td>${bcont}</td>
    </tr>
    <tr>
        <th>조회수</th>
        <td>${bc.viewcnt}</td>
    </tr>
    <tr>
        <th colspan="2">
            <button type="submit" class="btn-edit">수정</button>
            <button type="reset" class="btn-del">삭제</button>
            <button type="button" onclick="location='/board_list?page=${page}';">목록</button>
        </th>
    </tr>
</table>
</body>
</html>