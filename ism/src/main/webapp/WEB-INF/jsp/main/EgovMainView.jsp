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
		
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
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
				<li><a href="m1.html">주문관리</a></li>
				<li><a href="m3.html">발주관리</a></li>
				<li><a href="m4.html">재고관리</a></li>
			</ul>
			<div class="dashBoard">
				<div class="bigGraph">
					<h2 class="tit">단순통계(꺾은선 그래프) / 비교통계 (막대그래프)</h2>
					<div class="graph">
						<img src="<c:url value='/'/>images/custom/graph.png" alt="" style="width:100%; margin:95px 0;"/>
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