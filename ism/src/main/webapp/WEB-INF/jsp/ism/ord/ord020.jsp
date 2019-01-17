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
	<link href="/css/custom/base.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/layout.css" type="text/css" rel="stylesheet"  />
	<link href="/css/custom/common.css" type="text/css" rel="stylesheet"  />

<style type="text/css">
.paging a {
	margin : 0 3px 0 3px;
}
.paging a.on {
    background: #666;
    color: #fff;
}
.tbTab li {
    width: 11.1%;
}
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
form.searchArea .it {
    text-align: center;
    float: left;
    margin-left: 5px;
}
form.searchArea .searchMore li select {
    width: 100%;
    box-sizing: border-box;
    text-align: center;
    font-size: 14px;
    height: 34px;
    color: #666;
}
.rowPointer {
	cursor: pointer;
}
.compbtn {
	padding:3px;
	color:#fff;
	font-size:10px;
	text-decoration:none;
	background:rgba(150,0,29,1) !important;
}
.regPrd {
	padding:3px;
	color:#fff;
	font-size:10px;
	text-decoration:none;
	background:#457cac;
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
					<h2 class="pageTit">주문현황</h2>
					<form id="form1" name="form1" method="post" action="/ism/ord/ord020.do" class="searchArea">
		        		<input name="pageIndex" type="hidden" value="<c:out value='${ord020SearchVO.pageIndex}'/>"/>
						<div class="searchMore">
							<ul>
							    <li style="width:40%; text-align:left;">
							        <strong style="font-size:12px;">주문일</strong>
							        <input type="text" class="it datepicker" title="" value="${ord020SearchVO.dtSearch_frOrderDt}" id="dtSearch_frOrderDt" name="dtSearch_frOrderDt" placeHolder="시작주문일자" style="width:120px; float:none;"/> ~ 						<input type="text" class="it datepicker" title="" value="${ord020SearchVO.dtSearch_toOrderDt}" id="dtSearch_toOrderDt" name="dtSearch_toOrderDt" placeHolder="종료주문일자" style="width:120px; float:none;"/>
							    </li>
							    <li>
									<select name="dtSearch_cumType1">
										<option value="0">구분 선택</option>
                                        <c:forEach var="item" items="${ISM020}" varStatus="status">
										    <option value="${item.code}" <c:if test="${ord020SearchVO.dtSearch_cumType1 eq item.code}">selected</c:if> >${item.codeNm}</option>
                                        </c:forEach>
									</select>
								</li>
								
								<li>
									<select name="dtSearch_bycNm">
										<option value="">매입처 선택</option>
                                        <c:forEach var="item" items="${BycList}" varStatus="status">
										    <option value="${item.value}" <c:if test="${ord020SearchVO.dtSearch_bycNm eq item.value}">selected</c:if> >${item.value}</option>
                                        </c:forEach>
									</select>
								</li>
                                
								<li>
									<select name="dtSearch_cumNm">
										<option value="">매출처선택</option>
                                        <c:forEach var="item" items="${CumList}" varStatus="status">
										    <option value="${item.value}" <c:if test="${ord020SearchVO.dtSearch_cumNm eq item.value}">selected</c:if> >${item.value}</option>
                                        </c:forEach>
									</select>
								</li>			
                                
								<%--
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_bycNm}" name="dtSearch_bycNm" placeHolder="매입처"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_cumNm}" name="dtSearch_cumNm" placeHolder="매출처"/></li>
								--%>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_dlvNo}" name="dtSearch_dlvNo" placeHolder="송장번호"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_dlvCo}" name="dtSearch_dlvCo" placeHolder="택배사"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_orderno}" name="dtSearch_orderno" placeHolder="주문번호"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_orderItemid}" name="dtSearch_orderItemid" placeHolder="상품코드"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_orderItemName}" name="dtSearch_orderItemName" placeHolder="상품명"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_rcvUser}" name="dtSearch_rcvUser" placeHolder="수령자"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_rcvuserContact}" name="dtSearch_rcvuserContact" placeHolder="수령자 연락처"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_orderUser}" name="dtSearch_orderUser" placeHolder="주문자"/></li>
								<li><input type="text" class="it" title="" value="${ord020SearchVO.dtSearch_all}" name="dtSearch_all" placeHolder="통합검색"/></li>
								<!-- 
								<li>
									<select name="dtSearch_status">
										<option value="0">주문상태 선택</option>
                                        <c:forEach var="item" items="${ISM050}" varStatus="status">
										<option value="${item.code}">${item.codeNm}</option>
                                        </c:forEach>
									</select>
								</li>
								<li><select name="dtSearch_crtype"><option value="0">클레임/반품 선택</option><option value="C">클레임</option><option value="R">반품</option></select></li>
								 -->
							</ul>
							<p><a href="javascript:$('#search_isdetail').val(1);$('#form1').submit();" style="padding:7px 40px">검색</a></p>
						</div>
						
                        <div style="width:100%; display:inline-block;">
                            <div style="float:left;">
                                <a id="excelDownbtn" href="javascript:;" style="display:inline-block; text-align:center;">엑셀다운로드</a>
                                <!--a id="" href="javascript:;" style="display:inline-block; text-align:center;">엑셀업로드(개발중)</a-->
                            </div>
                            <div style="float:right;">
                                <select id="chgOrderStatus" name="chgOrderStatus" title="">
                                    <option value="0">상태값</option>
                                    <c:forEach var="item" items="${ISM050}" varStatus="status">
                                    <option value="${item.code}">${item.codeNm}</option>
                                   </c:forEach>
                                </select>
                                <a href="javascript:selectChgOrderStatus();" style="margin-left:-4px;">선택수정</a>
                                <a href="javascript:selectDel();" class="selDel">선택삭제</a>
                                <select id="pageUnit" name="pageUnit" title="" class="ml30">
									<option value="50" <c:if test="${ord020SearchVO.pageUnit eq '50'}"><c:out value="selected"/></c:if>>50개</option>
                                    <option value="100" <c:if test="${ord020SearchVO.pageUnit eq '100'}"><c:out value="selected"/></c:if>>100개</option>
									<option value="500" <c:if test="${ord020SearchVO.pageUnit eq '500'}"><c:out value="selected"/></c:if>>500개</option>
                                </select>
                                <button style="margin-left:-4px;">정렬</button>
                                <%--<a id="excelDownbtn" href="javascript:;">엑셀저장</a>--%>
                            </div>
                        </div>
						<input type="hidden" id="search_status" name="search_status" value="${ord020SearchVO.search_status}" />
						<input type="hidden" id="search_cstype" name="search_cstype" value="${ord020SearchVO.search_cstype}" />
						<input type="hidden" id="search_isdetail" name="search_isdetail" value="${ord020SearchVO.search_isdetail}" />
						<input type="hidden" id="search_tempdiv" name="search_tempdiv" value="${ord020SearchVO.search_tempdiv}">
						<input type="hidden" id="search_uploadviewkey" name="search_uploadviewkey" value="${ord020SearchVO.search_uploadviewkey}">
						<input type="hidden" id="search_cum010id" name="search_cum010id" value="${ord020SearchVO.search_cum010id}">
						<input type="hidden" id="search_cum030id" name="search_cum030id" value="${ord020SearchVO.search_cum030id}">
						<input type="hidden" id="search_uploadfilename" name="search_uploadfilename" value="${ord020SearchVO.search_uploadfilename}">
						<!-- 상세 검색으로 검색 했는지 여부 가지고가서 그럴경우 상세검색 토글료 open -->
					</form>

					<ul class="tbTab">
						<li><a href="javascript:search_status('TEMP');" <c:if test="${ord020SearchVO.search_status eq 'TEMP'}"><c:out value="class=on"/></c:if>>임시</a></li>
						<li><a href="javascript:search_status('ALL');" <c:if test="${ord020SearchVO.search_status eq 'ALL'}"><c:out value="class=on"/></c:if>>전체</a></li>
						<li><a href="javascript:search_status('1');" <c:if test="${ord020SearchVO.search_status eq '1'}"><c:out value="class=on"/></c:if>>출고전</a></li>
						<li><a href="javascript:search_status('2');" <c:if test="${ord020SearchVO.search_status eq '2'}"><c:out value="class=on"/></c:if>>출고대기</a></li>
						<li><a href="javascript:search_status('3');" <c:if test="${ord020SearchVO.search_status eq '3'}"><c:out value="class=on"/></c:if>>출고완료</a></li>
						<li><a href="javascript:search_status('4');" <c:if test="${ord020SearchVO.search_status eq '4'}"><c:out value="class=on"/></c:if>>출고지연</a></li>
						<li><a href="javascript:search_status('5');" <c:if test="${ord020SearchVO.search_status eq '5'}"><c:out value="class=on"/></c:if>>반품취소</a></li>
						<li><a href="javascript:search_cstype('C');" <c:if test="${ord020SearchVO.search_cstype eq 'C'}"><c:out value="class=on"/></c:if>>클레임</a></li>
						<li><a href="javascript:search_cstype('R');" <c:if test="${ord020SearchVO.search_cstype eq 'R'}"><c:out value="class=on"/></c:if>>반품관리</a></li>
					</ul>
					<div class="listTb">
