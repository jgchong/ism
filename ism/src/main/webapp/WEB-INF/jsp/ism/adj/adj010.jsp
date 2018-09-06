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
        .colR{color:red}
        .colB{color:blue}
        .listTb .bg_col th,
        .listTb .bg_col td{background:#eff9fe}
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
            <ul class="topBt">
                <li><a href="#">엑셀 다운로드</a></li>
                <li><a href="#">프린트 출력</a></li>
            </ul>
            <div class="contents">
                <h2 class="pageTit">종합판매정산</h2>
                <form method="post" action="" class="searchArea">
                    <input type="text" id="month" name="month" class="it monthPicker" />
                </form>
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="dataTb" summary="" >
                        <thead>
                        <tr>
                            <th scope="col">[단위: 원]</th>
                            <th scope="col"></th>
                            <th scope="col">2017년도</th>
                            <th scope="col">2018년도</th>
                            <th scope="col">2018년 1월</th>
                            <th scope="col" colspan="2">2018년 2월</th>
                        </tr>
                        <tr>
                            <th scope="col">구분</th>
                            <th scope="col">분류</th>
                            <th scope="col">누계<br />(VAT제외)</th>
                            <th scope="col">누계<br />(VAT제외)</th>
                            <th scope="col">월계<br />(VAT제외)</th>
                            <th scope="col">월계<br />(VAT포함)</th>
                            <th scope="col">월계<br />(VAT제외)</th>
                        </tr>
                        </thead>
                        <tr class="col1">
                            <th scope="row" rowspan="15">Ⅰ.매출액</th>
                            <td>매출 총액 (1+2+…+10)-10</td>
                            <td class="redBold">17,167,724,693</td>
                            <td class="redBold">1,998,543,920</td>
                            <td> 1,998,543,920 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>1.상품 매출 (정관장)</td>
                            <td>1,057,082,679</td>
                            <td>124,952,531</td>
                            <td> 124,952,531 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>2.상품 매출 (정관장 음료)</td>
                            <td>394,159,562</td>
                            <td>70,248,434</td>
                            <td> 70,248,434 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>3.상품 매출 (한삼인)</td>
                            <td>16,126,242</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>4.상품 매출 (동원F&amp;B)</td>
                            <td>129,361,933</td>
                            <td>11,640,142</td>
                            <td> 11,640,142 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>5.상품 매출 (혜인건강)</td>
                            <td>14,980,742,569</td>
                            <td>1,710,391,368</td>
                            <td> 1,710,391,368 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>6.상품 매출 [대아 (필립스)]</td>
                            <td>22,147,140</td>
                            <td>620,818</td>
                            <td> 620,818 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>7.상품 매출 (티에스엔씨)</td>
                            <td>1,687,000</td>
                            <td>41,636</td>
                            <td> 41,636 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>8.상품 매출 (에이치엔비)</td>
                            <td>237,227,328</td>
                            <td>54,653,397</td>
                            <td> 54,653,397 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>9.상품 매출 (코윈스리테일)</td>
                            <td>48,138,493</td>
                            <td>3,621,655</td>
                            <td> 3,621,655 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>10.상품 매출 (네트론)</td>
                            <td>2,445,073</td>
                            <td>184,464</td>
                            <td> 184,464 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>11.상품 매출 (천호식품)</td>
                            <td>24,551,336</td>
                            <td>12,600,093</td>
                            <td> 12,600,093 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>12.상품 매출 (셰프라인)</td>
                            <td>42,549,999</td>
                            <td>2,639,035</td>
                            <td> 2,639,035 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>13.기타 매출</td>
                            <td>210,207,976</td>
                            <td>6,950,348</td>
                            <td> 6,950,348 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col2">
                            <td>고객사 차월 이월 매출(-)</td>
                            <td>590,458,715</td>
                            <td>1,998,543,920</td>
                            <td> 1,998,543,920 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col1">
                            <th scope="row" rowspan="15">Ⅱ.매출원가</th>
                            <td>원가 총액 (1+…+10)-10</td>
                            <td class="redBold">14,331,811,077</td>
                            <td class="redBold">1,965,455,926</td>
                            <td> 1,965,455,926 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>1.상품 원가 (정관장)</td>
                            <td>920,210,089</td>
                            <td>118,790,345</td>
                            <td> 118,790,345 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>2.상품 원가 (정관장 음료)</td>
                            <td>356,251,273</td>
                            <td>66,717,273</td>
                            <td> 66,717,273 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>3.상품 원가 (한삼인)</td>
                            <td>14,553,182</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>4.상품 원가 (동원F&amp;B)</td>
                            <td>110,807,060</td>
                            <td>11,931,665</td>
                            <td> 11,931,665 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>5.상품 원가 (혜인건강)</td>
                            <td>12,468,932,156</td>
                            <td>1,691,509,489</td>
                            <td> 1,691,509,489 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>6.상품 원가 [대아 (필립스)]</td>
                            <td>18,185,000</td>
                            <td>590,000</td>
                            <td> 590,000 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>7.상품 원가 (티에스엔씨)</td>
                            <td>2,042,109</td>
                            <td>34,091</td>
                            <td> 34,091 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>8.상품 원가 (에이치앤비)</td>
                            <td>184,033,469</td>
                            <td>56,514,453</td>
                            <td> 56,514,453 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>9.상품 원가 (코윈스리테일)</td>
                            <td>34,725,636</td>
                            <td>1,780,727</td>
                            <td> 1,780,727 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>10.상품 원가 (네트론)</td>
                            <td>1,679,182</td>
                            <td>156,136</td>
                            <td> 156,136 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>11.상품 원가 (천호식품)</td>
                            <td>22,099,190</td>
                            <td>11,286,545</td>
                            <td> 11,286,545 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>12.상품 원가 (셰프라인)</td>
                            <td>32,218,591</td>
                            <td>2,132,400</td>
                            <td> 2,132,400 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>13.기타 원가</td>
                            <td>164,842,322</td>
                            <td>4,012,802</td>
                            <td> 4,012,802 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col2">
                            <td>고객사 차월 이월 매입(-)</td>
                            <td>0</td>
                            <td>1,965,455,926</td>
                            <td> 1,965,455,926 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅲ.매출총이익 <br />(차월이월 제외)</th>
                            <td>(Ⅰ-Ⅱ)</td>
                            <td class="redBold">2,835,913,616</td>
                            <td class="redBold">33,087,994</td>
                            <td class="r">33,087,994</td>
                            <td>0</td>
                            <td class="r">0</td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅲ-Ⅰ.매출총이익율</th>
                            <td>(Ⅲ/Ⅰ)</td>
                            <td>16.5%</td>
                            <td>1.7%</td>
                            <td>1.7%</td>
                            <td>#DIV/0!</td>
                            <td>#DIV/0!</td>
                        </tr>
                        <tr class="col1">
                            <th scope="row" rowspan="8"> Ⅳ.판관비</th>
                            <td>판관비 총액 (1+2+...+7-8-9)</td>
                            <td>610,712,693</td>
                            <td>105,667,944</td>
                            <td class="r">105,667,944</td>
                            <td>0</td>
                            <td class="r">0</td>
                        </tr>
                        <tr>
                            <td>1.지급수수료 (고객사 선공제)</td>
                            <td>409,940</td>
                            <td>72,427</td>
                            <td> 72,427 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>2.지급수수료 (영업사)</td>
                            <td>2,290,888</td>
                            <td>1,057,891</td>
                            <td> 1,057,891 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>3.지급수수료 (PG 외)</td>
                            <td>195,480,700</td>
                            <td>15,122,235</td>
                            <td> 15,122,235 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>4.광고선전비 (사은품,협찬)</td>
                            <td>327,273</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>5.광고선전비 (샘플)</td>
                            <td>1,936,509</td>
                            <td>631,545</td>
                            <td> 631,545 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>6.운반보관비</td>
                            <td>402,728,483</td>
                            <td>83,089,759</td>
                            <td> 83,089,759 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>7.기타</td>
                            <td>7,538,901</td>
                            <td>5,694,085</td>
                            <td> 5,694,085 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅴ.영업손익</th>
                            <td>(Ⅲ-Ⅳ)</td>
                            <td class="redBold">2,225,200,924</td>
                            <td class="redBold">-72,579,950</td>
                            <td class="r redBold">-72,579,950</td>
                            <td>0</td>
                            <td class="r">0</td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅴ-Ⅰ.영업손익율</th>
                            <td>(Ⅴ/Ⅰ)</td>
                            <td>13.0%</td>
                            <td>-3.6%</td>
                            <td>-3.6%</td>
                            <td>#DIV/0!</td>
                            <td>#DIV/0!</td>
                        </tr>
                        <tr>
                            <th scope="row">Ⅵ.영업외수익</th>
                            <td>(판매장려금 외)</td>
                            <td>5,228,826</td>
                            <td>668,190</td>
                            <td> 668,190 </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <th scope="row">Ⅶ.영업외비용</th>
                            <td>(파손,망실,분실 외)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <th scope="row" rowspan="6">웰스토어 내역</th>
                            <td>1. 웰스토어(포인트 지급액)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>2. 웰스토어(포인트 사용액)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col1">
                            <td>포인트 소계 (1-2)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>3. 웰스토어(적립금 지급액)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr>
                            <td>4. 웰스토어(적립금 지급액)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col1">
                            <td>적립금 소계 (3-4)</td>
                            <td>0</td>
                            <td>0</td>
                            <td> - </td>
                            <td>0</td>
                            <td> - </td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅷ.순손익</th>
                            <td>(Ⅴ+Ⅵ-Ⅶ)</td>
                            <td class="redBold">2,230,429,750</td>
                            <td class="redBold">-71,911,760</td>
                            <td class="r redBold">-71,911,760</td>
                            <td>0</td>
                            <td class="r">0</td>
                        </tr>
                        <tr class="col3">
                            <th scope="row">Ⅷ-Ⅰ. 순손익율</th>
                            <td>(Ⅷ/Ⅰ)</td>
                            <td>13.0%</td>
                            <td>-3.6%</td>
                            <td>-3.6%</td>
                            <td>#DIV/0!</td>
                            <td>#DIV/0!</td>
                        </tr>
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



</script>
<script type="text/javascript">
    $(function () {
        $(".monthPicker").datepicker({
            dateFormat: 'yymm',
            changeMonth: true,
            changeYear: true,
            showButtonPanel: true,
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            onClose: function(dateText, inst) {
                var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                $(this).val($.datepicker.formatDate('yymm', new Date(year, month, 1)));
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