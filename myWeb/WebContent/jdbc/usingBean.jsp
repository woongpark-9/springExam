<%@page import="jdbc.tempMemberVO"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
<h2>JSP Bean 데이터베이스 연동</h2>
<h3>회원정보</h3>
<table border="1" bordercolor="#0000ff">
<tr>
	<td><strong>ID</strong></td>
	<td><strong>PASSWD</strong></td>
	<td><strong>NAME</strong></td>
	<td><strong>MEM_NUM1</strong></td>
	<td><strong>MEM_NUM2</strong></td>
	<td><strong>E_MAIL</strong></td>
	<td><strong>PHONE</strong></td>
	<td><strong>ZIPCODE/ADDRESS</strong></td>
	<td><strong>JOB</strong></td>
</tr>
<jsp:useBean id="dao" class="jdbc.tempMemberDAO"/>
<%
	Vector<tempMemberVO> vlist = dao.getMemberList();
	int counter = vlist.size();
	for(int i=0; i<vlist.size(); i++) {
		tempMemberVO vo = vlist.elementAt(i);
%>
<tr>
	<td><%= vo.getId() %></td>
	<td><%= vo.getPasswd() %></td>
	<td><%= vo.getName() %></td>
	<td><%= vo.getMem_num1() %></td>
	<td><%= vo.getMem_num2() %></td>
	<td><%= vo.getEmail() %></td>
	<td><%= vo.getPhone() %></td>
	<td><%= vo.getZipcode() %>/<%= vo.getAddress()%></td>
	<td><%= vo.getJob() %></td>
</tr>
<%
	}
%>
</table><br>
total records : <%= counter %>
</body>
</html>