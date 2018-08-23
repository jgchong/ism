<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnList(){
    location.href = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeList.do'/>";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fnModify(){
    var varForm              = document.all["Form"];
    varForm.action           = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeModify.do'/>";
    varForm.codeId.value     = "${result.codeId}";
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
    if (confirm("<spring:message code='common.delete.msg'/>")) {
        var varForm              = document.all["Form"];
        varForm.action           = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeRemove.do'/>";
        varForm.codeId.value     = "${result.codeId}";
        varForm.submit();
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
                <p class="layerTit">공통코드 상세조회</p>
                
                <form name="Form" method="post" action="<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeModify.do'/>">
                	<input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>

                    <div class="layerTb" >
                        <table summary="분류코드명, 코드ID, 코드ID명, 코드ID설명, 사용여부를 보여주는 공통코드 상세조회 페이지이다.">
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >분류코드명<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>${result.clCodeNm}</td>
						  </tr>
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드ID<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>${result.codeId}</td>
						  </tr>
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드ID명<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td>${result.codeIdNm}</td>    
						  </tr> 
						  <tr> 
						    <th height="23" class="required_text" scope="row" ><label for="codeIdDc">코드ID설명</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td><textarea class="textarea"  cols="100" rows="5" disabled="disabled" id="codeIdDc">${result.codeIdDc}</textarea></td>
						  </tr> 
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="useAt">사용여부</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>
						        <select name="useAt" disabled id="useAt">
						            <option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Yes</option>
						            <option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >No</option>               
						        </select>
						    </td>    
						  </tr>     
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="#LINK" class="confirm" onclick="fnModify(); return false;"><spring:message code="button.update" /></a>
<c:if test="${result.useAt == 'Y'}">
						<a href="#LINK" class="confirm" onclick="javascript:fnDelete(); return false;"><spring:message code="button.delete" /></a>
</c:if>
						<a href="#noscript" class="cancel" onclick="fnList(); return false;">목록</a>
					</p>
                    <!-- 버튼 [e] -->

                    <!-- 검색조건 유지 -->
                    <input name="codeId" type="hidden">
                    <!-- 검색조건 유지 -->
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

