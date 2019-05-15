package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService{

	private static MemberService memberService; //2
	
	static {
		memberService = new MemberServiceImpl();
	} //3
	
	private MemberServiceImpl(){} //순서 1
	
	public static MemberService getMemberService(){
		return memberService;
	} //4
	//객체를 단 1번만 만드는 개발방법 : 싱글톤

	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(id);
//		System.out.println(cnt);
		String result ="";
		result += "<idcount>";
		result += "<cnt>"+cnt+"</cnt>";
		result += "</idcount>";
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		//String result = "<?xml version=\"1.0\" endoding=\"utf-8\"?>\n";
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);
		result += "<ziplist>";
		for(ZipcodeDto zipdDto : list) {
			result += "<zip>"+"\n";
			result += "		<zipcode>"+zipdDto.getZipcode()+"</zipcode>"+"\n";
			result += "		<address><![CDATA["+zipdDto.getSido()+zipdDto.getGugun()+zipdDto.getUpmyun()+zipdDto.getDoro()+zipdDto.getBuildingNumber()+zipdDto.getSigugunBuildingName()+"]]>\n";
			result += "		</address>"+"\n";
			result += "</zip>"+"\n";
		}
		result += "</ziplist>";
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto mdddto) {
		return MemberDaoImpl.getMemberDao().registerMember(mdddto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);
		return MemberDaoImpl.getMemberDao().loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto mddto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		return 0;
	}

}
