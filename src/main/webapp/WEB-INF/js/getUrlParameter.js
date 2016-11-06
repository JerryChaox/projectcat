function getUrlParameter(str, key){
    var re = new RegExp(key + '=([^&]*)(?:&)?');  
    return str.match(re) && str.match(re)[1];
}
