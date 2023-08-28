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
	text-decoration: none;
	color: black;
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
}
</style>
<title>상품 조회 관리</title>
</head>
<body>
	 <% String sid = (String)session.getAttribute("sid");
	 	if(!sid.equals("admin"))
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<jsp:include page="adPageBar.jsp" />

	<br><br>
	<div class="row">
		<div class="col-2"></div>
		<div class="col-3">
			<form action="searchCancle.do" class="d-flex" role="search">
		    	<c:choose>
			    	<c:when test="${searchName != 'none'}">
			    		<input class="form-control me-2" type="search" aria-label="Search" name="searchName" value="${searchName}">
			    	</c:when>
			   		<c:otherwise>
			   			<input class="form-control me-2" type="search" aria-label="Search" name="searchName" placeholder="검색어를 입력하세요">
			   		</c:otherwise>
			   	</c:choose>
			   	<input class="btn btn-outline-secondary" type="reset" value="취소">
		    	<input class="btn btn-outline-success" type="submit" value="검색">
		    </form>
		</div>
		<div class="col-2"><a class="btn btn-outline-secondary" href="adCancle.do">전체검색</a></div>
		<div class="col"></div>
		<div class="col-3" style="font-size: 1.5em;">- 취소 요청 조회</div>
		<div class="col"></div>
	</div>

	<hr>
<c:choose>
	<c:when test="${not empty carray}">
	<div class="row">
		<div class="col"></div>
		<div class="col-7">
			<table class="table table-hover" style="text-align: center;">
				<thead>
					<tr>
						<th scope="col">고객 아이디</th>
						<th scope="col">요청 금액</th>
						<th scope="col">요청날짜</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${carray}">
						<tr>
							<td>${i.memId}</td>
							<td>${i.guipTotal}</td>
							<td>${i.cancleDate}</td>
							<td>
								<form action="delGuip.do" method="post" id="cancle">
								<input type="hidden" name="guipId" value="${i.guipId}">
								<button type="button" class="btn btn-outline-secondary" onclick="cancle()">취소승인</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col"></div>
	</div>
		</c:when>
		<c:otherwise>
			<table class="no">
				<tr>
					<td class="td1"></td>
					<td class="td2"><br><h3>취소요청이 없습니다</h3></td>
					<td class="td3"></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
	<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>
	
	<jsp:include page="../footer.jsp" />
<script type="text/javascript">
function cancle() {
	if(window.confirm("취소승인을 진행하시겠습니까?")){
		document.getElementById('cancle').submit();
	}
	else {
		return false;
	}
}
</script>
</body>
</html>