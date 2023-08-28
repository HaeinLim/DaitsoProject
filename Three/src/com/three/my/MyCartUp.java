package com.three.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class MyCartUp implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		} 
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		int sangCount = Integer.parseInt(request.getParameter("sangCnt"));
		
		ThreeDAO tdao = new ThreeDAO();
		boolean result = tdao.cartUpdate(memId, sangId, sangCount);
		if(result) System.out.println("cartExinsertdone");
		else System.out.println("cartExInsertError");
	}
}
