<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title> KTI NMS </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="/css/custom/base.css" type="text/css" rel="stylesheet"/>
    <link href="/css/custom/layout.css" type="text/css" rel="stylesheet"/>
    <link href="/css/custom/common.css" type="text/css" rel="stylesheet"/>
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
            text-align: center;
        }

        .inmemotxt {
            float: left;
            margin-left: 16px;
            border: 1px solid #ccc;
            width: 72%;
            border-radius: 8px;
            min-height: 45px;
        }

        .memotime {
            float: right;
        }

        .memoTxt li {
            height: 75px;
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

        #layer_hidden1 {
            position: absolute;
            padding: 5px;
            filter: alpha(opacity=50);
            width: 250px;
            height: auto;
            background-color: white;
            border: 2px #000000 dotted;
            visibility: hidden;
        }

        .colR {
            color: red
        }

        .colB {
            color: blue
        }

        .listTb .bg_col th,
        .listTb .bg_col td {
            background: #eff9fe
        }
    </style>
</head>
<body>
<!-- 전체 레이어 시작 -->
<div class="wrap">
    <c:import url="/sym/mms/EgovMainMenuHead.do"/>
    <div class="container">
        <!-- 좌측메뉴 시작 -->
        <div class="lnb">
            <c:import url="/sym/mms/EgovMainMenuLeft.do"/>
        </div>
        <!-- //좌측메뉴 끝 -->
        <!-- 이부부까지가 기본 -->
        <div class="contentsWrap">
            <ul class="contentsMenu">
                <li><a href="adj010.do" class="on">종합판매정산</a></li>
                <li><a href="adj020.do">상품별정산</a></li>
                <li><a href="adj030.do">상품수불부</a></li>
                <li><a href="adj040.do">수금관리대장</a></li>
                <li><a href="adj050.do">재고관리대장</a></li>
                <li><a href="adj060.do">운송비대장</a></li>
                <li><a href="adj070.do">판매관리비</a></li>
                <li><a href="adj080.do">정산도움말</a></li>
            </ul>
            <ul class="topBt leng3">
                <li><a id="excelDownbtn" href="#">엑셀 다운로드</a></li>
                <li><a href="#" onclick="window.print()">프린트 출력</a></li>
                <li><a href="javascript:openPopAccount();">정산마감</a></li>
            </ul>
            <div class="contents">
                <h2 class="pageTit">종합판매정산</h2>
                <%--
                <form id="form1" name="form1" method="post" action="/ism/adj/adj010.do" class="searchArea">
                    <input id="dtSearch_frCreateDt" type="text" name="dtSearch_frCreateDt" value="${adj010SearchVO.dtSearch_frCreateDt}" class="it monthPicker"/>
                </form>
                --%>
                <form id="form1" name="form1" method="post" action="/ism/adj/adj010.do" class="searchArea" style="text-align:left; margin-top:-20px;">
                    <input id="dtSearch_frCreateDt" type="text" name="dtSearch_frCreateDt" value="${adj010SearchVO.dtSearch_frCreateDt}" class="it monthPicker"/>
                </form>
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="dataTb" summary="">
                        <thead>
                            <tr>
                                <th scope="col">[단위: 원]</th>
                                <th scope="col"></th>
                                <th scope="col" style="color:#000;">${yyyy00}년도</th>
                                <th scope="col" style="color:#000;">${yyyy01}년도</th>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <th scope="col">${yyyy01}년 ${status.count}월</th>
                                </c:forEach>
                            </tr>
                            <tr>
                                <th scope="col">구분</th>
                                <th scope="col">분류</th>
                                <th scope="col" style="color:#000;">누계<br/>(VAT제외)</th>
                                <th scope="col" style="color:#000;">누계<br/>(VAT제외)</th>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <th scope="col">월계<br/>(VAT제외)</th>
                                </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="col1">
                                <th scope="row" rowspan="13">Ⅰ.매출액</th>
                                <td>매출 총액</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.priceAll}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.priceAll}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].priceAll}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>1.상품 매출 (${top10bycList[0].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price01}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price01}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price01}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>2.상품 매출 (${top10bycList[1].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price02}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price02}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price02}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>3.상품 매출 (${top10bycList[2].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price03}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price03}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price03}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>4.상품 매출 (${top10bycList[3].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price04}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price04}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price04}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>5.상품 매출 (${top10bycList[4].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price05}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price05}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price05}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>6.상품 매출 (${top10bycList[5].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price06}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price06}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price06}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>7.상품 매출 (${top10bycList[6].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price07}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price07}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price07}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>8.상품 매출 (${top10bycList[7].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price08}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price08}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price08}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>9.상품 매출 (${top10bycList[8].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price09}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price09}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price09}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>10.상품 매출 (${top10bycList[9].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price10}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price10}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price10}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>11.기타 매출</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.price11}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.price11}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].price11}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col2">
                                <td>고객사 차월 이월 매출(-)</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM0.namuge}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultCUM1.namuge}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultCUMList[status.index].namuge}</td>
                                </c:forEach>
                            </tr>

                            <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

                            <tr class="col1">
                                <th scope="row" rowspan="13">II. 매입액</th>
                                <td>매입 원가</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.priceAll}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.priceAll}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].priceAll}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>1.상품 원가 (${top10bycList[0].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price01}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price01}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price01}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>2.상품 원가 (${top10bycList[1].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price02}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price02}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price02}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>3.상품 원가 (${top10bycList[2].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price03}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price03}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price03}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>4.상품 원가 (${top10bycList[3].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price04}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price04}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price04}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>5.상품 원가 (${top10bycList[4].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price05}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price05}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price05}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>6.상품 원가 (${top10bycList[5].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price06}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price06}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price06}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>7.상품 원가 (${top10bycList[6].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price07}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price07}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price07}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>8.상품 원가 (${top10bycList[7].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price08}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price08}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price08}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>9.상품 원가 (${top10bycList[8].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price09}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price09}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price09}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>10.상품 원가 (${top10bycList[9].byc010name})</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price10}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price10}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price10}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>11.기타 원가</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.price11}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.price11}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].price11}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col2">
                                <td>고객사 차월 이월 매입(-)</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC0.namuge}</td>
                                <td class="redBold numberWithCommasHtml">${adj020ResultBYC1.namuge}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj020ResultBYCList[status.index].namuge}</td>
                                </c:forEach>
                            </tr>


                            <tr class="col3">
                                <th scope="row">Ⅲ.매출총이익 <br/>(차월이월 제외)</th>
                                <td>(Ⅰ-Ⅱ)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price3_1}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price3_1}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price3_1}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col3">
                                <th scope="row">Ⅲ-Ⅰ.매출총이익율</th>
                                <td>(Ⅲ/Ⅰ)</td>
                                <td class="redBold">${adj010Result00.price3_2}</td>
                                <td class="redBold">${adj010Result01.price3_2}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td>${adj010ResultList[status.index].price3_2}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col1">
                                <th scope="row" rowspan="7"> Ⅳ.판관비</th>
                                <td>판관비 총액 (1+2+...+7-8-9)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_sum}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_sum}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_sum}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>1.지급수수료 (영업사)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_2}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_2}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_2}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>2.지급수수료 (PG 외)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_3}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_3}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_3}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>3.광고선전비 (사은품,협찬)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_4}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_4}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_4}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>4.광고선전비 (샘플)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_5}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_5}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_5}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>5.운반보관비</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_6}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_6}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_6}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td>6.기타</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price4_7}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price4_7}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price4_7}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col3">
                                <th scope="row">Ⅴ.영업손익</th>
                                <td>(Ⅲ-Ⅳ)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price5_1}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price5_1}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price5_1}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col3">
                                <th scope="row">Ⅴ-Ⅰ.영업손익율</th>
                                <td>(Ⅴ/Ⅰ)</td>
                                <td class="redBold">${adj010Result00.price5_2}</td>
                                <td class="redBold">${adj010Result01.price5_2}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td>${adj010ResultList[status.index].price5_2}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <th scope="row">Ⅵ.영업외수익</th>
                                <td>(판매장려금 외)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price6}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price6}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price6}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <th scope="row">Ⅶ.영업외비용</th>
                                <td>(파손,망실,분실 외)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price7}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price7}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price7}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col3">
                                <th scope="row">Ⅷ.순손익</th>
                                <td>(Ⅴ+Ⅵ-Ⅶ)</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result00.price8}</td>
                                <td class="redBold numberWithCommasHtml">${adj010Result01.price8}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td class="numberWithCommasHtml">${adj010ResultList[status.index].price8}</td>
                                </c:forEach>
                            </tr>
                            <tr class="col3">
                                <th scope="row">Ⅷ-Ⅰ. 순손익율</th>
                                <td>(Ⅷ/Ⅰ)</td>
                                <td class="redBold">${adj010Result00.price9}</td>
                                <td class="redBold">${adj010Result01.price9}</td>
                                <c:forEach var="result" items="${yyyymmList}" varStatus="status">
                                    <td>${adj010ResultList[status.index].price9}</td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </div> <!-- container -->
