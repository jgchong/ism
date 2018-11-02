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
            <!-- 상단화면 -->
            <ul class="topBt">
                <li><a href="javascript:;" class="layerBt" onclick="openSingleItemDetail()" name="stock1">입고등록</a></li>
                <li><a href="javascript:;" class="layerBt" onclick="openSingleItemDetail2()" name="stock2">이관등록</a></li>
            </ul>


            <!-- 하단 메인화면 -->
            <div class="contents">
                <h2 class="pageTit">재고관리</h2>

                <form id="form1" name="form1" method="post" action="/ism/skd/skd010.do" class="searchArea">
                    <!--
                    <a href="javascript:selectDel();" class="ml30">선택삭제</a>
                    -->
                    <a id="excelDownbtn" href="javascript:;">엑셀저장</a>
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

<!-- 입고등록 -->
<div id="skd010save" class="layerCont stock1">
    <div class="inner">
        <p class="layerTit">입고등록</p>
        <div class="layerContents">
            <div class="layerTb">
                <table cellpadding="0" cellspacing="0" class="" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="15%"/>
                        <col width="35%"/>
                        <col width="15%"/>
                        <col width="35%"/>
                    </colgroup>
                    <tbody>
                    <tr id="skd010save_autosearch">
                        <td colspan="4">
                            <input type="text" id="skd010save_autosearch_input" class="it" placeholder=" 상품명 검색"/>
                            <input type="hidden" id="skd010save_autosearch_name" value=""/>
                            <input type="hidden" id="skd010save_autosearch_value" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">수량</th>
                        <td><input type="number" id="skd010save_itemea" type="text" class="it " title="" value="" name=""/></td>
                        <th scope="row">입고날짜</th>
                        <td><input id="skd010save_createdate" type="text" class="it datepicker" title="" value="" name="" placeHolder="입고날짜"/></td>
                    </tr>
                    <tr>
                        <th scope="row">유통기한설정</th>
                        <td><input id="skd010save_expirationdate" type="text" class="it " title="" value="" name=""/></td>
                        <th scope="row">물류비</th>
                        <td><input type="number" id="skd010save_itemdlprice" type="text" class="it " title="" value="" name=""/></td>
                    </tr>
                    <tr>
                        <th scope="row">창고설정</th>
                        <td>
                            <select id="skd010save_whs010id">
                                <option value="">창고 선택</option>
                                <c:forEach var="item" items="${whsList}" varStatus="status">
                                    <option value="${item.whs010id}">${item.whsname}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th scope="row"></th>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <p class="layerFootBt">
            <a href="javascript:saveDetailData();" class="layerBt_v2 confirm" name="claim">저장</a>
            <a href="javascript:;" class="layerClose cancel">닫기</a>
        </p>
        <a href="javascript:;" class="layerClose layerTopClose"><img src="img/closePop.png" alt=""/></a>
    </div>
</div>


