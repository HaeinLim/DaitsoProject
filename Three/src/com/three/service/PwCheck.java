package com.three.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.MemberDTO;
import com.three.controller.ThreeImpl;

public class PwCheck implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String memName  = null;
		String memPhone = null;
		String memEmail = null;
		
		System.out.println("여기는 PwCheck.java:"+id);
		System.out.println(pw);
		
		ThreeDAO thdao = new ThreeDAO();
		MemberDTO memdto = new MemberDTO();
		
		memdto = thdao.loginCheck(id, pw);
		
		
		
		
		if(memdto != null) {
			id = memdto.getMemId();
			pw = memdto.getMemPw();
			memName = memdto.getMemName();
			memPhone = memdto.getMemPhone();
			memEmail = memdto.getMemEmail();
			
		} else {
			id = "";
			pw = "";
		}
		
		
		
		request.setAttribute("id", id);
		request.setAttribute("pw", pw);
		request.setAttribute("memName", memName);
		request.setAttribute("memPhone", memPhone);
		request.setAttribute("memEmail", memEmail);
		
		System.out.println("여기는 PwCheck.java");
		System.out.println(id);
		System.out.println(pw);
		System.out.println(memName);
		System.out.println(memPhone);
		System.out.println(memEmail);
		
	}

}