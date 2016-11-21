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


//
function getProjectByProjectId(projectId){    
	return anonymousMapping
    .appendIndex('project')
    .appendParam('submitFlag', 'single_query')
    .appendParam('projectId',projectId);
}

function getTopicByTopicId(topicId){
    return anonymousMapping
    .appendIndex('topic')
    .appendParam('submitFlag', 'single_query')
    .appendParam('topicId',topicId);
}

function getAllProjectList(offset, limit){
    return anonymousMapping
    .appendIndex('project')
    .appendParam('submitFlag', 'query')
    .appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getAllTopicList(offset, limit){
    return anonymousMapping
    .appendIndex('topic')
    .appendParam('submitFlag', 'query')
    .appendParam('offset',offset)
    .appendParam('limit',limit);
}

function dynamicGetProjectList(fluzzyName, offset, limit){
    return anonymousMapping
    .appendIndex('project')
    .appendParam('fluzzyName', fluzzyName)
    .appendParam('submitFlag', 'query')
    .appendParam('offset',offset)
    .appendParam('limit',limit);
}

function dynamicGetTopicList(className, fluzzyName, offset, limit){
	return anonymousMapping
    .appendIndex('topic')
    .appendIndex(className)
    .appendParam('submitFlag', 'query')
    .appendParam('fluzzyName', fluzzyName)
    .appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getTopicReplyList(topicId, offset, limit) {
	return anonymousMapping
	.appendIndex('topic')
	.appendIndex(topicId)
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

function bindingProfile() {
	return anonymousMapping
	.appendIndex('profile')
	.appendParam('submitFlag','update');
}

function getOtherProfile(othersId) {
	return normalMapping
	.appendIndex('othersProfile')
	.appendParam('submitFlag', 'query')
	.appendParam('othersId');
}

function getSelfProfile() {
	return normalMapping
	.appendIndex('profile')
	.appendParam('submitFlag', 'query');
}

function getSelfTopicList(offset, limit) {
	return normalMapping
	.appendIndex('topic')
	.appendParam('submitFlag', 'query')
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getSelfReplyList(offset, limit) {
	return normalMapping
	.appendIndex('reply')
	.appendParam('submitFlag', 'query')
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getTopicStarList(offset, limit) {
	return normalMapping
	.appendIndex('star')
	.appendIndex('topic')
	.appendParam('submitFlag', 'query')
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getNewsStarList(offset, limit) {
	return normalMapping
	.appendIndex('star')
	.appendIndex('news')
	.appendParam('submitFlag', 'query')
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

function getFollowingList(offset, limit) {
	return normalMapping
	.appendIndex('follow')
	.appendParam('submitFlag', 'query')
	.appendParam('offset',offset)
    .appendParam('limit',limit);
}

