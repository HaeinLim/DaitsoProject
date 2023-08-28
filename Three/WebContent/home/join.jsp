<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">




<link rel="stylesheet" href="./joinCss.css">
<script src="../WEB-INF/lib/jquery-3.7.0.min.js"></script>
<script>
 function validation(){
	 
	 var rePw1 = /^[a-zA-Z0-9]{4,12}$/;
	 var rePw2 = /^[a-zA-Z0-9]{4,12}$/; 
	 var reEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	 
	 var getIdDupl = document.getElementById("idDuplication").value;
	 var getPw1 = document.getElementById("pw1").value;
	 var getPw2 = document.getElementById("pw2").value;
	 var getPhone = document.getElementById("memPhone").value;
	 var getEmail = document.getElementById("memEmail").value;
	 
	 if(getIdDupl != "idCheck"){
		 alert("아이디 중복체크를 해주세요");
		 document.getElementById("id").focus();
		 return false;
	 } else if (getPw1 == ""){
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


 function idCheck(){
	  var reId = /^[a-z0-9]{6,12}$/;
	  var getId = document.getElementById("id").value
	  if(getId == ""){
			 alert("아이디를 입력하세요");
			 document.getElementById("id").focus();
		}else if(!reId.test(getId)){
			 alert("아이디는 영어 소문자와 숫자로 구성된 6글자 이상으로 조합하시오.");
			 document.getElementById("id").focus();
		} else{
	  		window.open("idCheck.do?id="+getId, "", "width=600, height=500");
		}
	}

</script>
</head>
<body>
  

  <section class="login">
    <div class="login_box">
      <div class="left">
        <div class="top_link"><a href="main.do">Return Home</a></div>
        <div class="contact">
          <form action="join.do" method="post" onsubmit="return validation()">
            <h3>Create Account</h3>
            <input class="join" type="text" placeholder="아이디" name="id" id="id"  placeholder="아이디" >
            <input class="joinCheck" type="button" name = "idBnt" value="check" onclick="idCheck()">
            <input class="join" type="password" name="pw1" id="pw1" placeholder="비밀번호" required>
            <input class="join" type="password" name="pw2" id="pw2" placeholder="비밀번호 확인" required>
            <input class="join" type="text" name="memName" id="memName" placeholder="이름" required>
            <input class="join" type="text" name="memPhone" id="memPhone"  placeholder="전화번호"  required>
            <input class="join" type="text" name="memEmail" id="memEmail"  placeholder="이메일">
            <input type="hidden" id="idDuplication" name="idDuplication" value="idUncheck" />
            <input type="hidden" id="memId" name="memId" value="" />
            <button class="submit">Sign up</button>
          </form>
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