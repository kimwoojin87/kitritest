package com.kitri.jdbctest;

public class DriverLoading {

	public DriverLoading() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!!!!!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DriverLoading();
	}
	
}
