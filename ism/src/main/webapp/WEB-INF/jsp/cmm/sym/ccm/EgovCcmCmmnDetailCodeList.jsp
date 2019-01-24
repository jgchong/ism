<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
	
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

<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do'/>";
    document.listForm.submit();
}
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fnSearch(){
    document.listForm.pageIndex.value = 1;
    document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fnRegist(){
    location.href = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeRegist.do'/>";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(){
    location.href = "";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fnDetail(codeId, code){
    var varForm              = document.all["Form"];
    varForm.action           = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeDetail.do'/>";
    varForm.codeId.value     = codeId;
    varForm.code.value       = code;
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
    // 
}
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
            	<h2 class="pageTit">공통상세코드 목록</h2>
	            <form name="listForm" action="<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do'/>" method="post">
	            	<input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
	
	                <!-- 검색 필드 박스 시작 -->
	                <div class="searchArea">
				        <select name="searchCondition" class="select" title="searchCondition">
				           <option selected value=''>--선택하세요--</option>
				           <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>코드ID</option>
				           <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>코드</option>
				           <option value='3' <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>>코드명</option>
				        </select>
				        <input class="it ml30" name="searchKeyword" type="text" size="35" value="${searchVO.searchKeyword}"  maxlength="35" id="searchKeyword">

                        <a href="#noscript" onclick="fnSearch(); return false;" style="margin-left:-4px;">조회</a>
						<a href="#noscript" onclick="fnRegist(); return false;">등록</a>
	                </div>
	                <!-- //검색 필드 박스 끝 -->
	                <div id="page_info"><div id="page_info_align"></div></div>                    
	                <!-- table add start -->
	                <div class="listTb">
	                    <table summary="코드ID, 코드, 코드명, 사용여부를 나타내는 공통상세코드 목록 테이블이다." cellpadding="0" cellspacing="0">
	                    <caption>공통상세코드 목록</caption>
	                    <colgroup>
	                    <col width="10%" >
	                    <col width="20%" >  
	                    <col width="30%" >
	                    <col width="30%" >
	                    <col width="10%" >
	                    </colgroup>
	                    <thead>
	                    <tr>
	                        <th scope="col" class="f_field" nowrap="nowrap">순번</th>
	                        <th scope="col" nowrap="nowrap">코드ID</th>
	                        <th scope="col" nowrap="nowrap">코드</th>
	                        <th scope="col" nowrap="nowrap">코드명</th>
	                        <th scope="col" nowrap="nowrap">사용여부</th>
	                    </tr>
	                    </thead>
	                    <tbody>                 
	
	                    <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	                    <!-- loop 시작 -->                                
							<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fnDetail('${resultInfo.codeId}','${resultInfo.code}');">
							    <td nowrap="nowrap"><strong><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></strong></td>
							    <td nowrap="nowrap">${resultInfo.codeId}</td>
							    <td nowrap="nowrap">${resultInfo.code}</td>
							    <td nowrap="nowrap">${resultInfo.codeNm}</td>
							    <td nowrap="nowrap"><c:if test="${resultInfo.useAt == 'Y'}">사용</c:if><c:if test="${resultInfo.useAt == 'N'}">미사용</c:if></td>
							</tr>   
	                    </c:forEach>     
	                    <c:if test="${fn:length(resultList) == 0}">
	                        <tr> 
	                            <td colspan=5>
	                                <spring:message code="common.nodata.msg" />
	                            </td>
	                        </tr>                                              
	                    </c:if>
	                    </tbody>
	                    </table>
	                </div>

	                <!-- 페이지 네비게이션 시작 -->
	                <div class="paging">
	                    <ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="linkPage" />
	                </div>
	                <!-- //페이지 네비게이션 끝 -->
	                
	                <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	            </form>
            
	            <form name="Form" action="" method="post" >
	                <input type=hidden name="codeId">
	                <input type=hidden name="code">
	                <input type="submit" style="width: 0px;height: 0px;visibility: hidden;"/>
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