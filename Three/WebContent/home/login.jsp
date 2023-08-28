<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">


<link rel="stylesheet" href="./loginCss.css">


</head>
<body>




<% /* 세션이 존재하니? main 존재안하면 밑에 코드를 다시실행 */ 
	if (session.getAttribute("sid")!=null &&  session.getAttribute("spw") != null)
		response.sendRedirect("main.do");
	else {
		if(request.getAttribute("loginCh")== "nologin") {
			out.print("<script> alert('아이디 혹은 비밀번호 오류입니다 다시 입력하세요.');");
			out.println("</script>");
		}
	}
%>



  <section class="login">
    <div class="login_box">
      <div class="left">
        <div class="top_link"><a href="main.do">Return Home</a></div>
        <div class="contact">
          <form action="login.do" method="post">
            <h2>Login</h2>
             <input type="text" id="id" name="id" placeholder="아이디">
             <input type="password" id="pw" name="pw" placeholder="비밀번호">
             <a href="join.jsp">Sign Up</a>
            <button class="submit">LET'S GO</button>
          </form>
        </div>  
        <hr><hr><hr>
        <div><a href="/Three/home/join.jsp">Sign up</a></div> 
      </div>
      
      <div class="right">
        <div class="right-text">
          <h2>DAISSO</h2>
          <h5>Korean No.1 Market</h5>
        </div>
      </div>
    </div>
  </section>

</body>
</html>