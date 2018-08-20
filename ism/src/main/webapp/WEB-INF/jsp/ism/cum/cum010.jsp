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
	<script src="/js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />
<style type="text/css">
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
.layerTb table td input.hidden {
    display: none;
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
.layerTb .sel3 {
    width: 30%;
}
</style>
</head>
<body>
<!-- 전체 레이어 시작 -->
<div class="wrap">
	<c:import url="/sym/mms/EgovMainMenuHead.do" />
	<div class="container">
	    <!-- 좌측메뉴 시작 -->
	    <div class="lnb">
	    	<c:import url="/sym/mms/EgovMainMenuLeft.do" />
	    </div>
	    <!-- //좌측메뉴 끝 -->
		<!-- 이부부까지가 기본 -->

			<div class="contentsWrap">
				<ul class="topBt">
					<li><a href="/ism/byc/byc010.do">매입처관리 바로가기</a></li>
					<li><a href="javascript://" onclick="viewCumData('-1')" class="layerBt" name="sales">매출처 신규등록</a></li>
				</ul>
				<div class="contents">
					<h2 class="pageTit">매출처관리</h2>
					<div class="listTb">
						<table cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<!--<col width="5%"/>//-->
								<col width="13%"/><col width="18%"/>
								<col width="8%"/><col width="8%"/>
								<col width="26%"/><col width="10%"/><col width="5%"/>
								<col width="5%"/>
							</colgroup>
							<thead>
								<tr>
									<!-- <th scope="col"><input type="checkbox" value="" name=""/></th> -->
									<th scope="col">업체별분류</th>
									<th scope="col">상호</th>
									<th scope="col">사업자구분</th>
									<th scope="col">사업자번호</th>
									<th scope="col">쇼핑몰명</th>
									<th scope="col">매출 업로드방식</th>
									<th scope="col">거래여부</th>
									<th scope="col">사용여부</th>
								</tr>
							</thead>
							<tbody>
<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<!-- <td><input type="checkbox" value="" name=""/></td> -->
									<td>${result.cotype1nm} > ${result.cotype2nm} > ${result.cotype3nm}</td>
									<td><a href="javascript://" onclick="viewCumData('${result.cum010id}')" class="layerBt" name="sales"><c:out value="${result.coname}"/></a></td>
									<td>${result.cogubunnm}</td>
									<td>${result.cono}</td>
									<td>${result.shopmallname}</td>
									<td>${result.uploadtype}</td>
									<td>${result.useyn}</td>
									<td>${result.shopuseyn}</td>
								</tr>
</c:forEach>
							</tbody>
						</table>
					</div>
<!-- 
					<div class="paging">
						<a href="#">&lt;&lt;</a>
						<a href="#">&lt;</a>
						<span>
							<a href="#" class="on">1</a>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#">4</a>
							<a href="#">5</a>
							<a href="#">6</a>
							<a href="#">7</a>
							<a href="#">8</a>
							<a href="#">9</a>
							<a href="#">10</a>
						</span>
						<a href="#">&gt;</a>
						<a href="#">&gt;&gt;</a>
					</div>
 -->
				</div>
			</div>
	</div> <!-- container -->
</div> <!-- wrap -->

<!-- 매출처 등록 및 수정 -->
<form id="form1" name="form1" method="post" enctype="multipart/form-data" action="/ism/cum/saveCumAll.do">
<div class="layerCont sales">
	<div class="inner">
		<p class="layerTit">매출처 등록 및 수정</p>
		<div id="cumDetailData" class="layerContents"><div class="inner" style="height:900px;"></div></div>
		<p class="layerFootBt">
			<a href="javascript:saveCumAll();" class="confirm">확인</a>
			<a href="javascript://" onclick="closeLayerPop()" class="cancel layerClose">취소</a>
		</p>
		<a href="javascript://" onclick="closeLayerPop()" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>
<input type="hidden" id="hcum010id" name="cum010id" />
</form>
</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var Ca = /\+/g;
var isSave = "F"; //저장처리가 되었는지 확인 화면 리플레시 여부 확인용
$(document).ready(function() {
});

function viewCumData(cum010id) {
	$("#hcum010id").val(cum010id);
	$.ajax({
        url : "/ism/cum/cum010SelectDetail.do",
        type: "post",
        data : { "cum010id" : cum010id },
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(data){
        	console.log(data);
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
            alert("Error : "+msg);
        }
    });
}

isNewUpload = 0; //첨부파일을 새로 업로드 했는지 여부. 다운로드시 새로 첨부된 경우 alert창 표시
function FileUpload(obj) {
	$('#attachfilename').val($(obj).val());
	isNewUpload = 1;
}
//메모입력
function inputmemodata(buss_type, buss_key) {
	if (!buss_key) {
		alert("매출처 저장 후 메모 등록이 가능합니다.");
		return;
	}
	var inputmemo = $('#inputmemo').val();
	savememodata(buss_key, buss_type, inputmemo, $('#memoul'));
	$('#inputmemo').val("");
	return false;
}
function addUser() {
	var addRow = 
		"			<tr>"+
		"				<td><input type='text' class='it ' title='' value='' name='cumusername'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='cumusertel'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='cumuseremail'/></td>"+
		"				<td colspan='1'><input type='text' class='it ' title='' value='' name='cummemo'/></td>"+
		"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
		"			</tr>";
	$('#userlist > tbody:last').append(addRow);
}

function addShop() {
	var addRow = 
		"			<tr>"+
		"				<td><input type='text' class='it ' title='' value='' name='shopmallname'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='shopurl'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='shopuid'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='shoppwd'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='uploadtype'/></td>"+
		"               <td><select name='shopUseYn'><option value='Y'>Y</option><option value='N'>N</option></select></td>"+
		"               <td colspan='1'><select name='uploadgubun'><option value='M'>수동</option><option value='A'>API</option></select></td>"+
		"				<td><a href='javascript://' onclick='delrow(this, 1)'>del</a></td>"+
		"			</tr>";
	$('#shoplist > tbody:last').append(addRow);
}

function saveCumAll() {
	if ($('#coname').val().trim() == "") {
		alert("상호는 필수 입력 사항입니다.");
		return;
	}
	
	if ($("#cotype1").val() == "") {
		alert("업체구분 대분류는 필수 입력 사항입니다.");
		return;
	}
	
	var userlist = "";
	var userlistRet = true;
	var shoplist = "";
	var shoplistRet = true;
	var shoplistCnt = 0;

	//담당자 항목에 한 필드라도 입력했으면 담당자명 체크
	$('#userlist tbody tr').each(function () {
	    var td = $(this).children();
	    userlist = "" +
	    td.eq(0).find("input").val().trim() +
	    td.eq(1).find("input").val().trim() +
	    td.eq(2).find("input").val().trim() +
	    td.eq(3).find("input").val().trim();

		if (userlist != "") {
			if (td.eq(0).find("input").val().trim() == "") {
				userlistRet = false;
				return false;
			}
		}
	});
	
	if (!userlistRet) {
		alert("담당자명은 필수 입력 사항입니다.");
		return;
	}
	
	$('#shoplist tbody tr').each(function () {
	    var td = $(this).children();
	    shoplist = ""+
	    td.eq(0).find("input").val().trim() +
	    td.eq(1).find("input").val().trim() +
	    td.eq(2).find("input").val().trim() +
	    td.eq(3).find("input").val().trim() +
	    td.eq(4).find("input").val().trim();

		if (shoplist != "") {
			if (td.eq(0).find("input").val().trim() == "") {
				shoplistRet = false;
				return false;
			}else{
				shoplistCnt++;
			}
		}
	});
	
	if (!shoplistRet) {
		alert("쇼핑몰명은 필수 입력 사항입니다.");
		return;
	}
	
	if (shoplistCnt == 0) {
		alert("1개이상의 쇼핑몰을 등록해야합니다.");
		return;
	}
	
    var options = {
    	success : function(data) {
        	console.log(data);
        	var retVal = data.split(",");
        	if (retVal[0] == "SUCCESS") {
        		alert("저장되었습니다.");
        		$('#hcum010id').val(retVal[1]);
        		isSave = "T";
        		viewCumData(retVal[1])
        		//location.href="/ism/cum/cum010.do";
        	}else{
        		alert("저장 중 오류가 발생했습니다.");        		
        	}
        },
        error : function(xhr, status, error) {
        	console.log(error);
        },
        type : "POST"
    };
	$("#form1").ajaxSubmit(options);
}

function cotypeChg(gubun) {
	var selval = $("#cotype"+gubun+" option:selected").val();
	var appOption = "";

	if (gubun == 1) {
		if (selval == "ISM031") appOption = "${ISM031}";
		else if (selval == "ISM032") appOption = "${ISM032}";

		$("#cotype2").html("<option value=''>중분류</option>" + appOption);
	}else if (gubun == 2) {
		if (selval == "ISM041") appOption = "${ISM041}";
		else if (selval == "ISM042") appOption = "${ISM042}";
		else if (selval == "ISM043") appOption = "${ISM043}";
		else if (selval == "ISM044") appOption = "${ISM044}";

		$("#cotype3").html("<option value=''>소분류</option>" + appOption);
	}
}

function closeLayerPop() {
	if (isSave == "T") {
		location.href="/ism/cum/cum010.do";
	}else{
		isSave = "F";
	}
}

function delrow(obj, isshop) {
	if ($(obj).parent().parent().parent().children().length == 1) {
		if (isshop == 1) {
			alert("쇼핑몰 어드민은 1개이상 존재해야합니다.");
		}else{
			$(obj).parent().parent().find("input").val("");	
		}
	}else{
		$(obj).parent().parent().remove();
	}
}
</script>