<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">


<link rel="stylesheet" href="./pwCheckCss.css">


</head>
<body>

  <%
  session = request.getSession();
	// 서블릿에서 만들어진 세션(ses2)을 jsp의 session 내장객체에 넣는다

  String sid = (String)session.getAttribute("sid");
  String spw = (String)session.getAttribute("spw");
  

  %>

  <section class="login">
    <div class="login_box">
      <div class="left">
        <div class="top_link"><a href="main.do">Return Home</a></div>
        <div class="contact">
          <form action="pwCheck.do?id=${sid}" method="post">
            <h2>본인 인증</h2>
             <input type="text" id="id" name="id" placeholder="${sid }" readonly>
             <input type="password" id="pw" name="pw" placeholder="암호를 입력해주세요" required>
            <button class="submit">본인 인증</button>
          </form>
        </div>  
        <hr><hr><hr>
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