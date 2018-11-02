<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title> KTI NMS </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="/css/custom/base.css" type="text/css" rel="stylesheet"/>
    <link href="/css/custom/layout.css" type="text/css" rel="stylesheet"/>
    <link href="/css/custom/common.css" type="text/css" rel="stylesheet"/>
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
            text-align: center;
        }

        .inmemotxt {
            float: left;
            margin-left: 16px;
            border: 1px solid #ccc;
            width: 72%;
            border-radius: 8px;
            min-height: 45px;
        }

        .memotime {
            float: right;
        }

        .memoTxt li {
            height: 75px;
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

        #layer_hidden1 {
            position: absolute;
            padding: 5px;
            filter: alpha(opacity=50);
            width: 250px;
            height: auto;
            background-color: white;
            border: 2px #000000 dotted;
            visibility: hidden;
        }
        .colR{color:red}
        .colB{color:blue}
        .listTb .bg_col th,
        .listTb .bg_col td{background:#eff9fe}
    </style>
</head>
<body>
<!-- 전체 레이어 시작 -->
<div class="wrap">
    <c:import url="/sym/mms/EgovMainMenuHead.do"/>
    <div class="container">
        <!-- 좌측메뉴 시작 -->
        <div class="lnb">
            <c:import url="/sym/mms/EgovMainMenuLeft.do"/>
        </div>
        <!-- //좌측메뉴 끝 -->
        <!-- 이부부까지가 기본 -->
        <div class="contentsWrap">
            <ul class="contentsMenu">
                <li><a href="adj010.do" >종합판매정산</a></li>
                <li><a href="adj020.do" >상품별정산</a></li>
                <li><a href="adj030.do">상품수불부</a></li>
                <li><a href="adj040.do">수금관리대장</a></li>
                <li><a href="adj050.do">재고관리대장</a></li>
                <li><a href="adj060.do">운송비대장</a></li>
                <li><a href="adj070.do">판매관리비</a></li>
                <li><a href="adj080.do" class="on">정산도움말</a></li>
            </ul>
            <div class="contents">
                <h2 class="pageTit">정산도움말</h2>

                <div class="textContents" style="margin-top:-20px;">
                    <strong style="font-size:16px; color:#333;">매달 확인 필요사항</strong>
                    <span style="display:block; padding:0 0 30px 10px; font-size:12px; color:#666;">
                        <br/>
						1. 이니시스 정산 주기에 따른 매입 매출 금액 확인 <br/>
                        2. 수수료, 발행 거래처 (수수료차감) 구분 <br/>
                        3. 2번 거래처 매출 수수료금액 포함하여 매출금액 산정 <br/>
                        4. 결산보고서 작성 후 원가부분은 수식으로 재 검산 <br/>
                        - 이월재고(전월재고) + 당월매입 - 당월재고 = 매출원가 <br/>
                        5. 이제너두 - 주문일기준 정산 <br/>
                        6. 위메프 정산 <br/>
                        - 정산금액 = 배송완료금액 + 배송비 - 배송완료후 환불금액 - 판매대행수수료 - 업무부담상품쿠폰 <br/>
                        - 매출신고 = 배송완료금액 + 배송비 - 배송완료후 환불금액 - 위메프부담상품쿠폰 <br/>
                        7. 티몬 정산 <br/>
                        - 정산금액 = 판매현황(상세데이터) -> 익월로 조회하면 정산 예정금액 확인 <br/>
                        - 매출신고 = 정산예정금액 + 판매수수료 <br/>
                        8. 한솔교육 : 주문일 기준 정산(1일~말일) <br/> 
                        9. 비즈마켓 이월건 관리 <br/>
                        10. 딜라이브정산 <br/>
                        - 딜라이브상품은 매도가 공급가 모두 0원 <br/>
                        - 넷케이티아이 상품은 매도가가 딜라이브로 주는 공급가임 <br/>
                        - 세금계산서 발행은 수수료 / 상품공급 2장 발행 <br/>
                        11. 유스랩 매입 이월 (10월로) <br/>
                        12. 에이치엔비 원가 이외 비용은 매도가에 금액 삽입 <br/>
                        13. 이제너두 공급가 역발행으로 정산방식 변경 <br/>
                        14. 키즈노트 공급가 역발행으로 정산방식 변경 <br/>
                        15. 쿠팡 정산 / 정산관리 -> 매출내역 <br/>
                    </span>
                </div>
            </div>


        </div>


    </div> <!-- container -->
</div> <!-- wrap -->



<div id="layer_hidden1">게시물 본문 미리 보기</div>
</body>
</html>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.techbytarun.excelexportjs.js"></script>
<script src="/js/custom/multiselect.js"></script>
<script src="/js/custom/ism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var Ca = /\+/g;

    //각종 초기화
    $(document).ready(function () {

    });



</script>
<script type="text/javascript">
    $(function () {
        $(".datepicker").datepicker({
            dateFormat: "yy-mm-dd",
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            changeMonth: true,
            changeYear: true,
            yearRange: "c-70:c+70",
            showMonthAfterYear: true
        });
    });
</script>