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
					<li><a href="">재고관리 바로가기</a></li>
					<li><a href="javascript://" onclick="viewWhsData('-1')" class="layerBt" name="whs">창고 신규등록</a></li>
				</ul>
				<div class="contents">
					<h2 class="pageTit">창고관리</h2>
					<div class="listTb">
						<table cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<col width="6%"/><col width="22%"/>
								<col width="6%"/><col width="11%"/>
								<col width="*"/><col width="6%"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">창고구분</th>
									<th scope="col">창고명</th>
									<th scope="col">사업자구분</th>
									<th scope="col">사업자번호</th>
									<th scope="col">창고주소</th>
									<th scope="col">사용여부</th>
								</tr>
							</thead>
							<tbody>
<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td>${result.whsgubunnm}</td>
									<td><a href="javascript://" onclick="viewWhsData('${result.whs010id}')" class="layerBt" name="whs"><c:out value="${result.whsname}"/></a></td>
									<td>${result.whscotypenm}</td>
									<td>${result.whscono}</td>
									<td>${result.whsadress}</td>
									<td>${result.useyn}</td>
								</tr>
</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</div> <!-- container -->
</div> <!-- wrap -->

<!-- 창고 등록 및 수정 [s]-->
<form id="form1" name="form1" method="post" enctype="multipart/form-data" action="/ism/whs/whs010SaveWhsAll.do">
<div class="layerCont whs">
	<div class="inner">
		<p class="layerTit">창고 등록 및 수정</p>
		<div id="whsDetailData" class="layerContents"><div class="inner" style="height:900px;"></div></div>
		<p class="layerFootBt">
			<a href="javascript:saveWhsAll();" class="confirm">확인</a>
			<a href="javascript://" onclick="closeLayerPop()" class="cancel layerClose">취소</a>
		</p>
		<a href="javascript://" onclick="closeLayerPop()" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>
<input type="hidden" id="hwhs010id" name="whs010id" />
</form>
<!-- 창고 등록 및 수정 [e]-->

<form id="formatdn" name="formatdn" method="post"></form>
<iframe name="tr" src="" width="0" height="0" frameborder="0" scrolling="no"></iframe>
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

