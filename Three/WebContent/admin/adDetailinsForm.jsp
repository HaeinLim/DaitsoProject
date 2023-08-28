<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>상품 상세설명 입력</title>
<style>
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
</style>
<script>
	function check(){
		if(document.insertForm.detName.value == ""
				|| document.insertForm.file1.value == "") {
			alert("내용이 입력되지 않았습니다.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	 <% String sid = (String)session.getAttribute("sid");
	 	if(!sid.equals("admin"))
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>	
	<jsp:include page="adPageBar.jsp"></jsp:include>
	
	<div class="wrapper">
		<main id="deIns">
			<section class="details">
				<h2 align=center>상품 상세설명 입력</h2>
				<br><br>
				
				<div class="container">
					<div class="row">
						<div align=right>
							<a class="btn btn-outline-secondary" href="adSang.do" role="button">상품조회</a>
						</div>
					</div>
				</div>
				<div class="container">
					<!-- 컨테이너랑 그리드 사용 -->
					<div class="row">
							<!-- 삭제버튼 클릭시 삭제 확인 함수 호출 -->
			
						<form action="detailInsert.do" method="post"
							enctype="multipart/form-data" name="insertForm" onsubmit="return check()">
							<input type="hidden" value="${sdto.sangId }" name="sangId">
							<table class="table table-bordered" style="table-layout: fixed; width:800px;">
								<tr>
									<td>작성일</td>
									<td><c:set var="today" value="<%=new java.util.Date()%>" />
										<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td>상품이름</td>
									<td>${sdto.sangName }</td>
									<!-- 상품에서 상세설명 입력하는 버튼 누르면 제품번호를 가지고 넘어올 예정, 상품 쪽 완성되면 추후 수정 -->
								</tr>
								<tr>
									<td>상품 상세설명 이름</td>
									<td><input type="text" name="detName"></td>
								</tr>
								<tr>
									<td>상품 상세설명</td>
									<td><input type="file" name="file1"><br> <input
										type="file" name="file2"><br> <input type="file"
										name="file3"><br> <input type="file" name="file4"><br>

										<br>
									<br>
									<br> <input class="btn btn-outline-primary" type="submit" value="입력">
										<input class="btn btn-outline-secondary" type="reset" value="취소"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</section>
		</main>
	</div>
</body>
</html>