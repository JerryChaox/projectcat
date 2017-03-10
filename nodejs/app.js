var xtpl = require('xtpl');
xtpl.renderFile('./views/index.xtpl',{
    title:'render my xtpl'
},function(error,content){
    console.log(content); 
});
