package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {
	
	public static void redirect(HttpServletRequest request,HttpServletResponse response,String path) throws IOException {
		response.sendRedirect(request.getContextPath()+path);//location : url 
	}
	
	public static void forward(HttpServletRequest request,HttpServletResponse response,String path) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);//path : 경로 내 프로젝트 안에서만 이동가능
		dispatcher.forward(request, response);
	}
}
