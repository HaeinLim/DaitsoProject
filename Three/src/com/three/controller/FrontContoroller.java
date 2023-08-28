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

		// 회원 로그인
		case "login.do":
			scmd = new ThreeLogin();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/login.jsp";
			break;

		// 회원 로그아웃
		case "logout.do":
			scmd = new LogOut();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/logout.jsp";
			break;

		// 회원가입 아이디 중복 체크
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

		// 회원가입등록
		case "join.do":
			scmd = new ThreeJoin();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "home/login.jsp";
			break;

		// 회원정보 수정시 비밀번호 확인
		case "pwCheck.do":
			scmd = new PwCheck();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/pwCheckMsg.jsp";
			break;

		// 회원탈퇴
		case "myDelete.do":
			scmd = new myDelete();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/logout.jsp";
			break;

		// 회원정보 수정
		case "myUpdate.do":
			scmd = new myUpdate();
			try {
				scmd.three(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myPage.jsp";
			break;

		// 카테고리 관리
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
		// cate insert 시 체크
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
		// 카테 업데이트 시 체크
		case "adUpCheckCate.do":
			scmd = new AdUpCheckCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "aj/adCate.jsp";
			break;

		// 카테고리 삭제
		case "adDeleteCate.do":
			scmd = new AdDeleteCate();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adCate.jsp";
			break;
		// 상품 등록
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
		// 상품조회
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

		// 상품수정
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

		// 회원 검색
		case "search.do":

			scmd = new ThreeSearch();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adMem.jsp";
			break;

		// 회원 전체 조회
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

		// 회원 삭제
		case "delete.do":

			scmd = new ThreeDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adMem.jsp";
			break;

		// 제품아이디 클릭시 해당 아이디 결과만 보여줌
		case "searchOne.do":
			scmd = new ThreeDetailSerOne();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adUpdateDetailForm.jsp";
			break;
		// 제품 상세설명 입력 페이지에 제품 이름 가져가도록
		case "adTakeSName.do":
			scmd = new AdtakeSName();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adDetailinsForm.jsp";
			break;
		// 제품 상세설명 추가입력
		case "detailInsert.do":

			scmd = new DetailInsert();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// 제품 상세설명 수정
		case "detUpdate.do":

			scmd = new DetailUpdate();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adUpdateDetailForm.jsp";
			break;

		// 제품 상세설명 삭제
		case "detailDelete.do":

			scmd = new DetailDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "admin/adSang.jsp";
			break;

		// 관리자 문의
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
		// 마이페이지 문의
		case "myPage.do":
			scmd = new MyPage();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myPage.jsp";
			break;
		// 마이페이지 문의
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

		// 상품 문의
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

		// 메인 페이지
		case "main.do":
			scmd = new HomeMain();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/main.jsp";
			break;
		// 상품 하나 누르면 상세페이지
		case "sangDetail.do":
			scmd = new HomeSangDetail();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		// 상세페이지에서 장바구니담기
		case "inputCart.do":
			scmd = new CartInsert();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "home/detail.jsp";
			break;
		// 주문목록보기
		case "myOrder.do":
			scmd = new MyOrder();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// 주문목록보기
		case "myOrderSearch.do":
			scmd = new MyOrderSearch();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// 주문목록삭제
		case "myDeleteOrder.do":
			scmd = new MyOrderDelete();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myOrder.jsp";
			break;
		// 주문내역 장바구니 담기시 ajax
		case "myOrderInsertCart.do":
			scmd = new MyOrderInsertCart();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "aj/myOrder.jsp";
			break;
		// 마이페이지 장바구니
		case "myCart.do":
			scmd = new MyCart();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// 마이페이지 장바구니비우기
		case "myCartDel.do":
			scmd = new MyCartDel();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// 마이페이지 장바구니수량변경
		case "myCartUp.do":
			scmd = new MyCartUp();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myCart.jsp";
			break;
		// 구입페이지로 이동
		case "myGuip.do":
			scmd = new MyGuip();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myGuip.jsp";
			break;
		// 구매후기 작성
		case "revInsert.do":
			scmd = new MyRevInsert();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// 구매후기 수정
		case "revUpdate.do":
			scmd = new MyRevUpdate();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRevUpdate.jsp"; // 수정시 수정된 데이터 가지고 수정폼으로 이동
			break;

		// 구매후기 삭제
		case "revDelete.do":
			scmd = new myRevDelete();

			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// 마이페이지에서 리뷰 조회 페이지로
		case "myReview.do":
			scmd = new MyRevInfos();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRev.jsp";
			break;

		// 주문목록에서 리뷰 작성 페이지로
		case "myNewReview.do":
			scmd = new MyNewRev();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "my/myRevInsert.jsp";
			break;

		// 리뷰 수정을 위한 리뷰 한개 검색
		case "revSearchOne.do":
			scmd = new MyRevSerOne();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myRevUpdate.jsp";
			break;
		// 주문취소 페이지
		case "myCancle.do":
			scmd = new MyCancle();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myCancle.jsp";
			break;
		// 구입 인서트
		case "payment.do":
			scmd = new MyGuipInsert();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "my/myOrder.jsp";
			break;
		// 구입 삭제
		case "delGuip.do":
			scmd = new AdGuipDel();
			try {
				scmd.three(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			str = "admin/adCancle.jsp";
			break;

		// 관리자 취소 요청 관리
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
