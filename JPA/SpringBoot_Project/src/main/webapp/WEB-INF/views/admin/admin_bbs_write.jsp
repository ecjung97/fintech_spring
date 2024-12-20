<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="../include/admin_header.jsp" /> <%-- 상단 공통페이지 읽어오기 --%>

<%-- 관리자 메인 본문 --%>
<div id="aMain_cont">
	<div id="aBw_wrap">
      <h2 class="aBw_title">관리자 자료실 글쓰기</h2>
      <form method="post" action="admin_bbs_write_ok"
         onsubmit="return write_check();" enctype="multipart/form-data">
         <table id="aBw_t">
            <tr>
               <th>글쓴이</th>
               <td><input name="bbs_name" id="bbs_name" size="14" /></td>
            </tr>
            <tr>
               <th>글제목</th>
               <td><input name="bbs_title" id="bbs_title" size="35" /></td>
            </tr>
            <tr>
               <th>비밀번호</th>
               <td><input type="password" name="bbs_pwd" id="bbs_pwd"
                  size="14" /></td>
            </tr>
            <tr>
               <th>글내용</th>
               <td><textarea name="bbs_cont" id="bbs_cont" rows="9" cols="36"></textarea>
               </td>
            </tr>
            <tr>
               <th>파일첨부</th>
               <td><input type="file" name="uploadFile" /></td>
            </tr>
         </table>
         <div id="aBw_menu">
            <input type="submit" value="저장" /> <input type="reset" value="취소"
               onclick="$('#bbs_name').focus();" /> <input type="button"
               value="목록" onclick="location='admin_bbs_list?page=${page}';" />
         </div>
      </form>
   </div>
</div>

<jsp:include page="../include/admin_footer.jsp" /> <%-- 하단 공통페이지 --%>