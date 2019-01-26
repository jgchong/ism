<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> KTI NMS </title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />

<style type="text/css">
.layerCont {
	width: 105%;
    top: 0;
    left: 0%;
    z-index: 0;
    display: block;
    max-height: 100%;
}
li img {
    top: 16px;
    left: 15px;
    width: 50px;
    height: 50px;
    border-radius: 100%;
}
.inmemo {
	float: left;
	text-align:center;
}
.inmemotxt {
	float: left;
	margin-left: 16px;
	border: 1px solid #ccc;
	width: 72%;
	border-radius: 8px;
	min-height:45px;
}
.memotime {
	float:right;
}
.memoTxt li {
	height : 75px;
}
.layerTb table td .memo a {
    position: absolute;
    top: 0;
    right: 0;
    padding: 0 15px;
    line-height: 28px;
    background: #457cac;
    border: 0;
    font-size: 14px;
    color: #fff;
    vertical-align: -2px;
}
</style>

</head>
<body>
<!-- 전체 레이어 시작 -->
<!-- 전체 레이어 끝 -->

<!-- 주문현황관리 -->

<div class="layerCont order">
	<div class="inner">
	
		<p class="layerTit">주문현황관리</p>
<form id="formorder" name="formorder" method="post" action="/ism/ord/updateOrderDetailData.do">
<c:forEach var="item" items="${result}" varStatus="status">
		<div id="orderDetil" class="layerContents" style="width:705px;float:left;padding-right:8px;">
			<div class='layerTb'>
				<table cellpadding='0' cellspacing='0' class='' summary='' >
					<caption></caption>
					<colgroup>
						<col width='15%'/><col width='35%'/>
						<col width='15%'/><col width='35%'/>
					</colgroup>
					<tbody>
						<tr>
							<th scope='row'>사업자구분</th>
							<td colspan="3">${item.code_nm} / ${item.cotype2nm} / ${item.cotype3nm}</td>
						</tr>
						<tr>
						    <th scope='row'>쇼핑몰명</th>
							<td>${item.shopmallname}</td>
							<th scope='row'>판매채널</th>
							<td></td>
						</tr>
						<tr>
						    <th scope='row'>매입처</th>
							<td>${item.bycname}</td>
							<th scope='row'>매출처</th>
							<td>${item.coname}</td>
						</tr>
						<tr>
						   <th scope='row'>상품코드</th>
							<td>${item.orderitemid}</td>
							<th scope='row'>주문번호</th>
							<td>${item.orderno}</td>
						</tr>
						<tr>
						    <th scope='row'>택배사</th>
							<td><input type='text' name='dlvco' value='${item.dlvco}' /></td>
							<th scope='row'>송장번호</th>
							<td><input type='text' name='dlvno' value='${item.dlvno}' /></td>
						</tr>
						<tr>
							<th scope='row'>상품명</th>
							<td><textarea name='orderitemname' style='width:100%;'>${item.orderitemname}</textarea></td>
							<th scope='row'>수량</th>
							<td>${item.orderitemqty}</td>
						</tr>
						<tr>
							<th scope='row'>옵션</th>
							<td><input type='text' name='orderitemopt' value='${item.orderitemopt}' /></td>
							<th scope='row'>배송메모</th>
							<td><input type='text' name='dlvmemo' value='${item.dlvmemo}' /></td>
						</tr>
						<tr>
						    <th scope='row'>주문자</th>
							<td><input type='text' name='orderuser' value='${item.orderuser}' /></td>
							<th scope='row'>주문자 연락처</th>
							<td><input type='text' name='orderusercontact' value='${item.orderusercontact}' /></td>
						</tr>
						<tr>
							<th scope='row' rowspan="2">수령자</th>
							<td rowspan="2"><input type='text' name='rcvuser' value='${item.rcvuser}' /></td>
							<th scope='row'>수령자 TEL</th>
							<td><input type='text' name='rcvusercontact' value='${item.rcvusercontact}' /></td>
						</tr>
						<tr>
							<th scope='row'>수령자 H.P</th>
							<td><input type='text' name='rcvusercontacthp' value='${item.rcvusercontacthp}' /></td>
						</tr>
						<tr>
							<th scope='row'>주소(우편번호)</th>
							<td colspan="3">
								<input type='text' id='postno' name='postno' value='${item.postno}' size="6" /> &nbsp; <a href='javascript:execDaumPostcode("${item.address}");'>우편번호찾기</a>
								<textarea name='address' style='width:100%;'>${item.address}</textarea>
							</td>
						</tr>
						<tr>
							<th scope='row'>매입단가</th>
							<td>${item.itembuyprice} 원</td>
							<th scope='row'>매입배송비</th>
							<td>${item.itembuydlvprice} 원</td>
						</tr>
						<tr>
							<th scope='row'>정산가<c:if test="${item.account2 eq '1'}">(공급)</c:if><c:if test="${item.account2 eq '2'}">(판매)</c:if></th>
							<td><input type='text' style='text-align:right;' name='orderitemprice' value='${item.orderitemprice}' /> 원</td>
							<th scope='row'>배송비</th>
							<td><input type='text' style='text-align:right;' name='dlvprice' value='${item.dlvprice}' /> 원</td>
						</tr>
						<!--tr>
							<th scope='row'>판매단가</th>
							<td></td>
							<th scope='row'>창고선택</th>
							<td>
							    <select></select>
							</td>
						</tr-->
						<tr>
							<th scope='row'>주문일자</th>
							<td>${item.orderdate}</td>
							<th scope='row'>처리일자</th>
							<td>${item.processdate}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<input type="hidden" id="odm010id" name="odm010id" value="${item.odm010id}" />
