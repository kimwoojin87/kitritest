<%@page import="org.json.JSONArray"%>
<%@page import="com.sun.xml.internal.stream.Entity"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.Map"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%><%-- <%
ObjectMapper mapper = new ObjectMapper();
//mapper.writeValue(out, request.getAttribute("rcart"));
String result = mapper.writeValueAsString(request.getAttribute("rcart"));
%><%=result%> --%>
<%
//1.map을 다 풀어헤침
Map<Product,Integer> map = (Map)(request.getAttribute("rcart"));
System.out.println("rcart : "+map);
ObjectMapper mapper = new ObjectMapper();
JSONArray jsonArray = new JSONArray();
for(Map.Entry<Product,Integer> entry : map.entrySet()){
	JSONObject json = new JSONObject();
	JSONObject productjson = new JSONObject(mapper.writeValueAsString(entry.getKey()));
	json.put("product", productjson);
	json.put("quantity",entry.getValue());
	
	jsonArray.put(json);
	System.out.println(json);
}
%>
<%=jsonArray.toString()%>
//product:{Product 객체 json오브젝트}
//1.product : 제이슨객체
//2.quantity : 제이슨객체
//3.새로운 제이슨 추가

//

//

//2.풀어헤친 맵을 제이슨 객체로 재생성


//3.생성한 제이슨을 제이슨배열 형태로 만들기
