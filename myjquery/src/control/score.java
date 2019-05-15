package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class score
 */
@WebServlet("/score")
public class score extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	int people;
	int totalScore;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청이 들어옴
		
		//요청안에서 데이터 겟
		String star = request.getParameter("star");
		
		//데이터를 이용해서 서비스로직 //
		//star ->  요청이 들어올때마다 참여자수 증가
		//총별점 -> 요청이 들어올때마다 totalstar += start
		//요청이 들어오면
		//1. 참여자수 1증가
		people++;
		//2. 총별점이 데이터만큼 증가
		totalScore += Integer.parseInt(star);
		String path = "/score.jsp";
		request.setAttribute("people", people);//
		request.setAttribute("totalScore", totalScore);//
		RequestDispatcher rd = request.getRequestDispatcher(path);//
		rd.forward(request, response);
		//응답
		//score.jsp
	}
}
