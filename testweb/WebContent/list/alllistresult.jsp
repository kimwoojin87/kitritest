<%@page import="mine.hello.dto.MemDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%List<MemDTO> list = (List)request.getAttribute("hmemberlist");%>
<!DOCTYPE html>
<div>
	<table class="table table-hover">
		<thead>
  		  <tr>
    		  <th scope="col">사원번호</th>
     		 <th scope="col">성</th>
     		 <th scope="col">이름</th>
     		 <th scope="col">이메일</th>
     		 <th scope="col">전화번호</th>
     		 <th scope="col">급여</th>
  		  </tr>
 		 </thead>
 		 <tbody>
<%if(list.size() != 0){%>
	<%for(int i=0;i<list.size();i++){%>
  			<tr class="table-warning">
    		  <td><%=list.get(i).getEmployee_id()%></td>
    		  <td><%=list.get(i).getFirst_name()%></td>
    		  <td><%=list.get(i).getLast_name()%></td>
    		  <td><%=list.get(i).getEmail()%></td>
    		  <td><%=list.get(i).getPhone_number()%></td>
     		  <td><%=list.get(i).getSalary()%></td>
   		 	</tr>	
<% 	}
	}%>
  		</tbody>
	</table>
  		<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
  <div class="btn-group mr-2" role="group" aria-label="First group">
    <button type="button" class="btn btn-secondary">1</button>
    <button type="button" class="btn btn-secondary">2</button>
    <button type="button" class="btn btn-secondary">3</button>
    <button type="button" class="btn btn-secondary">4</button>
    <button type="button" class="btn btn-secondary">5</button>
  </div>
</div>