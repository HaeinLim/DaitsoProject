package com.three.my;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.DAO.ThreeDAO;
import com.three.DTO.CartDTO;
import com.three.DTO.MemberDTO;
import com.three.DTO.OrderListDTO;
import com.three.DTO.QnaDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class MyGuip implements ThreeImpl {

   @Override
   public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
	   
	   
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      
      
      String memId = null;
      HttpSession session = request.getSession(false);
      if(session != null) {
         memId = (String)session.getAttribute("sid");
      }
      
      
      
      String [] sarr = request.getParameterValues("buy"); // sangId�뱾�쓽 諛곗뿴  
      ThreeDAO tdao = new ThreeDAO();
    
      MemberDTO mdto = tdao.findMem(memId);
      int total = 0;
      HashMap<Integer,SangpumDTO> sangMap = new HashMap<Integer,SangpumDTO>();
      CartDTO cdto = null; // cartId,memId,sangId,sangCnt
      ArrayList<CartDTO> cArray = new ArrayList<CartDTO>();
      int guipId = tdao.getGuipId();
      
      
      
      
      for(int i =0; i<sarr.length; i++) {
			int sangId = Integer.parseInt(sarr[i]);
	//		System.out.println(sangId);
			SangpumDTO sdto = tdao.findSang(sangId);
				// sangId, cateId, sangName, sangPrice, 
				// sangStar, sangPipath, sangAmount, sangDate
			sangMap.put(sangId, sdto); 

			cdto = tdao.findCart(sangId, memId);
			cArray.add(cdto);
			total += sdto.getSangPrice()*cdto.getSangCnt();
      }
      
      
      
      request.setAttribute("total", total);
      request.setAttribute("sangMap", sangMap);
      request.setAttribute("cArray", cArray);
      request.setAttribute("guipId", guipId);
      request.setAttribute("mdto", mdto);
      
 
   }
}