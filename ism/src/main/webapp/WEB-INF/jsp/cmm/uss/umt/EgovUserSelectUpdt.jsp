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
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title> KTI NMS </title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/EgovZipPopup.js' />" ></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnListPage(){
    document.userManageVO.action = "<c:url value='/uss/umt/user/EgovUserManage.do'/>";
    document.userManageVO.submit();
}
function fnDeleteUser(checkedIds) {
    if(confirm("<spring:message code="common.delete.msg" />")){
        document.userManageVO.checkedIdForDel.value=checkedIds;
        document.userManageVO.action = "<c:url value='/uss/umt/user/EgovUserDelete.do'/>";
        document.userManageVO.submit(); 
    }
}
function fnPasswordMove(){
    document.userManageVO.action = "<c:url value='/uss/umt/user/EgovUserPasswordUpdtView.do'/>";
    document.userManageVO.submit();
}
function fnUpdate(){
    if(validateUserManageVO(document.userManageVO)){
        $("#userphotosrc").val($("#userphoto").attr("src"));
        document.userManageVO.submit();
    }
}
function fn_egov_inqire_cert() {
    var url = '/uat/uia/EgovGpkiRegist.do';
    var popupwidth = '500';
    var popupheight = '400';
    var title = '인증서';

    Top = (window.screen.height - popupheight) / 3;
    Left = (window.screen.width - popupwidth) / 2;
    if (Top < 0) Top = 0;
    if (Left < 0) Left = 0;
    Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no, scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
    PopUpWindow = window.open(url, title, Future)
    PopUpWindow.focus();
}

function fn_egov_dn_info_setting(dn) {
    var frm = document.userManageVO;
    
    frm.subDn.value = dn;
}
<c:if test="${!empty resultMsg}">
	alert("<spring:message code="${resultMsg}" />");
</c:if>
//-->
</script>

