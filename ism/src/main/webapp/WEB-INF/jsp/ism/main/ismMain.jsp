<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title> KTI NMS </title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/Chart.bundle.js" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
<style>
.graphbtn li a {
    height: 38px;
    line-height: 36px;
    font-size: 15px;
}
.graphbtn li a.on {
    background-color: #3a5199;
    color: #fff;
}

.dashBoard .right li .bt a.on {
    background: #3d33da;
}

.dashBoard .right li .bt2 a.on {
    background: #3d33da;
}
.layerCont.layerCont_w2 {
    width: 600px;
}
</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
<!-- 전체 레이어 시작 -->
<div class="wrap">
	<c:import url="/sym/mms/EgovMainMenuHead.do" />
	<div class="container">
	    <!-- 좌측메뉴 시작 -->
	    <div class="lnb">
	    	<c:import url="/sym/mms/EgovMainMenuLeft.do" />
	    </div>
	    <!-- //좌측메뉴 끝 -->

		<div class="contentsWrap">
			<ul class="topBt leng4">
				<li><a href="/ism/ord/ord010.do">주문관리</a></li>
				<li><a href="/ism/po/po010.do">발주관리</a></li>
				<li><a href="/ism/skd/skd010.do">재고관리</a></li>
				<li><a href="javascript://" class="layerBt" name="dashBoardSetting">DashBoard 옵션</a></li>
			</ul>
			<div class="dashBoard">
				<div class="bigGraph" <c:if test="${dashBoardSetting.linegraph eq 'N'}">style="display:none;"</c:if> >
					<h2  style="float:left;" class="tit">단순통계(꺾은선 그래프)</h2>
					<div style="float:right;">기준일자 : 
						<div id="search_day_area" style="float:right;margin-left:10px;">
	<c:if test="${search_period eq 'D'}">
						<input type="text" class="datepicker" style="width:80px;" value="${search_day}" id="search_day" name="search_day"/>
	</c:if>
	<c:if test="${search_period eq 'M'}">
						<select id="search_day" name="search_day">
		<c:forEach items="${mapMonth}" var="entry">
							<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
						</select>
	</c:if>
	<c:if test="${search_period eq 'Y'}">
						<select id="search_day" name="search_day">
		<c:forEach items="${listYear}" var="entry">
							<option value="${entry}">${entry}</option>
		</c:forEach>
						</select>
	</c:if>
						</div>
					</div>
					<div class="graph">
						<canvas id="myChartLine" width="500" height="200"></canvas>
					</div>
					<div class="graphbtn">
						<ul class="topBt leng3 selProd">
							<li><a id="selProdD" class="<c:if test='${search_period eq \"D\"}'>on</c:if>" href="javascript:setDataPeriod('D','');">일별</a></li>
							<li><a id="selProdM" class="<c:if test='${search_period eq \"M\"}'>on</c:if>" href="javascript:setDataPeriod('M','');">월별</a></li>
							<li><a id="selProdY" class="<c:if test='${search_period eq \"Y\"}'>on</c:if>" href="javascript:setDataPeriod('Y','');">년별</a></li>
						</ul>
						<ul class="topBt leng4 selType">
							<li><a id="selTypeA" class="<c:if test='${search_type eq \"A\"}'>on</c:if>" href="javascript:setDataType('A','');">총매출액</a></li>
							<li><a id="selTypeP" class="<c:if test='${search_type eq \"P\"}'>on</c:if>" href="javascript:setDataType('P','');">상품별</a></li>
							<li><a id="selTypeC" class="<c:if test='${search_type eq \"C\"}'>on</c:if>" href="javascript:setDataType('C','');">매출처별</a></li>
							<li><a id="selTypeB" class="<c:if test='${search_type eq \"B\"}'>on</c:if>" href="javascript:setDataType('B','');">매입처별</a></li>
						</ul>
					</div>
				</div>
				<div class="bigGraph" <c:if test="${dashBoardSetting.bargraph eq 'N'}">style="display:none;"</c:if>>
					<h2  style="float:left;" class="tit">비교통계 (막대그래프)</h2>
					<div style="float:right;">기준일자 : 
						<div id="search_day_areaBar" style="float:right;margin-left:10px;">
	<c:if test="${search_periodBar eq 'D'}">
						<input type="text" class="datepicker" style="width:80px;" value="${search_dayBar}" id="search_dayBar" name="search_dayBar"/>
	</c:if>
	<c:if test="${search_periodBar eq 'M'}">
						<select id="search_dayBar" name="search_dayBar">
		<c:forEach items="${mapMonthBar}" var="entry">
							<option value="${entry.key}">${entry.value}</option>
		</c:forEach>
						</select>
	</c:if>
	<c:if test="${search_periodBar eq 'Y'}">
						<select id="search_dayBar" name="search_dayBar">
		<c:forEach items="${listYearBar}" var="entry">
							<option value="${entry}">${entry}</option>
		</c:forEach>
						</select>
	</c:if>
						</div>
					</div>
					<div class="graph">
						<canvas id="myChartBar" width="500" height="200"></canvas>
					</div>
					<div class="graphbtn">
						<ul class="topBt leng3 selProdBar">
							<li><a id="selProdDBar" class="<c:if test='${search_periodBar eq \"D\"}'>on</c:if>" href="javascript:setDataPeriod('D','Bar');">일별</a></li>
							<li><a id="selProdMBar" class="<c:if test='${search_periodBar eq \"M\"}'>on</c:if>" href="javascript:setDataPeriod('M','Bar');">월별</a></li>
							<li><a id="selProdYBar" class="<c:if test='${search_periodBar eq \"Y\"}'>on</c:if>" href="javascript:setDataPeriod('Y','Bar');">년별</a></li>
						</ul>
						<ul class="topBt leng4 selTypeBar">
							<li><a id="selTypeABar" class="<c:if test='${search_typeBar eq \"A\"}'>on</c:if>" href="javascript:setDataType('A','Bar');">총매출액</a></li>
							<li><a id="selTypePBar" class="<c:if test='${search_typeBar eq \"P\"}'>on</c:if>" href="javascript:setDataType('P','Bar');">상품별</a></li>
							<li><a id="selTypeCBar" class="<c:if test='${search_typeBar eq \"C\"}'>on</c:if>" href="javascript:setDataType('C','Bar');">매출처별</a></li>
							<li><a id="selTypeBBar" class="<c:if test='${search_typeBar eq \"B\"}'>on</c:if>" href="javascript:setDataType('B','Bar');">매입처별</a></li>
						</ul>
					</div>
				</div>
				<ul class="right">
