<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Refresh" content="3;url=member.mdo?cmd=login">
<title>회원탈퇴</title>
</head>
<body>
<c:set var="result" value="${result}"/>
<c:if test="${result == 0}">
<script type="text/javascript">
	alert("비밀번호가 맞지 않습니다.");
	history.go(-1);
</script>
</c:if>
회원정보가 삭제되었습니다.<br>
안녕히 가세요! ㅠ.ㅠ<br>
1초후에 Login page로 이동합니다.
</body>
</html>