var offset = 0;
$(".search-btn").click(function(){
	$(".main-content").find(".result").remove();
	$(".main-content").find(".no-result").remove();
	var fluzzyName=$(this).parents(".search-box").find(".clas").val();
	var limit=9;
	var className = $('input:radio:checked').val();//获取搜索的是什么类型的帖子
	$.ajax({
			type:'GET',
			url:'../anonymous/topic/' + className + '?submitFlag=dynamic_query&fluzzyName=' + fluzzyName + '&limit='+limit+ '&offset=' +offset,
			dataType:'json',
			
			success:function(data){			
				if(data != null){
					// 如果是项目交流贴
					if($(".project").attr("checked")){
						
						offset = offset + limit;
						
						for(var i=0;i < data.length;i++){
							$(".container-box").append(
								'<div class="forum-news">'+
			                        '<div class="forum-box"'+
			                            '<a href="'+data[i].person.headUrl+'" class="box1">'+
			                                '<img src="'+data[i].person.headUrl + '">' +			                                  
			                                '<span class="name">' + data[i].person.nickName + '</span>'+
			                                '<span class="time">' + data[i].createTime + '</span>'+
			                                '<div class="clear"></div>'+
			                            '</a>'+
				                        '<a href="../anonymous/topic?submitFlag=single_query&topicId='+ data[i].topicId +'" class="news">' +
				                            '<p>'+data[i].body+'</p>' + 
				                            '<div class="imgfloat">'+				  
				                                '<span>'+
				                                    '<img src="../image/forum-love.png">'+
				                                    data[i].likeCount +
				                                '</span>'+
				                                '<span class="comment">'+
				                                    '<img src="../image/talk.png">'+
				                                    data[i].replyCount + 
				                                '</span>'+
				                                '<div class="clear"></div>'+
				                            '</div>'+   
				                        '</a>'+  
			                        '</div>'+
			                    '</div>'
							)
						}					
					}else{
						
						offset = offset + limit;
						
						// 如果是技术交流贴
						for(var i=0;i<data.length;i++){
							$(".container-box").append(
								'<div class="forum-news">'+
							        '<div class="forum-box">'+
							            '<a href="'+data[i].person.headUrl+'" class="box1">'+
							                '<img src="'+data[i].person.headUrl+'">'+							                  
							                '<span class="name">' + data[i].person.nickName + '</span>'+
							                '<span class="classify">'+
							                    '<i class=" icon-tasks"></i>'+
							                    data[i].title+
							                '</span>'+
							                '<div class="clear"></div>'+
							            '</a>'+
							            '<a href="../anonymous/topic?submitFlag=single_query&topicId='+data[i].topicId+'" class="news">'+
							                '<p>'+data[i].title+'</p>'+
							                '<p>'+ data[i].body.subString(0,50)+'</p>'+
							                '<div class="imgfloat">'+
							                    '<span class="time">'+data[i].createTime+'</span>'+
							                    '<span>'+
							                        '<img src="../image/forum-love.png">'+
							                        data[i].likeCount+
							                    '</span>'+
							                    '<span class="comment">'+
							                        '<img src="../image/talk.png">'+
							                        data[i].replyCount+
							                    '</span>'+
							                    '<div class="clear"></div>'+
							                '</div>'+   
							            '</a>'+    
							        '</div>'+
							    '</div>'
							)
						}
					}
				}else{
					$(".container-box").append("<p class='no-result'>没有搜索到相关项目<p>")
				}
				//$(".type-con").text($("input:radio:checked").attr("class"));
			},
			error:function(jqXHR){
				alert('1');
				alert("系统错误："+jqXHR.status);
			}
	})
})


function more(topicId){
	var limit = 6;
	var className = $('input:radio:checked').val();//获取搜索的是什么类型的帖子
    $.ajax({
        type:'GET',
        url:'../anonymous/topic/' + className + '?submitFlag=dynamic_query&fluzzyName=' + fluzzyName + '&limit='+limit+ '&offset=' +offset,
        contentType:'application/json',
        dataType:'json',
        async:'false',
        success:function(data){
            if(data != null){
            	// 如果是项目交流贴
            	if($(".type-con").text()=='project'){
            		offset = offset + 1;
            		for(var i=0;i<data.length;i++){
						$(".container-box").append(
							'<div class="forum-news">'+
			                    '<div class="forum-box"'+
			                        '<a href="'+data[i].person.headUrl+'" class="box1">'+
			                            '<img src="'+data[i].person.headUrl+'">'+			                                  
			                            '<span class="name">'+data[i].person.nickName + '</span>'+
			                            '<span class="time">'+data[i].createTime +'</span>'+
			                            '<div class="clear"></div>'+
			                        '</a>'+
				                    '<a href="'+data[i].title +'" class="news">'+
				                        '<p>' + data[i].body.subString(0,50) +'</p>' + 
				                        '<div class="imgfloat">'+				  
				                            '<span>'+
				                                '<img src="../image/forum-love.png">'+
				                                data[i].likeCount +
				                            '</span>'+
				                            '<span class="comment">'+
				                                '<img src="../image/talk.png">'+
				                                data[i].replyCount + 
				                            '</span>'+
				                            '<div class="clear"></div>'+
				                        '</div>'+   
				                    '</a>'+  
			                    '</div>'+
			                '</div>'
						)
					}
            	}else{
            		offset = offset + 1;
            		// 如果是技术交流贴
					for(var i=0;i<data.length;i++){
						$(".container-box").append(
							'<div class="forum-news">'+
							    '<div class="forum-box">'+
							        '<a href="'+data[i].person.headUrl+'" class="box1">'+
							            '<img src="'+data[i].person.headUrl+'">'+							                  
							            '<span class="name">'+data[i].nickName+'</span>'+
							            '<span class="classify">'+
							                '<i class=" icon-tasks"></i>'+
							                data[i].title +
							            '</span>' +
							            '<div class="clear"></div>'+
							        '</a>'+
							        '<a href="'+data[i].body+'" class="news">'+
							            '<p>'+data[i].title+'</p>'+
							            '<p>' + data[i].body.subString(0,50) + '</p>'+
							            '<div class="imgfloat">'+
							                '<span class="time">'+data[i].createTime+'</span>'+
							                '<span>'+
							                    '<img src="../image/forum-love.png">'+
							                    data[i].likeCount +
							                '</span>'+
							                '<span class="comment">'+
							                    '<img src="../image/talk.png">'+
							                    data[i].replyCount + 
							                '</span>'+
							                '<div class="clear"></div>'+
							            '</div>'+   
							        '</a>'+    
							    '</div>'+
							'</div>'
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