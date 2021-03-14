<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div#displayArea {
	width: 200px;
	height: 200px;
	border: 5px double #6699ff;
}
</style>
<script src="../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$("#bt1").click(function(){
			if( $(this).val() == "보여주기"){
			$("#displayArea").html("<img id='img' src='ansi_main2s.png' border='0'/>");
				

				$(this).val("지우기");
			}else {
				$("#img").remove();
				$(this).val("보여주기");

			}
			
		});

	});
</script>
</head>
<body>
	<div id="displayArea">이곳의 내용이 변경</div>
	<input id ="bt1" type="button" value="안녕하세요">
</body>
</html>