<c:set var="cstype" value="${item.cstype}" />
<c:set var="accountcum" value="${item.accountcum}" />
<c:set var="accountbuy" value="${item.accountbuy}" />
<c:set var="claimstatus" value="${item.claimstatus}" />
<c:set var="mainstatus" value="${item.status}" />
<c:set var="claimreason" value="${item.claimreason}" />
<c:set var="retstatus" value="${item.retstatus}" />
<c:set var="retqty" value="${item.retqty}" />
<c:set var="retprice" value="${item.retprice}" />
</c:forEach>
</form>
<c:forEach var="item" items="${resultComp}" varStatus="status">
		<div id="orderDetil" class="layerContents" style="width:705px;float:left;padding-right:8px;">
			<div class='layerTb'>
				<table cellpadding='0' cellspacing='0' class='' summary='' >
					<caption></caption>
					<colgroup>
						<col width='15%'/><col width='35%'/>
						<col width='15%'/><col width='35%'/>
					</colgroup>
					<tbody>
						<tr>
							<th scope='row'>사업자구분</th>
							<td colspan="3">${item.code_nm} / ${item.cotype2nm} / ${item.cotype3nm}</td>
						</tr>
						<tr>
						    <th scope='row'>쇼핑몰명</th>
							<td>${item.shopmallname}</td>
							<th scope='row'>판매채널</th>
							<td></td>
						</tr>
						<tr>
						    <th scope='row'>매입처</th>
							<td>${item.bycname}</td>
							<th scope='row'>매출처</th>
							<td>${item.coname}</td>
						</tr>
						<tr>
						   <th scope='row'>상품코드</th>
							<td>${item.orderitemid}</td>
							<th scope='row'>주문번호</th>
							<td>${item.orderno}</td>
						</tr>
						<tr>
						    <th scope='row'>택배사</th>
							<td><input type='text' name='compdlvco' value='${item.dlvco}' /></td>
							<th scope='row'>송장번호</th>
							<td><input type='text' name='compdlvno' value='${item.dlvno}' /></td>
						</tr>
						<tr>
							<th scope='row'>상품명</th>
							<td><textarea name='comporderitemname' style='width:100%;'>${item.orderitemname}</textarea></td>
							<th scope='row'>수량</th>
							<td>${item.orderitemqty}</td>
						</tr>
						<tr>
							<th scope='row'>옵션</th>
							<td><input type='text' name='comporderitemopt' value='${item.orderitemopt}' /></td>
							<th scope='row'>배송메모</th>
							<td><input type='text' name='compdlvmemo' value='${item.dlvmemo}' /></td>
						</tr>
						<tr>
						    <th scope='row'>주문자</th>
							<td><input type='text' name='comporderuser' value='${item.orderuser}' /></td>
							<th scope='row'>주문자 연락처</th>
							<td><input type='text' name='comporderusercontact' value='${item.orderusercontact}' /></td>
						</tr>
						<tr>
							<th scope='row' rowspan="2">수령자</th>
							<td rowspan="2"><input type='text' name='comprcvuser' value='${item.rcvuser}' /></td>
							<th scope='row'>수령자 TEL</th>
							<td><input type='text' name='comprcvusercontact' value='${item.rcvusercontact}' /></td>
						</tr>
						<tr>
							<th scope='row'>수령자 H.P</th>
							<td><input type='text' name='comprcvusercontacthp' value='${item.rcvusercontacthp}' /></td>
						</tr>
						<tr>
							<th scope='row'>주소(우편번호)</th>
							<td colspan="3">
								<input type='text' id='comppostno' name='comppostno' value='${item.postno}' size="6" /> &nbsp; <a href='javascript:execDaumPostcode("${item.address}");'>우편번호찾기</a>
								<textarea name='compaddress' style='width:100%;'>${item.address}</textarea>
							</td>
						</tr>
						<tr>
							<th scope='row'>매입단가</th>
							<td>${item.itembuyprice} 원</td>
							<th scope='row'>매입배송비</th>
							<td>${item.itembuydlvprice} 원</td>
						</tr>
						<tr>
							<th scope='row'>정산가<c:if test="${item.account2 eq '1'}">(공급)</c:if><c:if test="${item.account2 eq '2'}">(판매)</c:if></th>
							<td><input type='text' style='text-align:right;' name='comporderitemprice' value='${item.orderitemprice}' /> 원</td>
							<th scope='row'>배송비</th>
							<td><input type='text' style='text-align:right;' name='compdlvprice' value='${item.dlvprice}' /> 원</td>
						</tr>
						<!--tr>
							<th scope='row'>판매단가</th>
							<td></td>
							<th scope='row'>창고선택</th>
							<td>
							    <select></select>
							</td>
						</tr-->
						<tr>
							<th scope='row'>주문일자</th>
							<td>${item.orderdate}</td>
							<th scope='row'>처리일자</th>
							<td>${item.processdate}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
