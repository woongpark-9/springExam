<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
	function deleteSave() {
		if(document.delForm.pass.value == "") {
			alert("비밀번호를 입력하십시요.");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>
<title>Insert title here</title>
</head>
<body bgcolor="${value_c}">
<center><b>글삭제</b></center><br>
<form method="post" name="delForm" action="/myWeb/board/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
	<table border="1" align="center" width="360">
		<tr height="30">
			<td align="center" bgcolor="${value_c}">
				<b>비밀번호를 입력해주세요.</b>
			</td>
		</tr>
		<tr height="30">
			<td align="center">
				비밀번호 :
				<input type="password" name="pass" size="8" maxlength="12">
				<input type="hidden" name="num" value="${num}">
			</td>
		</tr>
		<tr height="30">
			<td align="center" bgcolor="${value_c}">
				<input type="submit" value="글삭제">
				<input type="button" value="글목록" onclick="document.location.href='/myWeb/board/list.do?pageNum=${pageNum}'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>