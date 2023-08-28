package com.three.home;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.QnaDTO;
import com.three.controller.ThreeImpl;

public class Qna implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		ThreeDAO tdao = new ThreeDAO();
		
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			String memId = (String)session.getAttribute("sid");
			cartCnt = tdao.countCart(memId);
		}
		
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			int qnaId = qarray.get(i).getQnaId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(qnaId);
			qsmap.put(qnaId, qsrray);
		}
		
		request.setAttribute("cartCnt", cartCnt);
		request.setAttribute("sangId", sangId);

		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}

}