<!-- 이관등록 -->
<div id="skd020save" class="layerCont stock2">
    <div class="inner">
        <p class="layerTit">이관등록</p>
        <div class="layerContents">
            <div class="layerTb scrollTb mt10">
                <table cellpadding="0" cellspacing="0" class="" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="20%"/>
                        <col width="*"/>
                        <col width="15%"/>
                        <col width="15%"/>
                        <col width="8%"/>
                    </colgroup>
                    <tbody>
                    <tr id="skd020save_autosearch">
                        <td>
                            <select id="skd020save_autosearch_whs010id">
                                <option value="">창고 선택</option>
                                <c:forEach var="item" items="${whsList}" varStatus="status">
                                    <option value="${item.whs010id}">${item.whsname}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" id="skd020save_autosearch_input" class="it" placeholder=" 상품명 검색"/>
                            <input type="hidden" id="skd020save_autosearch_name"/>
                            <input type="hidden" id="skd020save_autosearch_value"/>
                        </td>
                        <td>
                            <input id="skd020save_autosearch_itemea" type="text" class="it c" title="" value="" name="" placeholder="재고 수량" readonly/>
                        </td>

                        <td>
                            <input id="skd020save_autosearch_itemea_update" type="number" class="it c" title="" value="" name="" placeholder="이관 수량"/>
                        </td>
                        <td style="text-align:center;">
                            <a href="javascript:addSkd020save_autosearch();" style="font-size:12px;">추가</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="layerTb mt10">
                <table cellpadding="0" cellspacing="0" class="" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="15%"/>
                        <col width="35%"/>
                        <col width="15%"/>
                        <col width="35%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <th scope="row">이관될 창고</th>
                        <td>
                            <select id="skd020save_whs010id">
                                <option value="">창고 선택</option>
                                <c:forEach var="item" items="${whsList}" varStatus="status">
                                    <option value="${item.whs010id}">${item.whsname}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th scope="row">이관될 날짜</th>
                        <td><input id="skd020save_createdate" type="text" class="it datepicker" title="" value="" name="" placeHolder="이관날짜"/></td>
                    </tr>
                    <tr>
                        <th scope="row">물류비</th>
                        <td><input type="number" id="skd020save_itemdlprice" type="text" class="it " title="" value="" name=""/></td>
                        <th scope="row"></th>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <p class="layerFootBt">
            <a href="javascript:saveDetailData2();" class="layerBt_v2 confirm" name="claim">저장</a>
            <a href="javascript:;" class="layerClose cancel">닫기</a>
        </p>
        <a href="javascript:;" class="layerClose layerTopClose"><img src="img/closePop.png" alt=""/></a>
    </div>
</div>


<!-- 재고 상세 -->
<div id="skd010Detail" class="layerCont stock1">
    <div class="inner">
        <p class="layerTit">재고상세</p>
        <div class="layerContents">
            <div class="layerTb">
                <table cellpadding="0" cellspacing="0" class="" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="15%"/>
                        <col width="35%"/>
                        <col width="15%"/>
                        <col width="35%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <input type="text" id="skd010Detail_itemname" class="it" placeholder="상품명 검색" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">수량</th>
                        <td><input type="text" id="skd010Detail_itemea" type="text" class="it " title="" value="" name="" readonly/></td>
                        <th scope="row">입고날짜</th>
                        <td><input id="skd010Detail_createdate" type="text" class="it" title="" value="" name="" placeHolder="입고날짜" readonly/></td>
                    </tr>
                    <tr id="skd010Detail_lasttr">
                        <th scope="row">유통기한설정</th>
                        <td><input id="skd010Detail_expirationdate" type="text" class="it " title="" value="" name="" readonly/></td>
                        <th scope="row">물류비</th>
                        <td><input type="text" id="skd010Detail_itemdlprice" type="text" class="it " title="" value="" name="" readonly/></td>
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="layerTb mt10">
                <table cellpadding="0" cellspacing="0" class="" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="100%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <th scope="row">메모내용</th>
                    </tr>
                    <tr>
                        <td>
                            <div class="memoTxt">
                                <ul id='memoul'></ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="memo">
                                <input type='text' class='it' title='' value='' id='detail_inputmemo' name='inputmemo'/>
                                <a onclick='inputmemodata()'>입력</a>
                            </p>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
        <p class="layerFootBt">
            <a href="javascript:;" class="layerClose cancel">닫기</a>
        </p>
        <a href="javascript:;" class="layerClose layerTopClose"><img src="img/closePop.png" alt=""/></a>
    </div>
</div>


