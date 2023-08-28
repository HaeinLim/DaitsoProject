<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
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
	<script src="../js/jquery-3.7.0.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<jsp:include page="adPageBar.jsp" />

	<br>
	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col">
			<c:choose>
				<c:when test="${sangId==0}">
					<a class="btn btn-outline-dark" href="adNewSang.do"
						role="button">상품 등록으로 돌아가기</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-outline-dark" href="adReSang.do?sangId=${sangId}"
						role="button">상품 수정으로 돌아가기</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
	</div>

	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col" style="font-size: 1.5em;">- 카테고리 등록</div>
		<div class="col"></div>
		<div class="col"></div>
	</div>
	<br>

	<div class="row">
		<div class="col"></div>
		<div class="col-4">
			<div class="card" style="width: 30rem;">
				<form action="adInsertCate.do" method="post" name="insertform">
					<div class="input-group">
						<label class="input-group-text" for="inputGroupSelect01">카테고리 분류</label>
						<select class="form-select" id="inputGroupSelect04"
							name="bigId" aria-label="Example select with button addon"
							required="required">
							<option disabled="disabled" selected="selected" value="0">- 카테고리 분류 선택 -</option>
							<c:forEach var="i" items="${barray}">
								<option value="${i.bigId}">${i.bigName}</option>
							</c:forEach>
						</select>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">카테고리 이름 : <input type="text" name="cateName" required="required" id="cNameInsert">
						</li>
					</ul>
					<c:if test="${not empty sangId}">
						<input type="hidden" name="sangId" value="${sangId}">
					</c:if>
					<div class="card-footer" align="center">
						<button type="reset" class="btn btn-outline-dark">취소</button>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-outline-primary" name="confirmbutton" id="insert">
							등록</button>					
					</div>
				</form>
			</div>
		</div>
		<div class="col"></div>
	</div>


	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col" style="font-size: 1.5em;">- 카테고리 수정</div>
		<div class="col"></div>
		<div class="col"></div>
	</div>
	<br>

	<div class="row">
		<div class="col"></div>
		<div class="col-4">	
			<div class="card" style="width: 30rem;">
				<form action="adUpdateCate.do" method="post" name="updateform">				
					<div class="input-group">
						<label class="input-group-text" for="inputGroupSelect01">변경할 카테고리 분류</label>
						<select class="form-select" id="bigs"
							name="bigId" aria-label="Example select with button addon">
							<option selected="selected" value="0">- 카테고리 분류 선택 -</option>
							<c:forEach var="i" items="${barray}">
								<option value="${i.bigId}">${i.bigName}</option>
							</c:forEach>
						</select>
					</div>					
					<div class="input-group">
						<label class="input-group-text" for="inputGroupSelect01">변경할 카테고리</label>
						<select class="form-select" id="cates" name="cateId"
							aria-label="Example select with button addon" required="required" onchange="handleOnChange(this)">
							<option disabled="disabled" selected="selected"  value="0">--카테고리 선택--</option>
							<c:forEach var="j" items="${barray}">
								<option value="${sdto.cateId}" disabled="disabled"  value="0">--${j.bigName}--</option>
								<c:forEach var="i" items="${carray}">
									<c:if test="${j.bigId == i.bigId}">
										<option value="${i.cateId}">${i.cateName}</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</div>					
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							변경할 카테고리 이름 : <input type="text" name="cateName" id="cateName">
						</li>
					</ul>
					<c:if test="${not empty cateId}">
					<input type="hidden" value="${sangId}" name="sangId">
					</c:if>
					<div class="card-footer" align="center">
						<button type="reset" class="btn btn-outline-dark">취소</button>
						<button type="button" class="btn btn-outline-primary"
							id="update">수정</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col"></div>
	</div>
	
	<br>
	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col" style="font-size: 1.5em;">- 카테고리 삭제</div>
		<div class="col"></div>
		<div class="col"></div>
	</div>
	<br>

	<div class="row">
		<div class="col"></div>
		<div class="col-4">	
			<div class="card" style="width: 30rem;">	
				<form action="adDeleteCate.do" method="post" name="deleteform">									
					<div class="input-group">
						<label class="input-group-text" for="inputGroupSelect01">삭제할 카테고리</label>
						<select class="form-select" id="delcate" name="cateId"
							aria-label="Example select with button addon" required="required">
							<option disabled="disabled" selected="selected"  value="0">--카테고리 선택--</option>
							<c:forEach var="j" items="${barray}">
								<option value="${sdto.cateId}" disabled="disabled"  value="0">--${j.bigName}--</option>
								<c:forEach var="i" items="${excarr}">
									<c:if test="${j.bigId == i.bigId}">
										<option value="${i.cateId}">${i.cateName}</option>									
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</div>					
					<c:if test="${not empty cateId}">
					<input type="hidden" value="${sangId}" name="sangId">
					</c:if>
					<div class="card-footer" align="center">
						<button type="reset" class="btn btn-outline-dark">취소</button>
						<button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#exampleModal"  id="delete">
						 삭제
						</button>
					</div>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">카테고리 삭제</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						       	해당카테고리를 삭제하시겠습니까? 삭제시 복구가 불가능합니다
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						        <button type="submit" class="btn btn-outline-danger">삭제</button>
						      </div>
						    </div>
						  </div>
						</div>
				</form>
			</div>
		</div>
		<div class="col"></div>
	</div>

