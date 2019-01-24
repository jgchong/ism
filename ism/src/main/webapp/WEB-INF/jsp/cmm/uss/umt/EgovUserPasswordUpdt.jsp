<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Language" content="ko" >
		
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnListPage(){
    document.passwordChgVO.action = "<c:url value='/uss/umt/user/EgovUserManage.do'/>";
    document.passwordChgVO.submit();
}
function fnUpdate(){
    if(validatePasswordChgVO(document.passwordChgVO)){
        if(document.passwordChgVO.newPassword.value != document.passwordChgVO.newPassword2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return;
        }
        document.passwordChgVO.submit();
    }
}
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
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
                <p class="layerTit">업무사용자 암호변경</p>

		        <form name="passwordChgVO" method="post" action="${pageContext.request.contextPath}/uss/umt/user/EgovUserPasswordUpdt.do" >
		            <input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
		            <!-- onsubmit="javascript:return FormValidation(document.passwordChgVO);" >  -->
			        <!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
			        <input name="checkedIdForDel" type="hidden" />
			        <!-- 검색조건 유지 -->
			        <input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
			        <input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
			        <input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
			        <input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
			        <!-- 우편번호검색 -->
			        <input type="hidden" name="url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />

                    <div class="layerTb" >
        
				        <table>
				            <tr>
				                <th width="20%" height="23" class="required_text" nowrap >사용자아이디</th>
				                <td width="80%" nowrap="nowrap">
				                    <input name="emplyrId" id="emplyrId" title="사용자아이디" type="text" size="20" value="<c:out value='${userManageVO.emplyrId}'/>"  maxlength="20" readonly="readonly"/>
				                    <input name="uniqId" id="uniqId" title="uniqId" type="hidden" size="20" value="<c:out value='${userManageVO.uniqId}'/>"/>
				                    <input name="userTy" id="userTy" title="userTy" type="hidden" size="20" value="<c:out value='${userManageVO.userTy}'/>"/>
				                </td>
				            </tr>
				            <tr> 
				                <th width="20%" height="23" class="required_text" nowrap >기존 비밀번호</th>
				                <td width="80%" nowrap="nowrap"><input name="oldPassword" id="oldPassword" title="기존 비밀번호" type="password" size="20" value=""  maxlength="100" /></td>
				            </tr>
				            <tr> 
				                <th width="20%" height="23" class="required_text" nowrap >비밀번호</th>
				                <td width="80%" nowrap="nowrap"><input name="newPassword" id="newPassword" title="비밀번호" type="password" size="20" value=""  maxlength="100" /></td>
				            </tr>
				            <tr>
				                <th width="20%" height="23" class="required_text" nowrap >비밀번호확인</th>
				                <td width="80%" nowrap="nowrap"><input name="newPassword2" id="newPassword2" title="비밀번호확인" type="password" size="20" value=""  maxlength="100" /></td>
				            </tr>
				        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="#LINK" onclick="JavaScript:fnUpdate(); return false;" class="confirm"><spring:message code="button.save" /></a>
						<a href="<c:url value='/uss/umt/user/EgovUserManage.do'/>" class="cancel" onclick="fnListPage(); return false;"><spring:message code="button.list" /></a>
					</p>
                    <!-- 버튼 [e] -->
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

