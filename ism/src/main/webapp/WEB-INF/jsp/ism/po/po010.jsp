<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> ISM </title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="/js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />
	
	<style type="text/css">
.topBt.leng3 li {
    width: 50%;
}
.connectedSortable li {
    margin: 5px;
}
.connectedSortable {
    min-height: 68px;
}
.listTit li {
    float: left;
    width: 200px;
    border-top: 1px solid #ccc;
    /*border-right: 1px solid #ccc;*/
    border-bottom: 2px solid #ccc;
	text-align: center;
    font-size: 14px;
    font-weight: 700;
}
/*
.listTit li:first-child {
    border-left: 1px solid #ccc;
}
*/
.listData {
	clear: both;
	text-align: center;
	cursor: pointer;
	display: inline-block;
}
.listData:hover   { background-color:#b0bde2 }

.listData li {
	float: left;
	width: 200px;
}
li.orderitemname {
	width: 250px;
}
li.orderitemqty {
	width: 50px;
}
li.delbtn {
	width: 50px;
}
input.orderitemqty {
	width: 48px;
	text-align: right;
}

	.muploadbtn {
		margin: 5px;
	    padding: 3px 12px;
	    border: 0;
	    background: #45b6b6;
	    color: #fff;
	    font-size: 14px;
	}
	.layerTit { margin: 0 0 0; }
	.upload { top: 31%; }
	
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
		<!-- 이부분까지가 기본 [s]-->
			<div class="contentsWrap">
				<ul class="topBt leng3">
					<li><a href="javascript:;" class="layerBt" name="upload">송장데이터 등록</a></li>
					<li><a href="javascript:;" class="layerBt" name="apiSetting">발주 API 환경설정</a></li>
				</ul>
				<div class="contents2">
					<ul class="depot">
<c:forEach var="result" items="${resultListWhs}" varStatus="status">
						<li>
							<strong>${result.whsname}</strong>
							<p>
								<a href="javascript://" class="layerBt ico1" onclick="openLayerPOSet('${result.whsname}','${result.whs010id}','W')" name="poSetting">발주환경설정</a>
								<a href="javascript://" class="layerBt ico3" onclick="openLayerPOList('${result.whsname}','${result.whs010id}','W','${result.receivetype}')" name="poList">발주</a>
							</p>
						</li>
</c:forEach>
					</ul>

					<div class="shop">
						<ul class="shopList">
<c:forEach var="result" items="${resultListByc}" varStatus="status">
	<c:if test="${result.uploadgubun eq 'M'}">
							<li>
								<p class="tit">${result.bycname}</p>
								<p class="date">데이터반영시점 &nbsp; ${result.uploaddate}</p>
								<p class="num">
									<strong>${result.pocnt}</strong><span>건</span>
								</p>
								<p class="icoBt">
									<a href="javascript://" class="layerBt ico3" onclick="openLayerPOList('${result.bycname}','${result.byc010id}','B','${result.receivetype}')" name="poList">발주</a>
									<a href="javascript://" class="layerBt ico1" onclick="openLayerPOSet('${result.bycname}','${result.byc010id}','B')" name="poSetting">발주환경설정</a>
								</p>
							</li>
	</c:if>
</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		<!-- 이부분까지가 기본 [e] -->
	</div>
	<!-- container 레이어 끝 -->
</div>
<!-- 전체 레이어 끝 -->
<!-- 레이어 팝업 [s] -->
	<!-- 발주주문양식 환경설정[s] -->
	<div class="layerCont layerCont_w2 poSetting">
		<div class="inner">
			<p class="layerTit">발주주문양식 환경설정</p>
			<div class="layerContents">
				<div id="layerPoCoName" class="lfl">창고-1</div>

				<div class="sortableDrag">
					<ul id="sortable1" class="connectedSortable"></ul>
					<ul id="sortable2" class="connectedSortable"></ul>
					<script>
						$(function(){
							$("#sortable1, #sortable2").sortable({
								connectWith:".connectedSortable"
							}).disableSelection();
						});
					</script>
				</div>
			</div>
			<p class="layerFootBt">
				<a href="javascript:savePOManualOption();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<input type="hidden" id="poo010id" name="poo010id" value="" />
	<input type="hidden" id="poconame" name="poconame" value="" />
	<input type="hidden" id="pocotype" name="pocotype" value="" />
	<input type="hidden" id="receivetype" name="receivetype" value="" />
	<!-- 발주주문양식 환경설정[e] -->
	
	<!-- 발주목록[s] -->
	<form id="formpo" name="formpo" method="post" action="/ism/po/po010SavePoList.do">
	<div class="layerCont layerCont_w2 poList">
		<div class="inner">
			<p class="layerTit">발주목록 및 전송</p>
			<div class="layerContents">
				<div id="layerPoListPoCoName" class="lfl">창고-1</div>
				<div id="poEmailSend">
					<div style="padding:5px;">
						담당자 : <select id="userList" name="userList"></select>
					</div>
					<div style="padding:5px;">
						참조 : <input type="text" name="ccUserList" value="" style="width:70%;">
					</div>
					<div style="padding:5px;">
						제목 : <input type="text" id="mailSubject" name="mailSubject" value="" style="width:70%;">
					</div>
					<div style="padding:5px;">
						<span style="vertical-align:top;">내용 : </span><textarea id="mailText" name="mailText" rows="3" style="width:70%;"></textarea>
					</div>
				</div>
				<div style="padding:5px;">
					주문메모 : <input type="text" id="ordermemo" name="ordermemo" value="" style="width:50%;">
					수령자에 주문자 포함 <input type="checkbox" id="addorderuser" name="addorderuser" value="Y" />
				</div>

				<div>
					<ul id="listTit" class="listTit"></ul>
					<div id="listData" class="listTb"></div>
				</div>
			</div>
			<p class="layerFootBt">
				<a href="javascript://" class="confirm" onclick="confirmpo()" id="posendbtn">발주전송</a>
				<a href="javascript://" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<input type="hidden" id="list_poo010id" name="poo010id" value="" />
	<input type="hidden" id="list_poconame" name="poconame" value="" />
	<input type="hidden" id="list_pocotype" name="pocotype" value="" />
	<input type="hidden" id="list_receivetype" name="receivetype" value="" />
	</form>
	<!-- 발주목록[e] -->

	<!-- 발주상세[s] -->
	<form id="formorder" name="formorder" method="post" action="/ism/ord/updateOrderDetailData.do">
	<div class="layerCont layerCont_v2 poDetail">
		<div class="inner">
			<p class="layerTit">주문현황관리</p>
			<div id="orderDetil" class="layerContents"><div class="inner" style="height:900px;"></div></div>
			<p class="layerFootBt">
				<a href="javascript:saveDetail();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose_v2 cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose_v2 layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	
	<input type="hidden" id="odm010id" name="odm010id" value="" />
	</form>
	<!-- 발주상세[e] -->

	<!-- API 환경설정 [s]-->
	<div class="layerCont layerCont_w2 apiSetting">
		<div class="inner">
			<p class="layerTit">API 환경설정</p>
			<div class="layerContents">
				<table cellpadding="0" cellspacing="0" class="apitb" summary="" >
					<caption></caption>
					<colgroup>
						<col width="20%"/><col width="80%"/>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">매입처</th>
							<td>
								<div class="layerForm">
								<div class="lfl">
									<select id="apibyc010id" name="apibyc010id" title="">
										<option value="0">매입처선택</option>
<c:forEach var="result" items="${resultListByc}" varStatus="status">
	<c:if test="${result.uploadgubun eq 'A'}">
										<option value="${result.byc010id}">${result.bycname}</option>
	</c:if>
</c:forEach>
									</select>
								</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">URL</th>
							<td><input type="text" class="it " title="" value="" id="apiurl" name="apiurl"/></td>
						</tr>
						<tr>
							<th scope="row">Context</th>
							<td><input type="text" class="it " title="" value="" id="apicontext" name="apicontext"/></td>
						</tr>
						<tr>
							<th scope="row">Version</th>
							<td><input type="text" class="it " title="" value="" id="apiversion" name="apiversion"/></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<p class="layerFootBt">
				<span id="msgarea" style="float:left;color:#0878ea;"></span>
				<a href="javascript:saveApiDetailData();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<!-- API 환경설정 [e]-->
	
	<!-- 송장 데이터 등록 [s] -->
<script type="text/javascript">
// 파일 리스트 번호
var fileIndex = 0;
// 등록할 전체 파일 사이즈
var totalFileSize = 0;
// 파일 리스트
var fileList = new Array();
// 파일 사이즈 리스트
var fileSizeList = new Array();
// 등록 가능한 파일 사이즈 MB
var uploadSize = 50;
// 등록 가능한 총 파일 사이즈 MB
var maxUploadSize = 500;

$(function (){
    // 파일 드롭 다운
    fileDropDown();
});

// 파일 드롭 다운
function fileDropDown(){
    var dropZone = $("#dropZone");
    //Drag기능 
    dropZone.on('dragenter',function(e){
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color','#E3F2FC');
    });
    dropZone.on('dragleave',function(e){
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color','#FFFFFF');
    });
    dropZone.on('dragover',function(e){
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color','#E3F2FC');
    });
    dropZone.on('drop',function(e){
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color','#FFFFFF');
        
        var files = e.originalEvent.dataTransfer.files;
        if(files != null){
            if(files.length < 1){
                alert("폴더 업로드 불가");
                return;
            }
            selectFile(files)
        }else{
            alert("ERROR");
        }
    });
}

// 파일 선택시
function selectFile(fileObject){
    var files = null;

    if(fileObject != null){
        // 파일 Drag 이용하여 등록시
        files = fileObject;
    }else{
        // 직접 파일 등록시
        files = $('#multipaartFileList_' + fileIndex)[0].files;
    }
    
    // 다중파일 등록
    if(files != null){
        for(var i = 0; i < files.length; i++){
            // 파일 이름
            var fileName = files[i].name;
            var fileNameArr = fileName.split("\.");
            // 확장자
            var ext = fileNameArr[fileNameArr.length - 1];
            // 파일 사이즈(단위 :MB)
            var fileSize = files[i].size / 1024 / 1024;
            
            //if($.inArray(ext, ['exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml']) >= 0)
            if($.inArray(ext, ['xls', 'xlsx']) < 0){
                // 확장자 체크
                alert("등록 불가 확장자");
                break;
            }else if(fileSize > uploadSize){
                // 파일 사이즈 체크
                alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB");
                break;
            }else{
                // 전체 파일 사이즈
                totalFileSize += fileSize;
                
                // 파일 배열에 넣기
                fileList[fileIndex] = files[i];
                
                // 파일 사이즈 배열에 넣기
                fileSizeList[fileIndex] = fileSize;

             	// 기존목록에 파일 있는지 확인
             	var chkFileExistVal = chkFileExist(fileName);
                console.log("jgc debug======"+chkFileExistVal);
    			if (chkFileExistVal == "F") {
                    console.log("jgc debug======OK");
                	// 업로드 파일 목록 생성
                    addFileList(fileIndex, fileName, fileSize);
                }
                
                // 파일 번호 증가
                fileIndex++;
            }
        }
    }else{
        alert("ERROR");
    }
}

// 업로드 파일 목록 생성
function addFileList(fIndex, fileName, fileSize){
    var html = "";
    html += "<tr id='fileTr_" + fIndex + "'>";
    html += "    <td class='left' >";
    html +=         fileName;
    html += "    </td>";
    html += "    <td class='left' >";
    html +=         "<a href='#' onclick='deleteFile(" + fIndex + "); return false;' class='btn small bg_02'>삭제</a>";
    html += "    </td>";
    html += "</tr>";

    $('#fileTableTbody').append(html);
}
function chkFileExist(fileName) {
	var retVal = "";
	$('#mfilelisttable tr').each(function() {
		var listFileName = $(this).find("td:first").text();
		listFileName = listFileName.trim();
	    if (fileName == listFileName) {
	    	retVal = "T";
	    	return false;
	    }else{
	    	retVal = "F";
	    }
	});
	return retVal;
}
// 업로드 파일 삭제
function deleteFile(fIndex){
    // 전체 파일 사이즈 수정
    totalFileSize -= fileSizeList[fIndex];
    
    // 파일 배열에서 삭제
    delete fileList[fIndex];
    
    // 파일 사이즈 배열 삭제
    delete fileSizeList[fIndex];
    
    // 업로드 파일 테이블 목록에서 삭제
    $("#fileTr_" + fIndex).remove();
}

// 파일 등록
function uploadFile(){
	// 등록할 파일 리스트
    var uploadFileList = Object.keys(fileList);

    // 파일이 있는지 체크
    if(uploadFileList.length == 0){
        // 파일등록 경고창
        alert("파일이 없습니다.");
        return;
    }
    
    // 용량을 500MB를 넘을 경우 업로드 불가
    if(totalFileSize > maxUploadSize){
        // 파일 사이즈 초과 경고창
        alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB");
        return;
    }
        
    if(confirm("등록 하시겠습니까?")){
        // 등록할 파일 리스트를 formData로 데이터 입력
        var form = $('#uploadForm');
        var formData = new FormData(form);
        for(var i = 0; i < uploadFileList.length; i++){
            formData.append('files', fileList[uploadFileList[i]]);
        }
        
        $.ajax({
            url:"/ism/po/po010batchup.do",
            data:formData,
            type:'POST',
            enctype:'multipart/form-data',
            processData:false,
            contentType:false,
            cache:false,
            success:function(result){
	            alert("성공");
	            location.href="/ism/po/po010.do";
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
}
function mfileonchange(files) {
	selectFile(files);
	//for (var i = 0; i < files.length; i++) {
	//	  var file = files[i];
	//	  console.log(file.name);
	//}
}
</script>
	<div class="layerCont upload">
		<div class="inner">
			<p class="layerTit">송장 데이터 등록</p>
			<div class="layerContents">
				<div style="float:right;margin:10px 0;">
    				<label for="mfile" class="muploadbtn">업로드</label>
        			<input multiple="multiple" type="file" id="mfile" name="mfile" onchange="javascript:mfileonchange(this.files);" style="display:none;"/>
        		</div>
			
			    <form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
				    <div id="dropZone">
				        <table id="mfilelisttable" class="table" width="100%" border="1px" class="connectedSortableLeft js-multiselect">
				            <tbody id="fileTableTbody">
				                <tr>
				                    <td colspan="2">
				                        파일을 선택 하세요
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </div>
			    </form>
			</div>
			
			<p class="layerFootBt">
				<a href="javascript:uploadFile();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<!-- 송장 데이터 등록 [e] -->
<!-- 레이어 팝업 [e] -->

<form id="formatdn" name="formatdn" method="post"></form>
<iframe name="tr" src="" width="0" height="0" frameborder="0" scrolling="no"></iframe>

</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
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
	$('#apibyc010id').change(function() { //매출처/쇼핑몰 변경시 처리
	    $.ajax({
	        url : "/ism/po/po010SelectApiDetail.do",
	        type: "post",
	        data : { "byc010id" : $(this).val() },
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success : function(data){
	            var cnt = 0; //return 값이 있는지 체크
	            $.each(data, function(index, item){
	            	cnt++;
	            	if (index == "url") $('#apiurl').val(item);
	            	else if (index == "context") $('#apicontext').val(item);
	            	else if (index == "version") $('#apiversion').val(decodeURIComponent(item.replace(Ca, " ")));
	            });
	            if (cnt == 0) { //return 값이 없는 경우 clear
	            	$('#apiurl').val('');
	            	$('#apicontext').val('');
	            	$('#apiversion').val('');
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
                alert(msg);
	        }
	    });
	});
});

function openLayerPOSet(poconame, keyId, PoType) {
	//저장시 사용위해 현재 레이어 팝업 발주처 정보저장
	$('#poo010id').val(keyId);
	$('#poconame').val(poconame);
	$('#pocotype').val(PoType);
	$('#layerPoCoName').text(poconame);

	//발주처의 발주환경 정보 읽기
	$.ajax({
        url : "/ism/po/po010SelectPoo010ListJson.do",
        type: "post",
        data : { "poo010id" : keyId, "pocotype" : PoType },
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(data){
            $('#sortable1').text(''); //필드 clear
            $('#sortable2').text(''); //필드 clear
            $.each(data, function(index, item){
            	if (item.isassign == "Y") {
            		$('#sortable2').append("<li class='ui-state-highlight' dataid='"+item.orderfield+"'>"+decodeURIComponent(item.orderfieldnm.replace(Ca, " "))+"</li>");
            	}else{
            		$('#sortable1').append("<li class='ui-state-default' dataid='"+item.orderfield+"'>"+decodeURIComponent(item.orderfieldnm.replace(Ca, " "))+"</li>");
            	}
            });
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
function savePOManualOption() {
	var savedata = "";
	var poo010id = $('#poo010id').val();
	var pocotype = $('#pocotype').val();

	$('#sortable2 li').each(function(index) {
		console.log($(this).text() + "^" + $(this).attr("dataid"));
		savedata += $(this).attr("dataid") + "^";
	});
	
	$.ajax({
        url : "/ism/po/po010SavePoo010.do",
        type: "post",
        data : { "poo010id" : poo010id, "pocotype" : pocotype, "savedata" : savedata },
        success : function(data){
            if (data == "SUCCESS") {
            	//$('#msgareamanual').text("정상적으로 저장되었습니다.");
            	alert("정상적으로 저장되었습니다.");
            }else{
            	//$('#msgareamanual').text("저장 중 오류가 발생했습니다.");
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
            $('#msgareamanual').text("Error : "+msg);
        }
    });
}
function openLayerPOList(poconame, keyId, PoType, receivetype) {
	// 제목, 내용 clear
	$('#mailSubject').val("");
	$('#mailText').val("");
	
	// 저장시 사용위해 현재 레이어 팝업 발주처 정보저장
	$('#poo010id').val(keyId);
	$('#poconame').val(poconame);
	$('#pocotype').val(PoType);
	$('#receivetype').val(receivetype);
	$('#layerPoListPoCoName').text(poconame);
	$('#list_poo010id').val(keyId);
	$('#list_poconame').val(poconame);
	$('#list_pocotype').val(PoType);
	$('#list_receivetype').val(receivetype);

	if (receivetype == "X") {
		$('#poEmailSend').css("display","none");
		$('#posendbtn').text("발주저장");
	}else{
		$('#poEmailSend').css("display","block");
		$('#posendbtn').text("발주전송");
	}

	//발주처의 담당자 정보 읽기
	$("#userList").html("");
	$.ajax({
        url : "/ism/po/po010SelectUserListJson.do",
        type: "post",
        async : false,
        data : { "keyid" : keyId, "pocotype" : PoType },
        dataType:'json',
        success : function(data){
        	//담당자가 2이상일 경우 선택 option 추가
			if (data.userlist.length == 0) {
				if (PoType == "W") {
	        		$("#userList").append("<option value='0'>해당 창고관리에서 담당자를 등록해주시기 바랍니다.</option>");
				}else{
	        		$("#userList").append("<option value='0'>해당 매입처관리에서 담당자를 등록해주시기 바랍니다.</option>");
				}
			}else if (data.userlist.length > 1) {
        		$("#userList").append("<option value='0'>담당자 선택</option>");
        	}
        	$.each(data.userlist, function(index, item){
            	console.log(item);
        		$("#userList").append("<option value='"+decodeURIComponent(item.useremail.replace(Ca, " "))+"'>"+decodeURIComponent(item.username.replace(Ca, " "))+"("+decodeURIComponent(item.useremail.replace(Ca, " "))+")</option>");
        	});
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
	
	var retVal;
	//발주처의 발주환경 정보 읽기
	$.ajax({
        url : "/ism/po/po010SelectPoo010ListJson.do",
        type: "post",
        async : false,
        data : { "poo010id" : keyId, "pocotype" : PoType },
        dataType:'json',
        success : function(data){
        	//console.log(data);
			retVal = data;
			$('#listTit').html("");
			
            $.each(data, function(index, item){
            	if (item.isassign == "Y") {
            		//$('#listTit').append(eval(item.orderfield + "HtmlTit"));
            		$('#listTit').append("<li class='"+item.orderfield+"'>"+decodeURIComponent(item.orderfieldnm.replace(Ca, " "))+"</li>");
            	}
            });
            $('#listTit').append("<li class='delbtn'>&nbsp;</li>");
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

	//실제 발주 데이터 읽기
	$.ajax({
        url : "/ism/po/po010SelectOrm010ListJson.do",
        type: "post",
        async : false,
        data : { "poo010id" : keyId, "pocotype" : PoType },
        dataType:'json',
        success : function(data){
        	//console.log(data);

        	var inputArr = ["orderitemqty", "orderitemname", "orderitemopt", "rcvuser", "rcvusercontact", "orderuser", "orderusercontact", "dlvprice", "dlvno", "dlvmemo", "address", "dlvco", "orderitemprice" ];

			$('#listData').html("");
        	var total = retVal.length;
			var setHtml = "";
            $.each(data, function(index, item){
                $.each(retVal, function(index2, item2){
                	if (index2 == 0) {
                		setHtml += "<ul class='layerBt_v2 listData' onclick='poDetailView(\""+item.odm010id+"\")' name='poDetail'><input type='hidden' name='odm010id' value='"+item.odm010id+"' />";
                	}
                	if (item2.isassign == "Y") {
                		setHtml += "<li class='"+item2.orderfield+"'>&nbsp;"+item[item2.orderfield]+"</li>";	
                		$('#listData').append();
                	}
                	if (index2 == (total-1)) {
                		setHtml += "<li class='delbtn' onclick='delRow(this)'>삭제</li></ul>";
                	}
                	//console.log($('#listData').html());
                });
            });

    		$('#listData').append(setHtml);
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

function saveApiDetailData() {
	var selectval = $("#apibyc010id option:selected").val();
	var apiurl = $('#apiurl').val();
	if (selectval == "0") {
		//$('#msgarea').text("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		alert("매입처를 선택해주시기 바랍니다.");
		return;
	}
	if (apiurl.trim() == "") {
		//$('#msgarea').text("URL을 입력해주시기 바랍니다.");
		alert("URL을 입력해주시기 바랍니다.");
		return;
	}
    $.ajax({
        url : "/ism/po/po010SaveApiDetail.do",
        type: "post",
        data : { "byc010id" : selectval, "apiurl" : apiurl, "apicontext" : $('#apicontext').val(), "apiversion" : $('#apiversion').val() },
        success : function(data){
            if (data == "SUCCESS") {
            	alert("정상적으로 저장되었습니다.");
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
            $('#msgarea').text("Error : "+msg);
        }
    });
	
}

function poDetailView(odm010id) {
	console.log("aaaaa");
	$('#odm010id').val(odm010id);
	console.log("bbbbb");
	$.ajax({
        url : "/ism/ord/odo020SelectOrderOne.do",
        type: "post",
        async : false,
        data : { "odm010id" : odm010id },
        dataType:'json',
        success : function(data){
        	var html = " "+
        	"			<div class='layerTb'>"+
        	"				<table cellpadding='0' cellspacing='0' class='' summary='' >"+
        	"					<caption></caption>"+
        	"					<colgroup>"+
        	"						<col width='15%'/><col width='35%'/>"+
        	"						<col width='15%'/><col width='35%'/>"+
        	"					</colgroup>"+
        	"					<tbody>"+
        	"						<tr>"+
        	"							<th scope='row'>사업자구분</th>"+
        	"							<td>"+data.code_nm+" / "+data.cotype2nm+ " / "+data.cotype3nm+"</td>"+
        	"							<th scope='row'>쇼핑몰명</th>"+
        	"							<td>"+data.shopmallname+"</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>매입처</th>"+
        	"							<td>"+data.bycname+"</td>"+
        	"							<th scope='row'>매입배송비</th>"+
        	"							<td>"+data.itembuydlvprice+" 원</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>매출처</th>"+
        	"							<td>"+data.coname+"</td>"+
        	"							<th scope='row'>공급배송비</th>"+
        	"							<td><input type='text' style='text-align:right;' name='dlvprice' value='"+data.dlvprice+"' /> 원</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>주문번호</th>"+
        	"							<td>"+data.orderno+"</td>"+
        	"							<th scope='row'>송장번호</th>"+
        	"							<td><input type='text' name='dlvno' value='"+data.dlvno+"' /></td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>상품코드</th>"+
        	"							<td>"+data.orderitemid+"</td>"+
        	"							<th scope='row'>배송메모</th>"+
        	"							<td><input type='text' name='dlvmemo' value='"+data.dlvmemo+"' /></td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>상품명</th>"+
        	"							<td><textarea name='orderitemname' style='width:100%;'>"+data.orderitemname+"</textarea></td>"+
        	"							<th scope='row'>주소(우편번호)</th>"+
        	"							<td>"+
        	"								<input type='text' id='postno' name='postno' value='"+data.postno+"' size='6' /> &nbsp;"+ 
        	"                               <a href='javascript:execDaumPostcode(\""+data.address+"\")'>우편번호찾기</a>"+
        	"								<textarea name='address' style='width:100%;'>"+data.address+"</textarea>"+
			"							</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>옵션</th>"+
        	"							<td><input type='text' name='orderitemopt' value='"+data.orderitemopt+"' /></td>"+
        	"							<th scope='row'>택배사</th>"+
        	"							<td><input type='text' name='dlvco' value='"+data.dlvco+"' /></td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>수령자</th>"+
        	"							<td><input type='text' name='rcvuser' value='"+data.rcvuser+"' /></td>"+
        	"							<th scope='row'>매입단가</th>"+
        	"							<td>"+data.itembuyprice+" 원</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>수령자연락처</th>"+
        	"							<td><input type='text' name='rcvusercontact' value='"+data.rcvusercontact+"' /></td>"+
        	"							<th scope='row'>공급가</th>"+
        	"							<td><input type='text' style='text-align:right;' name='orderitemprice' value='"+data.orderitemprice+"' /> 원</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>수령자핸드폰</th>"+
        	"							<td><input type='text' name='rcvusercontacthp' value='"+data.rcvusercontacthp+"' /></td>"+
        	"							<th scope='row'>수령자이메일</th>"+
        	"							<td><input type='text' name='rcvuseremail' value='"+data.rcvuseremail+"' /></td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>주문자</th>"+
        	"							<td><input type='text' name='orderuser' value='"+data.orderuser+"' /></td>"+
        	"							<th scope='row'>처리일자</th>"+
        	"							<td>"+data.processdate+"</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<th scope='row'>주문자 연락처</th>"+
        	"							<td><input type='text' name='orderusercontact' value='"+data.orderusercontact+"' /></td>"+
        	"							<th scope='row'>주문일자</th>"+
        	"							<td>"+data.orderdate+"</td>"+
        	"						</tr>"+
        	"					</tbody>"+
        	"				</table>"+
        	"			</div>"+
        	"			<div class='layerTb mt10'>"+
        	"				<table cellpadding='0' cellspacing='0' class='' summary='' >"+
        	"					<caption></caption>"+
        	"					<colgroup>"+
        	"						<col width='50%'/><col width='50%'/>"+
        	"					</colgroup>"+
        	"					<tbody>"+
        	"						<tr>"+
        	"							<th scope='row'>정산(매출처)</th>"+
        	"							<th scope='row'>정산(매입처)</th>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<td>"+
        	"								<input type='radio' value='1' name='accountcum' id='accountcum_1'/><label for='sel1_1'>정산확정대기</label>"+
        	"								<input type='radio' value='2' name='accountcum' id='accountcum_2'/><label for='sel1_2'>매출이월</label>"+
        	"							</td>"+
        	"							<td>"+
        	"								<input type='radio' value='1' name='accountbuy' id='accountbuy_1'/><label for='sel2_1'>정산확정대기</label>"+
        	"								<input type='radio' value='2' name='accountbuy' id='accountbuy_2'/><label for='sel2_2'>매입이월</label>"+
        	"							</td>"+
        	"						</tr>"+
        	"					</tbody>"+
        	"				</table>"+
        	"			</div>"+
        	"			<div id='statusset' class='layerTb mt10'>"+
        	"				<table cellpadding='0' cellspacing='0' class='' summary='' >"+
        	"					<caption></caption>"+
        	"					<colgroup>"+
        	"						<col width='100%'/>"+
        	"					</colgroup>"+
        	"					<tbody>";
        	/*
        	"						<tr>"+
        	"							<th scope='row'>상태값 변경</th>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<td>";
<c:forEach var="item" items="${ISM050}" varStatus="status">
			html += "<input type='radio' value='${item.code}' name='status' id='status_1'/><label for='sel3_1'>${item.codeNm}</label>";
</c:forEach>
			html +=
        	"							</td>"+
        	"						</tr>"+
        	*/
        	html = html +
        	"						<tr>"+
        	"							<th scope='row'>CS 구분</th>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<td>"+
        	"								<input type='radio' value='0' name='cstype' onclick='setCR(0)' id='sel4_1' checked/><label for='sel4_1'>없음</label>"+
        	"								<input type='radio' value='C' name='cstype' onclick='setCR(1)' id='sel4_1'/><label for='sel4_1'>클레임 접수</label>"+
        	"								<input type='radio' value='R' name='cstype' onclick='setCR(2)' id='sel4_2'/><label for='sel4_2'>반품 접수</label>"+
        	"							</td>"+
        	"						</tr>"+
        	"					</tbody>"+
        	"				</table>"+
        	"			</div>"+
        	"			<div id='statusset1' class='layerTb mt10'>";
        	if (data.cstype == 'C') {
        		html = html + claim1Html;
        	}else if (data.cstype == 'R') {
        		html = html + returnHtml;
        	}
        	
        	html = html +
        	"			</div>"+
        	"			<div id='statusset2' class='layerTb mt10'>";

        	if (data.cstype == 'C') {
        		html = html + claim2Html;
        	}
        	
        	html = html +
        	"			</div>";
        	/*
        	"			<div class='layerTb mt10'>"+
        	"				<table cellpadding='0' cellspacing='0' class='' summary='' >"+
        	"					<caption></caption>"+
        	"					<colgroup>"+
        	"						<col width='100%'/>"+
        	"					</colgroup>"+
        	"					<tbody>"+
        	"						<tr>"+
        	"							<th id='memotitle' scope='row'>메모내용</th>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<td>"+
        	"								<div class='memoTxt'><ul id='memoul'>"+decodeURIComponent(data.orderMemo.replace(Ca, " "))+"</ul></div>"+
        	"							</td>"+
        	"						</tr>"+
        	"						<tr>"+
        	"							<td>"+
        	"								<p class='memo'>"+
        	"									<input type='text' class='it' title='' value='' id='inputmemo' name='inputmemo'/>"+
        	"									<a onclick='inputmemodata(\"OD\")'>입력</a>"+
        	"								</p>"+
        	"							</td>"+
        	"						</tr>"+
        	"					</tbody>"+
        	"				</table>"+
        	"			</div>";
        	*/
        	$('#orderDetil').html(html);

        	if (data.accountcum != "") {
            	$('input:radio[name=accountcum]:input[value=' + data.accountcum + ']').attr("checked", true);
        	}
        	if (data.accountbuy != "") {
        		$('input:radio[name=accountbuy]:input[value=' + data.accountbuy + ']').attr("checked", true);
        	}
        	if (data.status != "") {
        		$('input:radio[name=status]:input[value=' + data.status + ']').attr("checked", true);
        	}
        	if (data.cstype != "") {
        		$('input:radio[name=cstype]:input[value=' + data.cstype + ']').attr("checked", true);
        	}
        	if (data.cstype == 'C') {
        		$("#claimstatus").val(data.claimstatus);
        		$('input:radio[name=claimreason]:input[value=' + data.claimreason + ']').attr("checked", true);
        	}else if (data.cstype == 'R') {
        		$('input:radio[name=retstatus]:input[value=' + data.retstatus + ']').attr("checked", true);
        		$('#retqty').val(data.retqty);
        		$('#retprice').val(data.retprice);
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

//클레임접수 또는 반품접수 라이오 버튼 클릭시
//type 1 : 클레임, 2 : 반품
function setCR(type) {
	$('#rtntable').remove();
	$('#clmtable1').remove();
	$('#clmtable2').remove();
	if (type == 1) {
		$('#statusset1').append(claim1Html);
		$('#statusset2').append(claim2Html);
	}else if (type == 2) {
		$('#statusset1').append(returnHtml);
	}else{
		$('#rtntable').remove();
		$('#clmtable1').remove();
		$('#clmtable2').remove();
	}
}

//action="/ism/ord/updateOrderDetailData.do"
function saveDetail() {
	console.log($('#formorder').serialize());
    var options = {
    	success : function(data) {
            console.log(data);
            if (data == 'SUCCESS') {
            	alert("저장되었습니다.");
            	$('.layerClose_v2').trigger('click');
            	openLayerPOList($('#poconame').val(),$('#poo010id').val(),$('#pocotype').val());
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

function delRow(obj) {
	event.stopPropagation();
	$(obj).parent().remove();
}

//발주서 전송
function confirmpo() {

	var userList = $("#userList").val().trim();
	var mailSubject = $("#mailSubject").val().trim();
	var mailText = $("#mailText").val().trim();

	if ($("#listData").find("ul").length == 0) {
		alert("발주 전송 목록이 없습니다.");
		return false;
	}
	
	if ($("#receivetype").val() != "X") {
		if (userList == "0") {
			alert("담당자를 선택해주시기 바랍니다.");
			return false;
		}
		
		if (mailSubject == "") {
			alert("제목은 입력 필수입니다.");
			return false;
		}

		if (mailText == "") {
			alert("내용은 입력 필수입니다.");
			return false;
		}		
	}
	
    var options = {
        success : function(data) {
            console.log(data);
            if (data == 'SUCCESS') {
               	alert("발주 전송 되었습니다.");
            	//$('.layerClose').trigger('click');
            	location.href="/ism/po/po010.do";
            }else if (data.indexOf(".xls") >= 0){
               	alert("발주 저장 되었습니다.");
               	downLoadFile(data);
            	location.href="/ism/po/po010.do";
            }else{
               	alert("저장 중 오류가 발생했습니다.");
            }
        },
        error : function(xhr, status, error) {
           	console.log(error);
        },
            type : "POST"
    };
        	
    $("#formpo").ajaxSubmit(options); ///ism/po/po010SavePoList.do
}

function downLoadFile(filename) {
	T = document.formatdn;
	T.target	= "tr";
	T.action	= "/ism/cmm/attachFileDownFileName.do?filename="+filename;
	T.submit();
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