<c:if test="${ord020SearchVO.search_status eq 'TEMP'}">
							<table id="mainListTable" cellpadding="0" cellspacing="0" style="margin:4px 0;" summary="" >
								<caption></caption>
								<colgroup>
									<col width="10%"/><col width="30%"/><col width="20%"/>
									<col width="10%"/><col width="10%"/><col width="10%"/><col width="10%"/>
								</colgroup>
								<thead>
								<tr>
									<th scope="col">업로드일</th>
									<th scope="col">업로드파일명</th>
									<th scope="col">매출처</th>
									<th scope="col" class="rowPointer" onclick="orderTempDetailList('S', '', '', '', '')">정상등록</th>
									<th scope="col" class="rowPointer" onclick="orderTempDetailList('P', '', '', '', '')">상품코드오류</th>
									<th scope="col" class="rowPointer" onclick="orderTempDetailList('O', '', '', '', '')">중복자료</th>
									<th scope="col" class="rowPointer" onclick="orderTempDetailList('T', '', '', '', '')">합계</th>
								</tr>
								<tbody>
<c:forEach var="resultstat" items="${resultstatList}" varStatus="status">
								<tr>
									<td class="rowPointer" ><c:out value="${resultstat.updatedt}"/></td>
									<td class="rowPointer" ><c:out value="${resultstat.uploadfilename}"/></td>
									<td class="rowPointer" ><c:out value="${resultstat.coname}"/></td>
									<td class="rowPointer" onclick="orderTempDetailList('S', '<c:out value="${resultstat.uploadviewkey}"/>','<c:out value="${resultstat.cum010id}"/>','<c:out value="${resultstat.cum030id}"/>','<c:out value="${resultstat.uploadfilename}"/>')"><c:out value="${resultstat.succcnt}"/></td>
									<td class="rowPointer" onclick="orderTempDetailList('P', '<c:out value="${resultstat.uploadviewkey}"/>','<c:out value="${resultstat.cum010id}"/>','<c:out value="${resultstat.cum030id}"/>','<c:out value="${resultstat.uploadfilename}"/>')"><c:out value="${resultstat.prodfailcnt}"/></td>
									<td class="rowPointer" onclick="orderTempDetailList('O', '<c:out value="${resultstat.uploadviewkey}"/>','<c:out value="${resultstat.cum010id}"/>','<c:out value="${resultstat.cum030id}"/>','<c:out value="${resultstat.uploadfilename}"/>')"><c:out value="${resultstat.overlapcnt}"/></td>
									<td class="rowPointer" onclick="orderTempDetailList('T', '<c:out value="${resultstat.uploadviewkey}"/>','<c:out value="${resultstat.cum010id}"/>','<c:out value="${resultstat.cum030id}"/>','<c:out value="${resultstat.uploadfilename}"/>')"><c:out value="${resultstat.totcnt}"/></td>
								</tr>
