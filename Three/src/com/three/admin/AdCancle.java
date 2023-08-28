package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CancleDTO;
import com.three.controller.ThreeImpl;

public class AdCancle implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<CancleDTO> carray = tdao.searchCancle();
		
		request.setAttribute("searchName", "none");
		request.setAttribute("carray", carray);
	}
}
