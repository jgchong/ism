<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmnCode(){
    location.href = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_CmmnCode(form){
    if(confirm("<spring:message code='common.save.msg'/>")){
        if(!validateCmmnCode(form)){            
            return;
        }else{
            form.submit();
        }
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
                <p class="layerTit">공통코드등록</p>

                <form:form commandName="cmmnCode" name="cmmnCode" method="post">

	                <div class="layerTb" >
	                    <table summary="분류코드, 코드ID, 코드ID명, 코드ID설명, 사용여부를 입력하는 공통코드 등록 테이블이다.">
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="clCode">분류코드</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td width="80%" nowrap colspan="3">
						        <select name="clCode" class="select" id="clCode">
						            <c:forEach var="result" items="${cmmnClCode}" varStatus="status">
						                <option value='<c:out value="${result.clCode}"/>'><c:out value="${result.clCodeNm}"/></option>
						            </c:forEach>                       
						        </select>
						    </td>
						  </tr> 
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="codeId">코드ID</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td width="80%" nowrap colspan="3">
						      <form:input  path="codeId" size="6" maxlength="6" id="codeId"/>
						      <form:errors path="codeId"/>
						    </td>
						  </tr> 
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="codeIdNm">코드ID명</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td width="80%" nowrap="nowrap">
						      <form:input  path="codeIdNm" size="60" maxlength="60" id="codeIdNm"/>
						      <form:errors path="codeIdNm"/>
						    </td>    
						  </tr> 
						  <tr> 
						    <th height="23" class="required_text" scope="row" ><label for="codeIdDc">코드ID설명</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>
						      <form:textarea path="codeIdDc" cols="100" rows="5" id="codeIdDc"/>
						      <form:errors   path="codeIdDc"/>
						    </td>
						  </tr> 
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="useAt">사용여부</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td width="30%" nowrap class="title_left" colspan="3">
						      <form:select path="useAt" id="useAt">
						          <form:option value="Y" label="Yes"/>
						          <form:option value="N" label="No"/>
						      </form:select>
						    </td>    
						  </tr>     
	                    </table>
	                </div>
	
					<!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="#noscript" class="cancel" onclick="fn_egov_list_CmmnCode(); return false;">목록</a>
						<a href="#noscript" class="confirm" onclick="fn_egov_regist_CmmnCode(document.cmmnCode); return false;">저장</a>
					</p>
	                <!-- 버튼 [e] -->
	
	                <!-- 검색조건 유지 -->
	                <input name="cmd" type="hidden" value="<c:out value='save'/>"/>
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

