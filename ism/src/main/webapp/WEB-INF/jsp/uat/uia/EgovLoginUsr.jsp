<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
		
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
<script type="text/javascript">
<!--
function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
        return false;
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
        return false;
<c:if test="${isArrowIP eq 'false'}">
    } else if (document.loginForm.ihidNum.value =="") {
        alert("인증번호를 입력하세요");
        return false;
</c:if>
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionSecurityLogin.do'/>";
        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
        document.loginForm.submit();
    }
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "="
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset)
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length
            return unescape(document.cookie.substring(offset, end))
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
    //form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        //alert(message);
    }
    getid(document.loginForm);
}
//-->
</script>
</head>

<body class="loginBody" onload="fnInit();">

	<div class="login">
		<p class="tit">
			<%--<img src="<c:url value='/'/>images/custom/logo.png" alt=""/>--%>
			<span style="font-size:40px; padding:0; color:#1f3d74; font-weight:900;">netKTI EDAS</span><br/>
			<span>로그인</span>
		</p>
		<form:form id="loginForm" name="loginForm" method="post">
			<fieldset id="" class="">
				<p><input type="text" title="" value="" placeHolder="아이디" id="id" name="id" maxlength="20"/></p>
				<p><input type="password" title="" value="" placeHolder="패스워드" id="password" name="password"  
						maxlength="20" onkeydown="javascript:if (event.keyCode == 13) { actionLogin(); }"/></p>
<c:if test="${isArrowIP eq 'false'}">
				<p><button type="button" onclick="javascript:sendMail();">이메일인증</button></p>
				<p><input type="text" title="" value="" placeHolder="인증번호입력" id="ihidNum" name="ihidNum" maxlength="20"/></p>
</c:if>
				<p><button onclick="javascript:actionLogin()">sign-in</button></p>
			</fieldset>
                            <input type="hidden" name="message" value="${message}" />
                            <input type="hidden" name="userSe"  value="USR"/>
                            <input name="j_username" type="hidden"/>
		</form:form>
		<!--<div id="msg" style="text-align:center;margin-top:10px;">${message}</div>-->
	</div>
</body>
</html>
<!-- jgc add id:mailv1 메일 send -->
<script type="text/javascript">
//jgc add id:mailv1 메일 send
function sendMail() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
        return false;
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
        return false;
    }

	var params = "uid="+document.loginForm.id.value+"&pwd="+document.loginForm.password.value;
	
	$.ajax({
		type: "POST",
		url: "/emailCheck.do",
		data: params,
		success: function(data) {
			//$('#msg').text(data);
			alert(data)
		},
		error: function(xhr, option, error) {
			alert(xhr.status);
			alert(error)
			//$('#msg').text(error);
		}
	});
}
</script>