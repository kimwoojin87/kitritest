package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.*;
import com.kitri.exception.AddException;
import com.kitri.service.OrderService;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private OrderService service;
    
    public AddOrderServlet() {
    	service = new OrderService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//장바구니정보가 주문테이블에 저장
		//1)주문자아이디정보가 필요함. 세션의 로그인정보에서 가져옴
		//2)세션에 저장되어있는 cart의 정보값을 가져오면 장바구니의 정보
		
		OrderInfo info = new OrderInfo();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginInfo");
		Customer c = new Customer();
		c.setId(id);
		info.setOrder_id(c); //주문자ID설정
			//장바구니 상품번호,수량->OrderLine에 설정
		Map<Product, Integer>cart = (Map<Product, Integer>)session.getAttribute("cart");
		List<OrderLine> lines = new ArrayList<OrderLine>();
		for(Product p:cart.keySet()) {
			//String no = p.getProd_no();//<--에러
			int quantity = (Integer)cart.get(p);//<--에러
			OrderLine line = new OrderLine();
			//상품번호,수량 -> orderLine에 설정
			line.setProduct(p);
			line.setOrder_quantity(quantity);
			lines.add(line);
		}
		info.setLines(lines);
		
		
		String result ="";
		try {
			service.addOrder(info);//주문이 성공되었을떄
			session.removeAttribute("cart");//세션정보를 지운다.
			result = "1";
		} catch (AddException e) {
			e.printStackTrace();
			result ="-1";
		}
		String path = "/addorderresult.jsp";
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
