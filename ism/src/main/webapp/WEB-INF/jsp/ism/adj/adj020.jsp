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
                <li><a href="adj010.do">종합판매정산</a></li>
                <li><a href="adj020.do" class="on">상품별정산</a></li>
                <li><a href="adj030.do">상품수불부</a></li>
                <li><a href="adj040.do">수금관리대장</a></li>
                <li><a href="adj050.do">재고관리대장</a></li>
                <li><a href="adj060.do">운송비대장</a></li>
                <li><a href="adj070.do">판매관리비</a></li>
                <li><a href="adj080.do">정산도움말</a></li>
            </ul>
            <ul class="topBt">
                <li><a id="excelDownbtn" href="#">엑셀 다운로드</a></li>
                <li><a href="#" onclick="window.print()">프린트 출력</a></li>
            </ul>
            <div class="contents">
                <h2 class="pageTit">상품별정산</h2>
                <form id="form1" name="form1" method="post" action="/ism/adj/adj020.do" class="searchArea">
                    <input id="dtSearch_frCreateDt" type="text" name="dtSearch_frCreateDt" value="${adj010SearchVO.dtSearch_frCreateDt}" class="it monthPicker"/>
                    <select id="dtSearch_adj020_01" name="dtSearch_adj020_01" title="">
                        <option value=''>매입/매출</option>
                        <option value='1' <c:if test="${adj010SearchVO.dtSearch_adj020_01 eq '1'}"><c:out value="selected"/></c:if>
                        >매입가
                        </option>
                        <option value='2' <c:if test="${adj010SearchVO.dtSearch_adj020_01 eq '2'}"><c:out value="selected"/></c:if>
                        >매출액
                        </option>
                    </select>
                </form>
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="" summary="">
                        <caption></caption>
                        <thead>
                        <tr>
                            <th scope="col">매출처</th>
                            <th scope="col">쇼핑몰</th>
                            <th scope="col">합계</th>
                            <c:forEach var="item" items="${top10bycList}" begin="0" end="10"  step="1"   varStatus="status">
                                <th scope="col">${item.byc010name}</th>
                            </c:forEach>
                            <th scope="col">기타</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr>
                                <td>${result.cum010name}</td>
                                <td>${result.name}</td>
                                <td>${result.priceAll}</td>
                                <td>${result.price01}</td>
                                <td>${result.price02}</td>
                                <td>${result.price03}</td>
                                <td>${result.price04}</td>
                                <td>${result.price05}</td>
                                <td>${result.price06}</td>
                                <td>${result.price07}</td>
                                <td>${result.price08}</td>
                                <td>${result.price09}</td>
                                <td>${result.price10}</td>
                                <td>${result.price11}</td>
                            </tr>
                        </c:forEach>

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
    var Ca = /\+/g;

    //각종 초기화
    $(document).ready(function () {

    });

    $('#dtSearch_adj020_01').change(function () {
        $('#form1').submit();
    });

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