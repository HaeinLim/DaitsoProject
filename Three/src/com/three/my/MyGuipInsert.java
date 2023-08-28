package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CartDTO;
import com.three.DTO.GuipDTO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.ReviewDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class MyGuipInsert implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int guipId=Integer.parseInt(request.getParameter("guipId"));
		int guipTot=Integer.parseInt(request.getParameter("guipTotal"));
		String [] carr = request.getParameterValues("cartId");
		String road = request.getParameter("road"); // 서브밋으로 넘어온 고객이 작성한 주소
		String zipcode = request.getParameter("zipcode");
		String detail = request.getParameter("detail");
		String plus = null;
		if(request.getParameter("plus")!=null) {
			plus = request.getParameter("plus");
		}
		
		String address = road + " (" + detail+")";
		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		}
		
		ThreeDAO tdao = new ThreeDAO();
		
		boolean result = tdao.insertGuip(guipId, memId, guipTot,address,zipcode,plus);
		if(result) System.out.println("guipinsertdone");
		else System.out.println("guipinserterror");
		for(int i=0; i<carr.length; i++) {
			int cartId = Integer.parseInt(carr[i]);
			CartDTO cdto = tdao.findCartByCart(cartId);
			SangpumDTO sdto = tdao.findSang(cdto.getSangId());
			boolean result2 = tdao.insertorder(cdto.getSangId(),guipId,sdto.getSangName(),cdto.getSangCnt(),sdto.getSangPrice());
			if(result2) System.out.println("orderinsertdone");
			else System.out.println("orderinserterror");
			boolean result3 = tdao.updateSangCountM(cdto.getSangId(), cdto.getSangCnt());
			if(result3) System.out.println("sangUpdatedone");
			else System.out.println("sangUpdateerror");
			
		}
		
		
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
