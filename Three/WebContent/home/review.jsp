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
<style type="text/css">
a:hover, a:visited, a:active, a:link {
		text-decoration : none;
		color : black;
}
.revImg {
	width: 80px;
	height: 60px;
}
.rate{background: url(/Three/img/nostar.png);width: 121px;height: 25px;position: relative;}
.rate span{position: absolute;background: url(/Three/img/star.png);width: auto;height: 25px;}
</style>
<title>Insert title here</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<br>
<c:choose>
<c:when test="${not empty rarray}">
<div class="row">
<div class="col-2"></div>
<div class="col">
<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
  <input type="radio" class="btn-check" name="revstar" id="revstar1" autocomplete="off" ${star == 'up' ? 'checked' : ''}>
  <label class="btn btn-outline-secondary" for="revstar1"><a href="sangDetail.do?sangId=${sangId}&star=${'up'}">별점높은순</a></label>

  <input type="radio" class="btn-check" name="revstar" id="revstar2" autocomplete="off" ${star == 'down' ? 'checked' : ''}>
  <label class="btn btn-outline-secondary" for="revstar2"><a href="sangDetail.do?sangId=${sangId}&star=${'down'}">별점낮은순</a></label>
</div>
<br><br>

<c:forEach var="i" items="${rarray}" varStatus="st">
<div class="card">
<c:if test="${not empty i.revPipath1 or not empty i.revPipath2 or not empty i.revPipath3}">
<div class="card-header">
<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath1}" class="revImg" alt="리뷰이미지" onerror="this.style.display='none'" onclick="window.open(this.src)">
<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath2}" class="revImg" alt="리뷰이미지" onerror="this.style.display='none'" onclick="window.open(this.src)">
<img src="<%=request.getContextPath()%>/rev_imgs/${i.revPipath3}" class="revImg" alt="리뷰이미지" onerror="this.style.display='none'" onclick="window.open(this.src)">
</div>
</c:if>
  <ul class="list-group list-group-flush">
    <li class="list-group-item" style="font-size: 0.8rem; color: gray;">${i.memId }</li>
    <li class="list-group-item">
    	<c:forEach var="j" begin="1" end="${i.revStar }">
    		<img src="/Three/img/star.png">
    	</c:forEach>
    	<c:forEach var="k" begin="1" end="${5-i.revStar }">
    		<img src="/Three/img/nostar.png">
    	</c:forEach>
    </li>
    <li class="list-group-item" style="font-size: 0.8rem; color: gray;">${i.revDate}</li>
  </ul>
  <c:if test="${not empty i.revContent}">
    <div class="card-body">
    <p class="card-text">${i.revContent}</p>
  </div>
  </c:if>
</div>
<c:if test="${fn:length(rarray) != st.count }"> <hr> </c:if>
</c:forEach>
</div>
<div class="col-2"></div>
</div>
	</c:when>
	<c:otherwise>
	<div class="qna">
		<br><h3>리뷰가 없습니다</h3><br>
	</div>
	</c:otherwise>
</c:choose>

</body>
</html>