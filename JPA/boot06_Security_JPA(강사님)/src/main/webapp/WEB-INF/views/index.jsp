<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp </title>
<link rel="stylesheet" type="text/css" href="./css/member.css" />
</head>
<body>
 <c:if test="${empty id}"> <%-- 로그인 전 --%>
   <div id="Index_wrap">
    <h1 class="title_size">Index Page for Everyone!</h1>
    <span class="login_a"><a href="/login">로그인</a></span>
   </div>
 </c:if>
 
 <c:if test="${!empty id}"> <%--로그인 이후 --%>
   <div id="Index_wrap">
    <h1 class="title_size">Index Page for Eventone!!</h1>
    <h2 class="Index_title">로그인 후 메인화면</h2>
    <table id="Index_t">
     <tr>
      <th><button type="button">정보수정</button>  <button type="button" onclick="location='logout';">로그아웃</button></th>
     </tr>
     
     <tr>
       <th class="idSize"> '${id}' 아이디 님으로 로그인을 한 "${name}"님 환영합니다.</th>
     </tr>
     
     <c:if test="${total_Auth == 'ROLE_BASICROLE_MANAGERROLE_ADMIN'}">
      <tr>
       <th>
       <a href="#">Admin 페이지</a>&nbsp;&nbsp;<a href="#">MANAGER 페이지</a>&nbsp;&nbsp;<a href="#">BASIC 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_BASICROLE_MANAGER'}">
      <tr>
       <th>
       <a href="#">MANAGER 페이지</a>&nbsp;&nbsp;<a href="#">BASIC 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_BASICROLE_nullROLE_ADMIN'}">
     <tr>
       <th>
       <a href="#">Admin 페이지</a>&nbsp;&nbsp;<a href="#">BASIC 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_nullROLE_MANAGERROLE_ADMIN'}">
       <tr>
       <th>
       <a href="#">Admin 페이지</a>&nbsp;&nbsp;<a href="#">MANAGER 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_nullROLE_nullROLE_ADMIN' or totalAuth =='ROLE_nullROLE_ADMINROLE_null'}">
        <tr>
       <th>
       <a href="#">Admin 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_nullROLE_MANAGER'}">
       <tr>
       <th>
        <a href="#">MABAGER 페이지</a>
       </th>
      </tr>
     </c:if>
     
     <c:if test="${total_Auth == 'ROLE_BASIC'}">
       <tr>
       <th>
       <a href="#">BASIC 페이지</a>
       </th>
      </tr>
     </c:if>
    </table>
   </div>
 </c:if>
</body>
</html>