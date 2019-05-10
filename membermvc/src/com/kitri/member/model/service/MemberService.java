package com.kitri.member.model.service;


import com.kitri.member.model.*;

public interface MemberService {
	
	String idCheck(String id);//아이디가져와야함.int 0 아이디 사용가능. 1이면 사용불가능
	String zipSearch(String doro);//리턴타입 zipcodedto가 들어가있는 list
	int registerMember(MemberDetailDto mdddto); //mamberdetaildto 0이면 실패 1이면 성공
	MemberDto loginMember(String id, String pass); // memberdto
	
	MemberDetailDto getMember(String id);// memberdetaildto 
	int modifyMember(MemberDetailDto mddto);// 0이면 수정할거없음 0이아니면 수정해야함
	int deleteMember(String id);// 리턴타입 int
}
