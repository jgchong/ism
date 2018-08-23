<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/sym/mpm/icon/";
  String imagePath_button = "/images/egovframework/sym/mpm/button/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title> KTI NMS </title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
	<style type="text/css">
div.searchArea		{ display:block; text-align:right; margin:0 0 20px; }
div.searchArea a	{ background:#457cac; padding:7px 15px; color:#fff; font-size:14px; border-radius:4px; text-decoration:none; }
div.searchArea .it	{ width:200px; height:30px; }
div.searchArea select				{ width:160px; height:34px; font-size:14px; }
div.searchArea button				{ padding:7px 15px; border:0; background:#457cac; color:#fff; font-size:14px; vertical-align:bottom; border-radius:4px; }
div.searchArea .ml30					{ margin-left:30px; }

div.searchArea .searchMore		{ border:1px solid #ebebeb; padding:5px; box-sizing:border-box; margin:10px 0 0; background:#f7f7f7; display:none; }
div.searchArea .searchMore ul	{ width:100%; display:inline-block; }
div.searchArea .searchMore li	{ float:left; width:20%; text-align:center; padding:5px; box-sizing:border-box; }
div.searchArea .searchMore li input			{ width:100%; box-sizing:border-box; text-align:center; font-size:14px; height:34px; }
div.searchArea .searchMore p	{ padding:10px 5px; }

.paging a.on {background-color:#457cac;color:#fff;}
	</style>

<style type="text/css">
    h1 {font-size:12px;}
    caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.progrmManageForm.checkField;
    if(document.progrmManageForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fDeleteProgrmManageList() {
    var checkField = document.progrmManageForm.checkField;
    var ProgrmFileNm = document.progrmManageForm.checkProgrmFileNm;
    var checkProgrmFileNms = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkProgrmFileNms += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkProgrmFileNms = ProgrmFileNm.value;
            }
        }
    }   

    document.progrmManageForm.checkedProgrmFileNmForDel.value=checkProgrmFileNms;
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgrmManageListDelete.do'/>";
    document.progrmManageForm.submit(); 
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//  document.menuManageForm.searchKeyword.value = 
    document.progrmManageForm.pageIndex.value = pageNo;
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>";
    document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListManage() { 
    document.progrmManageForm.pageIndex.value = 1;
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>";
    document.progrmManageForm.submit(); 
}
/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertProgramListManage() {
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListRegist.do'/>";
    document.progrmManageForm.submit(); 
}
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtProgramListDetail(progrmFileNm) {
    document.progrmManageForm.tmp_progrmNm.value = progrmFileNm;
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListDetailSelectUpdt.do'/>";
    document.progrmManageForm.submit(); 
}
/* ********************************************************
 * focus 시작점 지정함수
 ******************************************************** */
 function fn_FocusStart(){
        var objFocus = document.getElementById('F1');
        objFocus.focus();
    }

<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
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
		<div class="contentsWrap"> <!-- 추가 div -->

            <!-- 현재위치 네비게이션 시작 -->
            <div class="contents">
				<h2 class="pageTit">프로그램목록 관리</h2>
	            <form name="progrmManageForm" action ="<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>" method="post">
	            <input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
	
	                <!-- 검색 필드 박스 시작 -->
	                <div class="searchArea">
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
						<input name="checkedProgrmFileNmForDel" type="hidden" />
						<input class="it ml30" name="searchKeyword" type="text" size="60" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="60" id="F1" placeholder="프로그램 한글명"> 
                        <a href="#LINK" onclick="javascript:selectProgramListManage(); return false;" >조회 </a>
						<a href="<c:url value='/sym/mpm/EgovProgramListRegist.do'/>" onclick="insertProgramListManage(); return false;"><spring:message code="button.create" /></a>                              
	                    <a href="#LINK" onclick="fDeleteProgrmManageList(); return false;"><spring:message code="button.delete" /></a>   
	                </div>
	                <!-- //검색 필드 박스 끝 -->
	                <div id="page_info"><div id="page_info_align"></div></div>                    
	                <!-- table add start -->
	                <div class="listTb">
	                    <table summary="프로그램목록관리 목록으로 프로그램파일명, 프로그램명, url,프로그램설명 으로 구성" cellpadding="0" cellspacing="0">
	                    <caption>프로그램목록관리 목록</caption>
	                    <colgroup>
	                        <col width="3%" >
	                        <col width="20%" >
	                        <col width="20%" >
	                        <col width="40%" >
	                        <col width="17%" >
	                    </colgroup>
	                    <thead>
	                    <tr>
	                        <th scope="col" class="f_field" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fCheckAll();" title="전체선택"></th>
	                        <th scope="col" nowrap="nowrap">프로그램파일명</th>
	                        <th scope="col" nowrap="nowrap">프로그램 한글명</th>
	                        <th scope="col" nowrap="nowrap">URL</th>
	                        <th scope="col" nowrap="nowrap">프로그램설명</th>
	                    </tr>
	                    </thead>
	                    <tbody>                 
	
	                    <c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
	                    <!-- loop 시작 -->                                
	                      <tr>
						    <td nowrap="nowrap">
						       <input type="checkbox" name="checkField" class="check2" title="선택">
						       <input name="checkProgrmFileNm" type="hidden" value="<c:out value='${result.progrmFileNm}'/>"/>
						    </td>
						    <td style="cursor:hand;" nowrap="nowrap">                                 
						            <span class="link"><a href="<c:url value='/sym/prm/EgovProgramListDetailSelectUpdt.do'/>?tmp_progrmNm=<c:out value="${result.progrmFileNm}"/>"  onclick="selectUpdtProgramListDetail('<c:out value="${result.progrmFileNm}"/>'); return false;"><c:out value="${result.progrmFileNm}"/></a></span>
						    </td>
						    <td nowrap="nowrap"><c:out value="${result.progrmKoreanNm}"/></td>
						    <td nowrap="nowrap"><c:out value="${result.URL}"/></td>
						    <td nowrap="nowrap"><c:out value="${result.progrmDc}"/></td>  
	                      </tr>
	                     </c:forEach>     
	                    </tbody>
	                    </table>
	                </div>

	                <!-- 페이지 네비게이션 시작 -->
	                <div class="paging">
	                    <ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="linkPage" />
	                </div>
	                <!-- //페이지 네비게이션 끝 -->
	
					<input type="hidden" name="cmd">
					<input type="hidden" name="tmp_progrmNm">
	            </form>

            </div>
            <!-- //contents 끝 -->    
        </div>
        <!-- //contentsWrap 끝 -->
	</div>  
    <!-- //container 끝 -->
</div>
<!-- //전체 레이어 끝 -->
 </body>
</html>