<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../WEB-INF/lib/jquery-3.7.0.min.js"></script>
<script>

function sendCheckValue() {
	var chResult = document.getElementById("chResult").value;
	
	if (chResult=="N") {
		alert("다른 아이디를 입력해주세요.");
		window.close();
	}else {
		// 중복체크 결과인 idCheck 값을 전달
		window.opener.document.getElementById("idDuplication").value="idCheck";
		window.opener.document.getElementById("id").disabled=true;
		window.opener.document.getElementById("memId").value = document.getElementById("memId").value;
		window.close();
	}
	
}

</script>
</head>
<body>

<%
String id = (String)request.getAttribute("id");
System.out.println("여기는 아이디 체크"+ id);
System.out.println("여기는 아이디 체크"+ id);
System.out.println("여기는 아이디 체크"+ id);

%>
  
<div style="text-align: center">
  
  <h3>* 아이디 중복확인 * </h3> 
  <form name="checkIdForm">
		<c:choose>
			<c:when test="${resultCheck==0}">
				<p style="color: red">${message}</p>
				<input type="hidden" id="chResult" name="chResult" value="N"/>
			</c:when>
			<c:when test="${resultCheck==1}">
				<p style="color: blue">${message}</p>
				<input type="hidden" id="chResult" name="chResult" value="Y"/>
				<input type="hidden" id="memId" name="memId" value="${id}"/> 
				
			</c:when>
		</c:choose>

		<input type="button" onclick="window.close()" value="취소"/><br>
		<input type="button" onclick="sendCheckValue()" value="사용하기"/>

	</form>
    
</div>      
    
    


</body>
</html>