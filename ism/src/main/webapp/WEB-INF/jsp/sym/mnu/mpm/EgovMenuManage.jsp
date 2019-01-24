<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/sym/mpm/icon";
  String imagePath_button = "/images/egovframework/sym/mpm/button";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
		
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
	<style type="text/css">
div.searchArea		{ display:block; text-align:right; margin:0 0 20px; }
div.searchArea a	{ background:#457cac; padding:10px 15px; color:#fff; font-size:14px; text-decoration:none; }
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
    var checkField = document.menuManageForm.checkField;
    if(document.menuManageForm.checkAll.checked) {
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
function fDeleteMenuList() {
    var checkField = document.menuManageForm.checkField;
    var menuNo = document.menuManageForm.checkMenuNo;
    var checkMenuNos = "";
    var checkedCount = 0;
    if(checkField) {

        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuNos += ((checkedCount==0? "" : ",") + menuNo[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkMenuNos = menuNo.value;
            }
        }
    }   

    document.menuManageForm.checkedMenuNoForDel.value=checkMenuNos;
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageListDelete.do'/>";
    document.menuManageForm.submit(); 
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//  document.menuManageForm.searchKeyword.value = 
    document.menuManageForm.pageIndex.value = pageNo;
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
    document.menuManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectMenuManageList() { 
    document.menuManageForm.pageIndex.value = 1;
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
    document.menuManageForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertMenuManage() {
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>";
    document.menuManageForm.submit();   
}

/* ********************************************************
 * 일괄처리 화면호출 함수
 ******************************************************** */
/* function bndeInsertMenuManage() {
        document.menuManageForm.action = "<c:url value='/sym/mpm/EgovMenuRegistInsert.do'/>";
        document.menuManageForm.submit();   
    }
 */
function bndeInsertMenuManage() {
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuBndeRegist.do'/>";
    document.menuManageForm.submit();
} 
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtMenuManageDetail(menuNo) {
    document.menuManageForm.req_menuNo.value = menuNo;
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageListDetailSelect.do'/>";
    document.menuManageForm.submit();   
}
/* ********************************************************
 * 최초조회 함수
 ******************************************************** */
function fMenuManageSelect(){ 
    document.menuManageForm.action = "<c:url value='/sym/mpm/EgovMenuManageSelect.do'/>";
    document.menuManageForm.submit();
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
            	<h2 class="pageTit">메뉴목록관리</h2>
            	
	            <form name="menuManageForm" action ="<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>" method="post">
	            	<input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
	                <!-- 검색 필드 박스 시작 -->
	                <div class="searchArea">
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
						<input name="checkedMenuNoForDel" type="hidden" />
						<input name="req_menuNo" type="hidden"  />

                        <input class="it ml30" id="searchKeyword" name="searchKeyword" type="text" size="80" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="60" placeholder="메뉴명"/>

                        <a href="#LINK" onclick="javascript:selectMenuManageList(); return false;" style="margin-left:-4px;">조회</a>
                        <a href="<c:url value='/sym/mpm/EgovMenuRegistInsert.do'/>" onclick="insertMenuManage(); return false;"><spring:message code="button.create" /></a>
                        <a href="#LINK" onclick="fDeleteMenuList(); return false;"><spring:message code="button.delete" /></a>
	                </div>
	                <!-- //검색 필드 박스 끝 -->
	                <div id="page_info"><div id="page_info_align"></div></div>                    
	                <!-- table add start -->
	                <div class="listTb">
	                    <table summary="메뉴관리 목록 조회화면으로 메뉴ID,메뉴한글명,프로그램파일명,메뉴설명,상위메뉴ID로 구성." cellpadding="0" cellspacing="0">
	                    <caption>메뉴관리 목록</caption>
	                    <colgroup>
	                        <col width="4%" >
	                        <col width="13%" >  
	                        <col width="20%" >
	                        <col width="20%" >
	                        <col width="30%" >
	                        <col width="13%" >
	                    </colgroup>
	                    <thead>
	                    <tr>
	                        <th scope="col" class="f_field" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fCheckAll();" title="전체선택"/></th>
	                        <th scope="col" nowrap="nowrap">메뉴번호</th>
	                        <th scope="col" nowrap="nowrap">메뉴명</th>
	                        <th scope="col" nowrap="nowrap">프로그램파일명</th>
	                        <th scope="col" nowrap="nowrap">메뉴설명</th>
	                        <th scope="col" nowrap="nowrap">상위메뉴번호</th>
	                    </tr>
	                    </thead>
	                    <tbody>                 
	
	                    <c:forEach var="result" items="${list_menumanage}" varStatus="status">
	                    <!-- loop 시작 -->                                
	                      <tr>
						    <td nowrap="nowrap">
						       <input type="checkbox" name="checkField" class="check2" title="선택"/>
						       <input name="checkMenuNo" type="hidden" value="<c:out value='${result.menuNo}'/>"/>
						    </td>
						    <td nowrap="nowrap"><c:out value="${result.menuNo}"/></td>
						    <td nowrap style="cursor:hand;">
						       <span class="link"><a href="<c:url value='/sym/mpm/EgovMenuManageListDetailSelect.do?req_menuNo='/>${result.menuNo}" onclick="selectUpdtMenuManageDetail('<c:out value="${result.menuNo}"/>'); return false;"><c:out value="${result.menuNm}"/></a></span>
						    </td>
						    <td nowrap="nowrap"><c:out value="${result.progrmFileNm}"/></td>
						    <td nowrap="nowrap"><c:out value="${result.menuDc}"/></td>  
						    <td nowrap="nowrap"><c:out value="${result.upperMenuId}"/></td>  
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