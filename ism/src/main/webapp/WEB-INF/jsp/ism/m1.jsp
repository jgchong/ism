<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
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
	<script type="text/javascript">
		var num = '';
	</script>
	<style>
input {
  apperance: none;
  -webkit-apperance: none;
}
	</style>
</head>
<body>

	<div class="wrap">
		<div class="container">
			<div class="lnb">
				<div class="userArea">
					<h1 class="logo">넷케이티아이</h1>
					<p class="user">
						<span class="img">
							<img src="http://imgnews.naver.com/image/421/2015/09/12/1530464_article_99_20150912080114.jpg" alt=""/>
						</span>
						<strong>
							이름 : 홍길동
							<span>직급 : 대리 / 부서 : 회계팀</span>
						</strong>
					</p>
				</div>

				<ul class="menu">
					<li><a href="index.html">DASHBOARD</a></li>
					<li><a href="m1.html">주문관리</a></li>
					<li><a href="m2.html">주문현황</a>
						<p>
							<a href="#">클레임관리</a>
							<a href="m2_2.html">반품관리</a>
						</p>
					</li>
					<li><a href="m3.html">발주관리</a>
						<p>
							<a href="m3.html">매입처발주</a>
						</p>
					</li>
					<li><a href="m4.html">재고관리</a>
						<p>
							<a href="#">창고관리</a>
							<a href="#">재고관리</a>
							<a href="#">이관관리</a>
						</p>
					</li>
					<li><a href="m5.html">정산관리</a></li>
					<li><a href="m6.html">상품관리</a>
						<p>
							<a href="m6.html">운영상품관리</a>
						</p>
					</li>
					<li><a href="m7.html">업체관리</a>
						<p>
							<a href="m7.html">매입처관리</a>
							<a href="m7_2.html">매출처관리</a>
						</p>
					</li>
					<li><a href="m8.html">슈퍼어드민</a></li>
					<li class="siteEdit"><a href="#">환경설정</a></li>
				</ul>
			</div>

			<div class="contentsWrap">
				<ul class="topBt">
					<li><a href="javascript:;" class="layerBt" name="apiSetting">API 환경설정</a></li>
					<li><a href="javascript:;" class="layerBt" name="setting">수동수집환경설정</a></li>
				</ul>
				<div class="contents">
					<h2 class="pageTit">주문수집목록</h2>
					<form method="post" action="" class="searchArea">
						<a href="javascript:;" class="layerBt" name="upload">데이터 일괄 업로드</a>
						<input type="text" class="it ml30" title="" value="" name=""/>
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
								<tr>
									<td>1</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>2</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>3</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>4</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>5</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>6</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>7</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>8</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>9</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
								<tr>
									<td>10</td>
									<td>00쇼핑몰</td>
									<td><a href="#">http://admin.0000.com</a></td>
									<td>아이디</td>
									<td>비밀번호</td>
									<td>API / 수동</td>
									<td>3</td>
									<td><label for="file1" class="upload1">개별업로드</label><input type="file" id="file1" class="hidden"/></td>
									<td>2018.00.00 12:00</td>
								</tr>
							</tbody>
						</table>
					</div>

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
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="/js/custom/multiselect.js"></script>

	<!-- 수동수집 환경설정 -->
	<div class="layerCont layerCont_w2 setting">
		<div class="inner">
			<p class="layerTit">수동수집 환경설정</p>
			<div class="layerContents">
				<form method="post" action="" class="layerForm">
					<div class="lfl">
						<select name="" title="">
							<option value="" selected="selected"></option>
							<option value=""></option>
						</select>
						<button>매출처 선택</button>
					</div>
					<div class="lfr">
						<label for="file" class="file">+</label>
						<input type="file" id="file" class="hidden"/>
					</div>
				</form>

				<div class="sortableDrag">
					<ul id="sortable1" class="connectedSortable">
						<li class="ui-state-default">Item 1</li>
						<li class="ui-state-default">Item 2</li>
						<li class="ui-state-default">Item 3</li>
						<li class="ui-state-default">Item 4</li>
						<li class="ui-state-default">Item 5</li>
					</ul>
					<ul id="sortable2" class="connectedSortable">
						<li class="ui-state-highlight">Item 1</li>
						<li class="ui-state-highlight">Item 2</li>
						<li class="ui-state-highlight">Item 3</li>
						<li class="ui-state-highlight">Item 4</li>
						<li class="ui-state-highlight">Item 5</li>
					</ul>
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
				<a href="#" class="layerClose confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>

	<!-- API 환경설정 -->
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
							<th scope="row">NAME</th>
							<td><input type="text" class="it " title="" value="" name=""/></td>
						</tr>
						<tr>
							<th scope="row">URL</th>
							<td><input type="text" class="it " title="" value="" name=""/></td>
						</tr>
						<tr>
							<th scope="row">Context</th>
							<td><input type="text" class="it " title="" value="" name=""/></td>
						</tr>
						<tr>
							<th scope="row">Version</th>
							<td><input type="text" class="it " title="" value="" name=""/></td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<p class="layerFootBt">
				<a href="#" class="layerClose confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>

	<!-- 데이터 일괄 업로드 test [s]-->
