package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = 0;
		if(request.getParameter("sangId") != null) {
		sangId = Integer.parseInt(request.getParameter("sangId"));
		}
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<CateDTO> excarr =  tdao.searchCateNoExistsSang();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		
		request.setAttribute("carray", carray);
		request.setAttribute("excarr", excarr);
		request.setAttribute("barray", barray);
		request.setAttribute("sangId", sangId);
	}

}
