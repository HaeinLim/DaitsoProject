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
<title>구매후기 수정</title>
<style>
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

img.rev{
	width: 80px;
	height: 60px;
}

</style>
<script>
window.onload = function() {
	var item = document.getElementById('ss');
	var star = parseInt(item.getAttribute('value'));
	if(star != 5) {
		for(var i=star+1; i<=5; i++){
			document.getElementById('star' + i).src = "/Three/img/nostar.png";
		}
	}
}
	function deleteCheck() { // 상세설명 삭제시 발생하는 확인 알림창, 삭제시 삭제확인 알림창 팝업
		if (confirm('정말 삭제하시겠습니까?') == true) {
			alert("삭제되었습니다");
		} else {
			return false;
		}
	}
	
	function check() {
		alert("수정완료");
	}
	 
	function chagestar(num) {
		var item = document.getElementById('ss');
		var ss = parseInt(item.getAttribute('value'));
		if (num > ss) {
			for (var i = ss + 1; i <= num; i++)
				document.getElementById('star' + i).src = "/Three/img/star.png";
		} else if (num < ss) {
			for (var i = num + 1; i <= ss; i++)
				document.getElementById('star' + i).src = "/Three/img/nostar.png";
		}
		item.setAttribute('value', num);
		item.value = num;
	}
</script>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<!-- 마이페이지 리뷰수정 -->
<% if(session.getAttribute("sid") == null) response.sendRedirect("main.do");%>

	<jsp:include page="myPageBar.jsp"></jsp:include>
	<br><br>

	<div class="wrapper">
		<main id="reUpdate">
			<section class="rvUp">
				<h2 align=center>구매후기 수정</h2>
				<br>
				<div class="container">
					<!-- 컨테이너랑 그리드 사용 -->
					<div class="row">		
						<form action="revDelete.do?revId=${rdto.revId }&sangId=${rdto.sangId}" method="post" name="deleteForm" onsubmit="return deleteCheck()">
							<div class="rvDel" align="right">
								<a class="btn btn-outline-secondary" href="myReview.do"
									role="button">내 구매후기 조회</a> <input class="btn btn-danger" type="submit" value="삭제">
								
								<!-- 구매후기 삭제시 이미지도 서버에서 같이 삭제 되도록 hidden으로 보냄 -->
								<input type="hidden" name="file1" value="${rdto.revPipath1 }">
								<input type="hidden" name="file2" value="${rdto.revPipath2 }">
								<input type="hidden" name="file3" value="${rdto.revPipath3 }">
							</div>
						</form>
						<form action="revUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
							<table class="table table-bordered">
								<tr>
									<td>아이디</td>
									<td>${nick}</td>
								</tr>
								<tr>
									<td>상품명</td>
									<td>${sdto.sangName }</td>
								</tr>
								<tr>
									<td>별점</td>
									<td>
										<input type="hidden" name="revStar" id="ss" value="${rdto.revStar}">
										<img src="/Three/img/star.png" id="star1" onclick="chagestar(1)">
										<img src="/Three/img/star.png" id="star2" onclick="chagestar(2)">
										<img src="/Three/img/star.png" id="star3" onclick="chagestar(3)">
										<img src="/Three/img/star.png" id="star4" onclick="chagestar(4)">
										<img src="/Three/img/star.png" id="star5" onclick="chagestar(5)">
									</td>
								</tr>
								<tr>
									<td colspan=2>
										<img class="rev" src="<%=request.getContextPath() %>/rev_imgs/${rdto.revPipath1 }"
										onerror="this.style.display='none'" onclick="window.open(this.src)">
										<img class="rev" src="<%=request.getContextPath() %>/rev_imgs/${rdto.revPipath2 }"
										onerror="this.style.display='none'" onclick="window.open(this.src)">
										<img  class="rev" src="<%=request.getContextPath() %>/rev_imgs/${rdto.revPipath3 }"
										onerror="this.style.display='none'" onclick="window.open(this.src)">
									</td>
								</tr>
								<tr>
									<td colspan=2><input type="file" name="file1"><br>
										<input type="file" name="file2"><br> <input
										type="file" name="file3"></td>
								</tr>
								<tr>
									<td colspan=2>
										<textarea rows="10" cols="50" style="width:100%" name="revContent">${rdto.revContent }</textarea>
									</td>
								</tr>
								<tr>
									<td colspan=2 align=right><input
										class="btn btn-outline-primary" type="submit" value="수정">
										<input class="btn btn-outline-secondary" type="reset"
										value="취소"></td>
								</tr>
							</table>
							<input type="hidden" name="revId" value="${rdto.revId }">
							<input type="hidden" name="sangId" value="${rdto.sangId }">
							<!-- 서버에서 사진을 삭제하고 등록하는 과정이 들어가기 때문에 hidden으로 값을 전달 -->	
							<input type="hidden" name="before_file1" value="${rdto.revPipath1 }">
							<input type="hidden" name="before_file2" value="${rdto.revPipath2 }">
							<input type="hidden" name="before_file3" value="${rdto.revPipath3 }">
						</form>
					</div>
				</div>
			</section>
		</main>
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>