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
        .paging a {
            margin: 0 3px 0 3px;
        }

        .paging a.on {
            background: #666;
            color: #fff;
        }

        .tbTab li {
            width: 11.1%;
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

        form.searchArea .it {
            text-align: center;
            float: left;
            margin-left: 5px;
        }

        form.searchArea .searchMore li select {
            width: 100%;
            box-sizing: border-box;
            text-align: center;
            font-size: 14px;
            height: 34px;
            color: #666;
        }

        li.delbtn {
            width: 50px;
        }
    </style>

</head>
<body>

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
            <div class="contents">
                <h2 class="pageTit">운영상품관리</h2>
                <form id="form1" name="form1" method="post" action="/ism/prd/prd010.do" class="searchArea">
                    <input type="text" class="it datepicker" title="" value="${prd010SearchVO.dtSearch_frCreateDt}" name="dtSearch_frCreateDt" placeHolder="시작등록일자"/>
                    <input type="text" class="it datepicker" title="" value="${prd010SearchVO.dtSearch_toCreateDt}" name="dtSearch_toCreateDt" placeHolder="종료등록일자"/>
                    <a href="javascript:selectDel();">선택삭제</a>
                    <select id="pageUnit" name="pageUnit" title="" class="ml30">
                        <option value="50" <c:if test="${prd010SearchVO.pageUnit eq '50'}"><c:out value="selected"/></c:if>>50개</option>
                        <option value="100" <c:if test="${prd010SearchVO.pageUnit eq '100'}"><c:out value="selected"/></c:if>>100개</option>
                        <option value="500" <c:if test="${prd010SearchVO.pageUnit eq '500'}"><c:out value="selected"/></c:if>>500개</option>
                    </select>
                    <a href="javascript:;">정렬</a>
                    <a href="javascript:;" class="moreSearchBt">상세검색</a>
                    <ul style="margin-top: 10px; margin-bottom:10px;">
                        <a href="javascript:;" class="layerBt" onclick="openSingleItemDetail()" name="product" style="margin-top: 30px;">운영상품 등록</a>
                        <a href="#" style="margin-top: 30px;">운영상품 일괄 업로드</a>
                        <a id="excelDownbtn" href="javascript:;" style="margin-top: 30px;">운영상품 엑셀 저장</a>
                    </ul>
                    <div class="searchMore">
                        <ul>
                            <li>
                                <select name="dfSearch_itemcat1" title="">
                                    <option value="">상품 카테고리</option>
                                    <c:forEach var="item" items="${ISM090}" varStatus="status">
                                        <option value="${item.code}"
                                                <c:if test="${prd010SearchVO.dfSearch_itemcat1 eq item.code}">selected</c:if> >${item.codeNm}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li>
                                <select name="dfSearch_itemcrosstype" title="">
                                    <option value=''>결합여부</option>
                                    <option value='S'
                                            <c:if test="${prd010SearchVO.dfSearch_itemcrosstype eq 'S'}">selected</c:if> >N
                                    </option>
                                    <option value='F'
                                            <c:if test="${prd010SearchVO.dfSearch_itemcrosstype eq 'F'}">selected</c:if> >Y
                                    </option>
                                </select>
                            </li>
                            <li>
                                <select name="dfSearch_itemgubun" title="">
                                    <option value="">상품 구분</option>
                                    <option value="1"
                                            <c:if test="${prd010SearchVO.dfSearch_itemcat1 eq '1'}">selected</c:if> >제조사출고상품
                                    </option>
                                    <option value="2"
                                            <c:if test="${prd010SearchVO.dfSearch_itemcat1 eq '2'}">selected</c:if> >재고관리상품
                                    </option>
                                    <option value="3"
                                            <c:if test="${prd010SearchVO.dfSearch_itemcat1 eq '3'}">selected</c:if> >사은품
                                    </option>
                                </select>
                            </li>
                            <li><input type="text" class="it" title="" value="${prd010SearchVO.dfSearch_bycname}" name="dfSearch_bycname" placeHolder="매입처"/></li>
                            <li><input type="text" class="it" title="" value="${prd010SearchVO.dfSearch_itemcode}" name="dfSearch_itemcode" placeHolder="상품코드"/></li>
                            <li><input type="text" class="it" title="" value="${prd010SearchVO.dfSearch_itemname}" name="dfSearch_itemname" placeHolder="상품명"/></li>
                            <li><input type="text" class="it" title="" value="${prd010SearchVO.dfSearch_itemopt}" name="dfSearch_itemopt" placeHolder="옵션"/></li>
                            <li><input type="text" class="it" title="" value="${prd010SearchVO.dfSearch_whsname}" name="dfSearch_whsname" placeHolder="우선창고명"/></li>
                        </ul>
                        <p><a href="javascript:$('#search_isdetail').val(1);$('#form1').submit();">검색</a></p>
                    </div>
                    <script type="text/javascript">
                        $('.moreSearchBt').on('click', function () {
                            $('.searchMore').slideToggle();
                            return false;
                        })
                    </script>
                    <input type="hidden" id="search_isdetail" name="search_isdetail" value="0"/><!-- 상세 검색으로 검색 했는지 여부 가지고가서 그럴경우 상세검색 토글료 open -->
                    <input type="hidden" id="dfChange_whs010id" name="dfChange_whs010id" value="${prd010SearchVO.dfChange_whs010id}"/><!-- 우선창고 변경시 입력된다. -->
                    <input type="hidden" id="dfChange_orderitemid" name="dfChange_orderitemid" value="${prd010SearchVO.dfChange_whs010id}"/><!-- 우선창고 변경시 입력된다. -->
                </form>
                <!-- 상단 버튼과 검색 end-->
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="" summary="">
                        <caption></caption>
                        <colgroup>
                            <col width="4%"/>
                            <col width="4%"/>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="8%"/>
                            <col width="*"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="5%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col"><a href="javascript:chkall();">V</a></th>
                            <th scope="col">NO.</th>
                            <th scope="col">결합여부</th>
                            <th scope="col">매입처</th>
                            <th scope="col">상품코드</th>
                            <th scope="col">상품명</th>
                            <th scope="col">옵션</th>
                            <th scope="col">수량</th>
                            <th scope="col">매입단가</th>
                            <th scope="col">매입배송비</th>
                            <th scope="col">구분</th>
                            <th scope="col">우선창고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr id="${result.itemcode}" value="off"
                                <c:if test="${result.itemcrosstype eq 'C'}">class="<c:out value="${result.crossitemcode}"/>" style="display:none"</c:if>>
                                <td><input type="checkbox" id="chk_info" name="chk_info" class="chk_info" dataid="${result.orderitemid}"/></td>
                                <td>
                                    <c:out value="${result.listNo}"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.itemcrosstype eq 'S'}">
                                            N
                                        </c:when>
                                        <c:when test="${result.itemcrosstype eq 'F'}">
                                            <a href="javascript:showCrossItem('${result.itemcode}');">Y</a>
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${result.bycname}"/></td>
                                <td><c:out value="${result.itemcode}"/></td>
                                <td>
                                    <a href="javascript:" onclick="openSingleItemDetail('${result.itemcode}');"><c:out value="${result.itemname}"/></a>
                                </td>
                                <td><c:out value="${result.itemopt}"/></td>
                                <td><c:out value="${result.itemea}"/></td>
                                <td><c:out value="${result.itembuyprice}"/></td>
                                <td>
                                    <c:out value="${result.itembuydlvprice}"/></td>
                                <td>
                                    <c:if test="${result.itemgubun eq ''}">구분없음</c:if>
                                    <c:if test="${result.itemgubun eq '1'}">제조사출고상품</c:if>
                                    <c:if test="${result.itemgubun eq '2'}">재고관리상품</c:if>
                                    <c:if test="${result.itemgubun eq '3'}">사은품</c:if>
                                </td>
                                <td>
                                    <select class="dfChange_whs010id_select" dataid="${result.orderitemid}" <c:if test="${result.itemgubun eq '1'}">disabled</c:if>>
                                        <c:if test="${result.itemgubun eq '1'}">
                                            <option value="">우선창고없음</option>
                                        </c:if>
                                        <c:forEach var="item" items="${whsList}" varStatus="status">
                                            <option value="${item.whs010id}"
                                                    <c:if test="${result.pristock eq item.whs010id}">selected</c:if> >${item.whsname}</option>
                                        </c:forEach>
                                    </select>
                                </td>
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
    </div>
