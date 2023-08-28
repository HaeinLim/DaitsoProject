package com.three.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.QnaDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdDeleteQnaSub implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int qnaId = Integer.parseInt(request.getParameter("qnaId"));
		String sangName = request.getParameter("sangName");
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		boolean result = tdao.deleteQnaSub(qnaId);
		if(result) System.out.println("deleteQna done");
		
		ArrayList<CateDTO> carray = tdao.getAllCate();
		HashMap<Integer,ArrayList<SangpumDTO>> smap = new HashMap<Integer,ArrayList<SangpumDTO>>();
		HashMap<Integer,String> snmap = new HashMap<Integer, String>();
		for(int i=0; i<carray.size(); i++) {
			int cateId = carray.get(i).getCateId();
			ArrayList<SangpumDTO> sarray = tdao.searchSangByCate(cateId);
			smap.put(cateId, sarray);
		}
		
		ArrayList<QnaDTO> qarray = tdao.getAllQnaMain();
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			String sName = tdao.findSangName(qarray.get(i).getSangId());
			snmap.put(qarray.get(i).getQnaId(), sName);
			int nbunId = qarray.get(i).getBunId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(nbunId);
			qsmap.put(nbunId, qsrray);
		}
		
		request.setAttribute("sangName", sangName);
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("smap", smap);
		request.setAttribute("snmap", snmap);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}

}
