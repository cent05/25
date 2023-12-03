import { createServer } from 'http';
createServer(function(req,res){
    res.writeHead(200,{'content-type':'text/html'});
    res.write("Server Created");
    console.log("Server Created");
    res.end();
}).listen(8080);