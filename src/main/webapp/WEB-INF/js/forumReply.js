$(function(){
	// 页面信息
	$.ajax({
        type:'GET',
        url:'',
        dataType:'json',
        success: function(data){
            if(data!=null){
                var tmpltxt=doT.template($("#reply").text());
                $("#reply-box").html(tmpltxt(data));
            }else{
                alert("加载失败！")
            }
        },
        error:function(jqHXR){
            alert("出现错误"+jqHXR.status)
        }          
    })

    //评论
    $(".publish").click(function(){
    	var content=$(".textarea").text();
    	$.ajax({
    		type:'POST',
    		dataType:'json',
    		data:{
    			reply:$(".textarea").text();
    		}
    		success:function(){
    			if(data!=null){
	    			if(data.本人id==data.楼主id){
	    				$(".discuss-box").append(
			    		'<p class="discussList"><a class="people" href="#"><span class="name">'+
			    		"louzu"+'</span><span class="host">楼主</span>：</a>'+content+'</p>');//content不需要修改
			    	}else{
			    		$(".discuss-box").append(
			    		'<p class="discussList"><a class="people" href="'+data.点击昵称连接+'"><span class="name">'+
			    		"louzu"+'</span>：</a>'+data.content+'</p>');
			    	}
			    	$(".textarea").text("");
		    	}else{
		    		alert("出现错误");
		    	}
    		},
    		error:function(jqHXR){
    			alert("出现错误"+jqHXR);
    		}
    	})
    })
})