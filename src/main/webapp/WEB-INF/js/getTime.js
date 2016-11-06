function getTime(){
	// 获取当前时间
        var mydate=new Date();
        var year=mydate.getFullYear();
        var month=mydate.getMonth()+1;
        var day=mydate.getDate();
        var hour=mydate.getHours();
        var mimute=mydate.getMinutes();
        var time=year+'/'+month+'/'+day+' '+hour+':'+mimute;

        return time;
}