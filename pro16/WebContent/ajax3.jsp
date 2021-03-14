<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function fn_process() {
		var _id = $("#t_id").val();
		if(_id==''){
			alert("ID를 입력하세요");
			return;
		}
		$.ajax({
			type: "post",
			async: false,
			url: "http://localhost:8080/pro16/mem",
			dataType: "text",
			data: {id: _id},
			success: function (data,textStatus) {
				if(data == 'usable'){
					$('#message').text("사용할 수 있는 ID입니다.");
					$('#btn_duplicate').prop("disabled",true);
				}else {
					$('#message').text("사용할 수 없는 id입니다.");
				}
				},
				 error:function(data,textStatus){
			          alert("에러가 발생했습니다.");ㅣ
			       },
			       complete:function(data,textStatus){
			          alert("작업을완료 했습니다");
			       }
			    });  //end ajax	 
			 }		
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>