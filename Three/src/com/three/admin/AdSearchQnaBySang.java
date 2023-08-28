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

public class AdSearchQnaBySang implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		ThreeDAO tdao = new ThreeDAO();
		String sangName = tdao.findSang(sangId).getSangName();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		HashMap<Integer,ArrayList<SangpumDTO>> smap = new HashMap<Integer,ArrayList<SangpumDTO>>();
		for(int i=0; i<carray.size(); i++) {
			int cateId = carray.get(i).getCateId();
			ArrayList<SangpumDTO> sarray = tdao.searchSangByCate(cateId);
			smap.put(cateId, sarray);
		}
		
		ArrayList<QnaDTO> qarray = tdao.searchQnaBySang(sangId);
		HashMap<Integer,ArrayList<QnaDTO>> qsmap = new HashMap<Integer,ArrayList<QnaDTO>>();
		for(int i=0; i<qarray.size(); i++) {
			int qnaId = qarray.get(i).getQnaId();
			ArrayList<QnaDTO> qsrray = tdao.getAllQnaSub(qnaId);
			qsmap.put(qnaId, qsrray);
		}
		
		request.setAttribute("sangName", sangName);
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("smap", smap);
		request.setAttribute("qarray", qarray);	
		request.setAttribute("qsmap", qsmap);
	}
}
