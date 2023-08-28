<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery-3.7.0.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<title>나의 주문 목록</title>
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
</head>
<body>
	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<jsp:include page="myPageBar.jsp"/>

<br>
<div class="row">	
	<div class="col"></div>
	<div class="col-6" style="font-size: 1.3em;">- ${nick} 님의 주문페이지</div>	
	<div class="col"></div>
</div>
<br>
<div class="row">
<div class="col-4"></div>
<div class="col">
<a type="button" class="btn btn-outline-dark" href="myOrder.do">전체보기</a>
</div>
<div class="col-4">
	<form class="d-flex" role="search" style="width : 60%;" method="post" action="myOrderSearch.do">
        <input class="form-control me-2" type="search" placeholder="주문한 상품이름 검색" aria-label="Search" name="sangName" value="${sangName}">
        <button class="btn btn-outline-success" type="submit">Search</button>
	</form>
</div>
<div class="col-3"></div>
</div>
<c:choose>
	<c:when test="${not empty oamap}">
<div class="row">
	<div class="col"></div>
	<div class="col-5">
		<c:forEach var="i" items="${garray}" varStatus="status">
		<c:if test="${not empty oamap[i.guipId]}">
		<br>
		<div class="d-flex justify-content-between">
			<span>주문일자 : <span class="guipdate">${i.guipDate}</span></span>			
			<button type="button" class="btn-close"  data-bs-toggle="modal" data-bs-target="${'#exampleModal'}${status.count}" aria-label="Close"></button>
		</div>
		<table class="table table-hover" style="text-align: center;">
		  <thead>
		    <tr>
		    	<th scope="col">#</th>
		      <th scope="col">상품이름</th>
		      <th scope="col">상품갯수</th>
		      <th scope="col">상품가격(당)</th>
		      <th scope="col">상품 보기</th>
		      <th scope="col">장바구니</th>
		      <th scope="col">리뷰</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="j" items="${oamap[i.guipId]}" varStatus="st">
		 	<tr>
		      <th scope="row">${st.count}</th>
		      <td>${j.orderSname}</td>
		      <td>${j.orderScnt}(개)</td>
		      <td>${j.orderSprice}원</td>
		      <td><a type="button" class="btn btn-outline-secondary" href="sangDetail.do?sangId=${j.sangId}">이동</a></td>
		      <td><button type="button" class="btn btn-outline-secondary" onclick="show(${j.sangId},'${sid}')">담기</button></td>
		     <td>
		     <c:choose>
		     	<c:when test="${not empty rmap[j.orderId]}">
		     		<a type="button" class="btn btn-outline-secondary" href="myReview.do?memId=${i.memId}">내 리뷰보기</a>
		     		</c:when>
		     	<c:otherwise>
		     		<a type="button" class="btn btn-outline-secondary ${status.index}" href="myNewReview.do?sangId=${j.sangId}&orderId=${j.orderId}">리뷰쓰기</a>
		     	</c:otherwise>
		     </c:choose>
		     </td>
		    </tr>
		   </c:forEach> 
		   		<tr><td colspan="7" style="color: red; font-size: 0.1rem; text-align: right;">리뷰 쓰기는 구매 후 15일동안 가능합니다</td></tr>
		    <tr>
		      <th scope="row" colspan="3" class="table-active">구입 합계</th>
		      <td class="table-active">${i.guipTotal}원</td>
		      <td class="table-active" colspan="3"></td>
		    </tr>
		
		  </tbody>
		</table>
		<!-- Modal -->
			<form action="myDeleteOrder.do" method="post" >
				<input type="hidden" name="guipId" value="${i.guipId}">
				<input type="hidden" name="memId" value="${sid}">
				<div class="modal fade" id="${'exampleModal'}${status.count}" tabindex="-1" aria-labelledby="${'exampleModalLabel'}${status.count}" aria-hidden="true">
					<div class="modal-dialog">
					    <div class="modal-content">
							<div class="modal-header">
					        	<h1 class="modal-title fs-5" id="${'exampleModalLabel'}${status.count}">주문내역 삭제</h1>
					        	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
						        해당 주문내역을 삭제하시겠습니까?
							</div>
							<div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						        <input class="btn btn-danger" type="submit" value="삭제">
							</div>
					    </div>
					</div>
				</div>
			</form>
			</c:if>
		</c:forEach>
			</div>
		<div class="col"></div>
</div>
	</c:when>
	<c:otherwise>
		<div style="height: 100px"></div>
		<table class="no">
			<tr>
				<td class="td1"></td>
				<td class="td2"><br><h3>주문 내역이 없습니다</h3><br></td>
				<td class="td3"></td>
			</tr>
		</table>
		<br>
	</c:otherwise>
</c:choose>

<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>

<jsp:include page="../footer.jsp"/>

<!-- 장바구니 알림창 -->
<div  style="position: fixed; top: 15%; left: 45%; display: none;" id="cart">
<div class="card">
 <div class="card-body" align="center" style="font-size: 0.8rem;">
<span>장바구니에 상품이 담겼습니다</span>   <button type="button" class="btn-close" aria-label="Close" id="close"></button>
<br>
<br>
<a type="button" href="myCart.do" class="btn btn-outline-primary btn-sm">장바구니로 이동</a>
</div>
</div>
</div>

<script type="text/javascript">
window.onload=function (){
		var guipdate = document.getElementsByClassName('guipdate');
		var guipdatelen = guipdate.length;
		var now = new Date();
		var dday = new Date(now.setDate(now.getDate() - 15));//오늘로 부터 15일 전

		var year = dday.getFullYear();
		var month = ('0' + (dday.getMonth() + 1)).slice(-2);
		var day = ('0' + dday.getDate()).slice(-2);

		var dateString = year + '-' + month  + '-' + day;//String 오늘로 부터 15일 전		
		//오늘부터 15일 전이 구입날짜 보다 전이라면(작다면) 리뷰생성 가능 구입날짜 보다 후(크다면)라면 불가능
	    var date2 = new Date(dateString);
	    for(i=0; i<guipdatelen; i++) {
			var date1 = new Date(guipdate[i].innerText);
		    if(date1<date2){
		    	var review = document.getElementsByClassName('btn btn-outline-secondary '+i);
		    	var reviewlen = review.length;
		    	for(j=0; j<reviewlen; j++){
		    		if(review[j].text == '리뷰쓰기');
		    		review[j].className = 'btn btn-outline-secondary ' +i+ ' disabled';	    		
		    	}
		    }
	    }
	}
	//장바구니 팝업창
	function show(sangId,memId) {
		
		$.ajax({
			type : "post",
			url : "http://localhost:8200/Three/my/myOrderInsertCart.do",
			data : {sangId:sangId, memId:memId},
			dataType:"text",
			success : function(data) {		
				if(data != 0)
					document.getElementById('cart').style.display = 'block';
				else alert("품절등의 사유로 장바구니에 담을 수 있는 상품이 없습니다")
			},
			error : function(status) {
				alert(status+'error');
			}
		});
	}	
	document.getElementById('close').addEventListener('click', function(){
		document.getElementById('cart').style.display = 'none'
	})
</script>

</body>
</html>