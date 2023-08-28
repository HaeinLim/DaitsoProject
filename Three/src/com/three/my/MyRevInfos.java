package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.ReviewDTO;
import com.three.controller.ThreeImpl;

public class MyRevInfos implements ThreeImpl {

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
		ArrayList<ReviewDTO> rarray = tdao.getRevInfos(memId);
		HashMap<Integer, String> rmap = new HashMap<Integer, String>();
		
		for(int i=0; i<rarray.size(); i++) {
			int sangId = rarray.get(i).getSangId();
			String sangName = tdao.findSangName(sangId);
			rmap.put(sangId, sangName);
		}
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
		request.setAttribute("rarray", rarray);
		request.setAttribute("rmap", rmap);
		
		

	}

}
