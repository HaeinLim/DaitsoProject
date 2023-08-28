package com.three.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdReSang implements ThreeImpl{
	
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = Integer.parseInt(request.getParameter("sangId"));
			
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		SangpumDTO sdto = tdao.findSang(sangId);
		String cateName  = tdao.findCateName(sdto.getCateId());
		
		// 카테고리조회
		ArrayList<CateDTO> carray = tdao.getAllCate();
		
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("cateName", cateName);
		request.setAttribute("sdto", sdto);
	}
}
