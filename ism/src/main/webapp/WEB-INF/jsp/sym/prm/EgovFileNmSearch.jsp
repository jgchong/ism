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

body { overflow: auto; }
.contents {
    padding: 30px 30px 50px;
    min-height: 600px;
    background: #fff;
    border-radius: 4px;
    margin: 0;
}
	</style>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script language="javascript1.2"  type="text/javaScript"> 
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListSearch.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */ 
function selectProgramListSearch() { 
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListSearch.do'/>";
	document.progrmManageForm.submit();
}

/* ********************************************************
 * 프로그램목록 선택 처리 함수
 ******************************************************** */ 
function choisProgramListSearch(vFileNm) { 
	eval("opener.document.all."+opener.document.all.tmp_SearchElementName.value).value = vFileNm;
	if(opener.document.all.progrmFileNm_view){
		opener.document.all.progrmFileNm_view.value = vFileNm;
	}
    window.close();
}
//-->
</script>
</head>
<body>
            <div class="contents">
            	<h2 class="pageTit">프로그램파일명 검색</h2>
            	
				<form name="progrmManageForm" action ="<c:url value='/sym/prm/EgovProgramListSearch.do'/>" method="post">
					<input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				
				    <!-- 검색 필드 박스 시작 -->
				    <div class="searchArea">
				    	<input class="it ml30" id="searchKeyword" name="searchKeyword" type="text" size="30" value=""  maxlength="60" placeholder="프로그램파일명">
				        <a href="#LINK" onclick="javascript:selectProgramListSearch(); return false;">조회 </a>
				    </div>
				    <!-- //검색 필드 박스 끝 -->
				
				    <div id="page_info"><div id="page_info_align"></div></div>                    
				    <!-- table add start -->
				    <div class="listTb">
				        <table width="100%" summary="프로그램파일명 검색목록으로 프로그램파일명 프로그램명으로 구성됨">
				            <caption>프로그램파일명 검색</caption>
				            <colgroup>
				            <col width="50%" >
				            <col width="50%" >  
				            </colgroup>
				            <thead>
				            <tr>
				                <th scope="col" class="f_field" nowrap="nowrap">프로그램파일명</th>
				                <th scope="col" nowrap="nowrap">프로그램명</th>
				            </tr>
				            </thead>
				            <tbody>                 
				            
				            <c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
				            <!-- loop 시작 -->                                
				              <tr>
							    <td nowrap="nowrap">
							        <span class="link"><a href="#LINK" onclick="choisProgramListSearch('<c:out value="${result.progrmFileNm}"/>'); return false;">
							      <c:out value="${result.progrmFileNm}"/></a></span></td>
							    <td nowrap="nowrap"><c:out value="${result.progrmKoreanNm}"/></td>
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
</body>
</html>