<c:if test="${dashBoardSetting.ipStatus eq 'Y'}">
					<li>
						<p class="tit">
							<strong>재고상품입고현황</strong>
							<span>데이터반영시점<br/>${countAndTime.ipTime}</span>
						</p>
						<p class="num">
							<strong id="dispip">${countAndTime.ipDayCnt}</strong>
							<span>건</span>
						</p>
						<p id="btnip" class="bt">
							<a href="#" onclick="setCntDisp(this,'ip','ipDayCnt')" class="on">일단위</a>
							<a href="#" onclick="setCntDisp(this,'ip','ipWeekCnt')">주단위</a>
							<a href="#" onclick="setCntDisp(this,'ip','ipMonthCnt')">월단위</a>
						</p>
					</li>
</c:if>
<c:if test="${dashBoardSetting.otStatus eq 'Y'}">
					<li>
						<p class="tit">
							<strong>출고대기현황</strong>
							<span>데이터반영시점<br/>${countAndTime.otTime}</span>
						</p>
						<p class="num">
							<strong id="dispot">${countAndTime.otDayCnt}</strong>
							<span>건</span>
						</p>
						<p id="btnot" class="bt">
							<a href="#" onclick="setCntDisp(this,'ot','otDayCnt')" class="on">일단위</a>
							<a href="#" onclick="setCntDisp(this,'ot','otWeekCnt')">주단위</a>
							<a href="#" onclick="setCntDisp(this,'ot','otMonthCnt')">월단위</a>
						</p>
					</li>
</c:if>
<c:if test="${dashBoardSetting.prodStatus eq 'Y'}">
					<li>
						<p class="tit">
							<strong>현재운영중인상품수</strong>
							<span>데이터반영시점<br/>${countAndTime.prodTime}</span>
						</p>
						<p class="num">
							<strong id="dispprod">${countAndTime.prodMakerCnt}</strong>
							<span>건</span>
						</p>
						<p id="btnprod" class="bt2">
							<a href="#" onclick="setCntDisp(this,'prod','prodMakerCnt')" class="on">제조사 출고상품</a>
							<a href="#" onclick="setCntDisp(this,'prod','prodStockCnt')">재고상품</a>
						</p>
					</li>
</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 전체 레이어 끝 -->
<input type="hidden" id="search_period" name="search_period" value="${search_period}" />
<input type="hidden" id="search_type" name="search_type" value="${search_type}" />
<input type="hidden" id="search_periodBar" name="search_periodBar" value="${search_periodBar}" />
<input type="hidden" id="search_typeBar" name="search_typeBar" value="${search_typeBar}" />

