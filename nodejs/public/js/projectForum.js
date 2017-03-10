$(function(){
    $.ajax({
            type:'GET',
            url:'',
            dataType:'json',
            success: function(data!=null){
                if(data!=null){
                    var tmpltxt=doT.template($("#project").text());
                    $("#project-data").html(tmpltxt(data));
                }else{
                    $(".news").append("<p class='no-result'>没有项目交流贴了^-^<p>");
                }
            },
            error:function(jqHXR){
                alert("出现错误"+jqHXR.status)
            }          
    })
})

function more(){
    $.ajax({
        type:'POST',
        data:{

        },
        dataType:'json',
        success:function(data){
            if(data!=null){
                for(var i=0;i<data.length;i++){
                    $(".container-box").append(
                            '<div class="forum-news">' +
                                '<div class="forum-box">' +
                                    '<a href="'+data[i].头像连接+'" class="box1">'+
                                        '<img src="'+data[i].头像+'">'+                                                
                                        '<span class="name">'+data[i].发帖人+'</span>'+
                                        '<span class="time">'+data[i].时间+'</span>'+
                                        '<div class="clear">'+
                                        '</div>'+
                                    '</a>'+
                                    '<a href="data[i].帖子链接" class="news">'+
                                        '<p>'+data[i].帖子内容+'</p>'+
                                        '<div class="imgfloat">'+
                                            '<span>'+
                                                '<img src="../image/forum-love.png">'+
                                                data[i].点赞人数+
                                            '</span>'+
                                            '<span class="comment">'+
                                                '<img src="../image/talk.png">'+
                                                data[i].回复人数+
                                            '</span>'+
                                            '<div class="clear"></div>'+
                                        '</div>'+   
                                    '</a>'+   
                                '</div>'+
                            '</div>'
                        )
                }
            }else{
                $(".container-box").append("<p class='no-result'>没有更多帖子了<p>");
            }
        },
        error:function(jqHXR){
            alert("出现错误"+jqHXR.status);
        }
    })
}