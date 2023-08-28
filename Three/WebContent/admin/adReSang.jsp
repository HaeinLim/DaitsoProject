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
<style type="text/css">
.img {
	width: 80px;
	height: 60px;
}
</style>
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
		<div class="col-3" style="font-size: 1.5em;">- 상품수정</div>
		<div class="col-2"></div>
	</div>
	<br>

	<div class="row">
		<div class="col"></div>
		<div class="col-4">
			<div class="card" style="width: 30rem;">
				<div class="card-header">
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal">삭제</button>
				</div>

				<form action="update.do" method="post" name="update" enctype="multipart/form-data" id="update">
					<div class="input-group">
						<input type="file" class="form-control" id="inputGroupFile04"
							aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file">
					</div>
					<div class="input-group">
						<select class="form-select" id="inputGroupSelect04" name="cateId"
							aria-label="Example select with button addon">
							<c:forEach var="j" items="${barray}">
								<option value="${sdto.cateId}" disabled="disabled">--${j.bigName}--</option>
								<c:forEach var="i" items="${carray}">
									<c:if test="${j.bigId == i.bigId}">
										<option value="${i.cateId}"
											${cateName == i.cateName ? 'selected': ''}>${i.cateName}</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select> 
						<a class="btn btn-outline-secondary" " href="adCate.do?sangId=${sdto.sangId}"
							role="button">카테고리 관리 >></a>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">상품이름 : <input type="text" value="${sdto.sangName}" id="sangName" name="sangName"></li>
						<li class="list-group-item">상품가격 : <input type="text" value="${sdto.sangPrice}" id="sangPrice" name="sangPrice"></li>
						<li class="list-group-item">상품수량 : <input type="text" value="${sdto.sangAmount}" id="sangAmount" name="sangAmount"></li>
						<li class="list-group-item">상품등록일 : <input type="text" value="${sdto.sangDate}" id="sangDate" name="sangDate"></li>
						<li class="list-group-item">현재 사진 : 
							<img src="<%=request.getContextPath() %>/sang_imgs/${sdto.sangPipath}"
										class="img" onerror="this.style.display='none'" name="before_file" onclick="window.open(this.src)">
						</li>
					</ul>
					<input type="hidden" value="${sdto.sangId}" name="sangId">
					<div class="card-body">
						<button type="reset" class="btn btn-outline-dark">취소</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="check()">수정</button>
					</div>
				</form>

			</div>
		</div>
		<div class="col"></div>
	</div>

	<!-- Modal -->
	<form action="adDeleteSang.do" method="post">
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">상품삭제</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">해당 상품을 삭제합니다</div>
				<input type="hidden" name="sangId" value="${sdto.sangId}">
				<input type="hidden" name="file" value="${sdto.sangPipath}">
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-danger">삭제하기</button>
				</div>
			</div>
		</div>
	</div>
	</form>

	<script type="text/javascript">
		function check() {
			if(document.getElementById("inputGroupSelect04").value==''){
				alert("카테고리를 선택하세요");
				return false;
			}
			else if(document.getElementById("sangName").value==''){
				alert("상품이름을 입력하세요");
				return false;
			}
			else if(document.getElementById("sangPrice").value==''){
				alert("상품 가격을 입력하세요");
				return false;
			}
			else if(document.getElementById("sangAmount").value==''){
				alert("상품수량을 입력하세요");
				return false;
			}
			else if(document.getElementById("sangDate").value==''){
				alert("상품등록일을 입력하세요");
				return false;
			}
			else {
				document.getElementById("update").submit();
				alert("수정완료");
			}
				
		}
	</script>
	
	<jsp:include page="../footer.jsp" />

</body>
</html>