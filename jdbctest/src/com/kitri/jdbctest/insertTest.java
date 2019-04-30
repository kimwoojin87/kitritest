package com.kitri.jdbctest;

import java.sql.*;

public class insertTest {
	
	public insertTest() {
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
		insertTest it = new insertTest();
		String name ="귀에서피남";
		String id = "0찬";
		Connection con = null;
		Statement stmt = null;
		int cnt = 0;
		
		
		try {
//			2.
			con = it.makeConnection();
//			insert into jdbctest (no, name, id, joindate)
//			values (jdbctest_no_seq.nextval, '안효인', 'java2', sysdate);
			
//			3.
			String sql = "";
			sql += "insert into jdbctest (no, name, id, joindate) \n";
			sql += "values (jdbctest_no_seq.nextval, '" + name + "', '" + id + "', sysdate)";
//			System.out.println(sql);
			stmt = con.createStatement();
			
//			4.
			cnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if (stmt != null){
				stmt.close();
			}
			if (con !=null) {
				con.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(cnt !=0) {
			System.out.println("데이터 인서트 석쎽쓰!!!");
		}else {
			System.out.println("데이터 삽입 실패!!!!");
		}
//		
//		
	}

}
