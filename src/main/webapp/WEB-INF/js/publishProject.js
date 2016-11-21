$(function(){
	var flag=0;
	$(".input").focus(function(){
		$(this).parent().find(".inter").css({'opacity':'1'});
		$(this).parent().next().css({'opacity':'1'});
	})

	// 计算字数
	$(".input").keyup(function(){
		var num=$(this).val().length
		$(this).parent().find(".word").text(num);
		if($(this).val().length>$(this).parent().find(".most").text()){
			flag=0;
			$(this).parent().find(".word").css({'color':'red'})
		}
	})

	// 获取当前时间
	var mydate=new Date();
	var year=mydate.getFullYear();
	var month=mydate.getMonth()+1;
	var day=mydate.getDate();
	for(var i=0;i<3;i++){
		if($(".year").find("option").eq(i).text()==year){
			$(this).attr("selected","selected")
		}
	}
	
	$(".month").val($(".month option").eq(month-1).text());
	$(".day").val($(".day option").eq(day-1).text());

	$(".submit").click(function(e){
		var selectYear=parseInt(deleteChinese($(".year option:selected").text()));
		var selectMonth=parseInt(deleteChinese($(".month option:selected").text()));
		var selectDay=parseInt(deleteChinese($(".day option:selected").text()));
		alert(month);
		alert(selectMonth)
		if(selectYear>year){
			flag=1;
		}else if(selectYear==year&&selectMonth>month){
			flag=1;
		}else if(selectYear==year&&selectMonth==month&&selectDay>day){
			flag=1;
		}else{
			flag=0;
		}

		for(var i=0;i<$(".input").length;i++){
			if($(".input").eq(i)==""){
				flag=0;
			}else{
				flag=1;
			}
		}

		if(flag==0){
			stopDefault(e);
			alert("请正确填写项目内容")
		}else{
			var project={
				projectName:$(".name").val(),
				projectAbstract:$(".pro-intro").val(),
				managerInfo:$(".person-intro").val(),
				maxEnrollCount:$(".number").val(),
				background:$(".background").val(),
				major:$(".major").val(),
				requirement:$(".request").val(),
				deadline:($(".year option:selected").text() + '-' + $(".month option:selected").text() + '-' + $(".day option:selected").text())
			}
			alert(project.deadline);
			$.ajax({
				type:'POST',
				dataType:'json',
				contentType:'application/json',
				url:getRequestUrl('teacher', 'project', 'create', null, null),
				data:JSON.stringify(project),
				success:function(){
					alert('success');
					window.location.href="";
				},
				error:function(jqXHR){
					alert("出现错误");
				}
			})
		}
	})
})

function deleteChinese(content){
	var title =content
	var reg=/[\u4E00-\u9FA5]/g;
	var result=title.replace(reg,'');
	return result;
}