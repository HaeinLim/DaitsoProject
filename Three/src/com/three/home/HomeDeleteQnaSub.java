package com.three.home;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.DetailDTO;
import com.three.DTO.QnaDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;


public class HomeDeleteQnaSub implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		int qnaId = Integer.parseInt(request.getParameter("qnaId"));
		
		ThreeDAO tdao = new ThreeDAO();
		
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			String memId = (String)session.getAttribute("sid");
			cartCnt = tdao.countCart(memId);
		}
		
		//상품 
		SangpumDTO sdto = tdao.findSang(sangId);
		DetailDTO sd = tdao.findDetailOne(sangId);		
		
		boolean result = tdao.deleteQnaSub(qnaId);
		if(result) System.out.println("deleteQna done");
		
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			int bunId = qarray.get(i).getBunId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(bunId);
			qsmap.put(bunId, qsrray);
		}
		
		request.setAttribute("cartCnt", cartCnt);
		
		request.setAttribute("sangId", sangId);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	
		//상품
		request.setAttribute("sdto", sdto);
		request.setAttribute("sd", sd);	
	}

}
