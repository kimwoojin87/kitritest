package mine.hello.service.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mine.hello.dto.MemDTO;
import mine.hello.util.DBClose;
import mine.hello.util.DBConect;
import mine.hello.util.SiteConstance;

public class memDao {
	
	public List<MemDTO> selectALL() throws SQLException{
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
//			StringBuffer selectAllsql = new StringBuffer();
			
//			selectAllsql.append("select employee_id,first_name,last_name,email,phone_number,salary");
//			selectAllsql.append("from employees");
//			selectAllsql.append("order by employee_id asc");
			
//			pstmt = con.prepareStatement(selectAllsql.toString());
			
			String selectALLsql = "select employee_id,first_name,last_name,email,phone_number,salary\r\n" + 
					"from employees\r\n" + 
					"order by employee_id asc";
			pstmt = con.prepareStatement(selectALLsql);
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