</c:forEach>
								</tbody>
							</thead>
							</table>
</c:if>
<c:if test="${!(ord020SearchVO.search_status == 'TEMP' && ord020SearchVO.search_tempdiv == '')}">
						<table id="mainListTable" cellpadding="0" cellspacing="0" class="" summary="" >
							<caption></caption>
							<colgroup>
								<col width="2%"/><col width="2%"/><col width="8%"/><col width="4%"/><col width="4%"/><col width="5%"/>
								<col width="5%"/><col width="4%"/><col width="8%"/><col width="8%"/><col width="*"/>
								<col width="5%"/><col width="3%"/><col width="5%"/><col width="10%"/><col width="5%"/>
								<col width="4%"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><a href="javascript:chkall();">V</a></th>
									<th scope="col">NO.</th>
									<th scope="col">주문일자</th>
									<th scope="col">구분</th>
									<th scope="col">매입처</th>
									<th scope="col">매출처</th>
									<th scope="col">송장번호</th>
									<th scope="col">택배사</th>
									<th scope="col">주문번호</th>
									<th scope="col">상품코드</th>
									<th scope="col">상품명</th>
									<th scope="col">옵션</th>
									<th scope="col">수량</th>
									<th scope="col">수령자</th>
									<th scope="col">수령자연락처</th>
									<th scope="col">주문자</th>
									
									<th scope="col">상태</th>
								</tr>
							</thead>
							<tbody>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:set var = "rowClass" value = ""/>
	<c:if test="${result.cstype eq 'C' && ord020SearchVO.search_status ne 'TEMP'}">
		<!-- 클레임 접수의 경우 레드 -->
		<c:if test="${result.claimstatus eq 1}">
			<c:set var = "rowClass" value = "class='red'"/>
		</c:if>
		<!-- 클레임 처리의 경우 옐로우 -->
		<c:if test="${result.claimstatus eq 2}">
			<c:set var = "rowClass" value = "class='yellow'"/>
		</c:if>
		<!-- 클레임 처리 완료의 경우 블루 -->
		<c:if test="${result.claimstatus eq 3}">
			<c:set var = "rowClass" value = "class='blue'"/>
		</c:if>
	</c:if>
	<c:if test="${ord020SearchVO.search_status eq 'TEMP'}">
		<c:if test="${result.chkprod eq 'NOITEM'}">
			<c:set var = "rowClass" value = "class='red'"/>
		</c:if>
		<c:if test="${result.chkoverlap ne 'NEW'}">
			<c:set var = "rowClass" value = "class='blue'"/>
		</c:if>
	</c:if>
								<tr ${rowClass}>
									<td><input type="checkbox" id="chk_info" name="chk_info" class="chk_info" dataid="${result.odm010id}" /></td>
									<td><strong><c:out value="${(ord020SearchVO.pageIndex - 1) * ord020SearchVO.pageUnit + status.count}"/></strong></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderdate}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')">${result.code_nm}</td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.bycname}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.coname}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.dlvno}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.dlvco}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderno}"/></td>
									<c:if test="${ord020SearchVO.search_status ne 'TEMP'}">
										<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderitemid}"/></td>
									</c:if>
									<c:if test="${ord020SearchVO.search_status eq 'TEMP' && result.chkprod ne 'NOITEM'}">
										<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderitemid}"/></td>
									</c:if>
									<c:if test="${ord020SearchVO.search_status eq 'TEMP' && result.chkprod eq 'NOITEM'}">
										<td class="rowPointer"><c:out value="${result.orderitemid}"/><button type="button" onclick="fnCreateProd('<c:out value="${result.odm010id}"/>')" class="regPrd">상품등록 </button></td>
									</c:if>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderitemname}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderitemopt}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderitemqty}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.rcvuser}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.rcvusercontacthp}"/></td>
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"><c:out value="${result.orderuser}"/></td>
<c:if test="${ord020SearchVO.search_status ne 'TEMP'}">									
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')">
	<c:choose>
    	<c:when test="${result.cstype eq 'C'}">
									<c:out value="${result.ststusNm}"/></td>
		</c:when>
		<c:when test="${result.cstype eq 'R'}">
									<c:out value="${result.ststusNm}"/></td>
		</c:when>
		<c:otherwise>
									<c:out value="${result.ststusNm}"/></td>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test="${ord020SearchVO.search_status eq 'TEMP'}">
	<c:if test="${result.chkoverlap eq 'NEW'}">
									<td class="rowPointer" onclick="orderDetailView('<c:out value="${result.odm010id}"/>')"> </td>
	</c:if>
	<c:if test="${result.chkoverlap ne 'NEW'}">
									<td class="rowPointer" onclick="orderDetailCompView('<c:out value="${result.odm010id}"/>')"><button type="button" class="compbtn" >자료비교</button></td>
	</c:if>
