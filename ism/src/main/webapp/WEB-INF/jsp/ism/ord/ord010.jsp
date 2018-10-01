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
	#sortable2 li.ui-state-default {background:#3a5199;}
	#sortable1 li.ui-state-highlight {background:#333;}
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
		<!-- 이부부까지가 기본 -->

			<div class="contentsWrap">
				<ul class="topBt">
					<li><a href="javascript:;" class="layerBt" name="apiSetting">API 환경설정</a></li>
					<li><a href="javascript:;" class="layerBt" name="setting">수동수집환경설정</a></li>
				</ul>
				<div class="contents">
					<h2 class="pageTit">주문수집목록</h2>
					<form id="formMain" name="formMain" method="post" action="" class="searchArea">
						<a href="javascript:;" class="layerBt" name="upload">데이터 일괄 업로드</a>
						<input type="text" class="it ml30" title="" value="${ord010SearchVO.search_key1}" id="search_key1" name="search_key1"/>
						<button>검색</button>
					</form>

					<div class="listTb">
						<table cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<col width="5%"/><col width="15%"/><col width="*"/>
								<col width="10%"/><col width="10%"/><col width="10%"/>
								<col width="8%"/><col width="10%"/><col width="12%"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">NO.</th>
									<th scope="col">쇼핑몰명</th>
									<th scope="col">어드민쇼핑몰주소</th>
									<th scope="col">아이디</th>
									<th scope="col">패스워드</th>
									<th scope="col">구분</th>
									<th scope="col">24시간내<br/>업로드회수</th>
									<th scope="col">주문 데이터<br/>개별 업로드</th>
									<th scope="col">최근반영일</th>
								</tr>
							</thead>
							<tbody>
				<!-- loop 시작 -->
                <c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td><c:out value="${status.count}"/></td>
									<td><c:out value="${result.shopmallname}"/></td>
									<td><a href="${result.shopurl}"><c:out value="${result.shopurl}"/></a></td>
									<td><c:out value="${result.shopuid}"/></td>
									<td><c:out value="${result.shoppwd}"/></td>
<c:if test="${result.uploadgubun eq 'A'}">
									<td>API</td>
									<td>&nbsp;</td>
									<td>&nbsp;
</c:if>
<c:if test="${result.uploadgubun eq 'M'}">
									<td>수동</td>
									<td><c:out value="${result.uploadCnt}"/></td>
									<td>
										<label for="file${result.cum030id}" class="upload1">개별업로드</label>
</c:if>

<form id="orderfileform${result.cum030id}" name="orderfileform${result.cum030id}" action='/ism/ord/odo010orderupfile.do' enctype='multipart/form-data' method='post'>
										<input type="hidden" id="filecum010id" name="filecum010id" value="${result.cum010id}">
										<input type="hidden" id="filecum030id" name="filecum030id" value="${result.cum030id}">
										<input type="file" id="file${result.cum030id}" name="file1" class="hidden" onchange="orderFileUpload(${result.cum010id},${result.cum030id})"/>
</form>
									</td>
									<td><c:out value="${result.lastUploadDate}"/></td>
								</tr>
                </c:forEach>
                <c:if test="${fn:length(resultList) eq 0}">
				<tr>
					<td scope="col" colspan="15">검색내용이 없습니다.</td>
				</tr>
                </c:if>
				<!-- loop 끝 -->
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</div>
	<!-- container 레이어 끝 -->
</div>
<!-- 전체 레이어 끝 -->

	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="/js/custom/multiselect.js"></script>

	<!-- 수동수집 환경설정 [s] -->
	<div class="layerCont layerCont_w2 setting">
		<div class="inner">
			<p class="layerTit">수동수집 환경설정</p>
			<div class="layerContents">
				<form id="titfileform" name="titfileform" action='/ism/ord/odo010upfile.do' enctype='multipart/form-data' method='post' class="layerForm">
					<div class="lfl">
						<select id="shopmallidmanual" name="shopmallidmanual" title="">
							<option value="0" dataid="">쇼핑몰선택</option>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:if test="${result.uploadgubun eq 'M'}">
							<option value="${result.cum030id}" dataid="${result.uploadtype}">${result.shopmallname}</option>
	</c:if>
