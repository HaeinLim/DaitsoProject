package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdSearchSangByName implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String searchName = "none"; //기본적인 검색어는 none 처리이다
		
		//검색어를 받아오는 메소드 searchName으로 받는다
		if (request.getParameter("searchName")!= null) {
			searchName = request.getParameter("searchName");
		}
		ThreeDAO tdao = new ThreeDAO();
		
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		ArrayList<SangpumDTO> sarray = tdao.searchSangByName(searchName);
		
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("sarray", sarray);
		request.setAttribute("searchName", searchName);
	}
}