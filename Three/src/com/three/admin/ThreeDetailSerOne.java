package com.three.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.DetailDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class ThreeDetailSerOne implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int detId = Integer.parseInt(request.getParameter("detId"));
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		ThreeDAO tdao = new ThreeDAO();
		DetailDTO det = tdao.detailSearchOne(detId);
		SangpumDTO sdto = tdao.findSang(sangId);
		
		request.setAttribute("sdet", det);
		request.setAttribute("sdto", sdto);
		
	}

}
