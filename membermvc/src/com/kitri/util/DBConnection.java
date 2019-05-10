package com.kitri.util;

import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName(SiteConstance.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() throws SQLException {
		return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASSWORD);
	}
}
// A a1 = new A();
// A a2 = new A();
// A a3 = new A();
//static은 A라는 클래스가 읽혀지는 시점에서 생성됨.
// A a1,a2,a3에 모두 생성할 필요없이 A시점에 생성되므로  static을 씀