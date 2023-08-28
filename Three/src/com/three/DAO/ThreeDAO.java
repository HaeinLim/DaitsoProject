package com.three.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.three.DBCon.DBCon;
import com.three.DTO.BigDTO;
import com.three.DTO.CancleDTO;
import com.three.DTO.CartDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.DetailDTO;
import com.three.DTO.GuipDTO;
import com.three.DTO.MemberDTO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.QnaDTO;
import com.three.DTO.ReviewDTO;
import com.three.DTO.SangpumDTO;

public class ThreeDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ThreeDAO() throws ClassNotFoundException, SQLException {
		con = new DBCon().getConnection();
	}

	public void getClose() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
	}

	// 로그인체크
	public MemberDTO loginCheck(String id, String pw) throws SQLException {

		MemberDTO memdto = null;

		try {

			String sql = "SELECT * FROM member WHERE mem_id=? AND mem_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String id1 = rs.getString(1);
				String pw1 = rs.getString(2);
				String name1 = rs.getString(3);
				String phone1 = rs.getString(4);
				String email1 = rs.getString(5);

				memdto = new MemberDTO(id1, pw1, name1, phone1, email1);

			} else {
				memdto = null;
			} // if-end

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		} // tryCatch-end

		return memdto;

	} // loginCheck-end

	// 회원가입

	public String idCheck(String id) throws SQLException {

		String id1 = null;

		try {

			String sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				id1 = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		} // tryCatch-end

		return id1;

	}

	public boolean joinInsert(String memId, String memPw, String memName, String memPhone, String memEmail)
			throws SQLException {

		try {
			String sql = "INSERT INTO member VALUES(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			pstmt.setString(3, memName);
			pstmt.setString(4, memPhone);
			pstmt.setString(5, memEmail);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}

		return true;
	}

	public boolean myDelete(String id) throws SQLException {

		String sql = "delete from member where mem_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);

		pstmt.executeUpdate();
		return true;
	}

	public boolean myUpdate(String id, String pw, String memPhone, String memEmail) throws SQLException {
		String sql = "update member set mem_pw=?, mem_phone=?, mem_email=? where mem_id=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, pw);
		pstmt.setString(2, memPhone);
		pstmt.setString(3, memEmail);
		pstmt.setString(4, id);
		pstmt.executeUpdate();
		return true;
	}

	// 상품 전체 조회
	public ArrayList<SangpumDTO> getAllSang() throws SQLException {
		ArrayList<SangpumDTO> sarray = new ArrayList<SangpumDTO>();
		String sql = "select * from sangpum order by sang_date";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int sangId = rs.getInt("sang_id");
			int cateId = rs.getInt("cate_id");
			String sangName = rs.getString("sang_name");
			int sangPrice = rs.getInt("sang_price");
			double sangStar = rs.getDouble("sang_star");
			String sangPipath = rs.getString("sang_pipath");
			int sangAmount = rs.getInt("sang_amount");
			Date sangDate = rs.getDate("sang_date");

			SangpumDTO sdto = new SangpumDTO(sangId, cateId, sangName, sangPrice, sangStar, sangPipath, sangAmount,
					sangDate);

			sarray.add(sdto);

		}
		return sarray;
	}

	// big전체조회
	public ArrayList<BigDTO> getAllBig() throws SQLException {
		ArrayList<BigDTO> barry = new ArrayList<BigDTO>();
		String sql = "select * from big order by big_id";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int bigId = rs.getInt("big_id");
			String bigName = rs.getString("big_name");

			BigDTO bdto = new BigDTO(bigId, bigName);
			barry.add(bdto);
		}
		return barry;
	}

	// 카테고리 전체 조회
	public ArrayList<CateDTO> getAllCate() throws SQLException {
		ArrayList<CateDTO> catearry = new ArrayList<CateDTO>();
		String sql = "select * from cate order by big_id";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int cateId = rs.getInt("cate_id");
			String cateName = rs.getString("cate_name");
			int bigId = rs.getInt("big_id");

			CateDTO ctdto = new CateDTO(cateId, cateName, bigId);
			catearry.add(ctdto);
		}
		return catearry;
	}

	// 카테고리아이디로 카테고리 한개 조회
	public CateDTO findCate(int cateId) throws SQLException {
		CateDTO cdto = new CateDTO();
		String sql = "select * from cate where cate_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cateId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			String cateName = rs.getString("cate_name");
			int bigId = rs.getInt("big_id");
			cdto = new CateDTO(cateId, cateName, bigId);
		}
		return cdto;
	}

	// 카테고리 등록
	public boolean InsertCate(int bigId, String cateName) throws SQLException {
		String sql = "insert into cate values(cateseq.NEXTVAL,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateName);
			pstmt.setInt(2, bigId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert error");
			return false;
		}
		return true;
	}

	// 카테고리 이름중복 조회
	public boolean checkCateName(String cateName) throws SQLException {
		int result = 1;
		String sql = "select count(*) as result from cate where cate_name like '%' ||?|| '%'";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cateName);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt("result");
		}
		if (result == 0)
			return true;
		else
			return false;
	}
	//카테고리 이름으로 조회
	public CateDTO findCateByName(String cateName) throws SQLException {
		CateDTO cdto = null;
		String sql = "select * from cate where cate_name like '%' ||?|| '%'";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cateName);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			int bigId = rs.getInt("big_id");
			int cateId = rs.getInt("cate_id");
			cdto = new CateDTO(cateId, cateName, bigId);
		}
		return cdto;
	}
	// 카테고리 수정
	public boolean updateCate(int cateId, String cateName, int bigId) throws SQLException {
		String sql = "update cate set cate_name=?, big_id=? where cate_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateName);
			pstmt.setInt(2, bigId);
			pstmt.setInt(3, cateId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update error");
			return false;
		}
		return true;
	}

	// 상품이 존재하는 카테고리의 아이디값 찾기
	public ArrayList<CateDTO> searchCateNoExistsSang() throws SQLException {
		ArrayList<CateDTO> carr = new ArrayList<CateDTO>();
		String sql = "select * from cate where cate_id not in (select cate_id from sangpum group by cate_id)";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int cateId = rs.getInt("cate_id");
			String cateName = rs.getString("cate_name");
			int bigId = rs.getInt("big_id");

			CateDTO ctdto = new CateDTO(cateId, cateName, bigId);
			carr.add(ctdto);
		}
		return carr;

	}

	// 카테고리 삭제
	public boolean deleteCate(int cateId) throws SQLException {
		String sql = "delete from cate where cate_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cateId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete error");
			return false;
		}
		return true;
	}

	// 카테로 상품조회
	public ArrayList<SangpumDTO> searchSangByCate(int cateId) throws SQLException {
		ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
		String sql = "select * from sangpum where cate_id =? order by cate_id";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cateId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int sangId = rs.getInt("sang_id");
			String sangName = rs.getString("sang_name");
			int sangPrice = rs.getInt("sang_price");
			double sangStar = rs.getDouble("sang_star");
			String sangPipath = rs.getString("sang_pipath");
			int sangAmount = rs.getInt("sang_amount");
			Date sangDate = rs.getDate("sang_date");

			SangpumDTO sdto = new SangpumDTO(sangId, cateId, sangName, sangPrice, sangStar, sangPipath, sangAmount,
					sangDate);
			sarry.add(sdto);
		}
		return sarry;
	}

	// 상품등록
	public boolean insertSang(int cateId, String sangName, int sangPrice, String sangPipath, int sangAmount)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into sangpum values (sangseq.nextval, ?, ?, ?, 0, ?, ?, sysdate)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cateId);
		pstmt.setString(2, sangName);
		pstmt.setInt(3, sangPrice);
		pstmt.setString(4, sangPipath);
		pstmt.setInt(5, sangAmount);

		pstmt.executeUpdate();
		return true;
	}

	// 상품아이디로 상품한개 조회
	public SangpumDTO findSang(int sangId) throws SQLException {
		SangpumDTO sdto = new SangpumDTO();
		String sql = "select * from sangpum where sang_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int cateId = rs.getInt("cate_id");
			String sangName = rs.getString("sang_name");
			int sangPrice = rs.getInt("sang_price");
			double sangStar = rs.getDouble("sang_star");
			String sangPipath = rs.getString("sang_pipath");
			int sangAmount = rs.getInt("sang_amount");
			Date sangDate = rs.getDate("sang_date");
			sdto = new SangpumDTO(sangId, cateId, sangName, sangPrice, sangStar, sangPipath, sangAmount, sangDate);
		}
		else sdto = null;
		return sdto;
	}

	// 상품상세설명 존재여부 조회
	public HashMap<Integer, Integer> exsDetil() throws SQLException {
		HashMap<Integer, Integer> exsDet = new HashMap<Integer, Integer>();
		String sql = "select sang_id, detail_id from detail where sang_id in (select sang_id from sangpum)";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int sangId = rs.getInt("sang_id");
			int detId = rs.getInt("detail_id");
			exsDet.put(sangId, detId);
		}
		return exsDet;
	}

	// 카테고리 이름 조회
	public String findCateName(int cateId) throws SQLException {
		String cateName = null;
		String sql = "select cate_name from cate where cate_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cateId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			cateName = rs.getString("cate_name");
		}
		return cateName;
	}

	// 상품 업데이트
	public boolean update(int sangId, int cateId, String sangName, String sangDate, int sangAmount, int sangPrice,
			String sangPipath) throws SQLException {
		String sql = "update sangpum set cate_id=?, sang_name=?,sang_price=? ,sang_amount=?, sang_pipath =?,sang_date=TO_DATE(?,'YYYY-MM-DD') where sang_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cateId);
		pstmt.setString(2, sangName);
		pstmt.setInt(3, sangPrice);
		pstmt.setInt(4, sangAmount);
		pstmt.setString(5, sangPipath);
		pstmt.setString(6, sangDate);
		pstmt.setInt(7, sangId);
		pstmt.executeUpdate();
		return true;
	}

	// 카테고리 삭제
	public boolean DeleteSang(int sangId) throws SQLException {
		String sql = "delete from sangpum where sang_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete error");
			return false;
		}
		return true;
	}

	// 회원 이름 검색
	public ArrayList<MemberDTO> search(String keyWord) throws SQLException {
		ArrayList<MemberDTO> mearray = new ArrayList<MemberDTO>();
		String sql = "select * from member " + " where lower(mem_id) like '%' ||lower(?)|| '%' order by mem_name";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, keyWord);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String memId = rs.getString("mem_id");
			String memPw = rs.getString("mem_pw");
			String memName = rs.getString("mem_name");
			String memPhone = rs.getString("mem_phone");
			String memEmail = rs.getString("mem_email");

			MemberDTO md = new MemberDTO(memId, memPw, memName, memPhone, memEmail);
			mearray.add(md);
		}
		return mearray;

	} // search-end

	// 회원 전체 조회
	public ArrayList<MemberDTO> getAllinfo() throws SQLException {
		ArrayList<MemberDTO> marray = new ArrayList<MemberDTO>();
		String sql = "select * from member order by mem_name";

		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String memId = rs.getString("mem_id");
			String memPw = rs.getString("mem_pw");
			String memName = rs.getString("mem_name");
			String memPhone = rs.getString("mem_phone");
			String memEmail = rs.getString("mem_email");

			MemberDTO md = new MemberDTO(memId, memPw, memName, memPhone, memEmail);
			marray.add(md);
		}
		return marray;
	} // getAllinfo-end

	// 회원 삭제
	public boolean delete(String memId) throws SQLException {
		String sql = "delete from member where mem_id=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		pstmt.executeUpdate();

		return true;
	} // delete-end

	// 제품 상세설명 전체 조회
	public ArrayList<DetailDTO> getAll() throws SQLException {
		ArrayList<DetailDTO> darray = new ArrayList<DetailDTO>();
		String sql = "select * from detail order by detail_id desc";

		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int detId = rs.getInt("detail_id");
			int sangId = rs.getInt("sang_id");
			String detName = rs.getString("detail_name");
			String detPipath1 = rs.getString("detail_pipath1");
			String detPipath2 = rs.getString("detail_pipath2");
			String detPipath3 = rs.getString("detail_pipath3");
			String detPipath4 = rs.getString("detail_pipath4");
			String detDate = rs.getString("detail_date");

			DetailDTO dd = new DetailDTO(detId, sangId, detName, detPipath1, detPipath2, detPipath3, detPipath4,
					detDate);
			darray.add(dd);
		}
		return darray;
	} // getAllinfo-end

	// 제품 상세설명 한개 조회
	public DetailDTO detailSearchOne(int detId2) throws SQLException {
		DetailDTO det = null;
		String sql = "select * from detail where detail_id = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, detId2);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			int detId = rs.getInt("detail_id");
			int sangId = rs.getInt("sang_id");
			String detName = rs.getString("detail_name");
			String detPipath1 = rs.getString("detail_pipath1");
			String detPipath2 = rs.getString("detail_pipath2");
			String detPipath3 = rs.getString("detail_pipath3");
			String detPipath4 = rs.getString("detail_pipath4");
			String detDate = rs.getString("detail_date");

			det = new DetailDTO(detId, sangId, detName, detPipath1, detPipath2, detPipath3, detPipath4, detDate);
		} else {
			det = null;
		}
		return det;
	}

	// 제품 상세정보 입력
	public boolean detailInsert(int sangId, String detName, String detPipath1, String detPipath2, String detPipath3,
			String detPipath4) throws SQLException {

		String sql = "insert into detail" + " values (detailseq.NEXTVAL, ?, ?, ?, ?, ?, ?, sysdate)";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		pstmt.setString(2, detName);
		pstmt.setString(3, detPipath1);
		pstmt.setString(4, detPipath2);
		pstmt.setString(5, detPipath3);
		pstmt.setString(6, detPipath4);

		pstmt.executeUpdate();
		return true;

	}

	// 제품 상세설명 수정
	public boolean detailUpdate(String detName1, String detPipath11, String detPipath22, String detPipath33,
			String detPipath44, int detId1) throws SQLException {

		String sql = "update detail"
				+ " set detail_name= ?, detail_pipath1= ?, detail_pipath2= ?, detail_pipath3= ?, detail_pipath4= ?, detail_date = sysdate"
				+ " where detail_id = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, detName1);
		pstmt.setString(2, detPipath11);
		pstmt.setString(3, detPipath22);
		pstmt.setString(4, detPipath33);
		pstmt.setString(5, detPipath44);
		pstmt.setInt(6, detId1);

		pstmt.executeUpdate();
		return true;

	}

	// 제품 상세설명 삭제
	public boolean deleteDetail(int detId) throws SQLException {
		String sql = "delete from detail where detail_id=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, detId);
		pstmt.executeUpdate();

		return true;
	}

	//전체상품조회, 상품갯수가 0인것 제외
			public ArrayList<SangpumDTO> getAllSangnotZ() throws SQLException {
				ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
				String sql="select * from sangpum where sang_amount != 0order by sang_amount desc";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					int sangId=rs.getInt("sang_id");
					int cateId=rs.getInt("cate_id");
					String sangName=rs.getString("sang_name");
					int sangPrice=rs.getInt("sang_price");
					double sangStar=rs.getDouble("sang_star");
					String sangPipath=rs.getString("sang_pipath");
					int sangAmount=rs.getInt("sang_amount");
					Date sangDate = rs.getDate("sang_date");						
					SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
					sarry.add(sdto);		
				}
				return sarry;
			}
		
		// 정렬 조회, 상품갯수가 0인것 제외	
		public ArrayList<SangpumDTO> searchSangOrder(String order) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();		
			String sql = "select * from sangpum where sang_amount != 0 order by ";
			if(order.equals("starup"))	sql+="sang_star desc";
			else if(order.equals("stardown"))	sql+="sang_star";
			else if(order.equals("priceup"))	sql+="sang_price desc";
			else	sql+="sang_price";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				int cateId=rs.getInt("cate_id");
				String sangName=rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;
		}
		
		//이름으로 조회
		public ArrayList<SangpumDTO> searchSangByName(String searchName) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql="select * from sangpum where lower(sang_name) like '%' ||lower(?)|| '%' order by  sang_date desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, searchName);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				int cateId=rs.getInt("cate_id");
				String sangName = rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;		
		}
		
		//이름으로 조회	, 상품갯수가 0인것 제외
				public ArrayList<SangpumDTO> searchSangByNameZ(String searchName) throws SQLException {
					ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
					String sql="select * from sangpum where sang_amount != 0 and lower(sang_name) like '%' ||lower(?)|| '%' order by  sang_date desc";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, searchName);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						int sangId=rs.getInt("sang_id");
						int cateId=rs.getInt("cate_id");
						String sangName = rs.getString("sang_name");
						int sangPrice=rs.getInt("sang_price");
						double sangStar=rs.getDouble("sang_star");
						String sangPipath=rs.getString("sang_pipath");
						int sangAmount=rs.getInt("sang_amount");
						Date sangDate = rs.getDate("sang_date");						
						SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
						sarry.add(sdto);		
					}
					return sarry;		
				}
		
		//이름과 정렬 조회	, 상품갯수가 0인것 제외
		public ArrayList<SangpumDTO> searchSangByNameOrder(String searchName, String order) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql = "select * from sangpum where sang_amount != 0 and lower(sang_name) like '%' ||lower(?)|| '%' order by ";
			if(order.equals("starup"))	sql+="sang_star desc";
			else if(order.equals("stardown"))	sql+="sang_star";
			else if(order.equals("priceup"))	sql+="sang_price desc";
			else	sql+="sang_price";

			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, searchName);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				int cateId=rs.getInt("cate_id");
				String sangName = rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;		
		}
		
		//카테고리로 상품조회, 상품갯수가 0인것 제외
		public ArrayList<SangpumDTO> searchSangByCateZ(int cateId) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql="select * from sangpum  where sang_amount != 0 and cate_id =? order by sang_date desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cateId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				String sangName=rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");
							
				SangpumDTO sdto= new SangpumDTO(sangId, cateId, sangName, sangPrice, sangStar, sangPipath, sangAmount, sangDate);		
				sarry.add(sdto);		
			}
			return sarry;
		}
		
		//카데고리와 이름 조회, 상품갯수가 0인것 제외
		public ArrayList<SangpumDTO> searchSangByCateNameZ(int cateId, String searchName) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql="select * from sangpum where sang_amount != 0 and cate_id = ? and lower(sang_name) like '%' ||lower(?)|| '%' order by sang_date desc";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cateId);
			pstmt.setString(2, searchName);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				String sangName=rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;
		}
		
		//카테고리와 정렬 조회, 상품갯수가 0인것 제외
		public ArrayList<SangpumDTO> searchSangByCateOrder(int cateId, String order) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql="select * from sangpum where cate_id = ? and sang_amount != 0 order by ";
			if(order.equals("starup"))	sql+="sang_star desc";
			else if(order.equals("stardown"))	sql+="sang_star";
			else if(order.equals("priceup"))	sql+="sang_price desc";
			else	sql+="sang_price";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cateId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				String sangName=rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;		
		}
		
		// 카테고리, 이름, 정렬 조회, 상품갯수가 0인것 제외
		public ArrayList<SangpumDTO> searchSangByCateNameOrder(int cateId, String searchName ,String order) throws SQLException {
			ArrayList<SangpumDTO> sarry = new ArrayList<SangpumDTO>();
			String sql = "select * from sangpum where cate_id = ? and sang_amount != 0 and lower(sang_name) like '%' ||lower(?)|| '%' order by ";
			if(order.equals("starup"))	sql+="sang_star desc";
			else if(order.equals("stardown"))	sql+="sang_star";
			else if(order.equals("priceup"))	sql+="sang_price desc";
			else	sql+="sang_price";

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cateId);
			pstmt.setString(2, searchName);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int sangId=rs.getInt("sang_id");
				String sangName = rs.getString("sang_name");
				int sangPrice=rs.getInt("sang_price");
				double sangStar=rs.getDouble("sang_star");
				String sangPipath=rs.getString("sang_pipath");
				int sangAmount=rs.getInt("sang_amount");
				Date sangDate = rs.getDate("sang_date");						
				SangpumDTO sdto= new SangpumDTO(sangId,cateId,sangName,sangPrice,sangStar,sangPipath,sangAmount,sangDate);
				sarry.add(sdto);		
			}
			return sarry;
		}
	
	// 제품 상세설명 한개 조회(상품아이디로)
	public DetailDTO findDetailOne(int sangId) throws SQLException {
		DetailDTO sd = null;
		String sql = "select * from detail where sang_id = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			int detId = rs.getInt("detail_id");
			String detName = rs.getString("detail_name");
			String detPipath1 = rs.getString("detail_pipath1");
			String detPipath2 = rs.getString("detail_pipath2");
			String detPipath3 = rs.getString("detail_pipath3");
			String detPipath4 = rs.getString("detail_pipath4");
			String detDate = rs.getString("detail_date");

			sd = new DetailDTO(detId, sangId, detName, detPipath1, detPipath2, detPipath3, detPipath4, detDate);
		} else {
			sd = null;
		}
		return sd;
	}

	// 메인qna 전체조회
	public ArrayList<QnaDTO> getAllQnaMain() throws SQLException {
		ArrayList<QnaDTO> qmarry = new ArrayList<QnaDTO>();
		String sql = "select * from qna where depth=0 order by qna_date DESC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int qnaId = rs.getInt("qna_id");
			String memId = rs.getString("mem_id");
			int sangId = rs.getInt("sang_id");
			int depth = rs.getInt("depth");
			int bunId = rs.getInt("bundle_id");
			int bunOrder = rs.getInt("bundle_order");
			String qnaContent = rs.getString("qna_content");
			Date qnaDate = rs.getDate("qna_date");
			QnaDTO qdto = new QnaDTO(qnaId, memId, sangId, depth, bunId, bunOrder, qnaContent, qnaDate);
			qmarry.add(qdto);
		}
		return qmarry;
	}

	// 상품별 메인qna 전체조회
	public ArrayList<QnaDTO> searchQnaBySang(int sangId) throws SQLException {
		ArrayList<QnaDTO> qarry = new ArrayList<QnaDTO>();
		String sql = "select * from qna where sang_id =? and depth=0 order by qna_date DESC";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int qnaId = rs.getInt("qna_id");
			String memId = rs.getString("mem_id");
			int depth = rs.getInt("depth");
			int bunId = rs.getInt("bundle_id");
			int bunOrder = rs.getInt("bundle_order");
			String qnaContent = rs.getString("qna_content");
			Date qnaDate = rs.getDate("qna_date");
			QnaDTO qdto = new QnaDTO(qnaId, memId, sangId, depth, bunId, bunOrder, qnaContent, qnaDate);
			qarry.add(qdto);
		}
		return qarry;
	}

	// 메인 qna에 대한 대댓 전체 조회
	public ArrayList<QnaDTO> getAllQnaSub(int bunId) throws SQLException {
		ArrayList<QnaDTO> qsarry = new ArrayList<QnaDTO>();
		String sql = "select * from qna where depth=1 and bundle_id=? order by qna_date";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bunId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int qnaId = rs.getInt("qna_id");
			String memId = rs.getString("mem_id");
			int sangId = rs.getInt("sang_id");
			int depth = rs.getInt("depth");
			int bunOrder = rs.getInt("bundle_order");
			String qnaContent = rs.getString("qna_content");
			Date qnaDate = rs.getDate("qna_date");
			QnaDTO qdto = new QnaDTO(qnaId, memId, sangId, depth, bunId, bunOrder, qnaContent, qnaDate);
			qsarry.add(qdto);
		}
		return qsarry;
	}

	// 회원이 작성한 문의글 조회
	public ArrayList<QnaDTO> getAllMyQnaMain(String memId) throws SQLException {
		ArrayList<QnaDTO> qsarry = new ArrayList<QnaDTO>();
		String sql = "select * from qna where depth=0 and mem_id=? order by qna_date DESC";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int qnaId = rs.getInt("qna_id");
			int sangId = rs.getInt("sang_id");
			int depth = rs.getInt("depth");
			int bunId = rs.getInt("bundle_id");
			int bunOrder = rs.getInt("bundle_order");
			String qnaContent = rs.getString("qna_content");
			Date qnaDate = rs.getDate("qna_date");
			QnaDTO qdto = new QnaDTO(qnaId, memId, sangId, depth, bunId, bunOrder, qnaContent, qnaDate);
			qsarry.add(qdto);
		}
		return qsarry;
	}

	// qnaSub 입력
	public boolean insertQnaSub(QnaDTO qdto) throws SQLException {
		String sql = "insert into qna(qna_id,mem_id,sang_id,depth,bundle_id,bundle_order,qna_content) values(qnaseq.NEXTVAL, ?, ?, ?, ?, bundle_seq.NEXTVAL,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getMemId());
			pstmt.setInt(2, qdto.getSangId());
			pstmt.setInt(3, qdto.getDepth());
			pstmt.setInt(4, qdto.getBunId());
			pstmt.setString(5, qdto.getQnaContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert error");
			return false;
		}
		return true;
	}

	// qnaMain 입력
	public boolean insertQnaMain(QnaDTO qdto) throws SQLException {
		String sql = "insert into qna(qna_id,mem_id,sang_id,depth,bundle_id,bundle_order,qna_content) values(qnaseq.NEXTVAL, ?, ?, ?, qnaseq.CURRVAL, bundle_seq.NEXTVAL,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getMemId());
			pstmt.setInt(2, qdto.getSangId());
			pstmt.setInt(3, qdto.getDepth());
			pstmt.setString(4, qdto.getQnaContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert error");
			return false;
		}
		return true;
	}

	// qna 서브댓 한개 삭제
	public boolean deleteQnaSub(int qnaId) {
		String sql = "delete from qna where qna_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnaId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}

	// 메인 댓과 해당하는 서브댓 삭제
	public boolean deleteQnaMain(int bunId) {
		String sql = "delete from qna where bundle_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bunId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}

	// 회원이 주문한 구입목록 조회 - orderlist 존재시
	public ArrayList<GuipDTO> getAllGuipByMemExorder(String memId) throws SQLException {
		ArrayList<GuipDTO> garray = new ArrayList<GuipDTO>();
		String sql = "select * from guip where mem_id = ? and guip_id in (select guip_id from orderlist) order by guip_date DESC";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int guipId = rs.getInt("guip_id");
			int guipTotal = rs.getInt("guip_total");
			String address = rs.getString("address");
			String zipcode = rs.getString("zipcode");
			String address2 = rs.getString("address2");
			Date guipDate = rs.getDate("guip_date");
			GuipDTO gdto = new GuipDTO(guipId, memId, guipTotal, address, zipcode, address2, guipDate);
			garray.add(gdto);
		}
		return garray;
	}

	// 회원이 주문한 구입목록 조회
	public ArrayList<OrderListDTO> getAllOrderByGuip(int guipId) throws SQLException {
		ArrayList<OrderListDTO> oarray = new ArrayList<OrderListDTO>();
		String sql = "select * from orderlist where guip_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, guipId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int orderId = rs.getInt("order_id");
			int sangId = rs.getInt("sang_id");
			String osname = rs.getString("order_sang_name");
			int oscnt = rs.getInt("order_sang_count");
			int osprice = rs.getInt("order_sang_price");
			OrderListDTO odto = new OrderListDTO(orderId, sangId, guipId, osname, oscnt, osprice);
			oarray.add(odto);
		}
		return oarray;
	}
	// 회원이 주문한 구입목록 이름 조회
	public ArrayList<OrderListDTO> searchOrderBySNameByGuip(int guipId, String sangName) throws SQLException {
		ArrayList<OrderListDTO> oarray = new ArrayList<OrderListDTO>();
		String sql = "select * from orderlist where guip_id = ? and order_sang_name like '%' ||?|| '%' or guip_id = (case when not (select guip_id from orderlist where guip_id = ? and order_sang_name like '%' ||?|| '%') is null then ? else 0 end)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, guipId);
		pstmt.setString(2, sangName);
		pstmt.setInt(3, guipId);
		pstmt.setString(4, sangName);
		pstmt.setInt(5, guipId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int orderId = rs.getInt("order_id");
			int sangId = rs.getInt("sang_id");
			String osname = rs.getString("order_sang_name");
			int oscnt = rs.getInt("order_sang_count");
			int osprice = rs.getInt("order_sang_price");
				OrderListDTO odto = new OrderListDTO(orderId, sangId, guipId, osname, oscnt, osprice);
				oarray.add(odto);
			}
		return oarray;
	}	
	//주문번호로 리뷰 조회
	public ReviewDTO getAllReByOrder(int orderId) throws SQLException {
		ReviewDTO rdto = null;
		String sql = "select * from review where order_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int revId = rs.getInt("review_id");
			String memId = rs.getString("mem_id");
			int sangId = rs.getInt("sang_id");
			int revStar = rs.getInt("review_star");
			String revPipath1 = rs.getString("review_pipath1");
			String revPipath2 = rs.getString("review_pipath2");
			String revPipath3 = rs.getString("review_pipath3");
			String revContent = rs.getString("review_content");
			Date RevDate = rs.getDate("review_date");
			rdto = new ReviewDTO(revId, memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3, revContent, RevDate);
		}
		return rdto;
	}
	
	public boolean myOrderDelete(int guipId) {
		String sql = "delete from orderlist where guip_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, guipId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}
	// 장바구니 회원별 상품개수 구하기
	public int countCart(String memId) throws SQLException {
		int cnt=0;
		String sql = "select count(*) as cnt from cart where mem_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");			
		}
		return cnt;
	}
	// 장바구니에 이미 담긴 상품의 최대수량 확인 후 추가
		public boolean cartPlus(int sangId, String memId, int sangCount) throws SQLException {
			String sql = "update cart set sang_count ="
				+ " case when (select sang_amount from sangpum where sang_id = ?)"
					+ " > (select sang_count from cart where sang_id = ? and mem_id = ?)"
				+ " then sang_count + ?"
					+ " else (select sang_amount from sangpum where sang_id = ? )"
				+ " end where mem_id = ? and sang_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangId);
			pstmt.setInt(2, sangId);
			pstmt.setString(3, memId);
			pstmt.setInt(4, sangCount);
			pstmt.setInt(5, sangId);
			pstmt.setString(6, memId);
			pstmt.setInt(7, sangId);
			
			pstmt.executeUpdate();
			return true;
		}
	//상품존재여부 확인
	public int findSangCnt(int sangId) throws SQLException {
		int cnt=0;
		String sql = "select sang_amount as cnt from sangpum where sang_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");			
		}
		return cnt;
	}
	//장바구니에 상품담기
	public boolean cartInsert(String memId, int sangId, int sangCount) throws SQLException {
		String sql = "insert into cart values(cartseq.nextval, ?, ?, ?)";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		pstmt.setInt(2, sangId);
		pstmt.setInt(3, sangCount);
		
		pstmt.executeUpdate();
		
		return true;
	}
	//장바구니에 상품여부 확인
	public int findCartBySang(int sangId, String memId) throws SQLException {
		int cnt=0;
		String sql = "select count(*) as cnt from cart where sang_id = ? and mem_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		pstmt.setString(2, memId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");			
		}
		return cnt;
	}
	// 장바구니에 존재하는 상품 담기
	public boolean cartExUpdate(String memId, int sangId, int sangCount, int scnt) throws SQLException {
		String sql = "update cart set sang_count = case when ? > (select sang_count from cart where sang_id=? and mem_id=?) "
				+ "then sang_count + ? else ? end where mem_id = ? and sang_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, scnt);
		pstmt.setInt(2, sangId);
		pstmt.setString(3, memId);
		pstmt.setInt(4, sangCount);
		pstmt.setInt(5, scnt);
		pstmt.setString(6, memId);
		pstmt.setInt(7, sangId);		
		pstmt.executeUpdate();
		
		return true;
	}
	//멤버의 카트조회
	public ArrayList<CartDTO> getAllCart(String memId) throws SQLException {
		ArrayList<CartDTO> carray = new ArrayList<CartDTO>();
		String sql = "select * from cart where mem_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int cartId = rs.getInt("cart_id");
			int sangId = rs.getInt("sang_id");
			int sangCnt = rs.getInt("sang_count");
			CartDTO cdto = new CartDTO(cartId, memId, sangId, sangCnt);
			carray.add(cdto);
		}
		return carray;
	}
	//장바구니 삭제
	public boolean cartDel(String memId, int sangId) throws SQLException {
		String sql = "delete from cart where mem_id=? and sang_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setInt(2, sangId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}	
	//장바구니 수량 업데이트
	public boolean cartUpdate(String memId, int sangId, int sangCnt) throws SQLException {
		String sql = "update cart set sang_count=? where mem_id=? and sang_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangCnt);
			pstmt.setString(2, memId);
			pstmt.setInt(3, sangId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	//장바구니 한개 찾기
	public CartDTO findCart(int sangId, String memId) throws SQLException {
		CartDTO cdto = null;
		String sql = "select * from cart where sang_id = ? and mem_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		pstmt.setString(2, memId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			int cartId = rs.getInt("cart_id");	
			int sangCnt = rs.getInt("sang_count");		
			cdto = new CartDTO(cartId,memId,sangId,sangCnt);
		}
		return cdto;
	}
	//장바구니 한개 찾기
	public CartDTO findCartByCart(int cartId) throws SQLException {
		CartDTO cdto = null;
		String sql = "select * from cart where cart_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cartId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String memId = rs.getString("mem_id");
			int sangId = rs.getInt("sang_id");	
			int sangCnt = rs.getInt("sang_count");		
			cdto = new CartDTO(cartId,memId,sangId,sangCnt);
		}
		return cdto;
	}
	//상품리뷰 조회
	public ArrayList<ReviewDTO> getAllReviewBySang(int sangId, String star) throws SQLException {
		ArrayList<ReviewDTO> rarray = new ArrayList<ReviewDTO>();
		String sql = "select * from review where sang_id = ? order by review_star ";
		if(star.equals("up")) sql += "desc";
		else sql +="asc";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sangId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int revId = rs.getInt("review_id");
			String memId = rs.getString("mem_id");
			int orderId = rs.getInt("order_id");
			int revStar = rs.getInt("review_star");
			String revPipath1 = rs.getString("review_pipath1");
			String revPipath2 = rs.getString("review_pipath2");
			String revPipath3 = rs.getString("review_pipath3");
			String revContent = rs.getString("review_content");
			Date revDate = rs.getDate("review_date");
			ReviewDTO rdto = new ReviewDTO(revId, memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3, revContent, revDate);
			rarray.add(rdto);
		}
		return rarray;
	}	
	
	//구입번호구하기
	public int getGuipId() throws SQLException {
		int guipId = 0;
		String sql = "SELECT guipseq.NEXTVAL as guip_id  FROM DUAL";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			guipId = rs.getInt("guip_id");
		}
		return guipId;
	}
	//오더리스트번호구하기
	public int getOrderId() throws SQLException {
		int orderId = 0;
		String sql = "SELECT orderlistseq.NEXTVAL as order_id  FROM DUAL";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			orderId = rs.getInt("order_id");
		}
		return orderId;
	}
	public MemberDTO findMem(String memId) throws SQLException {
		MemberDTO mdto = null;
		String sql = "select * from member where mem_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String memPw = rs.getString("mem_pw");
			String memName = rs.getString("mem_name");
			String memPhone = rs.getString("mem_phone");
			String memEmail = rs.getString("mem_email");
			mdto = new MemberDTO(memId,memPw,memName,memPhone,memEmail);
		}
		return mdto;
	}
	// 구매후기 작성
		public boolean reviewInsert(String memId, int sangId, int orderId, int revStar, String revPipath1,
				String revPipath2, String revPipath3, String revContent) throws SQLException {
			String sql = "insert into review values(reviewseq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setInt(2, sangId);
			pstmt.setInt(3, orderId);
			pstmt.setInt(4, revStar);
			pstmt.setString(5, revPipath1);
			pstmt.setString(6, revPipath2);
			pstmt.setString(7, revPipath3);
			pstmt.setString(8, revContent);

			pstmt.executeUpdate();

			return true;
		}

		// 구매후기 수정
		public boolean reviewUpdate(int revStar1, String revPipath11, String revPipath22, String revPipath33,
				String revContent1, int revId1) throws SQLException {
			String sql = "update review"
					+ " set review_star = ?, review_pipath1 = ?, review_pipath2 = ?, review_pipath3 = ?, review_content = ?, review_date = sysdate"
					+ " where review_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, revStar1);
			pstmt.setString(2, revPipath11);
			pstmt.setString(3, revPipath22);
			pstmt.setString(4, revPipath33);
			pstmt.setString(5, revContent1);
			pstmt.setInt(6, revId1);

			pstmt.executeUpdate();

			return true;
		}

		// 구매후기 삭제
		public boolean reviewDelete(int revId) throws SQLException {
			String sql = "delete from review where review_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, revId);
			pstmt.executeUpdate();

			return true;
		}
		// 상품 별점 업데이트
		public boolean updateSangStar(int sangId) throws SQLException {
			String sql = "update sangpum set sang_star=(select round(avg(review_star),1) from review where sang_id=?) where sang_id=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangId);
			pstmt.setInt(2, sangId);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}		
		// 리뷰 전체 조회
		public ArrayList<ReviewDTO> getRevInfos(String memId) throws SQLException {
			ArrayList<ReviewDTO> rarray = new ArrayList<ReviewDTO>();
			
			String sql = "select * from review where mem_id =? order by review_id desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,memId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int revId = rs.getInt("review_id");
				int sangId = rs.getInt("sang_id");
				int orderId = rs.getInt("order_id");
				int revStar = rs.getInt("review_star");
				String revPipath1 = rs.getString("review_pipath1");
				String revPipath2 = rs.getString("review_pipath2");
				String revPipath3 = rs.getString("review_pipath3");
				String revContent = rs.getString("review_content");
				Date revDate = rs.getDate("review_date");
				
				ReviewDTO rd = new ReviewDTO(revId, memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3, revContent, revDate);
				rarray.add(rd);
			}
			return rarray;
		}
		
		// 상품 아이디로 상품 이름 반환하기
		public String findSangName(int sangId) throws SQLException {
			String sangName = null;
			String sql = "select sang_name from sangpum where sang_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sangName = rs.getString("sang_name"); 
			}
			return sangName;
		}
		
		// 주문목록 한개 조회하여 입력 폼으로
		public OrderListDTO findOrderList(int orderId, int sangId) throws SQLException {
			OrderListDTO odto = new OrderListDTO();
			String sql = "select * from orderlist where order_id = ? and sang_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, sangId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int guipId = rs.getInt("guip_id");
				String orderSname = rs.getString("order_sang_name");
				int orderScnt = rs.getInt("order_sang_count");
				int orderSprice = rs.getInt("order_sang_price");	

				odto = new OrderListDTO(orderId, sangId, guipId, orderSname, orderScnt, orderSprice);
			} else {
				odto = null;
			}
			return odto;
		}
		
		// 리뷰 수정을 위한 리뷰 한개 검색
		public ReviewDTO findRev(int revId) throws SQLException {
			ReviewDTO rdto = new ReviewDTO();
			String sql = "select * from review where review_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, revId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memId = rs.getString("mem_id");
				int sangId = rs.getInt("sang_id");
				int orderId = rs.getInt("order_id");
				int revStar = rs.getInt("review_star");
				String revPipath1 = rs.getString("review_pipath1");
				String revPipath2 = rs.getString("review_pipath2");
				String revPipath3 = rs.getString("review_pipath3");
				String revContent = rs.getString("review_content");
				Date revDate = rs.getDate("review_date");
				
				rdto = new ReviewDTO(revId, memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3, revContent, revDate);
			} else {
				rdto = null;
			}
			return rdto;
		}
		// 리뷰 수정을 위한 리뷰 한개 검색
		public ReviewDTO findRevByOrder(int orderId) throws SQLException {
			ReviewDTO rdto = new ReviewDTO();
			String sql = "select * from review where order_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memId = rs.getString("mem_id");
				int sangId = rs.getInt("sang_id");
				int revId = rs.getInt("review_id");
				int revStar = rs.getInt("review_star");
				String revPipath1 = rs.getString("review_pipath1");
				String revPipath2 = rs.getString("review_pipath2");
				String revPipath3 = rs.getString("review_pipath3");
				String revContent = rs.getString("review_content");
				Date revDate = rs.getDate("review_date");
				
				rdto = new ReviewDTO(revId, memId, sangId, orderId, revStar, revPipath1, revPipath2, revPipath3, revContent, revDate);
			} else {
				rdto = null;
			}
			return rdto;
		}
		// 회원이 주문한 구입목록 조회 - orderlist 존재시
		public ArrayList<GuipDTO> getAllGuipByDate(String memId) throws SQLException {
			ArrayList<GuipDTO> garray = new ArrayList<GuipDTO>();
			String sql = "select * from guip where mem_id=? and to_char( guip_date, 'yyyymmdd' ) >= to_char( sysdate-7, 'yyyymmdd') order by guip_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int guipId = rs.getInt("guip_id");
				int guipTotal = rs.getInt("guip_total");
				String address = rs.getString("address");
				String zipcode = rs.getString("zipcode");
				String address2 = rs.getString("address2");
				Date guipDate = rs.getDate("guip_date");
				GuipDTO gdto = new GuipDTO(guipId, memId, guipTotal, address, zipcode, address2, guipDate);
				garray.add(gdto);
			}
			return garray;
		}
		//구입 insert
		public boolean insertGuip(int guipId, String memId, int guipTot,String address,String zipcode, String plus) throws SQLException {
			String sql = "insert into guip values(?, ?, ?, ?,?,?,sysdate)";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, guipId);
			pstmt.setString(2, memId);
			pstmt.setInt(3, guipTot);
			pstmt.setString(4, address);
			pstmt.setString(5, zipcode);
			pstmt.setString(6, plus);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		//오더 insert
		public boolean insertorder(int sangId, int guipId, String sangName,int sangCount,int sangPrice) throws SQLException {
			String sql = "insert into orderlist values(orderlistseq.nextval,?, ?, ?, ?,?)";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangId);
			pstmt.setInt(2, guipId);
			pstmt.setString(3, sangName);
			pstmt.setInt(4, sangCount);
			pstmt.setInt(5, sangPrice);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		//상품 수량 변경 -
		public boolean updateSangCountM(int sangId, int sangCount) throws SQLException {
			String sql = "update sangpum set sang_amount = sang_amount - ? where sang_id=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangCount);
			pstmt.setInt(2, sangId);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		//상품 수량 변경 +
		public boolean updateSangCountP(int sangId, int sangCount) throws SQLException {
			String sql = "update sangpum set sang_amount = sang_amount + ? where sang_id=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sangCount);
			pstmt.setInt(2, sangId);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		// 구입아이디로 오더리스트 조회
		public ArrayList<OrderListDTO> searchOrderByGuip(int guipId) throws SQLException {
			ArrayList<OrderListDTO> oarray = new ArrayList<OrderListDTO>();
			String sql = "select * from orderlist where guip_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, guipId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				int sangId = rs.getInt("sang_id");
				String osname = rs.getString("order_sang_name");
				int oscnt = rs.getInt("order_sang_count");
				int osprice = rs.getInt("order_sang_price");
					OrderListDTO odto = new OrderListDTO(orderId, sangId, guipId, osname, oscnt, osprice);
					oarray.add(odto);
				}
			return oarray;
		}
		//구입 삭제
		public boolean deleteGuip(int guipId) throws SQLException {
			String sql = "delete from guip where guip_id=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, guipId);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		// findCancle
	      
	      public CancleDTO findCancle(int guipId) throws SQLException {
	         String sql = "select * from cancle where guip_id=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, guipId);
	         rs = pstmt.executeQuery();
	         CancleDTO cdto = new CancleDTO();
	         
	         if(rs.next()) {
	            
	            String memId = rs.getString("mem_id");
	            int guipTotal = rs.getInt("guip_total");
	            Date cancleDate = rs.getDate("cancle_date");
	            
	            cdto = new CancleDTO(guipId, memId, guipTotal, cancleDate);
	            
	         }else {
	            cdto = null;
	         }
	         return cdto;
	      }		
			// 취소 테이블 조회
			public ArrayList<CancleDTO> searchCancle() throws SQLException {
				ArrayList<CancleDTO> carray = new ArrayList<CancleDTO>();
				String sql = "select * from cancle order by cancle_date desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int guipId = rs.getInt("guip_id");
					String memId = rs.getString("mem_id");
					int guipTotal = rs.getInt("guip_total");
					Date cancleDate = rs.getDate("cancle_date");
					CancleDTO cdto = new CancleDTO(guipId, memId, guipTotal, cancleDate);
					carray.add(cdto);
					}
				return carray;
			}
			public boolean insertCancle(int guipId, String memId,int guipTotal) throws SQLException {
		         
		         try {
		            String sql = "INSERT INTO cancle VALUES(?,?,?,sysdate)";
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, guipId);
		            pstmt.setString(2, memId);
		            pstmt.setInt(3, guipTotal);
		            pstmt.executeUpdate();
		            
		         } catch (Exception e) {
		            e.printStackTrace();
		         }
		         return true;
		      }
		      
		      public GuipDTO findGuip(int guipId) throws SQLException {
		         
		         String sql = "select * from guip where guip_id=?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setInt(1, guipId);
		         rs = pstmt.executeQuery();
		         GuipDTO gdto = new GuipDTO();
		         
		         if(rs.next()) {
		            
		            String memId = rs.getString("mem_id");
		            int guipTotal = rs.getInt("guip_total");
		            
		            gdto = new GuipDTO(guipId, memId, guipTotal,null,null,null,null);
		            
		         }else {
		            gdto = null;
		         }
		         return gdto;
		      }
				//취소 삭제
				public boolean deleteCancle(int guipId) throws SQLException {
					String sql = "delete from cancle where guip_id=?";
					try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, guipId);
					pstmt.executeUpdate();
					} catch (SQLException e) {
						return false;
					}
					return true;
				}
				// 취소 테이블 조회
				public ArrayList<CancleDTO> searchCancleById(String search) throws SQLException {
					ArrayList<CancleDTO> carray = new ArrayList<CancleDTO>();
					String sql = "select * from cancle where mem_id like '%' ||?|| '%' order by cancle_date desc";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						int guipId = rs.getInt("guip_id");
						String memId = rs.getString("mem_id");
						int guipTotal = rs.getInt("guip_total");
						Date cancleDate = rs.getDate("cancle_date");
						CancleDTO cdto = new CancleDTO(guipId, memId, guipTotal, cancleDate);
						carray.add(cdto);
						}
					return carray;
				}
}
