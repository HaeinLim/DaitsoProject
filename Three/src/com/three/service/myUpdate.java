package com.three.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.MemberDTO;
import com.three.controller.ThreeImpl;

public class myUpdate implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memId = request.getParameter("id");
		String memName = request.getParameter("memName");
		String memPw1 = request.getParameter("pw1");
		String memPw2 = request.getParameter("pw2");
		String memPhone = request.getParameter("memPhone");
		String memEmail = request.getParameter("memEmail");
		
		System.out.println("여기는 update:"+memId);
		System.out.println("여기는 update:"+memPw1);
		System.out.println("여기는 update:"+memName);
		System.out.println("여기는 update:"+memPhone);
		System.out.println("여기는 update:"+memEmail);
		
		
		
		
		ThreeDAO thdao = new ThreeDAO();
		thdao.myUpdate(memId, memPw1, memPhone, memEmail);

		
		System.out.println("여기는 myUpdate.java: " +memId + memName + memPw1 + memPhone + memEmail);
		ThreeDAO tdao = new ThreeDAO();
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
			cartCnt = tdao.countCart(memId);
		} 
	    String nick = memId.substring(0,3)+"***";
	    
	    request.setAttribute("cartCnt", cartCnt);
		request.setAttribute("nick", nick);
		
		
	}

}