</c:if>
								</tr>
</c:forEach>
<c:if test="${fn:length(resultList) eq 0}">
								<tr>
									<td scope="col" colspan="17">검색내용이 없습니다.</td>
								</tr>
</c:if>
							</tbody>
						</table>
</c:if>
					</div>

<c:if test="${!(ord020SearchVO.search_status eq 'TEMP' && ord020SearchVO.search_tempdiv eq '')}">
					<div class="paging">
						<ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="fnLinkPage" />
					</div>
</c:if>
				</div>
				<!-- contents 레이어 끝 -->
			</div>
			<!-- contentsWrap 레이어 끝 -->
	</div>
	<!-- container 레이어 끝 -->
</div>
<!-- 전체 레이어 끝 -->

<!-- 주문현황관리 -->
<form id="formorder" name="formorder" method="post" action="/ism/ord/updateOrderDetailData.do">
<div class="layerCont order">
	<div class="inner">
		<p class="layerTit">주문현황관리</p>
		<div id="orderDetil" class="layerContents"><div class="inner" style="height:900px;"></div></div>
		<p class="layerFootBt">
			<a href="javascript:saveDetail();" class="confirm">확인</a>
			<a href="javascript:;" class="layerClose cancel">취소</a>
		</p>
		<a href="javascript:;" class="layerClose layerTopClose"><img src="/images/custom/closePop.png" alt=""/></a>
	</div>
