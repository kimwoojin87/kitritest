package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    //컨트롤러는 클라이언트의 요구를 분석하여 원하는 방향으로 연결시켜줘야함.
	//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String act = request.getParameter("act");
			
			String path = "/index.jsp";//멤버컨트롤러에서 가져오는 것
//			if(act != null)이라는 선행조건이 있어야함.
//			if(act.equals("mvjoin")) {
			if("mvjoin".equals(act)) {
				MoveUrl.redirect(request, response,"/user/member/member.jsp");
			}else if("mvlogin".equals(act)) {
				MoveUrl.redirect(request, response,"/user/login/login.jsp");
			}else if("idcheck".equals(act)) {
				String sid = request.getParameter("sid");
				String resultXML = MemberServiceImpl.getMemberService().idCheck(sid);
				
				response.setContentType("text/xml;charset=utf-8");
				//text/plain , text/html , text/xml 등으로 바꿔줌
				PrintWriter out = response.getWriter();
				out.print(resultXML);
			}else if("zipsearch".equals(act)) {
				String doro = request.getParameter("doro");
//				System.out.println("검색 도로명 :"+doro);
				String resultXML = MemberServiceImpl.getMemberService().zipSearch(doro);
//				System.out.println(resultXML);
				response.setContentType("text/xml;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(resultXML);
			}else if("register".equals(act)) {
				path = MemberController.getMemberController().register(request, response);
//				MoveUrl.redirect(request, response, path); redirect는 리퀘스트 리스폰스를 다 버리고감.
				MoveUrl.forward(request, response, path);
			}else if("login".equals(act)) {
				path = MemberController.getMemberController().login(request, response);
				MoveUrl.forward(request, response, path);
				//forward는 request,response를 담고있음 session과는 관계없음
			}else if("logout".equals(act)) {
				path = MemberController.getMemberController().logout(request, response);
				MoveUrl.redirect(request, response, path);
			}else if("".equals(act)) {
				
			}else if("".equals(act)) {
				
			}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}

