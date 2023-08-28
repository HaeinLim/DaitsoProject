package com.three.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.three.DAO.ThreeDAO;
import com.three.DTO.BigDTO;
import com.three.DTO.CateDTO;
import com.three.DTO.SangpumDTO;
import com.three.controller.ThreeImpl;

public class AdNewSang implements ThreeImpl {

    @Override
    public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		ThreeDAO tdao = new ThreeDAO();
		
		ArrayList<BigDTO> barray = tdao.getAllBig();
		ArrayList<CateDTO> carray = tdao.getAllCate();
		
		request.setAttribute("barray", barray);
		request.setAttribute("carray", carray);
    }
}

