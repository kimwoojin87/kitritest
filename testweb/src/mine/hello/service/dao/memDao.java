package mine.hello.service.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mine.hello.dto.MemDTO;

public class memDao {
	
	public List<MemDTO> selectALL(){
		List<MemDTO> list = new ArrayList<MemDTO>();
		
		//2)DB연결작업
		Connection con=null;
		//3)SQL구문 송신
		PreparedStatement pstmt = null;
		//4)결과수신
		ResultSet rs = null;
		//5)연결닫기
		
		return list;
	}
}
