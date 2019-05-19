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
import mine.hello.dto.MemPageDTO;
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
			int currentpage = Integer.parseInt(request.getParameter("currentpage")); //화면에서 누른 페이지수 
			int cntperpage = 5;				//페이지당 표시할 게시물건수
			
			int cnt = memdao.cntPage();  //전체 페이지수
			List<MemDTO> list = memdao.selectALL(currentpage,cntperpage);	
			//화면에서 누른 페이지수, 페이지당 표시할 게시물건수
			// 파라미터로 넘기는 이유 : 관리포인트 줄이기 위해서.
			
			//
			MemPageDTO page = new MemPageDTO();
			page.setCurrentpage(currentpage);
			
			int totalPage = (int)(Math.ceil(cnt / cntperpage));
			
			page.setTotalPage(totalPage);
			
			request.setAttribute("hmemberlist", list);
			request.setAttribute("page", page);
			String path = "list/alllistresult.jsp";
			
			RequestDispatcher rs = request.getRequestDispatcher(path);
			rs.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
