function savememodata(buss_key, buss_type, inputmemo, setidObj) {
	if (inputmemo.trim() == "") {
		alert("메모를 입력해주시기 바랍니다.");
		return false;
	}
	$.ajax({
        url : "/ism/cmm/insertMemo.do",
        type: "post",
        data : { "buss_key" : buss_key, "inputmemo" : inputmemo, "buss_type" : buss_type },
        success : function(data){
        	if (data = "SUCCESS") {
            	setMemo(buss_key, buss_type, setidObj);	
        	}else if (data = "LFAIL") {
        		alert("세션 종료되거나 로그인 인증에 실패했습니다. 재로그인 해주시기 바랍니다.");
        	}else {
        		alert("메모 저장 중 오류가 발생했습니다.");
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

//메모입력 후 화면 메모창에 메모목록 재게시 
function setMemo(buss_key, buss_type, setidObj) {
	$.ajax({
        url : "/ism/cmm/selectListMemo.do",
        type: "post",
        data : { "buss_key" : buss_key, "buss_type" : buss_type },
        success : function(data){
        	$(setidObj).html(decodeURIComponent(data.replace(Ca, " ")));
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