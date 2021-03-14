<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입폼</title>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript">
// 중복체크 false일 경우 다시 idDuplication 설정
</script>
</head>
<body>
<h3 align="center">회원 가입</h3>
<form method="post" action="member.mdo?cmd=regProc" name="regForm">
	<table border="1">
		<tr>
			<td colspan="2" align="center">회원가입 정보 입력</td>
		</tr>
		<tr>
			<td align="right">아이디 :</td>
			<td>
				<input type="text" name="id" size="20">&nbsp;
				<input type="button" value="중복확인" onclick="idCheck(this.form.id.value)">
				<input type="hidden" name="idDuplication" value="idCheck">
			</td>
		</tr>
		<tr>
			<td align="right">패스워드: </td>
			<td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td align="right">패스워드 확인: </td>
			<td><input type="password" name="repass"></td>
		</tr>
		<tr>
			<td align="right">이름: </td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td align="right">전화번호: </td>
			<td>
				<select name="phone1">
					<option value="02">02</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="018">018</option>
					<option value="019">019</option>
				</select> -
				<input type="text" name="phone2" size="5"> -
				<input type="text" name="phone3" size="5">
			</td>
		</tr>
		<tr>
			<td align="right">이메일: </td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td align="right">우편번호: </td>
			<td>
				<input type="text" name="zipcode">
				<input type="button" value="찾기" onclick="zipCheck()">
			</td>
		</tr>
		<tr>
			<td align="right">주소1: </td>
			<td><input type="text" name="address1" size="50"></td>
		</tr>
		<tr>
			<td align="right">주소2: </td>
			<td><input type="text" name="address2" size="50"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="회원가입" onclick="inputCheck()">
				<input type="reset" value="다시입력">
			</td>
		</tr>
	</table>
</form>
</body>
</html>