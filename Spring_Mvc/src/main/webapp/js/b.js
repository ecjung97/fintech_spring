/**
 * b.js
 */

function check(){
	if($.trim($('#writer').val()) == ''){
		//alert('글쓴이를 입력하세요!');
		$('#error_writer').html("<font size='1' color='red'>글쓴이를 입력하세요!</font>");
		$('#writer').val('').focus();
		return false;
	}else{//글쓴이를 입력한 경우
		$('#error_writer').text('');
	}
	
	if($.trim($('#title').val()) == ''){
		//alert('글제목을 입력하세요!');
		$('#error_title').html("<font size='1' color='red'>글제목을 입력하세요!</font>");
		$('#title').val('').focus();
		return false;
	}else{
		$('#error_title').text('');
	}
		
	if($.trim($('#content').val()) == ''){
		//alert('글내용을 입력하세요!');
		$('#error_content').html("<font size='1' color='red'>글내용을 입력하세요!</font>");
		$('#content').val('').focus();
		return false;
	}else{
		$('#error_content').text('');
	}
}

function reSet(){
	$('#writer').focus();//글쓴이 입력필드로 포커스 이동
	$('#error_writer').text('');//글쓴이 에러메시지 초기화
	$('#error_title').text('');
	$('#error_content').text('');
}
