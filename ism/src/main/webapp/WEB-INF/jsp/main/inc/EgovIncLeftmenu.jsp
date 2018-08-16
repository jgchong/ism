
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/EgovMainMenu.js"/>"></script>
<script type="text/javascript">
<!--
/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
function fn_MovePage(nodeNum) {
    var nodeValues = treeNodes[nodeNum].split("|");
    //parent.main_right.location.href = nodeValues[5];
    document.menuListForm.action = "${pageContext.request.contextPath}"+nodeValues[5];
    //alert(document.menuListForm.action);
    document.menuListForm.submit();
}
//-->
</script>
<!-- 메뉴 시작 -->

<div class="userArea">
<%
LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
if(loginVO == null){
%>
	<h1 class="logo">넷케이티아이</h1>
	<p class="user">
		<span class="img">로그인정보 없음</span>
		<strong>
			<a href="<c:url value='/uat/uia/egovLoginUsr.do'/>"><img src="<c:url value='/images/leftmenu/login.jpg' />" alt="로그인" /></a>
			로그인후 사용하십시오.
		</strong>
	</p>
</div>
<%
}else{
%>
	<h1 class="logo">넷케이티아이</h1>
	<p class="user" onclick="javascript:location.href='<c:url value='/uss/umt/user/EgovUserSelectUpdtView.do'/>'" style="cursor:pointer;">
		<span class="img">
<c:if test="${userOneSelect.userphotosrc eq null}">
			<img src="/images/custom/user_male.png" alt=""/>
</c:if>
<c:if test="${userOneSelect.userphotosrc ne null}">
			<img src="${userOneSelect.userphotosrc}" alt=""/>
</c:if>
		</span>
		<strong>
			<c:set var="loginName" value="<%= loginVO.getName()%>"/>
			이름 : <c:out value="${loginName}"/>
			<span>직급 : 대리 / 부서 : 회계팀</span>
		</strong>
	</p>
</div>
<div style="text-align:center;padding-top:10px;padding-bottom:10px;border-bottom:1px solid #38373d;">
<a href="<c:url value='/uat/uia/actionLogout.do'/>"><img src="<c:url value='/images/leftmenu/logout.jpg' />" alt="로그아웃" /></a>
</div>
<ul class="menu">
	<li><a href="<c:url value='/uat/uia/actionMain.do'/>">DASHBOARD</a></li>
     <script type="text/javascript">
     <!--
         var Tree = new Array;
         if(document.menuListForm.tmp_menuNm != null){
             for (var j = 0; j < document.menuListForm.tmp_menuNm.length; j++) {
                 Tree[j] = document.menuListForm.tmp_menuNm[j].value;
             }
         }
         createTree(Tree, true, document.getElementById("baseMenuNo").value);
     //-->
     </script>
</ul>
<%
}
%>
<!-- //메뉴 끝 -->