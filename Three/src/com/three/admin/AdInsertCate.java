package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdInsertCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = 0;
		if(request.getParameter("sangId") != null) {
		sangId = Integer.parseInt(request.getParameter("sangId"));
		}
		String cateName = request.getParameter("cateName");
		int bigId = Integer.parseInt(request.getParameter("bigId"));
		
		ThreeDAO tdao = new ThreeDAO();
		
		boolean result1 = tdao.InsertCate(bigId,cateName);
		if (result1)	System.out.println("insertCateDone");
		else System.out.println("insertCateError");


		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		ArrayList<CateDTO> excarr =  tdao.searchCateNoExistsSang();
		
		
		/* request.setAttribute("result", result); */
		request.setAttribute("excarr", excarr);
		request.setAttribute("carray", carray);
		request.setAttribute("barray", barray);
		request.setAttribute("sangId", sangId);
	}

}
