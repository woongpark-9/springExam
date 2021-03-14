<%@ include file="view/color.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">
<p style="text-align: center">
<b>글목록(전체글:${count})</b><br>
</p>
<table width="700" align="center">
	<tr>
		<td align="right" bgcolor="${value_c}">
			<a href="/myWeb/board/writeForm.do">글쓰기</a>
		</td>
	</tr>
</table>
<!-- 만약 카운트(글 수) 가 0이라면 게시판에 저장된 글이 없습니다. 출력 -->
<c:if test="${count == 0}">
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
</c:if>
<!-- 글 수가 하나라도 있으면  글 띄어주기-->
<c:if test="${count > 0}">
	<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr height="30" bgcolor="${value_c}">
			<td align="center" width="50">번 호</td>
			<td align="center" width="250">제   목</td>
			<td align="center" width="100">작성자</td>
			<td align="center" width="150">작성일</td>
			<td align="center" width="50">조 회</td>
			<td align="center" width="100">IP</td>
		</tr>
		<!-- 답변형 게시글 -->
		<c:forEach var="article" items="${articleList}">
			<tr height="30">
				<td align="center" width="50">
					<c:out value="${number}"/>
					<c:set var="number" value="${number - 1}"/>
				</td>
				<td width="250">
				<!-- depth가 0보다 크면 답글 -->
					<c:if test="${article.depth > 0}">
						<img src="images/level.gif" width="${5 * article.depth}" height="16">
						<img src="images/re.gif">
					</c:if>
					<c:if test="${article.depth == 0 }">
						<img src="images/level.gif" width="${5 * article.depth}" height="16">
					</c:if>
					
					<a href="/myWeb/board/content.do?num=${article.num}&pageNum=${currentPage}&hit=y">
						${article.subject}
					</a>
					<c:if test="${article.readcount >= 30}">
						<img src="images/hot.gif" border="0" height="16">
					</c:if>
				</td>
				<td align="center" width="100">
					<a href="mailto:${article.email}">${article.writer}</a>
				</td>
				<td align="center" width="150">
					<fmt:formatDate value="${article.regdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td align="center" width="50">
					${article.readcount}
				</td>
				<td align="center" width="100">
					${article.ip}
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<br>
<c:set var="pageNum" value="1" />
<div style="width: 100%; text-align: center">
<br>
	<!-- 페이징 처리 -->
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1}"/>
	<c:set var="pageCount" value="${count / pageSize + imsi}"/>
	<c:set var="pageBlock" value="${3}"/>
	<fmt:parseNumber var="result" value="${(currentPage - 1) / pageBlock}" integerOnly="true"/>
	<c:set var="startPage" value="${result * pageBlock + 1}"/>
	<c:set var="endPage" value="${startPage + pageBlock - 1}"/>
	<c:if test="${endPage > pageCount}"> 
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	<c:if test="${startPage > pageBlock}">
		<a href="<c:url value='/board/list.do?pageNum=${startPage - pageBlock}'/>">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == pageNum}">
			<a href="<c:url value='/board/list.do?pageNum=${i}'/>" style="color: red;">[${i}]</a>
		</c:if>
		<c:if test="${i != pageNum}">
			<a href="<c:url value='/board/list.do?pageNum=${i}'/>">[${i}]</a>
		</c:if>
	</c:forEach>
	<c:if test="${endPage < pageCount}">
		<a href="<c:url value='/board/list.do?pageNum=${startPage + pageBlock}'/>">[다음]</a>
	</c:if>
</div>

<form method="post" action="/myWeb/board/list.do?pageNum=${pageNum }"  name="searchForm"  onsubmit="return searchCheck()">
<!-- 검색 기능  서브밋 하게되면 자바스크립트로 조건검사 후 onsubmit-->
<div style="width: 100%; text-align:center">
<table align="center">
	<tr>
		<td> 
				<select name="search">
					<option value="writer">작성자</option>
					<option value="subject">제목</option>
					<option value="subjectAndContent">제목+내용</option>
				</select> 
				<input type="text" name="searchvalue" size="30"> 
				&nbsp;&nbsp;&nbsp;<input type="submit" value="검색"  >
		</td>
	<tr>
</table>
</div>
</form>
</body>
</html>