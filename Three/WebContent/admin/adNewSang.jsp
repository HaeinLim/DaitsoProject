<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 상세 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
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
		<div class="col-3">
			<a class="btn btn-outline-dark" href="adSang.do" role="button">상품
				조회</a>
		</div>
		<div class="col-3" style="font-size: 1.5em;">- 상품등록</div>
		<div class="col-2"></div>
	</div>
	<br>

	<div class="row">
		<div class="col"></div>
		<div class="col-4">
			<div class="card" style="width: 30rem;">
				<form action="adInsertSang.do" method="post" name="insert" enctype="multipart/form-data">
					<div class="input-group">
						<input type="file" class="form-control" id="inputGroupFile04"
							aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file">
					</div>
					<div class="input-group">
						<select class="form-select" id="inputGroupSelect04" name="cateId"
							aria-label="Example select with button addon" required="required">
							<option value="" selected="selected">--카테고리 선택--</option>
							<c:forEach var="j" items="${barray}">
								<option value="" disabled="disabled">--${j.bigName}--</option>
								<c:forEach var="i" items="${carray}">
									<c:if test="${j.bigId == i.bigId}">
										<option value="${i.cateId}"
											${cateName == i.cateName ? 'selected': ''}>${i.cateName}</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select> 
						<a class="btn btn-outline-secondary" " href="adCate.do"
							role="button">카테고리 관리 >></a>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">상품이름 : <input type="text" id="sangName" name="sangName"></li>
						<li class="list-group-item">상품가격 : <input type="text" name="sangPrice" value="0"></li>
						<li class="list-group-item">상품수량 : <input type="text" name="sangAmount" value="0"></li>
					</ul>
					<input type="hidden" value="${sdto.sangId}" name="sangId">
					<div class="card-body">
						<button type="reset" class="btn btn-outline-dark">취소</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="check()">등록</button>
					</div>
				</form>

			</div>
		</div>
		<div class="col"></div>
	</div>


	<script type="text/javascript">
		function check() {
			if(!document.getElementById("inputGroupSelect04").value) {
				alert("카테고리를 선택해주세요");
				document.getElementById("inputGroupSelect04").focus();
			}
			else if(!document.getElementById("sangName").value){
				alert("상품이름을 입력해주세요");
				document.getElementById("sangName").focus();
			}
			else {
				document.insert.submit();
				alert("등록완료");
			}
		}
	</script>
	
	<jsp:include page="../footer.jsp" />

</body>
</html>