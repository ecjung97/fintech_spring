<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
 function check(){
	 if($.trim($('#gname').val()) == ''){
		 alert('공지 작성자를 입력하세요!');
		 $('#gname').val('').focus();
		 return false;
	 }
	 if($.trim($('#gtitle').val()) == ''){
		 alert('공지 제목을 입력하세요!');
		 $('#gtitle').val('').focus();
		 return false;
	 }
	 if($.trim($('#gcont').val()) == ''){
		 alert('공지 내용을 입력하세요!');
		 $('#gcont').val('').focus();
		 return false;
	 }
 }
</script>
</head>
<body>
<h2>공지사항 수정폼</h2>
<form method="post" action="gongji7_Edit_ok" onsubmit="return check();">
<input type="hidden" name="gno" value="${gc.gno}" />
<input type="hidden" name="page" value="${page}" />

<label for="gname">공지 작성자 : </label>
<input name="gname" id="gname" size="14" value="${gc.gname}" /><br/><br/>

<label for="gtitle">공지 제목 : </label>
<input name="gtitle" id="gtitle" size="36" value="${gc.gtitle}" /><br/><br/>

<label for="gcont">공지 내용 : </label>
<textarea name="gcont" id="gcont" rows="10" cols="36">${gc.gcont}</textarea>
<hr/>

<button type="submit">수정</button> <%-- button태그에서 type속성을 생략하면 기본값은 submit이다.  --%>
<button type="reset" onclick="location='gongji7_cont?gno=${gc.gno}&page=${page}';">취소</button>
<button type="button" onclick="location='/gongji/gongji7_list?page=${page}';">목록</button>
</form>
</body>
</html>