</div>

<!-- 주문현황관리 -->
<div id="layerCont" class="layerCont product">
    <div class="inner">
        <p class="layerTit">운영상품 등록 및 수정</p>
        <div class="layerContents">
            <div class="layerTb mb10">
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
                        <th scope="row">상품카테고리</th>
                        <td colspan="3">
                            <select id="detail_category">
                                <option value="">상품 카테고리</option>
                                <c:forEach var="item" items="${ISM090}" varStatus="status">
                                    <option value="${item.code}">${item.codeNm}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">구분선택</th>
                        <td>
                            <input type="radio" name="detail_sel1" id="detail_sel1_1" onclick="radiobox01Click('S')" value='S'/><label for="detail_sel1_1">단품</label>
                            <input type="radio" name="detail_sel1" id="detail_sel1_2" onclick="radiobox01Click('F')" value='F'/><label for="detail_sel1_2">결합</label>
                        </td>
                        <th scope="row">매입처</th>
                        <td>
                            <select id="detail_byc">
                                <option value="">매입처 선택</option>
                                <c:forEach var="item" items="${bycList}" varStatus="status">
                                    <option value="${item.byc010id}">${item.bycname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- //여기 1 -->
            <div id="detail_itemsearch" class="layerTb scrollTb mt10">
                <table cellpadding="0" cellspacing="0">
                    <caption></caption>
                    <colgroup>
                        <col width="*"/>
                        <col width="15%"/>
                    </colgroup>
                    <tbody>
                    <tr id="detail_autosearch">
                        <td>
                            <p class="memo">
                                <input type="text" id="detail_autocomplete" class="it" placeholder=" 상품명 검색"/>
                                <input type="hidden" id="detail_autosearch_name"/>
                                <input type="hidden" id="detail_autosearch_value"/>
                                <a href="javascript:addCrossitemSearch();">추가</a>
                            </p>
                        </td>
                        <td style="text-align: center">
                            <a href="javascript:initCrossitemSearch();"><strong>초기화</strong></a>
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
                        <th scope="row">상품명</th>
                        <td><input id="detail_itemname" type="text" class="it " title="" value="" name=""/></td>
                        <th scope="row">옵션</th>
                        <td><input id="detail_itemopt" type="text" class="it " title="" value="" name=""/></td>
                    </tr>
                    <tr>
                        <th scope="row">단위수량</th>
                        <td><input id="detail_itemea" type="number" class="it " title="" value="" name=""/></td>
                        <th scope="row">매입단가</th>
                        <td><input id="detail_itembuyprice" type="number" class="it " title="" value="" name=""/></td>
                    </tr>
                    <tr>
                        <th scope="row">면세여부</th>
                        <td colspan="3">
                            <input id="detail_sel3_1" class="detail_update_no" type="radio" value="1" name="detail_sel3" onclick="radiobox03Click(1)"/><label for="detail_sel3_1">세금부과상품</label>
                            <input id="detail_sel3_2" class="detail_update_no" type="radio" value="2" name="detail_sel3" onclick="radiobox03Click(2)"/><label for="detail_sel3_2">면세상품</label>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">구분</th>
                        <td colspan="3">
                            <input id="detail_sel2_1" class="detail_update_no" type="radio" value="1" name="detail_sel2" onclick="radiobox02Click(1)"/><label for="detail_sel2_1">제조사 출고상품</label>
                            <input id="detail_sel2_2" class="detail_update_no" type="radio" value="2" name="detail_sel2" onclick="radiobox02Click(2)"/><label for="detail_sel2_2">재고관리상품</label>
                            <input id="detail_sel2_3" class="detail_update_no" type="radio" value="3" name="detail_sel2" onclick="radiobox02Click(3)"/><label for="detail_sel2_3">사은품</label>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">매입배송비</th>
                        <td colspan="3"><input id="detail_itembuydlvprice" type="number" class="it " title="" value="" name=""/></td>
                    </tr>
                    <!-- 자사상품 출고시 생성 -->
                    <tr>
                        <th scope="row">우선창고 설정</th>
                        <td>
                            <select id="detail_pristock" class="detail_itemgubun_2">
                                <option value="">우선창고 선택</option>
                                <c:forEach var="item" items="${whsList}" varStatus="status">
                                    <option value="${item.whs010id}">${item.whsname}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th scope="row">상품크기</th>
                        <td><input id="detail_itemsize" type="text" class="it detail_itemgubun_2" title="" value="" name=""/></td>
                    </tr>
                    <tr>
                        <th scope="row">카톤수량</th>
                        <td><input id="detail_cartonqty" type="number" class="it detail_itemgubun_2" title="" value="" name=""/></td>
                        <th scope="row">파렛트수량</th>
                        <td><input id="detail_palletqty" type="number" class="it detail_itemgubun_2" title="" value="" name=""/></td>
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
            <a href="javascript:saveDetailData();" class="confirm" name="claim">확인</a>
            <a href="javascript:;" class="layerClose cancel">취소</a>
        </p>
        <a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
    </div>
</div>


</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var Ca = /\+/g;
    $(document).ready(function () {
        <c:if test="${prd010SearchVO.search_isdetail eq 1}">
        $('.searchMore').slideToggle();
        </c:if>

        $.ajax({
            url: "/ism/cum/prd010selectAll.do",
            type: "post",
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                $('#detail_autocomplete').autocomplete({
                    minLength: 0,
                    source: data,
                    focus: function (event, ui) {
                        return false;
                    },
                    select: function (event, ui) {
                        $('#detail_autocomplete').val(ui.item.label);
                        $('#detail_autosearch_name').val(ui.item.label);
                        $('#detail_autosearch_value').val(ui.item.itemcode);
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


    $('#pageUnit').change(function () { //페이지 크기 변경
        $('#form1').submit();
    });
    $('.dfChange_whs010id_select').change(function () { //우선창고 설정 변경
        var a = $(this).val();
        var b = $(this).attr("dataid");
        $('#dfChange_whs010id').val(a);
        $('#dfChange_orderitemid').val(b);
        $('#form1').submit();
    });

    //common.js 있는 부분을 처리를 위해 common.js 빼고 추가
    $('.layerBt').on('click', function () {

    })

    $('.layerClose').on('click', function () {
        $('.layerCont').fadeOut();
        $('.fade').fadeOut(function () {
            $('.fade').remove();
        })
        return false;
    })


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

    //결합상품 결합상품들 열기
    function showCrossItem(itemcode) {
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


    //체크박스된 주문 목록 삭제
    function selectDel() {
        if (confirm("선택 주문을 삭제하시겠습니까?")) {
            var orderitemids = "";
            $('.chk_info').each(function() {
                if ($(this).is(":checked")) {
                    orderitemids += ($(this).attr("dataid") + ",");
                }
            });
            if (orderitemids == "") {
                alert("삭제 할 주문을 선택해주시기 바랍니다.");
                return;
            }else{
                orderitemids = orderitemids.substring(0,orderitemids.length - 1);
            }
            $.ajax({
                url : "/ism/prd/prd010SelectDel.do",
                type: "post",
                data : { "orderitemids" : orderitemids },
                success : function(data){
                    if (data == "SUCCESS") {
                        alert("삭제 되었습니다.");
                        $('#form1').submit();
                    }else{
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
                    alert("Error : "+msg);
                }
            });
        }
    }




    //운영상품 디테일 설정 ----------------------------------------------------

    var firstCheck = 0;
    var currentItemcoed = '';

    function openSingleItemDetail(itemcode) {
        if (itemcode == null || itemcode == '') {
            //처음 여는 경우
            if (firstCheck == 0) {
                currentItemcoed = '';
                firstCheck = 1;
                initAllDetail();
            }
            //그대로 열기
        } else {
            //해당 상품의 내용을 클릭하기기

            currentItemcoed = itemcode;
            firstCheck = 0;
            initSettingDetail();
        }
        //open
        $('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
        $('.fade').fadeIn();
        $('#layerCont').css({
            'margin': '-' + ($('#layerCont').height() / 2) + 'px 0 0 -' + ($('#layerCont').width() / 2) + 'px'
        })
        $('#layerCont').fadeIn();
        return false;
    }

    //등록 또는 수정입력시
    //수정의 경우 currentItemcoed 값을 찾기
    //등록을 하는경우 currentItemcoed에 값을 입력하기
    //등록을 하는경우 firstCheck = 0;부여
    //firstCheck = 0인경우 모든 값 초기화
    //확인의 경우 메모 입력과는 별개로 동작
    function initAllDetail() {
        $('.detail_update_no').attr('disabled', false);
        $('#detail_sel1_1').prop('disabled', false);
        $('#detail_sel1_2').prop('disabled', false);
        $('#detail_sel1_1').trigger('click');
        $('#detail_sel2_1').trigger('click');
        $('#detail_sel3_1').trigger('click');
        $('#detail_category').val('');
        $('#detail_byc').val('');
        $('#detail_itemname').val('');
        $('#detail_itemopt').val('');
        $('#detail_itemea').val('');
        $('#detail_itembuyprice').val('');
        $('#detail_itembuydlvprice').val('');
        $('#detail_pristock').val('');
        $('#detail_itemsize').val('');
        $('#detail_cartonqty').val('');
        $('#detail_palletqty').val('');
        $('#memoul').html('');
        $('#detail_autocomplete').val('');
        $('#detail_autosearch_value').val('');
        $('#detail_autosearch_name').val('');
        $('.detail_autosearch_regist').remove();
    }

    function initSettingDetail() {
        if (currentItemcoed == '') {

        } else {
            initAllDetail();
            setMemo(currentItemcoed, 'PR', $('#memoul'));
            $.ajax({
                url: "/ism/prd/prd010Detail.do",
                type: "post",
                data : { "currentItemcoed" : currentItemcoed },
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function (data) {
                    setDetailResult(data);
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

    function setDetailResult(data) {
        $('#detail_category').val(data.itemcat1);
        if (data.itemcrosstype == 'F') {
            $('#detail_sel1_2').trigger('click');
            $.each(data.childItemcode, function(index, item){
                createSearchResult(item.itemcode, item.label);
            });
        } else {
            $('#detail_sel1_1').trigger('click');
        }
        $('#detail_byc').val(data.byc010id);
        $('#detail_itemname').val(data.itemname);
        $('#detail_itemopt').val(data.itemopt);
        $('#detail_itemea').val(data.itemea);
        $('#detail_itembuyprice').val(data.itembuyprice);
        if (data.itemgubun == '3') {
            $('#detail_sel2_3').trigger('click');
        } else if (data.itemgubun == '2') {
            $('#detail_sel2_2').trigger('click');
        } else {
            $('#detail_sel2_1').trigger('click');
        }
        $('#detail_itembuydlvprice').val(data.itembuydlvprice);
        $('#detail_pristock').val(data.pristock);
        $('#detail_itemsize').val(data.itemsize);
        $('#detail_cartonqty').val(data.cartonqty);
        $('#detail_palletqty').val(data.palletqty);
        if (data.taxfree == '0') {
            $('#detail_sel3_1').trigger('click');
        } else if (data.taxfree == '1') {
            $('#detail_sel3_2').trigger('click');
        }
        $('#detail_sel1_1').prop('disabled', true);
        $('#detail_sel1_2').prop('disabled', true);
        $('.detail_update_no').attr('disabled', true);
    }

    function saveDetailData() {
        var detail_childItemcode = '';
        var detail_category = $('#detail_category').val();
        var detail_byc = $('#detail_byc').val()
        var detail_itemname = $('#detail_itemname').val();
        var detail_pristock = $('#detail_pristock').val()
        $('.detail_autosearch_regist').each(function (index, item) {
            var temp = $(this).attr("dataid");
            if (index == 0) {
                detail_childItemcode = temp
            } else {
                detail_childItemcode = detail_childItemcode +',' + temp;
            }
        });

        if (detail_category == '') {
            alert("카테고리를 선택해주세요.")
            return;
        }

        if (detail_byc == '') {
            alert("매입처를 선택해주세요.")
            return;
        }

        if (detail_itemname == '') {
            alert("상품명을 입력해주세요.")
            return;
        }

        if (detail_itemgubun == 2 || detail_itemgubun == 3) {
            if (detail_pristock == '') {
                alert("우선창고를 선택해주세요.")
                return;
            }
        }
        
        $.ajax({
            url: "/ism/prd/prd010DetailSave.do",
            type: "post",
            data : {
                "currentItemcoed" : currentItemcoed,
                "detail_category" : $('#detail_category').val(),
                "detail_itemcrosstype" : detail_itemcrosstype,
                "detail_byc" : $('#detail_byc').val(),
                "detail_itemname" : $('#detail_itemname').val(),
                "detail_itemopt" : $('#detail_itemopt').val(),
                "detail_itemea" : $('#detail_itemea').val(),
                "detail_itembuyprice" : $('#detail_itembuyprice').val(),
                "detail_itembuydlvprice" : $('#detail_itembuydlvprice').val(),
                "detail_itemgubun" : detail_itemgubun,
                "detail_pristock" : $('#detail_pristock').val(),
                "detail_itemsize" : $('#detail_itemsize').val(),
                "detail_cartonqty" : $('#detail_cartonqty').val(),
                "detail_palletqty" : $('#detail_palletqty').val(),
                "detail_childItemcode" : detail_childItemcode,
                "detail_taxfree" : detail_taxfree
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("저장되었습니다.");
                currentItemcoed = '';
                firstCheck = 0;
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


    //메모입력
    function inputmemodata() {
        if (currentItemcoed == '') {
            alert("운영상품 등록 후 메모 등록이 가능합니다.");
            return;
        }
        var inputmemo = $('#detail_inputmemo').val();
        savememodata(currentItemcoed, 'PR', inputmemo, $('#memoul'));
        $('#detail_inputmemo').val("");
        return false;
    }

    //결합상품 결합상품들 추가
    function addCrossitemSearch() {
        var searchValue = $('#detail_autosearch_value').val();
        var searchLavel = $('#detail_autocomplete').val();
        var searchName = $('#detail_autosearch_name').val();
        var a = 0;
        if (searchValue != null && searchValue != '' && searchLavel != null && searchLavel != '' && searchName != null && searchName != '') {
            $('.detail_autosearch_regist').each(function () {
                var temp = $(this).attr("dataid");
                if (searchValue == temp) {
                    a = 1;
                }
            });
            if (a == 1) {
                alert("이미 추가하였습니다.");
            } else {
                $('#detail_autocomplete').val('');
                $('#detail_autosearch_value').val('');
                $('#detail_autosearch_name').val('');
                createSearchResult(searchValue, searchName);
            }
        } else {
            alert("상품명을 입력해주세요.")
        }
    }

    function createSearchResult(searchValue, searchName) {
        $('#detail_autosearch').after("<tr class='detail_autosearch_regist' dataid='" + searchValue + "'><td colspan='1'>" + searchName + "(" + searchValue + ")</td><td style=\"text-align: center\"><button class='delbtn' onclick='delRow(this)'>삭제</button></td></tr>");
    }

    function delRow(obj) {
        $(obj).parent().parent().remove();
    }

    //결합상품 초기화
    function initCrossitemSearch() {
        $('.detail_autosearch_regist').remove();
    }


    //여기 2
    //라디오박스 01
    var detail_itemcrosstype = 'S';

    function radiobox01Click(myRadio) {
        if (myRadio == 'F') {
            $('#detail_itemsearch').show();
            detail_itemcrosstype = 'F';
        } else if (myRadio == 'S') {
            $('#detail_itemsearch').hide();
            detail_itemcrosstype = 'S';
        }
    }

    //라디오박스 02
    var detail_itemgubun = 1;

    function radiobox02Click(myRadio) {
        if (myRadio == 1) {
            $('.detail_itemgubun_2').prop('disabled', true);
            detail_itemgubun = 1;
        } else if (myRadio == 2) {
            $('.detail_itemgubun_2').prop('disabled', false);
            detail_itemgubun = 2;
        } else if (myRadio == 3) {
            $('.detail_itemgubun_2').prop('disabled', false);
            detail_itemgubun = 3;
        }
    }

    //라디오박스 02
    var detail_taxfree = 0;

    function radiobox03Click(myRadio) {
        if (myRadio == 1) {
            detail_taxfree = 0;
        } else if (myRadio == 2) {
            detail_taxfree = 1;
        }
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