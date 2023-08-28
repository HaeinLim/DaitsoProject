package com.three.admin;

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

public class DetailInsert implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fileSave = request.getSession().getServletContext().getRealPath("detail_imgs");
		
		MultipartRequest multi = new MultipartRequest(request, fileSave,
						15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		ThreeDAO tdao = new ThreeDAO();
		
		int sangId = Integer.parseInt(multi.getParameter("sangId"));
		String detName = multi.getParameter("detName");
		String detPipath1 = multi.getFilesystemName("file1");
		String detPipath2 = multi.getFilesystemName("file2");
		String detPipath3 = multi.getFilesystemName("file3");
		String detPipath4 = multi.getFilesystemName("file4");
		
		try {
			boolean result = tdao.detailInsert(sangId, detName, detPipath1, detPipath2, detPipath3, detPipath4);
			
			if(result) {
				System.out.println("저장");
			} else {
				System.out.println("실패");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
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
