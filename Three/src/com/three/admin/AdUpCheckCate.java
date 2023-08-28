package com.three.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdUpCheckCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 해당파일은 카테 중복 이름이 존재하는지에 대한 여부를 작성한다
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThreeDAO tdao = new ThreeDAO();
		
		int bigId = 0;
		if(request.getParameter("bigId") != null) {
			bigId = Integer.parseInt(request.getParameter("bigId"));
		}
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String cateName = request.getParameter("cateName");	
		CateDTO cdto = null;
		
		cdto = tdao.findCateByName(cateName);
		//이름 중복사항 존재
		if(cdto!=null) {
			//같은 big내 카테고리 이름 중복
			if(cdto.getBigId() == bigId && cdto.getCateId() != cateId)
				request.setAttribute("check", 0);
			//다른 big내 카테고리 이름 중복
			else if(cdto.getBigId() != bigId && cdto.getCateId() != cateId)
			request.setAttribute("check", 0);
			else request.setAttribute("check", 1);
		}
		// 중복 사항 없음
		else request.setAttribute("check", 1);
	
	}
}
