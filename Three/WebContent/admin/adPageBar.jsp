<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a {
		text-decoration: none;
	}
	.bar td a:link, .bar td a:hover, .bar td a:visited, .bar td a:active {
		color: white;
	}
	.bar {
		width: 100%; 
		background-color: black;
		text-align: center;
	}
	.bar td {
		position: sticky;
    	top: 0px;
	}
	.bar .td1, .bar .td3 {
		width: 25%; 
	}
	.bar .td2 {
		width: 50%;
		 
	}
	.bar .td2 a {
		 font-size: 50px; 
	}
	.bar .td1 a, .bar .td3 a {
		 font-size: 19px; 
	}
</style>
</head>
<body>

	<table class="bar">
		<tr>
			<td class="td1"><a class="btn btn-dark" href="main.do">메인</a></td>
			<td class="td2"><a href="/Three/admin/adPage.jsp">관리자 페이지</a></td>
			<td class="td3"><a href="logout.do">로그아웃</a></td>		
		</tr>
	</table>



</body>
</html>