package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.PageBean;
import com.kitri.dto.RepBoard;
import com.kitri.service.RepBoardService;

@WebServlet("/boardlist")
public class RepBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepBoardService service;
	public RepBoardListServlet() {
		service = new RepBoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp = request.getParameter("currentPage");
		int currentPage = 1;//보여줄 현재페이지
		if(cp != null) {
			currentPage = Integer.parseInt(cp);
		}
		//----------------------------------------		
		//int cntPerPage = 10; //페이지별 보여줄 목록수
		int cntPerPage = 2;
		int totalCnt = service.getTotalCnt(); //총게시글 수
		int cntPerPageGroup=3; //페이지그룹에 보여줄 페이지수
		String url="boardlist";
		PageBean pb = 
				new PageBean(currentPage, 
						     cntPerPage, 
						     cntPerPageGroup,
						     totalCnt,
						     url
						     );
		List<RepBoard> list = 
				service.findByRows(pb.getStartRow(), 
						           pb.getEndRow());
		pb.setList(list);
		request.setAttribute("pb", pb);		
		System.out.println(pb);
		
		String path = "/boardlistresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}
}