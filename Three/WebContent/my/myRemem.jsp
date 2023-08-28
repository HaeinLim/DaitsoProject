<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">




<link rel="stylesheet" href="./myRememCss.css">
<script src="../WEB-INF/lib/jquery-3.7.0.min.js"></script>
<script>
 function validation(){
	 
	 var rePw1 = /^[a-zA-Z0-9]{4,12}$/;
	 var rePw2 = /^[a-zA-Z0-9]{4,12}$/; 
	 var reEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	 
	 var getPw1 = document.getElementById("pw1").value;
	 var getPw2 = document.getElementById("pw2").value;
	 var getPhone = document.getElementById("memPhone").value;
	 var getEmail = document.getElementById("memEmail").value;
	 
	 if (getPw1 == ""){
		 alert("비밀번호를 입력하세요");
		 document.getElementById("pw1").focus();
		 return false;
	 } else if (getPw2 == ""){
		 alert("비밀번호 확인을 입력하세요");
		 document.getElementById("pw2").focus();
		 return false;
	 } else if (!rePw1.test(getPw1)){
		 alert("비밀번호는 영문과 숫자로 구성된 6글자 이상으로 조합하시오.");
		 document.getElementById("pw1").focus();
		 return false;
	 } else if (getPw1 != getPw2){
		 alert("비밀번호와 다릅니다");
		 document.getElementById("pw2").focus();
		 return false;
	 } else if (getPhone == ""){
		 alert("전화번호를 입력하세요");
		 document.getElementById("memPhone").focus();
		 return false;
	 } else if (getEmail == ""){
		 alert("이메일을 입력하세요");
		 document.getElementById("memEmail").focus();
		 return false;
	 } else if (!reEmail.test(getEmail)){
		 alert("이메일 형식을 지켜주세요");
		 document.getElementById("memEmail").focus();
		 return false;
	 }
	 
 
 }


 function deleteCheck() {	
		if(confirm('정말 삭제하시겠습니까?')==true) {
			alert("삭제되었습니다");
		} else {
			return false;
		}
 }

</script>
</head>
<body>
  	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
  <%
	String id = (String)request.getAttribute("id");
	String pw = (String)request.getAttribute("pw");
	String memName = (String)request.getAttribute("memName");
	String memPhone = (String)request.getAttribute("memPhone");
	String memEmail = (String)request.getAttribute("memEmail");
	
	
	System.out.println("여기는 remem:"+id);
	System.out.println("여기는 remem:"+pw);
	System.out.println("여기는 remem:"+memName);
	System.out.println("여기는 remem:"+memPhone);
	System.out.println("여기는 remem:"+memEmail);
  %>
  <section class="login">
    <div class="login_box">
      <div class="left">
      <div class="top_link">
		<c:choose>
			<c:when test="${sid == 'admin'}"><a href="/Three/admin/adPage.jsp">관리자 페이지</a></c:when>
			<c:otherwise><a href="myPage.do">마이 페이지</a></c:otherwise>           
		</c:choose>
		</div>
        <div class="contact">
          <form action="myUpdate.do?id=${id}" method="post" onsubmit="return validation()">
            <h3>정보 수정</h3>
            <input class="join" type="text" placeholder="아이디" name="id" id="id"  value="${id }" readonly >
            <input class="join" type="password" name="pw1" id="pw1" placeholder="비밀번호" required>
            <input class="join" type="password" name="pw2" id="pw2" placeholder="비밀번호 확인" required>
            <input class="join" type="text" name="memName" id="memName" placeholder="이름" value="${memName}" required>
            <input class="join" type="text" name="memPhone" id="memPhone"  placeholder="전화번호" value="${memPhone}" required>
            <input class="join" type="text" name="memEmail" id="memEmail" value="${memEmail}" placeholder="이메일">
            <button class="submit" onclick="validation()">Sign up</button>
          </form><br>
			<c:if test="${sid != 'admin'}">
          <form action="myDelete.do?id=${id}" method="post" onsubmit="return deleteCheck()">
			<input type="submit" name = "submit" value="회원탈퇴" >
		  </form>
			</c:if>
        </div>  
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