</div>

<input type="hidden" id="odm010id" name="odm010id" value="" />
</form>
</body>
</html>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var Ca = /\+/g;
$(document).ready(function() {
	$('#pageUnit').change(function() { //목록 보여줄 수 50 100 500 변경시 처리
		document.form1.pageIndex.value = 1;
		$('#form1').submit();
	});
	//common.js 있는 부분을 여기서만 처리를 위해 common.js 빼고 추가
	$('.layerBt').on('click',function(){
		$('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
		$('.fade').fadeIn();
		$('.'+$(this).attr('name')).css({
			'margin':'-'+($('.'+$(this).attr('name')).height()/2)+'px 0 0 -'+($('.'+$(this).attr('name')).width()/2)+'px'
		})
		$('.'+$(this).attr('name')).fadeIn();
		return true;
	})
	$('.layerClose').on('click',function(){
		$('.layerCont').fadeOut();
		$('.fade').fadeOut(function(){
			$('.fade').remove();
		})
		return false;
	})

	$('.layerBt_v2').on('click',function(){
		$('body').append('<div class="fade_v2" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:200; display:none;"></div>')
		$('.fade_v2').fadeIn();
		$('.'+$(this).attr('name')).css({
			'margin':'-'+($('.'+$(this).attr('name')).height()/2)+'px 0 0 -'+($('.'+$(this).attr('name')).width()/2)+'px'
		})
		$('.'+$(this).attr('name')).fadeIn();
		return false;
	})
	$('.layerClose_v2').on('click',function(){
		$('.layerCont_v2').fadeOut();
		$('.fade_v2').fadeOut(function(){
			$('.fade_v2').remove();
		})
		return false;
	})
    $("#excelDownbtn").click(function () {
        document.form1.action = "<c:url value='/ism/ord/ord020ExcelDownload.do'/>";
        document.form1.submit();
        document.form1.action = "<c:url value='/ism/ord/ord020.do'/>";
    });

	$("#dtSearch_frOrderDt").change(function() {
		document.form1.pageIndex.value = 1;
        document.form1.submit();
	});

	$("#dtSearch_toOrderDt").change(function() {
		document.form1.pageIndex.value = 1;
        document.form1.submit();
	});
});


function fnLinkPage(pageNo){
    document.form1.pageIndex.value = pageNo;
    document.form1.action = "<c:url value='/ism/ord/ord020.do'/>";
    document.form1.submit();
}

//목록 주문번호 클릭시 상세 주문 내용 레이어 팝업 오픈
function orderDetailView(odm010id) {
	window.open("<c:url value='/ism/ord/odo020SelectOrderDetailPopUp.do'/>?odm010id="+odm010id, "", "width=810, height=700, status=1");
}
var chkallval = 0;
function chkall() {
	if ( chkallval == 0 ) {
		chkallval = 1;
		$("input:checkbox[id='chk_info']").prop('checked', true);
	}else{
		chkallval = 0;
		$("input:checkbox[id='chk_info']").prop('checked', false);
	}
}

//체크선택된 주문의 상태 일괄 변경
function selectChgOrderStatus() {
	var selectoptionval = $("#chgOrderStatus option:selected").val();
	var chgodm010ids = "";
	$('.chk_info').each(function() {
		if ($(this).is(":checked")) {
			chgodm010ids += ($(this).attr("dataid") + ",");
		}
	});
	if (selectoptionval == '0') {
		alert("변경 할 상태값을 선택해주시기 바랍니다.");
		return;
	}
	if (chgodm010ids == "") {
		alert("변경 할 주문을 선택해주시기 바랍니다.");
		return;
	}else{
		chgodm010ids = chgodm010ids.substring(0,chgodm010ids.length - 1);
	}
	$.ajax({
        url : "/ism/ord/ord020SelectChgOrderStatus.do",
        type: "post",
        data : { "selectoptionval" : selectoptionval, "chgodm010ids" : chgodm010ids },
        success : function(data){
        	if (data == "SUCCESS") {
            	alert("상태 변경되었습니다.");
            	document.form1.pageIndex.value = 1;
        		$('#form1').submit();	
        	}else{
            	alert("상태 변경 중 오류가 발생했습니다.");	
        	}
        },
        error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'Not connect.\n Verify Network.';
            } else if (jqXHR.status == 404) {
                msg = 'Requested page not found. [404]';
            } else if (jqXHR.status == 500) {
                msg = 'Internal Server Error [500].';
            } else if (exception === 'parsererror') {
                msg = 'Requested JSON parse failed.';
            } else if (exception === 'timeout') {
                msg = 'Time out error.';
            } else if (exception === 'abort') {
                msg = 'Ajax request aborted.';
            } else {
                msg = 'Uncaught Error.<br>' + jqXHR.responseText;
            }
            alert("Error : "+msg);
        }
    });
}

