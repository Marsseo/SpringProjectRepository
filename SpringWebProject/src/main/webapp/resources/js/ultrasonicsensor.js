window.onload = function(){
	var test=ultrasonicsensor();
var ws = new WebSocket("ws://"+location.host+"/SpringWebProject/websocket/ultrasonicsensor");
//ws.onopen=handleOnOpen;
ws.onmessage=handleOnMessage;
//ws.onclose=handleOnClose;

function handleOnMessage(event){
	 var data= JSON.parse(event.data); //JSON.parse는 문자열로 되어있는 제이슨( '{"xxx":"value"}')을 javascript객체( {"xxx":"value"} )로 만들어주는 작업을함
		$("#ultrasonicsensorStatus").html("distance :"+data.distance+";");
	
}}