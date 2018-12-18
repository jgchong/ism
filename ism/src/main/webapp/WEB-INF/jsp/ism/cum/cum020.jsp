<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> KTI NMS </title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="/js/custom/common.js" type="text/javascript" charset="utf-8"></script>
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />
<style type="text/css">
li img {
    top: 16px;
    left: 15px;
    width: 50px;
    height: 50px;
    border-radius: 100%;
}
.inmemo {
	float: left;
	text-align:center;
}
.inmemotxt {
	float: left;
	margin-left: 16px;
	border: 1px solid #ccc;
	width: 72%;
	border-radius: 8px;
	min-height:45px;
}
.memotime {
	float:right;
}
.memoTxt li {
	height : 75px;
}
.layerTb table td input.hidden {
    display: none;
}
.layerTb table td .memo a {
    position: absolute;
    top: 0;
    right: 0;
    padding: 0 15px;
    line-height: 28px;
    background: #457cac;
    border: 0;
    font-size: 14px;
    color: #fff;
    vertical-align: -2px;
}
.layerTb .sel3 {
    width: 30%;
}
.layerTb .selcs1 {
    width: 43%;
}
.itcs1 {
	width:49%;display:none;float:right;text-align:center;
}
.rowPointer {
	cursor: pointer;
}
label.upload1 {
	padding: 7px 15px;
    border: 0;
    background: #45b6b6;
    color: #fff;
    font-size: 14px;
}
</style>
</head>
<body>
<!-- 전체 레이어 시작 -->
<div class="wrap">
	<c:import url="/sym/mms/EgovMainMenuHead.do" />
	<div class="container">
	    <!-- 좌측메뉴 시작 -->
	    <div class="lnb">
	    	<c:import url="/sym/mms/EgovMainMenuLeft.do" />
	    </div>
	    <!-- //좌측메뉴 끝 -->
		<!-- 이부부까지가 기본 -->

			<div class="contentsWrap">
				<div class="contents">
					<h2 class="pageTit">매출처 상품코드관리</h2>
					<form id="formMain" name="formMain" method="post" action="/ism/cum/cum020.do" class="searchArea">
						<a href="javascript://" id="excelDownbtn" style="background:#45b6b6;">매출처 상품코드 등록 양식 다운로드</a>
						
						<select id="search_cum030id" name="search_cum030id">
							<option value="0">매출처 쇼핑몰 선택</option>
<c:forEach var="result" items="${searchCumList}" varStatus="status">
<option value="${result.cum030id}" <c:if test="${search_cum030id eq result.cum030id}">selected</c:if> >${result.shopmallname}</option>
</c:forEach>
						</select>
						<button type="submit" style="margin-left:-4px;">검색</button>
						<input type="hidden" id="search_shopmallname" name="search_shopmallname" value="" />
					</form>
					<form id="formMainUp" name="formMainUp" method="post" enctype='multipart/form-data' action="/ism/cum/cum020ExcelUpload.do" class="searchArea">
						<label id="fileupxlsbtn" for="fileupxls" class="upload1">매출처 상품코드 업로드</label>
						<input type="file" id="fileupxls" name="file1" style="display:none;"/>
						<input type="hidden" id="hsearch_cum030id" name="hsearch_cum030id" />
					</form>
					<div class="listTb">
						<table cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<col width="3%"/>
								<col width="13%"/><col width="13%"/>
								<col width="13%"/><col width="*"/>
								<col width="13%"/><col width="13%"/>
								<col width="13%"/><col width="13%"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">NO.</th>
									<th scope="col">매출처명</th>
									<th scope="col">쇼핑몰명</th>
									<th scope="col">운영상품코드</th>
									<th scope="col">운영상품명</th>
									<th scope="col">매출처상품명</th>
									<th scope="col">정산가</th>
									<th scope="col">옵션1</th>
									<th scope="col">옵션2</th>
								</tr>
							</thead>
							<tbody>
<c:forEach var="result" items="${searchResult}" varStatus="status">
								<tr>
									<td><c:out value="${status.count}"/></td>
									<td><c:out value="${result.coname}"/></td>
									<td><c:out value="${result.shopmallname}"/></td>
									<td><c:out value="${result.itemcode}"/></td>
									<td><c:out value="${result.itemname}"/></td>
									<td><c:out value="${result.cumprodcode}"/></td>
									<td><c:out value="${result.accountPrice}"/></td>
									<td><c:out value="${result.cumprodoptnm1}"/></td>
									<td><c:out value="${result.cumprodoptnm2}"/></td>
								</tr>
</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>
	</div> <!-- container -->
</div> <!-- wrap -->

</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#excelDownbtn").click(function () {
    	if ($("#search_cum030id").val() == "0") {
    		alert("매출처 쇼핑몰을 선택해주시기 바랍니다.")
    	}else{
    		$("#search_shopmallname").val($("#search_cum030id option:selected").text());
            document.formMain.action = "<c:url value='/ism/cum/cum020ExcelDownload.do'/>";
            document.formMain.submit();
            document.formMain.action = "<c:url value='/ism/cum/cum020.do'/>";    		
    	}
    });
    
    $("#fileupxlsbtn").click(function () {
    	console.log("aaa="+$("#search_cum030id").val());
    	if ($("#search_cum030id").val() == "0") {
    		alert("매출처를 선택해주세요.");
    		event.preventDefault();
    	}else{
    		$("#hsearch_cum030id").val($("#search_cum030id").val());
    	}
    });
    
    $("#fileupxls").change(function () {
    	excelUpload();
    });
});

function excelUpload() {
    var options = {
        	success : function(data) {
        		console.log(data);
                //location.href="/ism/ord/ord020.do?search_key1="+data.split("^")[0]+"&search_status=TEMP";
                if (data == "SUCCESS") {
                	alert("매출처 상품코드가 등록됐습니다.");
                	location.href="/ism/cum/cum020.do?search_cum030id="+$("#search_cum030id").val();
                }else{
                	alert("매출처 상품코드 등록 중 오류가 발생했습니다.");
                }
            },
            error : function(xhr, status, error) {
            	console.log(error);
            },
            type : "POST"
        };
    	
	$("#formMainUp").ajaxSubmit(options);
}
</script>