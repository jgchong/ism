<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>E-DAS</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="/js/custom/common.js" type="text/javascript" charset="utf-8"></script>
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

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var Ca = /\+/g;
$(document).ready(function() {
	$('#pageUnit').change(function() { //목록 보여줄 수 50 100 500 변경시 처리
		document.form1.pageIndex.value = 1;
		$('#form1').submit();
	});
	//common.js 있는 부분을 여기서만 처리를 위해 common.js 빼고 추가
	$("#dtSearch_frPoDt").change(function() {
		document.form1.pageIndex.value = 1;
        document.form1.submit();
	});

	$("#dtSearch_toPoDt").change(function() {
		document.form1.pageIndex.value = 1;
        document.form1.submit();
	});
	
	$("#dtSearch_bycNm").on('change', function(ret) {  
        var bycId = ret.target.value;
        $.ajax({
            url : "/ism/po/po020SelectSndNm.do",
            type: "post",
            data : { "bycId" : bycId },
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success : function(data){
            	console.log(data.userlist.length);
                if (data.userlist.length > 0) {
					$("#dtSearch_sndNm option").remove();
					$("#dtSearch_sndNm").append("<option value=''>선택</option>");
       				$.each(data.userlist,function(index, item) {
       					$("#dtSearch_sndNm").append("<option value='"+decodeURIComponent(item.username.replace(Ca, " "))+"'>"+decodeURIComponent(item.username.replace(Ca, " "))+"</option>");
       				});
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
                $('#msgareamanual').text("Error : "+msg);
            }
        });
    });
});

function fnLinkPage(pageNo){
    document.form1.pageIndex.value = pageNo;
    document.form1.action = "<c:url value='/ism/po/po020.do'/>";
    document.form1.submit();
}

</script>
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

            <!-- 하단 메인화면 -->
            <div class="contents">
                <h2 class="pageTit">발주발송내역</h2>

                <form id="form1" name="form1" method="post" action="/ism/po/po020.do" class="searchArea">
                	<input name="pageIndex" type="hidden" value="<c:out value='${ismpomsearch020VO.pageIndex}'/>"/>
                	<strong style="font-size:12px;">발송일</strong>
					<input type="text" class="it datepicker" title="" value="${ismpomsearch020VO.dtSearch_frPoDt}" id="dtSearch_frPoDt" name="dtSearch_frPoDt" placeHolder="발송시작일자" style="width:120px; float:none;"/> ~ 
					<input type="text" class="it datepicker" title="" value="${ismpomsearch020VO.dtSearch_toPoDt}" id="dtSearch_toPoDt" name="dtSearch_toPoDt" placeHolder="발송종료일자" style="width:120px; float:none;"/>
                    <strong style="font-size:12px;margin-left:20px;">매입처</strong>
                    <select name="dtSearch_bycNm" id="dtSearch_bycNm">
						<option value="">선택</option>
                        <c:forEach var="item" items="${bycList}" varStatus="status">
						<option value="${item.byc010id}" <c:if test="${ismpomsearch020VO.dtSearch_bycNm eq item.byc010id}">selected</c:if> >${item.bycname}</option>
	                    </c:forEach>
					</select>
					<strong style="font-size:12px;margin-left:20px;">발신인</strong>
                    <select name="dtSearch_sndNm" id="dtSearch_sndNm">
						<option value="">선택</option>
                        <c:forEach var="item" items="${bycNmList}" varStatus="status">
						<option value="${item.bycusername}" <c:if test="${ismpomsearch020VO.dtSearch_sndNm eq item.bycusername}">selected</c:if> >${item.bycusername}</option>
	                    </c:forEach>
					</select>
                    <button style="margin-left:4px;">검색</button>
                </form>
                <div class="listTb">
                    <table cellpadding="0" cellspacing="0" class="" summary="">
                        <caption></caption>
                        <colgroup>
                            <col width="8%"/>
                            <col width="*"/>
                            <col width="15%"/>
                            <col width="15%"/>
                            <col width="15%"/>
                            <col width="15%"/>
                            <col width="15%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">발송일</th>
                            <th scope="col">매입처</th>
                            <th scope="col">수신인</th>
                            <th scope="col">발신인</th>
                            <th scope="col">첨부</th>
                            <th scope="col">상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultPoList}" varStatus="status">
                            <tr>
                                <td><c:out value="${result.sortNo}"/></td>
                                <td><c:out value="${result.processdate}"/></td>
                                <td><c:out value="${result.bycname}"/></td>
                                <td><c:out value="${result.bycusername}"/>(<c:out value="${result.rcvuseremail}"/>)</td>
                                <td><c:out value="${result.regid}"/></td>
                                <td>
                                	<c:choose>
									    <c:when test="${result.uploadfilename eq 'NO_FILE'}">
									    -
									    </c:when>
									    <c:when test="${result.uploadfilename ne 'NO_FILE'}">
									    <a href="#contents" style="font-size:11px;text-decoration:none;" onclick="javascript:reSendMail('','<c:out value="${result.uploadfilename}"/>', 'X')"><c:out value="${result.uploadfilename}"/></a>
									    </c:when>
									    <c:otherwise>
									        error
									    </c:otherwise>
									</c:choose> 
                                	
                                </td>
                                <td>성공&nbsp;
                                	<c:choose>
									    <c:when test="${result.uploadfilename eq 'NO_FILE'}">
									    (No File)
									    </c:when>
									    <c:when test="${result.uploadfilename ne 'NO_FILE'}">
									    <a href="#contents" onclick="javascript:reSendMail('<c:out value="${result.rcvuseremail}"/>', '<c:out value="${result.uploadfilename}"/>', '<c:out value="${result.receiveType}"/>')"><img src="/images/resend.png" /></a>
									    </c:when>
									    <c:otherwise>
									        error
									    </c:otherwise>
									</c:choose> 
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
    </div> <!-- container -->
</div> <!-- wrap -->

<form id="formatdn" name="formatdn" method="post"></form>
<iframe name="tr" src="" width="0" height="0" frameborder="0" scrolling="no"></iframe>

</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $(".datepicker").datepicker({
            dateFormat : "yy-mm-dd",
            monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
            changeMonth : true,
            changeYear : true,
            yearRange : "c-70:c+70",
            showMonthAfterYear : true
        });
    });
    
    function reSendMail(rcvuseremail, uploadfilename, receiveType) {
    	
    	$.ajax({
            url : "/ism/po/po020ReSendMail020.do",
            type: "post",
            data : { "rcvuseremail" : rcvuseremail, "uploadfilename" : uploadfilename, "receiveType" : receiveType },
            success : function(data){
                if (data == "SUCCESS") {
                	alert("정상적으로 메일이 발송되었습니다.");
                }else if (data.indexOf(".xls") >= 0){
                	alert("발주정보가 다운로드되었습니다.");
                   	downLoadFile(data);
                }else{
                	//$('#msgareamanual').text("저장 중 오류가 발생했습니다.");
                	alert("처리 중 오류가 발생했습니다.");
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
                $('#msgareamanual').text("Error : "+msg);
            }
        });
    }
    
    function downLoadFile(filename) {
    	filename = encodeURI(filename);
    	T = document.formatdn;
    	T.target	= "tr";
    	T.action	= "/ism/cmm/attachFileDownFileName.do?filename="+filename;
    	T.submit();
    }
    
</script>
