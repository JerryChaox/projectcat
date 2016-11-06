// 网页内容和最上面固定的蓝色边边的重合
$(function(){
	onTop();
})
function onTop(){
	var header=$(".header").outerHeight();
	$(".header-box").css({'height':header})
}

function stopDefault( e ) { 
	if ( e && e.preventDefault ) 
		e.preventDefault(); 
	else 
		window.event.returnValue = false; 
	return false; 
} 