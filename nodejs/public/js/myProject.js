var oPosition = {};
var iCurr = 0;
$(function(){
    // 页面加载
    $.ajax({
        type:'GET',
        url:'',
        dataType:'json',
        success: function(data){
            if(data!=null){
                var tmpltxt=doT.template($("#project").text());
                $("#project-box").html(tmpltxt(data));

                if(有未读的项目通知){
                    $(".inform").find("span").removeClass("disnone");
                }
            }
        },
        error:function(jqHXR){
            alert("加载失败！")
        }          
    })    

	 $(".btn-box").css({'height':$(".btn").outerHeight()})

	// 轮播图容器的高度
	slideHeight();

	for(var i=0;i<$(".slider .box").length;i++){
		$(".item").eq(i).attr("num",i);
		$(".box").eq(i).attr("num",i);
	}
	$(".item").click(function(){
		var i=$(this).attr('num');
		doAnimate(i);
		$(this).siblings().removeClass("active");
		$(this).addClass("active");	
	})

	bindTochuEvent();
})

function doAnimate(i){
	$(".slider-box").stop(true,true);
	$(".slider-box").animate({'left':-i*100+'%'},300);
}

function slideHeight(){
	var a=$(".signUp").height();
	var b=$(".public").height();
	var c=$(".projectInform").height();

	if(a>=b){
		var sliderHei;
		sliderHei=a;
		if(sliderHei<c){
			sliderHei=c;
		}
	}else{
		sliderHei=b;
		if(sliderHei<c){
			sliderHei=c;
		}
	}

	if($("body").height()<$(window).height()){
		sliderHei=$(window).height()-$(".header").outerHeight()-$(".btn").outerHeight()-50;
	}

	$(".slider-box").css({'height':sliderHei});
}

//绑定触摸事件
function bindTochuEvent(){
    $(".slider-box").get(0).addEventListener('touchstart', touchStartFunc, false);
    $(".slider-box").get(0).addEventListener('touchmove', touchMoveFunc, false);
    $(".slider-box").get(0).addEventListener('touchend', touchEndFunc, false);
}
//获取触点位置
function touchPos(e){
    var touches = e.changedTouches, l = touches.length, touch, tagX, tagY;
    for (var i = 0; i < l; i++) {
        touch = touches[i];
        tagX = touch.clientX;
        tagY = touch.clientY;
    }
    oPosition.x = tagX;
    oPosition.y = tagY;
    return oPosition;
}
//触摸开始
function touchStartFunc(e){
    touchPos(e);
    startX = oPosition.x;
    startY = oPosition.y;
    temPos = $(".slider-box").position().left;
}
//触摸移动 
function touchMoveFunc(e){
    touchPos(e);
    var moveX = oPosition.x - startX;
    var moveY = oPosition.y - startY;
    if (Math.abs(moveY) < Math.abs(moveX)) {
    	e.preventDefault();
        $(".slider-box").css({
            left: temPos + moveX
        });
    }
}
//触摸结束
function touchEndFunc(e){
    touchPos(e);
    var moveX = oPosition.x - startX;
    var moveY = oPosition.y - startY;

    var iCurr=Math.floor(-($(".slider-box").position().left/$(".slider").width()*100/33.33))-1;
    if(iCurr>$(".box").length){
        iCurr=$(".box").length-1;
    }else if(iCurr<0){
        iCurr=0;
    }


    if (Math.abs(moveY) < Math.abs(moveX)) {
        if (moveX > 0) {
            iCurr=iCurr-1;
            if (iCurr >= 0) {
                var moveX = iCurr;
                doAnimate(moveX);
            }
            else {
                doAnimate(0);
                iCurr = 0;
            }
        }
        else{
            iCurr++;
            if (iCurr < $(".box").length && iCurr >= 0) {
            var moveX = iCurr;
            doAnimate(moveX);
        	}
	        else {
	            iCurr = $(".box").length - 1;
	            doAnimate(($(".box").length - 1));
	        }
    	}
    	$(".item").eq(iCurr).addClass("active").siblings().removeClass("active");
    }
}