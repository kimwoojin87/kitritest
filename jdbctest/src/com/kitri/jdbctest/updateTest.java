package com.kitri.jdbctest;

import java.sql.*;

//이 사람의 아이디를 현재 시간으로 수정하는 걸 만들어라.
public class updateTest {
	public updateTest(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection makeConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		System.out.println("db 연결 성공!!");
		return con;
	}
	
	public static void main(String[] args) {
		
		
		updateTest ut = new updateTest();
		
		Connection con = null;
		Statement stmt = null;
		int cnt =0;
		int num = 18;
		String id = "Balance";
		
		try {
			con = ut.makeConnection();
			
			String sql = "update jdbctest set id='" + id + "' where no='" + num + "'";
			
			stmt = con.createStatement();
			cnt = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			if(stmt != null) {
				stmt.close();
				}
			if(con !=null) {
				con.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(cnt !=0) {
			System.out.println("변경완료");
		}else {
			System.out.println("변경실패!!");
		}
	}
}
