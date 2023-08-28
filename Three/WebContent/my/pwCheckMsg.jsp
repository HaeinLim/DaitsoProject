<%@page import="com.three.DTO.MemberDTO"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


  <%
  
  
  
  
  String id = (String)request.getAttribute("id");
  String pw = (String)request.getAttribute("pw");
  String memName = (String)request.getAttribute("memName");
  String memPhone = (String)request.getAttribute("memPhone");
  String memEmail = (String)request.getAttribute("memEmail");
  
  /* String id = (String)request.getAttribute("id");
  String pw = (String)request.getAttribute("pw"); */
  System.out.println("여기는 checkMsg:"+id);
  System.out.println("여기는 checkMsg:"+pw);
  System.out.println("여기는 checkMsg:"+memName);
  System.out.println("여기는 checkMsg:"+memPhone);
  System.out.println("여기는 checkMsg:"+memEmail);
  
  
  String str = null;
  
  
  
  if(id != ""){
	  	
	    out.print("<script> alert('본인인증 되셨습니다.');");
		out.println("</script>");	
	  	
		str = "my/myRemem.jsp";
		RequestDispatcher rd1 = request.getRequestDispatcher("../" + str);
		rd1.forward(request, response);
		
	}else {
		
		out.print("<script> alert('비밀번호가 일치하지 않습니다.');");
		out.print("history.back();");
		out.println("</script>");	
  		
  }
  
  %>
  
  
  
  
  
</body>
</html>