package com.three.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.admin.AdCancle;
import com.three.admin.AdCate;
import com.three.admin.AdDeleteCate;
import com.three.admin.AdDeleteQnaMain;
import com.three.admin.AdDeleteQnaSub;
import com.three.admin.AdDeleteSang;
import com.three.admin.AdFindCate;
import com.three.admin.AdInsertCate;
import com.three.admin.AdInsertQna;
import com.three.admin.AdInsertSang;
import com.three.admin.AdNewSang;
import com.three.admin.AdQna;
import com.three.admin.AdReSang;
import com.three.admin.AdSang;
import com.three.admin.AdSearchQnaBySang;
import com.three.admin.AdSearchSang;
import com.three.admin.AdSearchSangByName;
import com.three.admin.AdUpCheckCate;
import com.three.admin.AdUpdateCate;
import com.three.admin.AdUpdateSang;
import com.three.admin.AdtakeSName;
import com.three.admin.DetailDelete;
import com.three.admin.DetailInsert;
import com.three.admin.DetailUpdate;
import com.three.admin.AdGuipDel;
import com.three.admin.ThreeDelete;
import com.three.admin.ThreeDetailSerOne;
import com.three.admin.ThreeGetAllInfo;
import com.three.admin.ThreeSearch;
import com.three.admin.searchCancle;
import com.three.home.CartInsert;
import com.three.home.HomeDeleteQnaMain;
import com.three.home.HomeDeleteQnaSub;
import com.three.home.HomeInsertQnaMain;
import com.three.home.HomeInsertQnaSub;
import com.three.home.HomeMain;
import com.three.home.HomeSangDetail;
import com.three.home.Qna;
import com.three.my.CancleInsert;
import com.three.my.MyCancle;
import com.three.my.MyCart;
import com.three.my.MyCartDel;
import com.three.my.MyCartUp;
import com.three.my.MyDeleteQnaMain;
import com.three.my.MyDeleteQnaSub;
import com.three.my.MyGuip;
import com.three.my.MyGuipInsert;
import com.three.my.MyNewRev;
import com.three.my.MyOrder;
import com.three.my.MyOrderDelete;
import com.three.my.MyOrderInsertCart;
import com.three.my.MyOrderSearch;
import com.three.my.MyPage;
import com.three.my.MyQna;
import com.three.my.MyRevInfos;
import com.three.my.MyRevInsert;
import com.three.my.MyRevSerOne;
import com.three.my.MyRevUpdate;
import com.three.my.myRevDelete;
import com.three.service.IdCheck;
import com.three.service.LogOut;
import com.three.service.PwCheck;
import com.three.service.ThreeJoin;
import com.three.service.ThreeLogin;
import com.three.service.myDelete;
import com.three.service.myUpdate;

/**
 * Servlet implementation class Three
 */
