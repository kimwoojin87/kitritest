package com.kitri.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.*;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class MemberDaoImpl implements MemberDao{
	
	private MemberDaoImpl() {}; //1
	
	private static MemberDao memberDao; //2
	
	static {
		memberDao = new MemberDaoImpl();
	} //3
	
	
	
	public static MemberDao getMemberDao() {
		return memberDao;
	} //4

	@Override
	public int idCheck(String id) {
		int cnt = 1;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(id) \n");
			sql.append("from member \n");
			sql.append("where id = ?");
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			cnt = 1;//익셉션이 났을떄 지정해두는 작업 할 것.
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return cnt;
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select 	case  \n");
			sql.append("			when length(new_post_code) = 4 then '0'||new_post_code \n");
			sql.append("			else new_post_code \n");
			sql.append("		end zipcode,  \n");
			sql.append("		sido_kor sido, gugun_kor gugun,  \n");
			sql.append("		nvl(upmyon_kor, ' ') upmyon, doro_kor doro,  \n");
			sql.append("		case when building_refer_number != '0' \n");
			sql.append("			then building_origin_number||'-'||building_refer_number  \n");
			sql.append("			else trim(to_char(building_origin_number, '99999')) \n");
			sql.append("		end building_number, sigugun_building_name \n");
			sql.append("from 	postcode \n");
			sql.append("where 	doro_kor like '%'||?||'%' \n");
			sql.append("or sigugun_building_name like '%'||?||'%' \n");
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, doro);
			pstm.setString(2, doro);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ZipcodeDto zipDto = new ZipcodeDto();
				zipDto.setZipcode(rs.getString("zipcode"));
				zipDto.setSido(rs.getString("sido"));
				zipDto.setGugun(rs.getString("gugun"));
				zipDto.setUpmyun(rs.getString("upmyon"));
				zipDto.setDoro(rs.getString("doro"));
				zipDto.setBuildingNumber(rs.getString("building_number"));
				zipDto.setSigugunBuildingName(rs.getString("sigugun_building_name"));
				
				list.add(zipDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return list;
	}

	@Override
	public int registerMember(MemberDetailDto mddDto) {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstm =null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into member(id,name,pass,emailid,emaildomain,joindate) \n");
			sql.append("	values(?,?,?,?,?,sysdate) \n");
			sql.append("	into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3) \n");
			sql.append("	values(?,?,?,?,?,?,?) \n");
			sql.append("select * from dual \n");
			pstm = con.prepareStatement(sql.toString());
			int idx = 0;
			pstm.setString(++idx, mddDto.getId());
			pstm.setString(++idx, mddDto.getName());
			pstm.setString(++idx, mddDto.getPass());
			pstm.setString(++idx, mddDto.getEmailid());
			pstm.setString(++idx, mddDto.getEmaildomain());
			pstm.setString(++idx, mddDto.getId());
			pstm.setString(++idx, mddDto.getZipcode());
			pstm.setString(++idx, mddDto.getAddress());
			pstm.setString(++idx, mddDto.getAddressDetail());
			pstm.setString(++idx, mddDto.getTel1());
			pstm.setString(++idx, mddDto.getTel2());
			pstm.setString(++idx, mddDto.getTel3());
			cnt = pstm.executeUpdate();
			
			System.out.println("cnt =="+cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
			pstm.close();
				}
				if (con != null) {
			con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		MemberDto memberDto = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select name, id, emailid, emaildomain, joindate \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, map.get("userid"));
			pstm.setString(2, map.get("userpwd"));
			rs = pstm.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setEmailid(rs.getString("emailid"));
				memberDto.setEmaildomain(rs.getString("emaildomain"));
				memberDto.setJoindate(rs.getString("joindate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstm, rs);
		}
		return memberDto;
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto mdDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		return 0;
	}

}
