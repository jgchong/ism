<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>E-DAS</title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script src="<c:url value='/'/>js/Chart.bundle.js" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value='/'/>js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link href="<c:url value='/'/>css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="<c:url value='/'/>css/custom/common.css" type="text/css" rel="stylesheet"  />
<style>
.layerTopClose {
    top: 6px;
    right: 6px;
}
</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
<!-- 전체 레이어 시작 -->
<form name="form1" action="/ism/main/mainAccountCloseAct.do">
<div class="wrap">
	<div class="">
		<div class="contentsWrap">
			<div>
				<div class="inner">
					<p class="layerTit">정산 마감</p>
					<div class="layerContents">
						<table cellpadding="0" cellspacing="0" class="apitb" summary="" >
							<caption></caption>
							<colgroup>
								<col width="20%"/><col width="25%"/>
								<col width="20%"/><col width="25%"/>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">현재일</th>
									<td colspan="3"><span id="today">${today}</span></td>
								</tr>
								<tr>
									<th scope="row">마감월</th>
									<td colspan="3">
										<select id="closemonth" name="closemonth">
<c:forEach var="item" items="${closeAccountInfo}" varStatus="status">
											<option value="${item.closemonth}">${fn:substring(item.closemonth,0,4)}-${fn:substring(item.closemonth,4,6)}</option>
</c:forEach>
										</select>
									</td>
								</tr>
<c:forEach var="item" items="${closeAccountInfo}" varStatus="status">
	<c:if test="${status.count eq '1'}">
								<tr>
									<th scope="row">매출 마감 작업일자</th>
									<td><span id="cumclosedate">${item.cumclosedate}</span></td>
									<th scope="row">매입 마감 작업일자</th>
									<td><span id="bycclosedate">${item.bycclosedate}</span></td>
								</tr>
								<tr>
									<th scope="row">매출 마감 상태</th>
									<td>
										<select id="cumclosestatus" name="cumclosestatus">
											<option value="1" <c:if test="${item.cumclosestatus eq '1'}">selected</c:if> >미마감</option>
											<option value="2" <c:if test="${item.cumclosestatus eq '2'}">selected</c:if> >마감완료</option>
										</select>
										<input type="hidden" id="orgcumclosestatus" name="orgcumclosestatus" value="${item.cumclosestatus}"/>
									</td>
									<th scope="row">매입 마감 상태</th>
									<td>
										<select id="bycclosestatus" name="bycclosestatus">
											<option value="1" <c:if test="${item.bycclosestatus eq '1'}">selected</c:if> >미마감</option>
											<option value="2" <c:if test="${item.bycclosestatus eq '2'}">selected</c:if> >마감완료</option>
										</select>
										<input type="hidden" id="orgbycclosestatus" name="orgbycclosestatus" value="${item.bycclosestatus}"/>
									</td>
								</tr>
	</c:if>
								<input type="hidden" id="${item.closemonth}" name="orgcumclosestatus" value="${item.cumclosedate}^${item.bycclosedate}^${item.cumclosestatus}^${item.bycclosestatus}"/>
</c:forEach>
							</tbody>
						</table>
						
					</div>
					<p class="layerFootBt">
						<a href="javascript:saveClose();" class="confirm">마감처리</a>
						<a href="javascript:window.close();" class="cancel">닫기</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 전체 레이어 끝 -->
</body>
</html>

<script type="text/javascript">
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
$(document).ready(function(){
	$("#closemonth").change(function() { 
    	var dataSplitArr = $('#'+$(this).val()).val().split("^");

    	$('#cumclosedate').text(dataSplitArr[0]);
    	$('#bycclosedate').text(dataSplitArr[1]);
    	$("#cumclosestatus").val(dataSplitArr[2]);
    	$("#bycclosestatus").val(dataSplitArr[3]);
    	
    	$("#orgcumclosestatus").val(dataSplitArr[2]);
    	$("#orgbycclosestatus").val(dataSplitArr[3]);
	});
});

function saveClose() {
	document.form1.submit();
	opener.location.href = "/ism/adj/adj010.do";
}
</script>
