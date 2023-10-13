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
<title>상품 상세설명 수정</title>
<style>
.img {
	width: 80px;
	height: 60px;
}

.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
</style>
<script>
	function deleteCheck() { // 상세설명 삭제시 발생하는 확인 알림창, 삭제시 삭제확인 알림창 팝업
		if (confirm('정말 삭제하시겠습니까?') == true) {
			alert("삭제되었습니다");
		} else {
			return false;
		}
	}
	function updateCheck() { // 수정시 수정 완료 알림 팝업
		alert("수정되었습니다");
	}
</script>
</head>
<body>
	 <% String sid = (String)session.getAttribute("sid");
	 	if(!sid.equals("admin"))
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<jsp:include page="adPageBar.jsp"></jsp:include>

	<div class="wrapper">
		<main id="adDup">
			<section class="update">
			<h2 align=center>상품 상세설명 수정</h2>
			<br><br>
				<div class="container">
					<div class="row">
						<form action="detailDelete.do?detId=${sdet.detId }" method="post"
							name="deleteForm" onsubmit="return deleteCheck()" align=right>
							
									<a class="btn btn-outline-secondary" href="adSang.do" role="button">상품조회</a>
									<input class="btn btn-danger" type="submit" value="삭제">
									
								<!-- 삭제버튼 클릭시 삭제 확인 함수 호출 -->
							
							<input type="hidden" name="file1" value="${sdet.detPipath1 }">
							<!-- 파일이 같이 삭제되도록 hidden에 정보를 담아 보냄 -->
							<input type="hidden" name="file2" value="${sdet.detPipath2 }">
							<input type="hidden" name="file3" value="${sdet.detPipath3 }">
							<input type="hidden" name="file4" value="${sdet.detPipath4 }">
						</form>
					</div>
				</div>
			
				<div class="container">
					<!-- 컨테이너랑 그리드 사용 -->
					<div class="row">
						<form action=detUpdate.do method="post"
							enctype="multipart/form-data" onsubmit="return updateCheck()">
							<input type="hidden" value="${sdto.sangId }" name="sangId">
							<table class="table table-bordered"
								style="table-layout: fixed; width: 800px;">
								<tr align=center>
									<th colspan=2>상품 상세설명</th>
								</tr>
								<tr>
									<td align=center>상품이름</td>
									<td>${sdto.sangName }</td>
								</tr>
								<tr>
									<td align=center>상품 상세설명 <br>이름
									</td>
									<td><input type="text" name="detName"
										value="${sdet.detName }"></td>
								</tr>


								<tr>
									<td align=center>상품 상세설명 수정</td>
									<td>사진1 <input type="file" name="file1"><br>
										사진2 <input type="file" name="file2"><br>
										사진3 <input type="file" name="file3"><br>
										사진4	 <input type="file" name="file4"><br>
									</td>
								</tr>
								<tr>
									<td align=center>수정 전 이미지</td>
									<td>
										<!-- onerror="this.style.display='none'" : 이미지 관련 에러 발생시 보여주지 않음(ex. 이미지 등록 안함) -->
										<p style="font-size: 0.3rem; word-spacing: 4rem;">사진1 사진2 사진3 사진4<p>
										<img src="<%=request.getContextPath() %>/detail_imgs/${sdet.detPipath1 }"
										class="img" onerror="this.style.display='none'"  onclick="window.open(this.src)">
										<img src="<%=request.getContextPath() %>/detail_imgs/${sdet.detPipath2 }"
										class="img" onerror="this.style.display='none'"  onclick="window.open(this.src)">
										<img src="<%=request.getContextPath() %>/detail_imgs/${sdet.detPipath3 }"
										class="img" onerror="this.style.display='none'"  onclick="window.open(this.src)">
										<img src="<%=request.getContextPath() %>/detail_imgs/${sdet.detPipath4 }"
										class="img" onerror="this.style.display='none'"  onclick="window.open(this.src)">
									</td>
								</tr>
								<tr>
									<td align=center>작성일</td>
									<td>${sdet.detDate }</td>
								</tr>
								<tr>
									<td colspan=2 align=right>
										<input class="btn btn-outline-primary" type="submit" value="수정">
										<input class="btn btn-outline-secondary" type="reset" value="취소"></td>
								</tr>
							</table>
							<input type="hidden" value="${sdet.detId }" name="detId">
							<input type="hidden" name="before_file1"
								value="${sdet.detPipath1 }">
							<!-- 기존 첨부된 이미지 파일의 경로 hidden으로 전달 -->
							<input type="hidden" name="before_file2"
								value="${sdet.detPipath2 }"> <input type="hidden"
								name="before_file3" value="${sdet.detPipath3 }"> <input
								type="hidden" name="before_file4" value="${sdet.detPipath4 }">
						</form>
					</div>
				</div>
			</section>
		</main>
	</div>
</body>
</html>