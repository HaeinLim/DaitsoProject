<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	a {
		text-decoration: none;
	}
	.menut td a:link, table td a:hover, table td a:visited, table td a:active {
		color: white;
	}
	.menut {
		width: 100%; 
		background-color: black;
		text-align: center;
	}
	.menut td {
		position: sticky;
    	top: 0px;
	}
	.menut .td1 {
		width: 25%; 
	}
	.menut .td2 {
		width: 50%;		 
	}
	.menut .td3, .td4 {
		width: 25%; 	
	}
	.menut .td2 a {
		 font-size: 50px; 
	}
	.menut .td3 a, .td4 a{
		 font-size: 19px; 
	}
</style>	
</head>
<body>
<nav class="navbar navbar-dark bg-dark fixed-top">
	<!-- 첫번째 단 -->
	<table class="menut">
		<tr>
			<td class="td1"></td>
			<td class="td2"><a href="main.do"><b>DTO</b></a></td>
			<c:choose>
				<c:when test="${empty sid}">
					<td class="td3">
						<a href="/Three/home/login.jsp">로그인</a><span style="color: white;"> ㅣ </span><a href="/Three/home/join.jsp">회원가입</a>
					</td>
				</c:when>
				<c:when test="${sid == 'admin'}">
					<td class="td3">
						<a href="/Three/admin/adPage.jsp">관리자페이지</a><span style="color: white;"> ㅣ </span><a href="logout.do">로그아웃</a>     
					</td>
				</c:when>
				<c:otherwise>
					<td class="td4"><!-- session 값이 있을 시 -->
						<a href="myCart.do">장바구니</a><span class="badge text-bg-light"> ${cartCnt}</span><span style="color: white;"> ㅣ </span><a href="myPage.do">마이페이지</a><span style="color: white;"> ㅣ </span><a href="logout.do">로그아웃</a>     
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</nav>
</body>
</html>