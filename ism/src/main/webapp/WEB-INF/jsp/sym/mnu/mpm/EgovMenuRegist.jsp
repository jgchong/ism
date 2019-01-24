<%@ page contentType="text/html; charset=utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:url var="ImgUrl" value="/images"/>

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

<style type="text/css">
    h1 {font-size:12px;}
    caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="menuManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 입력처리 함수
 ******************************************************** */
function insertMenuManage(form) {
    if(!validateMenuManageVO(form)){            
        return;
    }else{
    	if(confirm("<spring:message code="common.save.msg" />")){
         form.action="<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>";
         form.submit();
    	}
    }
}

/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
    document.all.tmp_SearchElementName.value = "progrmFileNm";
    window.open("<c:url value='/sym/prm/EgovProgramListSearch.do'/>",'','width=800,height=600');
}

/* ********************************************************
 * 목록조회  함수
 ******************************************************** */
function selectList(){
    location.href = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
}
/* ********************************************************
 * 파일명 엔터key 목록조회  함수
 ******************************************************** */
function press() {
    if (event.keyCode==13) {
        searchFileNm();    // 원래 검색 function 호출
    }
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
                <p class="layerTit">메뉴등록</p>

                <form:form commandName="menuManageVO" name="menuManageVO" method="post" action="${pageContext.request.contextPath}/sym/mnu/mpm/EgovMenuRegistInsert.do">

                    <div class="layerTb" >
                        <table summary="메뉴 등록화면">
						  <tr> 
						    <th width="15%" height="23" class="required_text" scope="row"><label for="menuNo">메뉴번호</label><img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="35%" nowrap="nowrap">
						      &nbsp;
						      <form:input path="menuNo" size="10" maxlength="10" title="메뉴No"/>
						      <form:errors path="menuNo" />
						    </td>
						    <th width="15%" height="23" class="required_text" scope="row"><label for="menuOrdr">메뉴순서</label><img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="35%" nowrap="nowrap">
						      &nbsp;
						      <form:input path="menuOrdr" size="10"  maxlength="10" title="메뉴순서" />
						      <form:errors path="menuOrdr" />
						    </td>
						  </tr>  
						  <tr> 
						    <th width="15%" height="23" class="required_text" scope="row"><label for="menuNm">메뉴명</label><img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="35%" nowrap="nowrap">
						      &nbsp;
						      <form:input path="menuNm" size="30"  maxlength="30" title="메뉴명" />
						      <form:errors path="menuNm" />
						    </td>
						    <th width="15%" height="23" class="required_text" scope="row"><label for="upperMenuId">상위메뉴번호</label><img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="35%" nowrap="nowrap">
						      &nbsp;
						      <form:input path="upperMenuId" size="10"  maxlength="10" title="상위메뉴No"/>
						      <form:errors path="upperMenuId" />
						    </td>
						  </tr>
						  <tr> 
						    <th width="15%" height="23" class="required_text" scope="row"><label for="progrmFileNm">프로그램파일명</label><img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="85%"  colspan="3" nowrap="nowrap">
						        &nbsp;
						        <input type="text" name="progrmFileNm_view" size="60" disabled="disabled">
						        <form:input path="progrmFileNm" size="60"  maxlength="60" title="프로그램파일명" cssStyle="display:none"/>
						        <form:errors path="progrmFileNm" />
						        <a href="<c:url value='/sym/prm/EgovProgramListSearch.do'/>?tmp_SearchElementName=progrmFileNm" target="_blank" title="새창으로" onclick="javascript:searchFileNm(); return false;" style="selector-dummy:expression(this.hideFocus=false);" >
						        <img src="<c:url value='/images/img_search.gif' />" alt='프로그램파일명 검색' width="15" height="15" />(프로그램파일명 검색)</a>
						    </td>
						  </tr>
						  <tr> 
						    <th width="15%" height="23" class="required_text" scope="row"><label for="relateImageNm">관련이미지명</label></th>
						    <td width="35%" nowrap="nowrap">
						          &nbsp;
						          <form:input path="relateImageNm" size="30"  maxlength="30" title="관련이미지명"/>
						          <form:errors path="relateImageNm" />
						    </td>
						    <th width="15%" height="23" class="required_text" scope="row"><label for="relateImagePath">관련이미지경로</label></th>
						    <td width="35%" nowrap="nowrap">
						          &nbsp;
						          <form:input path="relateImagePath" size="30"  maxlength="30" title="관련이미지경로"/>
						          <form:errors path="relateImagePath" />
						    </td>
						  </tr>
						  <tr> 
						    <th width="15%" height="23" class="required_text" scope="row"><label for="menuDc">메뉴설명</label></th>
						    <td colspan="3" nowrap="nowrap">&nbsp;
						      <form:textarea path="menuDc" rows="14" cols="75" cssClass="txaClass" title="메뉴설명"/>
						      <form:errors path="menuDc"/>
						    </td>
						  </tr> 
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>" class="cancel" onclick="javascript:selectList(); return false;">목록</a>
						<a href="#LINK" class="confirm" onclick="javascript:insertMenuManage(document.getElementById('menuManageVO')); return false;"><spring:message code="button.save" /></a>
					</p>
                    <!-- 버튼 [e] -->

                    <!-- 검색조건 유지 -->
					<input type="hidden" name="tmp_SearchElementName" value="">
					<input type="hidden" name="tmp_SearchElementVal" value="">
					<input name="cmd" type="hidden" value="<c:out value='insert'/>">
                    <!-- 검색조건 유지 -->
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

