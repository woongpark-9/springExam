<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:requestEncoding  value="utf-8"/>
	<%String name= request.getParameter("name"); %>
안녕하세요 , <%=name %> 회원님!
