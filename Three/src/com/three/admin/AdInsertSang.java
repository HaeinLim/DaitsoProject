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

public class AdInsertSang implements ThreeImpl {

    @Override
    public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        

        String fileSave = request.getSession().getServletContext().getRealPath("sang_imgs");
        
        MultipartRequest multi = new MultipartRequest(request, fileSave,
						15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
        
        int cateId = Integer.parseInt(multi.getParameter("cateId"));
        String sangName = multi.getParameter("sangName");
		int sangAmount = Integer.parseInt(multi.getParameter("sangAmount"));
		int sangPrice = Integer.parseInt(multi.getParameter("sangPrice"));
		String sangPipath = multi.getFilesystemName("file");
		
		ThreeDAO tdao = new ThreeDAO();
		
		boolean result = tdao.insertSang(cateId, sangName, sangPrice, sangPipath, sangAmount);
		if(result) System.out.println("sangInsertdone");
		else System.out.println("sangInserterror");
		
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
