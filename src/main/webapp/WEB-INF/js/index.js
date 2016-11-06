(function (win){ 
	var callboarTimer; 
	var callboard = $('#callboard'); 
	var callboardUl = callboard.find('ul'); 
	var callboardLi = callboard.find('li'); 
	var liLen = callboard.find('li').length; 
	var initHeight = callboardLi.first().outerHeight(true); 
	win.autoAnimation = function (){ 
		if (liLen <= 1) return; 

		var self = arguments.callee; //arguments.callee是指调用自身
		var callboardLiFirst = callboard.find('li').first(); 
		callboardLiFirst.animate({ 
			marginTop:-initHeight
		}, 500, function (){ 
			clearTimeout(callboarTimer); 
			callboardLiFirst.appendTo(callboardUl).css({marginTop:0}); 
			callboarTimer = setTimeout(self, 3000); 
		}); 
	} 

}(window)); 
setTimeout(window.autoAnimation, 3000); 