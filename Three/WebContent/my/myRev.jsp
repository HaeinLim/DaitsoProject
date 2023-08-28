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
<title>나의 리뷰 페이지</title>
<style>
a:hover, a:visited, a:active, a:link {
		text-decoration : none;
		color : black;
}
img.img-fluid {
	width: 80px;
	height: 60px;
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
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
}

.no .td3 {
	width: 20%;
}
</style>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<jsp:include page="myPageBar.jsp" />
	<br>
	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
	<div class="row">
		<div class="col"></div>
		<div class="col-6" style="font-size: 1.3em;">- ${nick} 님의 구매후기 보기</div>
		<div class="col"></div>
	</div>
	<br>
	<c:choose>
		<c:when test="${not empty rarray}">
			<c:forEach var="i" items="${rarray}">
				<form action="revSearchOne.do?sangId=${i.sangId}" method="post">
					<input type="hidden" name="revId" value="${i.revId }">
					<div class="row">
						<div class="col-3"></div>
						<div class=col>
							<div align=right>
								<input class="btn btn-outline-secondary" type="submit"
									value="리뷰 수정">
							</div>
							<a href="sangDetail.do?sangId=${i.sangId}">
							<div class="card mb-3">
								<div class="card-top">
									<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath1 }" class="img-fluid" alt="리뷰1"
										onerror="this.style.display='none'" onclick="window.open(this.src)"> 
									<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath2 }"
										class="img-fluid" alt="리뷰2" onerror="this.style.display='none'" onclick="window.open(this.src)"> 
									<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath3 }" class="img-fluid" alt="리뷰3"
										onerror="this.style.display='none'" onclick="window.open(this.src)">
								</div>
								<div class="card-body">
									<h6 class="card-title">
										<c:forEach var="j" begin="1" end="${i.revStar }">
								    		<img src="/Three/img/star.png">
								    	</c:forEach>
								    	<c:forEach var="k" begin="1" end="${5-i.revStar }">
								    		<img src="/Three/img/nostar.png">
								    	</c:forEach>
									</h6>
									<p class="card-text">
										<small class="text-muted">${rmap[i.sangId] }</small>
									</p>
									<p class="card-text">${i.revContent}</p>
									<%-- ${i.revContent} --%>
									<p class="card-text">
										<small class="text-muted">${i.revDate}</small>
									</p>
									<%-- ${i.revDate} --%>
								</div>
							</div>
							</a>
						</div>
						<div class="col-3"></div>
					</div>
				</form>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div style="height: 100px"></div>
			<table class="no">
				<tr>
					<td class="td1"></td>
					<td class="td2"><br>
					<h3>리뷰가 없습니다</h3>
						<br></td>
					<td class="td3"></td>
				</tr>
			</table>
			<br>
		</c:otherwise>
	</c:choose>

	<div style="position: fixed; bottom: 5px; right: 5px">
		<a href="#"><img src="/Three/img/goup.png"></a>
	</div>


	<jsp:include page="../footer.jsp" />

</body>
</html>