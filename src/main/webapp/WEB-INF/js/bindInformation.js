var flag=0;

$(function(){
	// 页面加载完成后对于模板1，模板2
	// $.ajax({
	// 	type:'GET',
	// 	url:'',
	// 	dataType:'json',
	// 	success:function(data){
	// 		if(data!=null){
	// 			$(".model1-box").html(填);
	// 			$(".model2-box").html(填);
	// 		}else{
	// 			alert("出现错误");
	// 		}
	// 	},
	// 	error:function(jqHXR){
	// 		alert("出现错误"+jqHXR.status);
	// 	}
	// })
	
	$(".name").blur(function(){
		var name=/[^\u4e00-\u9fa5]/g;
		if(name.test($(this).val())){
			$(this).parents(".sbox").find(".interpret").html("<span></span>有非法字符");
			flag=0;
		}

		else if($(this).val().length>4||$(this).val().length<2){
			if($(this).val().length==0){
				$(this).parents(".sbox").find(".interpret").html("<span></span>姓名不能为空");
			}else{
				$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的真实姓名");
			}
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("<span></span>");
			flag=1;
		}
	})

	$(".school").blur(function(){
		var school=/[^\u4e00-\u9fa5]/g;
		if(school.test($(this).val())||$(this).val().length<5||$(this).val().indexOf("学")<0){
			if($(this).val().length==0){
				$(this).parents(".sbox").find(".interpret").html("<span></span>学校不能为空");
			}else{
				$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的学校全称");
			}
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("<span></span>");
			flag=1;
		}
	})

	$(".major").blur(function(){
		var major=/[\u4e00-\u9fa5]/g;
		if(major.test($(this).val())||$(this).val().indexOf('(')>0&&$(this).val().indexOf(')')>0){
			if($(this).val().length>3){
				$(this).parents(".sbox").find(".interpret").html("<span></span>");
				flag=1;
			}else{
				$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的专业全称");
				flag=0;
			}
		}

		else if($(this).val().length==0){
			$(this).parents(".sbox").find(".interpret").html("<span></span>专业名称不能为空");
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的专业全称");
			flag=0;
		}
	})  

	$(".tel").blur(function(){
		var tel=/[^\d]/g;
		if(tel.test($(this).val())){
			$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的联系方式");
			flag=0;
		}

		else if($(this).val().length!=11){
			$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的联系方式");
			flag=0;
		}

		else if($(this).val().replace(/(^\s*)|(\s*$)/g, "").length==0){
			$(this).parents(".sbox").find(".interpret").html("<span></span>联系方式不能为空");
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("<span></span>");
			flag=1;
		}
	}) 

	$(".mail").blur(function(){
		var mail= /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
		if(!mail.test($(this).val())){
			$(this).parents(".sbox").find(".interpret").html("<span></span>请输入正确的邮箱");
			flag=0;
		}

		else if($(this).val().length==0){
			$(this).parents(".sbox").find(".interpret").html("<span></span>邮箱不能为空");
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("<span></span>");
			flag=1;
		}
	}) 

	$(".resume").blur(function(){
		if($(this).val().length==0){
			$(this).parents(".sbox").find(".interpret").html("简历不能为空");
			flag=0;
		}

		else{
			$(this).parents(".sbox").find(".interpret").html("");
			flag=1;
		}
	}) 

	$(".bind-btn").click(function(e){
		for(var i=0;i<$("input[type='text']").length;i++){
			if($("input[type='text']").eq(i).val()=="")
				flag=0;
		}
		alert($("textarea").val())

		if($("textarea").val()==0){
			flag=0;
		}

		if(flag==0){
			stopDefault(e);
		}else{
			var person = {
					name:$(".name").val(),
					school:$(".school").val(),
					major:$(".major").val(),
					phoneNumber:$(".tel").val(),
					mail:$(".mail").val(),
					defaultResume:$(".resume").val()
				};
			$.ajax({
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				url:'../anonymous/profile?submitFlag=update',
				data:JSON.stringify(person),
				success:function(data){
					if(data!=null){
						window.location.href='../index'
					}else{
						alert("信息绑定失败");
					}
				},
				error:function(jqHXR){
					alert("信息绑定失败");
				}
			});
		}
	})

})

// 点击模板
// $(".model1").click(function(){
// 	var content=$(".model1-box").text();
// 	$("textarea").html(content);
// 	$("textarea").focus();
// })

// $(".model2").click(function(){
// 	var content=$(".model2-box").text();
// 	$("textarea").html(content);
// 	$("textarea").focus();
// })
