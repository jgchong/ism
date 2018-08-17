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
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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
                        <a href="javascript:;" class="layerBt" name="product" style="margin-top: 30px;">운영상품 단품등록</a>
                        <a href="javascript:;" class="layerBt" name="product" style="margin-top: 30px;">운영상품 결합등록</a>
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
                                    <option value="">결합여부</option>
                                    <option value="S"
                                            <c:if test="${prd010SearchVO.dfSearch_itemcrosstype eq 'S'}">selected</c:if> >N
                                    </option>
                                    <option value="P"
                                            <c:if test="${prd010SearchVO.dfSearch_itemcrosstype eq 'P'}">selected</c:if> >Y
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
                            <col width="5%"/>
                            <col width="5%"/>
                            <col width="10%"/>
                            <col width="13%"/>
                            <col width="8%"/>
                            <col width="*"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="5%"/>
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
                            <th scope="col">우선창고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr id="${result.itemcode}" value="off"
                                <c:if test="${result.itemcrosstype eq 'F'}">class="<c:out value="${result.crossitemcode}"/>" style="display:none"
                            </c:if>>
                                <td><input type="checkbox" id="chk_info" name="chk_info" class="chk_info" dataid="${result.orderitemid}"/></td>
                                <td>
                                    <c:out value="${result.listNo}"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.itemcrosstype eq 'S'}">
                                            N
                                        </c:when>
                                        <c:when test="${result.itemcrosstype eq 'P'}">
                                            <a href="javascript:showCrossItem('${result.itemcode}');">Y</a>
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${result.bycname}"/></td>
                                <td><c:out value="${result.itemcode}"/></td>
                                <td>
                                    <a href="javascript:openSingleItemDetail('${result.itemcode}');"><c:out value="${result.itemname}"/></a>
                                </td>
                                <td><c:out value="${result.itemopt}"/></td>
                                <td><c:out value="${result.itemea}"/></td>
                                <td><c:out value="${result.itembuyprice}"/></td>
                                <td>
                                    <c:out value="${result.itembuydlvprice}"/></td>
                                <td>
                                    <select id="dfChange_whs010id_select" dataid="${result.orderitemid}" <c:if test="${result.itemgubun ne '2'}">disabled</c:if>>
                                        <c:if test="${result.itemgubun ne '2'}">
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

<form id="form2" name="form1" method="post" action="/ism/prd/prd010Detail.do">
    <input type="hidden" id="dfDetail_itemname" name="dfDetail_itemname" value="${prd010DetailVO.dfDetail_itemname}"/>
    <input type="hidden" id="dfDetail_orderitemid" name="dfDetail_orderitemid" value="${prd010DetailVO.dfDetail_orderitemid}"/>
    <!-- 주문현황관리 -->
    <div class="layerCont product">
        <div class="inner">
            <p class="layerTit">운영상품 단품 등록 및 수정</p>
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
                            <th scope="row">상품카테고리</th>
                            <td colspan="3">
                                <select name="" title="" class="three">
                                    <option value="" selected="selected">대분류</option>
                                    <option value=""></option>
                                </select>
                                <select name="" title="" class="three">
                                    <option value="" selected="selected">중분류</option>
                                    <option value=""></option>
                                </select>
                                <select name="" title="" class="three">
                                    <option value="" selected="selected">소분류</option>
                                    <option value=""></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">구분선택</th>
                            <td>
                                <input type="radio" value="" name="sel1" id="sel1_1"/><label for="sel1_1">단품</label>
                                <input type="radio" value="" name="sel1" id="sel1_2"/><label for="sel1_2">결합</label>
                            </td>
                            <th scope="row">매입처</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <tr>
                            <th scope="row">상품명</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">옵션</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <tr>
                            <th scope="row">단위수량</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">매입단가</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <tr>
                            <th scope="row">구분</th>
                            <td colspan="3">
                                <input type="radio" value="" name="sel2" id="sel2_1"/><label for="sel2_1">제조사 출고상품</label>
                                <input type="radio" value="" name="sel2" id="sel2_2"/><label for="sel2_2">재고관리상품</label>
                                <input type="radio" value="" name="sel2" id="sel2_3"/><label for="sel2_3">사은품</label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">배송비</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">등록일</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <tr>
                            <th scope="row">자사상품코드</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">매입사코드등록</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <!-- 자사상품 출고시 생성 -->
                        <tr>
                            <th scope="row">우선창고 설정</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">상품크기</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                        </tr>
                        <tr>
                            <th scope="row">카톤수량</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
                            <th scope="row">파렛트수량</th>
                            <td><input type="text" class="it " title="" value="" name=""/></td>
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
                                    메모내용
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="memo">
                                    <input type="text" class="it" title="" value="" name=""/>
                                    <button>입력</button>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <p class="layerFootBt">
                <a href="#" class="confirm" name="claim">확인</a>
                <a href="javascript:;" class="layerClose cancel">취소</a>
            </p>
            <a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
        </div>
    </div>