<input type="hidden" id="hipDayCnt" name="hipDayCnt" value="${countAndTime.ipDayCnt}" />
<input type="hidden" id="hipWeekCnt" name="hipWeekCnt" value="${countAndTime.ipWeekCnt}" />
<input type="hidden" id="hipMonthCnt" name="hipMonthCnt" value="${countAndTime.ipMonthCnt}" />
<input type="hidden" id="hotDayCnt" name="hotDayCnt" value="${countAndTime.otDayCnt}" />
<input type="hidden" id="hotWeekCnt" name="hotWeekCnt" value="${countAndTime.otWeekCnt}" />
<input type="hidden" id="hotMonthCnt" name="hotMonthCnt" value="${countAndTime.otMonthCnt}" />
<input type="hidden" id="hprodMakerCnt" name="hprodMakerCnt" value="${countAndTime.prodMakerCnt}" />
<input type="hidden" id="hprodStockCnt" name="hprodStockCnt" value="${countAndTime.prodStockCnt}" />


<!-- 대쉬보드 옵션 [s]-->
<form id="dashBoardSettingform" name="dashBoardSettingform" action="/ism/main/mainSaveDashBoardSetting.do">
<div class="layerCont layerCont_w2 dashBoardSetting">
	<div class="inner">
		<p class="layerTit">DASHBOARD 옵션 설정</p>
		<div class="layerContents">
			<table cellpadding="0" cellspacing="0" class="apitb" summary="" >
				<caption></caption>
				<colgroup>
					<col width="50%"/><col width="50%"/>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">꺽은선 그래프</th>
						<td><input type="checkbox" name="linegraph" id="linegraph" value="Y" <c:if test="${dashBoardSetting.linegraph eq 'Y'}">checked</c:if>></td>
					</tr>
					<tr>
						<th scope="row">막대 그래프</th>
						<td><input type="checkbox" name="bargraph" id="bargraph" value="Y" <c:if test="${dashBoardSetting.bargraph eq 'Y'}">checked</c:if>></td>
					</tr>
					<tr>
						<th scope="row">재고상품입고현황</th>
						<td><input type="checkbox" name="ipStatus" id="ipStatus" value="Y" <c:if test="${dashBoardSetting.ipStatus eq 'Y'}">checked</c:if>></td>
					</tr>
					<tr>
						<th scope="row">출고대기현황</th>
						<td><input type="checkbox" name="otStatus" id="otStatus" value="Y" <c:if test="${dashBoardSetting.otStatus eq 'Y'}">checked</c:if>></td>
					</tr>
					<tr>
						<th scope="row">현재운영중인상품수</th>
						<td><input type="checkbox" name="prodStatus" id="prodStatus" value="Y" <c:if test="${dashBoardSetting.prodStatus eq 'Y'}">checked</c:if>></td>
					</tr>
				</tbody>
			</table>
			
		</div>
		<p class="layerFootBt">
			<span id="msgarea" style="float:left;color:#0878ea;"></span>
			<a href="javascript:saveDashBoardSetting();" class="confirm">확인</a>
			<a href="javascript:;" class="layerClose cancel">취소</a>
		</p>
		<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>
</form>
<!-- 대쉬보드 옵션 [e]-->
</body>
</html>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>

<script type="text/javascript">
<c:set var="cntadjdate" value="0"/>
<c:set var="stradjdate" value=""/>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:set var="cntadjdate" value="${cntadjdate+1}"/>
	<c:set var="stradjdate" value="${stradjdate}\n${result.shopmallname}"/>
</c:forEach>
var cntadjdate = "${cntadjdate}";
var stradjdate = "${stradjdate}";
//기초 선언 [s]
//기본 색상 14개 setting
var colorArra = ["#494841", "#0099a4", "#000080", "#c53c23", "#6a8518", "#003458", "#fbceb1", "#ff3399", "#392f31", "#f7e600", "#008d62", "#660099", "#800000", "#ff0000"];

var barChartData = {}; //차트 데이터 변수선언
var barChartDataBar = {}; //차트 데이터 변수선언

//기초 차트 setting
var ctx = $("#myChartLine");
var myChart = new Chart(ctx, {
    type: 'line',
    data: barChartData
});

var ctxbar = $("#myChartBar");
var myChartBar = new Chart(ctxbar, {
    type: 'bar',
    data: barChartDataBar
});
//기초 선언 [e]

//Line 차트에 데이터 반영 [s]
var aaa = "${graphVal.labels}"; //하단 데이터 라벨
var jbSplit = aaa.split(",");   //하단 데이터 라벨 split

