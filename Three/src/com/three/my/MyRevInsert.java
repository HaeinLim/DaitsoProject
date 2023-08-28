package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.three.DAO.ThreeDAO;
import com.three.DTO.ReviewDTO;
import com.three.controller.ThreeImpl;

public class MyRevInsert implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileSave = request.getSession().getServletContext().getRealPath("rev_imgs");

		MultipartRequest multi = new MultipartRequest(request, fileSave, 15 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());

		ThreeDAO tdao = new ThreeDAO();

		int revStar = Integer.parseInt(multi.getParameter("revStar")); // 라디오버튼값 가져오는 문제(수정필요)
		System.out.println(revStar);
		String revPipath1 = multi.getFilesystemName("file1");
		String revPipath2 = multi.getFilesystemName("file2");
		String revPipath3 = multi.getFilesystemName("file3");
		String revContent = multi.getParameter("revContent");
		String memId = multi.getParameter("memId");
		int sangId = Integer.parseInt(multi.getParameter("sangId"));
		int orderId = Integer.parseInt(multi.getParameter("orderId"));

		try {
			boolean result = tdao.reviewInsert(memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3,
					revContent);

			if (result)
				System.out.println("입력성공");
			else
				System.out.println("입력실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean result = tdao.updateSangStar(sangId);
		if (result)
			System.out.println("sangstarupdate");
		else
			System.out.println("sangstarupdateerror");
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