<style type="text/css">
table td label.imgfile {
    padding: 3px 12px;
    border: 0;
    background: #45b6b6;
    color: #fff;
    font-size: 14px;
}
table td input.hidden {
    display: none;
}
.img_wrap {
	width:300px;
	margin-top:50px;
}
.img_wrap img {
	max-width: 100px;
}
</style>
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
                <p class="layerTit">업무사용자 상세조회(수정)</p>
                
		        <form:form commandName="userManageVO" action="${pageContext.request.contextPath}/uss/umt/user/EgovUserSelectUpdt.do" name="userManageVO" method="post" >
		        
			        <!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
			        <input name="checkedIdForDel" type="hidden" />
			        <!-- 검색조건 유지 -->
			        <input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
			        <input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
			        <input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
			        <input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
			        <!-- 우편번호검색 -->
			        <input type="hidden" name="zip_url" value="<c:url value='/sym/cmm/EgovCcmZipSearchPopup.do'/>" />
			        <!-- 사용자유형정보 : password 수정화면으로 이동시 타겟 유형정보 확인용, 만약검색조건으로 유형이 포함될경우 혼란을 피하기위해 userTy명칭을 쓰지 않음-->
			        <input type="hidden" name="userTyForPassword" value="<c:out value='${userManageVO.userTy}'/>" />

                    <div class="layerTb" >
                        <table>
                            <tr> 
                                <th width="20%" height="23" class="required_text"  >사진</th>
				                <td width="80%" colspan="3">
				                	<div>
				                		<div class="img_wrap">
				                			<img id="userphoto" src="${userManageVO.userphotosrc}"/>
				                		</div>
				                	</div>
				                	<label for="imgfile" class="imgfile">사진업로드</label>
				                	<input type="file" id="imgfile" name="imgfile" class="hidden"/>
				                	<input type="hidden" id="userphotosrc" name="userphotosrc"/>
				                </td>
                            </tr>
                            <tr> 
                                <th width="20%" height="23" class="required_text"  >사용자아이디
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:input path="emplyrId" id="emplyrId" cssClass="txaIpt" size="20" maxlength="20" readonly="readonly" />
				                    <form:errors path="emplyrId" cssClass="error"/>
				                    <form:hidden path="uniqId" />
				                </td>
				                <th width="20%" height="23" class="required_text"  >핸드폰번호&nbsp;&nbsp;</th>
				                <td width="30%" >
				                    <form:input path="moblphonNo" id="moblphonNo" cssClass="txaIpt" size="20" maxlength="15" />
				                    <form:errors path="moblphonNo" cssClass="error" />
				                </td>
                            </tr>
                            <tr> 
                                <th width="20%" height="23" class="required_text"  >비밀번호힌트
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="passwordHint" id="passwordHint">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="passwordHint" cssClass="error"/>
				                </td>
                                <th width="20%" height="23" class="required_text" >비밀번호정답
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:input path="passwordCnsr" id="passwordCnsr" cssClass="txaIpt" size="50" maxlength="100" />
				                    <form:errors path="passwordCnsr" cssClass="error"/>
				                </td>
                            </tr>

                            <tr> 
                                <th width="20%" height="23" class="required_text">이름
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:input path="emplyrNm" id="emplyrNm" cssClass="txaIpt" size="20"  maxlength="60" />
				                    <form:errors path="emplyrNm" cssClass="error" />
				                </td>
                                <th width="20%" height="23" class="required_text">소속기관&nbsp;&nbsp;</th>
				                <td width="30%" >
				                    <form:select path="insttCode" id="insttCode">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${insttCode_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="insttCode" cssClass="error"/>
				                </td>
                            </tr>

                            <tr> 
                                <th width="20%" height="23" class="required_text">부서
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="orgnztId" id="orgnztId">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="orgnztId" cssClass="error"/>
				                </td>
                                <th width="20%" height="23" class="required_text"  >직위명&nbsp;&nbsp;</th>
				                <td width="30%" >
				                    <form:input path="ofcpsNm" id="ofcpsNm" cssClass="txaIpt" size="20" maxlength="30" />
				                    <form:errors path="ofcpsNm" cssClass="error" />
				                </td>
                            </tr>

                            <tr>
                                <th width="20%" height="23" class="required_text" >이메일주소
                                </th>
				                <td width="30%" >
				                    <form:input path="emailAdres" id="emailAdres" cssClass="txaIpt" size="20" maxlength="50" />
				                    <form:errors path="emailAdres" cssClass="error" />
				                </td>
                                <th width="20%" height="23" class="required_text"  >집전화지역번호
                                </th>
				                <td width="30%" >
				                    <form:input path="areaNo" id="areaNo" cssClass="txaIpt" size="4" maxlength="4" />
				                    - <form:input path="homemiddleTelno" id="homemiddleTelno" cssClass="txaIpt" size="4" maxlength="4" />
				                    - <form:input path="homeendTelno" id="homeendTelno" cssClass="txaIpt" size="4" maxlength="4" />
				                    <form:errors path="areaNo" cssClass="error" />
				                    <form:errors path="homemiddleTelno" cssClass="error" />
				                    <form:errors path="homeendTelno" cssClass="error" />
				                </td>
                            </tr>

                            <tr> 
                                <th width="20%" height="23" class="required_text"  >사무실전화번호</th>
				                <td width="30%" >
				                    <form:input path="offmTelno" id="offmTelno" cssClass="txaIpt" size="20" maxlength="15" />
				                    <form:errors path="offmTelno" cssClass="error" />
				                </td>
                                <th width="20%" height="23" class="required_text"  >팩스번호</th>
				                <td width="30%" >
				                    <form:input path="fxnum" id="fxnum" cssClass="txaIpt" size="20" maxlength="15" />
				                    <form:errors path="fxnum" cssClass="error" />
				                </td>
                            </tr>

                            <tr>
                                <th width="20%" height="23" class="required_text"  >주소
                                </th>
				                <td width="30%" >
				                    <form:input path="homeadres" id="homeadres" cssClass="txaIpt" size="40" maxlength="100" readonly="readonly"/>
				                    <form:errors path="homeadres" cssClass="error" />
				                    <input name="zip_view" id="zip_view" type="hidden" size="20" value="<c:out value='${userManageVO.zip}'/>"  maxlength="8" readonly="readonly"/>
				                    <form:hidden path="zip" />
				                    <form:errors path="zip" cssClass="error" />
                                </td>
                                <th width="20%" height="23" class="required_text"  >상세주소&nbsp;&nbsp;</th>
				                <td width="30%" >
				                    <form:input path="detailAdres" id="detailAdres" cssClass="txaIpt" size="40" maxlength="50" />
				                    <form:errors path="detailAdres" cssClass="error" />
				                </td>
                            </tr>

                            <tr> 
                                <th width="20%" height="23" class="required_text"  > 사용자상태코드
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="emplyrSttusCode" id="emplyrSttusCode">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${emplyrSttusCode_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="emplyrSttusCode" cssClass="error"/>
				                </td>
                                <th width="20%" height="23" class="required_text"  >권한그룹
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="groupId" id="groupId">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="groupId" cssClass="error"/>
				                </td>
                            </tr>
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
						<a href="#LINK" onclick="JavaScript:fnUpdate(); return false;" class="confirm"><spring:message code="button.save" /></a>
<c:if test="${userSearchVO.searchCondition ne 'ONLY'}">
						<a href="<c:url value='/uss/umt/user/EgovUserDelete.do'/>" class="cancel" onclick="fnDeleteUser('<c:out value='${userManageVO.userTy}'/>:<c:out value='${userManageVO.uniqId}'/>'); return false;"><spring:message code="button.delete" /></a>
						<a href="<c:url value='/uss/umt/user/EgovUserManage.do'/>" class="cancel" onclick="fnListPage(); return false;"><spring:message code="button.list" /></a>
</c:if>
						<a href="<c:url value='/uss/umt/user/EgovUserPasswordUpdtView.do'/>" onclick="fnPasswordMove(); return false;" class="confirm"><spring:message code="button.passwordUpdate" /></a>
                        <!-- <a href="#LINK" onclick="javascript:document.userManageVO.reset();" class="cancel" ><spring:message code="button.reset" /></a> -->
					</p>
                    <!-- 버튼 [e] -->
                    <form:hidden path="password" />                       
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


<script type="text/javascript">
$(document).ready(function() {
	$('#imgfile').on("change",handleImgFileSelect);
});

function handleImgFileSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f) {
		if (!f.type.match("image.*")) {
			alert("이미지만 가능합니다.");
			return;
		}
		//alert(f.size);
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#userphoto').attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}
</script>
