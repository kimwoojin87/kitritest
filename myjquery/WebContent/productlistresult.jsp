<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<Product> list =(List<Product>)request.getAttribute("productlist");%>
<%//서블릿에서 지정한 "productlist"로 리퀘스트받아서 사용해야함 %>
<script>
function img(name){
	location.href = "productinfo?Prod_no="+name;
}
</script>
<div>
<%if(list.size() != 0){
	//사이즈가 0이 아니니까 태그를 뿌려줌
	for(int i=0 ; i<list.size() ; i++){
%>		
	<div class="styleman"><a><img src="img/<%=list.get(i).getProd_no()%>.jpg" width="200px" height="200px" onclick="javascript:img('<%=list.get(i).getProd_no()%>')" alt="<%=list.get(i).getProd_name()%>"/></a><br>
	카테고리 : <%=list.get(i).getProductcategori().getCate_name()%><br>
	상품번호 : <%=list.get(i).getProd_no()%><br>
	상품명 : <%=list.get(i).getProd_name()%><br>
	가격 : <%=list.get(i).getProd_price()%><br>
	</div>
	<%}
}else{
 	//사이즈가 0이니까 데이터가없습니다 화면에 뿌림.
%>	
	데이터가 없습니다...
<%}%>
</div>