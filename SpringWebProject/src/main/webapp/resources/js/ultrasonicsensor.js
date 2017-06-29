$(function ultrasonicsensor(command,angle) {
			var json = {"command":command, "angle":angle};		
			var ws = new WebSocket("ws://"+location.host+"/SpringWebProject/websocket/ultrasonicsensor");
			var data;
			ws.onmessage = function(event){
			data= JSON.parse(event.data); //JSON.parse는 문자열로 되어있는 제이슨( '{"xxx":"value"}')을 javascript객체( {"xxx":"value"} )로 만들어주는 작업을함
			$("#ultrasonicsensorStatus").html("angle"+data.angle+";"+"distance"+data.distance+";");
			//	var shift= series.data.length > 50; //화면이 이동되는 순간은 20개 이상일때 true를 리턴, 이하일때는 false
			//	series.addPoint([data.time,data.photoresistor],true,shift); //[x축,y축]
			};

/*
				$.ajax({
					url:"http://" + location.host + "/SensingWebProject/ultrasonicsensor",
					data: json,
					method: "post",
					success: function(data) {
						if(data.result == "success") {
							$("#ultrasonicsensorStatus").html("angle"+data.angle+";"+"distance"+data.distance+";");
						}
					}
				});
				*/
			});