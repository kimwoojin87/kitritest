<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/journal/bootstrap.min.css" rel="stylesheet" integrity="sha384-ciphE0NCAlD2/N6NUApXAN2dAs/vcSAOTzyE202jJx3oS8n4tAQezRgnlHqcJ59C" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>전체사원</title>
<style>
#array{
	border: thin; overflow: auto; border-color: tomato;
}
section{
	height: 60%; width: 60%;
}
.btn-toolbar{
	display : inherit;
}
</style>
<script>
function search(currentpage){
	
	$.ajax({
		url : "/testweb/memberlist",
		data : "currentpage="+currentpage,
		success : function(result){
			$("#index").html(result);
		}	
	});
}


function nextPage(){
	var curpage = $(".btn-secondary").val();
	var ccc = (int)curpage + 1;
	search(ccc);
}
</script>
</head>
<body>
<div id="array" align="center" >
<div>
<button type="button" class="btn btn-danger" onclick="javascript:search(1)" id="search">검색하기</button>
</div>
<section id="index">여기가 입력될 곳</section>
</div>
</body>
</html>