</c:forEach>
		<p class="layerFootBt">
			<a href="javascript:saveDetail();" class="confirm">확인</a>
			<a href="javascript:window.close();" class="cancel">취소</a>
		</p>
		<a href="javascript:window.close();" class="layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>


</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var Ca = /\+/g;
var claim1Html = ""+
"<table id='clmtable1' cellpadding='0' cellspacing='0' class='' summary='' >"+
"	<caption></caption>"+
"	<colgroup>"+
"		<col width='30%'/><col width='70%'/>"+
"	</colgroup>"+
"	<tbody>"+
"		<tr>"+
"			<th scope='row'>클레임 처리 상태</th>"+
"			<td>"+
"				<select id='claimstatus' name='claimstatus' title=''>";

<c:forEach var="item" items="${ISM060}" varStatus="status">
	claim1Html += "<option value='${item.code}' selected='selected'>${item.codeNm}</option>";
</c:forEach>

claim1Html +=
"				</select>"+
"			</td>"+
"		</tr>"+
"	</tbody>"+
"</table>";
var claim2Html = ""+
"<table id='clmtable2' cellpadding='0' cellspacing='0' class='' summary='' >"+
"	<caption></caption>"+
"	<colgroup>"+
"		<col width='100%'/>"+
"	</colgroup>"+
"	<tbody>"+
"		<tr>"+
"			<th scope='row'>클레임 사유</th>"+
"		</tr>"+
"		<tr>"+
"			<td>";
<c:forEach var="item" items="${ISM070}" varStatus="status">
claim2Html += "<input type='radio' value='${item.code}' name='claimreason'/><label for='sel5_1'>${item.codeNm}</label>"
</c:forEach>
claim2Html +=
"			</td>"+
"		</tr>"+
"	</tbody>"+
"</table>";
var returnHtml = ""+
"<table id='rtntable' cellpadding='0' cellspacing='0' class='' summary=''>"+
"	<caption></caption>"+
"	<colgroup>"+
"		<col width='100%'>"+
"	</colgroup>"+
"	<tbody>"+
"		<tr>"+
"			<th scope='row'>상태값 변경</th>"+
"		</tr>"+
"		<tr>"+
"			<td>";
<c:forEach var="item" items="${ISM080}" varStatus="status">
returnHtml += "<input type='radio' value='${item.code}' name='retstatus'/><label for='sel2_1'>${item.codeNm}</label>"
</c:forEach>
returnHtml +=
"				<br><input type='text' class='it7' title='' value='' id='retqty' name='retqty' placeholder='반품 수량 입력'>"+
"				<input type='text' class='it7' title='' value='' id='retprice' name='retprice' placeholder='반품비 입력'>"+
"			</td>"+
"		</tr>"+
"	</tbody>"+
"</table>";