</div> <!-- wrap -->
</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $('.dataTb tr').each(function(){
        if($(this).find('th').length > 0){
            $(this).children().eq(3).css({
                'background':'rgba(197,217,241,1)'
            })
            $(this).children().eq(2).css({
                'background':'rgba(200,200,200,1)'
            })
        }else{
            $(this).children().eq(2).css({
                'background':'rgba(197,217,241,1)'
            })
            $(this).children().eq(1).css({
                'background':'rgba(200,200,200,1)'
            })
        }
    })

    var Ca = /\+/g;

    //각종 초기화
    $(document).ready(function () {
        $('.numberWithCommasHtml').each(function (index, item) {
            var numberCommas = $(this).text()
            $(this).text(numberWithCommas(numberCommas))
        });
    });

    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    $("#excelDownbtn").click(function () {
        document.form1.action = "<c:url value='/ism/adj/adj010ExcelDownload.do'/>";
        document.form1.submit();
        document.form1.action = "<c:url value='/ism/adj/adj010.do'/>";
    });


    function openPopAccount() {
        window.open("/ism/main/mainAccountClose.do","account close","width=700,height=350,menubar=no,resizable=no,scrollbars=no,toolbar=no");
    }


</script>
<script type="text/javascript">
    $(function () {
        $(".monthPicker").datepicker({
            dateFormat: 'yy-mm',
            changeMonth: true,
            changeYear: true,
            showButtonPanel: true,
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            onClose: function (dateText, inst) {
                var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                $(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
                $('#form1').submit();
            }
        });

        $(".monthPicker").focus(function () {
            $(".ui-datepicker-calendar").hide();
            $("#ui-datepicker-div").position({
                my: "center top",
                at: "center bottom",
                of: $(this)
            });
        });
    });
</script>