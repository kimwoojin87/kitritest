package com.kitri.dao;

import java.sql.*;
import java.util.List;

import com.kitri.dto.customer.Customer;
import com.kitri.exception.NotFoundException;

public class CustomerDAO {
	public Customer selectById(String id) throws com.kitri.exception.NotFoundException{
		//1)JDBC드라이버로드
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@192.168.14.52:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			//3)SQL구문 DB서버로 송신 executeQuery()
			//4)결과수신 : rs
			String selectByIdSQL = "select * from customer where id=?";
			pstm = con.prepareStatement(selectByIdSQL);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				Customer c = new Customer();
				c.setId(id);
				c.setPass(rs.getString("pass"));
				c.setName(rs.getString("name"));
				return c;
			}else {
				throw new NotFoundException("아이디에 해당하는 고객이 없습니다.");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
		
	}
	
	public List<Customer> selectByName(String name){
		return null;
	}
	
	public List<Customer> selectAll(){
		return null;
	}
	
	public void insert(Customer c) {
		
	}
}
