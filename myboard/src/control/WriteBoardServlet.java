package control;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dao.RepBoardService;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;


@WebServlet("/writeboard")
public class WriteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepBoardService service= null;   
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new RepBoardService();
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");
		
		RepBoard repBoard = new RepBoard();
		
		repBoard.setBoard_subject(subject);
		repBoard.setBoard_writer(writer);
		repBoard.setBoard_password(password);
		repBoard.setBoard_contents(contents);
		
		
		try {
			service.write(repBoard);
			request.setAttribute("result", "글쓰기 성공");
			
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("result", "글쓰기 실패");
		}
		
		
		String path = "/writeboardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
