$(function(){
//使lamp在背景图片的中间
    var bgHeight=$(".bgimg").height();
    var LHeight=$(".lamp").height();

    var lamptopHeight=bgHeight/2-LHeight/2+'px';

    var lampWidth=$(".lamp").width();
    var lampleftWidth=($("body").width()-lampWidth)/2+'px';

    $(".bg").css({'height':bgHeight;});
    $(".lamp").css({'top':lamptopHeight});
    $(".lamp").css({'left':lampleftWidth});

    $(window).bind("resize",function(){
         var bgHeight=$(".bgimg").height();
         $(".bg").css({'height':bgHeight;});
    });
})
