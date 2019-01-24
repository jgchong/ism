<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="registerFlag" value="${empty groupManageVO.groupId ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty groupManageVO.groupId ? '그룹 등록' : '그룹 수정'}"/>

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
<validator:javascript formName="groupManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectGroupList() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    varFrom.submit();       
}

function fncGroupInsert() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateGroupManage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
}

function fncGroupUpdate() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateGroupManage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
}

function fncGroupDelete() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupDelete.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

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
                <p class="layerTit">사용자그룹관리</p>
                <form:form commandName="groupManage" method="post" >

                    <div class="layerTb" >
                        <table summary="그룹을 수정하는 테이블입니다.그룹 ID,그룹 명,설명,등록일자 정보를 담고 있습니다.">
                          <tr>
                            <th class="required_text" width="25%" scope="row"  nowrap="nowrap">그룹 ID
                            </th>
                            <td nowrap="nowrap"><input name="groupId" id="groupId" type="text" value="<c:out value='${groupManage.groupId}'/>" size="40" title="그룹 ID"/></td>
                          </tr>
                          <tr>    
                            <th class="required_text" width="25%" scope="row"  nowrap="nowrap">그룹 명
                                <img src="<c:url value='/'/>images/required.gif" width="15" height="15" alt="필수" />
                            </th>
                            <td nowrap="nowrap"><input name="groupNm" id="groupNm" type="text" value="<c:out value='${groupManage.groupNm}'/>" title="그룹명" maxLength="50" size="40" />&nbsp;<form:errors path="groupNm" /></td>
                          </tr>
                          <tr>    
                            <th class="required_text" width="20%" scope="row"  nowrap="nowrap">설명</th>
                            <td nowrap="nowrap"><input name="groupDc" id="groupDc" type="text" value="<c:out value='${groupManage.groupDc}'/>" title="설명" maxLength="50" size="50" /></td>
                          </tr>
                          <tr>    
                            <th class="required_text" width="20%" scope="row"  nowrap="nowrap">등록일자</th>
                            <td nowrap="nowrap"><input name="groupCreatDe" id="groupCreatDe" type="text" value="<c:out value='${groupManage.groupCreatDe}'/>" title="등록일자" maxLength="50" size="20" readonly="readonly"/></td>
                          </tr>
                        </table>
                    </div>

                    <!-- 버튼 [s] -->   
					<p class="layerFootBt">
<c:if test="${registerFlag == 'INSERT'}">
						<a href="#LINK" onclick="javascript:fncSelectGroupList()" class="cancel" style="selector-dummy:expression(this.hideFocus=false);">목록</a>
                        <a href="#LINK" onclick="javascript:fncGroupInsert()" class="confirm" style="selector-dummy:expression(this.hideFocus=false);">저장</a>  
</c:if>
<c:if test="${registerFlag == 'UPDATE'}">
                        <a href="#LINK" onclick="javascript:fncGroupUpdate()" class="confirm" style="selector-dummy:expression(this.hideFocus=false);">저장</a>  
                        <a href="#LINK" onclick="javascript:fncGroupDelete()" class="cancel" style="selector-dummy:expression(this.hideFocus=false);">삭제</a>   
</c:if>
					</p>
                    <!-- 버튼 [e] -->

					<!-- 검색조건 유지 -->
					<c:if test="${registerFlag == 'UPDATE'}">
						<input type="hidden" name="searchCondition" value="<c:out value='${groupManageVO.searchCondition}'/>"/>
						<input type="hidden" name="searchKeyword" value="<c:out value='${groupManageVO.searchKeyword}'/>"/>
						<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
					</c:if>
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

