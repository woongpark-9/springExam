<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function fn_process() {
		var value=$("#t_input").val();
		$("#t_output").val(value);
	}
</script>
</head>
<body>
	<input type="text" id="t_input"/>
	<input type="button" value="입력하기" onClick="fn_process()" /><br><br>
	<div>
		결과 :<br>
		<input type="text" id="t_output" disabled />	</div>
	
	
</body>
</html>