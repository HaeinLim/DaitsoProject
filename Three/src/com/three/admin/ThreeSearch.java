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

		// ȸ�� ��ȸ
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<MemberDTO> alist1 = tdao.search(request.getParameter("keyWord"));
										// �˻�â�� �Է��� ���� ������ dao�� �������ϱ� ������ request.getParameter�� �� ��������
		
		request.setAttribute("alist1", alist1);
		
		
	}

}
