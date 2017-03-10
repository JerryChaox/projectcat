$(function(){
	var aHeight=$(".comment-box [type='button']").outerHeight();
	$(".comment-box .textarea").css({'minHeight':aHeight});

	$(".blank").css({'height':$(".comment-box").height()+20+'px'});

	$(".ques-box").css({'height':$(".ques").height()+"px"})

	$(".comment-box .textarea").html("回复楼主");
	$(".comment-box .textarea").click(function(){
		if($(this).hasClass("on")){
			$(this).html("");
			$(this).removeClass("on");
		}
	})
})

$(".reply").click(function(){
	if($(this).hasClass("on")){
		$(this).parents(".comment").find(".reply-box").fadeOut(300);
		$(this).removeClass("on");
	}
	else{
		$(this).parents(".comment").find(".reply-box").fadeIn(300);
		$(this).addClass("on");
	}
});

$(".discussList").click(function(){
	var txt=$(this).find(".name").text();
	$(".comment-box .textarea").text("回复"+txt+'：');
	$(".comment-box .textarea").focus();

	$(".comment-box .textarea").keyup(function(){
		$(this).text("");
	})
})

// 评论框有内容发表按钮可以按
$(".comment-box .textarea").keyup(function(){
	$(this).css({"color":"#000"});
	if($(this).text()==""){
		$(".publish").attr("disabled","disabled");
		$(".publish").removeClass("active");
	}else{
		$(".publish").removeAttr("disabled");
		$(".publish").addClass("active");
	}
})