$(function(){
	var flag=0;
	$(".title").focus(function(){
		$(this).next().css({'opacity':'1'});
	})

	$(".title").keyup(function(){
		var num=$(this).val().length
		$(this).parent().find(".aa").text(num);
		if($(this).val().length>$(this).parent().find(".most").text()){
			flag=0;
			$(this).parent().find(".aa").css({'color':'red'})
		}
	})

	$(".publish").click(function(e){
		if($(".title").val()!=""&&$("textarea").val()!=""){
			flag=1;
		}

		if(flag==0){
			stopDefault(e);
			alert("请正确填写项目内容");
		}else{
			var topic={
				title:$(".title").val(),
				body:$("textarea").val(),
				className:'tech_module'
			}
			$.ajax({
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				url:'../normal/topic?submitFlag=create',
				data:JSON.stringify(topic),
				success:function(data){
					if(data!=null){
						alert("报名成功")
					}else{
						alert("出现错误")
					}
				},
				error:function(jqXHR){
					alert("出现错误")
				}
			});
		}
	})
})