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


public class HomeInsertQnaSub implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("memId");
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		int bunId = Integer.parseInt(request.getParameter("bunId"));
		String qContent = request.getParameter("qContent");
		
		QnaDTO qdto = new QnaDTO(0,memId,sangId,depth,bunId,0,qContent,null);
		ThreeDAO tdao = new ThreeDAO();
		
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			cartCnt = tdao.countCart(memId);
		}
		
		//상품 
		SangpumDTO sdto = tdao.findSang(sangId);
		DetailDTO sd = tdao.findDetailOne(sangId);		
		
		boolean result = tdao.insertQnaSub(qdto);
		if(result) System.out.println("qnaInsert done");
		
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			int nbunId = qarray.get(i).getBunId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(nbunId);
			qsmap.put(nbunId, qsrray);
		}
		
		request.setAttribute("cartCnt", cartCnt);
		//상품
		request.setAttribute("sdto", sdto);
		request.setAttribute("sd", sd);
		
		request.setAttribute("sangId", sangId);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}

}