//체크박스된 주문 목록 삭제
function selectDel() {
	if (confirm("선택 주문을 삭제하시겠습니까?")) {
		var chgodm010ids = "";
		$('.chk_info').each(function() {
			if ($(this).is(":checked")) {
				chgodm010ids += ($(this).attr("dataid") + ",");
			}
		});
		if (chgodm010ids == "") {
			alert("삭제 할 주문을 선택해주시기 바랍니다.");
			return;
		}else{
			chgodm010ids = chgodm010ids.substring(0,chgodm010ids.length - 1);
		}
		$.ajax({
	        url : "/ism/ord/ord020SelectDel.do",
	        type: "post",
	        data : { "chgodm010ids" : chgodm010ids },
	        success : function(data){
	        	if (data == "SUCCESS") {
	            	alert("삭제 되었습니다.");
	            	document.form1.pageIndex.value = 1;
	        		$('#form1').submit();	
	        	}else{
	            	alert("삭제 중 오류가 발생했습니다.");	
	        	}
	        },
	        error: function (jqXHR, exception) {
	            var msg = '';
	            if (jqXHR.status === 0) {
	                msg = 'Not connect.\n Verify Network.';
	            } else if (jqXHR.status == 404) {
	                msg = 'Requested page not found. [404]';
	            } else if (jqXHR.status == 500) {
	                msg = 'Internal Server Error [500].';
	            } else if (exception === 'parsererror') {
	                msg = 'Requested JSON parse failed.';
	            } else if (exception === 'timeout') {
	                msg = 'Time out error.';
	            } else if (exception === 'abort') {
	                msg = 'Ajax request aborted.';
	            } else {
	                msg = 'Uncaught Error.<br>' + jqXHR.responseText;
	            }
	            alert("Error : "+msg);
	        }
	    });
	}
}