function viewWhsData(whs010id) {
	$("#hwhs010id").val(whs010id);
	$.ajax({
        url : "/ism/whs/whs010SelectDetail.do",
        type: "post",
        data : { "whs010id" : whs010id },
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(data){
        	console.log(data);
        	var addhtml = ""+
			"<div class='layerTb'>"+
			"	<table cellpadding='0' cellspacing='0' class='c' summary='' >"+
			"		<caption></caption>"+
			"		<colgroup>"+
			"			<col width='15%'/><col width='35%'/>"+
			"			<col width='15%'/><col width='35%'/>"+
			"		</colgroup>"+
			"		<tbody>"+
			"			<tr>"+
			"				<th scope='row'>창고구분</th>"+
			"				<td>"+
			"                   <input type='radio' name='whsgubun' id='whsgubun_1' value='1'/><label for='sel1_1'>자사창고</label>"+
			"                   <input type='radio' name='whsgubun' id='whsgubun_1' value='2'/><label for='sel1_1'>외부창고</label>"+
			"				</td>"+
			"				<th scope='row'>창고명</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.whsname+"' id='whsname' name='whsname'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>사업자구분</th>"+
			"				<td>"+
			"					<input type='radio' name='whscotype' value='1' id='sel1_1'/><label for='sel1_1'>법인</label>"+
			"					<input type='radio' name='whscotype' value='2' id='sel1_2'/><label for='sel1_2'>개인</label>"+
			"					<input type='radio' name='whscotype' value='3' id='sel1_3'/><label for='sel1_3'>기타</label>"+
			"				</td>"+
			"				<th scope='row'>사업자번호</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.whscono+"' name='whscono'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>법인등록번호</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.whslawcono+"' name='whslawcono'/></td>"+
			"				<th scope='row'>업태/종목</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.whsbustype+"' name='whsbustype'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>창고주소</th>"+
			"				<td colspan='3' class='l'><input type='text' class='it ' title='' value='"+data.whsadress+"' name='whsadress'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>정산일</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.accontdate+"' name='accontdate'/></td>"+
			"				<th scope='row'>사용여부</th>"+
			"				<td>"+
			"					<input type='radio' name='useyn' id='sel4_1' value='Y' checked/><label for='sel4_1'>사용</label>"+
			"					<input type='radio' name='useyn' id='sel4_2' value='N'/><label for='sel4_2'>비사용</label>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>파일첨부<br/>(구비서류)</th>"+
			"				<td colspan='3' style='text-align:left;'>"+
			"                   <input type='text' class='it' style='width: 80%;' onclick='downLoadFile("+data.cmm020id+")' value='"+data.orgfilename+"' id='attachfilename' name='attachfilename' readonly /> &nbsp; "+
			"                   <label for='attachfile'>파일선택</label>"+
			"                   <input type='file' id='attachfile' name='attachfile' onchange='FileUpload(this)' class='hidden'/></td>"+
			"			</tr>"+
			"		</tbody>"+
			"	</table>"+
			"</div>"+
			"<div class='layerTb mt10'>"+
			"	<table id='userlist' cellpadding='0' cellspacing='0' class='c' summary='' >"+
			"		<caption></caption>"+
			"		<colgroup>"+
			"			<col width='15%'/><col width='20%'/>"+
			"			<col width='20%'/><col width='*'/>"+
			"			<col width='6%'/>"+
			"		</colgroup>"+
			"		<thead>"+
			"			<tr>"+
			"				<th scope='row' colspan='5' class='tit'>담당자</th>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>담당자명</th>"+
			"				<th scope='row'>연락처</th>"+
			"				<th scope='row'>이메일</th>"+
			"				<th scope='row'>메모</th>"+
			"				<th scope='row'><a href='javascript:addUser();' class='celPlus'>+</a></th>"+
			"			</tr>"+
			"		</thead>"+
			"		<tbody>";
			
			if (Object.keys(data.userlist).length > 0) {
				$.each(data.userlist,function(index, item) {
					addhtml = addhtml +
					"			<tr>"+
					"				<td><input type='text' class='it ' title='' value='"+item.username+"' name='whsusername'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+item.usertel+"' name='whsusertel'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+item.useremail+"' name='whsuseremail'/></td>"+
					"				<td colspan='1'><input type='text' class='it ' title='' value='"+item.memo+"' name='whsmemo'/></td>"+
					"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
					"			</tr>";
				});
			}else{
				addhtml = addhtml +
				"			<tr>"+
				"				<td><input type='text' class='it ' title='' value='' name='whsusername'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='whsusertel'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='whsuseremail'/></td>"+
				"				<td colspan='1'><input type='text' class='it ' title='' value='' name='whsmemo'/></td>"+
				"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
				"			</tr>";
			}

			addhtml = addhtml +
			"		</tbody>"+
			"	</table>"+
			"</div>"+
			"<div class='layerTb mt10'>"+
			"	<table id='shoplist' cellpadding='0' cellspacing='0' class='c' summary='' >"+
			"		<caption></caption>"+
			"		<colgroup>"+
			"			<col width='*'/><col width='15%'/>"+
			"			<col width='15%'/><col width='6%'/>"+
			"		</colgroup>"+
			"		<thead>"+
			"			<tr>"+
			"				<th scope='row' colspan='4' class='tit'>어드민</th>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>URL</th>"+
			"				<th scope='row'>아이디</th>"+
			"				<th scope='row'>패스워드</th>"+
			"				<th scope='row'><a href='javascript:addShop();' class='celPlus'>+</a></th>"+
			"			</tr>"+
			"		</thead>"+
			"		<tbody>";

			if (Object.keys(data.whs030).length > 0) {
				$.each(data.whs030,function(index, item) {
					addhtml = addhtml +
					"			<tr>"+
					"				<td><input type='text' class='it ' title='' value='"+item.whsurl+"' name='whsurl'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+item.whsuid+"' name='whsuid'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+item.whspwd+"' name='whspwd'/></td>"+
					"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
					"			</tr>";
				});
			}else{
				addhtml = addhtml +
				"			<tr>"+
				"				<td><input type='text' class='it ' title='' value='' name='whsurl'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='whsuid'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='whspwd'/></td>"+
				"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
				"			</tr>";
			}
			
			addhtml = addhtml +
			"		</tbody>"+
			"	</table>"+
			"</div>"+
			"<div class='layerTb mt10'>"+
			"	<table cellpadding='0' cellspacing='0' class='' summary='' >"+
			"		<caption></caption>"+
			"		<colgroup>"+
			"			<col width='100%'/>"+
			"		</colgroup>"+
			"		<tbody>"+
			"			<tr>"+
			"				<th scope='row'>메모내용</th>"+
			"			</tr>"+
			"			<tr>"+
			"				<td>"+
			"                   <div class='memoTxt'><ul id='memoul'>"+data.whsMemo+"</ul></div>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<td>"+
			"					<p class='memo'>"+
			"						<input type='text' class='it' title='' value='' id='inputmemo' name='inputmemo'/>"+
			"						<a onclick='inputmemodata(\"ST\","+data.whs010id+")'>입력</a>"+
			"					</p>"+
			"				</td>"+
			"			</tr>"+
			"		</tbody>"+
			"	</table>"+
			"</div>";
        	$('#whsDetailData').html(addhtml);

        	if (data.whsgubun != "") {
            	$('input:radio[name=whsgubun]:input[value=' + data.whsgubun + ']').attr("checked", true);
        	}

        	if (data.whscotype != "") {
            	$('input:radio[name=whscotype]:input[value=' + data.whscotype + ']').attr("checked", true);
        	}

        	if (data.useyn != "") {
            	$('input:radio[name=useyn]:input[value=' + data.useyn + ']').attr("checked", true);
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
		"				<td><input type='text' class='it ' title='' value='' name='whsusername'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='whsusertel'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='whsuseremail'/></td>"+
		"				<td colspan='1'><input type='text' class='it ' title='' value='' name='whsmemo'/></td>"+
		"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
		"			</tr>";
	$('#userlist > tbody:last').append(addRow);
}

function addShop() {
	var addRow = 
		"			<tr>"+
		"				<td><input type='text' class='it ' title='' value='' name='whsurl'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='whsuid'/></td>"+
		"				<td><input type='text' class='it ' title='' value='' name='whspwd'/></td>"+
		"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
		"			</tr>";
	$('#shoplist > tbody:last').append(addRow);
}

function saveWhsAll() {
	if ($('#whsname').val().trim() == "") {
		alert("창고명은 필수 입력 사항입니다.");
		return;
	}

	var userlist = "";
	var userlistRet = true;
	var shoplist = "";
	var shoplistRet = true;
	var shoplistCnt = 0;

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
	    td.eq(2).find("input").val().trim();

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
		alert("URL은 필수 입력 사항입니다.");
		return;
	}
	
    var options = {
    	success : function(data) {
        	console.log(data);
        	var retVal = data.split(",");
        	if (retVal[0] == "SUCCESS") {
        		alert("저장되었습니다.");
        		$('#hwhs010id').val(retVal[1]);
        		isSave = "T";
        		viewWhsData(retVal[1]);
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

function closeLayerPop() {
	if (isSave == "T") {
		location.href="/ism/whs/whs010.do";
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


function downLoadFile(cmm020id) {
	console.log(cmm020id);
	if (cmm020id > 0) {
		T = document.formatdn;
		//임시저장
		//var t = T.target;
		//var a = T.action;
		T.target	= "tr";
		T.action	= "/ism/cmm/attachFileDown.do?cmm020id="+cmm020id;
		//복구
		T.submit();
		//T.target	= t;
		//T.action	= a;	
	}
}
</script>