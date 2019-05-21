<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
div.vieworder>table, div.vieworder>table th, div.vieworder>table td{
border:1px solid; border-collapse: collapse;
}
</style>
<div class="vieworder">
	<table>
		<tr><th>주문번호</th><th>주문일자</th>
		<th>주문상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th></tr>
<%List<OrderInfo> list = (List)request.getAttribute("orderlist");
	for(OrderInfo info : list){
%>	<tr>		
<% 		int order_no = info.getOrder_no();
		Date order_dt = info.getOrder_dt();
		//여러분들 디비테이블을 설계할떄 컬럼의 자료형을 날짜타입과 문자타입을 설계할수있지
		//어떤떄 날짜타입과, 문자타입
		//내가 직접 찾아서 입력해야하는 값은 문자타입으로 설계가 편함.
		//예를들어 졸업일자,출생년도는 문자타입으로 저장이 편하다.
		//시간이 정확히 필요한 정보들은 날짜타입으로 저장하는 것이 편하다.
		//예를들어 주문을 누르자마자 자동저장되게 하는 타입은 날짜타입이 편하다.
		//그렇다면 상품제조일자는? 문자타입이 적절하다.
		//예매사이트라면? 날짜타입이 적절하다.
 		List<OrderLine> lines = info.getLines();
%>		<td rowspan="<%=lines.size() %>"><%=order_no%></td>
		<td rowspan="<%=lines.size() %>"><%=order_dt%></td>
<% 
		//for(OrderLine line: lines){
			for(int i =0;i<lines.size();i++){
				
			OrderLine line = lines.get(i);
			Product p = line.getProduct();
			String prod_no = p.getProd_no();
			String prod_name = p.getProd_name();
			int prod_price = p.getProd_price();
			int order_quantity = line.getOrder_quantity();
			
%>    
	<%=i>0?"</tr><tr>":""%>
	<td><%=prod_no %></td><td><%=prod_name%></td><td><%=prod_price %></td><td><%=order_quantity %></td>
<%			
		}//end line 
		%>	</tr>		
<%	}//end info
%>
	</table>
</div>