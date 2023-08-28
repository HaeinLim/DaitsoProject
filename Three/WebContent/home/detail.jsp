<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>${sdto.sangName }</title>
<style>
a:hover, a:visited, a:active, a:link {
		text-decoration : none;
		color : black;
}
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

#product>.view>article {
	width: 100%;
	height: auto;
	overflow: hidden;
}

#product>.view>.info>div {
	float: left;
	width: 50%;
	height: 100%;
	padding: 10px;
	box-sizing: border-box;
}

#product>.view>.info>.img>img {
	width: 100%;
}

#product>.view>.info>.sangInfo>nav {
	border-bottom: 1px solid #eaeaea;
	padding-bottom: 10px;
	margin-bottom: 12px;
	overflow: hidden;
}

#product>.view>.info>.sangInfo>nav>h2 {
	float: left;
	font-weight: bold;
	font-size: 1.5em;
}

#product>.view>.info>.sangInfo>nav>price {
	font-size: 16px;
	margin-left: 6px;
}

#product>.view>.info>.sangInfo>nav>.delivery {
	font-size: 16px;
}

#product>.view>.info>.sangInfo>nav>.arrival {
	font-size: 14px;
	margin-left: 10px;
}

#product>.view>.info>.sangInfo>.button>form {
	display: inline;
}

.dButtons {
	border-top: 2px solid #d2d2d2;
	margin: 15px;
	text-align: center;
}

.btnMargin {
	margin-top: 15px;
}

.forLine {
	border-top: 2px solid #d2d2d2;
}

#dataField1, #dataField2, #dataField3 {
	margin-bottom: 20px;
}

.sangImg {
	width: 300px;
	height: 300px;
}

.cnt {
	text-align: center;
	width: 100px;
}
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
	.rate{background: url(/Three/img/bg.png)no-repeat; width:119px; height: 25px;position: relative;}
	.rate span{position: absolute;background: url(/Three/img/s.png);width: auto;height: 25px;}
</style>
<script>
	function check() {
		var sid = '<%=session.getAttribute("sid")%>';
		if (sid == "null") {
			alert("로그인 후 이용해주세요");
			return false;
		} else {
			alert("장바구니에 추가되었습니다");
			return true;
		}
	}
	function changePNum() {
        var item = document.querySelector('input[name=sangCount]');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        var max = document.getElementById('max').value;
        
        if (parseInt(newval) < 1) { 
        	alert("주문가능한 최소수량은 1개입니다");
        	return false; 
        }
        else if(parseInt(newval) > max) {
        	alert("주문가능한 최대수량은 " + max +"개입니다");
        	return false;
        }
        item.setAttribute('value', newval);
        item.value = newval;
	}
</script>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<%@include file="detailBar.jsp"%>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

<% 
	
	if(request.getAttribute("sdto") == null) {
		out.print("<script>alert('존재하지 않는 상품이거나 품절된 상품입니다'); location.href='main.do';</script>");	
	}
%>
<c:if test="${sdto.sangAmount == 0}"></c:if>
	<div class="wrapper">
		<main id="product">
			<section class="view">
				<article class="info">
					<div class="img">
						<img
							src="<%=request.getContextPath()%>/sang_imgs/${sdto.sangPipath }"
							class="sangImg" alt="상품이미지">
					</div>
					<div class="sangInfo">
						<nav>
							<!-- 구획을 나타내는 nav -->
							<p class="h2">${sdto.sangName }</p>
							<div class="rate"><span style="width: ${sdto.sangStar*20}%"></span></div>
						</nav>
						<nav>
							<span class="price">${sdto.sangPrice }원</span>
						</nav>
						<nav>
							<span class="delivery">배송일 : 구매일로부터 3일 내 배송</span>
							<!-- <span class="arrival"></span> -->
						</nav>
						<nav>
							<span class="from">원산지 : 상세설명 참조</span>
						</nav>
						<div class="button">
							<form action="inputCart.do" method="post" name="inputForm"
								onsubmit="return check()">
								<input type="hidden" id="max" value="${sdto.sangAmount}">
								  <div class="row justify-content-start">
								  	<div class="col-6">
										<div class="input-group">
											<button type="button" class="btn btn-outline-secondary btn-sm down" onclick="javascript:changePNum();"><b class="down">-</b></button>
											<input type="number"  name="sangCount"  id="p_num" class="p_num" onkeyup="javascript:changePNum();" min="1" max="${sdto.sangAmount}" value="1" style="width: 3rem;">
											<button type="button" class="btn btn-outline-secondary btn-sm up" onclick="javascript:changePNum();"><b class="up">+</b></button>
										</div>
									</div>
									<div class="col-6"><button type="submit" class="btn btn-outline-dark">장바구니</button></div>	
								</div>
								<input type="hidden" name="memId" value="${sid }"> 
								<input type="hidden" name="sangId" value="${sdto.sangId }">
							</form>
						</div>
					</div>
				</article>



				<article class="details">
					<div class="dButtons">
						<div class="btnMargin">
							<a class="btn btn-outline-dark" href="#dataField2" role="button">문의</a>
							<a class="btn btn-outline-dark" href="#dataField3" role="button">구매후기</a>
						</div>
					</div>
					<div id="dataField1">
						<p class="h4">상품정보</p>
						<div class="forLine">
							<jsp:include page="sangDetail.jsp"></jsp:include>
						</div>
					</div>
					<div id="dataField2">
						<p class="h4">문의</p>
						<div class="forLine">
							<jsp:include page="qna.jsp"></jsp:include>
						</div>
					</div>
					<div id="dataField3">
						<p class="h4">구매후기</p>
						<div class="forLine">
							<jsp:include page="review.jsp"></jsp:include>
						</div>
					</div>
				</article>

				<!-- 위로 올라가는 버튼 -->
				<div style="position: fixed; bottom: 5px; right: 5px">
					<a href="#"><img src="/Three/img/goup.png"></a>
				</div>

			</section>
		</main>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>