</form>


</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(document).ready(function () {
        <c:if test="${prd010SearchVO.search_isdetail eq 1}">
        $('.searchMore').slideToggle();
        </c:if>
    });
    $('#pageUnit').change(function () { //페이지 크기 변경
        $('#form1').submit();
    });
    $('#dfChange_whs010id_select').change(function () { //우선창고 설정 변경
        var a = $('#dfChange_whs010id_select').val();
        var b = $('#dfChange_whs010id_select').attr("dataid");
        $('#dfChange_whs010id').val(a);
        $('#dfChange_orderitemid').val(b);
        $('#form1').submit();
    });


    //common.js 있는 부분을 여기서만 처리를 위해 common.js 빼고 추가
    $('.layerBt').on('click', function () {
        openSingleItemDetail();
        $('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
        $('.fade').fadeIn();
        $('.' + $(this).attr('name')).css({
            'margin': '-' + ($('.' + $(this).attr('name')).height() / 2) + 'px 0 0 -' + ($('.' + $(this).attr('name')).width() / 2) + 'px'
        })
        $('.' + $(this).attr('name')).fadeIn();
        return false;
    })
    $('.layerClose').on('click', function () {
        $('.layerCont').fadeOut();
        $('.fade').fadeOut(function () {
            $('.fade').remove();
        })
        return false;
    })
    //레이어 작업시 레이어 버튼의 name 과 같은 class 를 찾아 오픈 (기본적으로 버튼의 클래스는 layerBt 로 통일)
    //레이어의 클래스는 layerCont 로 통일. 더블 클래스를 사용하여 버튼의 네임값을 추가로 설정.
    //레이어의 닫기 및 취소등의 버튼의 클래스는 layerClose 로 통일.


    /* 레이어 위로 레이어가 뜰때. */
    $(document).on('click', '.layerBt_v2', function () {
        $('body').append('<div class="fade_v2" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:200; display:none;"></div>')
        $('.fade_v2').fadeIn();
        $('.' + $(this).attr('name')).css({
            'margin': '-' + ($('.' + $(this).attr('name')).height() / 2) + 'px 0 0 -' + ($('.' + $(this).attr('name')).width() / 2) + 'px'
        })
        $('.' + $(this).attr('name')).fadeIn();
        return false;
    })
    $(document).on('click', '.layerClose_v2', function () {
        $('.layerCont_v2').fadeOut();
        $('.fade_v2').fadeOut(function () {
            $('.fade_v2').remove();
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

    //결합상품창 열기
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

    function openSingleItemDetail(itemId) {
        if (itemId == null || itemId == '') {
            $('#dfDetail_orderitemid').val('');
            alert("운영상품 단품 등록클릭");
        } else {
            $('#dfDetail_orderitemid').val(itemId);
            alert(itemId + "운영상품 단품 수정 클릭");
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