//하단 데이터 라벨 차트 데이터에 setting
for ( var i in jbSplit ) {
    if (jbSplit[i] != "") barChartData.labels[i] = jbSplit[i];
}

//읽어온 dataset 추가
<c:forEach var="item" items="${graphVal.datas}" varStatus="status">
	//dataset 선언
	var newDataset = {
		label: "${item.titles}",
		backgroundColor: colorArra["${status.index}"],
		borderColor: colorArra["${status.index}"],
		data: [],
		fill: false,
        lineTension: 0
	};

	//dataset의 데이터 setting
	var itemvals = "${item.values}";
	var itemvalsSplit = itemvals.split(",");

	for ( var i in itemvalsSplit ) {
		newDataset.data.push(itemvalsSplit[i]);
	}

	//새로 생성한 dataset 주입
	barChartData.datasets.push(newDataset);
</c:forEach>

//차트 업데이트
myChart.update();
//Line 차트에 데이터 반영 [e]

//Bar 차트에 데이터 반영 [s]
var aaaBar = "${graphValBar.labels}"; //하단 데이터 라벨
var jbSplitBar = aaaBar.split(",");   //하단 데이터 라벨 split

//하단 데이터 라벨 차트 데이터에 setting
for ( var i in jbSplitBar ) {
    if (jbSplitBar[i] != "") barChartDataBar.labels[i] = jbSplitBar[i];
}

//읽어온 dataset 추가
<c:forEach var="item" items="${graphValBar.datas}" varStatus="status">
	//dataset 선언
	var newDatasetBar = {
		label: "${item.titles}",
		backgroundColor: colorArra["${status.index}"],
		borderColor: colorArra["${status.index}"],
		data: [],
		fill: false,
        lineTension: 0
	};

	//dataset의 데이터 setting
	var itemvalsBar = "${item.values}";
	var itemvalsSplitBar = itemvalsBar.split(",");

	for ( var i in itemvalsSplitBar ) {
		newDatasetBar.data.push(itemvalsSplitBar[i]);
	}

	//새로 생성한 dataset 주입
	barChartDataBar.datasets.push(newDatasetBar);
</c:forEach>

//차트 업데이트
myChartBar.update();
//Bar 차트에 데이터 반영 [e]

$(document).ready(function(){
	setdatepicker();

	$("body").on("change", "#search_day", function() {
		setData($(this).val(), $("#search_period").val(), $("#search_type").val(), "");
	});

	$("body").on("change", "#search_dayBar", function() {
		setData($(this).val(), $("#search_periodBar").val(), $("#search_typeBar").val(), "Bar");
	});

	if (cntadjdate > 0) {
		alert("아래 업체의 보증보험일자 확인 바랍니다.\n"+stradjdate);
	}
});

//일별, 월별, 년별 버튼 클릭시 처리
function setDataPeriod(search_period, isBar) {
	//차트 초기화
	clearOnPeriod(isBar);

	//버튼 class on
	$("#selProd"+search_period+isBar).attr("class","on");

	//클릭값 hidden에 저장
	$("#search_period"+isBar).val(search_period);
	
	//년,월,일 로 변경시 기준일자 초기화 후 화면에 다시 그림
	var setHtml = "";
	var toDay = new Date();
	var search_day = "";
	var toDayMonth = "";
	var toDayDate = "";
	
	if (toDay.getMonth() <= 9) toDayMonth = "0"+ (toDay.getMonth()+1);
	else toDayMonth = ""+ (toDay.getMonth()+1);
	
	if (toDay.getDate() <= 9) toDayDate = "0"+ toDay.getDate();
	else toDayDate = ""+ toDay.getDate();

	if (search_period == "D") { //일자별
		search_day = toDay.getFullYear() + "-" +toDayMonth + "-" + toDayDate;
		setHtml = "<input type='text' class='datepicker' style='width:80px;' value="+search_day+" id='search_day"+isBar+"' name='search_day"+isBar+"'/>";
	}else if (search_period == "M") { //월별
		search_day = "" + toDay.getFullYear() + toDayMonth;
		setHtml = "<select id='search_day"+isBar+"' name='search_day"+isBar+"'>";
		if (isBar == "") {
			<c:forEach items="${mapMonth}" var="entry">
				setHtml = setHtml + "<option value='${entry.key}'>${entry.value}</option>";
			</c:forEach>
		}else{
			<c:forEach items="${mapMonthBar}" var="entry">
				setHtml = setHtml + "<option value='${entry.key}'>${entry.value}</option>";
			</c:forEach>
		}
		setHtml = setHtml + "</select>";
	}else if (search_period == "Y") { //년별
		search_day = "" + toDay.getFullYear();
		setHtml = "<select id='search_day"+isBar+"' name='search_day"+isBar+"'>";
		if (isBar == "") {
			<c:forEach items="${listYear}" var="entry">
				setHtml = setHtml + "<option value='${entry}'>${entry}</option>";
			</c:forEach>
		}else{
			<c:forEach items="${listYearBar}" var="entry">
				setHtml = setHtml + "<option value='${entry}'>${entry}</option>";
			</c:forEach>
		}
		setHtml = setHtml + "</select>";
	}
	$("#search_day_area"+isBar).html(setHtml);

	//일자별일때 datepicker 기능 setting
	setdatepicker();
	
	//실제 조건에 맞게 데이터 호출
	setData(search_day, search_period, $("#search_type"+isBar).val(), isBar);
}

