<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<title>마이페이지</title>
<style type="text/css">

</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
	
<jsp:include page="myPageBar.jsp"/>

<br><br>
<div class="row">
<div class="col"></div>
<div class="col">
<h4>${nick}님 안녕하세요!</h4>
<br>
<div class="list-group"  style="text-align: center;">
	<a type="button" class="list-group-item list-group-item-action" href="myCart.do">장바구니 <span class="badge text-bg-secondary"> ${cartCnt}</span></a>			
	<a type="button" class="list-group-item list-group-item-action" href="myOrder.do">내 주문목록보기</a>			
	<a type="button" class="list-group-item list-group-item-action" href="myQna.do">내 문의글 보기</a>				
	<a type="button" class="list-group-item list-group-item-action" href="myReview.do">내 리뷰 보기</a>
	<a type="button" href="myCancle.do" class="list-group-item list-group-item-action">결제 취소</a>
  	<a type="button" href="/Three/my/pwcheck.jsp" class="list-group-item list-group-item-action">회원정보 수정</a>
</div>
</div>
<div class="col"></div>
</div>
<br><br>
	

	
<jsp:include page="../footer.jsp"/>
</body>
</html>