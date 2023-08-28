package com.three.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class DetailDelete implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fileSave = request.getSession().getServletContext().getRealPath("detail_imgs");
		
		File dir = new File(fileSave);
		File[] files = dir.listFiles();
		String file1 = request.getParameter("file1");
		String file2 = request.getParameter("file2");
		String file3 = request.getParameter("file3");
		String file4 = request.getParameter("file4");
		
		for(File f:files) {
			if(f.getName().equals(file1) || f.getName().equals(file2)
					|| f.getName().equals(file3)||f.getName().equals(file4)) {
				f.delete();
			}
		}
		
		
		ThreeDAO tdao = new ThreeDAO();
		int detId = Integer.parseInt(request.getParameter("detId"));
		
		tdao.deleteDetail(detId);
		
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
