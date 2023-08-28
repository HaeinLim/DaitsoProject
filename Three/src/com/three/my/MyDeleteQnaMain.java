package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.QnaDTO;
import com.three.controller.ThreeImpl;

public class MyDeleteQnaMain implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ThreeDAO tdao = new ThreeDAO();
		String memId = request.getParameter("memId");
		int bunId = Integer.parseInt(request.getParameter("bunId"));
		
		boolean result = tdao.deleteQnaMain(bunId);
		if(result) System.out.println("deleteQna done");
		
		ArrayList<QnaDTO> qarray = tdao.getAllMyQnaMain(memId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		HashMap<Integer,String> snmap = new HashMap<Integer, String>();
		for(int i=0; i<qarray.size(); i++) {
			String sangName = tdao.findSangName(qarray.get(i).getSangId());
			snmap.put(qarray.get(i).getQnaId(), sangName);
			int bnId = qarray.get(i).getBunId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(bnId);
			qsmap.put(bnId, qsrray);
		}
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
	    request.setAttribute("snmap", snmap);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}
}
