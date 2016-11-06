$(function(){
    //论坛背景图片高度和背景内容高度一样
    var headHeight=$(".forum-writing").height();
    var navHeight=$(".nav").height(); 
    //alert(navHeight)
    //box3距顶部的高度
    boxtopHeight();
    //box3动画效果
     forumHover();

    $(".dask").css({'top':headHeight,'marginBottom':navHeight});
    var daskHeight;
    if($(window).height()>$(document.body).height()){
        daskHeight=$(window).height();
    }else{
        daskHeight=$(document.body).height();
    }
    $(".dask").css({'width':$(window).width(),'height':daskHeight})
})
//box3距顶部的高度
function  boxtopHeight(){
   var boxHeight=$(".box3").height();
   var btopHeight=($(window).height()-boxHeight)/2+'px'
   $(".box3").css({'top':btopHeight});
}

function forumHover(){
    var right=-$(".box3 ul").width()-2;
    $(".box3").css({'right':right});

    $(".toleft").click(function(){
    	if($(this).hasClass("active")){
    		$(".box3").stop(true,true);
    		$(".box3").animate({'right':right},100);
            $(this).find(".btn").attr("src","../image/toleft.png");
            $(this).removeClass("active");
            $(".dask").addClass("disnone");
    	}else{
    		$(".box3").stop(true,true);
    		$(".box3").animate({'right':'0'},100);
            $(this).find(".btn").attr("src","../image/toright.png");
            $(this).addClass("active");
            $(".dask").removeClass("disnone");
    	}
            
    })

    $(".dask").click(function(){
    		$(".box3").stop(true,true);
    		$(".box3").animate({'right':right},100);
            $(".toleft").find(".btn").attr("src","../image/toleft.png");
            $(".toleft").removeClass("active");
            $(".dask").addClass("disnone");
    })
}


