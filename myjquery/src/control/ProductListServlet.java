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
		//request.getParameter("form"); 요청받을 데이터가 없음.
		List<Product> list = productservice.findAll(); //서비스로직호출
		request.setAttribute("productlist", list); // servletcontext:웹컨텐트 전체에서  httpsession:클라이언트별로 갖고있을 정보 request: 요청페이지에서만 담을 정보
		String path = "productlistresult.jsp"; // 서버사이드에서 앞에 /가 붙으면 웹컨텍스트 안쪽에서의 경로.
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
}