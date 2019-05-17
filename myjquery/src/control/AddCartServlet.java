package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;



@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prod_no = request.getParameter("no");//쿼리스트링 같은 문자열뒤에있는 name=값을 가져옴
		String quantity = request.getParameter("quantity");
		HttpSession session = request.getSession();//세션은 서버에서 실행
		//"cart" 장바구니에 상품이 있을수도있으니까 확인해주는 작업 getAttribute
		Map<Product, Integer> c = (Map<Product, Integer>)session.getAttribute("cart");
		//map 어떤 정보를 담는 것. 자바단에서 어떤 정보를 얻는게 변수를 선언하거나,클래스를 호출
		//map 두개를 같이 이차원으로 저장함
		//map이라는 객체로 넘어오면 getparameter.
		
		if(c==null) {// 얻어온 데이터가 없을시
			c = new HashMap<Product, Integer>();
			
			session.setAttribute("cart", c);
		}//if문 끝
		Product product = new Product();
		product.setProd_no(prod_no);
		//장바구니에 해당상품이 존재하는가 확인
		int intQuantity = Integer.parseInt(quantity);
		
		Integer inte = c.get(product);
		if(inte != null) {//존재하면 수량을 합산한다.
			intQuantity += inte.intValue();
//			int oldQuantity = inte.intValue();
		}
		
		c.put(product, intQuantity);//장바구니에 상품,수량 추가
		
		request.setAttribute("product", product);
		request.setAttribute("intquantity", intQuantity);
		String path = "addcartresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
//		System.out.println("========장바구니 내용=====");
		Set<Product> keys = c.keySet();//keyset
		for(Product key: keys) {
			int q = c.get(key);
//			System.out.println("상품번호:"+key.getProd_no()+", 수량"+q);
		}
		
	}//doget

}//addcartservlet
