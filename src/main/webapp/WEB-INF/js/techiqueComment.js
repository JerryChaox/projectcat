$(document).delegate(".comment",'click',function(){
    var txt=$(this).parents(".others").find(".name a:first-child").text();
    $(".comment-box .textarea").html("<span class='delete'>回复<i>"+txt+'</i>：</span>');
    $(".comment-box .textarea").focus();
    $("#replyer").text(txt);
    $("#replyer").addClass("active");
})

//评论
    $(".publish-btn").click(function(){
    	var reply={
    		content:$(".textarea").text(),
    		replyer:$("#replyer").text(),
    	}
    	var txt=$("#replyer").text();
		var content=$(".comment-box .textarea").text();
		
    	$.ajax({
    		type:'POST',
    		dataType:'json',
    		data:JSON.stringify(reply2),
    		success:function(data){
    			if(data!=null){
    				// 如果是 xx回复xx
    				if($("#replyer").hasClass("active")){
		    			$(".others-box").append(
		    				'<div class="others">'+
		                        '<div class="container border-box">+'
		                            '<div class="head">'+
		                                '<a href="'+data.头像连接'"><img class="border-box" src="'+data.头像地址'"></a>'+
		                                '<div class="border-box">'+
		                                    '<p class="name">'+
		                                        '<a href="'+data.头像连接'">'+data.昵称'</a>'+
		                                        '<a class="attention" href="javascript:;"><i class="icon-plus"></i>关注</a>'+
		                                    '</p>'+
		                                '</div>'+
		                                '<div class="clear"></div>'+
		                            '</div>'+
		                            '<div class="detail border-box">'+
		                                '<p class="time"><i class="icon-time"></i>'+data.时间+'</p>'+
		                                '<p>回复<em>'+txt+'</em>'+content+'</p>'+
		                                '<p class="reply">'+
		                                    '<a href="javascript:;" class="zan-btn"><i class="icon-heart-empty"></i><span>0</span></a>'
		                                    '<a href="javascript:;" class="comment"><i class="icon-comment-alt"></i>回复</a>'+
		                                '</p>'+
		                            '</div>'+
		                        '</div>'+
		                    '</div>');
				    	$("#replyer").removeClass("active");
				    }else{
				    	// 如果是直接回复楼主
				    	$(".others-box").append(
		    				'<div class="others">'+
		                        '<div class="container border-box">+'
		                            '<div class="head">'+
		                                '<a href="'+data.头像连接'"><img class="border-box" src="'+data.头像地址'"></a>'+
		                                '<div class="border-box">'+
		                                    '<p class="name">'+
		                                        '<a href="'+data.头像连接'">'+data.昵称'</a>'+
		                                        '<a class="attention" href="javascript:;"><i class="icon-plus"></i>关注</a>'+
		                                    '</p>'+
		                                '</div>'+
		                                '<div class="clear"></div>'+
		                            '</div>'+
		                            '<div class="detail border-box">'+
		                                '<p class="time"><i class="icon-time"></i>'+data.时间+'</p>'+
		                                '<p>'+content+'</p>'+
		                                '<p class="reply">'+
		                                    '<a href="javascript:;" class="zan-btn"><i class="icon-heart-empty"></i><span>0</span></a>'
		                                    '<a href="javascript:;" class="comment"><i class="icon-comment-alt"></i>回复</a>'+
		                                '</p>'+
		                            '</div>'+
		                        '</div>'+
		                    '</div>');
				    }
			    	$(".textarea").text("");
		    	}else{
		    		alert("出现错误");
		    	}
    		},
    		error:function(jqXHR){
    			alert("出现错误"+jqXHR);
    		}
    	})
    })