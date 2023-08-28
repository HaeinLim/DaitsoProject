package com.three.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CateDTO;
import com.three.controller.ThreeImpl;

public class AdUpCheckCate implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �ش������� ī�� �ߺ� �̸��� �����ϴ����� ���� ���θ� �ۼ��Ѵ�
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
		//�̸� �ߺ����� ����
		if(cdto!=null) {
			//���� big�� ī�װ� �̸� �ߺ�
			if(cdto.getBigId() == bigId && cdto.getCateId() != cateId)
				request.setAttribute("check", 0);
			//�ٸ� big�� ī�װ� �̸� �ߺ�
			else if(cdto.getBigId() != bigId && cdto.getCateId() != cateId)
			request.setAttribute("check", 0);
			else request.setAttribute("check", 1);
		}
		// �ߺ� ���� ����
		else request.setAttribute("check", 1);
	
	}
}
