<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
div.vieworder>table, div.vieworder>table th, div.vieworder>table td{
border:1px solid; border-collapse: collapse;
}
</style>
<div class="vieworder">
	<table>
		<tr><th>주문번호</th><th>주문일자</th>
		<th>주문상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th></tr>
<c:set var="list" value="${requestScope.orderlist}"/>
<c:forEach var="info" items="${list}">
	<c:set var="order_no" value="${info.order_no}"/>
	<c:set var="order_dt" value="${info.order_dt}"/>
	<c:set var="lines" value="${info.lines}"/>
	<tr>		
		<td rowspan="${fn:length(lines)}">${order_no}</td>
		<td rowspan="${fn:length(lines)}">${order_dt}</td>
		<c:forEach begin="0" end="${fn:length(lines)-1}" step="1" var="i">
			<c:set var="line" value="${lines[i]}"/>
			<c:set var="p" value="${line.product}"/>
			<c:set var="prod_no" value="${p.prod_no}"/>
			<c:set var="prod_name" value="${p.prod_name}"/>
			<c:set var="prod_price" value="${p.prod_price}"/>
			<c:set var="order_quantity" value="${line.order_quantity}"/>
			${i>0 ? "</tr><tr>" : ""}
			<td>${prod_no}</td>
			<td>${prod_name}</td>
			<td>${prod_price}</td>
			<td>${order_quantity}</td>
		</c:forEach>
	</tr>	
</c:forEach>	
	</table>
</div>