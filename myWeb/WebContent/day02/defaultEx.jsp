<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
	<header>
	<div id="login"><a href="#">Login</a></div>
	<div class="clear"></div>
	<div id="logo"><img alt="javaLine Web" src="/myhtml/images/logo.png"></div>
		<nav id="top_menu">
			<ul>
				<li><a href="#">Home</a>
				<li><a href="#">YoungWoong</a>
				<li><a href="#">Board</a>
				<li><a href="#">Ajax Board</a>
				<li><a href="#">Member</a>
			</ul>
		</nav>
	</header>
	<div class="clear"></div>
	<div id="sub_img"></div>
	<nav id="sub_menu">
		<ul>
			<li><a href="#">Login</a>
			<li><a href="#">Join Us</a>
			<li><a href="#">Board</a>
			<li><a href="#">Ajax Board</a>
		</ul>
	
	</nav>
	<article id="contents">
	<h1>My Board</h1>
	<input type="button" value="write" class="btn"/>
	<table id="board">
		<tr>
			<th class="tno">NO.</th>
			<th class="twriter">Writer</th>
			<th class="ttitle">Title</th>
			<th class="tread">Read</th>
			<th class="tdate">Date</th>
		</tr>
		<tr>
			<td>21</td>
			<td>Tommy.Lee</td>
			<td class="left">Who Are You</td>
			<td>29</td>
			<td>2021.02.09</td>
		</tr>
			
		
	
	</table>
	<form action="#" method="post" name="find_frm" onsubmit="return check()">
		<div id="table_search">
			<select class="select_box" name="find" size="1">
				<option value="writer">이름</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
				
			
			</select>
			<input type="text" class="input_box" name="find_box" />
			<input type="submit" value="search" class="btn">
			
		
		</div>
	</form>
	<div class="clear"></div>
	<div id="page_control">
		<a href="#">Prev</a><a href="#">1</a><a href="#">2</a>
		<a href="#">3</a><a href="#">4</a><a href="#">5</a>
		<a href="#">6</a><a href="#">7</a><a href="#">8</a>
		<a href="#">9</a><a href="#">10</a>		<a href="#">Next</a>
	</div>
	</article>
	<div class="clear"></div>
	<footer>
		<hr>
		<div id="copy">
		copy
		</div>
	</footer>
	</div>
	<div id="social">
	<img alt="Facebook" src="/myhtml/images/facebook.gif">
	<img alt="Twiter" src="/myhtml/images/twitter.gif">
	</div>
	
</body>
</html>