//총매출액, 상품별, 매출처별, 매입처별 버튼 클릭시 처리
function setDataType(search_type, isBar) {
	//차트 초기화
	clearOnType(isBar);

	//버튼 class on
	$("#selType"+search_type+isBar).attr("class","on");

	//클릭값 hidden에 저장
	$("#search_type"+isBar).val(search_type);

	//실제 조건에 맞게 데이터 호출
	setData($("#search_day"+isBar).val(), $("#search_period"+isBar).val(), search_type, isBar);
}

function setData(search_day, search_period, search_type, isBar) {
	console.log("search_day = "+search_day);
	console.log("search_period = "+search_period);
	console.log("search_type = "+search_type);
	console.log("isBar = "+isBar);
	$.ajax({
        url : "/ism/main/mainPageChart.do",
        type: "post",
        data : { "search_day" : search_day, "search_period" : search_period, "search_type" : search_type, "isBar" : isBar},
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(data){
        	//모든 dataset 지운다[s]
			var objData;
			if (isBar == "") {
				objData = barChartData;
			}else{
				objData = barChartDataBar;
			}
        	var datasetslength = objData.datasets.length;
        	for(i=0;i<datasetslength;i++) {
        		objData.datasets.splice(0, 1);
        	}

			if (isBar == "") {
	        	myChart.update();
			}else{
	        	myChartBar.update();
			}
        	//모든 dataset 지운다[e]

        	//차트에 데이터 반영 [s]
        	var aaa = data.labels;
        	var jbSplit = aaa.split(",");
            
        	for ( var i in jbSplit ) {
                if (jbSplit[i] != "") objData.labels[i] = jbSplit[i];
            }

            $.each(data.datas, function(index, item){            	
            	var bbb = item.values;
            	var bjbSplit = bbb.split(",");
				
				var newDataset = {
					label: item.titles,
					backgroundColor: colorArra[index],
					borderColor: colorArra[index],
					data: [],
					fill: false,
			        lineTension: 0
				};
			
				for ( var i in bjbSplit ) {
					newDataset.data.push(bjbSplit[i]);
				}
			
				objData.datasets.push(newDataset);

            });

			if (isBar == "") {
	        	myChart.update();
			}else{
	        	myChartBar.update();
			}
        	//차트에 데이터 반영 [e]
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

function clearOnPeriod(isBar) {
	//버튼 class clear
	$(".selProd"+isBar).children().find("a").each(function(index, item){
		$(this).attr("class","");
	});

	//그래프의 labels를 모두 지운다
	var objData;
	if (isBar == "") {
		objData = barChartData;
	}else{
		objData = barChartDataBar;
	}
	var labelslength = objData.labels.length;
	for(i=0;i<labelslength;i++) {
		objData.labels.splice(-1, 1);
	}


	if (isBar == "") {
		myChart.update();
	}else{
		myChartBar.update();
	}
}

function clearOnType(isBar) {
	//버튼 class clear
	$(".selType"+isBar).children().find("a").each(function(index, item){
		$(this).attr("class","");
	});
}

function setCntDisp(obj, idx, objname) {
	$("#btn"+idx).find("a").each(function(index, item){
		$(this).attr("class","");
	});
	
	$(obj).attr("class","on");
	
	$("#disp"+idx).text($("#h"+objname).val());
}

function saveDashBoardSetting() {
    var options = {
    	success : function(data) {
        	alert("저장되었습니다.");
        	location.href="/";
        },
        error : function(xhr, status, error) {
        	console.log(error);
       	},
        type : "POST"
    };
    $("#dashBoardSettingform").ajaxSubmit(options);
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