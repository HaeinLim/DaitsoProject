package com.three.home;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class HomeMain implements ThreeImpl {
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		ThreeDAO tdao = new ThreeDAO();
		int cartCnt = 0;
		HttpSession session = request.getSession(false);
		if(session != null) {
			String memId = (String)session.getAttribute("sid");
			cartCnt = tdao.countCart(memId);
		}
		//ī�װ� �з�
		ArrayList<CateDTO> allcarray = tdao.getAllCate();
		ArrayList<CateDTO> carray1 = new ArrayList<CateDTO>();
		ArrayList<CateDTO> carray2 = new ArrayList<CateDTO>();
		ArrayList<CateDTO> carray3 = new ArrayList<CateDTO>();
		for(int i=0; i<allcarray.size(); i++) {
			if(allcarray.get(i).getBigId() == 1) {
				carray1.add(allcarray.get(i));
			}
			else if(allcarray.get(i).getBigId() ==2) {
				carray2.add(allcarray.get(i));
			}
			else {
				carray3.add(allcarray.get(i));
			}
		}
		
		// �������� ��(����)
		int nowPage = 1;
		if (request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		String ch = null;
		if (request.getParameter("ch")!=null) {
			ch = request.getParameter("ch");
		}
		// ��ü ��ǰ ����Ʈ
		ArrayList<SangpumDTO> allsarray = new ArrayList<SangpumDTO>();
		String searchName = "none"; //�⺻���� �˻���� none ó���̴�
		int cateId = 0;
		String order = "none"; // ���Ĺ�ư�� ���� �� ���� ��
		
		//�˻�� �޾ƿ��� �޼ҵ� searchName���� �޴´�
		if (request.getParameter("searchName")!= null) {
			searchName = request.getParameter("searchName");
		}
		
		if (request.getParameter("cateId")!=null) {
			cateId = Integer.parseInt(request.getParameter("cateId"));
		}
		if (request.getParameter("order")!=null) {
			order = request.getParameter("order");
		}
		
		// ī�׿���-> 0: ī��/�˻�, ī��, ī��/����, ī��/�˻�/����,
		// ī�� x-> �˻�, ����, �˻�/����, x
		if(cateId != 0) {
			if(order.equals("none")) {
				if(searchName.equals("none")) {
					allsarray = tdao.searchSangByCateZ(cateId);
				}
				else	allsarray = tdao.searchSangByCateNameZ(cateId, searchName);							
			}
			else {
				if(searchName.equals("none")) {
					allsarray = tdao.searchSangByCateOrder(cateId, order);
				}
				else	allsarray = tdao.searchSangByCateNameOrder(cateId, searchName ,order);
			}
		}
		else {
			if(order.equals("none")) {
				if(searchName.equals("none")) {
					allsarray = tdao.getAllSangnotZ();
				}
				else	allsarray = tdao.searchSangByNameZ(searchName);				
			}else {
				if(searchName.equals("none")) {
					allsarray = tdao.searchSangOrder(order);
				}
				else	allsarray = tdao.searchSangByNameOrder(searchName,order);
			}
		}
		int arcnt = 0;
		if(allsarray.size() != 0)	arcnt = allsarray.size(); // ��ǰ�� ����
		int pagecnt = 0; // ������ ��ü ����
		if(arcnt != 0) {
			if(arcnt%8 == 0) pagecnt = arcnt/8;
			else 	pagecnt = arcnt/8+1; 
		}
		//������ ��ȣ ����
		ArrayList<Integer> pageGruop = new ArrayList<Integer>();
		if (pagecnt > 2) {
			if(nowPage ==1) {
				pageGruop.add(1); pageGruop.add(2); pageGruop.add(3);
			}
			else if(nowPage == pagecnt) {
				pageGruop.add(nowPage-2); pageGruop.add(nowPage-1); pageGruop.add(nowPage);
			}
			else {
				pageGruop.add(nowPage-1); pageGruop.add(nowPage); pageGruop.add(nowPage+1);
			}
		}
		else if(pagecnt == 2) {
			pageGruop.add(1); pageGruop.add(2);
		}
		else pageGruop.add(1);
		
		ArrayList<SangpumDTO> sarray1 = new ArrayList<SangpumDTO>();
		ArrayList<SangpumDTO> sarray2 = new ArrayList<SangpumDTO>();
		//���������� �� ��ǰ����Ʈ
		if(pagecnt != 0) {
			if(pagecnt > nowPage) {
				for(int i = ((nowPage-1)*8); i<=((nowPage-1)*8+3); i++) {
					sarray1.add(allsarray.get(i));
				}
				for(int i = ((nowPage-1)*8+4); i<=((nowPage-1)*8+7); i++) {
					sarray2.add(allsarray.get(i));
				}			
			}
			else {
				int scnt=0; // ������ �������� �� ��ǰ����
				if(arcnt%8==0) scnt = 8;
				else  scnt = arcnt%8;
				if (scnt <4) {
					for(int i = ((nowPage-1)*8); i<=((nowPage-1)*8+(scnt-1)); i++) {
						sarray1.add(allsarray.get(i));
					}
				}
				else {
					for(int i = ((nowPage-1)*8); i<=((nowPage-1)*8+3); i++) {
						sarray1.add(allsarray.get(i));
					}
					for(int i = ((nowPage-1)*8+4); i<=((nowPage-1)*8+(scnt-1)); i++) {
						sarray2.add(allsarray.get(i));
					}
				}
			}
		}
		request.setAttribute("cartCnt", cartCnt);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageGruop", pageGruop);
		request.setAttribute("pageCnt", pagecnt);
		request.setAttribute("carray1", carray1);
		request.setAttribute("carray2", carray2);
		request.setAttribute("carray3", carray3);
		request.setAttribute("searchName", searchName);
		request.setAttribute("cateId", cateId);
		request.setAttribute("order", order);
		request.setAttribute("ch", ch);
		request.setAttribute("sarray1", sarray1);
		request.setAttribute("sarray2", sarray2);
	}
}
