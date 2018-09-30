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
function fnIdCheck(){
    var retVal;
    var url = "<c:url value='/uss/umt/cmm/EgovIdDplctCnfirmView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.userManageVO.emplyrId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.userManageVO.emplyrId.value = retVal;
        document.userManageVO.id_view.value = retVal;
    }
}
function showModalDialogCallback(retVal) {
    if(retVal) {
        document.userManageVO.emplyrId.value = retVal;
        document.userManageVO.id_view.value = retVal;
    }
}
function fnListPage(){
    document.userManageVO.action = "<c:url value='/uss/umt/user/EgovUserManage.do'/>"; 
    document.userManageVO.submit();
}
function fnInsert(){
    if(validateUserManageVO(document.userManageVO)){
        if(document.userManageVO.password.value != document.userManageVO.password2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return;
        }
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

/*
if (typeof(opener.fn_egov_dn_info_setting) == 'undefined') {
    alert('메인 화면이 변경되거나 없습니다');
    this.close();
} else {
    opener.fn_egov_dn_info_setting(dn);
    this.close();
}
*/

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
                <p class="layerTit">사용자등록</p>
                
		        <form:form commandName="userManageVO" action="${pageContext.request.contextPath}/uss/umt/user/EgovUserInsert.do" name="userManageVO" method="post" >
		            <!-- 우편번호검색 -->
		            <input type="hidden" name="zip_url" value="<c:url value='/sym/cmm/EgovCcmZipSearchPopup.do'/>" />

                    <div class="layerTb" > 
				        <table>
				        	<tr>
				        		<th width="20%" height="23">이미지</th>
				                <td width="80%" colspan="3">
				                	<div>
				                		<div class="img_wrap">
				                			<img id="userphoto" src="data:image/png;base64,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"/>
				                		</div>
				                	</div>
				                	<label for="imgfile" class="imgfile">사진업로드</label>
				                	<input type="file" id="imgfile" name="imgfile" class="hidden"/>
				                	<input type="hidden" id="userphotosrc" name="userphotosrc"/>
				                </td>
				        	</tr>
				            <tr> 
				                <th width="20%" height="23" class="required_text">사용자아이디
				                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
				                </th>
				                <td width="30%" >
				                    <input type="text" size="20" maxlength="20" disabled="disabled" id="id_view" name="id_view" >
				                    <form:input path="emplyrId" id="emplyrId" title="사용자아이디" size="20" maxlength="20" cssStyle="display:none" />
				                    <a href="#LINK" onclick="fnIdCheck();">
				                        <img src="/images/img_search.gif" alt=""/>(중복아이디 검색)
				                    </a>
				                    <form:errors path="emplyrId" cssClass="error"/>                    
				                </td>
                                <th width="20%" height="23" class="required_text">핸드폰번호&nbsp;&nbsp;</th>
                                <td width="30%" >
                                    <form:input path="moblphonNo" id="moblphonNo" title="핸드폰번호" cssClass="txaIpt" size="20" maxlength="15" />
                                    <form:errors path="moblphonNo" cssClass="error" />
                                </td>
				            </tr>

				            <tr> 
				                <th width="20%" height="23" class="required_text"  >비밀번호
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:password path="password" id="password" title="비밀번호" size="20" maxlength="20" />
				                    <form:errors path="password" cssClass="error" />
				                </td>
				                <th width="20%" height="23" class="required_text"  >비밀번호확인
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <input name="password2" id="password2" title="비밀번호확인" type="password" size="20" maxlength="20" />
				                </td>
				            </tr>

				            <tr> 
				                <th width="20%" height="23" class="required_text"  >비밀번호힌트
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="passwordHint" id="passwordHint" title="비밀번호힌트">
				                        <form:option value="" label="--선택하세요--"/>
				                        <form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
				                    </form:select>
				                    <form:errors path="passwordHint" cssClass="error"/>
				                </td>
				                <th width="20%" height="23" class="required_text" >비밀번호정답
                                <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:input path="passwordCnsr" id="passwordCnsr" title="비밀번호정답" cssClass="txaIpt" size="50" maxlength="100" />
				                    <form:errors path="passwordCnsr" cssClass="error"/>
				                </td>
				            </tr>

				            <tr> 
                                <th width="20%" height="23" class="required_text">이름
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
                                <td width="30%" >
                                    <input name="emplyrNm" id="emplyrNm" title="사용자이름" type="text" size="20" value="" maxlength="60" />
                                    <form:errors path="emplyrNm" cssClass="error" />
                                </td>
				                <th width="20%" height="23" class="required_text">소속기관</th>
				                <td width="30%" >
				                    <form:select path="insttCode" id="insttCode" title="소속기관">
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
                                    <form:select path="orgnztId" id="orgnztId" title="부서">
                                        <form:option value="" label="--선택하세요--"/>
                                        <form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
                                    </form:select>
                                    <form:errors path="orgnztId" cssClass="error"/>
                                </td>
				                <th width="20%" height="23" class="required_text"  >직위명</th>
				                <td width="30%" >
				                    <form:input path="ofcpsNm" id="ofcpsNm" title="직위명" cssClass="txaIpt" size="20" maxlength="30" />
				                    <form:errors path="ofcpsNm" cssClass="error" />
				                </td>
 				            </tr>

				            <tr>
                                <th width="20%" height="23" class="required_text">이메일주소</th>
                                <td width="30%">
                                    <form:input path="emailAdres" id="emailAdres" title="이메일주소" cssClass="txaIpt" size="20" maxlength="50" />
                                    <form:errors path="emailAdres" cssClass="error" />
                                </td>
                                <th width="20%" height="23" class="required_text">집전화지역번호
                                </th>
                                <td width="30%" >
                                    <form:input path="areaNo" id="areaNo" title="areaNo" cssClass="txaIpt" size="5" maxlength="5" />
                                    - <form:input path="homemiddleTelno" title="homemiddleTelno" id="homemiddleTelno" cssClass="txaIpt" size="5" maxlength="5" />
                                    - <form:input path="homeendTelno" title="homeendTelno" id="homeendTelno" cssClass="txaIpt" size="5" maxlength="5" />
                                    <form:errors path="areaNo" cssClass="error" />
                                    <form:errors path="homemiddleTelno" cssClass="error" />
                                    <form:errors path="homeendTelno" cssClass="error" />
                                </td>
				            </tr>

				            <tr> 
				                <th width="20%" height="23" class="required_text">사무실전화번호</th>
				                <td width="30%" >
				                    <form:input path="offmTelno" id="offmTelno" title="사무실전화번호" cssClass="txaIpt" size="20" maxlength="15" />
				                    <form:errors path="offmTelno" cssClass="error" />
				                </td>
				                <th width="20%" height="23" class="required_text">팩스번호</th>
				                <td width="30%" >
				                    <form:input path="fxnum" id="fxnum" cssClass="txaIpt" title="팩스번호" size="20" maxlength="15" />
				                    <form:errors path="fxnum" cssClass="error" />
				                </td>
				            </tr>

				            <tr>
				                <th width="20%" height="23" class="required_text">주소</th>
				                <td width="30%" >
				                    <form:input path="homeadres" id="homeadres" title="주소" cssClass="txaIpt" size="40" maxlength="100" />
				                    <form:errors path="homeadres" cssClass="error" />
                                    <form:hidden path="zip" />
                                    <form:errors path="zip" cssClass="error" />
                                    <input name="zip_view" id="zip_view" type="hidden" title="우편번호" size="20" value="<c:out value='${userManageVO.zip}'/>"  maxlength="8" readonly="readonly" />
				                </td>
				                <th width="20%" height="23" class="required_text"  >상세주소</th>
				                <td width="30%" >
				                    <form:input path="detailAdres" id="detailAdres" title="상세주소" cssClass="txaIpt" size="40" maxlength="50" />
				                    <form:errors path="detailAdres" cssClass="error" />
				                </td>
				            </tr>

				            <tr> 
                                <th width="20%" height="23" class="required_text">사용자상태코드
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
                                <td width="30%" >
                                    <form:select path="emplyrSttusCode" id="emplyrSttusCode" title="사용자상태코드">
                                        <form:option value="" label="--선택하세요--"/>
                                        <form:options items="${emplyrSttusCode_result}" itemValue="code" itemLabel="codeNm"/>
                                    </form:select>
                                    <form:errors path="emplyrSttusCode" cssClass="error"/>
                                </td>
				                <th width="20%" height="23" class="required_text">그룹아이디
                                    <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                                </th>
				                <td width="30%" >
				                    <form:select path="groupId" id="groupId" title="그룹아이디">
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
						<a href="#LINK" onclick="JavaScript:fnInsert(); return fallse;" class="confirm"><spring:message code="button.save" /></a>
						<a href="<c:url value='/uss/umt/user/EgovUserManage.do'/>" class="cancel" onclick="fnListPage(); return false;"><spring:message code="button.list" /></a>
						<!-- <a href="#LINK" onclick="javascript:document.userManageVO.reset();" class="cancel" ><spring:message code="button.reset" /></a> -->
					</p>
                    <!-- 버튼 [e] -->                         

			        <!-- 검색조건 유지 -->
			        <input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
			        <input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
			        <input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
			        <input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/><c:if test="${userSearchVO.pageIndex eq null}">1</c:if>"/>
			        
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
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#userphoto').attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}
</script>

