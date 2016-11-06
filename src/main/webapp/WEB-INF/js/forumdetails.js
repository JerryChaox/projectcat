$(function(){
	// 页面信息
	var url = location.search;
	var topicId = getUrlParameter(url, 'topicId');
	$.ajax({
        type:'GET',
        url:'../anonymous/topic?submitFlag=single_query&topicId=' + topicId,
        dataType:'json',
        success: function(data){
            if(data!=null){
                var tmpltxt=doT.template($("#topic").text());
                $("#topic-data").html(tmpltxt(data));
                more(topicId);
            }else{
                alert("加载失败！")
            }
        },
        error:function(jqXHR){
            alert("出现错误"+jqXHR.status);
        }          
    })

    //评论
    $(".publish").click(function(){
        var reply={
            content:$(".textarea").text(),
            replyer:$("#replyer").text()
        }

    	var content=$(".textarea").text();
    	$.ajax({
        	type:'POST',
        	dataType:'json',
        	contentType:'application/json',
        	data:JSON.stringify(reply),
        	success:function(data){
        		if(data!=null){
                    // 如果是 xx回复xx
                    if($("#replyer").hasClass("active")){
    	    			$(".content").append(
    			    		'<div class="discuss container border-box">'+
                                '<div class="box">'+
                                    '<a href="'+data.头像连接+'"><img src="'+data.地址+'" class="border-box">'+'</a>'+
                                    '<div class="comment border-box">'+
                                        '<p class="name">'+
                                            data.昵称+
                                            '<a href="javascript:;" class="reply"><img src="../image/reply.png"></a>'+
                                        '</p>'+
                                        '<div class="reply-box disnone">'+
                                                '<a href="javascript:;"><i class="icon-heart-empty"></i>点赞</a>'+
                                                '<a href="'+data.回复页面连接+'"><i class="icon-comment-alt"></i>回复</a>'+
                                                '<div class="clear">'+
                                                '</div>'+
                                        '</div>'+
                                        '<p class="time">第'+data.楼层+'楼&nbsp;&nbsp;'+data.时间+'</p>'+ 
                                    '</div>'+
                                    '<div class="clear">'+
                                    '</div>'+
                                    '<div class="forum">'+
                                        '<p>回复<em>'+replyer+'</em>'+content+'</p>'+
                                        '<div class="discuss-box border-box">'+
                                            '<div class="zan">'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+  
                            '</div>'
                        );
                        $("#replyer").removeClass("active");
                    }else{
                        //回复楼主
                        $(".content").append(
                            '<div class="discuss container border-box">'+
                                '<div class="box">'+
                                '<a href=../normal/othersProfile?submitFlag=query&othersId="'+ data[i].person.personId+'"><img src=.."'+data.person.headUrl+'" class="border-box">'+'</a>'+
                                    '<div class="comment border-box">'+
                                        '<p class="name">'+
                                            data.昵称+
                                            '<a href="javascript:;" class="reply"><img src="../image/reply.png"></a>'+
                                        '</p>'+
                                        '<div class="reply-box disnone">'+
                                                '<a href="javascript:;"><i class="icon-heart-empty"></i>点赞</a>'+
                                                '<a href="'+data.回复页面连接+'"><i class="icon-comment-alt"></i>回复</a>'+
                                                '<div class="clear">'+
                                                '</div>'+
                                        '</div>'+
                                        '<p class="time">第'+data.楼层+'楼&nbsp;&nbsp;'+data.时间+'</p>'+ 
                                    '</div>'+
                                    '<div class="clear">'+
                                    '</div>'+
                                    '<div class="forum">'+
                                        '<p>'+content+'</p>'+
                                        '<div class="discuss-box border-box">'+
                                            '<div class="zan">'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+  
                            '</div>'
                        );
    			    	$(".textarea").text("");
                    }
    		   }else{
    		   	alert("出现错误");
    		   }
        	},
        	error:function(jqXHR){
        		alert("出现错误"+jqXHR);
        	}
    	})
    })
})

// 点赞功能
$(document).delegate('.zan-btn','click',function(){
    if($(this).hasClass("active")){
        // 取消赞
        $.ajax({
            type:'POST',
            DataType:'json',
            url:'',
            data:{

            },
            success:function(data){
                if(data!=null){
                    $(this).removeClass("active");
                    $(this).html('<i class="icon-heart-empty"></i>点赞');
                    var num=$(this).find("span").text();
                    num--;
                    $(this).find("span").text(num);
                }
            },
            error:function(jqXHR){
                alert("出现错误："+jqXHR);
            }
        })
    }else{
        // 点赞
        $.ajax({
            type:'POST',
            DataType:'json',
            url:'',
            data:{

            },
            success:function(data){
                if(data!=null){
                    $(this).addClass("active");
                    $(this).html('<i class="icon-heart"></i>已赞');
                    var num=$(this).find("span").text();
                    num++;
                    $(this).find("span").text(num);
                }
            },
            error:function(jqXHR){
                alert("出现错误："+jqXHR);
            }
        })
    }
})

// 下拉加载更多
var offset = 0;
function more(topicId){
	var limit = 6;
    $.ajax({
        type:'GET',
        url:'../anonymous/topic/' + topicId + '/reply?submitFlag=query&offset=' + offset + '&limit=' + limit,
        contentType:'application/json',
        async:'false',
        dataType:'json',
        success:function(data){
            if(data != null){
                for(var i=0;i<data.length;i++){
                    $(".content").append(
                            '<div class="discuss container border-box">'+
                                '<div class="box">'+
                                    '<a href=../normal/othersProfile?submitFlag=query&othersId="'+ data[i].person.personId+'"><img src="..'+ data[i].person.headUrl +'" class="border-box">'+'</a>'+
                                    '<div class="comment border-box">'+
                                        '<p class="name">'+
                                            data[i].person.nickName+
                                            '<a href="javascript:;" class="reply"><img src="../image/reply.png"></a>'+
                                        '</p>'+
                                        '<div class="reply-box disnone">'+
                                                '<a href="javascript:;"><i class="icon-heart-empty"></i>点赞</a>'+
                                                '<a href="#"><i class="icon-comment-alt"></i>回复</a>'+
                                                '<div class="clear">'+
                                                '</div>'+
                                        '</div>'+
                                        '<p class="time">第'+ (offset + i + 1) +'楼&nbsp;&nbsp;'+data[i].updateTime+'</p>'+ 
                                    '</div>'+
                                    '<div class="clear">'+
                                    '</div>'+
                                    '<div class="forum">'+
                                        '<p>'+data[i].replyBody+'</p>'+                                      
                                    '</div>'+
                                '</div>'+  
                            '</div>'
                                )
                        }
                //finish a roll;
                offset = offset + limit;
                    }else{
                        $(".dynamic").append("<p class='no-result'>没有更多内容了<p>");
                    }
                },
        error:function(jqXHR){
                    alert("出现错误"+jqXHR.status);
        }
    })
}

// 点击回复图标，点赞，回复框出现
$(document).delegate(".reply",'click',function(){
    if($(this).hasClass("on")){
        $(this).parents(".comment").find(".reply-box").fadeOut(300);
        $(this).removeClass("on");
    }
    else{
        $(this).parents(".comment").find(".reply-box").fadeIn(300);
        $(this).addClass("on");
    }
});

$(document).delegate(".comment-btn",'click',function(){
    var txt=$(this).find(".first").find(".name").text();
    $(".comment-box .textarea").html("<span class='delete'>回复<i>"+txt+'</i>：</span>');
    $(".comment-box .textarea").focus();
    $("#replyer").text(txt);
    $("#replyer").addClass("active");
})