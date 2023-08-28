package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.GuipDTO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.ReviewDTO;
import com.three.controller.ThreeImpl;

public class MyOrder implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		}
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<GuipDTO> garray = tdao.getAllGuipByMemExorder(memId);
		ArrayList<OrderListDTO> oarray = new ArrayList<OrderListDTO>();
		HashMap<Integer, ArrayList<OrderListDTO>> oamap = new HashMap<Integer, ArrayList<OrderListDTO>>();
		HashMap<Integer, ReviewDTO> rmap = new HashMap<Integer, ReviewDTO>();
		for (int i = 0; i < garray.size(); i++) {
			oarray = tdao.getAllOrderByGuip(garray.get(i).getGuipId());
			if(oarray.size() != 0) {
				oamap.put(garray.get(i).getGuipId(), oarray);
				for(int j=0; j<oarray.size(); j++) {
					ReviewDTO rdto = tdao.getAllReByOrder(oarray.get(j).getOrderId());
					if(rdto!= null)
						rmap.put(oarray.get(j).getOrderId(), rdto);
				}
			}			
		}
		//구입전체 array
		//구입아이디:오더array
		//오더아이디:리뷰어레이
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
		request.setAttribute("garray", garray);
		request.setAttribute("oamap", oamap);
		request.setAttribute("rmap", rmap);
	}
}
