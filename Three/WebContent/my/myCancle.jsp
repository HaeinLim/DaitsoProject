<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<style type="text/css">
	a:hover, a:visited, a:active, a:link {
	text-decoration : none;
		color : black;
	}
	.no {
		width: 100%; 
		text-align: center;
	}
	.no .td1 {
		width: 20%; 
	}
	.no .td2 {
		width: 60%;
		background-color: #DCDCDC;
		border-top-left-radius: 15px; border-top-right-radius: 15px;
        border-bottom-left-radius: 15px; border-bottom-right-radius: 15px;
	}
	.no .td3 {
		width: 20%; 	
	}
</style>
<title>주문 취소내역</title>
</head>
<body>
	<%
		if (session.getAttribute("sid") == null)
		out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");
	%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<jsp:include page="myPageBar.jsp" />

	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col-6" style="font-size: 1.3em;">- ${nick} 님의 주문페이지</div>
		<div class="col"></div>
	</div>
	<br>
	
	<div class="row">
		<div class="col"></div>
		<c:choose>
			<c:when test="${not empty garray }">
				<div class="col-6">
					<table class="table table-hover" style="text-align: center;">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">구입날짜</th>
								<th scope="col">주소</th>
								<th scope="col">구입합계</th>
								<th scope="col"></th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${garray}" varStatus="st">
								<tr>
									<th scope="row">${st.count}</th>
									<td>${i.guipDate}</td>
									<td>우편번호(${i.zipcode})<br>${i.address}${i.address2}</td>
									<td>${i.guipTotal}원</td>
									<td>
										<form action="reqCancle.do" method="post" id="${'cancle'}${st.count}">
											<input type="hidden" name="guipId" value="${i.guipId}">
											<button type="button" class="btn btn-outline-dark" onclick="reqCancle(${st.count})" ${empty cmap[i.guipId] ? '': 'disabled'}>결제취소</button>
										</form>
									</td>
									<td style="color: red;"><c:if test="${not empty cmap[i.guipId]}">결제취소 요청중</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<div style="height: 100px"></div>
				<table class="no">
					<tr>
						<td class="td1"></td>
						<td class="td2"><br><h3>취소내역이 없습니다</h3><br></td>
						<td class="td3"></td>
					</tr>
				</table>
				<br>
			</c:otherwise>
		</c:choose>
		<div class="col"></div>
	</div>

	<div style="position: fixed; bottom: 5px; right: 5px">
		<a href="#"><img src="/Three/img/goup.png"></a>
	</div>

	<jsp:include page="../footer.jsp" />

<script type="text/javascript">
function reqCancle(num) {
	if(window.confirm("취소요청을 진행하시겠습니까? 승인은 2~3일내에 진행됩니다")){
		 document.getElementById('cancle'+num).submit();
	}
	else {
		return false;
	}
}
</script>
</body>
</html>