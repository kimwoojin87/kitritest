package mine.hello.service.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mine.hello.dto.MemDTO;
import mine.hello.util.DBClose;
import mine.hello.util.DBConect;
import mine.hello.util.SiteConstance;

public class memDao {
	
	public int cntPage() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int allCnt = 0; //전체 게시물수
		
		
		try {
			Class.forName(SiteConstance.DB_DRIVER);
			con = DBConect.makeCon();
			String cntPage ="select count(*) cnt from employees";
			//현패-1*페이지에나타낼건수+1 and 현재*페이지에나타낼건수
			pstmt = con.prepareStatement(cntPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				allCnt = rs.getInt("cnt");
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return allCnt;
	}
	
	public List<MemDTO> selectALL(int currentpage,int cntperpage) throws SQLException{
		List<MemDTO> list = new ArrayList<MemDTO>();
		
		//2)DB연결작업
		Connection con=null;
		//3)SQL구문 송신
		PreparedStatement pstmt = null;
		//4)결과수신
		ResultSet rs = null;
		//5)연결닫기
		
		
		
		try {
			Class.forName(SiteConstance.DB_DRIVER);
			con = DBConect.makeCon();
			String selectALLsql = "select employee_id,first_name,last_name,email,phone_number,salary\r\n" + 
					"from(\r\n" + 
					"    select rownum as rn, employee_id,first_name,last_name,email,phone_number,salary\r\n" + 
					"    from employees\r\n" + 
					")\r\n" + 
					"where rn between (?-1) * ?+1 and (? * ?)";
			//현패-1*페이지에나타낼건수+1 and 현재*페이지에나타낼건수
			pstmt = con.prepareStatement(selectALLsql);
			pstmt.setInt(1, currentpage);
			pstmt.setInt(2, cntperpage);
			pstmt.setInt(3, currentpage);
			pstmt.setInt(4, cntperpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				int salary = rs.getInt("salary");
				
				MemDTO memDTO = new MemDTO(employee_id, first_name, last_name, email, phone_number, salary);
				
				list.add(memDTO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
}
