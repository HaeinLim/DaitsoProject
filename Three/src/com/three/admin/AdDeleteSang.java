package com.three.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdDeleteSang implements ThreeImpl{
	
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int sangId = Integer.parseInt(request.getParameter("sangId"));
		
		String fileSave = request.getSession().getServletContext().getRealPath("detail_imgs");
		File dir = new File(fileSave);
		File[] files = dir.listFiles();
		String file = request.getParameter("file");
		for(File f:files) {
			if(f.getName().equals(file)) {
				f.delete();
			}
		}
		
		ThreeDAO tdao = new ThreeDAO();
		
		boolean result = tdao.DeleteSang(sangId);
		if(result) System.out.println("deleteSangDone");
		else System.out.println("deleteErorr");
		
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		ArrayList<SangpumDTO> sarray = tdao.getAllSang();
		HashMap<Integer, Integer> exdet = tdao.exsDetil();

		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("sarray", sarray);
		request.setAttribute("exdet", exdet);
	}
}