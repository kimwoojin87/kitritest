package com.kitri.util;

import java.sql.*;

public class DBClose {
	
	public static void close(Connection con,PreparedStatement pstm) {
		try {
			
			if(pstm !=null) {
				pstm.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con,PreparedStatement pstm,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if(pstm !=null) {
				pstm.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con,Statement stmt) {
		try {
			
			if(stmt !=null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if(stmt !=null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
