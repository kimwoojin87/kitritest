<%@page import="com.kitri.service.ProductService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!ProductService service = new ProductService(); %>
<%	
	//HttpSession htsession = request.getSession();
	/* Map<Product,Integer>map = (Map)(htsession.getAttribute("rcart")); */
	// Map<Product, Integer> map = (Map<Product, Integer>)htsession.getAttribute("cart");
	Map<Product,Integer>map=(Map)(request.getAttribute("rcart"));
	System.out.println(map); 
	Map<Product,Integer>map2=new HashMap<Product,Integer>();
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
<div>
<table>
<%	Set<Product> keys = map.keySet();%>
<th>
	<td></td>
</th>
<% //Product array[] = (Product[])keys.toArray();  //첫번쨰 포문 돌리는 시작점.%>
<%    for(Product prod:keys){%>
    	상품번호:<%=prod.getProd_no()%><br>
   		상품가격:<%=prod.getProd_price()%><br>
    	상품이름:<%=prod.getProd_name()%><br>
    	<%Product p1 = service.findByNo(prod.getProd_no());
    		int quantity = map.get(prod);
    		map2.put(p1,quantity);
    	%>
    	상품수량:<%=quantity %><br>------------<br>
   <%  } %>
</table>
</div> 
</body>
</html>