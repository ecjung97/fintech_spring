<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<link rel="stylesheet" type="text/css" href="./css/member.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
 function login_check(){
    if($.trim($("#login_id").val())==""){
       alert("로그인 아이디를 입력하세요!");
       $("#login_id").val("").focus();
       return false;
    }
    if($.trim($("#login_pwd").val())==""){
       alert("로그인 비번을 입력하세요!");
       $("#login_pwd").val("").focus();
       return false;
    }
 }//로그인 인증 유효성 검증
 
 //비번찾기
 function pwd_find(){
    $url="pwd_find";//매핑주소
    window.open($url,"비번검색","width=400px,height=300px"+
          ",scrollbars=yes");
    //open(공지창경로,공지창이름,속성) 메서드로 폭이 400픽셀,
    //높이가 300픽셀,스크롤바가 생성되는 새로운 공지창을 만든다.
 }
</script>
</head>
<body>
   <c:if test="${empty id}">
      <%--로그인 전 화면 --%>
      <div id="Login_wrap">
         <h2 class="Login_title">로그인 폼</h2>
         <form method="post" action="member_Login_ok"
            onsubmit="return login_check();">
            <table id="Login_t">
               <tr>
                  <th>아이디</th>
                  <td><input name="login_id" id="login_id" size="14"
                     tabindex="1" />
                  <%-- tabindex="1"로 설정하면 탭키를 눌렀을
     때 첫번째로 포커스를 가진다. --%></td>
                  <th rowspan="2">
                     <%--rowspan="2" 2개행을 합침 --%> <button type="submit">로그인</button>
                  </th>
               </tr>
               <tr>
                  <th>비밀번호</th>
                  <td><input type="password" name="login_pwd" id="login_pwd"
                     size="14" tabindex="2" /></td>
               </tr>
            </table>
            <div id="Login_menu">
               <button type="button" onclick="pwd_find();">비번찾기</button> <button
                  type="button" onclick="location='member_join';">회원가입</button>
            </div>
         </form>
      </div>
   </c:if>
   <c:if test="${!empty id}">
      <%--로그인 이후 화면 --%>
      <div id="Index_wrap">
         <h2 class="Index_title">로그인 후 메인화면</h2>
         <form method="post" action="member_logout">
            <table id="Index_t">
               <tr>
                  <th><button type="button" onclick="location='member_edit';">정보수정</button> <button type="button"
                   onclick="location='member_del';">회원탈퇴</button> <button type="submit">로그아웃</button></th>
               </tr>
               <tr>
                  <th>${id}님로그인을 환영합니다.</th>
               </tr>
            </table>
         </form>
      </div>
   </c:if>
</body>
</html>




