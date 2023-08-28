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

	<br>
	<div class="row">
		<div class="col-2"></div>
		<div class="col"></div>
		<div class="col-4">
			<div class="btn-group dropend">
				<a type="button" class="btn btn-outline-primary" href="adNewSang.do">새상품
					등록</a>
			</div>
		</div>
		<div class="col-3" style="font-size: 1.5em;">- 상품조회</div>
		<div class="col-2"></div>
	</div>

	<br>
	<div class="row">
		<div class="col-2"></div>
		<div class="col"></div>
		<div class="col-4">
			<button class="btn btn-outline-dark" type="button"
				data-bs-toggle="collapse" data-bs-target="#CollapseExample1"
				aria-expanded="false" aria-controls="CollapseExample1">
				카테고리</button>
		</div>
		<div class="col-3">
		<!-- 검색창 -->
		    <form action="adSearchSangByName.do" class="d-flex" role="search">
		    	<c:choose>
			    	<c:when test="${searchName != 'none'}">
			    		<input class="form-control me-2" type="search" aria-label="Search" name="searchName" value="${searchName}">
			    	</c:when>
			   		<c:otherwise>
			   			<input class="form-control me-2" type="search" aria-label="Search" name="searchName" placeholder="검색어를 입력하세요">
			   		</c:otherwise>
			   	</c:choose>
		    	<button class="btn btn-outline-success" type="submit">Search</button>
		    </form>	
		</div>
		<div class="col-2"></div>
	</div>

	<br>
	<div class="row">
		<div class="col-3"></div>

		<div class="col">
			<div class="collapse" id="CollapseExample1">
				<a type="button" class="btn btn-outline-dark" href="adSang.do">전체보기</a>
				<c:forEach var="i" items="${barray}" varStatus="status">
					<button type="button" class="btn btn-outline-dark dropdown-toggle"
						data-bs-toggle="dropdown" aria-expanded="false">${i.bigName}</button>
					<ul class="dropdown-menu">
						<c:forEach var="j" items="${carray}">
							<c:if test="${i.bigId == j.bigId}">
								<li><a class="dropdown-item"
									href="searchSangByCate.do?cateId=${j.cateId}">${j.cateName}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:forEach>
			</div>
		</div>


		<div class="col-3"></div>
	</div>

	<hr>
<c:choose>
	<c:when test="${not empty sarray}">
	<div class="row">
		<div class="col"></div>
		<div class="col-7">
			<table class="table table-hover" style="text-align: center;">
				<thead>
					<tr>
						<th scope="col">상품이름</th>
						<th scope="col">상품등록일</th>
						<th scope="col">재고수량</th>
						<th scope="col">상품가격</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${sarray}">
						<tr>
							<td>${i.sangName}</td>
							<td>${i.sangDate}</td>
							<td>${i.sangAmount}</td>
							<td>${i.sangPrice}</td>
							<td>
								<c:choose>
									<c:when test="${not empty exdet[i.sangId]}">
										<a class="btn btn-outline-secondary" href="searchOne.do?detId=${exdet[i.sangId]}&sangId=${i.sangId}" role="button">상품상세수정</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-outline-secondary" href="adTakeSName.do?sangId=${i.sangId}" role="button">상품상세입력</a>
									</c:otherwise>
								</c:choose>
								<a class="btn btn-outline-secondary" href="adReSang.do?sangId=${i.sangId}" role="button">상품수정</a></td>
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
					<td class="td2"><br><h3>해당하는 상품이 없습니다</h3><h5>다른 검색어를 입력하시거나 철자와 띄어쓰기를 확인하세요</h5><br></td>
					<td class="td3"></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
	<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>
	
	<jsp:include page="../footer.jsp" />

</body>
</html>