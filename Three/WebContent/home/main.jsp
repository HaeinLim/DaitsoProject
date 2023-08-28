<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>다있소</title>
<style type="text/css">
	a:hover, a:visited, a:active, a:link {
		text-decoration : none;
		color : black;
	}
	.nn {
		font-size: 0.9rem;
		color:gray;
	}
	.pp {
		font-size: 1.3rem;
		color: red;
	}
	.ss {
		font-size: 1.2rem;
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
	.card-img-top {
		width:13rem;
		height:8rem;
	}
	.card {
		align-items: center;
	}
	.rate{background: url(/Three/img/bg.png)no-repeat; width:119px; height: 25px;position: relative;}
	.rate span{position: absolute;background: url(/Three/img/s.png);width: auto;height: 25px;}
</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


<%@include file="menuBar.jsp"%>

<br><br><br><br><br><br><br><br><br><br><br><br><br>




<c:if test="${not empty sarray1}">
	<div class="row row-cols-1 row-cols-md g-4 align-items-center">
		<div class="col-sm-2"></div>
		<c:forEach var="i" items="${sarray1}" varStatus="st">
			<div class="col-sm-2">
			<a href="sangDetail.do?sangId=${i.sangId}">
				<div class="card" >
					<img src="<%=request.getContextPath()%>/sang_imgs/${i.sangPipath}" class="card-img-top">
					<ul class="list-group list-group-flush">
						<li class="list-group-item"><span class="nn"> 상품명  :  </span><span class="ss"><b> ${i.sangName} </b></span></li>
						<li class="list-group-item"><span class="nn"> 가격  :  </span><span class="pp"><b> ${i.sangPrice}원 </b></span></li>
						<li class="list-group-item star"><div class="rate"><span style="width: ${i.sangStar*20}%"></span></div></li>
					</ul>
				</div>
				</a>
			</div>
		</c:forEach>
		<div class="col-sm-2"></div>
	</div>
</c:if>

<br>

<c:if test="${not empty sarray2}">
	<div class="row row-cols-1 row-cols-md g-4 align-items-center">
		<div class="col-sm-2"></div>
		<c:forEach var="i" items="${sarray2}" varStatus="st">
			<div class="col-sm-2">
			<a href="sangDetail.do?sangId=${i.sangId}">
				<div class="card">
					<img src="<%=request.getContextPath()%>/sang_imgs/${i.sangPipath}" class="card-img-top">
					<ul class="list-group list-group-flush">
						<li class="list-group-item"><span class="nn">상품명 : </span><span class="ss"><b>${i.sangName} </b></span></li>
						<li class="list-group-item"><span class="nn">가격 : </span><span class="pp"><b>${i.sangPrice}원 </b></span></li>
						<li class="list-group-item star"><div class="rate"><span style="width: ${i.sangStar*20}%"></span></div></li>
					</ul>				
				</div>
				</a>
			</div>				
		</c:forEach>
		<div class="col-sm-2"></div>
	</div>
</c:if>

<c:if test="${empty sarray1}">
	<table  class="no">
		<tr>
			<td class="td1"></td>
			<td class="td2"><br><h3>해당하는 상품이 없습니다</h3><h5>다른 검색어를 입력하시거나 철자와 띄어쓰기를 확인하세요</h5><br></td>
			<td class="td3"></td>
		</tr>
		</table>
</c:if>

<br>
<br>

<!--<nav aria-label="Page navigation example">  -->
	<ul class="pagination justify-content-center">
		<c:if test="${nowPage == 1}">
			<li class="page-item disabled"><a class="page-link">&laquo;</a></li>
		</c:if>
		<c:if test="${nowPage != 1}">
			<li class="page-item"><a class="page-link"  href="main.do?nowPage=${nowPage-1}&cateId=${cateId}&order=${order}&ch=${ch}&searchName=${searchName}">&laquo;</a></li>
		</c:if>
	    <c:forEach var="i" items="${pageGruop}">
	    	<c:choose>
		    	<c:when test="${i == nowPage}">
		    		<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
		    	</c:when>
		    	<c:otherwise>
		    		<li class="page-item"><a class="page-link" href="main.do?nowPage=${i}&cateId=${cateId}&order=${order}&ch=${ch}searchName=${searchName}">${i}</a></li>
		    	</c:otherwise>
	    	</c:choose>
	    </c:forEach>
	    <c:if test="${nowPage == pageCnt}">
			<li class="page-item disabled"><a class="page-link">&raquo;</a></li>
		</c:if>
		<c:if test="${nowPage != pageCnt}">
			<li class="page-item"><a class="page-link" href="main.do?nowPage=${nowPage+1}&cateId=${cateId}&order=${order}&ch=${ch}&searchName=${searchName}">&raquo;</a></li>
		</c:if>
	</ul>

<br>


<jsp:include page="../footer.jsp"/>



</body>
</html>