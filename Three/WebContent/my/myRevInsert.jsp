<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>구매후기 작성</title>
<style>
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
</style>
<script>
	function check() {
		if(document.revInsert.writeRev.value==""){
			alert("구매후기를 작성해주세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<!-- 구매후기 작성(입력)페이지 -->

	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<% if(request.getAttribute("sdto") == null) {
	out.print("<script>alert('해당 상품이 존재하지 않습니다'); location.href='myOrder.do';</script>");
}
%>
<jsp:include page="myPageBar.jsp"/>

	<div class="wrapper">
		<main id="reIns">
			<section class="review">
				<h2 align=center>구매후기 작성</h2>
				<br>
				<div class="container">
				<a type="button" class="btn btn-outline-secondary" href="myOrder.do">주문목록보기</a>
					<div class="row">
						<form action="revInsert.do" method="post"
							enctype="multipart/form-data" name="revInsert" onsubmit="return check()">
							<table class="table table-bordered">
								<tr>
									<td>아이디</td>
									<td>${nick}</td>
								</tr>
								<tr>			
									<td>상품명</td>
									<td>${sdto.sangName}</td>
								</tr>	
								<tr>
									<td>별점</td>	
									<td>
										<input type="hidden" name="revStar" id="ss" value="5">
										<img src="/Three/img/star.png" id="star1" onclick="chagestar(1)">
										<img src="/Three/img/star.png" id="star2" onclick="chagestar(2)">
										<img src="/Three/img/star.png" id="star3" onclick="chagestar(3)">
										<img src="/Three/img/star.png" id="star4" onclick="chagestar(4)">
										<img src="/Three/img/star.png" id="star5" onclick="chagestar(5)">
									</td>
								</tr>
								<tr>
									<td colspan=4><input type="file" name="file1"><br>
										<input type="file" name="file2"><br> <input
										type="file" name="file3"><br></td>
								</tr>
								<tr>
									<td colspan=4><textarea rows="10" cols="50" name="revContent"
											style="width: 100%" placeholder="구매후기를 작성해주세요"></textarea></td>
								</tr>
								<tr>
									<td colspan=2 align=right><input
										class="btn btn-outline-primary" type="submit" value="등록">
										<input class="btn btn-outline-secondary" type="reset"
										value="취소"></td>
								</tr>
							</table>
							<input type="hidden" name="memId" value="${sid }">
							<input type="hidden" name="sangId" value="${odto.sangId }">		
							<input type="hidden" name="orderId" value="${odto.orderId }">
						</form>
					</div>
				</div>
			</section>
		</main>
	</div>
<script type="text/javascript">
 function chagestar(num){
		var item = document.getElementById('ss');
        var ss = parseInt(item.getAttribute('value'));
		if(num > ss){
			for(var i=ss+1; i<=num; i++)
				document.getElementById('star'+i).src = "/Three/img/star.png";
		}
		else if(num<ss){
			for(var i=num+1; i<=ss; i++)
				document.getElementById('star'+i).src = "/Three/img/nostar.png";
		}
		item.setAttribute('value', num);
        item.value = num;
	}
</script>
<jsp:include page="../footer.jsp"/>

</body>
</html>