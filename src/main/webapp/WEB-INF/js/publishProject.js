$(function(){
	$("[type='text']").focus(function(){
		$(this).parent().find(".inter").css({'opacity':'1'});
	})
})