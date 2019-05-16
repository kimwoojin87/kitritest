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
</head>
<body>
<form>
<div id="borderst"><img src="img/<%=product.getProd_no()%>.jpg" width="200px" height="200px"><br>
	카테고리 : <%=product.getProductcategori().getCate_no()%><br>
	상품번호 : <%=product.getProd_no()%><br>
	상품명 : <%=product.getProd_name()%><br>
	가격 : <%=product.getProd_price()%><br>
	상품설명 : <%=product.getProd_detail()%><br>
	수량선택 : <select name="number">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	</select>
	</div>
</form>
<button onclick="history.go(-1)">이전으로</button>
</body>
</html>