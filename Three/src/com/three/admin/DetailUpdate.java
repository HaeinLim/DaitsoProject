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
import com.three.DTO.DetailDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class DetailUpdate implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fileSave = request.getSession().getServletContext().getRealPath("detail_imgs");
		
		MultipartRequest multi = new MultipartRequest(request, fileSave,
						15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		ThreeDAO tdao = new ThreeDAO();
		
		String detName = multi.getParameter("detName");
		String detPipath1 = multi.getParameter("before_file1");
		String detPipath2 = multi.getParameter("before_file2");
		String detPipath3 = multi.getParameter("before_file3");
		String detPipath4 = multi.getParameter("before_file4");
		int detId = Integer.parseInt(multi.getParameter("detId"));
		int sangId = Integer.parseInt(multi.getParameter("sangId"));
		String updateFile1 = multi.getFilesystemName("file1");
		String updateFile2 = multi.getFilesystemName("file2");
		String updateFile3 = multi.getFilesystemName("file3");
		String updateFile4 = multi.getFilesystemName("file4");
		
		File dir = new File(fileSave);
		File[] files = dir.listFiles();
		
		if(updateFile1 == null && updateFile2 == null && updateFile3 == null && updateFile4 ==null) {
			tdao.detailUpdate(detName, detPipath1, detPipath2, detPipath3, detPipath4, detId);
		} else {
			for(File f:files) {
				if(f.getName().equals(detPipath1) || f.getName().equals(detPipath2)
						|| f.getName().equals(detPipath3)||f.getName().equals(detPipath4)) {
					f.delete();
				}
			}
			tdao.detailUpdate(detName, updateFile1, updateFile2, updateFile3, updateFile4, detId);
		}
		
		DetailDTO det = tdao.detailSearchOne(detId);
		SangpumDTO sdto = tdao.findSang(sangId);


		request.setAttribute("sdet", det);
		request.setAttribute("sdto", sdto);
		
	}

}
