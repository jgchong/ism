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
<script type="text/javaScript">
<!--
/* ********************************************************
 * 최초조회 함수
 ******************************************************** */
function fMenuCreatManageSelect(){ 
    document.menuCreatManageForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
    document.menuCreatManageForm.pageIndex.value = pageNo;
    document.menuCreatManageForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectMenuCreatManageList() { 
    document.menuCreatManageForm.pageIndex.value = 1;
    document.menuCreatManageForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 메뉴생성 화면 호출
 ******************************************************** */
function selectMenuCreat(vAuthorCode) {
    document.menuCreatManageForm.authorCode.value = vAuthorCode;
    document.menuCreatManageForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatSelect.do'/>";
    window.open("#", "_menuCreat", "scrollbars = yes, top=100px, left=100px, height=700px, width=850px");    
    document.menuCreatManageForm.target = "_menuCreat";
    document.menuCreatManageForm.submit();  
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
//-->
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
				<h2 class="pageTit">메뉴생성관리</h2>
	            <form name="menuCreatManageForm" action ="<c:url value='/sym/mpm/EgovMenuCreatManageSelect.do'/>" method="post">
	            	
	            	<input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
	
	                <!-- 검색 필드 박스 시작 -->
	                <div class="searchArea">
						<input name="checkedMenuNoForDel" type="hidden" />
						<input name="authorCode"          type="hidden" />
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

						<input class="it ml40" id="searchKeyword" name="searchKeyword" type="text" size="80" value=""  maxlength="60" placeholder="보안설정대상사용자ID"/> 

                        <a href="#LINK" onclick="javascript:selectMenuCreatManageList(); return false;">조회 </a>
	                </div>
	                <!-- //검색 필드 박스 끝 -->
	                <div id="page_info"><div id="page_info_align"></div></div>                    
	                <!-- table add start -->
	                <div class="listTb">
	                    <table summary="메뉴생성관리  목록화면으로 권한코드, 권한명, 권한설명, 메뉴생성여부, 메뉴생성으로 구성됨" cellpadding="0" cellspacing="0">
	                    <caption>메뉴생성관리 목록</caption>
	                    <colgroup>
	                    <col width="20%" >
	                    <col width="20%" >  
	                    <col width="20%" >
	                    <col width="20%" >
	                    <col width="20%" >
	                    </colgroup>
	                    <thead>
	                    <tr>
	                        <th scope="col" class="f_field" nowrap="nowrap">권한코드</th>
	                        <th scope="col" nowrap="nowrap">권한명</th>
	                        <th scope="col" nowrap="nowrap">권한 설명</th>
	                        <th scope="col" nowrap="nowrap">메뉴생성여부</th>
	                        <th scope="col" nowrap="nowrap">메뉴생성</th>
	                    </tr>
	                    </thead>
	                    <tbody>                 
	
	                    <c:forEach var="result" items="${list_menumanage}" varStatus="status">
	                    <!-- loop 시작 -->                                
	                      <tr>
						    <td nowrap="nowrap"  ><c:out value="${result.authorCode}"/></td>
						    <td nowrap="nowrap"  ><c:out value="${result.authorNm}"/></td>
						    <td nowrap="nowrap"  ><c:out value="${result.authorDc}"/></td>
						    <td nowrap="nowrap"  >
						          <c:if test="${result.chkYeoBu > 0}">Y</c:if>
						          <c:if test="${result.chkYeoBu == 0}">N</c:if>
						    </td>
						    <td nowrap="nowrap" >
	                            <a href="<c:url value='/sym/mnu/mcm/EgovMenuCreatSelect.do'/>?authorCode='<c:out value="${result.authorCode}"/>'"  onclick="selectMenuCreat('<c:out value="${result.authorCode}"/>'); return false;">메뉴생성</a>
						    </td>
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
	
	                <input type="hidden" name="req_menuNo">
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