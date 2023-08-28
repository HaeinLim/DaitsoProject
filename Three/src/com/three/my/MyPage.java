package com.three.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class MyPage implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ThreeDAO tdao = new ThreeDAO();
		int cartCnt = 0;
		String memId = null;
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
