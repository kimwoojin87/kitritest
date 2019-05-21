package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.OrderInfo;
import com.kitri.service.OrderService;

/**
 * Servlet implementation class ViewOrderServlet
 */
@WebServlet("/vieworder")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService service;
    public ViewOrderServlet() {
    	service = new OrderService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginInfo");
		List<OrderInfo> orderList = service.findById(id);
		request.setAttribute("orderlist", orderList);
		String path = "/vieworderresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	

}
