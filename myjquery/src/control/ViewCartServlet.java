package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

import sun.print.resources.serviceui;

/**
 * Servlet implementation class ViewCartServlet
 */
@WebServlet("/viewcart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService service;
	
    public ViewCartServlet() {
    	service = new ProductService();
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("서블릿들어와짐");
//		1)세션얻기
		HttpSession session = request.getSession();
		//2)세션속성중 "cart"속성얻기
		//자료형은 Map<Product,Integer> c = session.getAttribute("cart");
		Map<Product, Integer> c = (Map<Product, Integer>)session.getAttribute("cart");
		
		Map<Product, Integer>rc = new HashMap<Product, Integer>();
		
		if(c!=null) {
			Set<Product>keys = c.keySet();//set = 집합체,set은 그냥 아무거나 정렬없이 넣어짐.
			for(Product p : keys) {
				String no = p.getProd_no();
				
				Product p1 = service.findByNo(no);
				int quantity = c.get(p);
				rc.put(p1, quantity);
			}
		}
		
		request.setAttribute("rcart", rc);
		String path = "viewcartresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	

}
