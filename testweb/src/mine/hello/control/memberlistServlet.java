package mine.hello.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mine.hello.dto.MemDTO;
import mine.hello.service.dao.memDao;

/**
 * Servlet implementation class memberlistServlet
 */
@WebServlet("/memberlist")
public class memberlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	memDao memdao = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		memdao = new memDao();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기로 들어는 왔나?");
		try {
			List<MemDTO> list = memdao.selectALL();
			
			request.setAttribute("hmemberlist", list);
			String path = "list/alllistresult.jsp";
			
			RequestDispatcher rs = request.getRequestDispatcher(path);
			rs.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
