$(document).ready(function(){

	$('.layerBt').on('click',function(){
		$('body').append('<div class="fade" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:100; display:none;"></div>')
		$('.fade').fadeIn();
		$('.'+$(this).attr('name')).css({
			'margin':'-'+($('.'+$(this).attr('name')).height()/2)+'px 0 0 -'+($('.'+$(this).attr('name')).width()/2)+'px'
		})
		$('.'+$(this).attr('name')).fadeIn();
		return false;
	})
	$('.layerClose').on('click',function(){
		$('.layerCont').fadeOut();
		$('.fade').fadeOut(function(){
			$('.fade').remove();
		})
		return false;
	})
	//레이어 작업시 레이어 버튼의 name 과 같은 class 를 찾아 오픈 (기본적으로 버튼의 클래스는 layerBt 로 통일)
	//레이어의 클래스는 layerCont 로 통일. 더블 클래스를 사용하여 버튼의 네임값을 추가로 설정.
	//레이어의 닫기 및 취소등의 버튼의 클래스는 layerClose 로 통일.



	/* 레이어 위로 레이어가 뜰때. */
	$(document).on('click','.layerBt_v2',function(){ 
		$('body').append('<div class="fade_v2" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.8; z-index:200; display:none;"></div>')
		$('.fade_v2').fadeIn();
		$('.'+$(this).attr('name')).css({
			'margin':'-'+($('.'+$(this).attr('name')).height()/2)+'px 0 0 -'+($('.'+$(this).attr('name')).width()/2)+'px'
		})
		$('.'+$(this).attr('name')).fadeIn();
		return false;
	})
	$(document).on('click','.layerClose_v2',function(){ 
		$('.layerCont_v2').fadeOut();
		$('.fade_v2').fadeOut(function(){
			$('.fade_v2').remove();
		})
		return false;
	})
})