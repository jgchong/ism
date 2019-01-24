<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

	</style>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnCheckAll() {
    var checkField = document.listForm.checkField;
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
function fnDeleteUser() {
    var checkField = document.listForm.checkField;
    var id = document.listForm.checkId;
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length > 0) {
        //alert(checkedIds);
        if(confirm("<spring:message code="common.delete.msg" />")){
            document.listForm.checkedIdForDel.value=checkedIds;
            document.listForm.action = "<c:url value='/uss/umt/user/EgovUserDelete.do'/>";
            document.listForm.submit();
        }
    }
}
function fnSelectUser(id) {
    document.listForm.selectedId.value = id;
    array = id.split(":");
    if(array[0] == "") {
    } else {
        userTy = array[0];
        userId = array[1];    
    }
    document.listForm.selectedId.value = userId;
    document.listForm.action = "<c:url value='/uss/umt/user/EgovUserSelectUpdtView.do'/>";
    document.listForm.submit();
      
}
function fnAddUserView() {
    document.listForm.action = "<c:url value='/uss/umt/user/EgovUserInsertView.do'/>";
    document.listForm.submit();
}
function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/umt/user/EgovUserManage.do'/>";
    document.listForm.submit();
}
function fnSearch(){
    document.listForm.pageIndex.value = 1;
    document.listForm.action = "<c:url value='/uss/umt/user/EgovUserManage.do'/>";
    document.listForm.submit();
}
function fnViewCheck(){ 
    if(insert_msg.style.visibility == 'hidden'){
        insert_msg.style.visibility = 'visible';
    }else{
        insert_msg.style.visibility = 'hidden';
    }
}
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
//-->
</script>

</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->

<div class="wrap">
	<c:import url="/sym/mms/EgovMainMenuHead.do" />
	<div class="container">
	    <!-- 좌측메뉴 시작 -->
	    <div class="lnb">
	    	<c:import url="/sym/mms/EgovMainMenuLeft.do" />
	    </div>
	    <!-- //좌측메뉴 끝 -->
		<div class="contentsWrap">
            <!-- 현재위치 네비게이션 시작 -->
            <div class="contents">
				<h2 class="pageTit">사용자목록</h2>
                <form name="listForm" action="<c:url value='/uss/umt/user/EgovUserManage.do'/>" method="post">
                <!-- <input type="submit" id="invisible" class="invisible"/> -->
		        <input name="selectedId" type="hidden" />
		        <input name="checkedIdForDel" type="hidden" />
		        <input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
                <!-- 검색 필드 시작 -->
                <div class="searchArea">
                <select name="sbscrbSttus" id="sbscrbSttus" title="검색조건1-사용자상태" class="ml30">
                    <option value="0" <c:if test="${empty userSearchVO.sbscrbSttus || userSearchVO.sbscrbSttus == '0'}">selected="selected"</c:if> >상태(전체)</option>
                    <option value="A" <c:if test="${userSearchVO.sbscrbSttus == 'A'}">selected="selected"</c:if> >가입신청</option>
                    <option value="D" <c:if test="${userSearchVO.sbscrbSttus == 'D'}">selected="selected"</c:if> >삭제</option>
                    <option value="P" <c:if test="${userSearchVO.sbscrbSttus == 'P'}">selected="selected"</c:if> >승인</option>
                </select>
                <select name="searchCondition" id="searchCondition" title="검색조건2-검색어구분" class="ml30">
                    <option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if> >ID</option>
                    <option value="1" <c:if test="${empty userSearchVO.searchCondition || userSearchVO.searchCondition == '1'}">selected="selected"</c:if> >Name</option>
                </select>
                <input name="searchKeyword" title="검색어" type="text" value="<c:out value="${userSearchVO.searchKeyword}"/>" class="it ml30"/>

                <a href="<c:url value='/uss/umt/user/EgovUserManage.do'/>" onclick="javascript:fnSearch(); return false;" style="margin-left:-4px;">조회</a>
                <a href="#LINK" onclick="javascript:fnDeleteUser(); return false;"><spring:message code="button.delete" /></a>
                <a href="<c:url value='/uss/umt/user/EgovUserInsertView.do'/>" onclick="fnAddUserView(); return false;"><spring:message code="button.create" /></a>
                </div>
                <!-- 검색 필드 끝 -->
                <!-- table add start -->
                <div class="listTb">
                    <table summary="사용자 목록을 제공한다." cellpadding="0" cellspacing="0">
                    <caption>사용자목록</caption>
					<colgroup>
						<col width="3%"/><col width="3%"/><col width="10%"/>
						<col width="10%"/><col width="8%"/><col width="13%"/>
						<col width="8%"/><col width="8%"/><col width="*"/>
						<col width="5%"/><col width="10%"/><col width="12%"/>
					</colgroup>
                    <thead>
                    <tr>
                        <th scope="col" nowrap="nowrap"><strong>No.</strong></th>
                        <th scope="col" nowrap="nowrap"><input name="checkAll" type="checkbox" title="Check All" onclick="javascript:fnCheckAll();"/></th>
                        <th scope="col" nowrap="nowrap">아이디</th>
                        <th scope="col" nowrap="nowrap">소속기관</th>
                        <th scope="col" nowrap="nowrap">부서</th>
                        <th scope="col" nowrap="nowrap">직위</th>
                        <th scope="col" nowrap="nowrap">사용자이름</th>
                        <th scope="col" nowrap="nowrap">사용자이메일</th>
                        <th scope="col" nowrap="nowrap">핸드폰번호</th>
                        <th scope="col" nowrap="nowrap">등록일</th>
                        <th scope="col" nowrap="nowrap">가입상태</th>
                    </tr>
                    </thead>
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
                    <tr>
	                    <td nowrap="nowrap"><strong><c:out value="${status.count}"/></strong></td>
	                    <td nowrap="nowrap">
	                        <input name="checkField" title="Check <c:out value="${status.count}"/>" type="checkbox"/>
	                        <input name="checkId" type="hidden" value="<c:out value='${result.userTy}'/>:<c:out value='${result.uniqId}'/>"/>
	                    </td>
	                    <td nowrap style="cursor:pointer;cursor:hand" >
	                        <span class="link"><a href="<c:url value='/uss/umt/user/EgovUserSelectUpdtView.do'/>?selectedId=<c:out value="${result.uniqId}"/>"  onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>'); return false;"><c:out value="${result.userId}"/></a></span>
	                    </td>
	                    <td nowrap="nowrap"><c:out value="${result.insttCodenm}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.orgnztnm}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.ofcpsnm}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.userNm}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.emailAdres}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.moblphonNo}"/></td>
	                    <td nowrap="nowrap"><c:out value="${result.sbscrbDe}"/></td>
	                    <td nowrap="nowrap">
	                        <c:forEach var="emplyrSttusCode_result" items="${emplyrSttusCode_result}" varStatus="status">
	                            <c:if test="${result.sttus == emplyrSttusCode_result.code}"><c:out value="${emplyrSttusCode_result.codeNm}"/></c:if>
	                        </c:forEach>
	                    </td>
                     </tr>
                     </c:forEach>     
                     </tbody>
                     </table>
                </div>
                <!-- 페이지 네비게이션 시작 -->
                <div class="paging">
                    <ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="fnLinkPage" />
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