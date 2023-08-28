<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery-3.7.0.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<title>장바구니</title>
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
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
.sangImg {
	width: 7rem;
	height: 4rem;;
}
</style>
</head>
<body>
	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="/Three/my/myCart.js"></script>  
	<jsp:include page="myPageBar.jsp" />

	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col-6" style="font-size: 1.3em;">- ${nick} 님의 장바구니</div>
		<div class="col"></div>
	</div>
	<br>

	<c:choose>
		<c:when test="${not empty carray}">
			<div class="row">
				<div class="col"></div>
				<div class="col-6" id="basket">
				<form method="post" action="myGuip.do" id="payform">
					<table class="table table-hover" style="vertical-align:middle;">
						<thead>
							<tr>
								<td><span style="font-size: 0.8rem;">전체선택</span><br><input type="checkbox" class="form-check-input" name="selectall" onclick="javascript:basket.allCheckedItem(this);"></td>
								<th scope="col" colspan="2">상품</th>
								<th scope="col" style="text-align: center;">가격</th>
								<th scope="col" style="text-align: center;">수량</th>
								<th scope="col" style="text-align: center;">합계</th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach var="i" items="${carray}" varStatus="st">
								<c:if test="${not empty smap[i.cartId]}">	
									<input type="hidden" id="${'max'}${st.count}" value="${smap[i.cartId].sangAmount}">										
									<tr>
										<td class="check"><input type="checkbox" name="buy" value="${i.sangId}" onclick="javascript:basket.checkItem();" class="form-check-input"></td>
										<td>
											<img src="<%=request.getContextPath()%>/sang_imgs/${smap[i.cartId].sangPipath }" class="sangImg">
										</td>
										<td class="pname"><b>${smap[i.cartId].sangName}</b></td>
										<td class="basketprice" style="text-align: center;">
											<input type="hidden" name="p_price" id="${'p_price'}${st.count}" class="p_price" value="${smap[i.cartId].sangPrice}">
											${smap[i.cartId].sangPrice}원
											</td>
										<td class="num">
											<div class="input-group">
												<button type="button" class="btn btn-outline-secondary btn-sm down" onclick="javascript:basket.changePNum(${st.count});"><b class="down">-</b></button>
												<input type="number"  name="${'p_num'}${st.count}"  id="${'p_num'}${st.count}" class="p_num" onkeyup="javascript:basket.changePNum(${st.count});" min="1" max="${smap[i.cartId].sangAmount}" value="${i.sangCnt}" style="width: 2rem;">
												<button type="button" class="btn btn-outline-secondary btn-sm up" onclick="javascript:basket.changePNum(${st.count});"><b class="up">+</b></button>
											</div>
										</td>																					
										<td class="sum" style="text-align: center;">${smap[i.cartId].sangPrice * i.sangCnt}원</td>
									</tr>
								</c:if>
								<c:if test="${not empty snmap[i.cartId]}">						
									<tr>
										<td class="check" style="color: gray; font-size: 1rem; text-align: center;">품절
											<input type="hidden" name="zero" value="${snmap[i.cartId].sangId}">	
										</td>
										<td>
											<img src="<%=request.getContextPath()%>/sang_imgs/${snmap[i.cartId].sangPipath }" class="sangImg">
										</td>
										<td class="pname"><b>${snmap[i.cartId].sangName}</b></td>
										<td class="basketprice" style="text-align: center;">
											${snmap[i.cartId].sangPrice}원
											</td>
										<td class="num">
											<div class="input-group">
												<button type="button" class="btn btn-outline-secondary btn-sm down" disabled="disabled"><b class="down">-</b></button>
												<input type="number"  name="${'p_num'}${st.count}"  id="${'p_num'}${st.count}" class="p_num" value="0" style="width: 2rem;" readonly="readonly">
												<button type="button" class="btn btn-outline-secondary btn-sm up" disabled="disabled"><b class="up">+</b></button>
											</div>
										</td>																					
										<td class="sum" style="text-align: center;">0원</td>
									</tr>
								</c:if>
						</c:forEach>
							<tr class="table-active">
								<td colspan="3" style="text-align: center;"><b>선택한 상품 총 갯수</b></td>
								<td colspan="3" style="text-align: center;" id="sum_p_num"><b>0개</b></td>
							</tr>
							<tr class="table-active">
								<td colspan="3" style="text-align: center;"><b>선택한 상품 총 금액</b></td>
								<td colspan="3" style="text-align: center;" id="sum_p_price"><b>0원</b></td>
							</tr>
						</tbody>
					</table>
					<div>
						<button type="button" class="btn btn-outline-secondary btn-sm"  onclick="javascript:basket.delCheckedItem();" style="font-size: 0.7rem;">선택상품 삭제</button>
						<button type="button" class="btn btn-outline-secondary btn-sm" style="font-size: 0.7rem;" onclick="javascript:basket.delZero();">품절상품 삭제</button>
					</div>
					<div style="text-align: center;"><button type="button" class="btn btn-primary" onclick="javascript:basket.pay();">결제하기</button></div>
				</form>
				</div>
				<div class="col"></div>
			</div>
		</c:when>
		<c:otherwise>
			<div style="height: 100px"></div>
			<table class="no">
				<tr>
					<td class="td1"></td>
					<td class="td2"><br>
						<h3>장바구니에 담긴 상품이 없습니다</h3> <br></td>
					<td class="td3"></td>
				</tr>
			</table>
			<br>
		</c:otherwise>
	</c:choose>
	<div style="position: fixed; bottom: 5px; right: 5px">
		<a href="#"><img src="../img/goup.png"></a>
	</div>
	<jsp:include page="../footer.jsp" />
	
<script type="text/javascript">

</script>
</body>
</html>