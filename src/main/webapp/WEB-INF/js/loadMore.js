$(function(){
	$(window).scroll(function(){
	    loadmore();
	});
	
})


function loadmore(){
	var url = location.search;
	var topicId = getUrlParameter(url, 'topicId');
    var body_height=$(document).height();
    var scroll_height=$(window).height();
    var scroll_top=$(window).scrollTop();
    if(scroll_top+scroll_height>=body_height){
    	more(topicId);
    }
}