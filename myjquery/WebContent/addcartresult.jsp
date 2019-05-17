<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	var arr = $("div.addcartresult>button")
	$(arr[0]).click(function(){
		alert("상품목록가기 클릭했습니다.");
		location.href="productlist.jsp"
		return false;
	});
	$(arr[1]).click(function(){
		alert("장바구니보기 클릭완료");
		location.href="viewcart";
		return false;
	});
});
</script>
<div class="addcartresult" style="position:absolute;top: 200px;left: 300px; width:250px; border:1px solid;">
장바구니에 넣었습니다.
<button>상품목록으로가기</button>
<button>장바구니보기</button>
</div>
