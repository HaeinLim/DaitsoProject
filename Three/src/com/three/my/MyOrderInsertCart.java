package com.three.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.controller.ThreeImpl;

public class MyOrderInsertCart implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ThreeDAO tdao = new ThreeDAO();
		int sangId= 0;
		int scnt=0;
		String memId= null;
		if(request.getParameter("memId") != null) memId = request.getParameter("memId");
		System.out.println(memId);
		if(request.getParameter("sangId") != null) {
			sangId = Integer.parseInt(request.getParameter("sangId"));
			scnt = tdao.findSangCnt(sangId); //상품최대 수량
			if(scnt>0) {
				int ccnt = tdao.findCartBySang(sangId, memId);
				if(ccnt > 0) {
					// 상품최대 수량scnt보다 더 담기지 않게
					// cart에 담긴 상품개수를 확인해서 scnt보다 작을 때만 insert
					boolean result = tdao.cartExUpdate(memId, sangId, 1, scnt);
					if(result) System.out.println("cartExinsertdone");
					else System.out.println("cartExInsertError");
				}else {
					boolean result = tdao.cartInsert(memId, sangId, 1);
					if(result) System.out.println("cartinsertdone");
					else System.out.println("cartInsertError");
				}
			}
		}
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
		request.setAttribute("cnt", scnt);	
		
		
		
	}

}
