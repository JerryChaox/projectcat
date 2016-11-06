$(function(){
	$(".publish").click(function(e){
		var topic= {
				classId:'1',
				body:$("textarea").val()
		}
		if($("textarea").val()==""){
			stopDefault(e);
		}else{
			$.ajax({
				type:'POST',
				url:'../normal/topic?submitFlag=create',
				contentType:'application/json',
				dataType:'json',
				data:JSON.stringify(topic),
				success:function(data){
					// 发帖成功跳转页面
					window.location.href="";
				},
				error:function(jqXHR){
					alert("出现错误"+jqXHR);
				}
			});
		}
	})
})