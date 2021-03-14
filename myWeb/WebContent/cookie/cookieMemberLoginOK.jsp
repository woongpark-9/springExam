<%@page import="logon.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String id = request.getParameter("id");
    String passwd = request.getParameter("passwd");
    LogonDBBean dbb = LogonDBBean.getInstance();
    int check = dbb.userCheck(id, passwd);
    if(check==1){
    	//key,value
    	Cookie ck = new Cookie("memId",id);
    	ck.setMaxAge(20*60);
    	response.addCookie(ck);
    	response.sendRedirect("cookieLoginConfirm.jsp");
    }else if(check==0) {
    
    %>
    <script>
    	alert("비밀번호가 맞지 않습니다.");
    	//뒤로가기
    	history.go(-1);
    	
    </script>
    <%}else { %>
    <script>
    	alert("아이디가 맞지 않습니다.");
    	history.go(-1);
    </script>
    <%} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>