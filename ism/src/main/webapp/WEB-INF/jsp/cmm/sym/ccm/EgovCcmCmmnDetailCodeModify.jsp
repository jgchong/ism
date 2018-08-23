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
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title> KTI NMS </title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnDetailCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmnDetailCode(){
    location.href = "<c:url value='/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CmmnDetailCode(form){
    if(confirm("<spring:message code='common.save.msg'/>")){
        if(!validateCmmnDetailCode(form)){          
            return;
        }else{
            form.cmd.value = "Modify";
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
                <p class="layerTit">공통상세코드 수정</p>

				<form:form commandName="cmmnDetailCode" name="cmmnDetailCode" method="post">
					<input name="cmd" type="hidden" value="Modify">
					<form:hidden path="codeId"/>
					<form:hidden path="code"/>

                    <div class="layerTb" >
                        <table summary="코드명, 코드설명, 사용여부를 수정할 수 있는 공통상세코드 수정 테이블이다.">
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드ID<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td width="80%" nowrap colspan="3">
						        ${cmmnDetailCode.codeIdNm}
						    </td>
						  </tr> 
						  <tr> 
						    <th width="20%" height="23" class="required_text" scope="row" nowrap >코드<img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td width="80%" nowrap="nowrap"><c:out value='${cmmnDetailCode.code}'/></td>
						  </tr>
						  <tr>
						    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="codeNm">코드명</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>          
						    <td width="80%" nowrap="nowrap">
						      <form:input  path="codeNm" size="60" maxlength="60" id="codeNm"/>
						      <form:errors path="codeNm"/>
						    </td>    
						  </tr> 
						  <tr> 
						    <th height="23" class="required_text" scope="row" ><label for="codeDc">순서</label><img src="${ImgUrl}/required.gif" alt="필수"  width="15" height="15"></th>
						    <td>
						      <form:textarea path="codeDc" rows="3" cols="60" id="codeDc"/>
						      <form:errors   path="codeDc"/>
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
						<a href="#noscript" class="cancel" onclick="fn_egov_list_CmmnDetailCode(); return false;">목록</a>
						<a href="#noscript" class="confirm" onclick="fn_egov_modify_CmmnDetailCode(document.cmmnDetailCode); return false;">저장</a>
					</p>
                    <!-- 버튼 [e] -->                    

                    <!-- 검색조건 유지 -->
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

