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
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title> KTI NMS </title>
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
<validator:javascript formName="progrmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 입력 처리 함수
 ******************************************************** */
function insertProgramListManage(form) {
    if(confirm("<spring:message code="common.save.msg" />")){
        if(!validateProgrmManageVO(form)){          
            return;
        }else{
            form.action="<c:url value='/sym/prm/EgovProgramListRegist.do'/>";
            form.submit();
        }
    }
}
/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function selectList(){
    location.href = "<c:url value='/sym/prm/EgovProgramListManageSelect.do' />";
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
                <p class="layerTit">프로그램목록 등록</p>

                <form:form commandName="progrmManageVO"  action="/sym/prm/EgovProgramListRegist.do" method="post" >

                    <div class="layerTb" >
                        <table summary="프로그램목록 등록">
                          <tr> 
						    <th width="20%" height="23" class="required_text" scope="row"><label for="F1">프로그램파일명</label>
                            <img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="80%" nowrap="nowrap">
						      &nbsp; 
						      <form:input path="progrmFileNm" size="50"  maxlength="50" id="F1" title="프로그램파일명"/>
						      <form:errors path="progrmFileNm" />
						    </td>
						  </tr> 
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row"><label for="progrmStrePath">저장경로</label>
                            <img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="80%" nowrap="nowrap">
						      &nbsp; <form:input path="progrmStrePath"  size="60"   maxlength="60" title="저장경로"/> 
						      <form:errors path="progrmStrePath" />
						    </td>
						  </tr>
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row"><label for="progrmKoreanNm">프로그램 한글명</label>
                            <img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="80%" nowrap="nowrap">
						      &nbsp; <form:input path="progrmKoreanNm" size="60"  maxlength="50" title="프로그램 한글명"/> 
						      <form:errors path="progrmKoreanNm"/>
						    </td>
						  </tr>
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row"><label for="URL">URL</label>
                            <img src="${ImgUrl}/required.gif" width="15" height="15" alt="필수"></th>
						    <td width="80%" nowrap="nowrap">
						      &nbsp; <form:input path="URL" size="60"    maxlength="60" title="URL"/> 
						      <form:errors path="URL"/>
						    </td>
						  </tr>
						  <tr> 
						    <th height="23" class="required_text" scope="row"><label for="progrmDc">프로그램설명</label></th>
						    <td>&nbsp;
						      <form:textarea path="progrmDc" rows="14" cols="75" cssClass="txaClass" title="프로그램설명"/>
						      <form:errors path="progrmDc"/>
						    </td>
						  </tr> 
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>" class="cancel" onclick="selectList(); return false;"><spring:message code="button.list" /></a>
						<a href="#LINK" class="confirm" onclick="javascript:insertProgramListManage(document.getElementById('progrmManageVO')); return false;"><spring:message code="button.save" /></a>
					</p>
                    <!-- 버튼 [e] -->

                    <!-- 검색조건 유지 -->
                    <input name="cmd" type="hidden" value="<c:out value='insert'/>"/>
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

