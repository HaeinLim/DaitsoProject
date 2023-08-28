package com.three.my;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.three.DAO.ThreeDAO;
import com.three.DTO.ReviewDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class MyRevUpdate implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileSave = request.getSession().getServletContext().getRealPath("rev_imgs");

		MultipartRequest multi = new MultipartRequest(request, fileSave, 15 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());
		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		}
		ThreeDAO tdao = new ThreeDAO();

		int revStar = Integer.parseInt(multi.getParameter("revStar"));
		String revPipath1 = multi.getParameter("before_file1");
		String revPipath2 = multi.getParameter("before_file2");
		String revPipath3 = multi.getParameter("before_file3");
		String revContent = multi.getParameter("revContent");
		int revId = Integer.parseInt(multi.getParameter("revId"));
		int sangId = Integer.parseInt(multi.getParameter("sangId"));

		String updateFile1 = multi.getFilesystemName("file1");
		String updateFile2 = multi.getFilesystemName("file2");
		String updateFile3 = multi.getFilesystemName("file3");

		File dir = new File(fileSave);
		File[] files = dir.listFiles();

		if (updateFile1 == null && updateFile2 == null && updateFile3 == null) {
			tdao.reviewUpdate(revStar, revPipath1, revPipath2, revPipath3, revContent, revId);
		} else {
			for (File f : files) {
				if (f.getName().equals(revPipath1) || f.getName().equals(revPipath2)
						|| f.getName().equals(revPipath3)) {
					f.delete();
				}
			}
			tdao.reviewUpdate(revStar, updateFile1, updateFile2, updateFile3, revContent, revId);
		}
		boolean result = tdao.updateSangStar(sangId);
		if(result) System.out.println("sangstarupdate");
		else System.out.println("sangstarupdateerror");
		ReviewDTO rdto = tdao.findRev(revId);

		request.setAttribute("rdto", rdto);

		SangpumDTO sdto = tdao.findSang(sangId);

		request.setAttribute("sdto", sdto);
		
		String nick = memId.substring(0,3)+"***";
		
	    request.setAttribute("nick", nick);
	}

}
