<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
    	String[] titles = {
    			"서재응 완벽투구 ....",
    			"리틀감독 서재응"
    			,"박찬호 호투"
    	};
    for(int i=0; i<titles.length; i++) {
    	
    
    %>
    <%if(i==0) { %><strong><%} %>
    <%= titles[i] %>
    <%if(i==0) { %></strong><%} %>
<br>
<%} %>
</body>
</html>