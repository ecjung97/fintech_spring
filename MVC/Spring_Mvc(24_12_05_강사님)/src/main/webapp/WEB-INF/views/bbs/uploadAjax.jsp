<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비동기식 이진파일 업로드 폼</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            $('#uploadBtn').on('click', function (e) {

                let formData = new FormData();
                /* 첨부파일을 upload 하는 또 다른 방식은 비동기식 Ajax를 이용한 file data만을 전송. Ajax를 이용ㅇ하는 첨부파일 처리는
                FormData라는 객체를 이용하는데 IE의 경우 10버전 이후부터 지원된다. */
                var inputFile = $("input[name='uploadFile']"); // file 객체를 구함
                var files = inputFile[0].files; // 첫번째 file객체에서 첨부한 file을 배열로 구한다.
                console.log(files); // 콘솔에 출력

                // 첨부파일을 formData에 추가
                for (let i=0; i<files.length; i++) {
                    formData.append("uploadFile", files[i]);
                }

                $.ajax({
                    url: '/bbs/uploadAjaxOK',
                    processData: false, // processData data를 content type에 맞게 변환여부
                    contentType: false, // 요청 content type
                    data : formData, // formData 자체를 전송
                    type : 'POST', // 보내는 방식은 post
                    success : function(result) {
                        // 받아오는 것이 성공시 호출되는 callback함수
                    }
                }); // jQuery 비동기식 Ajax
            });
        });
    </script>
</head>
<body>
<h2>Upload with Ajax</h2>
<input type="file" name="uploadFile" multiple />
<button type="button" id="uploadBtn">업로드</button>
</body>
</html>