<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<style type="text/css">
	a {
		text-decoration: none;
	}
	.menut td a:link, table td a:hover, table td a:visited, table td a:active {
		color: white;
	}
	.menut {
		width: 100%; 
		background-color: black;
		text-align: center;
	}
	.menut td {
		position: sticky;
    	top: 0px;
	}
	.menut .td1 {
		width: 25%; 
	}
	.menut .td2 {
		width: 50%;		
	}
	.menut .td3, .td4 {
		width: 25%; 	
	}
	.menut .td2 a {
		 font-size: 50px; 
	}
	.menut .td3 a, .td4 a{
		 font-size: 19px; 
	}
	.barhr{
    	background-color: white; 
    	height: 3px;
	}
	.offcanvas-size{
    --bs-offcanvas-width: min(95vw, 250px) !important;
	}
	.accordian-size{
    --bs-accordion-border-width: min(95vw, 250px) !important;
    }
	.nav a, .nav a:link, .nav a:visited, .nav a:hover, .nav a:active{
		color: white;
	}
	.accordion-body a:hover, .accordion-body a:visited, .accordion-body a:active, .accordion-body a:link {
		color : black;
	}
	
</style>


<nav class="navbar navbar-dark bg-dark fixed-top">
	<!-- 첫번째 단 -->
	<table class="menut">
		<tr>
			<td class="td1"></td>
			<td class="td2"><a href="main.do"><b>DTO</b></a></td>
			<c:choose>
				<c:when test="${empty sid}">
					<td class="td3">
						<a href="/Three/home/login.jsp">로그인</a><span style="color: white;"> ㅣ </span><a href="/Three/home/join.jsp">회원가입</a>
					</td>
				</c:when>
				<c:when test="${sid == 'admin'}">
					<td class="td3">
						<a href="/Three/admin/adPage.jsp">관리자페이지</a><span style="color: white;"> ㅣ </span><a href="logout.do">로그아웃</a>     
					</td>
				</c:when>
				<c:otherwise>
					<td class="td4"><!-- session 값이 있을 시 -->
						<a href="myCart.do">장바구니</a><span class="badge text-bg-light"> ${cartCnt}</span><span style="color: white;"> ㅣ </span><a href="myPage.do">마이페이지</a><span style="color: white;"> ㅣ </span><a href="logout.do">로그아웃</a>     
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr><td colspan="4"><hr class="barhr"></td></tr>
	</table>

	<!-- 두번째 단 -->
	<div class="container-fluid">

	    <!-- 메뉴버튼 -->
	    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
	      <span class="navbar-toggler-icon"></span>
	    </button>
		
		
			<!-- 검색창 -->

		    <!-- 정렬 -->
		    <nav class="nav">
		    
				<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" ${ch == 'ch1'? 'disabled': ''}>
					<label class="btn btn-outline-light" for="btnradio1"><a href="main.do?cateId=${cateId}&order=${'starup'}&searchName=${searchName}&ch=${'ch1'}">별점 높은 순</a></label>
				
					<input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" ${ch == 'ch2'? 'disabled': ''}>
					<label class="btn btn-outline-light" for="btnradio2"><a href="main.do?cateId=${cateId}&order=${'stardown'}&searchName=${searchName}&ch=${'ch2'}">별점 낮은 순</a></label>
				
					<input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" ${ch == 'ch3'? 'disabled': ''}>
					<label class="btn btn-outline-light" for="btnradio3"><a href="main.do?cateId=${cateId}&order=${'priceup'}&searchName=${searchName}&ch=${'ch3'}">가격 높은 순</a></label>
				  
					<input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off" ${ch == 'ch4'? 'disabled': ''}>
					<label class="btn btn-outline-light" for="btnradio4"><a href="main.do?cateId=${cateId}&order=${'pricedown'}&searchName=${searchName}&ch=${'ch4'}">가격 낮은 순</a></label>
				</div>
				<span class="navbar-text" style="color:black;">______</span>
				<form action="main.do" class="d-flex" role="search">
		    	<c:choose>
			    	<c:when test="${searchName != 'none'}">
			    		<input class="form-control me-2" type="search" aria-label="Search" name="searchName" value="${searchName}">
			    	</c:when>
			   		<c:otherwise>
			   			<input class="form-control me-2" type="search" aria-label="Search" name="searchName" placeholder="검색어를 입력하세요">
			   		</c:otherwise>
			   	</c:choose>
			   	<input type="hidden" name="cateId" value="${cateId}">
		    	<button class="btn btn-outline-light" type="submit">Search</button>
		    </form>	
			</nav>
    	

	    <!-- offcanvas 창 -->
	    <div class="offcanvas offcanvas-start offcanvas-size"  data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
	    	<div class="offcanvas-header">
	    		<h5 class="offcanvas-title" id="offcanvasScrollingLabel">카테고리</h5>
	    		<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	  		</div>
	  		<div class="offcanvas-body">
	    		<div class="accordion accordion-flush accordian-size" id="accordionFlushExample">
	  				<div class="accordion-item">
	    				<h2 class="accordion-header" id="flush-heading1">
			      			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse1" aria-expanded="false" aria-controls="flush-collapse1">
			       		 		식품
			      			</button>
	    				</h2>
	    				<div id="flush-collapse1" class="accordion-collapse collapse" aria-labelledby="flush-heading1" data-bs-parent="#accordionFlushExample">
	      					<div class="accordion-body">
	      						<c:forEach var="i" items="${carray1}">
			      					<br>
			      					<div><a href="main.do?cateId=${i.cateId}">${i.cateName}</a></div>
		      					</c:forEach>
	      					</div>
	    				</div>
	    				<h2 class="accordion-header" id="flush-heading2">
			      			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse2" aria-expanded="false" aria-controls="flush-collapse2">
			       		 		생활
			      			</button>
	    				</h2>
		    			<div id="flush-collapse2" class="accordion-collapse collapse" aria-labelledby="flush-heading2" data-bs-parent="#accordionFlushExample">
		      				<div class="accordion-body">
	      						<c:forEach var="i" items="${carray2}">
			      					<br>
			      					<div><a href="main.do?cateId=${i.cateId}">${i.cateName}</a></div>
		      					</c:forEach>
	      					</div>
		    			</div>
		    			<h2 class="accordion-header" id="flush-heading3">
			      			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse3" aria-expanded="false" aria-controls="flush-collapse3">
			       		 		패션
			      			</button>
	    				</h2>
		    			<div id="flush-collapse3" class="accordion-collapse collapse" aria-labelledby="flush-heading3" data-bs-parent="#accordionFlushExample">
		      				<div class="accordion-body">
	      						<c:forEach var="i" items="${carray3}">
			      					<br>
			      					<div><a href="main.do?cateId=${i.cateId}">${i.cateName}</a></div>
		      					</c:forEach>
	      					</div>
		    			</div>
		    			<h2 class="accordion-header" id="flush-heading4">
			      			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse4" aria-expanded="false" aria-controls="flush-collapse4">
			       		 		잡화
			      			</button>
	    				</h2>
		    			<div id="flush-collapse4" class="accordion-collapse collapse" aria-labelledby="flush-heading4" data-bs-parent="#accordionFlushExample">
		      				<div class="accordion-body">
	      						<c:forEach var="i" items="${cmap[4]}">
			      					<br>
			      					<div><a href="main.do?cateId=${i.cateId}">${i.cateName}</a></div>
		      					</c:forEach>
	      					</div>
		    			</div>
		    			<h2 class="accordion-header" id="flush-heading5">
			      			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse5" aria-expanded="false" aria-controls="flush-collapse5">
			       		 		기타
			      			</button>
	    				</h2>
		    			<div id="flush-collapse5" class="accordion-collapse collapse" aria-labelledby="flush-heading5" data-bs-parent="#accordionFlushExample">
		      				<div class="accordion-body">
	      						<c:forEach var="i" items="${cmap[5]}">
			      					<br>
			      					<div><a href="main.do?cateId=${i.cateId}">${i.cateName}</a></div>
		      					</c:forEach>
	      					</div>
		    			</div>
	  				</div>
				</div>
	  		</div>
		</div><!-- offcanvas 창 end -->
	</div><!-- 두번째단 끝 -->
</nav>
