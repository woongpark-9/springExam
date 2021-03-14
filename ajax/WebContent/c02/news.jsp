<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="httpRequest.js"></script>
<script type="text/javascript">
function loadNews() {
	sendRequest("getNewsTitles.jsp",null,loadedNews,"GET");
}
function loadedNews() {
	if(httpRequest.readyState == 4) {
		if(httpRequest.status == 200) {
			var newsList = document.getElementById("newsList");
			newsList.innerHTML = httpRequest.responseText;
		}
	}
}
window.onload = function() {
	loadNews();
}
</script>
</head>
<body>
	<div id="newsList" ></div>

</body>
</html>