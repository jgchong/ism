<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
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

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkCount++;
                    checkField[i].value = checkId[i].value;

                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  그룹이 없습니다.");
                returnBoolean = false;
            }
        } else {
             if(document.listForm.delYn.checked == false) {
                alert("선택된 그룹이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.groupIds.value = returnValue;

    return returnBoolean;
}

function fncSelectGroupList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    document.listForm.submit();
}

function fncSelectGroup(groupId) {
    document.listForm.groupId.value = groupId;
    document.listForm.action = "<c:url value='/sec/gmt/EgovGroup.do'/>";
    document.listForm.submit();
}

function fncAddGroupInsert() {
    location.replace("<c:url value='/sec/gmt/EgovGroupInsertView.do'/>");
}

function fncGroupListDelete() {
    if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/sec/gmt/EgovGroupListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
        fncSelectGroupList('1');
    }
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
            <form:form id="listForm" name="listForm" action="<c:url value='/sec/gmt/EgovAuthorList.do'/>" method="post">
				<h2 class="pageTit">그룹 관리</h2>

                <!-- 검색 필드 박스 시작 -->
                <div class="searchArea">
                    <input class="it ml30" id="searchKeyword" name="searchKeyword" type="text" value="<c:out value='${groupManageVO.searchKeyword}'/>" size="25" placeholder="그룹 명" onkeypress="press();" />
                    <a href="#LINK" onclick="javascript:fncSelectGroupList('1')" style="selector-dummy:expression(this.hideFocus=false);">조회 </a>
                    <a href="#LINK" onclick="javascript:fncAddGroupInsert()" style="selector-dummy:expression(this.hideFocus=false);">등록</a>
                    <a href="#LINK" onclick="javascript:fncGroupListDelete()" style="selector-dummy:expression(this.hideFocus=false);">삭제</a>
                </div>
                <!-- //검색 필드 박스 끝 -->
                <div id="page_info"><div id="page_info_align"></div></div>
                <!-- table add start -->
                <div class="listTb">
                    <table summary="그룹 관리에 관한 테이블입니다.그룹 ID,그룹 명,설명,등록일자의 정보를 담고 있습니다." cellpadding="0" cellspacing="0">
                    <caption>그룹 관리</caption>
                    <colgroup>
                        <col width="3%" >
                        <col width="12%" >
                        <col width="25%" >
                        <col width="40%" >
                        <col width="15%" >
                        <col width="5%" >
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col" class="f_field" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
                        <th scope="col" nowrap="nowrap">그룹 ID</th>
                        <th scope="col" nowrap="nowrap">그룹 명</th>
                        <th scope="col" nowrap="nowrap">설명</th>
                        <th scope="col" nowrap="nowrap">등록일자</th>
                        <th scope="col" nowrap="nowrap"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="group" items="${groupList}" varStatus="status">
                    <!-- loop 시작 -->
                      <tr>
					    <td nowrap="nowrap"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${group.groupId}"/>" /></td>
					    <td nowrap="nowrap"><a href="#LINK" onclick="javascript:fncSelectGroup('<c:out value="${group.groupId}"/>')"><c:out value="${group.groupId}"/></a></td>
					    <td nowrap="nowrap"><c:out value="${group.groupNm}"/></td>
					    <td nowrap="nowrap"><c:out value="${group.groupDc}"/></td>
					    <td nowrap="nowrap"><c:out value="${group.groupCreatDe}"/></td>
					    <td nowrap="nowrap"><a href="#LINK" onclick="javascript:fncSelectGroup('<c:out value="${group.groupId}"/>')"><img src="<c:url value='/images/img_search.gif'/>" width="15" height="15" align="middle" alt="상세조회"></a></td>
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
	                
				<input type="hidden" name="groupId"/>
				<input type="hidden" name="groupIds"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
				<input type="hidden" name="searchCondition"/>
            </form:form>

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