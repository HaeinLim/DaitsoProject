package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CancleDTO;
import com.three.DTO.GuipDTO;
import com.three.controller.ThreeImpl;

public class MyCancle implements ThreeImpl {

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
		ArrayList<GuipDTO> garray = tdao.getAllGuipByDate(memId);
		HashMap<Integer, String> cmap = new HashMap<Integer, String>();
		for(int i=0; i<garray.size(); i++) {
			CancleDTO cdto = tdao.findCancle(garray.get(i).getGuipId());
			if(cdto != null) cmap.put(garray.get(i).getGuipId(), "Y");
		}
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
		request.setAttribute("garray", garray);
		request.setAttribute("cmap", cmap);
	}
}
