package com.three.home;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.DetailDTO;
import com.three.DTO.QnaDTO;
import com.three.DTO.ReviewDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class HomeSangDetail implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String star = "up";
		if(request.getParameter("star")!=null) {
			star = request.getParameter("star");
		}
		ThreeDAO tdao = new ThreeDAO();
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			String memId = (String)session.getAttribute("sid");
			cartCnt = tdao.countCart(memId);
		}
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		//상품
		SangpumDTO sdto = null;
		DetailDTO sd = null;
		if(tdao.findSangCnt(sangId)!=0) {
			sdto = tdao.findSang(sangId);
			sd = tdao.findDetailOne(sangId);
		}
		
		//문의
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			int qnaId = qarray.get(i).getQnaId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(qnaId);
			qsmap.put(qnaId, qsrray);
		}
		//리뷰
		ArrayList<ReviewDTO>  rarray = tdao.getAllReviewBySang(sangId,star);

		for(int i=0; i<rarray.size(); i++) {
			String nick = rarray.get(i).getMemId().substring(0,3)+"***";
			rarray.get(i).setMemId(nick);
		}
		
		request.setAttribute("cartCnt", cartCnt);
		request.setAttribute("sangId", sangId);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
		request.setAttribute("sdto", sdto);
		request.setAttribute("sd", sd);
		request.setAttribute("rarray", rarray);
		request.setAttribute("star", star);
	
		
	}

}
