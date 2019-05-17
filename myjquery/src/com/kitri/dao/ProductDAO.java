package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategori;

public class ProductDAO {
	
	public List<Product> selectAll(){
		List<Product> list = new ArrayList<>();
		//2)DB연결작업
		Connection con=null;
		//3)SQL구문 송신
		PreparedStatement pstmt = null;
		//4)결과수신
		ResultSet rs = null;
		//5)연결닫기
		try {
			//1)JDBC드라이버로드 //예외ㅓ리 트라이캐치
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="kitri";
			String password="kitri";
			con= DriverManager.getConnection(url, user, password);
			
			String selectAllsql = "select pc.cate_no,pc.cate_name,\r\n" + 
					"p.prod_no,p.prod_name,p.prod_price,p.prod_detail\r\n" + 
					"from product p join product_category pc \r\n" + 
					"on p.prod_cate=pc.cate_no\r\n" + 
					"order by cate_no,prod_name";
			
			pstmt = con.prepareStatement(selectAllsql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategori pc = new ProductCategori(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail);
				
				p.setProductcategori(pc);
				list.add(p);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//런타임다이나믹로드를 위해서 class.forname()설정
			catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public Product SelectByNo(String prodNo) {
		Product product = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="kitri";
		String password="kitri";
		try {
			con = DriverManager.getConnection(url, user, password);
			String SelectByNosql = "select pc.cate_no,pc.cate_name,\r\n" + 
						"p.prod_no,p.prod_name,p.prod_price,p.prod_detail\r\n" + 
						"from product p join product_category pc \r\n" + 
						"on p.prod_cate=pc.cate_no\r\n" + 
						"where p.prod_no=? \r\n";
			pstmt = con.prepareStatement(SelectByNosql);
			pstmt.setString(1, prodNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategori pc = new ProductCategori(cate_no, cate_name);
				product = new Product(prod_no, prod_name, prod_price, prod_detail);
				
				product.setProductcategori(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
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
		return product;
	}
}
