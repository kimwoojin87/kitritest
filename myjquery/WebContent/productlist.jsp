<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록리스트</title>
<style type="text/css">
.styleman{
	border: 1px; border-style: dashed; border-color: teal; float: left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	function product(){
		$.ajax({
			url : "productlist",
			success : function(result) {
				$("#list").html(result);
			}
		});
		return false;
	}
</script>
</head>
<body>
<div>
<h3>상품목록을 봐주세요.</h3>
	<button onclick="javascript:product()" id="submit" name="submit">상품목록확인</button>
	<div id="list">	
	</div>
</div>
</body>
</html>