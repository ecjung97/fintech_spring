<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
</head>
<body>
<div id="aMain_wrap">
	<%-- 관리자 메인 상단 --%>
	<div id="aMain_header">
		<%-- 관리자 로고 --%>
		<div id="aMain_logo">
			<a href="admin_main"><img src="./images/admin/admin_logo.png" /></a>
		</div>
		
		<%-- 관리자 상단메뉴 --%>
		<div id="aMain_menu">
			<ul>
				<li><a href="admin_gongji_list">공지사항</a></li>
				<li><a href="admin_board_list">게시판</a></li>
				<li><a href="admin_bbs_list">자료실</a></li>
				<li><a href="admin_member_list">회원관리</a></li>
			</ul>
		</div>
		
		<%-- 관리자 메인상단 우측메뉴 --%>
		<div id="aMain_right">
			<form method="post" action="admin_logout">
				<h3 class="aRight_title">
					${admin_name}님 로그인을 환영합니다! <button type="submit">로그아웃</button>
				</h3>
			</form>
		</div>
	</div>
	
	<div class="clear"></div>
	
	<%-- 관리자 메인 본문 --%>
	<div id="aMain_cont">
		<h2 class="aMainCont_title">관리자 메인 화면입니다.</h2>
	</div>
	
	<div class="clear"></div>
	
	<%-- 관리자 메인 하단 --%>
	<div id="aMain_footer">
		<h2 class="aMainFooter_title">서울시 종로구 돈화문로 26 단성사 빌딩 404호. TEL)02-9999-9999</h2>
	</div>
</div>

</body>
</html>