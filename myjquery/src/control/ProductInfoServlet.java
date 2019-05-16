package control;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

import sun.rmi.server.Dispatcher;


@WebServlet("/productinfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ProductService productService = null;
    
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		productService = new ProductService();
	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("Prod_no");
		//selectByNo(Prod_no)
		Product product = productService.findByNo(prodNo);
		
		request.setAttribute("productinfo", product);
		String path = "productinforesult.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

	

}
