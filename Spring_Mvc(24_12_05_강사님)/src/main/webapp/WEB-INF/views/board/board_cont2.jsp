<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 내용보기와 비동기식 아작스 댓글 </title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	 let formObj = $("form[role='form']");
	 
	 //수정폼으로 매핑이동
	 $('.btn-edit').on("click",function(){
		 formObj.attr("action","/board/board_edit");//액션 속성 지정
		 formObj.attr("method","get");//메서드 속성을 지정
		 formObj.submit();//히든 번호값 제출
	 });
	 
	 //삭제매핑으로 이동
	 $('.btn-del').on('click',function(){
		formObj.attr("action","/board/board_del");//액션 속성에 매핑주소 설정
		formObj.submit();//별도의 메서드 방식을 지정하지 않아서 폼태그에서 지정된 post방식으로 전송된다.	
	 });
  });
</script>
<style type="text/css">
 #modDiv{ /* 댓글 수정폼 */
   width:300px;
   height:100px;
   background-color:gray;
   position: absolute;
   top:50%;
   left:50%;
   margin-top:-50px;
   margin-left:-150px;
   padding:10px;
   z-index:1000; /* position이 absolute나 fixed로 설정된 곳에서 사용한다. 이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 값이 큰것이
   먼저 나온다. */
 }
 span.reply_color{
  color:red; background:gold; font-size:20px; border-radius:10px; padding:5px; box-shadow:5px 5px 5px gray;
  font-weight:bolder;
 }
</style>
</head>
<body>
<form method="post" role="form">
 <input type="hidden" name="bno" value="${bc.bno}" />
 <%-- hidden은 브라우저 상에서 만들어 지지 않는다. 하지만 bno 피라미터 이름에 value속성값인 게시판 번호를 담아서 post방식으로 전달한다.
 히든은 페이지 소스 보기에서는 보인다.그러므로 보안상 중요한 비번같은 데이터는 히든으로 보내면 안된다.  --%>
 <input type="hidden" name="page" value="${page}" /> <%--페이징에서 책갈피 기능 구현때문에 쪽번호 전달 --%>
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
  <button type="submit" class="btn-del">삭제</button>
  <button type="button" onclick="location='/board/board_list?page=${page}';">목록</button>
  </th>
 </tr>
</table>

<br/>
<hr/>
<br/>

<%-- 댓글 수정 화면 --%>
<div id="modDiv" style="display: none;"> <%-- display:none; css는 해당 요소 안보이게 한다. --%>
 <div class="modal-title"></div> <%--댓글 번호 --%>
 <div>
   <textarea rows="3" cols="30" id="replytext"></textarea>
 </div>
 <div>
 <button type="button" id="replyModBtn">댓글수정</button>
 <button type="button" id="replyDelBtn">댓글삭제</button>
 <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
 </div>
</div>

<c:if test="${bc.replycnt != 0}">
[댓글 개수 : ]<span class="reply_color">${bc.replycnt} 개</span>
</c:if>

<h2>아작스 댓글 연습페이지</h2>
<div>
 <div>
 댓글 작성자:<input type="text" name="replyer" id="newReplyWriter" />
 </div>
 <br/>
 <div>
 댓글 내용:<textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
 </div>
 <br/>
 <button type="button" id="replyAddBtn">댓글등록</button>
</div>

<br/>
<hr/>
<br/>

<%--댓글 목록이 출력되는 부분 --%>
<ul id="replies"></ul>

<script type="text/javascript">
 //let bno = 13;//게시판 번호
 let bno = ${bc.bno};//자바스크립트 코드 내에서 JSP문법인 EL(Expression Language:표현언어)을 적용
 
 getAllList();//댓글목록 함수 호출
 function getAllList(){
	 $.getJSON("/replies/all/"+bno, function(data){
		 let str="";
		 $(data).each(function(){//jQuery each()함수로 반복
			 str += "<li data-rno='"+this.rno+"' class='replyLi'>"
			 +this.rno+" : <span class='com' style='color:red;font-weight:bold;'>"+ this.replytext +"</span>"
			 +" <button>댓글수정</button></li><br/>";			 
		 });
		 $('#replies').html(str);//jQuery html()함수로 해당영역에 문자와 태그를 변경적용
	 });
 }//getAllList()
 
 //댓글 추가
 $('#replyAddBtn').on('click',function(){
	let replyer = $('#newReplyWriter').val();//댓글 작성자
	let replytext = $('#newReplyText').val();//댓글 내용
	
	$.ajax({//jQuery 비동기식 아작스 함수
		type:'post',
		url:'/replies/insertReply', //댓글저장 매핑주소
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"POST"
		},
		dataType:'text',
		data:JSON.stringify({
			bno:bno,
			replyer:replyer,
			replytext:replytext
		}),
		success:function(result){//받아오는 것이 성공시 호출되는 콜백함수
			if(result === 'SUCCESS'){
				alert('댓글이 등록되었습니다!');
				location.reload();//새로 고침.
				getAllList();//댓글목록 함수 호출
			}
		}
	});
 });
 
 //댓글수정 화면
 $('#replies').on('click','.replyLi button', function(){
	let reply = $(this).parent(); //parent() 부모요소를 선택 => li태그를 가리킴
	let rno = reply.attr('data-rno');//data-rno속성값을 가져옴.=>결국 댓글번호
	let replytext = reply.children('.com').text();//li태그 자식요소의 클래스 선택자 .com은 span태그를 가리킴. text()함수로 문자
	//댓글내용만 가져옴.
	
	$('.modal-title').html(rno);//modal-title 클래스선택자에 댓글번호를 표시
	$('#replytext').val(replytext);//replytext 아이디선택자 textarea입력박스에 기존댓글내용을 표시
	$('#modDiv').show('slow');//댓글수정화면 보이기
 });
 
 //댓글수정 화면 닫기
 function modDivClose(){
	 $('#modDiv').hide('slow');
 }
 
 //댓글 수정완료
 $('#replyModBtn').on('click', function(){
    let rno = $('.modal-title').html();//댓글번호
    let replytext = $('#replytext').val();//수정할 댓글내용
    
    $.ajax({ //jQuery 비동기식 아작스 함수
    	type:'put',//댓글내용 수정시 메서드방식은 put
    	url:'/replies/'+rno,//수정완료 매핑주소
    	headers:{
    		"Content-type":"application/json",
    		"X-HTTP-Method-Override":"PUT"
    	},
    	data:JSON.stringify({replytext:replytext}),//수정할 내용 JSON자료
    	dataType:'text',
    	success:function(result){
    		if(result === 'SUCCESS'){
    			alert('댓글이 수정되었습니다!');
    			$('#modDiv').hide('slow');//댓글수정 화면 닫기
    			getAllList();//댓글목록 함수호출
    		}
    	}
    });
 });
 
 //댓글 삭제
 $('#replyDelBtn').on('click', function(){
	let rno = $('.modal-title').html();//댓글 번호
	//alert(rno);
	
	$.ajax({
		type:'delete', //삭제 메서드 방식
		url: '/replies/' + rno, //삭제 매핑 주소
		headers : {
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"DELETE"
		},
		dataType:'text',
		success:function(result){
			if(result === 'SUCCESS'){
				alert('댓글이 삭제되었습니다!');
				$('#modDiv').hide('slow');
				location.reload();//새로 고침.
				getAllList();
			}
		}
	});
 });
</script>
</body>
</html>