@WebServlet("*.do")
public class FrontContoroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FrontContoroller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] url = request.getRequestURI().split("/");
		String fName = url[url.length - 1]; // list.do
		// String c = request.getServletPath(); //home/main.do
		// request.getRequestURI().substring(request.getContextPath().length());
		String str = null;
		ThreeImpl scmd = null;
		System.out.println(fName);
		switch (fName) {

		// ȸ�� �α���
		case "login.do":
			scmd = new ThreeLogin();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/login.jsp";
			break;

		// ȸ�� �α׾ƿ�
		case "logout.do":
			scmd = new LogOut();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/logout.jsp";
			break;

		// ȸ������ ���̵� �ߺ� üũ
		case "idCheck.do":
			scmd = new IdCheck();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(request.getAttribute("id1"));
			str = "home/idCheck.jsp";
			break;

		// ȸ�����Ե��
		case "join.do":
			scmd = new ThreeJoin();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/login.jsp";
			break;

		// ȸ������ ������ ��й�ȣ Ȯ��
		case "pwCheck.do":
			scmd = new PwCheck();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/pwCheckMsg.jsp";
			break;

		// ȸ��Ż��
		case "myDelete.do":
			scmd = new myDelete();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/logout.jsp";
			break;

		// ȸ������ ����
		case "myUpdate.do":
			scmd = new myUpdate();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myPage.jsp";
			break;

		// ī�װ� ����
		case "adCate.do":
			scmd = new AdCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adCate.jsp";
			break;

		case "adInsertCate.do":
			scmd = new AdInsertCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adCate.jsp";
			break;
		// cate insert �� üũ
		case "adFindCate.do":
			scmd = new AdFindCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "aj/adCate.jsp";
			break;

		case "adUpdateCate.do":
			scmd = new AdUpdateCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adCate.jsp";
			break;
		// ī�� ������Ʈ �� üũ
		case "adUpCheckCate.do":
			scmd = new AdUpCheckCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "aj/adCate.jsp";
			break;

		// ī�װ� ����
		case "adDeleteCate.do":
			scmd = new AdDeleteCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adCate.jsp";
			break;
		// ��ǰ ���
		case "adNewSang.do":
			scmd = new AdNewSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adNewSang.jsp";
			break;
		case "adInsertSang.do":
			scmd = new AdInsertSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;
		// ��ǰ��ȸ
		case "adSang.do":
			scmd = new AdSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;
		case "searchSangByCate.do":
			scmd = new AdSearchSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;
		case "adSearchSangByName.do":
			scmd = new AdSearchSangByName();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// ��ǰ����
		case "adReSang.do":
			scmd = new AdReSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adReSang.jsp";
			break;
		case "update.do":
			scmd = new AdUpdateSang();
			try {
				scmd.three(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str = "admin/adReSang.jsp";
			break;

		case "adDeleteSang.do":
			scmd = new AdDeleteSang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// ȸ�� �˻�
		case "search.do":

			scmd = new ThreeSearch();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adMem.jsp";
			break;

		// ȸ�� ��ü ��ȸ
		case "getAllinfo.do":
			scmd = new ThreeGetAllInfo();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str = "admin/adMem.jsp";
			break;

		// ȸ�� ����
		case "delete.do":

			scmd = new ThreeDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adMem.jsp";
			break;

		// ��ǰ���̵� Ŭ���� �ش� ���̵� ����� ������
		case "searchOne.do":
			scmd = new ThreeDetailSerOne();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adUpdateDetailForm.jsp";
			break;
		// ��ǰ �󼼼��� �Է� �������� ��ǰ �̸� ����������
		case "adTakeSName.do":
			scmd = new AdtakeSName();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adDetailinsForm.jsp";
			break;
		// ��ǰ �󼼼��� �߰��Է�
		case "detailInsert.do":

			scmd = new DetailInsert();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// ��ǰ �󼼼��� ����
		case "detUpdate.do":

			scmd = new DetailUpdate();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adUpdateDetailForm.jsp";
			break;

		// ��ǰ �󼼼��� ����
		case "detailDelete.do":

			scmd = new DetailDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// ������ ����
		case "adQna.do":
			scmd = new AdQna();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adQna.jsp";
			break;
		case "searchQnaBySang.do":
			scmd = new AdSearchQnaBySang();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adQna.jsp";
			break;
		case "AdDeleteQnaMain.do":
			scmd = new AdDeleteQnaMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adQna.jsp";
			break;
		case "AdDeleteQnaSub.do":
			scmd = new AdDeleteQnaSub();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adQna.jsp";
			break;
		case "insertQna.do":
			scmd = new AdInsertQna();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adQna.jsp";
			break;
		// ���������� ����
		case "myPage.do":
			scmd = new MyPage();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myPage.jsp";
			break;
		// ���������� ����
		case "myQna.do":
			scmd = new MyQna();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myQna.jsp";
			break;
		case "MyDeleteQnaMain.do":
			scmd = new MyDeleteQnaMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myQna.jsp";
			break;
		case "MyDeleteQnaSub.do":
			scmd = new MyDeleteQnaSub();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myQna.jsp";
			break;

		// ��ǰ ����
		case "qna.do":
			scmd = new Qna();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		case "deleteQnaMain.do":
			scmd = new HomeDeleteQnaMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		case "deleteQnaSub.do":
			scmd = new HomeDeleteQnaSub();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		case "insertQnaMain.do":
			scmd = new HomeInsertQnaMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		case "insertQnaSub.do":
			scmd = new HomeInsertQnaSub();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;

		// ���� ������
		case "main.do":
			scmd = new HomeMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/main.jsp";
			break;
		// ��ǰ �ϳ� ������ ��������
		case "sangDetail.do":
			scmd = new HomeSangDetail();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		// ������������ ��ٱ��ϴ��
		case "inputCart.do":
			scmd = new CartInsert();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		// �ֹ���Ϻ���
		case "myOrder.do":
			scmd = new MyOrder();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// �ֹ���Ϻ���
		case "myOrderSearch.do":
			scmd = new MyOrderSearch();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// �ֹ���ϻ���
		case "myDeleteOrder.do":
			scmd = new MyOrderDelete();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// �ֹ����� ��ٱ��� ���� ajax
		case "myOrderInsertCart.do":
			scmd = new MyOrderInsertCart();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "aj/myOrder.jsp";
			break;
		// ���������� ��ٱ���
		case "myCart.do":
			scmd = new MyCart();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// ���������� ��ٱ��Ϻ���
		case "myCartDel.do":
			scmd = new MyCartDel();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// ���������� ��ٱ��ϼ�������
		case "myCartUp.do":
			scmd = new MyCartUp();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// ������������ �̵�
		case "myGuip.do":
			scmd = new MyGuip();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myGuip.jsp";
			break;
		// �����ı� �ۼ�
		case "revInsert.do":
			scmd = new MyRevInsert();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// �����ı� ����
		case "revUpdate.do":
			scmd = new MyRevUpdate();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRevUpdate.jsp"; // ������ ������ ������ ������ ���������� �̵�
			break;

		// �����ı� ����
		case "revDelete.do":
			scmd = new myRevDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// �������������� ���� ��ȸ ��������
		case "myReview.do":
			scmd = new MyRevInfos();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// �ֹ���Ͽ��� ���� �ۼ� ��������
		case "myNewReview.do":
			scmd = new MyNewRev();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRevInsert.jsp";
			break;

		// ���� ������ ���� ���� �Ѱ� �˻�
		case "revSearchOne.do":
			scmd = new MyRevSerOne();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myRevUpdate.jsp";
			break;
		// �ֹ���� ������
		case "myCancle.do":
			scmd = new MyCancle();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myCancle.jsp";
			break;
		// ���� �μ�Ʈ
		case "payment.do":
			scmd = new MyGuipInsert();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myOrder.jsp";
			break;
		// ���� ����
		case "delGuip.do":
			scmd = new AdGuipDel();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "admin/adCancle.jsp";
			break;

		// ������ ��� ��û ����
		case "adCancle.do":
			scmd = new AdCancle();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "admin/adCancle.jsp";
			break;
		case "reqCancle.do":
	         scmd = new CancleInsert();
	         try {
	            scmd.three(request, response);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

	         str = "my/myCancle.jsp";
	         break;
		case "searchCancle.do":
	         scmd = new searchCancle();
	         try {
	            scmd.three(request, response);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

	         str = "admin/adCancle.jsp";
	         break;
		}
		request.getRequestDispatcher("../" + str).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
