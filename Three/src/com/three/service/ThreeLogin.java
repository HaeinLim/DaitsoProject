package com.three.service;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.MemberDTO;
import com.three.controller.ThreeImpl;

public class ThreeLogin implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String getid = null;
		String getpw =  null;
		if(request.getParameter("id")!= null) {
			getid = request.getParameter("id");
		}
		if(request.getParameter("pw")!=null) {
			getpw = request.getParameter("pw");
		};
//

		String memId = null;
		String memPw = null;
		String memName = null;
		HttpSession session = null;
		String loginCh = null;
		ThreeDAO thdao = new ThreeDAO();
		MemberDTO memdto = thdao.loginCheck(getid, getpw);
		if(memdto != null) {
			memId = memdto.getMemId();
			memPw = memdto.getMemPw();
			memName = memdto.getMemName();
			session = request.getSession(true);
			session.setAttribute("sid", memId);
			session.setAttribute("spw", memPw);	
			System.out.println(session.getAttribute("sid"));
		}
		else {
			loginCh="nologin";
			request.setAttribute("loginCh", loginCh);
			
		}
		System.out.println(loginCh);
	}

	

}
