<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	try {
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num);
%>
<body bgcolor="<%= bodyback_c %>">
<center><b>글수정</b></center><br>
<form method="post" name="writeForm" action="updateProc.jsp?pageNum=<%= pageNum %>" onsubmit="return writeSave()">
	<input type="hidden" name="num" value="<%= article.getNum() %>">
	<table border="1" align="center" bgcolor="<%= bodyback_c %>">
		<tr>
			<td align="right" colspan="2" bgcolor="<%= value_c %>">
				<a href="list.jsp">글 목록</a>
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="<%= value_c %>" align="center">이름</td>
			<td width="330">
				<input type="text" size="12" maxlength="12" name="writer" value="<%= article.getWriter() %>">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="<%= value_c %>" align="center">제목</td>
			<td width="330">
				<input type="text" size="50" maxlength="50" name="subject" value="<%= article.getSubject() %>">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="<%= value_c %>" align="center">이메일</td>
			<td width="330">
				<input type="text" size="30" maxlength="30" name="email" value="<%= article.getEmail() %>">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="<%= value_c %>" align="center">내용</td>
			<td width="330">
				<textarea rows="13" cols="50" name="content"><%= article.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="<%= value_c %>" align="center">비밀번호</td>
			<td width="330">
				<input type="password" size="10" maxlength="10" name="pass">
			</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="<%= value_c %>" align="center">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="window.location='list.jsp?pageNum=<%= pageNum %>'">
			</td>
		</tr>
	</table>
</form>
<%
	}catch(Exception e){}
%>
</body>
</html>