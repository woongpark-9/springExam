<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="color.jsp" %>
    <%
    String id = "";
    try{
    	Cookie[] cks = request.getCookies();
    	if(cks!=null){
    		for(int i=0; i<cks.length; i++){
    			if(cks[i].getName().equals("memId")){
    				id = cks[i].getValue();
    			}
    		}
    		if(id.equals("")) {
    			response.sendRedirect("cookieMemberLogin.jsp");
    		}
    	}else {
    		response.sendRedirect("cookieMemberLogin.jsp");
    	}
    }catch(Exception e){}
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="<%=bodyback_c %>" >
	<form method="post" action="cookieLogOut.jsp">
	<table width="300" border="1" align="center">
		<tr>
			<td align="center" bgcolor="<%=value_c %>">
				<b><%=id %></b>님이 로그인 하셨습니다.
			</td>
				</tr>
				<tr>
					<td align="center" bgcolor="<%=value_c %>">
						<input type="submit" value="로그아웃">
					</td>
				</tr>
				
	</table>
	</form>

</body>
</html>