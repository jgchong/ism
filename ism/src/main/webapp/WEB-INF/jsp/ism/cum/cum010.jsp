<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>E-DAS</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="/js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />
<style type="text/css">
.paging a {
	margin : 0 3px 0 3px;
}
.paging a.on {
    background: #666;
    color: #fff;
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
.layerTb .selcs1 {
    width: 43%;
}
.itcs1 {
	width:49%;display:none;float:right;text-align:center;
}
.rowPointer {
	cursor: pointer;
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
					<form id="formMain" name="formMain" method="post" action="" class="searchArea">
						<!--a href="javascript:;" class="" style="background:#45b6b6;">매출처 다운로드</a-->
						<input type="text" class="it ml30" title="" value="${cum010SearchVO.search_coname}" id="search_coname" name="search_coname"/>
						<button style="margin-left:-4px;">검색</button>
					</form>
					<div class="listTb">
						<table cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<!--<col width="5%"/>//-->
								<col width="3%"/>
								<col width="13%"/><col width="18%"/>
								<col width="8%"/><col width="8%"/>
								<col width="*"/><col width="10%"/><col width="5%"/>
								<col width="5%"/>
							</colgroup>
							<thead>
								<tr>
									<!-- <th scope="col"><input type="checkbox" value="" name=""/></th> -->
									<th scope="col">NO.</th>
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
								<tr onclick="viewCumData('${result.cum010id}')" class="layerBt rowPointer" name="sales">
									<td><strong><c:out value="${(cum010SearchVO.pageIndex - 1) * cum010SearchVO.pageSize + status.count}"/></strong></td>
									<td>${result.cotype1nm} > ${result.cotype2nm} > ${result.cotype3nm}</td>
									<td><c:out value="${result.coname}"/></td>
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

					<div class="paging">
						<ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="fnLinkPage" />
					</div>

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
			<a href="javascript:saveCumAll();" class="confirm">저장</a>
			<a href="javascript://" onclick="closeLayerPop()" class="cancel layerClose">닫기</a>
		</p>
		<a href="javascript://" onclick="closeLayerPop()" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>
<input type="hidden" id="hcum010id" name="cum010id" />
</form>

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
			"				<th scope='row'>상호</th>"+
			"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(data.coname.replace(Ca, " "))+"' id='coname' name='coname'/></td>"+
			"				<th scope='row'>업체구분</th>"+
			"				<td>"+
			"					<select class='sel3' id='cotype1' name='cotype1' onchange='cotypeChg(1)' title=''>"+
			"						<option value=''>대분류</option>${ISM020}"+
			"					</select>"+
			"					<select class='sel3' id='cotype2' name='cotype2' onchange='cotypeChg(2)' title=''>"+
			"						<option value=''>중분류</option>"+
			"					</select>"+
			"					<select class='sel3' id='cotype3' name='cotype3' onchange='cotypeChg(3)' title=''>"+
			"						<option value=''>소분류</option>"+
			"					</select>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>사업자구분</th>"+
			"				<td>"+
			"					<input type='radio' name='cogubun' value='1' id='sel1_1'/><label for='sel1_1'>법인</label>"+
			"					<input type='radio' name='cogubun' value='2' id='sel1_2'/><label for='sel1_2'>개인</label>"+
			"					<input type='radio' name='cogubun' value='3' id='sel1_3'/><label for='sel1_3'>기타</label>"+
			"				</td>"+
			"				<th scope='row'>사업자번호</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.cono+"' name='cono'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>법인등록번호</th>"+
			"				<td><input type='text' class='it ' title='' value='"+data.lawcono+"' name='lawcono'/></td>"+
			"				<th scope='row'>업태/종목</th>"+
			"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(data.cobustype.replace(Ca, " "))+"' name='cobustype'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>회사주소</th>"+
			"				<td colspan='3' class='l'><input type='text' class='it ' title='' value='"+decodeURIComponent(data.coaddr.replace(Ca, " "))+"' name='coaddr'/></td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>정산<br/>(세금계산서발행)</th>"+
			"				<td colspan='3'>"+
			"					<input type='radio' name='account' value='1' id='sel2_1'/><label for='sel2_1'>정발행</label>"+
			"					<input type='radio' name='account' value='2' id='sel2_2'/><label for='sel2_2'>역발행</label> /&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			"					<input type='radio' name='account2' value='1' id='sel3_1'/><label for='sel3_1'>공급가 정산</label>"+
			"					<input type='radio' name='account2' value='2' id='sel3_2'/><label for='sel3_2'>수수료 정산</label>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>대금정산</th>"+
			"				<td>"+
			"					<select id='accountamt' name='accountamt' onchange='accountamtChg()' title=''>"+
			"						<option value=''>선택</option>${ISM0A0}"+
			"					</select>"+
			"                   <input type='text' class='itcs1 datepicker' title='' value='"+data.accountamtdate+"' id='accountamtdate' name='accountamtdate'/>"+
			"				</td>"+
			"				<th scope='row'>매출처거래여부</th>"+
			"				<td>"+
			"					<input type='radio' name='useyn' id='sel4_1' value='Y' checked/><label for='sel4_1'>사용</label>"+
			"					<input type='radio' name='useyn' id='sel4_2' value='N'/><label for='sel4_2'>비사용</label>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>파일첨부<br/>(구비서류)</th>"+
			"				<td colspan='3' style='text-align:left;'>"+
			"                   <input type='text' class='it' style='width: 80%;' onclick='downLoadFile("+data.cmm020id+")' value='"+decodeURIComponent(data.orgfilename.replace(Ca, " "))+"' id='attachfilename' name='attachfilename' readonly /> &nbsp; "+
			"                   <label for='attachfile'>파일선택</label> &nbsp;  &nbsp; "+
			"                   <label><a href='javascript:delAttchFile();'>파일삭제</a></label>"+
			"                   <input type='file' id='attachfile' name='attachfile' onchange='FileUpload(this)' class='hidden'/>"+
			"                   <input type='hidden' value='"+data.cmm020id+"' id='cmm020id' name='cmm020id' />"+
			"               </td>"+
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
			
			if (Object.keys(data.cum020).length > 0) {
				$.each(data.cum020,function(index, item) {
					addhtml = addhtml +
					"			<tr>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.cumusername.replace(Ca, " "))+"' name='cumusername'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.cumusertel.replace(Ca, " "))+"' name='cumusertel'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.cumuseremail.replace(Ca, " "))+"' name='cumuseremail'/></td>"+
					"				<td colspan='1'><input type='text' class='it ' title='' value='"+decodeURIComponent(item.cummemo.replace(Ca, " "))+"' name='cummemo'/></td>"+
					"				<td><a href='javascript://' onclick='delrow(this)'>del</a></td>"+
					"			</tr>";
				});
			}else{
				addhtml = addhtml +
				"			<tr>"+
				"				<td><input type='text' class='it ' title='' value='' name='cumusername'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='cumusertel'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='cumuseremail'/></td>"+
				"				<td colspan='1'><input type='text' class='it ' title='' value='' name='cummemo'/></td>"+
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
			"			<col width='*'/><col width='*'/><col width='10%'/>"+
			"			<col width='10%'/><col width='*'/><col width='8%'/><col width='10%'/><col width='6%'/>"+
			"		</colgroup>"+
			"		<thead>"+
			"			<tr>"+
			"				<th scope='row' colspan='8' class='tit'>어드민</th>"+
			"			</tr>"+
			"			<tr>"+
			"				<th scope='row'>쇼핑몰명</th>"+
			"				<th scope='row'>URL</th>"+
			"				<th scope='row'>아이디</th>"+
			"				<th scope='row'>패스워드</th>"+
			"				<th scope='row'>업로드타입</th>"+
			"				<th scope='row'>사용</th>"+
			"				<th scope='row'>업로드구분</th>"+
			"				<th scope='row'><a href='javascript:addShop();' class='celPlus'>+</a></th>"+
			"			</tr>"+
			"		</thead>"+
			"		<tbody>";
			if (Object.keys(data.cum030).length > 0) {
				$.each(data.cum030,function(index, item) {
					addhtml = addhtml +
					"			<tr>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.shopmallname.replace(Ca, " "))+"' name='shopmallname'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.shopurl.replace(Ca, " "))+"' name='shopurl'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.shopuid.replace(Ca, " "))+"' name='shopuid'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+item.shoppwd+"' name='shoppwd'/></td>"+
					"				<td><input type='text' class='it ' title='' value='"+decodeURIComponent(item.uploadtype.replace(Ca, " "))+"' name='uploadtype'/></td>";
					if (item.useyn == "Y") {
						addhtml = addhtml +
						"               <td><select name='shopUseYn'><option value='Y' selected>Y</option><option value='N'>N</option></select></td>"
					}else{
						addhtml = addhtml +
						"               <td><select name='shopUseYn'><option value='Y'>Y</option><option value='N' selected>N</option></select></td>"
						
					}
					
					if (item.uploadgubun == "M") {
						addhtml = addhtml +
						"               <td colspan='1'><select name='uploadgubun'><option value='M' selected>수동</option><option value='A'>API</option></select></td>"
					}else{
						addhtml = addhtml +
						"               <td colspan='1'><select name='uploadgubun'><option value='M'>수동</option><option value='A' selected>API</option></select></td>"
						
					}
					addhtml = addhtml +
					"					<td><a href='javascript://' onclick='delrow(this, 1)'>del</a>"+
					"					<input type='hidden' name='cum030id' value='"+item.cum030id+"'/></td>"+
					"			</tr>";
				});
			}else{
				addhtml = addhtml +
				"			<tr>"+
				"				<td><input type='text' class='it ' title='' value='' name='shopmallname'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='shopurl'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='shopuid'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='shoppwd'/></td>"+
				"				<td><input type='text' class='it ' title='' value='' name='uploadtype'/></td>"+
				"               <td><select name='shopUseYn'><option value='Y'>Y</option><option value='N'>N</option></select></td>"+
				"               <td colspan='1'><select name='uploadgubun'><option value='M'>수동</option><option value='A'>API</option></select></td>"+
				"				<td><a href='javascript://' onclick='delrow(this, 1)'>del</a><input type='hidden' name='cum030id' value='0'/></td>"+
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
			"                   <div class='memoTxt'><ul id='memoul'>"+decodeURIComponent(data.cumMemo.replace(Ca, " "))+"</ul></div>"+
			"				</td>"+
			"			</tr>"+
			"			<tr>"+
			"				<td>"+
			"					<p class='memo'>"+
			"						<input type='text' class='it' title='' value='' id='inputmemo' name='inputmemo'/>"+
			"						<a onclick='inputmemodata(\"CM\","+data.cum010id+")'>입력</a>"+
			"					</p>"+
			"				</td>"+
			"			</tr>"+
			"		</tbody>"+
			"	</table>"+
			"</div>";
        	$('#cumDetailData').html(addhtml);
        	$("#cotype1").val(data.cotype1);
        	
        	if (data.cotype1 == "ISM031") $("#cotype2").append("${ISM031}");
        	else if (data.cotype1 == "ISM032") $("#cotype2").append("${ISM032}");
        	$("#cotype2").val(data.cotype2);
        	
        	if (data.cotype2 == "ISM041") $("#cotype3").append("${ISM041}");
        	else if (data.cotype2 == "ISM042") $("#cotype3").append("${ISM042}");
        	else if (data.cotype2 == "ISM043") $("#cotype3").append("${ISM043}");
        	else if (data.cotype2 == "ISM044") $("#cotype3").append("${ISM044}");
        	$("#cotype3").val(data.cotype3);
        	
        	if (data.cogubun != "") {
            	$('input:radio[name=cogubun]:input[value=' + data.cogubun + ']').attr("checked", true);
        	}else{
            	$('input:radio[name=cogubun]:input[value=1]').attr("checked", true);
        	}
        	if (data.account != "") {
            	$('input:radio[name=account]:input[value=' + data.account + ']').attr("checked", true);
        	}else{
        		//정산 default 값 정발행으로 set
        		$('input:radio[name=account]:input[value=1]').attr("checked", true);
        	}
        	if (data.account2 != "") {
            	$('input:radio[name=account2]:input[value=' + data.account2 + ']').attr("checked", true);
        	}else{
        		//정산 default 값 공급가정산으로 set
        		$('input:radio[name=account2]:input[value=1]').attr("checked", true);
        	}
        	if (data.uploadgubun != "") {
            	$('input:radio[name=uploadgubun]:input[value=' + data.uploadgubun + ']').attr("checked", true);
        	}
        	if (data.useyn != "") {
            	$('input:radio[name=useyn]:input[value=' + data.useyn + ']').attr("checked", true);
        	}
        	$("#accountamt").val(data.accountamt);
        	accountamtChg(); //위 대금정산이 3일 경우 일자입력 input display 하기 위해
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
		"				<td><a href='javascript://' onclick='delrow(this, 1)'>del</a><input type='hidden' name='cum030id' value='0'/></td>"+
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
	//대금정산 타입이 보증보험 발행이 3이 아니면 일자 clear
	if ($("#accountamt").val() != "3") {
		$("#accountamtdate").val("");
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
function accountamtChg() {
	if($("#accountamt").val()=="3") {
		$("#accountamt").attr("class","selcs1");
		$("#accountamtdate").css("display","block");
		setdatepicker();
	}else{
		$("#accountamt").removeClass("selcs1");
		$("#accountamtdate").css("display","none");
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
function delAttchFile() {
	$('#attachfilename').val('');
	$('#cmm020id').val('0');
	
	var agent = navigator.userAgent.toLowerCase();
	if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ){
	    // ie 일때 input[type=file] init.
	    $("#attachfile").replaceWith( $("#excelFile").clone(true) );
	} else {
	    //other browser 일때 input[type=file] init.
	    $("#attachfile").val("");
	}
}
</script>


<script type="text/javascript">
function setdatepicker() {
    $(function () {
        $(".datepicker").datepicker({
            dateFormat : "yy-mm-dd",
            monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
            changeMonth : true,
            changeYear : true,
            yearRange : "c-70:c+70",
            showMonthAfterYear : true
        });
    });
}
</script>
