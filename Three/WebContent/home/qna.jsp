<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>문의글</title>
<style type="text/css">
	a:hover, a:visited, a:active, a:link {
	text-decoration : none;
		color : black;
	}
	.qna {
		text-align: center;
		background-color: #DCDCDC;
		border-top-left-radius: 15px; border-top-right-radius: 15px;
        border-bottom-left-radius: 15px; border-bottom-right-radius: 15px;
	}
</style>
</head>
<body>


<br>
<c:if test="${not empty sid}">
<div class="row">
	<div class="col">
		<form action="insertQnaMain.do" method="post">
			<input type="hidden" name="memId" value="${sid}"><!-- session -->
			<input type="hidden" name="sangId" value="${sangId}">
			<input type="hidden" name="depth" value="0">
			<div class="input-group mb-3">
				<input type="text" class="form-control" name="qContent" placeholder="문의 답글 입력" aria-label="Recipient's username" aria-describedby="button-addon2">
				<button class="btn btn-outline-secondary" type="submit" id="button-addon2">작성</button>
			</div>
		</form>
	</div>
</div>
</c:if>

<c:choose>
	<c:when test="${not empty qarray}">
		<c:forEach var="i" items="${qarray}" varStatus="status">
			<div class="row">
				<div class="col">
					<div class="card">
						<div class="card-header">
						<c:choose>
							<c:when test="${i.memId eq sid}">							
								<div class="d-grid gap-2 d-md-flex justify-content-md-end">
									<button type="button" class="btn-close"  data-bs-toggle="modal" data-bs-target="${'#exampleModal'}${status.count}" aria-label="Close"></button>
								</div>	
							</c:when>
							<c:otherwise>
								<br>
							</c:otherwise>
						</c:choose>
						</div>
							<div class="card-body">
								${i.qnaContent}<br>
								<p style="font-size: 10px; color: gray;">${i.qnaDate}<p>
							</div>
						<div class="card-footer text-muted">
							<button class="btn btn-secondary" type="button" data-bs-toggle="collapse" data-bs-target="${'#collapseExample'}${status.count}" aria-expanded="false" aria-controls="collapseExample">
								답글보기 <span class="badge text-bg-light rounded-pill">${fn:length(qsmap[i.bunId])}</span>
							</button>
							<c:if test="${not empty qsmap[i.bunId]}">
							<c:forEach var="j" items="${qsmap[i.bunId]}" varStatus="st">
								<div class="collapse" id="${'collapseExample'}${status.count}">
									<div class="card card-body">
										<c:if test="${j.memId eq sid}">
											<div class="d-grid gap-2 d-md-flex justify-content-md-end">
												<button type="button" class="btn-close"  data-bs-toggle="modal" data-bs-target="${'#Modal'}${status.count}${st.count}" aria-label="Close"></button>
											</div>
										</c:if>
										${j.qnaContent}<br>
										<p style="font-size: 10px; color: gray;">${j.qnaDate}<p>
									</div>
								</div>
								<!-- Modal -->
									<form action="deleteQnaSub.do" method="post">
										<input type="hidden" name="sangId" value="${sangId}">
										<input type="hidden" name="qnaId" value="${j.qnaId}">
										<div class="modal fade" id="${'Modal'}${status.count}${st.count}" tabindex="-1" aria-labelledby="${'ModalLabel'}${status.count}${st.count}" aria-hidden="true">
											<div class="modal-dialog">
											    <div class="modal-content">
													<div class="modal-header">
											        	<h1 class="modal-title fs-5" id="${'ModalLabel'}${status.count}${st.count}">문의글 삭제</h1>
											        	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
												        해당 문의 답변글을 삭제합니다<br>
												        정말 삭제 하시겠습니까?
													</div>
													<div class="modal-footer">
												        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
												        <input class="btn btn-danger" type="submit" value="삭제">
													</div>
											    </div>
											</div>
										</div>
									</form>
							</c:forEach>
							</c:if>
							<br>
							<c:if test="${not empty sid}">
							<form action="insertQnaSub.do" method="post">
							<input type="hidden" name="memId" value="${sid}">
							<input type="hidden" name="sangId" value="${i.sangId}">
							<input type="hidden" name="depth" value="1">
							<input type="hidden" name="bunId" value="${i.qnaId}">
							<div class="collapse" id="${'collapseExample'}${status.count}">
							<br>
							<p style="color: red; font-size: .7em;">답글에 문의시 확인이 어렵습니다 새 문의글을 작성해주시길 바랍니다</p>
								<div class="input-group mb-3">
									<input type="text" class="form-control" name="qContent" placeholder="문의 답글 입력" aria-label="Recipient's username" aria-describedby="button-addon2">
									<button class="btn btn-outline-secondary" type="submit" id="button-addon2">작성</button>
								</div>
							</div>
							</form>
							</c:if>
						</div>
					</div>
					</div>
			</div>
		
			<!-- Modal -->
			<form action="deleteQnaMain.do" method="post" >
				<input type="hidden" name="sangId" value="${sangId}">
				<input type="hidden" name="memId" value="${i.memId}">
				<input type="hidden" name="bunId" value="${i.bunId}">
				<div class="modal fade" id="${'exampleModal'}${status.count}" tabindex="-1" aria-labelledby="${'exampleModalLabel'}${status.count}" aria-hidden="true">
					<div class="modal-dialog">
					    <div class="modal-content">
							<div class="modal-header">
					        	<h1 class="modal-title fs-5" id="${'exampleModalLabel'}${status.count}">문의글 삭제</h1>
					        	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
						        해당 문의글을 삭제시 답변글까지 모두 삭제됩니다<br>
						        정말 삭제 하시겠습니까?
							</div>
							<div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						        <input class="btn btn-danger" type="submit" value="삭제">
							</div>
					    </div>
					</div>
				</div>
			</form>
		
		</c:forEach>
	</c:when>
	<c:otherwise>
	<div class="qna">
		<br><h3>문의 내용이 없습니다</h3><br>
	</div>
	</c:otherwise>
</c:choose>

<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>

</body>
</html>