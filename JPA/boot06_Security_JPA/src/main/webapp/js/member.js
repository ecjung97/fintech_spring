/**
 * member.js
 */

function join_check(){
   if($.trim($('#mem_id').val()) === ''){
      alert('회원아이디를 입력하세요!');
      $('#mem_id').val(''); // 아이디 입력필드 초기화
      $('#mem_id').focus(); // 아이디 입력박스로 포커스 이동
      return false;
   }
   
   let mem_pwd = $.trim($('#mem_pwd').val());
   let mem_pwd2 = $.trim($('#mem_pwd2').val());
   if(mem_pwd.length === 0){
      alert("비밀번호를 입력하세요!");
      $('#mem_pwd').val('').focus();
      return false;
   }
   
   if(mem_pwd2 == ''){
      alert('비밀번호 확인을 입력하세요!');
      $('#mem_pwd2').val('').focus();
      return false;
   }
   
   if(mem_pwd != mem_pwd2){
      alert('비밀번호가 다릅니다!');
      $('#mem_pwd').val('');
      $('#mem_pwd2').val('');
      $('#mem_pwd').focus();
      return false;
   }
   
   if($.trim($('#mem_name').val()) == ''){
      alert('회원이름을 입력하세요!');
      $('#mem_name').val('').focus();
      return false;
   }
} // join_check()

function id_check() {
	$('#idcheck').hide(); // idcheck 영역을 숨김
	$mem_id = $.trim($('#mem_id').val());
	
	// 아이디 입력길이 체크
	if ($mem_id.length < 4) {
		$newtext = "<font color='red' size='3'><b>아이디는 4자 이상이어야 합니다!</b></font>";
		$('#idcheck').text(''); // idcheck 영역 문자 초기화
		$('#idcheck').show(); // idcheck 영역을 보이게 한다.
		$('#idcheck').append($newtext); // 경고 문자열 추가
		$('#mem_id').val('').focus();
		return false;
	}
	
	if ($mem_id.length > 12) {
		$newtext = "<font color='red' size='3'><b>아이디는 12자 이하이어야 합니다!</b></font>";
		$('#idcheck').text('');
		$('#idcheck').show();
		$('#idcheck').append($newtext);
		$('#mem_id').val('').focus();
		return false;
	}
	
	// 정규포현식으로 아이디 유효성 검증
	if (!(validate_userid($mem_id))) {
		$newtext = "<font color='red' size='3'><b>아이디는 영문소문자, 숫자, _조합만 가능합니다!</b></font>";
		$('#idcheck').text('');
		$('#idcheck').show();
		$('#idcheck').append($newtext);
		$('#mem_id').val('').focus();
		return false;
	}
	
	/* 비동기식 아작스 통신 시 403 forbidden 에러 발생 해결법)
	   에러 원인 -> csrf(Cross-site request forgery) 의 token 누락으로 발행한다.
	   
	   member_join.jsp에 head 태그 내에 csrf meta tag를 추가해 준다.
	   <meta name="_csrf_header" content="${_csrf.headerName}" >
	   <meta name="_csrf" content="${_csrf.token}" >
	   
	   let header = $("meta[name='_csrf_header']").attr('content');
	   let token = $("meta[name='_csrf']").attr('content);
	   
	   beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
	   },
	   코드를 추가하면 403 접근 금지 에러가 해결된다.
	 */
	
	   let header = $("meta[name='_csrf_header']").attr('content');
	   let token = $("meta[name='_csrf']").attr('content');
	   
	   // 비동기식 아이디 중복확인
	   $.ajax({ // 비동기식 아작스 실행
			type:'POST', // 데이터를 보내는 방법
			url:'member_idcheck', // 매핑주소
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: {"id":$mem_id}, // id 파라미터 이름에 아이디를 저장
			datatype:'int', // 서버로 부터 받아오는 타입
			success:function(result) { // 받아오는 것이 성공 시 호출되는 콜백함수이다. 받아온 데이터는 매계변수 result에 저장
				if (result == 1) {
					$newtext = "<font color='red' size='3'><strong>중복 아이디 입니다.</strong></font>";
					$('#idcheck').text('');
					$('#idcheck').show();
					$('#idcheck').append($newtext);
					$('#mem_id').val('').focus();
					return false;
				}else { // 중복 아이디가 아니면 즉 사용가능한 아이디이면
					$newtext = "<font color='blue' size='3'><b>사용가능한 아이디입니다.</b></font>";
					$('#idcheck').text('');
					$('#idchkeck').show();
					$('#idcheck').append($newtext);
					$('#mem_pwd').focus(); // 비번 입력필드로 포커스 이동
				} // if else
			},
			error:function() { // 비동기식 아작스로 백엔트 서버 데이터를 못가져와서 에러가 발생했을 때 호출되는 함수이다.
				alert('data error');
			}
	   }); // $.ajax({})
} // idcheck();

// 아이디를 정규표현식으로 유효성 검증
function validate_userid($id) {
	let pattern = new RegExp(/^[a-z0-9_]+$/); // 아이디를 영문소문자와 숫자 와 _조합으로 처리
	return pattern.test($id);
}















