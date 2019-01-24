<%--
  Class Name : EgovIdDplctCnfirm.jsp
  Description : 아이디중복확인
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.03   JJY              최초 생성
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 JJY
    since    : 2009.03.03
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ID중복확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<link rel="stylesheet" href="<c:url value='/css/common.css'/>" type="text/css">
<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
<base target="_self">
<script type="text/javascript" src="<c:url value='/js/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript">
<!--
function fnCheckId(){
	if(document.checkForm.checkId.value==""){
		alert("중복조회할 아이디를 입력하십시오.");
		document.checkForm.focus();
        return;
	}
	if(fnCheckNotKorean(document.checkForm.checkId.value)){
		document.checkForm.submit();
    }else{
    	alert("한글은 사용할 수 없습니다.");
        return;
    }
}
function fnReturnId(){
	var retVal="";
    if (document.checkForm.usedCnt.value == 0){
	    retVal = document.checkForm.resultId.value;
	    setReturnValue(retVal);
	    window.returnValue = retVal; 
        window.close();
    }else if (document.checkForm.usedCnt.value == 1){
        alert("이미사용중인 아이디입니다.");
        return;
    }else{
    	alert("먼저 중복확인을 실행하십시오");
        return;
    }
}
function fnClose(){
    var retVal="";
    window.returnValue = retVal; 
    window.close();
}
function fnCheckNotKorean(koreanStr){                  
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) { 
        }else{
            //hangul finding....
            return false;
        }
    }
    return true;
}
//-->
</script>

<style type="text/css">
   	a.bt	{ display:inline-block; width:80px; padding:10px 0; font-size:14px; text-align:center; text-decoration:none; background:#45b6b6; color:#fff; float:right; }
   	.buttons        { width:100%; text-align:center; padding:30px 0 0; overflow:hidden; }
   	.buttons a      { width:80px; height:20px; float:none; display:inline-block; line-height:22px; border:0; }
    .buttons a.cancel		{ background:#666; color:#fff; }
    .buttons a.confirm		{ background:#457cac; color:#fff; }

</style>

</head>
<body>
<div class="layerTb">
    <form name="checkForm" action ="<c:url value='/uss/umt/cmm/EgovIdDplctCnfirm.do'/>" style="width:290px; margin:0 auto;">
    <input type="submit" id="invisible" class="invisible"/>

    <table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
        <tr>
            <td style="font-size:14px; font-weight:bold;">아이디 중복확인</td>
        </tr>
        <tr>
            <td style="padding:10px 0;">
                <div style="width:205px; float:left;">
                    <input type="hidden" name="resultId" value="<c:out value="${checkId}"/>" />
                    <input type="hidden" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
                    <input type="text" name="checkId" title="선택여부" value="<c:out value="${checkId}"/>" maxlength="20" style="width:calc(100% - 2px); border:1px solid #999; height:34px; line-height:34px; text-indent:10px;" />
                </div>
                <a href="#LINK" onclick="javascript:fnCheckId(); return false;" class="bt"><spring:message code="button.inquire" /></a>
	        </td>
	    </tr>
	    <tr>     
            <td>
                <c:choose>
                <c:when test="${usedCnt eq -1}">
                    중복확인을 실행하십시오
                </c:when>
                <c:when test="${usedCnt eq 0}">
                    <span style="color:green;">${checkId} 는 사용가능한 아이디입니다.</span>
                </c:when>
                <c:otherwise>
                    <span style="color:red;">${checkId} 는 사용할수 없는 아이디입니다.</span>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <!-- 버튼 시작(상세지정 style로 div에 지정) -->
    <div class="buttons">
	    <a href="#LINK" onclick="javascript:fnReturnId(); return false;" class="confirm"><spring:message code="button.use" /></a>
	    <a href="#LINK" onclick="javascript:fnClose(); return false;" class="cancel"><spring:message code="button.close" /></a>
    </div>
    </form>
</div>
</body>
</html>