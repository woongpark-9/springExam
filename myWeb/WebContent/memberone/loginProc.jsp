<%@page import="memberone.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <jsp:useBean id="dao" class="memberone.StudentDAO"/> --%>
<% 
	request.setCharacterEncoding("utf-8"); 
	StudentDAO dao = StudentDAO.getInstance();
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	int check = dao.loginCheck(id, pass);
%>
<%
	if(check == 1) { //로그인 성공
		session.setAttribute("loginID", id);
		response.sendRedirect("login.jsp");
	}else if(check == 0) { // 비밀번호 오류 
%>
	<script>
		alert("비밀번호가 틀렸습니다.");
		history.go(-1); // 이전 문서로 이동
	</script>
<%
	}else { // 아이디가 존재하지 않을 경우
%>
	<script>
		alert("아이디가 존재하지 않습니다.");
		history.go(-1); 
	</script>
<%
	}
%>