$(function(){
	// 设置下拉菜单的宽度
	var clientWidth=$(window).width();
	$(".choose li ul").css({'width':clientWidth})

	// 下拉菜单
	for(var i=0;i<$(".navdown").length;i++){
		var he=$(".navdown").eq(i).find("ul").height();
		$(".downbox").eq(i).css({'height':he});
	}
	navDown();

	// 遮罩层大小
	var daskHeight;
	if($(window).height()>$(document.body).height()){
		daskHeight=$(window).height();
	}else{
		daskHeight=$(document.body).height();
	}
	$(".dask").css({'width':$(window).width(),'height':daskHeight})
})

function navDown(){
	$(".choose .navdown").click(function(){
		if($(this).hasClass("on")){
			$(this).find("ul").stop(true,true);
			$(".navdown ul").css({'top':'-51px'});
			$(this).find("ul").animate({'top':'0'},200);
			$(this).find(".downbox").css({'visibility':'visible'})
			$(this).find(".downbox").fadeIn(200);
			$(this).find("img").attr("src","../image/toptrangle.png");
			$(this).siblings().addClass("on");
			$(this).siblings().find("img").attr("src","../image/trangle.png");
			$(this).siblings().find(".downbox").hide();
			$(".dask").removeClass("disnone");
			$(this).removeClass("on");
				
		}else{
			$(".navdown").stop(true,true);
			$(".navdown").css({'top':'-51px'});
			$(".downbox").hide();
			$(this).find("ul").animate({'top':'-51px'},200);
			$(this).find(".downbox").fadeOut(200);
			$(this).find("img").attr("src","../image/trangle.png");
			$(".dask").addClass("disnone");
			$(this).addClass("on");
		}
	})

	$(".choose li ul li a").click(function(){
		var text=$(this).text();
		$(this).parents(".navdown").find(".chooselist").html(text);
		$(this).parents(".navdown").removeClass("on");
		$(this).parents(".downbox").fadeOut();
		$(".navdown a img").attr("src","../image/trangle.png");
		$(".dask").addClass("disnone");
		$(this).parents(".downbox").find("ul").css({'top':'-51px'});
	})

	$(".dask").click(function(){
		$(".navdown").stop(true,true);
		$(".navdown ul").animate({'top':'-51px'},200);
		$(".downbox").hide();
		$(".navdown a img").attr("src","../image/trangle.png");
		$(".dask").addClass("disnone");
		$(".navdown").addClass("on");
	})

	$(".search-btn").click(function(){
		$(".main-content").find(".result").remove();
		$(".main-content").find(".no-result").remove();
		var fluzzyName=$(this).parent(".search-box").find(".clas").val();
		var limit=9;
		var offset=0;
		$.ajax({
				type:'GET',
				url:'../anonymous/project?submitFlag=dynamic_query&fluzzyName=' + fluzzyName + '&limit='+limit+ '&offset=' +offset,
				dataType:'json',
				success:function(data){
					if(data != null){
						for(var i=0;i<data.length;i++){
							$(".main-content").append(
									'<a href="projectDetails.html?projectId=' + data[i].projectId + '"' + ' class="result container border-box">'+
										'<img src="../image/searchpro/project.jpg" class="projectimg">'+
										'<div class="details border-box">'+
											'<p class="project">'+ data[i].projectName +'</p>'+	
											'<p class="detail">'+
												'<span><i class=" icon-map-marker"></i>广州</span>'+
												'<span><i class="icon-user"></i>'+ data[i].background +'</span>'+
												'<span><i class=" icon-lightbulb"></i>'+ data[i].major +'</span>'+
												'<br />'+
												'<span class="first"><i class=" icon-magic"></i>已有' + data[i].enrolledCount +'人报名</span>'+
											'</p>'+
										'</div>'+
										'<div class="clear">'+
										'</div>'+
										'<img src="../image/searchpro/tag.png" class="tag"/>'+
									'</a>'
								)
						}
					}else{
						$(".main-content").append("<p class='no-result'>没有搜索到相关项目<p>")
					}
				},
				error:function(jqXHR){
					alert("系统错误："+jqXHR.status);
				}
			})
	})
}


