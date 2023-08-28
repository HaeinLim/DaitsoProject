package com.three.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.three.controller.ThreeImpl;

public class LogOut implements ThreeImpl {

	@Override
	public void three(HttpServletRequest request, HttpServletResponse response) throws Exception {

	HttpSession session = request.getSession();
	if(session != null) {
		session.invalidate();
	}
	}
}
