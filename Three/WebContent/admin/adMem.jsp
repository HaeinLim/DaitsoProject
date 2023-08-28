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
<title>관리자-회원정보 관리</title>
<style>
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
a:hover, a:visited, a:active, a:link {
	text-decoration: none;
	color: black;
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
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
}

.no .td3 {
	width: 20%;
}
</style>
<script>
	function searchCheck() { // 검색어 미입력시 검색버튼 누를 때 발생하는 알림창
		if (document.searchForm.keyWord.value == "") {
			alert("검색할 이름을 입력하지 않았습니다")
			return false;
		}
		return true;
	}

	function deleteCheck() { // 회원 삭제시 발생하는 확인 알림창, 삭제시 삭제확인 알림창 팝업
		if (confirm('정말 삭제하시겠습니까?') == true) {
			alert("삭제되었습니다");
		} else {
			return false;
		}
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
		
	<jsp:include page="adPageBar.jsp" />

	<div class="wrapper">
		<main id="client">
			<section class="clientInfo">
				<!-- 회원 정보를 조회(기본 가나다순), 삭제 할 수 있는 페이지 삭제시 삭제알람메시지 -->
				<h2 align=center>회원정보 관리</h2>
				<br>
				<div class="container">	<!-- 컨테이너랑 그리드 사용 -->
					<div class="row">
						<table class="table table-bordered table-hover" style="table-layout: fixed; text-align:center;">
							<tr>
								<td colspan=6 align=left>
									<!-- onsubmit을 통해 검색어 입력 확인 함수 호출 -->
									<form action="search.do" method="post" name="searchForm"
										onsubmit="return searchCheck()">
										<input type="text" name="keyWord" placeholder="검색할 회원 아이디 입력"
											maxlength="30" value="${param.keyWord }"> 
											<input class="btn btn-outline-primary" type="submit" value="검색">
										<input class="btn btn-outline-secondary" type="reset" value="취소">
										<!-- 검색창에 입력한 검색어 유지를 위한 value -->
									</form>
								</td>
							</tr>
							<tr>
								<td colspan=6 align=right>
									<form action="getAllinfo.do" method="post">
										<input class="btn btn-outline-secondary" type="submit" value="전체조회">
									</form>
								</td>
							</tr>
							<c:choose>
							<c:when test="${not empty alist1}">
							<tr>
								<th>아이디</th>
								<th>비밀번호</th>
								<th>이름</th>
								<th>전화번호</th>
								<th>이메일</th>
								<th>삭제</th>
							<tr>
								<c:forEach var="dto" items="${alist1 }">
									<!-- db에 저장된 값을 전부 가져올 때까지의 반복문 -->
									<tr>
										<td>${dto.memId }</td>
										<td>*****</td>
										<td>${dto.memName }</td>
										<td>${dto.memPhone }</td>
										<td>${dto.memEmail }</td>
										<td>
											<form action="delete.do?memId=${dto.memId }" method="post"
												name="deleteForm" onsubmit="return deleteCheck()">
												<input class="btn btn-danger" type="submit" value="삭제">
												<!-- 삭제버튼 클릭시 삭제 확인 함수 호출 -->
											</form>
										</td>
									</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
								<table class="no">
									<tr>
										<td class="td1"></td>
										<td class="td2"><br>
											<h3>검색 내용이 없습니다</h3> <br></td>
										<td class="td3"></td>
									</tr>
								</table>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
			</section>
		</main>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>