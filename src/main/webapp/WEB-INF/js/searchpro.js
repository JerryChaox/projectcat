$(function(){
	$(".search-btn").click(function(){
		alert($(this).parent(".search-box").find(".clas").val());

		$(".main-content").find(".result").remove();
		$(".main-content").find(".no-result").remove();
		$(".main-content").append("<p class='no-result'>没有搜索到相关项目<p>")
	})
})