</c:forEach>
						</select>
						<span id="uploadtypeinfo" style="margin-left:20px;"></span>
						<!-- <button>매출처 선택</button> -->
					</div>
					<div id="titfile" class="lfr">
						<label for="file" class="file">+</label>
						<input type="file" id="file" name="file" onchange="titfileUpload()" class="hidden"/>
					</div>
				</form>

				<div class="sortableDrag">
					<ul id="sortable1" class="connectedSortable">
						<li class="ui-state-default" style="width:200px;">엑셀파일을 선택해주세요.</li>
					</ul>
<c:forEach var="result" items="${orderField}" varStatus="status">
	<c:set var="maxheightval" value="max-height:69px;"/>
	<c:if test="${result.code eq 'postno'}">
		<c:set var="maxheightval" value=""/>
	</c:if>
	<c:if test="${result.code eq 'address'}">
		<c:set var="maxheightval" value=""/>
	</c:if>
					<div class="targetField" style="float:left;">
						<span style="position:absolute;" dataid="${result.code}">${result.codeNm}</span>
						<ul id="sortable2" class="connectedSortable ${result.code} sortable2" style="width:133px;min-height:69px;${maxheightval}height:100%;margin:15px 3px;"></ul>
					</div>
</c:forEach>
					<script>
						$(function(){
							$(".connectedSortable").sortable({
								connectWith:".connectedSortable"
							}).disableSelection();
						});
					</script>
				</div>
			</div>
			<p class="layerFootBt">
				<span id="msgareamanual" style="float:left;color:#0878ea;"></span>
				<a href="javascript:saveManualDetail();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<!-- 수동수집 환경설정 [e] -->
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
							<th scope="row">매출처/쇼핑몰</th>
							<td>
								<div class="layerForm">
								<div class="lfl">
									<select id="shopmallid" name="shopmallid" title="">
										<option value="0">쇼핑몰선택</option>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:if test="${result.uploadgubun eq 'A'}">
										<option value="${result.cum030id}">${result.coname}/${result.shopmallname}</option>
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
	<!-- 데이터 일괄 업로드 [s]-->
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
        
        loadingBarOpen();
        
        $.ajax({
            url:"/ism/ord/ord010batchup.do",
            data:formData,
            type:'POST',
            enctype:'multipart/form-data',
            processData:false,
            contentType:false,
            cache:false,
            success:function(result){
	            console.log(result);
	            console.log(result.split("^")[0]);
	            console.log(result.split("^")[1]);
	            if (result.split("^")[1].length > 2) {
		            alert(result.split("^")[1].substring(1,result.split("^")[1].length) + " 가 수동수집환경 설정이 안되어 있습니다.");
	            }else{
		            alert("성공");	            	
	            }
	            location.href="/ism/ord/ord020.do?search_key1="+result.split("^")[0]+"&search_status=TEMP";
            },
            error: function (jqXHR, exception) {
            	loadingBarClose();
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
			<p class="layerTit">데이터 일괄 업로드</p>
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
			<!-- <div onchange="dropfile();">Drap and Drop here.</div> -->
			<p class="layerFootBt">
				<a href="javascript:uploadFile();" class="confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<!-- 데이터 일괄 업로드 [e]-->
	
	<!-- 로딩바[s] -->
	<div class="layerCont layerCont_v2 loadingbar" style="width:319px;">
		<div class="inner" style="text-align:center;">
			<img src="/images/custom/loading_bar.gif" style="height:191px;" />
		</div>
	</div>
	<!-- 로딩바[e] -->
</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#shopmallidmanual').change(function() { //매출처/쇼핑몰 변경시 처리
		$('.targetField').children('ul').css("background-color","");
		$('#uploadtypeinfo').text("주문수집 파일 명 : "+$('option:selected', this).attr('dataid'));
		selectManualDetailData($(this).val());
	});
	$('#shopmallid').change(function() { //매출처/쇼핑몰 변경시 처리
	    $.ajax({
	        url : "/ism/ord/odo020.do",
	        type: "post",
	        data : { "cum030id" : $(this).val() },
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success : function(data){
	            var cnt = 0; //return 값이 있는지 체크
	            var Ca = /\+/g;
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
	            $('#msgarea').text("");
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
	});
});
function titfileUpload() {
	var selectval  = $("#shopmallidmanual option:selected").val();
	var selectdata = $("#shopmallidmanual option:selected").attr('dataid');
	if (selectval == "0") {
		//$('#msgareamanual').text("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		alert("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		clearInputFile();
		return;
	}
	if (selectdata == "") {
		alert("업로드 타입이 등록되지 않았습니다.");
		clearInputFile();
		return;
	}

    var Ca = /\+/g;
    var options = {
    	success : function(data) {
        	console.log(decodeURIComponent(data.replace(Ca, " ")));
        	var titlenames = decodeURIComponent(data.replace(Ca, " "));
        	var titlenameArray = titlenames.split('^');

        	var result = [];
        	var isDup = false;
        	var dupTitle = "";
        	$.each(titlenameArray, function(index, element) { // 배열의 원소수만큼 반복
                if ($.inArray(element, result) == -1) {
                	result.push(element);
                }else{
                	dupTitle += element+" ";
                    isDup = true;
                }
            });

        	if (isDup) {
                alert(dupTitle + "가 중복되었습니다. 중복 제거 후 다시 업로드해주시기 바랍니다.");
        		clearInputFile();
        		return false;
        	}
        	
        	$('#sortable1').text('');
            $('.sortable2').text(''); //시스템 필드 clear
        	for ( var i in titlenameArray ) {
        		if (titlenameArray[i] != '') $('#sortable1').append("<li class='ui-state-default' dataid='"+i+"' style='margin:5px;'>"+titlenameArray[i]+"</li>");
            }
        },
        error : function(xhr, status, error) {
    		clearInputFile();
        	console.log(error);
       	},
        type : "POST"
    };
    $("#titfileform").ajaxSubmit(options);
}
function orderFileUpload(cum010id, cum030id) {
	loadingBarOpen();
    var options = {
    	success : function(data) {
            console.log(data.split("^")[0]);
            console.log(data.split("^")[1]);

            if (data.split("^")[1] == "-1") {
            	loadingBarClose();
	            alert("업로드 타입이 맞지 않는 파일입니다.");
	            return;
            }else if (data.split("^")[1] == "0") {
            	loadingBarClose();
	            alert("수동수집환경 설정이 안되어 있습니다.\n설정 후 다시 업로드해주시기 바랍니다.");
	            return;
            }else{
	            alert("성공");	            	
            }
        	//$("#formMain").submit();
            location.href="/ism/ord/ord020.do?search_key1="+data.split("^")[0]+"&search_status=TEMP";
        },
        error : function(xhr, status, error) {
        	loadingBarClose();
        	console.log(error);
        },
        type : "POST"
    };
	
	$("#orderfileform"+cum030id).ajaxSubmit(options);
}
function selectManualDetailData(cum030id) {
    $.ajax({
        url : "/ism/ord/odo010.do",
        type: "post",
        data : { "cum030id" : cum030id },
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(data){
        	var Ca = /\+/g;
            $('#sortable1').text(''); //사용자 필드 clear
            $('.sortable2').text(''); //시스템 필드 clear
            var cnt = 0;
            $.each(data, function(index, item){
            	var additem = "";
            	var isassign = "";
            	var orderfield = "";
                $.each(item, function(index, element){
                	if (index == "additem") additem = element;
            		else if (index == "isassign") isassign = element;
            		else if (index == "orderfield") orderfield = element;
                });
                additem = decodeURIComponent(additem.replace(Ca, " "));
            	if ((additem != "NONE") && (isassign == "N") ) {
            		$('#sortable1').append('<li class="ui-state-default" style="margin:5px;">'+additem+'</li>');
            	}
            	if ((additem != "NONE") && (isassign == "Y") ) {
            		var additems = additem.split("#");
            	    console.log("additems = " + additems);
                	for ( var i in additems ) {
                	    console.log(i + "/" + additems[i]);
                		$('.'+orderfield).append('<li class="ui-state-default" style="margin:5px;">'+additems[i]+'</li>');
            	    }
            	}
            	cnt++;
            });
            //조회 데이터 없는 경우 사용자필드 초기화
            if (cnt == 0) {
            	$('#sortable1').append('<li class="ui-state-default ui-sortable-handle" style="width:200px;">엑셀파일을 선택해주세요.</li>');
            }
            $('#msgareamanual').text("");
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
function saveApiDetailData() {
	var selectval = $("#shopmallid option:selected").val();
	var apiurl = $('#apiurl').val();
	if (selectval == "0") {
		//$('#msgarea').text("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		alert("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		return;
	}
	if (apiurl.trim() == "") {
		//$('#msgarea').text("URL을 입력해주시기 바랍니다.");
		alert("URL을 입력해주시기 바랍니다.");
		return;
	}
    $.ajax({
        url : "/ism/ord/odo020Save.do",
        type: "post",
        data : { "cum030id" : selectval, "apiurl" : $('#apiurl').val(), "apicontext" : $('#apicontext').val(), "apiversion" : $('#apiversion').val() },
        success : function(data){
            if (data == "SUCCESS") {
            	//$('#msgarea').text("정상적으로 저장되었습니다.");
            	alert("정상적으로 저장되었습니다.");
            }else{
            	//$('#msgarea').text("저장 중 오류가 발생했습니다.");
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

function saveManualDetail() {
	var selectval = $("#shopmallidmanual option:selected").val();
	if (selectval == "0") {
		//$('#msgareamanual').text("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		alert("매출처/쇼핑몰을 선택해주시기 바랍니다.");
		return;
	}
	var userTitleNames = "";
	var sysmTitleNames = "";
	var assgTitleNames = "";
	var cum030id = $("#shopmallidmanual option:selected").val();

	$('#sortable1 li').each(function(index) {
		userTitleNames += ($(this).text() + "^");
	});
	
	//세팅된 아이템 갯수 체크
	var isCorrect = 1;
	$('.targetField').each(function(index) {
		sysmTitleNames = $(this).children('span').attr('dataid');
		var setItemCnt = 0;
		if ($(this).children('ul').children('li').text() == "") setItem = "NONE";
		else {
			$(this).children('ul').children('li').each(function(index) {
				setItemCnt += 1;
			});
		}
		if ( (sysmTitleNames != "postno") && (sysmTitleNames != "address")  ) {
			if (setItemCnt > 1) {
				$(this).children('ul').css("background-color","#fdfd82");
				isCorrect = 0;
			}else{
				$(this).children('ul').css("background-color","");
			}
		}else{
			$(this).children('ul').css("background-color","");
		}
	});
	
	if (isCorrect == 0) {
		//$('#msgareamanual').text("우편번호와 주소이외에는 2개의 필드를 설정할수 없습니다.");
		alert("우편번호와 주소이외에는 2개의 필드를 설정할수 없습니다.");
		return;
	}
	
	sysmTitleNames = "";
	$('.targetField').each(function(index) {
		sysmTitleNames += ($(this).children('span').attr('dataid') + "^");
		var setItem = "";
		var setItemCnt = 0;
		if ($(this).children('ul').children('li').text() == "") setItem = "NONE";
		else {
			$(this).children('ul').children('li').each(function(index) {
				setItem += $(this).text() + "#";
				setItemCnt += 1;
			});
			setItem = setItem.substring(0,setItem.length - 1);
			//setItem = $(this).children('ul').children('li').text();
		}
		assgTitleNames += (setItem + "^");
	});

	console.log('userTitleNames : ' + userTitleNames);
	console.log('sysmTitleNames : ' + sysmTitleNames);
	console.log('assgTitleNames : ' + assgTitleNames);

	$.ajax({
        url : "/ism/ord/odo010Save.do",
        type: "post",
        data : { "cum030id" : cum030id, "userTitleNames" : userTitleNames, "sysmTitleNames" : sysmTitleNames, "assgTitleNames" : assgTitleNames },
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

function clearInputFile() {
	var agent = navigator.userAgent.toLowerCase();
	
	if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ){
		$("#file").replaceWith( $("#file").clone(true) );
	} else { // other browser 일때 input[type=file] init.
		$("#file").val("");
	}
}

function loadingBarOpen() {
	$('body').append('<div class="fade_v2" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:200; display:none;"></div>');
	$('.fade_v2').fadeIn();
	$('.loadingbar').css({
		'margin':'-'+($('.loadingbar').height()/2)+'px 0 0 -'+($('.loadingbar').width()/2)+'px'
	});
	$('.loadingbar').fadeIn();
	return false;
}

function loadingBarClose() {
	$('.layerCont_v2').fadeOut();
	$('.fade_v2').fadeOut(function(){
		$('.fade_v2').remove();
	})
	return false;
}
</script>