package com.three.my;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.ReviewDTO;
import com.three.controller.ThreeImpl;

public class myRevDelete implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileSave = request.getSession().getServletContext().getRealPath("rev_imgs");
		int sangId = Integer.parseInt(request.getParameter("sangId"));

		File dir = new File(fileSave);
		File[] files = dir.listFiles();
		String file1 = request.getParameter("file1");
		String file2 = request.getParameter("file2");
		String file3 = request.getParameter("file3");

		for (File f : files) {
			if (f.getName().equals(file1) || f.getName().equals(file2) || f.getName().equals(file3)) {
				f.delete();
			}
		}

		ThreeDAO tdao = new ThreeDAO();
		int revId = Integer.parseInt(request.getParameter("revId"));

		tdao.reviewDelete(revId);

		boolean result = tdao.updateSangStar(sangId);
		if (result)
			System.out.println("sangstarupdate");
		else
			System.out.println("sangstarupdateerror");

		String memId = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			memId = (String)session.getAttribute("sid");
		}
		
		ArrayList<ReviewDTO> rarray = tdao.getRevInfos(memId);
		HashMap<Integer, String> rmap = new HashMap<Integer, String>();

		for (int i = 0; i < rarray.size(); i++) {
			sangId = rarray.get(i).getSangId();
			String sangName = tdao.findSangName(sangId);
			rmap.put(sangId, sangName);
		}
		String nick = memId.substring(0, 3) + "***";

		request.setAttribute("nick", nick);

		request.setAttribute("rarray", rarray);
		request.setAttribute("rmap", rmap);

	}

}
