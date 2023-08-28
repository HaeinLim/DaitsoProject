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
		
		
		int bigId = 0; //������ ���� ���� �ִ�
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String cateName = request.getParameter("cateName");
		if(request.getParameter("bigId") != null) {
			bigId = Integer.parseInt(request.getParameter("bigId"));
		}
		
		// ����ڴ� ī�װ� �̸� ���游, ��ġ������, ��ġ�� �̸��� ��� ������ �� �ִ�
		// �̶� �̸��� ������ ���� �� bigId���� ���� ���� ������ ���� �� �� �ִ�
		if(bigId==0)	{
			bigId = tdao.findCate(cateId).getBigId();
			boolean result = tdao.updateCate(cateId, cateName, bigId);
			if(result) System.out.println("�̸��� ����Ϸ�");
			else System.out.println("�̸��� ���� ����");
		}
		else {
			boolean result = tdao.updateCate(cateId, cateName, bigId);
			if(result) System.out.println("ī�װ� �����Ϸ�");
			else System.out.println("ī�װ� ���� ����");
		}	
		
		// ȭ�������� ���� ��
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
