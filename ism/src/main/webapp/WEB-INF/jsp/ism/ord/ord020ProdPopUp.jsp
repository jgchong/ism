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
	width: 100%;
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

<!-- 상품등록 -->
<div class="layerCont order">
	<div class="inner">
		<p class="layerTit">매출상품등록</p>
		<div id="prodDetil" class="layerContents">
<form id="formprod" name="formprod" method="post" action="/ism/ord/saveOrderItemData.do">
<c:forEach var="item" items="${result}" varStatus="status">
<input type="hidden" id="coname" name="cum030id" value="${item.cum030id}" />
<input type="hidden" id="orderitemid" name="orderitemid" value="${item.cumprodcode}" />
<input type="hidden" id="itemcode" name="itemcode" value="${item.odm010id}" />
<input type="hidden" id="cumprodoptnm1" name="cumprodoptnm1" value="${item.orderitemopt}" />
			<div class='layerTb'>
				<table cellpadding='0' cellspacing='0' class='' summary='' >
					<caption></caption>
					<colgroup>
						<col width='15%'/><col width='35%'/>
						<col width='15%'/><col width='35%'/>
					</colgroup>
					<tbody>
						<tr>
							<th scope='row'>매출처</th>
							<td>${item.coname}</td>
						    <th scope='row'>매출상품코드</th>
							<td>${item.cumprodcode}</td>
						</tr>
						<tr>
							<th scope='row'>매출상품명</th>
							<td>${item.orderitemname}</td>
						    <th scope='row'>옵션</th>
							<td>${item.orderitemopt}</td>
						</tr>
					</tbody>
				</table>
			</div>
</c:forEach>
</form>
			<div class='layerTb mt10'>
			<form id="form1" name="form1" method="post" class="searchArea">                   
                    <div class="searchMore" style="height:60px;">
                        <ul style="float:left;width:80%;margin:4px 0;">
                            <li style="width:25%;"><input type="text" class="it" title="" value="" name="dtSearch_bycNm" placeHolder="매입처"/></li>
                            <li style="width:25%;"><input type="text" class="it" title="" value="" name="dtSearch_orderItemid" placeHolder="상품코드"/></li>
                            <li style="width:25%;"><input type="text" class="it" title="" value="" name="dtSearch_orderItemName" placeHolder="상품명"/></li>
                        </ul>
                        <p style="padding:0;margin-top:16px;"><a href="javascript:fn_prodSearch();" style="padding:7px 40px">검색</a></p>
                    </div>
                </form>
				<table cellpadding='0' cellspacing='0' class='' summary='' >
					<caption></caption>
					<colgroup>
						<col width='5%'/>
						<col width='15%'/>
						<col width='10%'/>
						<col width='50%'/>
						<col width='20%'/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">V</th>
							<th scope="col">매입처</th>
                            <th scope="col">상품코드</th>
                            <th scope="col">상품명</th>
                            <th scope="col">옵션</th>
						</tr>
					</thead>
					<tbody id='prodList'>
					</tbody>
				</table>
			</div>
			
		</div>
		<p class="layerFootBt">
			<a href="javascript:saveOrdProd();" class="confirm">확인</a>
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

$(document).ready(function() {
	
	$(document).on("click","#chk_info",function(){
		if ($(this).prop('checked')) {			
            //체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
            $('input[type="checkbox"][name="chk_info"]').prop('checked', false);
            $(this).prop('checked', true);
        }
	}); 
});

function saveOrdProd() {
	var chkItem = "";
	$("input:checkbox:checked").each(function (index) {  
		chkItem = $(this).attr("dataid");  
    }); 
	if(chkItem==""){
		alert("매핑할 상품을 선택하세요");
		return;
	}
	$("#itemcode").val(chkItem);
	
	console.log($('#formprod').serialize());
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
    	
	$("#formprod").ajaxSubmit(options);
}

//action="/ism/ord/updateOrderDetailresult.do"
function saveOrdProd123() {
	var chkItem = "";
	$("input:checkbox:checked").each(function (index) {  
		chkItem = $(this).attr("dataid");  
    }); 
	if(chkItem==""){
		alert("매핑할 상품을 선택하세요");
		return;
	}
	$("#itemcode").val(chkItem);
	
	var queryString = $("form[name=formprod]").serialize() ;

	$.ajax({
        url: "/ism/ord/saveOrderItemData.do",
        type: "post",
        data: queryString,
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
        	console.log(data);
            if (data == 'SUCCESS') {
            	alert("저장되었습니다.");
            	opener.opener_tempsearch();
            	window.close();
            }else{
            	alert("저장 중 오류가 발생했습니다.");
            }
        },
        error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'Not connect.\n Verify Network.';
            } else if (jqXHR.status == 404) {
                msg = 'Requested page not found. [404]';
            } else if (jqXHR.status == 500) {
                msg = 'Internal Server Error [500].';
            } else if (exception === 'parsererror') {
                msg = 'Requested JSON parse failed.';
            } else if (exception === 'timeout') {
                msg = 'Time out error.';
            } else if (exception === 'abort') {
                msg = 'Ajax request aborted.';
            } else {
                msg = 'Uncaught Error.<br>' + jqXHR.responseText;
            }
            alert("Error : " + msg);
        }
    });
}

function fn_prodSearch()
{
	var queryString = $("form[name=form1]").serialize() ;

	$.ajax({
        url: "/ism/ord/odo020SelectProdList.do",
        type: "post",
        data: queryString,
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
        	var addhtml = "";
        	
        	if (Object.keys(data.prodList).length > 0) {
				$.each(data.prodList,function(index, item) {
					addhtml = addhtml +
					"			<tr>"+
					"               <td><input type='checkbox' id='chk_info' name='chk_info' class='check_class' dataid='"+ item.itemcode +"'/></td>"+
					"				<td>"+item.bycname+"</td>"+
					"				<td>"+item.itemcode+"</td>"+
					"				<td>"+item.itemname+"</td>"+
					"				<td>"+item.itemopt +"</td>"+
					//"				<td>"+decodeURIComponent(item.bycurl.replace(Ca, " "))+"' name='bycurl'/></td>"+
					"			</tr>";
				});
			}
        	else{
        		addhtml = addhtml +
				"			<tr>"+
				"               <td colspan='5'>조회된 데이터가 없습니다.</td>"+
				"			</tr>";
        	}
        	$('#prodList').html(addhtml);
        },
        error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'Not connect.\n Verify Network.';
            } else if (jqXHR.status == 404) {
                msg = 'Requested page not found. [404]';
            } else if (jqXHR.status == 500) {
                msg = 'Internal Server Error [500].';
            } else if (exception === 'parsererror') {
                msg = 'Requested JSON parse failed.';
            } else if (exception === 'timeout') {
                msg = 'Time out error.';
            } else if (exception === 'abort') {
                msg = 'Ajax request aborted.';
            } else {
                msg = 'Uncaught Error.<br>' + jqXHR.responseText;
            }
            alert("Error : " + msg);
        }
    });

}
</script>