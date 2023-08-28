<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<title>관리자 페이지</title>
<style type="text/css">
	a, a:link, a:hover, a:visited,a:active{
		text-decoration: none;
		color: balck;
		font-size: 1.2rem;
	}
</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

	 <% String sid = (String)session.getAttribute("sid");
	 	if(!sid.equals("admin"))
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<jsp:include page="adPageBar.jsp"/>

<br><br>
<div class="row">
<div class="col"></div>
<div class="col">
<br>
<div class="list-group"  style="text-align: center;">
  <a href="search.do" class="list-group-item list-group-item-action">회원조회</a>
  <a href="adSang.do" class="list-group-item list-group-item-action">상품조회</a>
  <a href="adQna.do" class="list-group-item list-group-item-action">문의글조회</a>
  <a href="adCancle.do" class="list-group-item list-group-item-action">취소요청관리</a>
  <a href="/Three/my/pwcheck.jsp" class="list-group-item list-group-item-action">관리자정보 수정</a>
</div>
</div>
<div class="col"></div>
</div>
<br><br>
	
	
	
	
<jsp:include page="../footer.jsp"/>
</body>
</html>