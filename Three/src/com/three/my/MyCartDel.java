package com.three.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class MyCartDel implements ThreeImpl {

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
		String [] sarr = request.getParameterValues("sangId");
		
		ThreeDAO tdao = new ThreeDAO();
		for(int i =0; i<sarr.length; i++) {
			int sangId = Integer.parseInt(sarr[i]);
			boolean result = tdao.cartDel(memId, sangId);
			if(result) System.out.println("cartDelDone");
			else System.out.println("cartDelError");
		}
	}
}
