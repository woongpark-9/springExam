<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- key와 value가 String인 특수한 MAP : properties -->
<fmt:bundle basename="bundle.testBundle">
<fmt:message key="TITLE" var="title"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
<fmt:message key="NAME"/>
<br>
<c:if test="${not empty param.id}">
	<fmt:message key="MESSAGE">
		<fmt:param value="${param.id}"/>
	</fmt:message>
</c:if>
</body>
</html>
</fmt:bundle>