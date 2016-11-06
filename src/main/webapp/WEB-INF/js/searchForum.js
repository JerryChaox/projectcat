$(".search-btn").click(function(){
	$(".main-content").find(".result").remove();
	$(".main-content").find(".no-result").remove();
	var fluzzyName=$(this).parent(".search-box").find(".clas").val();
	var limit=9;
	var offset=0;
	var checked={
		forum:$("input:radio:checked").attr("class");//获取搜索的是什么类型的帖子
	}
	$.ajax({
			type:'POST',
			url:'../anonymous/topic?submitFlag=dynamic_query&fluzzyName=' + fluzzyName + '&limit='+limit+ '&offset=' +offset,
			contentType:'application/json',
        	data:JSON.stringify(checked),	
			dataType:'json',
			success:function(data){
				if(data != null){
					// 如果是项目交流贴
					if($(".project").attr("checked")){
						for(var i=0;i<data.length;i++){
							$(".container-box").append(
								'<div class="forum-news">'+
			                        '<div class="forum-box"'+
			                            '<a href="'+data[i].头像连接+'" class="box1">'+
			                                '<img src="'+data[i].头像+'">'+			                                  
			                                '<span class="name">'+data[i].昵称'</span>'+
			                                '<span class="time">'+data[i].时间+'</span>'+
			                                '<div class="clear"></div>'+
			                            '</a>'+
				                        '<a href="'+data[i].项目交流贴地址+'" class="news">'+
				                            '<p>'+data[i].项目交流贴内容+'</p>'
				                            '<div class="imgfloat">'+				  
				                                '<span>'+
				                                    '<img src="../image/forum-love.png">'+
				                                    data[i].点赞人数+
				                                '</span>'+
				                                '<span class="comment">'+
				                                    '<img src="../image/talk.png">'+
				                                    data[i].评论人数
				                                '</span>'+
				                                '<div class="clear"></div>'+
				                            '</div>'+   
				                        '</a>'+  
			                        '</div>'+
			                    '</div>'+
							)
						}						
					}else{
						// 如果是技术交流贴
						for(var i=0;i<data.length;i++){
							$(".container-box").append(
								'<div class="forum-news">'+
							        '<div class="forum-box">'+
							            '<a href="'+data[i].头像连接+'" class="box1">'+
							                '<img src="'+data[i].头像地址+'">'+							                  
							                '<span class="name">'+data[i].昵称+'</span>'+
							                '<span class="classify">'+
							                    '<i class=" icon-tasks"></i>'+
							                    data[i].类别+
							                '</span>'+
							                '<div class="clear"></div>'+
							            '</a>'+
							            '<a href="'+data[i].技术交流贴连接+'" class="news">'+
							                '<p>'+data[i].标题+'</p>'+
							                '<p>'+data[i].部分内容+'</p>'+
							                '<div class="imgfloat">'+
							                    '<span class="time">'+data[i].时间+'</span>'+
							                    '<span>'+
							                        '<img src="../image/forum-love.png">'+
							                        data[i].点赞人数+
							                    '</span>'+
							                    '<span class="comment">'+
							                        '<img src="../image/talk.png">'+
							                        data[i].评论人数+
							                    '</span>'+
							                    '<div class="clear"></div>'+
							                '</div>'+   
							            '</a>'+    
							        '</div>'+
							    '</div>'+
							)
						}
					}
				}else{
					$(".container-box").append("<p class='no-result'>没有搜索到相关项目<p>")
				}
				$(".type-con").text($("input:radio:checked").attr("class"));
			},
			error:function(jqXHR){
				alert("系统错误："+jqXHR.status);
			}
	})
})

var offset = 0;
function more(topicId){
	var limit = 6;
	var checked={
		forum:$("input:radio:checked").attr("class");//获取搜索的是什么类型的帖子
	}
    $.ajax({
        type:'POST',
        url:'../anonymous/topic/' + topicId + '/reply?submitFlag=query&offset=' + offset + '&limit=' + limit,
        contentType:'application/json',
        data:JSON.stringify(checked),
        dataType:'json',
        success:function(data){
            if(data != null){
            	// 如果是项目交流贴
            	if($(".type-con").text()=='project'){
            		for(var i=0;i<data.length;i++){
						$(".container-box").append(
							'<div class="forum-news">'+
			                    '<div class="forum-box"'+
			                        '<a href="'+data[i].头像连接+'" class="box1">'+
			                            '<img src="'+data[i].头像+'">'+			                                  
			                            '<span class="name">'+data[i].昵称'</span>'+
			                            '<span class="time">'+data[i].时间+'</span>'+
			                            '<div class="clear"></div>'+
			                        '</a>'+
				                    '<a href="'+data[i].项目交流贴地址+'" class="news">'+
				                        '<p>'+data[i].项目交流贴内容+'</p>'
				                        '<div class="imgfloat">'+				  
				                            '<span>'+
				                                '<img src="../image/forum-love.png">'+
				                                data[i].点赞人数+
				                            '</span>'+
				                            '<span class="comment">'+
				                                '<img src="../image/talk.png">'+
				                                data[i].评论人数
				                            '</span>'+
				                            '<div class="clear"></div>'+
				                        '</div>'+   
				                    '</a>'+  
			                    '</div>'+
			                '</div>'+
						)
					}
            	}else{
            		// 如果是技术交流贴
					for(var i=0;i<data.length;i++){
						$(".container-box").append(
							'<div class="forum-news">'+
							    '<div class="forum-box">'+
							        '<a href="'+data[i].头像连接+'" class="box1">'+
							            '<img src="'+data[i].头像地址+'">'+							                  
							            '<span class="name">'+data[i].昵称+'</span>'+
							            '<span class="classify">'+
							                '<i class=" icon-tasks"></i>'+
							                data[i].类别+
							            '</span>'+
							            '<div class="clear"></div>'+
							        '</a>'+
							        '<a href="'+data[i].技术交流贴连接+'" class="news">'+
							            '<p>'+data[i].标题+'</p>'+
							            '<p>'+data[i].部分内容+'</p>'+
							            '<div class="imgfloat">'+
							                '<span class="time">'+data[i].时间+'</span>'+
							                '<span>'+
							                    '<img src="../image/forum-love.png">'+
							                    data[i].点赞人数+
							                '</span>'+
							                '<span class="comment">'+
							                    '<img src="../image/talk.png">'+
							                    data[i].评论人数+
							                '</span>'+
							                '<div class="clear"></div>'+
							            '</div>'+   
							        '</a>'+    
							    '</div>'+
							'</div>'+
						)
					}
            	}
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