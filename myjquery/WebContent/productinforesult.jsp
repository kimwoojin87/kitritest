<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Product product = (Product)request.getAttribute("productinfo"); %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form{
	border: 1px; border-style: dashed; border-color: rgba(0,0,0,.5); float: left;
}
</style>
<script>
$(function(){
	var $bt = $(".addcart");
	$bt.click(function(){
		$.ajax({
			url:'addcart',
			method:'get',
			data:
				'no=<%=product.getProd_no()%>&quantity='+$("input[name=quantity]").val(),
				success:function(result){
					//$("section").html(result);//html를 사용하면 해당내용을 채우라는 뜻
					$("section").append(result.trim()); //append는 start tag end tag 사이에 내용을 추가하라는뜻
				}
		});
		return false;
	});
});

</script>
</head>
<body>
<div>
	<button onclick="history.go(-1)">이전으로</button>
<!-- <form action="addcart" method="get"> -->
	<input type="hidden" name="no" value="<%=product.getProd_no()%>">
<div id="borderst"><img src="img/<%=product.getProd_no()%>.jpg" width="200px" height="200px"><br>
	카테고리 : <%=product.getProductcategori().getCate_no()%><br>
	상품번호 : <%=product.getProd_no()%><br>
	상품명 : <%=product.getProd_name()%><br>
	가격 : <%=product.getProd_price()%><br>
	상품설명 : <%=product.getProd_detail()%><br>
	수량선택 : <input type="number" name="quantity" value="1" min="1" max="99"><br>
	</div>
	<button class="addcart">장바구니담기</button>
<!-- </form> -->
<section>
</section>
</div>
</body>
</html>