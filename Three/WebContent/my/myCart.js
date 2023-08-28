let basket = {
    totalCount: 0, 
    totalPrice: 0,
    //체크한 장바구니 상품 비우기
    delCheckedItem: function(){
    	if(window.confirm("해당상품을 삭제하시겠습니까?")) {
    	var sarr = [];
        document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
        	var sangId = item.value;
        	sarr.push(sangId);
        	item.parentElement.parentElement.remove();
        });
        $.ajax({
			type : "post",
			url : "http://localhost:8200/Three/my/myCartDel.do",
			traditional: true, //배열 보낼시 사용
			data : {sangId:sarr},
			success : function(status) {						
				console.log(status);
			},
			error : function(status) {
				console.log(status);
			}
		}); 
		this.reCalc();
        this.updateUI();
        }      
    },
    allCheckedItem: function(selectAll){
        document.getElementsByName('buy').forEach(function (item) {
    		item.checked = selectAll.checked;
  		});
        this.reCalc();
        this.updateUI();
    },
    //재계산
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            if(item.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.checked == true){
                var count = parseInt(item.getAttribute('value'));
                this.totalCount += count;
                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
                this.totalPrice += count * price;
            }
        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
    },
    //화면 업데이트
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent =this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent =this.totalPrice.formatNumber() + '원';
    },
    //개별 수량 변경
    changePNum: function (pos) {
        var item = document.querySelector('input[name=p_num'+pos+']');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        if(newval == ''){
        	alert("주문가능한 최소수량은 1개입니다")
        	item.setAttribute('value', 1);
        	item.value = 1;
        	return false;
        }
        var max = document.getElementById('max'+pos).value;
        
        if(max ==0) {
        	alert("현재 상품 수량이 없습니다");
        	item.setAttribute('value', max);
        	item.value = max;
        	return false;
        }
        if (parseInt(newval) < 1) { 
        	alert("주문가능한 최소수량은 1개입니다");
        	item.setAttribute('value', 1);
        	item.value = 1;
        	return false; 
        }
        else if(parseInt(newval) > max) {
        	alert("주문가능한 최대수량은 " + max +"개입니다");
        	item.setAttribute('value', max);
        	item.value = max;
        	return false;
        }

        item.setAttribute('value', newval);
        item.value = newval;

        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
        var sangId = item.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.value;
		
        //AJAX 업데이트 전송
		$.ajax({
			type : "post",
			url : "http://localhost:8200/Three/my/myCartUp.do",
			traditional: true, //배열 보낼시 사용
			data : {sangId:sangId, sangCnt:newval},
			success : function(status) {						
				console.log(status);
			},
			error : function(status) {
				console.log(status);
			}
		}); 
        //전송 처리 결과가 성공이면    
        this.reCalc();
        this.updateUI();
    },
    checkItem: function () {
    	const checkboxes = document.querySelectorAll('input[name="buy"]'); // 선택된 체크박스
  		const checked = document.querySelectorAll('input[name="buy"]:checked'); // select all 체크박스
  		const selectAll = document.querySelector('input[name="selectall"]');
		if(checkboxes.length === checked.length)  {
			selectAll.checked = true;
		}else {
		    selectAll.checked = false;
		}		
        this.reCalc();
        this.updateUI();
    },
	delZero: function () {
    	if(window.confirm("품절상태인 상품을 모두 삭제하시겠습니까?")) {
    	var sarr1 = [];
        document.querySelectorAll('input[name="zero"]').forEach(function (item) {
	        	var sangId1 = item.value;
	        	sarr1.push(sangId1);
	        	item.parentElement.parentElement.remove();
        });
        $.ajax({
			type : "post",
			url : "http://localhost:8200/Three/my/myCartDel.do",
			traditional: true, //배열 보낼시 사용
			data : {sangId:sarr1},
			success : function(status) {						
				console.log(status);
			},
			error : function(status) {
				console.log(status);
			}
		});
		}    
    },
    pay : function(){
    	var ch = document.querySelectorAll('input[name="buy"]:checked');
    	if(ch.length == 0){
    		alert("상품을 선택해 주세요");
    		return false;
    	}else document.getElementById('payform').submit();    		
    }
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};