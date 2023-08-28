package com.three.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.MemberDTO;
import com.three.controller.ThreeImpl;

public class ThreeJoin implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		String memPw1 = request.getParameter("pw1");
		String memPw2 = request.getParameter("pw2");
		String memPhone = request.getParameter("memPhone");
		String memEmail = request.getParameter("memEmail");
				
		
		

		ThreeDAO thdao = new ThreeDAO();
		System.out.println("여기는 ThreeJoin.java : " +memId);
		System.out.println("여기는 ThreeJoin.java : " +memName);
		System.out.println("여기는 ThreeJoin.java : " +memPw1);
		System.out.println("여기는 ThreeJoin.java : " +memPw2);
		System.out.println("여기는 ThreeJoin.java : " +memPhone);
		System.out.println("여기는 ThreeJoin.java : " +memEmail);
		
		thdao.joinInsert(memId, memPw1, memName, memPhone, memEmail );
		
		
	}

}
