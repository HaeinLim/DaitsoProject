package com.three.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.three.DAO.ThreeDAO;
import com.three.DTO.CancleDTO;
import com.three.DTO.GuipDTO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.ReviewDTO;
import com.three.controller.ThreeImpl;

public class AdGuipDel implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int guipId = Integer.parseInt(request.getParameter("guipId"));
		
		ThreeDAO tdao = new ThreeDAO();
		
		ArrayList<OrderListDTO> oarray = tdao.searchOrderByGuip(guipId);
		for(int i=0; i<oarray.size(); i++) {
			boolean result = tdao.updateSangCountP(oarray.get(i).getSangId(), oarray.get(i).getOrderScnt());
			if(result) System.out.println("sangupdatedone");
			else System.out.println("sangpudateerror");
			ReviewDTO rdto = tdao.findRevByOrder(oarray.get(i).getOrderId());
			if(rdto != null) {
				boolean result2 = tdao.reviewDelete(rdto.getRevId());
				if(result2) System.out.println("revDeldone");
				else System.out.println("revDelerror");
			}
		}
		boolean result2 = tdao.deleteCancle(guipId);
		if(result2) System.out.println("cancleDeletedone");
		else System.out.println("cancleDeleteerror");
		boolean result = tdao.deleteGuip(guipId);//order도 연속삭제됨
		if(result) System.out.println("guipDeletedone");
		else System.out.println("guipDeleteerror");
		
		ArrayList<CancleDTO> carray = tdao.searchCancle();
		
		request.setAttribute("carray", carray);
	}
}