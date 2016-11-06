$(function(){
	// 遮罩层大小
	$(".dask").css({'width':$(window).width(),'height':$("body").height()});
})

$(".sign-up").click(function(){
	$(".dask").removeClass("disnone");
	$(".resume").fadeIn(100);
	$.ajax({
		type:'GET',
		url:'../normal/profile?submitFlag=query',
		dataType:'json',
		success:function(data){
			if(data != null){
				$(".resume-content").html(data.defaultResume);
				$(".resume-content").focus();
			}else{
				alert("出现错误");
			}
		},
		error:function(jqHXR){
			alert("出现错误"+jqHXR.status)
		}
	})
})

$(".dask").click(function(){
	$(".dask").addClass("disnone");
	$(".resume").fadeOut(100);
})

$(".close").click(function(){
	$(".dask").addClass("disnone");
	$(".resume").fadeOut(100);
})