package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdUpdateCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThreeDAO tdao = new ThreeDAO();
		
		
		int bigId = 0; //들어오지 않을 수도 있다
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String cateName = request.getParameter("cateName");
		if(request.getParameter("bigId") != null) {
			bigId = Integer.parseInt(request.getParameter("bigId"));
		}
		
		// 사용자는 카테고리 이름 변경만, 위치만변경, 위치와 이름을 모두 변경할 수 있다
		// 이때 이름만 변경을 원할 시 bigId값은 들어올 수도 들어오지 않을 수 도 있다
		if(bigId==0)	{
			bigId = tdao.findCate(cateId).getBigId();
			boolean result = tdao.updateCate(cateId, cateName, bigId);
			if(result) System.out.println("이름만 변경완료");
			else System.out.println("이름만 변경 실패");
		}
		else {
			boolean result = tdao.updateCate(cateId, cateName, bigId);
			if(result) System.out.println("카테고리 수정완료");
			else System.out.println("카테고리 수정 실패");
		}	
		
		// 화면유지를 위한 값
		int sangId = 0;
		if(request.getParameter("sangId") != null) {
			sangId = Integer.parseInt(request.getParameter("sangId"));
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
