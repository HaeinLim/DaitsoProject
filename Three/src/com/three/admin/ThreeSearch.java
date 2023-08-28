package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.MemberDTO;
import com.three.controller.ThreeImpl;

public class ThreeSearch implements ThreeImpl {
	
	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		// 회원 조회
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<MemberDTO> alist1 = tdao.search(request.getParameter("keyWord"));
										// 검색창에 입력한 값을 가져와 dao에 보내야하기 때문에 request.getParameter로 값 가져오기
		
		request.setAttribute("alist1", alist1);
		
		
	}

}
