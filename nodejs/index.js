var express = require('express');
var app = express();

var ModelProxy = require( 'modelproxy-promise' ); 
var path = require('path').resolve( __dirname, './interface_sample.json' );
ModelProxy.init(path);
var searchModel = new ModelProxy( {
    searchItems: 'Search.getItems'
} );

app.use(express.static('public'));
app.set('views', './views');
app.set('view engine', 'xtpl');


app.get('/profile',function(req, res, next) {  
	   searchModel.searchItems({personId:'1'})
    	  .then(function(data) {
	    res.render('person', data);
    	  })
    	 .catch(
           error => console.error(error)
    	 );
});

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});