<div id="layer_hidden1">게시물 본문 미리 보기</div>
</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var Ca = /\+/g;

    //결합상품 결합상품들 열기
    function showChildItem(itemcode) {
        var elementId = '#' + itemcode;
        var elementClass = '.' + itemcode;

        if ($(elementId).val() == 'on') {
            $(elementId).val('off');
            $(elementClass).hide();
        } else {
            $(elementId).val('on');
            $(elementClass).show();
        }
    }
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    //각종 초기화
    $(document).ready(function () {

        $('.numberWithCommasHtml').each(function (index, item) {
            var numberCommas = $(this).text()
            $(this).text(numberWithCommas(numberCommas))
        });

        //디테일창 닫기 초기화
        $('.layerClose').on('click', function () {
            g_currentId = ''
            $('.layerCont').fadeOut();
            $('.fade').fadeOut(function () {
                $('.fade').remove();
            })
            return false;
        })

        //상품 자동완성검색창 01
        $.ajax({
            url: "/ism/prd/prd010selectGubun2.do",
            type: "post",
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                $('#skd010save_autosearch_input').autocomplete({
                    minLength: 0,
                    source: data,
                    focus: function (event, ui) {
                        return false;
                    },
                    select: function (event, ui) {
                        $('#skd010save_autosearch_input').val(ui.item.label);
                        $('#skd010save_autosearch_name').val(ui.item.label);
                        $('#skd010save_autosearch_value').val(ui.item.itemcode);
                        return false;
                    },
                    open: function () {
                        $(this).autocomplete('widget').css('z-index', 300).css('height', '200px').css('overflow-y', 'scroll').css('overflow-x', 'hidden');
                        return false;
                    }
                }).autocomplete("instance")._renderItem = function (ul, item) {
                    return $("<li>")
                        .append("<div>" + item.label + "</div>")
                        .appendTo(ul);
                };
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
                alert("Error : " + msg);
            }
        });
    });

    $("#excelDownbtn").click(function () {
        document.form1.action = "<c:url value='/ism/skd/skd010ExcelDownload.do'/>";
        document.form1.submit();
        document.form1.action = "<c:url value='/ism/skd/skd010.do'/>";
    });

    /////////////////////////////////////////////////////////////////////////// 메인화면 ///////////////////////////////////////////////////////////////////////


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

    //체크박스 전체 클릭하기.
    var chkallval = 0;

    function chkall() {
        if (chkallval == 0) {
            chkallval = 1;
            $("input:checkbox[id='chk_info']").prop('checked', true);
        } else {
            chkallval = 0;
            $("input:checkbox[id='chk_info']").prop('checked', false);
        }
    }


    //체크박스된 주문 목록 삭제
    function selectDel() {
        if (confirm("선택 주문을 삭제하시겠습니까?")) {
            var orderitemids = "";
            $('.chk_info').each(function () {
                if ($(this).is(":checked")) {
                    orderitemids += ($(this).attr("dataid") + ",");
                }
            });
            if (orderitemids == "") {
                alert("삭제 할 주문을 선택해주시기 바랍니다.");
                return;
            } else {
                orderitemids = orderitemids.substring(0, orderitemids.length - 1);
            }
            $.ajax({
                url: "/ism/skd/skd010SelectDel.do",
                type: "post",
                data: {"skd010ids": orderitemids},
                success: function (data) {
                    if (data == "SUCCESS") {
                        alert("삭제 되었습니다.");
                        $('#form1').submit();
                    } else {
                        alert("삭제 중 오류가 발생했습니다.");
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
                    alert("Error : " + msg);
                }
            });
        }
    }


    /////////////////////////////////////////////////////////////////////////// 입고관리skd010save ///////////////////////////////////////////////////////////////////////

    var firstCheck01 = 0;

    function openSingleItemDetail() {
        //처음 여는 경우
        if (firstCheck01 == 0) {
            firstCheck01 = 1;
            initSkd010save()
        }
        //그대로 열기
        //open
        $('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
        $('.fade').fadeIn();
        $('#skd010save').css({
            'margin': '-' + ($('#skd010save').height() / 2) + 'px 0 0 -' + ($('#skd010save').width() / 2) + 'px'
        })
        $('#skd010save').fadeIn();
        return false;
    }

    function initSkd010save() {
        $('#skd010save_autosearch_input').val('');
        $('#skd010save_autosearch_name').val('');

        //필수값
        $('#skd010save_autosearch_value').val('');
        $('#skd010save_itemea').val('');
        $('#skd010save_createdate').val('');
        $('#skd010save_whs010id').val('');

        //서브값
        $('#skd010save_itemdlprice').val('');
        $('#skd010save_expirationdate').val('');

    }

    function saveDetailData() {
        var skd010save_autosearch_value = $('#skd010save_autosearch_value').val();
        var skd010save_whs010id = $('#skd010save_whs010id').val();
        var skd010save_itemea = $('#skd010save_itemea').val()
        var skd010save_createdate = $('#skd010save_createdate').val();

        if (skd010save_autosearch_value == '') {
            alert("상품명을 검색해주세요.")
            return;
        }

        if (skd010save_whs010id == '') {
            alert("창고를 입력해주세요.")
            return;
        }

        if (skd010save_itemea == '') {
            alert("수량을 입력해주세요.")
            return;
        }

        if (skd010save_createdate == '') {
            alert("입고날짜를 등록해주세요.")
            return;
        }

        $.ajax({
            url: "/ism/skd/skd010Save.do",
            type: "post",
            data: {
                "skd010save_itemcode": skd010save_autosearch_value,
                "skd010save_itemea": skd010save_itemea,
                "skd010save_createdate": skd010save_createdate,
                "skd010save_expirationdate": $('#skd010save_expirationdate').val(),
                "skd010save_itemdlprice": $('#skd010save_itemdlprice').val(),
                "skd010save_whs010id": skd010save_whs010id
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("저장되었습니다.");
                firstCheck01 = 0;
                $('#form1').submit();
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
                    msg = jqXHR.responseText;
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.<br>' + jqXHR.responseText;
                }
                alert("Error : " + msg);
            }
        });
    }


    /////////////////////////////////////////////////////////////////////////// 이관관리 ///////////////////////////////////////////////////////////////////////


    function openSingleItemDetail2() {
        initSkd020save()
        //open
        $('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
        $('.fade').fadeIn();
        $('#skd020save').css({
            'margin': '-' + ($('#skd020save').height() / 2) + 'px 0 0 -' + ($('#skd020save').width() / 2) + 'px'
        })
        $('#skd020save').fadeIn();
        return false;
    }


    function initSkd020save() {
        $('#skd020save_whs010id').prop('disabled', false);
        $('#skd020save_autosearch_input').val('');
        $('#skd020save_autosearch_name').val('');
        $('#skd020save_autosearch_itemea').val('');
        $('#skd020save_autosearch_whs010id').val('');
        $('#skd020save_autosearch_itemea_update').val('');
        $('#skd020save_autosearch_value').val('');

        $('.skd020save_autosearch_regist').remove()


        //필수값
        $('#skd020save_createdate').val('');
        $('#skd020save_whs010id').val('');

        //서브값
        $('#skd020save_itemdlprice').val('');
    }

    function saveDetailData2() {
        var skd020save_whs010id = $('#skd020save_whs010id').val();
        var skd020save_createdate = $('#skd020save_createdate').val();
        var skd020save_skd010id = '';
        var skd020save_itemea = '';
        var skd020save_itemea_update = '';
        var skd020save_whs010id_update = '';

        $('.skd020save_autosearch_regist').each(function (index, item) {
            if (index == 0) {
                skd020save_skd010id = $(this).attr("dataid01");
                skd020save_whs010id_update = $(this).attr("dataid02");
                skd020save_itemea_update = $(this).attr("dataid03");
                skd020save_itemea = $(this).attr("dataid04");
            } else {
                skd020save_skd010id = skd020save_skd010id + ',' + $(this).attr("dataid01");
                skd020save_whs010id_update = skd020save_whs010id_update + ',' + $(this).attr("dataid02");
                skd020save_itemea_update = skd020save_itemea_update + ',' + $(this).attr("dataid03");
                skd020save_itemea = skd020save_itemea + ',' + $(this).attr("dataid04");
            }
        });


        if (skd020save_skd010id == '' || skd020save_itemea == '' || skd020save_itemea_update == '' || skd020save_whs010id_update == '') {
            alert("이관할 창고 서식을 제대로 입력해주세요.")
            return;
        }

        if (skd020save_whs010id == '') {
            alert("창고를 입력해주세요.")
            return;
        }

        if (skd010save_itemea == '') {
            alert("수량을 입력해주세요.")
            return;
        }

        if (skd020save_createdate == '') {
            alert("입고날짜를 등록해주세요.")
            return;
        }

        $.ajax({
            url: "/ism/skd/skd020Save.do",
            type: "post",
            data: {
                "skd020save_whs010id": skd020save_whs010id,
                "skd020save_createdate": skd020save_createdate,
                "skd020save_itemdlprice": $('#skd020save_itemdlprice').val(),
                "skd020save_skd010ids": skd020save_skd010id,
                "skd020save_itemeas": skd020save_itemea,
                "skd020save_itemea_updates": skd020save_itemea_update,
                "skd020save_whs010id_updates": skd020save_whs010id_update
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("저장되었습니다.");
                firstCheck01 = 0;
                $('#form1').submit();
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
                    msg = jqXHR.responseText;
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.<br>' + jqXHR.responseText;
                }
                alert("Error : " + msg);
            }
        });
    }


    // $('#skd020save_autosearch_input').prop('disabled', true);
    $('#skd020save_autosearch_whs010id').change(function () { //이관될 창고 설정 변경 (//자기자신은 클릭하면 안되도록 변경하기)
        var a = $(this).val();
        if (a != '') {
            // $('#skd020save_autosearch_input').prop('disabled', false);
            // $('#skd020save_whs010id').prop('disabled', true);
            $.ajax({
                url: "/ism/skd/skd020seletWhsitem.do",
                type: "post",
                data: {"whs010id": a},
                success: function (data) {
                    $('#skd020save_autosearch_input').autocomplete({
                        minLength: 0,
                        source: data,
                        focus: function (event, ui) {
                            return false;
                        },
                        select: function (event, ui) {
                            $('#skd020save_autosearch_input').val(ui.item.label);
                            $('#skd020save_autosearch_name').val(ui.item.label);
                            $('#skd020save_autosearch_value').val(ui.item.skd010id);
                            $('#skd020save_autosearch_itemea').val(ui.item.itemea);
                            return false;
                        },
                        open: function () {
                            $(this).autocomplete('widget').css('z-index', 300).css('height', '200px').css('overflow-y', 'scroll').css('overflow-x', 'hidden');
                            return false;
                        }
                    }).autocomplete("instance")._renderItem = function (ul, item) {
                        return $("<li>")
                            .append("<div>" + item.label + "</div>")
                            .appendTo(ul);
                    };
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
                    // $('#skd020save_whs010id').prop('disabled', false);
                    alert("Error : " + msg);
                }
            });
        } else {
            // $('#skd020save_whs010id').prop('disabled', false);
            // $('#skd020save_autosearch_input').prop('disabled', true);
        }
    });

    function addSkd020save_autosearch() {
        var searchValue = $('#skd020save_autosearch_value').val();
        var searchLavel = $('#skd020save_autosearch_input').val();
        var searchName = $('#skd020save_autosearch_name').val();
        var whs010id = $('#skd020save_autosearch_whs010id').val();
        var itemea_update = $('#skd020save_autosearch_itemea_update').val();
        var itemea = $('#skd020save_autosearch_itemea').val();
        var whsname = $('#skd020save_autosearch_whs010id option:selected').text();
        var a = 0;
        if (searchValue != null && searchValue != '' && searchLavel != null && searchLavel != '' && searchName != null && searchName != '' && whs010id != '' && itemea_update != '') {
            $('.skd020save_autosearch_regist').each(function () {
                var temp1 = $(this).attr("dataid01");
                var temp2 = $(this).attr("dataid02");
                if (searchValue == temp1 && whs010id == temp2) {
                    a = 1;
                }
            });
            if (a == 1) {
                alert("이미 추가하였습니다.");
            } else {
                $('#skd020save_autosearch_value').val('');
                $('#skd020save_autosearch_input').val('');
                $('#skd020save_autosearch_name').val('');
                $('#skd020save_autosearch_itemea').val('');
                $('#skd020save_autosearch_whs010id').val('');
                $('#skd020save_autosearch_itemea_update').val('');
                createSearchResult(searchValue, searchName, itemea_update, whs010id, whsname, itemea);
            }
        } else {
            alert("서식을 제대로 입력해주세요.")
        }

    }

    function createSearchResult(searchValue, searchName, itemea_update, whs010id, whsname, itemea) {
        $('#skd020save_autosearch').after("" +
            "<tr class='skd020save_autosearch_regist' dataid01='" + searchValue + "' dataid02='" + whs010id + "' dataid03='" + itemea_update + "' dataid04='" + itemea + "'>" +
            "<td colspan='4'>" + searchName + " ( 재고 : " + itemea + " | " + whsname + "에서 " + itemea_update + "개 이동" + ")</td>" +
            "<td style=\"text-align: center\"><button class='delbtn' onclick='delRow(this)'>삭제</button></td>" +
            "</tr>");
    }

    function delRow(obj) {
        $(obj).parent().parent().remove();
    }


    ////////////////////////////////////////////////////////// 상세화면

    var g_currentId;

    function openSingleItemDetail3(currentId) {
        setMemo(currentId, 'SK', $('#memoul'));
        initSettingDetail(currentId);
        g_currentId = currentId;

        //open
        $('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
        $('.fade').fadeIn();
        $('#skd010Detail').css({
            'margin': '-' + ($('#skd010Detail').height() / 2) + 'px 0 0 -' + ($('#skd010Detail').width() / 2) + 'px'
        })
        $('#skd010Detail').fadeIn();
        return false;
    }


    function initSettingDetail(currentId) {
        $('.skd020Detail').remove()
        $.ajax({
            url: "/ism/skd/skd010Detail.do",
            type: "post",
            data: {"currentId": currentId},
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                $('#skd010Detail_itemname').val(data.itemname)
                $('#skd010Detail_itemea').val(data.itemea)
                $('#skd010Detail_createdate').val(data.createdate)
                $('#skd010Detail_expirationdate').val(data.expirationdate)
                $('#skd010Detail_itemdlprice').val(data.itemdlprice)
                $.each(data.skd020, function (index, item) {
                    $('#skd010Detail_lasttr').after("<tr class=\"skd020Detail\">\n" +
                        "                        <td colspan=\"1\">\n" +
                        "                            <input value='" + item.whsname + "' type=\"text\" class=\"it \" placeholder=\"창고\" readonly/>\n" +
                        "                        </td>\n" +
                        "                        <td colspan=\"1\">\n" +
                        "                            <input value='" + item.itemea + "' type=\"text\" class=\"it\" placeholder=\"재고수량\" readonly/>\n" +
                        "                        </td>\n" +
                        "                        <td colspan=\"1\">\n" +
                        "                            <input value='" + item.createdate + "' type=\"text\" class=\"it\" placeholder=\"이관날짜\" readonly/>\n" +
                        "                        </td>\n" +
                        "                        <td colspan=\"1\">\n" +
                        "                            <input value='" + item.itemdlprice + "' type=\"text\" class=\"it\" placeholder=\"물류비\" readonly/>\n" +
                        "                        </td>\n" +
                        "                    </tr>")
                });
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
                alert("Error : " + msg);
            }
        });
    }

    //메모입력
    function inputmemodata() {
        if (g_currentId == '') {
            alert("운영상품 등록 후 메모 등록이 가능합니다.");
            return;
        }
        var inputmemo = $('#detail_inputmemo').val();
        savememodata(g_currentId, 'SK', inputmemo, $('#memoul'));
        $('#detail_inputmemo').val("");
        return false;
    }

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