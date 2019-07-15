package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.exception.NotFoundException;
import com.kitri.service.ProductService;

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
		String userAgent = request.getHeader("User-Agent");
		
		HttpSession session = request.getSession();
		Map<Product, Integer> c = (Map)session.getAttribute("cart");
		Map<Product, Integer> rc = new HashMap<>();
		if( c != null ){
			Set<Product>keys =  c.keySet();
			for(Product p: keys){
				String no = p.getProd_no();
				try{
					Product p1 = service.findByNo(no);
					int quantity = c.get(p);          
					rc.put(p1, quantity);
				}catch(NotFoundException e){ }
			}
			request.setAttribute("rcart", rc);
			String path = "/viewcartresult.jsp";
			if(userAgent.contains("Dalvik")) {//안드로이드 앱에서 요청
				path = "/viewcartresultjson.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}

	}
}
