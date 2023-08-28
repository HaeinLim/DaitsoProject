package com.three.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class IdCheck implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		System.out.println("����� idCheck.java: "+id);
		ThreeDAO thdao = new ThreeDAO();

		
		String id1 = thdao.idCheck(id);
		String message = null;
		int resultCheck = 0;
		
		if(id1 != null) {
			message = id+" ��(��) �̹� ���Ե� ���̵� �Դϴ�.";
			resultCheck = 0;
		}else {
			message = id+" ��(��) ��� ������ ���̵� �Դϴ�.";
			resultCheck = 1;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("message", message);
		request.setAttribute("resultCheck", resultCheck);
		
		System.out.println(id);
		System.out.println(resultCheck);

	}

}
