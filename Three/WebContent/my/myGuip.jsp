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

<title>결제 하기</title>
</head>
<body>
	 <% if(session.getAttribute("sid") == null)
		 out.print("<script>alert('로그인을 해주세요.'); location.href='main.do';</script>");%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 결제 API를 위한 jquery -->
 <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-1.12.4.min.js"
    ></script>
<script type="text/javascript" src="../js/jquery-3.7.0.js"></script>
<!-- 결제 API를 위한 iamport -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script>

    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
  function sample4_execDaumPostcode () {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
    
 // 결제 API
   
	
    
	function requestPay () {
		IMP.init("imp81616640");
		var pName = $('#pName').val();
		var pPhone = $('#pPhone').val();
		var pEmail = $('#pEmail').val();
		
		
		var pAdd1 = $('#sample4_roadAddress').val();
		var pAdd2 = $('#sample4_detailAddress').val();
		var pAdd3 = $('#sample4_extraAddress').val();
		var pAdd = pAdd1+" "+pAdd2+" "+pAdd3;
		
		var postCode = $('#sample4_postcode').val(); 
		
		var guipTotal = $('#guipTotal').val();
		var guipId = $('#guipId').val();
		
		if(postCode == "") {
			alert("주소를 입력하세요");
			document.getElementById('sample4_postcode').focus;
			return false;
		}
		else if(pAdd2 == "") {
			alert("주소를 입력하세요");
			document.getElementById('sample4_detailAddress').focus;
			return false;
		}
		else {
	    IMP.request_pay({
	      pg: "html5_inicis",
	      pay_method: "card",
	      merchant_uid: guipId,   // 주문번호 //구입 ID가 들어가야됨+
	      name: "구입번호:"+guipId,
	      amount: guipTotal,                         // 숫자 타입
	      buyer_email: pEmail,
	      buyer_name: pName,
	      buyer_tel: pPhone,
	      buyer_addr: pAdd,
	      buyer_postcode: postCode
	    }, function (rsp) { // callback
	      //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.      		
		      	if (rsp.success){
		      		
					var msg = '결제가 완료되었습니다.';
				     alert(msg);
				     document.payment.submit();            	
		      	}else {
		        	var msg = '결제에 실패하였습니다.';
		        	msg += '에러내용 : ' + rsp.error_msg;
		        	alert(msg);
		      	}
	    });
	}
 }
</script>

<jsp:include page="myPageBar.jsp"/>
<br>
<form action="payment.do" name="payment" method="post">
<div class="row">
<div class="col"></div>
<div class="col-5">
<br>
<h5>배송주소</h5>
<div class="input-group mb-3">
  <span class="input-group-text">우편번호</span>
  <input type="text" class="form-control" id="sample4_postcode" name="zipcode"  readonly required>
  <button type="button" class="btn btn-secondary" id="kakao"onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
</div>

<div class="input-group mb-3">
  <span class="input-group-text">도로명주소</span>
  <input type="text"  class="form-control"  id="sample4_roadAddress" name="road" readonly>
  <span class="input-group-text">지번주소</span>
<input type="text"  class="form-control"  id="sample4_jibunAddress" readonly>
</div>
<span id="guide" style="color:#999;display:none"></span>
<div class="input-group mb-3">
  <span class="input-group-text">상세주소</span>
  <input type="text"  class="form-control" id="sample4_detailAddress" name="detail" required>
  <span class="input-group-text">참고항목</span>
<input type="text"  class="form-control" id="sample4_extraAddress" readonly>
</div>
<br>
<h5>고객 정보</h5>
<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">이름</span>
  <input type="text" class="form-control"aria-label="Username" aria-describedby="basic-addon1" id="pName" value="${mdto.getMemName()}" readonly>
</div>

<div class="input-group mb-3">
  <span class="input-group-text">전화번호</span>
  <input type="text" class="form-control" id="pPhone" value="${mdto.getMemPhone()}" readonly>
</div>

<div class="input-group mb-3">
<span class="input-group-text">이메일</span>
  <input type="text" class="form-control" id="pEmail" value="${mdto.getMemEmail()}" readonly>
</div>
<br>


<h5>주문목록</h5>

<table class="table table-hover" style="text-align: center;">
  <thead>
   <tr>
          <th scope="col">상품이름</th>
      <th scope="col">상품갯수</th>
      <th scope="col">상품가격(당)</th>
   </tr>
  </thead>
   <tbody>
       <c:forEach var="i" items="${cArray}" >
       <input type="hidden" name="cartId" value="${i.cartId }">
      <c:if test="${not empty sangMap[i.sangId]}">
          <tr>
            <td>${sangMap[i.sangId].getSangName()}</td>
            <td>${i.sangCnt } (개)</td>
            <td>${sangMap[i.sangId].getSangPrice()} 원 </td>
          </tr>
      </c:if>
        </c:forEach>
          <tr>
             <th class="table-active" colspan="2">총 금액:</th>
             <th class="table-active" colspan="2" >${total }</th>          
          </tr>
      </tbody>

</table>
<span></span>
   
    
       

<br><br>
<input type="hidden" name="guipId" id="guipId" value="${guipId }">
<input type="hidden" name="guipTotal" id="guipTotal" value="${total }">

<div style="text-align: center;"><button class="btn btn-outline-dark" id="" onclick="requestPay()">결제하기</button></div><!--  type="button"  -->

</div>
<div class="col"></div>
</div>
<br>
</form>

<div style="position: fixed; bottom: 5px; right: 5px">
	<a href="#"><img src="../img/goup.png"></a>
</div>



<jsp:include page="../footer.jsp"/>

</body>
</html>