$(function(){
	$(window).scroll(function(){
	    loadmore();
	});
	
})


function loadmore(){
	var url = location.search;
	objectIdName = url.match(/[^[&|?}]\w*Id/g)
	var objectId = getUrlParameter(url, objectIdName);
    var body_height=$(document).height();
    var scroll_height=$(window).height();
    var scroll_top=$(window).scrollTop();
    if(scroll_top+scroll_height>=body_height){
    	more(objectId);
    }
}