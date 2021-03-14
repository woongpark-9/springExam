<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function fn_process(){
    $.ajax({
        type:"post",
        async:false, 
        url:"http://localhost:8080/pro16/ajaxTest2",
        dataType:"xml",
        success:function (info,textStatus){
          $(info).find("book").each(function(){  
              var title=$(this).find("title").text();
              var writer=$(this).find("writer").text();
              var image=$(this).find("image").text();
              $("#bookInfo").append(
                  	"<p>" +title+ "</p>" +
	                "<p>" +writer + "</p>"+
 	                "<img src="+image + "   />"				
              );
          });
        },
        error:function(data,textStatus){
        	 alert("에러가 발생했습니다.");ㅣ
        },
        complete:function(data,textStatus){
           alert("작업을완료 했습니다");
        }
   }); 
 }
</script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div id="bookInfo"></div>
<input type=button value="도서정보 요청"  onclick="fn_process()">
</body>
</html>