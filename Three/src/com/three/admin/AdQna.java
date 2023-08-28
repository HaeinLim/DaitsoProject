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

public class AdQna implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId=0;
		if(request.getParameter("sangId")!=null)
			sangId = Integer.parseInt(request.getParameter("sangId"));
		
		ThreeDAO tdao = new ThreeDAO();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		HashMap<Integer,ArrayList<SangpumDTO>> smap = new HashMap<Integer,ArrayList<SangpumDTO>>();
		for(int i=0; i<carray.size(); i++) {
			int cateId = carray.get(i).getCateId();
			ArrayList<SangpumDTO> sarray = tdao.searchSangByCate(cateId);
			smap.put(cateId, sarray);
		}
		
		ArrayList<QnaDTO> qarray = null;
		if(sangId != 0) qarray = tdao.searchQnaBySang(sangId);
		else qarray = tdao.getAllQnaMain();
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		HashMap<Integer,String> snmap = new HashMap<Integer, String>();
		for(int i=0; i<qarray.size(); i++) {
			int bunId = qarray.get(i).getBunId();
			String sangName = tdao.findSangName(qarray.get(i).getSangId());
			snmap.put(qarray.get(i).getQnaId(), sangName);
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(bunId);
			qsmap.put(bunId, qsrray);
		}
		
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("smap", smap);
		request.setAttribute("snmap", snmap);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}
}
