package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdDeleteCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = 0;
		int cateId = 0;
		if(request.getParameter("sangId") != null) {
			sangId = Integer.parseInt(request.getParameter("sangId"));
		}
		if(request.getParameter("cateId") != null) {
			cateId = Integer.parseInt(request.getParameter("cateId"));
		}
		
		ThreeDAO tdao = new ThreeDAO();
		if(cateId != 0)	{
			boolean result = tdao.deleteCate(cateId);
			if (result) {
				System.out.println("deleteCateDone");
			}
			else System.out.println("deleteCateError");
		}
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		ArrayList<CateDTO> excarr =  tdao.searchCateNoExistsSang();
		
		request.setAttribute("excarr", excarr);
		request.setAttribute("carray", carray);
		request.setAttribute("barray", barray);
		request.setAttribute("sangId", sangId);
	}

}

