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
		
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
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
    location.href = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do'/>";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fnModify(){
    var varForm              = document.all["Form"];
    varForm.action           = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeModify.do'/>";
    varForm.codeId.value     = "${result.codeId}";
    varForm.code.value       = "${result.code}";
    varForm.cmd.value        = "Modify";
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
    if (confirm("<spring:message code='common.delete.msg'/>")) {
        var varForm              = document.all["Form"];
        varForm.action           = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeRemove.do'/>";
        varForm.codeId.value     = "${result.codeId}";
        varForm.code.value       = "${result.code}";
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
                <p class="layerTit">공통상세코드 상세조회</p>

                <form name="Form" method="post" action="<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do'/>">
                    <input type="submit" id="invisible" style="width: 0px;height: 0px;visibility: hidden;"/>
				    <input type="hidden" name="codeId">
				    <input type="hidden" name="code">

                    <div class="layerTb" >
                        <table summary="코드ID명, 코드, 코드명, 코드설명, 사용여부가 나타나 있는 공통상세코드 상세조회 테이블이다.">
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드ID명<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>${result.codeIdNm}</td>
						  </tr>
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td>${result.code}</td>    
						  </tr> 
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드명<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td>${result.codeNm}</td>    
						  </tr> 
						  <tr> 
						    <th height="23" class="required_text" scope="row" ><label for="codeDc">순서</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td><textarea class="textarea"  cols="75" rows="14"  style="width:450px;" id="codeDc">${result.codeDc}</textarea></td>
						  </tr> 
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="useAt">사용여부</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>
						        <select name="useAt" id="useAt">
						            <option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Yes</option>
						            <option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >No</option>               
						        </select>
						    </td>    
						  </tr>     
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="#noscript" class="confirm" onclick="fnModify(); return false;">수정</a>
<c:if test="${result.useAt == 'Y'}">
						<a href="#noscript" class="confirm" onclick="fnDelete(); return false;">삭제</a>
</c:if>
						<a href="#noscript" class="cancel" onclick="fnList(); return false;">목록</a>
					</p>
                    <!-- 버튼 [e] -->

                    <!-- 검색조건 유지 -->
                    <input name="cmd" type="hidden" value="<c:out value='save'/>"/>
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

