$(function(){
	$.ajax{
		var webPath = "www.projectcat.top/project";
		type:'GET',
		url: webPath + '/normal/profile?submigFlag=query',
		dataType:'json',
		success:function(data){
			if(data.status){
				$(".school").html(data.school);
				$(".profession").html(data.profession);
				$(".year").html(data.grade);
				$(".interest").html(data.interest);
				$(".skilled").html(data.major);
				$(".resume").html(data.defaultResume);
			}else{
				alert("出现错误")
			}
		},
		error:function(jqXHR){
				alert("系统错误："+jqXHR.status);
		}
	}
})
