<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMANTIC.jsp</title>
<style>
body{
background-image: url("http://hello-beautiful.co.kr/wp-content/uploads/2018/11/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg");
background-repeat:no-repeat;
}
nav>ul{
list-style:none;
padding-inline-start: 0px;
}
nav>ul>li{display:inline-block;}
footer{
	position:fixed;
	bottom: 0px;
	width: 100%;
	background-color: gray;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script>
$(function(){
	//dom트리에서 nav>ul>li>a객체들 찾기
	var aArr = $("nav>ul>li>a");
	
	$(aArr).click(function() {
			var vurl = $(this).attr("href");
			if (vurl == '/myjquery/logout') {
				$.ajax({
					url : vurl,
					method : 'get',
					success : function(result) {
						alert(result.trim());
						location.href="semantic.jsp";
					}
				});
			} else {
				$.ajax({
					url : vurl,
					method : 'get',
					success : function(result) {
						$("section").html(result);
					} //결과를 응답받은 응답내용이 function의 인자값으로 와야함.
				});//$.ajax 는 비동기방식
			}
			return false;
		});
	});
	//인라인 이벤트인 on은 사용하지않는 편이 좋음.
</script>
</head>
<body>
<header><h1>my web</h1></header>
<nav>메뉴
<jsp:include page="menu.jsp"/>
</nav>
<section>본문</section><!-- 메뉴 클릭할때마다 바뀜. -->
<footer>사업자 : 김우진연구소 | 대표 : 김삿갓</footer>
</body>
</html>