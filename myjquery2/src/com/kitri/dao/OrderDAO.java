package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class OrderDAO {
	public void insert(OrderInfo info) throws AddException{// orderinfo - has a lines
		Connection con = null;
		try {
			// 1)드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);//setautocommit 값을 false로 주면 기본오토커밋을 해제시키는 작업.
			
			
			insertInfo(con, info);//주문기본추가하기
			List<OrderLine> lines = info.getLines();
			insertLine(con, lines);
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AddException("주문추가 오류"+e.getMessage());
		} finally {
			// DB닫기
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // finally
	}// end insert()

	public void insertInfo(Connection con, OrderInfo info) throws SQLException{// orderinfo 가 insert에 올라감.
		PreparedStatement pstmt = null;
		String insertInfoSQL = "insert into order_info(order_no,order_id)\r\n" + "values(order_info_no_seq.nextval, ?)";
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getOrder_id().getId());
			pstmt.executeUpdate();
		//} catch (SQLException e) { 예외를 떠넘기자.
		//	e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void insertLine(Connection con, List<OrderLine> lines) { // ????
		PreparedStatement pstmt = null;
		String insertLineSQL = "insert into order_line(order_no,order_prod_no,order_quantity)\r\n"
				+ "values(ORDER_INFO_NO_SEQ.currval,?,?)";
		try {
			pstmt = con.prepareStatement(insertLineSQL);
			for (OrderLine line : lines) {
				String prod_no = line.getProduct().getProd_no();
				pstmt.setString(1, prod_no);

				int quantity = line.getOrder_quantity();
				pstmt.setInt(2, quantity);
				//pstmt.executeUpdate();//일괄처리, 배치처리해야함. 현재는 너무 효율이 떨어지는 상태.
				pstmt.addBatch();//일괄처리작업에 추가함.
			}
			pstmt.executeBatch();//반복문이 끝나고 일괄처리
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<OrderInfo> selectById(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<OrderInfo>list = new ArrayList<OrderInfo>();
		try {
			// 1)드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			//3)SQL 송신
			String selectByIdSQL = "select info.order_no, order_dt,\r\n" + 
					"prod_no,prod_name,prod_price,\r\n" + 
					"order_quantity\r\n" + 
					"from order_info info join order_line line\r\n" + 
					"on info.ordeR_no = line.order_no\r\n" + 
					"join product p on p.prod_no = line.order_prod_no\r\n" + 
					"where order_id = ?\r\n" + 
					"order by order_no desc, p.prod_no";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			OrderInfo info = null;
			OrderLine line = null;
			List<OrderLine> lines= null;
			int old_order_no = -1;
			while(rs.next()) {
				int order_no = rs.getInt("order_no");
				if(old_order_no != order_no) {
					info = new OrderInfo();
					list.add(info);
					info.setOrder_no(order_no);//헤딩결과에 별칭이 빠진걸로 나오니 헤딩명 그대로 찾아와야함.
					info.setOrder_dt(rs.getDate("order_dt"));
					lines = new ArrayList<OrderLine>();
					info.setLines(lines);
					old_order_no = order_no;
				}
				line = new OrderLine();
				String prod_no = rs.getString("prod_no");
				Product p = new Product();
				p.setProd_no(prod_no);
				//:
				line.setProduct(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				
				lines.add(line);//???
			}
		}catch(Exception e) {
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
			return list;
	}
}
