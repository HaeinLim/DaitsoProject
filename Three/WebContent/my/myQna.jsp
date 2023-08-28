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
<title>마이페이지 문의글 보기</title>
<style type="text/css">
	a:hover, a:visited, a:active, a:link {
	text-decoration : none;
		color : black;
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
		border-top-left-radius: 15px; border-top-right-radius: 15px;
        border-bottom-left-radius: 15px; border-bottom-right-radius: 15px;
	}
	.no .td3 {
		width: 20%; 	
	}
</style>
</head>
<body>
	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<jsp:include page="myPageBar.jsp"/>

<br>
<div class="row">	
	<div class="col"></div>
	<div class="col-6" style="font-size: 1.3em;">- ${nick} 님의 문의페이지</div>	
	<div class="col"></div>
</div>
<br>
<c:choose>
	<c:when test="${not empty qarray}">
		<c:forEach var="i" items="${qarray}" varStatus="status">
			<div class="row">
				<div class="col"></div>
				<div class="col-6">
					<div class="card">
						<div class="card-header">
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<button type="button" class="btn-close"  data-bs-toggle="modal" data-bs-target="${'#exampleModal'}${status.count}" aria-label="Close"></button>
							</div>
						</div>
							<div class="card-body">
								<p style="font-size: 13px; color: gray;">${snmap[i.qnaId]}</p>
								${i.qnaContent }<br>
								<div class="d-grid gap-2 d-md-flex justify-content-md-end">
									<div class="btn-group dropend">
									<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"></button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="sangDetail.do?sangId=${i.sangId}">해당 상품 보기</a></li>
										</ul>
									</div>
								</div>
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
										<c:if test="${j.memId eq sid}"><!-- session -->
											<div class="d-grid gap-2 d-md-flex justify-content-md-end">
												<button type="button" class="btn-close"  data-bs-toggle="modal" data-bs-target="${'#Modal'}${status.count}${st.count}" aria-label="Close"></button>
											</div>
										</c:if>
										${j.qnaContent}<br>
										<p style="font-size: 10px; color: gray;">${j.qnaDate}<p>
									</div>
								</div>
								<!-- Modal -->
									<form action="MyDeleteQnaSub.do" method="post">
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
						</div>
					</div>
				</div>
				<div class="col"></div>
			</div>
		
			<!-- Modal -->
			<form action="MyDeleteQnaMain.do" method="post" >
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
		<div style="height: 100px"></div>
		<table class="no">
			<tr>
				<td class="td1"></td>
				<td class="td2"><br><h3>문의 내용이 없습니다</h3><br></td>
				<td class="td3"></td>
			</tr>
		</table>
		<br>
	</c:otherwise>
</c:choose>

<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>