<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajax 댓글 연습</title>
    <style type="text/css">
        #modDiv{ /* 댓글 수정폼 */
            width: 300px;
            height: 100px;
            background-color: gray;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -50px;
            margin-left: -150px;
            padding: 10px;
            z-index: 1000; /* position이 absolute나 fixed로 설정된 곳에서 사용한다. 이 속성은 요소가 곂쳐지는 순서를 제어할 수 있다 .
                              값이 큰것이 먼저 나온다. */
        }
    </style>
</head>
<body>
<%-- 댓글 수정 화면 --%>
<div id="modDiv" style="display: none;"> <%-- display: none; css는 해당 요소 안보이게 한다. --%>
    <div class="modal-title"></div> <%-- 댓글 번호 --%>
    <div>
        <textarea rows="3" cols="30" id="replytext"></textarea>
    </div>
    <div>
        <button type="button" id="replyModBtn">댓글 수정</button>
        <button type="button" id="replyDelBtn">댓글 삭제</button>
        <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
    </div>
</div>

<h2>Ajax 댓글 연습 페이지</h2>
<div>
    <div>
        댓글 작성자: <input type="text" name="replyer" id="newReplyWriter" />
    </div>
    <br/>
    <div>
        댓글 내용: <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
    </div>
    <br/>
    <button type="button" id="replyAddBtn">댓글등록</button>
</div>

<br/>
<hr/>
<br/>

<%-- 댓글 목록이 출력되는 부분 --%>
<ul id="replies"></ul>

<script src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery 라이브러리 CDN --%>
<script type="text/javascript">
    let bno=4; // 게시판 번호

    getAllList(); // 댓글목록 함수 호출
    function getAllList() {
        $.getJSON("/replies/all/"+bno, function (data) {
            let str="";
            $(data).each(function () { // jQuery each() 함수로 반복
                str += "<li data-rno='"+this.rno+"' class='replyLi'>"
                +this.rno+" : <span class='com' style='color: red; font-weight:bold;'>"+ this.replytext +"</span>"
                +" <button>댓글수정</button></li><br/>";
            });
            $('#replies').html(str); // jQuery html() 함수로 해당영역에 문자와 태그를 변경적용
        });
    } // getAllList()

    // 댓글 추가
    $('#replyAddBtn').on('click', function() {
        let replyer = $('#newReplyWriter').val(); // 댓글 작성자
        let replytext = $('#newReplyText').val(); // 댓글 내용

        $.ajax({ // jQuery 비동기식 아작스 함수
            type: 'post',
            url: '/replies/insertReply', // 댓글저장 mapping주소
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: 'text',
            data: JSON.stringify({
                bno: bno,
                replyer: replyer,
                replytext: replytext
            }),
            success: function (result) { // 받아오는 것이 성공 시 호출되는 콜백함수
                if (result === 'SUCCESS') {
                    alert('댓글이 등록되었습니다!');
                    getAllList(); // 댓글목록 함수 호출 
                }
            }
        });
    });

    // 댓글 수정 화면
    $('#replies').on('click','.replyLi button', function() {
        let reply = $(this).parent(); // parent() 부모요소를 선택 => li tag를 가리킴
        let rno = reply.attr('data-rno'); // data-rno 속성값을 가져옴. => 결국 댓글번호
        let replytext = reply.children('.com').text(); // li tag 자식요소의 클래스 선택자 .com은 span tag를 가리킴. text() 함수로
        // 문자 댓글 내용만 가져옴.

        $('.modal-title').html(rno); // modal-title class 선택자에 댓글번호를 표시
        $('#replytext').val(replytext); // replytext id 선택자 textarea 입력박스에 기존댓글 내용을 표시
        $('#modDiv').show('slow'); // 댓글 수정화면 보이기
    });

    // 댓글수정 화면 닫기
    function modDivClose() {
        $('#modDiv').hide('slow');
    }

    // 댓글 수정완료
    $('#replyModBtn').on('click', function() {
        let rno = $('.modal-title').html(); // 댓글번호
        let replytext = $("#replytext").val(); // 수정할 댓글내용

        $.ajax({ // jQuery 비동기식 ajax 함수
            type: 'put', // 댓글 내용 수정 시 method 방식은 put
            url: '/replies/'+rno, // 수정완료 mapping 주소
            headers: {
                "Content-type" : "application/json",
                "X-HTTP-Method-Override":"PUT"
            },
            data:JSON.stringify({replytext:replytext}), // 수정할 내용 JSON 자료
            dataType: 'text',
                success:function(result) {
                if (result === 'SUCCESS') {
                    alert ('댓글이 수정되었습니다!');
                    $('#modDiv').hide('slow'); // 댓글 수정 화면 닫기
                    getAllList(); // 댓글목록 함수호출
                }
            }
        });
    });

    // 댓글 삭제
    $('#replyDelBtn').on('click', function() {
        let rno = $('.modal-title').html(); // 댓글 번호

        $.ajax({
            type: 'delete', // 삭제 method 방식
            url: '/replies/' + rno, // 삭제 mapping주소
            headers : {
                "Conten-Type" : "application/json",
                "X-HTTP-Method-Override" : "DELETE"
            },
            dataType: 'text',
            success: function(result) {
                if (result === 'SUCCESS') {
                    alert ('댓글이 삭제되었습니다!');
                    $('#modDiv').hide('slow');
                    getAllList();
                }
            }
        })
    })
</script>
</body>
</html>
