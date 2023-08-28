package com.three.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.ReviewDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class MyRevSerOne implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int revId = Integer.parseInt(request.getParameter("revId"));
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		}
		ThreeDAO tdao = new ThreeDAO();
		ReviewDTO rdto = tdao.findRev(revId);
		
		request.setAttribute("rdto", rdto);
		
		SangpumDTO sdto = tdao.findSang(sangId);
		
		request.setAttribute("sdto", sdto);
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
	}

}
