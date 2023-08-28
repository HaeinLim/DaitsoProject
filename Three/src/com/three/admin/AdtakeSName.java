package com.three.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdtakeSName implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ThreeDAO tdao = new ThreeDAO();
		
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		SangpumDTO sdto = tdao.findSang(sangId);
		
		request.setAttribute("sdto", sdto);
		
	}

}