$(document).ready(function() {

	if ('${accountcum}' != "") {
    	$('input:radio[name=accountcum]:input[value="${accountcum}"]').attr("checked", true);
	}

	if ('${accountbuy}' != "") {
    	$('input:radio[name=accountbuy]:input[value="${accountbuy}"]').attr("checked", true);
	}

	if ('${mainstatus}' != "") {
    	$('input:radio[name=status]:input[value="${mainstatus}"]').attr("checked", true);
	}
	
	if ('${cstype}' != "") {
		$('input:radio[name=cstype]:input[value="${cstype}"]').attr("checked", true);
	}
	
	if ('${cstype}' == 'C') {
		$('#statusset1').append(claim1Html);
		$('#statusset2').append(claim2Html);
		$("#claimstatus").val('${claimstatus}');
		$('input:radio[name=claimreason]:input[value="${claimreason}"]').attr("checked", true);
	}else if ('${cstype}' == 'R') {
		$('#statusset1').append(returnHtml);
		$('input:radio[name=retstatus]:input[value="${retstatus}"]').attr("checked", true);
		$('#retqty').val("${retqty}");
		$('#retprice').val("${retprice}");
	}
});

//클레임접수 또는 반품접수 라이오 버튼 클릭시
//type 1 : 클레임, 2 : 반품
function setCR(type) {
	$('#memotitle').text('메모내용');
	$('#rtntable').remove();
	$('#clmtable1').remove();
	$('#clmtable2').remove();
	if (type == 1) {
		$('#memotitle').text('클레임 메모내용');
		$('#statusset1').append(claim1Html);
		$('#statusset2').append(claim2Html);
	}else if (type == 2) {
		$('#memotitle').text('반품 메모내용');
		$('#statusset1').append(returnHtml);
	}else{
		$('#memotitle').text('메모내용');
		$('#rtntable').remove();
		$('#clmtable1').remove();
		$('#clmtable2').remove();
	}
}
//메모입력
function inputmemodata(buss_type, buss_key) {
	//buss_key = $('#odm010id').val(); 
	var inputmemo = $('#inputmemo').val();
	savememodata(buss_key, buss_type, inputmemo, $('#memoul'));
	$('#inputmemo').val("");
	return false;
}

//action="/ism/ord/updateOrderDetailresult.do"
function saveDetail() {
	console.log($('#formorder').serialize());
    var options = {
    	success : function(data) {
            console.log(data);
            if (data == 'SUCCESS') {
            	alert("저장되었습니다.");
            	opener.opener_tempsearch();
            	window.close();
            }else{
            	alert("저장 중 오류가 발생했습니다.");
            }
        },
        error : function(xhr, status, error) {
        	console.log(error);
        },
        type : "POST"
    };
    	
	$("#formorder").ajaxSubmit(options);
}
</script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function execDaumPostcode(address) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postno').value = data.zonecode; //5자리 새우편번호 사용
            }
        }).open({
        	q: address
        });
    }
</script>