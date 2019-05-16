package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;


@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //1)요청전달데이터얻기
	//2)model 호출
	//3)결과를 request.attribute로 추가
	//4)view로 이동
	ProductService productservice = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		productservice = new ProductService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter("form");
		List<Product> list = productservice.findAll();
		
		request.setAttribute("productlist", list);
		
		String path = "productlistresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}


}
