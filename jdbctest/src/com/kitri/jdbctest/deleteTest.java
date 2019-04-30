package com.kitri.jdbctest;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class deleteTest {
	
	public deleteTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("디비 연결완료!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() throws SQLException, FileNotFoundException, IOException{
		Connection con = null;
		Properties prop = new Properties();
		prop.load(new FileReader(new File("src\\com\\kitri\\jdbctest\\user.properties")));
		String url = prop.getProperty("url");
		
		con = DriverManager.getConnection(url, prop);
		System.out.println("db 연결완료");
		return con;
	}
	
	public static void main(String[] args) {
		deleteTest dt = new deleteTest();
		
		Connection con = null;
		Statement stmt = null;
		int cnt = 0;
		int number =67;
		
		try {
			con = dt.makeConnection();
			String sql = "delete from jdbctest where no = '"+ number +"'";
			stmt = con.createStatement();
			cnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("파일 불러오기 실패~!~!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(cnt != 0 ) {
			System.out.println("삭제성공");
		}else {
			System.out.println("삭제실패");
		}
		
	}
}