<script type="text/javascript">	
	//수정 시 선택한 카데고리 이름 출력
	function handleOnChange(e) {
		  // 선택된 데이터의 텍스트값 가져오기
		  const text = e.options[e.selectedIndex].text;		  
		  // 선택한 텍스트 출력
		  document.getElementById('cateName').value
		    = text;
		}
	//카테고리 입력시 이름체크
	document.getElementById('insert').addEventListener('click', function(){
		cf=document.insertform;
		sf=document.getElementById('inputGroupSelect04');
		cNameInsert=document.getElementById('cNameInsert').value;
		if(sf.options[sf.selectedIndex].value==0)
			alert("카테고리 분류를 선택해주세요");
		else if(!cf.cateName.value)
			alert("카테고리 이름을 입력해주세요");
		else {
			$.ajax({
				type : "post",
				url : "http://localhost:8200/Three/admin/adFindCate.do",
				data : {cateName:cNameInsert},
				dataType:"text",
				success : function(data) {		
					if(data != 0) {
						cf.submit();
						alert("카테고리 등록 완료");
					}
					else {
						cNameInsert = "";
						alert("이미 존재하는 카테고리입니다\n 다른 이름을 입력해주세요");
					}
				},
				error : function(status) {
					alert(status+'error');
				}
			});
		}
	})
	//카테고리 수정 시 중복체크
	document.getElementById('update').addEventListener('click', function(){
		cf=document.updateform;
		sf=document.getElementById('cates');
		bf=document.getElementById('bigs');
		cateId = sf.options[sf.selectedIndex].value
		bigId = bf.options[bf.selectedIndex].value
		cNameInsert=document.getElementById('cateName').value; //변경할 카테고리 이름
		if(cateId==0)
			alert("변경할 카테고리를 선택해주세요");
		else if(!cNameInsert)
			alert("변경할 카테고리 이름을 입력해주세요");
		else {
			$.ajax({
				type : "post",
				url : "http://localhost:8200/Three/admin/adUpCheckCate.do",
				data : {bigId:bigId,cateId:cateId,cateName:cNameInsert},
				dataType:"text",
				success : function(data) {		
					if(data != 0) {
						cf.submit();
						alert("카테고리 변경 완료");
					}
					else {
						cNameInsert = "";
						cateId=0;
						alert("이미 존재하는 카테고리입니다\n 다른 이름을 입력해주세요");
					}
				},
				error : function(status) {
					alert(status+'error');
				}
			});
		}
	})
	
</script>

	<jsp:include page="../footer.jsp" />

</body>
</html>