//화면 상단 각 상태별 클릭시 목록 조회
function search_status(status) {
	document.form1.reset();
	$('#search_status').val(status);
	$('#search_cstype').val('0');
	document.form1.pageIndex.value = 1;
	$('#form1').submit();
}

//화면 상단 클레임 및 반품탭 클릭시 목록 조회
function search_cstype(cstype) {
	document.form1.reset();
	$('#search_status').val("ALL");
	$('#search_cstype').val(cstype);
	document.form1.pageIndex.value = 1;
	$('#form1').submit();	
}

function opener_search() {
	$('#form1').submit();	
}

//LDC 추가
//리스트 상세.
function orderTempDetailList(search_tempdiv, search_uploadviewkey, search_cum010id, search_cum030id, search_uploadfilename)
{
	document.form1.reset();
	document.form1.search_tempdiv.value = search_tempdiv;
	document.form1.search_uploadviewkey.value = search_uploadviewkey;
	document.form1.search_cum010id.value = search_cum010id;
	document.form1.search_cum030id.value = search_cum030id;
	document.form1.search_uploadfilename.value = search_uploadfilename;
	document.form1.pageIndex.value = 1;
	document.form1.action = "<c:url value='/ism/ord/ord020.do'/>";
	document.form1.submit();
}
//상품 등록
function fnCreateProd(odm010id){
	window.open("<c:url value='/ism/ord/odo020SelectOrderProdPopUp.do'/>?odm010id="+odm010id, "", "width=810, height=700, status=1");
}
//
function opener_tempsearch() {
	
	document.form1.search_tempdiv.value = "${ord020SearchVO.search_tempdiv}";
	document.form1.search_uploadviewkey.value = "${ord020SearchVO.search_uploadviewkey}";
	document.form1.search_cum010id.value = "${ord020SearchVO.search_cum010id}";
	document.form1.search_cum030id.value = "${ord020SearchVO.search_cum030id}";
 document.form1.action = "<c:url value='/ism/ord/ord020.do'/>";
 document.form1.submit();
	$('#form1').submit();
	
}
//자료비교 팝업..
function orderDetailCompView(odm010id) {
	window.open("<c:url value='/ism/ord/odo020SelectOrderDetailCompPopUp.do'/>?odm010id="+odm010id, "", "width=1500, height=700, status=1");
}
</script>

<script type="text/javascript">
    $(function () {
        $(".datepicker").datepicker({
            dateFormat : "yy-mm-dd",
            monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
            changeMonth : true,
            changeYear : true,
            yearRange : "c-70:c+70",
            showMonthAfterYear : true
        });
    });
</script>