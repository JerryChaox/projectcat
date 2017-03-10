$(function(){
	// 遮罩层大小
	var daskHeight;
	if($(window).height()>$(document.body).height()){
		daskHeight=$(window).height();
	}else{
		daskHeight=$(document.body).height();
	}
	$(".dask").css({'width':$(window).width(),'height':daskHeight})
})

$(".header .collect").click(function(){
	if($(this).hasClass("on")){
		$(".dask").removeClass("disnone");
		$(".collect-detail").show();
		$(this).removeClass("on");
	}else{
		$(".dask").addClass("disnone");
		$(".collect-detail").hide();
		$(this).addClass("on");
	}
})

$(".dask").click(function(){
	$(this).addClass("disnone");
	$(".collect-detail").hide();
	$(".header .collect").addClass("on");
})