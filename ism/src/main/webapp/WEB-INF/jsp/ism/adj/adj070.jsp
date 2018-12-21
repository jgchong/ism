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
                <li><a href="adj020.do">상품별정산</a></li>
                <li><a href="adj030.do">상품수불부</a></li>
                <li><a href="adj040.do">수금관리대장</a></li>
                <li><a href="adj050.do">재고관리대장</a></li>
                <li><a href="adj060.do">운송비대장</a></li>
                <li><a href="adj070.do" class="on">판매관리비</a></li>
                <li><a href="adj080.do">정산도움말</a></li>
            </ul>
            <div class="contents">
                <h2 class="pageTit">판매관리비</h2>
                <%--
                <form id="form1" name="form1" method="post" action="/ism/adj/adj070.do" class="searchArea">
                    <input id="dtSearch_frCreateDt" type="text" name="dtSearch_frCreateDt" value="${adj010SearchVO.dtSearch_frCreateDt}" class="it monthPicker"/>
                </form>
                --%>
                <form id="form1" name="form1" method="post" action="/ism/adj/adj070.do" class="searchArea" style="text-align:left; margin-top:-20px;">
                    <input id="dtSearch_frCreateDt" type="text" name="dtSearch_frCreateDt" value="${adj010SearchVO.dtSearch_frCreateDt}" class="it monthPicker"/>
                </form>
                <div style="display:inline-block; width:100%; height:200px;" class="listTb">
                    <div style="float:left; width:calc(50% - 10px);">
                        <table cellpadding="0" cellspacing="0" class="" summary="">
                            <caption></caption>
                            <colgroup>
                                <col width="30%"/>
                                <col width="70%"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col" colspan="2">1) 부서 사용금액</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr class="total">
                                    <td>합계</td>
                                    <td>${col_sum00}</td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <tr>
                                    <td>사용품/협찬</td>
                                    <td><input type="text" class="it it2 adj070update1" value="${resultObject.price1}" name=""/></td>
                                    <c:set var="col_sum00" value="${col_sum00 + resultObject.price1}"/>
                                </tr>
                                <tr>
                                    <td>샘플사용</td>
                                    <td><input type="text" class="it it2 adj070update1" value="${resultObject.price2}" name=""/></td>
                                    <c:set var="col_sum00" value="${col_sum00 + resultObject.price2}"/>
                                </tr>
                                <tr>
                                    <td>영업용</td>
                                    <td><input type="text" class="it it2 adj070update1" value="${resultObject.price3}" name=""/></td>
                                    <c:set var="col_sum00" value="${col_sum00 + resultObject.price3}"/>
                                </tr>
                                <tr>
                                    <td>파손,망실,분실 건</td>
                                    <td><input type="text" class="it it2 adj070update1" value="${resultObject.price33}" name=""/></td>
                                    <c:set var="col_sum00" value="${col_sum00 + resultObject.price33}"/>
                                </tr>
                            </tbody>
                        </table>
                        <p style="display:inline-block; text-align:right; padding:10px 0; width:100%;">
                            <a href="javascript:" onclick="updateItem1()" class="btn" style="padding:7px 15px; border:0; background:#457cac; color:#fff; font-size:14px; vertical-align:bottom;">확인</a>
                        </p>
                        
                    </div>
                    
                    <div style="float:right; width:calc(50% - 10px);">
                        <table cellpadding="0" cellspacing="0" class="" summary="">
                            <caption></caption>
                            <colgroup>
                                <col width="30%"/>
                                <col width="70%"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col" colspan="2"> 2) 타부서 사용금액</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr class="total">
                                    <td>합계</td>
                                    <td>${col_sum001}</td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <tr>
                                    <td>선장품(경영지원실)</td>
                                    <td><input type="text" class="it it2 adj070update2" value="${resultObject.price4}" name=""/></td>
                                    <c:set var="col_sum001" value="${col_sum001 + resultObject.price4}"/>
                                </tr>
                                <tr>
                                    <td>선장품(사내반찬)</td>
                                    <td><input type="text" class="it it2 adj070update2" value="${resultObject.price5}" name=""/></td>
                                    <c:set var="col_sum001" value="${col_sum001 + resultObject.price5}"/>
                                </tr>
                                <tr>
                                    <td>기타(타부서)</td>
                                    <td><input type="text" class="it it2 adj070update2" value="${resultObject.price6}" name=""/></td>
                                    <c:set var="col_sum001" value="${col_sum001 + resultObject.price6}"/>
                                </tr>
                            </tbody>
                        </table>
                        <p style="display:inline-block; text-align:right; padding:10px 0; width:100%;">
                            <a href="javascript:" onclick="updateItem2()" class="btn" style="padding:7px 15px; border:0; background:#457cac; color:#fff; font-size:14px; vertical-align:bottom;">확인</a>
                        </p>
                    </div>
                </div>
                
                
                <div style="display:inline-block; width:100%;" class="listTb">
                    <div style="float:left; width:calc(50% - 10px);">
                         <table cellpadding="0" cellspacing="0" class="" summary="">
                            <caption></caption>
                             <colgroup>
                                <col width="*"/>
                                <col width="18%"/>
                                <col width="18%"/>
                                <col width="18%"/>
                                <col width="30%"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col" colspan="6">3)지급수수료 (수수료정산 매출처)</th>
                                </tr>
                                <tr>
                                    <th scope="col">매출처</th>
                                    <th scope="col">매출액</th>
                                    <th scope="col">정산금액</th>
                                    <th scope="col">지금수수료</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr class="total">
                                    <td>합계</td>
                                    <td>${col_sum01}</td>
                                    <td>${col_sum0000}</td>
                                    <td>${col_sum02}</td>
                                    <td></td>
                                </tr>
                            </tfoot>
                            <tbody>
                                 <c:forEach var="result" items="${resultList3}" varStatus="status">
                                    <tr>
                                        <td>${result.cum010name}</td>
                                        <td>${result.price}</td>
                                        <td><input type="text" class="it it2 adj070update3${result.cum010id}" value="${result.saleprice}" name=""/></td>
                                        <td><input type="text" class="it it2 adj070update3${result.cum010id}" value="${result.givesusuprice}" name=""/></td>
                                        <td><a href="javascript:" onclick="updateItem3(${result.cum010id})" class="btn">확인</a></td>
                                        <c:set var="col_sum0000" value="${col_sum0000 + result.saleprice}"/>
                                        <c:set var="col_sum01" value="${col_sum01 + result.price}"/>
                                        <c:set var="col_sum02" value="${col_sum02 + result.givesusuprice}"/>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p style="display:inline-block; text-align:right; padding:10px 0; width:100%;">
                            <a href="javascript:" onclick="updateItem2()" class="btn" style="padding:7px 15px; border:0; background:#457cac; color:#fff; font-size:14px; vertical-align:bottom;">확인</a>
                        </p>
                    </div>

                    <div style="float:right; width:calc(50% - 10px);">
                        <table cellpadding="0" cellspacing="0" class="" summary="">
                            <caption></caption>
                            <colgroup>
                                <col width="*"/>
                                <col width="18%"/>
                                <col width="18%"/>
                                <col width="16%"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col" colspan="4">4) 영업외수익</th>
                            </tr>
                            <tr>
                                <th scope="col">매입처</th>
                                <th scope="col">금액</th>
                                <th scope="col">메모</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr class="total">
                                <td>합계</td>
                                <td>${col_sum03}</td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td><input type="text" class="it it2 adj070insert4" value="" name="" placeholder="매입처"/></td>
                                <td><input type="number" class="it it2 adj070insert4" value="" name="" placeholder="금액"/></td>
                                <td><input type="text" class="it it2 adj070insert4" value="" name="" placeholder="메모"/></td>
                                <td><a href="javascript:" onclick="insertItem4()" class="btn">추가하기</a></td>
                            </tr>
                            <c:forEach var="result" items="${resultList4}" varStatus="status">
                                <tr>
                                    <td><input type="text" class="it it2 adj070update4${result.adj0702id}" value="${result.bycname}" name=""/></td>
                                    <td><input type="text" class="it it2 adj070update4${result.adj0702id}" value="${result.price}" name=""/></td>
                                    <td><input type="text" class="it it2 adj070update4${result.adj0702id}" value="${result.memo}" name=""/></td>
                                    <td><a href="javascript:" onclick="updateItem4(${result.adj0702id})" class="btn">확인</a></td>
                                    <c:set var="col_sum03" value="${col_sum03 + result.price}"/>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                 </div>



                 <div style="display:inline-block; width:100%;" class="listTb">

                    <div style="float:left; width:calc(50% - 10px);">
                        <table cellpadding="0" cellspacing="0" class="" summary="">
                            <caption></caption>
                            <colgroup>
                                <col width="*"/>
                                <col width="18%"/>
                                <col width="18%"/>
                                <col width="18%"/>
                                <col width="16%"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col" colspan="5">5) 영업사원수수료</th>
                            </tr>
                            <tr>
                                <th scope="col">매출처</th>
                                <th scope="col">매출액</th>
                                <th scope="col">지급수수료</th>
                                <th scope="col">정산정책</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr class="total">
                                <td>합계</td>
                                <td></td>
                                <td>${col_sum05}</td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td><input type="text" class="it it2 adj070insert5" value="" name="" placeholder="매출처"/></td>
                                <td><input type="number" class="it it2 adj070insert5" value="" name="" placeholder="매출액"/></td>
                                <td><input type="number" class="it it2 adj070insert5" value="" name="" placeholder="지급수수료"/></td>
                                <td><input type="text" class="it it2 adj070insert5" value="" name="" placeholder="정산정책"/></td>
                                <td><a href="javascript:" onclick="insertItem5()" class="btn">추가하기</a></td>
                            </tr>
                            <c:forEach var="result" items="${resultList5}" varStatus="status">
                                <tr>
                                    <td><input type="text" class="it it2 adj070update5${result.adj0703id}" value="${result.bycname}" name=""/></td>
                                    <td><input type="text" class="it it2 adj070update5${result.adj0703id}" value="${result.price1}" name=""/></td>
                                    <td><input type="text" class="it it2 adj070update5${result.adj0703id}" value="${result.price2}" name=""/></td>
                                    <td><input type="text" class="it it2 adj070update5${result.adj0703id}" value="${result.memo}" name=""/></td>
                                    <td><a href="javascript:" onclick="updateItem5(${result.adj0703id})" class="btn">확인</a></td>
                                    <c:set var="col_sum05" value="${col_sum05 + result.price2}"/>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

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

    function updateItem1() {
        var input = new Array();


        $('.adj070update1').each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070update1.do",
            type: "post",
            data: {
                "closedt": $(".monthPicker").val(),
                "in1": input[0],
                "in2": input[1],
                "in3": input[2],
                "in4": input[3]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    function updateItem2() {
        var input = new Array();


        $('.adj070update2').each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070update2.do",
            type: "post",
            data: {
                "closedt": $(".monthPicker").val(),
                "in1": input[0],
                "in2": input[1],
                "in3": input[2]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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


    function updateItem3(adj060id) {
        var input = new Array();


        $('.adj070update3' + adj060id).each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070update3.do",
            type: "post",
            data: {
                "adj060id": adj060id,
                "closedt": $(".monthPicker").val(),
                "in0": input[0],
                "in1": input[1],
                "in2": input[2]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    function insertItem4() {
        var input = new Array();


        $('.adj070insert4').each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070insert4.do",
            type: "post",
            data: {
                "closedt": $(".monthPicker").val(),
                "in1": input[0],
                "in2": input[1],
                "in3": input[2]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    function updateItem4(adj060id) {
        var input = new Array();


        $('.adj070update4' + adj060id).each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070update4.do",
            type: "post",
            data: {
                "adj060id": adj060id,
                "closedt": $(".monthPicker").val(),
                "in0": input[0],
                "in1": input[1],
                "in2": input[2]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    function insertItem5() {
        var input = new Array();


        $('.adj070insert5').each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070insert5.do",
            type: "post",
            data: {
                "closedt": $(".monthPicker").val(),
                "in1": input[0],
                "in2": input[1],
                "in3": input[2],
                "in4": input[3]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    function updateItem5(adj060id) {
        var input = new Array();


        $('.adj070update5' + adj060id).each(function (index, item) {
            input[index] = $(this).val()
        });
        $.ajax({
            url: "/ism/adj/adj070update5.do",
            type: "post",
            data: {
                "adj060id": adj060id,
                "closedt": $(".monthPicker").val(),
                "in0": input[0],
                "in1": input[1],
                "in2": input[2],
                "in3": input[3]
            },
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                alert("등록되었습니다.");
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
                    alert("Error : " + "제대로된 값을 입력해주세요.");
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

    $("#excelDownbtn").click(function () {
        document.form1.action = "<c:url value='/ism/adj/adj070ExcelDownload.do'/>";
        document.form1.submit();
        document.form1.action = "<c:url value='/ism/adj/adj070.do'/>";
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