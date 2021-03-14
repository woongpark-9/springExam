<%@page import="boardone.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="boardone.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<!-- 컬러변수 포함시키기 -->
<%@ include file="view/color.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!

	int pageSize = 5; // 한 페이지에 보여줄 글의 수
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 %>
 <%
 
 	String pageNum = request.getParameter("pageNum");
 	//만약 페이지넘버 값이 없다면 기본값 1로 설정해주기
 	if(pageNum == null) {
 		pageNum = "1";
 	}
 	int currentPage = Integer.parseInt(pageNum); // 현재 페이지
 	int startRow = (currentPage - 1) * pageSize + 1; // 시작하는 글번호
 	int endRow = currentPage * pageSize; // 끝나는 글번호
 	
 	int count = 0; 
 	int number = 0;
 	List<BoardVO> articleList = null;
 	BoardDAO dbPro = BoardDAO.getInstance();
 	count = dbPro.getArticleCount(); // 전체 글의 수
 	//만약 글의수가 0보다 크면 글 리스트를 가져옴
 	if(count > 0) {
 		articleList = dbPro.getArticles(startRow, endRow); 
 	}
 	
 	number = count - (currentPage - 1) * pageSize; // 화면에 보여줄 게시글 번호
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="<%= bodyback_c %>">
<p style="text-align: center">
<b>글목록(전체글:<%= count %>)</b><br>
</p>
<table width="700" align="center">
	<tr>
		<td align="right" bgcolor="<%= value_c %>">
			<a href="writeForm.jsp">글쓰기</a>
		</td>
	</tr>
</table>
<% if(count == 0) { %>
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
<% }else { %>
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="<%= value_c %>">
		<td align="center" width="50">번 호</td>
		<td align="center" width="250">제   목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조 회</td>
		<td align="center" width="100">IP</td>
	</tr>
	<%
		for(int i=0; i<articleList.size(); i++) {
			BoardVO article = articleList.get(i);			
	%>
	<tr height="30">
		<td align="center" width="50"><%= number-- %></td>
		<td width="250">
		<!-- 수정 5 -->	
		<%
			// 들여쓰기 관련 
			// depth가 높을수록 깊이가 크니 5 * depth만큼 wid값을 설정
			int wid = 0;
			if(article.getDepth() > 0) {
				wid = 5 * (article.getDepth());				
		%>	
			<img src="images/level.gif" width="<%= wid %>" height="16">
			<img src="images/re.gif">
		<%
			}else {
		%>
			<img src="images/level.gif" width="<%= wid %>" height="16">
		<%	} %>
			
			<a href="content.jsp?num=<%= article.getNum() %>&pageNum=<%= currentPage %>&hit=y">
				<%= article.getSubject() %>
			</a>
			<% if(article.getReadcount() >= 10) { %>
				<img src="images/hot.gif" border="0" height="16">
			<% } %>
		</td>
		<td align="center" width="100">
			<a href="mailto:<%= article.getEmail() %>"><%= article.getWriter() %></a>
		</td>
		<td align="center" width="150">
			<%= sdf.format(article.getRegdate()) %>
		</td>
		<td align="center" width="50">
			<%= article.getReadcount() %>
		</td>
		<td align="center" width="100">
			<%= article.getIp() %>
		</td>
	</tr>
	<% } %>
</table>
<% } %>

<div style="width: 100%; text-align: center">
<%
	if(count > 0) { // 글이 하나라도 있으면
		int pageBlock = 3; // 한 블록에 보여줄 페이지 수
		// 한번에 구하면 오류가 날수있기 때문에 임시 값을 설정
		int imsi = count % pageSize == 0 ? 0 : 1;
		int pageCount = count / pageSize + imsi; // 전체 페이지 수
		int startPage = (int)((currentPage - 1)/pageBlock) * pageBlock + 1; // 시작 페이지
		int endPage = startPage + pageBlock - 1; // 끝 페이지
		
		if(endPage > pageCount) endPage = pageCount;
		
		if(startPage > pageBlock) {
%>
			<a href="list.jsp?pageNum=<%= startPage - pageBlock %>">[이전]</a>
<%
		}
		for(int i=startPage; i<=endPage; i++) {
%>	
			<a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
		}
		if(endPage < pageCount) {
%>
			<a href="list.jsp?pageNum=<%= startPage + pageBlock %>">[다음]</a>
<%
		}
	}
%>
</div>
</body>
</html>