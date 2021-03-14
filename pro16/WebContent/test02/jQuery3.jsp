<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js">
</script>
<script type="text/javascript">
	function addImage() {
		$(".class1").html("<img src='/pro16/image/duke.png'>");
	}
</script>
</head>
<body>
	<div class="class1"></div>
	<div class="class1"></div>
	<input type="button" value="이미지 추가하기" onClick="addImage()" />
</body>
</html>