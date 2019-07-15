package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gblist")
public class GuestBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<GuestBookDto> list = new ArrayList<GuestBookDto>();
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			
//			1.db에서 data 얻어와서 셀렉트하기 seq, name ,suvject, content, logtime
			StringBuffer sql = new StringBuffer();
			
			
			sql.append("select seq,name,subject,content,to_char(logtime,'yyyy-mm-dd HH24:mi:ss') as logtime from guestbook order by seq desc");
			pstm = con.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				GuestBookDto GBDTO = new GuestBookDto(rs.getInt("seq"), rs.getString("name"),
						rs.getString("subject"), rs.getClob("content"), rs.getString("logtime"));
				
				list.add(GBDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"ko\">\r\n" + 
				"<head>\r\n" + 
				"<title>글목록</title>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\">\r\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\"></script>\r\n" + 
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\"></script>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</script>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<div class=\"container\" align=\"center\">\r\n" + 
				"  <div class=\"col-lg-8\" align=\"center\">\r\n" + 
				"  <h2>글목록</h2>\r\n" + 
				"  <table class=\"table table-borderless\">\r\n" + 
				"  	<tr>\r\n" + 
				"  		<td align=\"right\"><button type=\"button\" class=\"btn btn-link\"><a href=\"/guestbookservlet/guestbook/write.html\">글쓰기</a></button></td>\r\n" + 
				"  	</tr>\r\n" + 
				"</table>\r\n" + 
				"  <table class=\"table table-active\">\r\n" + 
				"    <tbody>");
				for(int i=0;i<list.size();i++) {
				out.println("<tr>\r\n" + 
						"        <td>작성자 : '"+list.get(i).getName() +"'</td>\r\n" + 
						"        <td style=\"text-align: right;\">작성일 : "+list.get(i).getLogtime()+"</td>\r\n" + 
						"      </tr>\r\n" + 
						"      <tr>\r\n" + 
						"        <td colspan=\"2\"><strong>"+list.get(i).getSeq()+" .  "+list.get(i).getSubject() +"</strong></td>\r\n" + 
						"      </tr>\r\n" + 
						"      <tr>\r\n" + 
						"        <td colspan=\"2\"><textarea style=\"width:100%\" readOnly=\"true\">"+getClobConvertToStr(list.get(i).getContent()) +"</textarea></td>\r\n" + 
						"      </tr>" );
				}
				out.println("</tbody>\r\n" + 
						"  </table>\r\n" + 
						"  </div>\r\n" + 
						"</div>");
	}
	public String getClobConvertToStr(Clob content) {
	      int size = 0;
	      String str = "";
	      
	      try {
	         if(content != null) {
	            size = (int)content.length();
	            str = content.getSubString(1, size);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return str;
	   }

}
