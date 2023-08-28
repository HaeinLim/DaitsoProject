package com.three.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class AdFindCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String cateName = null;
		if(request.getParameter("cateName") != null) {
			cateName = request.getParameter("cateName");
		}
		
		ThreeDAO tdao = new ThreeDAO();
		boolean result = tdao.checkCateName(cateName);
		if(result)	request.setAttribute("check", 1);
		else request.setAttribute("check", 0);
		
	}

}
