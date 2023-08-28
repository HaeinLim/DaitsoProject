package com.three.admin;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdUpdateSang implements ThreeImpl{

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fileSave = request.getSession().getServletContext().getRealPath("sang_imgs");
		
		MultipartRequest multi = new MultipartRequest(request, fileSave,
						15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());		
		
		int cateId = Integer.parseInt(multi.getParameter("cateId"));
		int sangId = Integer.parseInt(multi.getParameter("sangId"));
		String sangName = multi.getParameter("sangName");
		String sangDate = multi.getParameter("sangDate");
		int sangAmount = Integer.parseInt(multi.getParameter("sangAmount"));
		int sangPrice = Integer.parseInt(multi.getParameter("sangPrice"));
		String sangPipath = null;
		if(multi.getParameter("before_file") != null) {
			sangPipath = multi.getParameter("before_file");
		}
		String updateFile = multi.getFilesystemName("file");
		
		File dir = new File(fileSave);
		File[] files = dir.listFiles();
		
		ThreeDAO tdao = new ThreeDAO();
		boolean result = false;
		if(updateFile != null) {
			for(File f:files) {
				if(f.getName().equals(sangPipath))
					f.delete();
			}
			result = tdao.update(sangId, cateId,sangName, sangDate, sangAmount, sangPrice, updateFile);
		} else	result = tdao.update(sangId, cateId,sangName, sangDate, sangAmount, sangPrice, sangPipath);
		if (result) System.out.println("sangUpdateDone");
		else System.out.println("sangUpdateError");
		
		SangpumDTO sdto = tdao.findSang(sangId);
		String cateName  = tdao.findCateName(cateId);
		
		ArrayList<CateDTO> carray = tdao.getAllCate();
		ArrayList<BigDTO> barray = tdao.getAllBig();
		
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
		request.setAttribute("cateName", cateName);
		request.setAttribute("sdto", sdto);

		
	}

}
