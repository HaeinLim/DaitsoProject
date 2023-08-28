package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CartDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class MyCart  implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		} 
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<CartDTO> carray = tdao.getAllCart(memId);
		HashMap<Integer,SangpumDTO> smap = new HashMap<Integer,SangpumDTO>();
		HashMap<Integer,SangpumDTO> snmap = new HashMap<Integer,SangpumDTO>();
		for(int i=0; i<carray.size(); i++) {
			int cartId = carray.get(i).getCartId();
			int sangId = carray.get(i).getSangId();
			SangpumDTO sdto = tdao.findSang(sangId);
			if(sdto!= null && sdto.getSangAmount() != 0)
				smap.put(cartId, sdto);
			else	snmap.put(cartId, sdto);
		}
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
		request.setAttribute("carray", carray);	
		request.setAttribute("smap", smap);
		request.setAttribute("snmap", snmap);
	}
}
