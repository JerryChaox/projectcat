$(function(){
    $(".blank").css({'height':$(".comment-box").height()+20+'px'});

    // 问题收缩
    $(window).on('scroll',function(){
        if($(window).scrollTop()>50){
            $(".ques").addClass("no");
        }else{
            $(".ques").removeClass("no");
        }
    })

	// 页面信息
    var url = location.search;
	var topicId = getUrlParameter(url, 'topicId');
	$.ajax({
        type:'GET',
        url:'../anonymous/topic?submitFlag=single_query&topicId=' + topicId,
        dataType:'json',
        success: function(data){
            if(data!=null){
                var tmpltxt=doT.template($("#reply").text());
                $("#reply-box").html(tmpltxt(data));
            }else{
                alert("加载失败！")
            }
        },
        error:function(jqXHR){
            alert("出现错误"+jqXHR.status)
        }          
    })
})

// 关注
$(document).delegate('.attention','click',function(){
    // 取消关注
    if($(this).hasClass("active")){
        $.ajax({
            type:'POST',
            dataType:'json',
            //how to get followedId
            url:'../normal/follow?submitFlag=delete&followedId=' + followedId,
            success:function(data){
                if(data!=null){
                    $(this).html('<i class="icon-plus"></i>关注');
                    $(this).removeClass("active");
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }else{
        // 关注
        $.ajax({
            type:'POST',
            dataType:'json',
            //how to get followedId
            url:'../normal/follow?submitFlag=create&followedId=' + followedId,
            success:function(data){
                if(data!=null){
                    $(this).html('已关注');
                    $(this).addClass("active");
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }
})

// 点赞
$(document).delegate('.zan-btn','click',function(){
    // 取消赞
    if($(this).hasClass("active")){
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'',
            success:function(data){
                if(data!=null){
                    $(this).find("i").attr("class","icon-heart-empty");
                    $(this).find("i").css({'color':'#999'});
                    $(this).removeClass("active");
                    // 点赞人数减1
                    var num=$(this).find("span").text();
                    num--;
                    $(this).find("span").text(num);
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }else{
        // 赞
        $.ajax({
            type:'POST',
            dataType:'json',
            data:{

            },
            url:'',
            success:function(data){
                if(data!=null){
                    $(this).find("i").attr("class","icon-heart")
                    $(this).find("i").css({'color':'#0091f0'});
                    $(this).addClass("active");
                    // 点赞人数加1
                    var num=$(this).find("span").text();
                    num++;
                    $(this).find("span").text(num);
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }
})

// 收藏帖子
$(".collect").click(function(){
    // 取消收藏
    if($(this).hasClass("active")){
        $.ajax({
            type:'POST',
            dataType:'json',
            data:{

            },
            url:'',
            success:function(data){
                if(data!=null){
                    $(this).find("i").attr("class","icon-star-empty");
                    $(this).removeClass("active");
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }else{
        // 收藏
        $.ajax({
            type:'POST',
            dataType:'json',
            url:'../normal/star?submitFlag=create&objectId=' + objectId + '&starClass=topic',
            success:function(data){
                if(data!=null){
                    $(this).find("i").attr("class","icon-star");
                    $(this).addClass("active");
                }
            },
            error:function(jqXHR){
                alert("出现错误"+jqXHR);
            }
        })
    }
})

$(".publish").click(function(){
    var reply={
        content:$(".textarea").text(),
    }
    var content=$(".comment-box .textarea").text();
    $.ajax({
        type:'POST',
        dataType:'json',
        data:JSON.stringify(reply),
        success:function(data){
            if(data!=null){
                $(".others-box").append(
                        '<div class="others">'+
                            '<div class="container border-box">'+
                                '<div class="head">'+
                                    '<a href="'+data.头像连接+'"><img class="border-box" src="'+data.头像+'"></a>'+
                                    '<div class="border-box">'+
                                        '<p class="name">'+
                                            '<a href="'+data.头像连接+'">'+data.昵称+'</a>'+
                                            '<a class="attention" href="javascript:;"><i class="icon-plus"></i>关注</a>'+
                                        '</p>'+
                                    '</div>'+
                                    '<div class="clear">'+
                                    '</div>'+
                                '</div>'+
                                '<div class="detail border-box">'+
                                    '<p class="time"><i class="icon-time"></i>'+data.时间+'</p>'+
                                    '<p>'+data.回复内容+'</p>'+
                                    '<p class="reply">'+
                                        '<a href="javascript:;" class="zan-btn"><i class="icon-heart-empty"></i><span>0</span></a>'+
                                        '<a href="'+data.评论连接+'"><i class=" icon-comment-alt"></i>0</a>'+
                                        '<a href="javascript:;" class="red-paper"><img src="../image/red.png"></a>'+
                                    '</p>'+
                                '</div>'+
                            '</div>'+
                        '</div>'
                )
            }
        },
        error:function(jqXHR){
            alert("出现错误"+jqXHR);
        }
    })
})

$(".adopt-btn").click(function(){
    $.ajax({
        type:'POST',
        dataType:'json',
        url:'',
        data:{

        },
        success:function(data){
            if(data!=null){
                var adopt=$(this).parents(".others").clone();
                $(this).parents(".others").remove();
                $(".all-answer").after(adopt);
                adopt.attr("class","accept-box");
                $(".accept-box .aa").removeClass("container").removeClass("border-box");
                $(".accept-box .bb").addClass("accept");
                $(".accept").prepend('<p><i class="icon-flag"></i>提问者采纳</p>');
                $(".accept .head .border-box").append('<p class="job">头衔</p>');
                $(".accept-box .detail").addClass("container");
                $("body").find(".adopt").remove();
            }
        },
        error:function(jqXHR){
            alert("出现错误"+jqXHR);
        }
    })
})