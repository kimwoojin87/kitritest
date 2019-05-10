package com.kitri.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.*;

public interface MemberDao {
	
	int idCheck(String id);
	List<ZipcodeDto> zipSearch(String doro);
	int registerMember(MemberDetailDto mddDto); 
	MemberDto loginMember(Map<String, String> map); //마이바티스는 1개이상 인자값을 못받음.
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto mdDto);
	int deleteMember(String id);
	
}
