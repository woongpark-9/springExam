<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function addHtml() {
		$("#article").html('안녕하세요' + '<br>');
	}
</script>
</head>
<body>
	<div>
		<p id="article"></p>
		
	</div>
	<input type="button" value="추가하기" onClick="addHtml()" />

</body>
</html>