<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.deImgs {
	text-align: center;
	margin-top: 15px;
}

.img {
	width:600px;
	height:400px;
}
</style>
</head>
<body>
	<div class="deImgs">
		<img src="<%=request.getContextPath()%>/detail_imgs/${sd.detPipath1 }" class="img" onerror="this.style.display='none'"><br> 
		<img src="<%=request.getContextPath() %>/detail_imgs/${sd.detPipath2 }" class="img" onerror="this.style.display='none'"><br> 
		<img src="<%=request.getContextPath() %>/detail_imgs/${sd.detPipath3 }" class="img" onerror="this.style.display='none'"> <br>
		<img src="<%=request.getContextPath() %>/detail_imgs/${sd.detPipath4 }" class="img" onerror="this.style.display='none'"><br>
	</div>
</body>
</html>