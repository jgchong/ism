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
                <li><a href="adj010.do">종합판매정산</a></li>
                <li><a href="adj020.do">상품별정산</a></li>
                <li><a href="adj030.do">상품수불부</a></li>
                <li><a href="adj040.do">수금관리대장</a></li>
                <li><a href="adj050.do" class="on">재고관리대장</a></li>
                <li><a href="adj060.do">운송비대장</a></li>
                <li><a href="adj070.do">판매관리비</a></li>
                <li><a href="adj080.do">정산도움말</a></li>
            </ul>
            <ul class="topBt">
                <li><a id="excelDownbtn" href="#">엑셀 다운로드</a></li>
                <li><a href="#" onclick="window.print()">프린트 출력</a></li>
            </ul>


            <!-- 하단 메인화면 -->
            <div class="contents">
                <h2 class="pageTit">재고관리</h2>

                <form id="form1" name="form1" method="post" action="/ism/skd/skd010.do" class="searchArea">
                    <!--
                    <a href="javascript:selectDel();" class="ml30">선택삭제</a>
                    -->
                    <input type="text" class="it ml30" title="" value="${skd010SearchVO.dfSearch_itemname}" name="dfSearch_itemname" placeHolder="상품명"/>
                    <button style="margin-left:-4px;">검색</button>
                </form>
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="" summary="">
                        <caption></caption>
                        <colgroup>
                            <col width="8%"/>
                            <col width="*"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="4%"/>
                            <col width="10%"/>
                            <col width="12%"/>
                            <col width="12%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col" rowspan="3">상품코드</th>
                            <th scope="col" rowspan="3">상품명</th>
                            <th scope="col" colspan="5">보관장소</th>
                            <th scope="col" colspan="1" rowspan="2">합계</th>
                            <th scope="col" colspan="1" rowspan="2">총재고금액(VAT불포함)</th>
                            <th scope="col" colspan="1" rowspan="2">총재고금액(VAT포함)</th>
                        </tr>
                        <tr>
                            <c:forEach var="item" items="${whsListForTop}" begin="0" end="3" step="1" varStatus="status">
                                <th scope="col">${item.whsname} 합계</th>
                            </c:forEach>
                            <th scope="col">기타</th>
                        </tr>
                        <tr>
                            <c:forEach var="item" items="${whsListForTop}" begin="0" end="3" step="1" varStatus="status">
                                <th scope="col">${item.cmm020id}</th>
                            </c:forEach>
                            <c:forEach var="item" items="${whsListForTop}" begin="4" end="5" step="1" varStatus="status">
                                <th scope="col">${item.cmm020id}</th>
                            </c:forEach>
                            <c:forEach var="item" items="${whsListForTop}" begin="6" end="7" step="1" varStatus="status">
                                <th scope="col">${item.whsname}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr id="${result.itemcode}" value="off"
                                <c:if test="${result.resultType eq 'C'}">class="<c:out value="${result.parentItemcode}"/>" style="display:none"
                            </c:if>>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.resultType eq 'C'}">
                                        </c:when>
                                        <c:when test="${result.resultType eq 'P'}">
                                            ${result.itemcode}
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.resultType eq 'C'}">
                                            <a href="javascript:" onclick="openSingleItemDetail3('${result.skd010id}')">${result.createdate}</a>
                                        </c:when>
                                        <c:when test="${result.resultType eq 'P'}">
                                            <a href="javascript:showChildItem('${result.itemcode}');">${result.itemname}</a>
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td onmouseover="showlayer('layer_hidden1', '${result.whs1itemname}');" onmouseout="hidelayer('layer_hidden1');">${result.whs1itemea}</td>
                                <td onmouseover="showlayer('layer_hidden1', '${result.whs2itemname}');" onmouseout="hidelayer('layer_hidden1');">${result.whs2itemea}</td>
                                <td onmouseover="showlayer('layer_hidden1', '${result.whs3itemname}');" onmouseout="hidelayer('layer_hidden1');">${result.whs3itemea}</td>
                                <td onmouseover="showlayer('layer_hidden1', '${result.whs4itemname}');" onmouseout="hidelayer('layer_hidden1');">${result.whs4itemea}</td>
                                <td onmouseover="showlayer('layer_hidden1', '${result.whsNamuge}');" onmouseout="hidelayer('layer_hidden1');">
                                    <c:if test="${result.whsNamuge ne ''}">
                                        ...
                                    </c:if>
                                    <c:if test="${result.whsNamuge eq ''}">
                                        -
                                    </c:if>
                                </td>
                                <td>${result.itemea}</td>
                                <td <c:if test="${result.resultType eq 'P'}">class="numberWithCommasHtml"</c:if>>${result.itemAllprice}</td>
                                <td <c:if test="${result.resultType eq 'P'}">class="numberWithCommasHtml"</c:if>>${result.itemAllbuyprice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="paging">
                    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnLinkPage"/>
                </div>
            </div>
        </div>
    </div> <!-- container -->
</div> <!-- wrap -->


<div id="layer_hidden1">게시물 본문 미리 보기</div>
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

    function showlayer(id, namuge) {
        if (namuge != '') {
            document.getElementById("layer_hidden1").innerHTML = namuge;
        } else {
            document.getElementById("layer_hidden1").innerHTML = '-';
        }
        layer_hidden1.style.visibility = "visible";

    }

    function hidelayer(id) {
        layer_hidden1.style.visibility = "hidden";
    }

    function movetip(event) {
        layer_hidden1.style.marginTop = event.clientY + document.body.scrollTop + 10 + "px";
        layer_hidden1.style.marginLeft = event.clientX + document.body.scrollLeft + 10 + "px";
    }

    document.onmousemove = movetip;

    $("#excelDownbtn").click(function () {
        document.form1.action = "<c:url value='/ism/skd/skd010ExcelDownload.do'/>";
        document.form1.submit();
        document.form1.action = "<c:url value='/ism/adj/adj050.do'/>";
    });

</script>
<script type="text/javascript">
    $(function () {
        $(".datepicker").datepicker({
            dateFormat: "yy-mm-dd",
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            changeMonth: true,
            changeYear: true,
            yearRange: "c-70:c+70",
            showMonthAfterYear: true
        });
    });
</script>