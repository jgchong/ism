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
			<ul class="topBt leng3">
				<li><a href="javascript:setData();">주문관리</a></li>
				<li><a href="javascript:setData2();">발주관리</a></li>
				<li><a href="m4.html">재고관리</a></li>
			</ul>
			<div class="dashBoard">
				<div class="bigGraph">
					<h2 class="tit">단순통계(꺾은선 그래프) / 비교통계 (막대그래프)</h2>
					<div class="graph">
						<canvas id="myChart" width="500" height="200"></canvas>
					</div>
					<div class="graphbtn">
						<ul class="topBt leng3">
							<li><a class="on" href="javascript:setData();">년별</a></li>
							<li><a href="javascript:setData2();">월별</a></li>
							<li><a href="m4.html">일별</a></li>
						</ul>
						<ul class="topBt leng4">
							<li><a class="on" href="javascript:setData();">총매출액</a></li>
							<li><a href="javascript:setData2();">상품별</a></li>
							<li><a href="m4.html">매출처별</a></li>
							<li><a href="m4.html">매입처별</a></li>
						</ul>
					</div>
				</div>
				<ul class="right">
					<li>
						<p class="tit">
							<strong>재고상품입고현황</strong>
							<span>데이터반영시점<br/>2018.04.04 13:30</span>
						</p>
						<p class="num">
							<strong>40</strong>
							<span>건</span>
						</p>
						<p class="bt">
							<a href="#">월단위</a>
							<a href="#">일단위</a>
							<a href="#">연단위</a>
						</p>
					</li>
					<li>
						<p class="tit">
							<strong>재고상품출고현황</strong>
							<span>데이터반영시점<br/>2018.04.04 13:30</span>
						</p>
						<p class="num">
							<strong>40</strong>
							<span>건</span>
						</p>
						<p class="bt">
							<a href="#">월단위</a>
							<a href="#">일단위</a>
							<a href="#">연단위</a>
						</p>
					</li>
					<li>
						<p class="tit">
							<strong>현재운영중인상품수</strong>
							<span>데이터반영시점<br/>2018.04.04 13:30</span>
						</p>
						<p class="num">
							<strong>40</strong>
							<span>건</span>
						</p>
						<p class="bt2">
							<a href="#">제조사 출고상품</a>
							<a href="#">재고상품</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 전체 레이어 끝 -->
</body>
</html>
<script type="text/javascript">
var barChartData = {
		datasets: [{
            label: '매출액',
			yAxisID: 'y-axis-2',

            // Changes this dataset to become a line
            type: 'line',
			fill: false,
			borderColor: '#ff00ff',
            lineTension: 0
        }, {
            label: 'prod1',
			yAxisID: 'y-axis-1',
            backgroundColor: '#ff0000'
        }, {
            label: 'prod2',
			yAxisID: 'y-axis-1',
            backgroundColor: '#99ff00'
        }, {
            label: 'prod3',
			yAxisID: 'y-axis-1',
            backgroundColor: '#5500ff'
        }, {
            label: 'prod4',
			yAxisID: 'y-axis-1',
            backgroundColor: '#440000'
        }, {
            label: 'prod5',
			yAxisID: 'y-axis-1',
            backgroundColor: '#110000'
        }],
		labels: ['2014년', '2015년', '2016년', '2017년', '2018년']
    };
	
var ctx = $("#myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: barChartData,
	options: {
		scales: {
			yAxes: [{
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'left',
				id: 'y-axis-1',
				ticks: { beginAtZero:true }
			}, {
				type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
				display: true,
				position: 'right',
				id: 'y-axis-2',
				gridLines: {
					drawOnChartArea: false
				},
				ticks: { beginAtZero:true }
			}],
		}
	}
});

function setData() {
	var resultArr1 = new Array();
	var resultArr2 = new Array();
	var resultArr3 = new Array();
	var resultArr4 = new Array();
	var resultArr5 = new Array();
	var resultArr6 = new Array();

<c:forEach var="item" items="${graphVal.amt}">
	resultArr1.push("${item}");
</c:forEach>

<c:forEach var="item" items="${graphVal.prod1}">
resultArr2.push("${item}");
</c:forEach>
<c:forEach var="item" items="${graphVal.prod2}">
resultArr3.push("${item}");
</c:forEach>
<c:forEach var="item" items="${graphVal.prod3}">
resultArr4.push("${item}");
</c:forEach>
<c:forEach var="item" items="${graphVal.prod4}">
resultArr5.push("${item}");
</c:forEach>
<c:forEach var="item" items="${graphVal.prod5}">
resultArr6.push("${item}");
</c:forEach>


barChartData.datasets[0].data = resultArr1;
barChartData.datasets[1].data = resultArr2;
barChartData.datasets[2].data = resultArr3;
barChartData.datasets[3].data = resultArr4;
barChartData.datasets[4].data = resultArr5;
barChartData.datasets[5].data = resultArr6;
	myChart.update();
}

function setData2() {
	barChartData.datasets = barChartData1.datasets;
	barChartData.labels = barChartData1.labels;
	myChart.update();
}

</script>