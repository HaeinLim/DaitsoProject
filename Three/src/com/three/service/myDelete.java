package com.three.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;



public class myDelete implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		ThreeDAO thdao = new ThreeDAO();
		
		try {
		thdao.myDelete(id);
		}catch (Exception e) {
			// TODO: handle exception
		}
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
	
	}

}