<style>
/*
    #dropZone
    {
        border:2px dotted #3292A2;
        width:90%;
        height:50px;
        color:#92AAB0;
        text-align:center;
        font-size:24px;
        padding-top:12px;
        margin-top:10px;
    }
*/
</style>

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
    console.log("jgc debug=1");
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
            
            //if($.inArray(ext, ['exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml']) >= 0){
            console.log("jgc debug="+$.inArray(ext, ['xls', 'xlsx']));
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

                // 업로드 파일 목록 생성
                addFileList(fileIndex, fileName, fileSize);

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
    html +=         fileName + "<a href='#' onclick='deleteFile(" + fIndex + "); return false;' class='btn small bg_02'>삭제</a>"
    html += "    </td>"
    html += "</tr>"

    $('#fileTableTbody').append(html);
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
            url:"/ism/ord/ord020.do",
            data:formData,
            type:'POST',
            enctype:'multipart/form-data',
            processData:false,
            contentType:false,
            dataType:'json',
            cache:false,
            success:function(result){
                if(result.data.length > 0){
                    alert("성공");
                    location.reload();
                }else{
                    alert("실패");
                    location.reload();
                }
            }
        });
    }
}
function mfileonchange(files) {
	console.log(files.length);
	for (var i = 0; i < files.length; i++) {
		  var file = files[i];
		  console.log(file.name);
	}
}
</script>
	<div class="layerCont upload">
		<div class="inner">
			<p class="layerTit">데이터 일괄 업로드</p>
			<div class="layerContents">

    <form name="fileForm" action="requestupload2" method="post" enctype="multipart/form-data">
        <input multiple="multiple" type="file" name="mfile" name="mfile" onchange="javascript:mfileonchange(this.files);"/>
        <!-- <input type="text" name="src" /> -->
        <input type="submit" value="전송" />
    </form>

    <form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
    <a href="#" onclick="uploadFile(); return false;" class="btn bg_01">파일 업로드</a>
    <div id="dropZone">
        <table class="table" width="100%" border="1px" class="connectedSortableLeft js-multiselect">
            <tbody id="fileTableTbody">
                <tr>
                    <td>
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
				<a href="#" class="layerClose confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
	<!-- 데이터 일괄 업로드 test [e]-->
	
	<!-- 데이터 일괄 업로드 -->
	<div class="layerCont upload123">
		<div class="inner">
			<p class="layerTit">데이터 일괄 업로드</p>
			<div class="layerContents">
				<div class="sortable">
					<div class="sortableCon">
						<p class="soltableTit">반영 전 주문데이터</p>
						<select name="from[]" class="connectedSortableLeft js-multiselect" multiple="multiple">
							<option value="1">Item 1</option>
							<option value="2">Item 2</option>
							<option value="3">Item 3</option>
							<option value="4">Item 4</option>
							<option value="5">Item 5</option>
						</select>
					</div>
					<div class="connectedSortableBt">
						<a href="javascript:;" class="js_right_Selected">&gt;</a>
						<a href="javascript:;" class="js_left_Selected">&lt;</a>
					</div>
					<div class="sortableCon">
						<p class="soltableTit">반영 후 주문데이터</p>
						<select name="to[]" class="connectedSortableRight js_multiselect_to" multiple="multiple">
							<option value="6">Item 6</option>
							<option value="7">Item 7</option>
							<option value="8">Item 8</option>
							<option value="9">Item 9</option>
							<option value="10">Item 10</option>
						</select>
					</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function(){
						$('.js-multiselect').each(function(){
							var $ms = $(this);
							$ms.multiselect({
								right:$ms.parents('.sortable').find('.js_multiselect_to'),
								rightSelected:$ms.parents('.sortable').find('.js_right_Selected'),
								leftSelected:$ms.parents('.sortable').find('.js_left_Selected')
							});
						});
					});
				</script>
			</div>
			<p class="layerFootBt">
				<a href="#" class="layerClose confirm">확인</a>
				<a href="javascript:;" class="layerClose cancel">취소</a>
			</p>
			<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
		</div>
	</div>
</body>
</html>
