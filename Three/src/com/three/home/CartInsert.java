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

public class CartInsert implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String memId = request.getParameter("memId");
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		int sangCount = Integer.parseInt(request.getParameter("sangCount"));

		ThreeDAO tdao = new ThreeDAO();
		
		try {
			int cartEx = tdao.findCartBySang(sangId, memId);

			if (cartEx != 0)
				tdao.cartPlus(sangId, memId, sangCount);
			else {
				boolean result = tdao.cartInsert(memId, sangId, sangCount);

				if (result)
					System.out.println("장바구니 성공");
				else
					System.out.println("장바구니 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			cartCnt = tdao.countCart(memId);
		}

		// 상품
		SangpumDTO sdto = tdao.findSang(sangId);
		DetailDTO sd = tdao.findDetailOne(sangId);

		// qna
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer, ArrayList<QnaDTO>> qsmap = new HashMap<Integer, ArrayList<QnaDTO>>();
		for (int i = 0; i < qarray.size(); i++) {
			int qnaId = qarray.get(i).getQnaId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(qnaId);
			qsmap.put(qnaId, qsrray);
		}

		request.setAttribute("cartCnt", cartCnt);
		// qna
		request.setAttribute("sangId", sangId);
		request.setAttribute("qarray", qarray);
		request.setAttribute("qsmap", qsmap);
		// 상품
		request.setAttribute("sdto", sdto);
		request.setAttribute("sd", sd);
	}

}
