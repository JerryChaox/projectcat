function getUrlParameter(str, key){
    var re = new RegExp(key + '=([^&]*)(?:&)?');  
    return str.match(re) && str.match(re)[1];
}

var anonymousMapping = '../anonymous';
var normalMapping = '../normal';
var teacherMapping = '../teacher';
var studentMapping = '../student';

//add url parameters
String.prototype.appendParam = function(name, value){
	if(name === null && value === null) {
		return this;
	}
	
	if(name instanceof Array && value instanceof Array) {
		var url = this;
		var length = name.length;
		if(name.length != value.length) {
			alert('name.length is not equal to value.length');
			return null;
		} else {
			for(var i = 0; i < length; i++) {
			    if(this.indexOf('?') >= 0) {
			    	url = url + '&' + name[i] + '=' + value[i];
			    } else {
			    	url =  url + '?' + name[i] + '=' + value[i];
			    }
			}
			return url;
		}	
	} else {
		if(this.indexOf('?') >= 0) {
	    	return this + '&' + name + '=' + value;
	    } else {
	    	return this + '?' + name + '=' + value;
	    }
	} 
}  

//add url index
String.prototype.appendIndex = function(index){     
    return this + '/' + index;
}  

//get any url
function getRequestUrl(mappingType, resourceIndex, operationType,name, value) {
	var url = eval(mappingType + 'Mapping')
	.appendIndex(resourceIndex)
	.appendParam('submitFlag', operationType);
	return url.appendParam(name, value);
}