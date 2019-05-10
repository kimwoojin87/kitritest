package com.kitri.member.controller;

import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberServiceImpl;

public class MemberController {
	
	public static MemberController memberController;
	
	static {
		memberController = new MemberController();
		
	}
	
	private MemberController() {}
	
	public static MemberController getMemberController() {
		return memberController;
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String path ="/index.jsp";
		
		MemberDetailDto memberDetailDto = new MemberDetailDto();
		memberDetailDto.setName(request.getParameter("name"));
		memberDetailDto.setId(request.getParameter("id"));
		memberDetailDto.setPass(request.getParameter("pass"));
		memberDetailDto.setEmailid(request.getParameter("emailid"));
		memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));
		memberDetailDto.setTel1(request.getParameter("tel1"));
		memberDetailDto.setTel2(request.getParameter("tel2"));
		memberDetailDto.setTel3(request.getParameter("tel3"));
		memberDetailDto.setZipcode(request.getParameter("zipcode"));
		memberDetailDto.setAddress(request.getParameter("address"));
		memberDetailDto.setAddressDetail(request.getParameter("address_detail"));
		
		int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetailDto);
		if(cnt !=0) {
			request.setAttribute("userInfo", memberDetailDto);
			path = "/user/member/registerok.jsp";
		}else {
			path = "/user/member/registerfail.jsp";
		}
		
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDto memberDto = MemberServiceImpl.getMemberService().loginMember(id, pass);
		if(memberDto != null) {
			//////////////////////////cookie의 정보 ///////////////////////////
			String idsv = request.getParameter("idsave");
			if("idsave".equals(idsv)) {
				Cookie cookie = new Cookie("kid_inf", id);
				cookie.setDomain("localhost");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60*60*24*365*50); //제일 많이 하는 실수 : 쿠키는 클라이언트에 저장되어야함.
				response.addCookie(cookie);
			}else {
				Cookie cookie[]=request.getCookies();
				if(cookie != null){
					for(Cookie c : cookie){
						if("kid_inf".equals(c.getName())){
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
							c.setMaxAge(0); //쿠키를 지우는 메소드는 없으므로 만료시키면된다
							response.addCookie(c);
							break;
						}
					}
				}
			}
			//////////////////////////cookie의 정보 ///////////////////////////
			//////////////////////////session의 정보 ///////////////////////////
			HttpSession session = request.getSession();//세션은 바구니. 용도별로 나뉘어져있다.
			session.setAttribute("userInfo", memberDto);
			//////////////////////////session의 정보 ///////////////////////////
			path = "/user/login/loginok.jsp";
		}else {
			path = "/user/login/loginfail.jsp";
		}
		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", null);//첫번쨰방법. 정상적인 방법은 아닌 꼼수
//		session.removeAttribute("userInfo");//일반적인 방법. 하나씩 지울때 사용한다.
		session.invalidate();//세션안에 있는 것을 전부다 지워라
		return "/user/login/login.jsp";
	}
}