var sensorchart;

$(function() {
	sensorChart = new Highcharts.Chart({
		chart : {
			renderTo : "sensorChartContainer",
			type : "spline",
			events : {
				load : requestSensorData
			}
		},
		colors : [ 'red', 'yellow', 'purple' ],
		title : {
			text : "Sensor Charts"
		},
		xAxis : {
			type : "datetime",
			tickPixelInterval : 100,
			minRange : 20 * 1000
		},
		yAxis :  {
			title : {
				text : "데이터",
				margin : 30
			}
		},
		series : [ {
			name : "온도(ºC)",
			data : []
		}, {
			name : "조도",	
//			type : 'column',
			data : []
		}, {
			name : "가스",
			data : []
		} ]
	});
});

function requestSensorData() {
	var ws = new WebSocket("ws://" + location.host
			+ "/SensingCarRemoteWebControl/websocket/thermistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		console.log("Data: " + data.temperature);
		if( data.temperature > 25) {
			$('#thermistorimg').html("<img style='height: 100px;' src='/SpringWebProject/resources/image/hot.jpg'/>");
		} else {
			$('#thermistorimg').html("<img style='height: 100px;' />");
		}
		var series1 = sensorChart.series[0];
		var shift = series1.data.length > 20;
		series1.addPoint([ data.time, data.temperature ], true, shift);
	};
	var ws2 = new WebSocket("ws://" + location.host
			+ "/SensingCarRemoteWebControl/websocket/photoresistorsensor");
	ws2.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series2 = sensorChart.series[1];
		var shift = series2.data.length > 20;
		series2.addPoint([ data.time, data.photoresistor ], true, shift);
	};
	var ws3 = new WebSocket("ws://" + location.host
			+ "/SensingCarRemoteWebControl/websocket/gassensor");
	ws3.onmessage = function(event) {
		var data = JSON.parse(event.data);		
		var series3 = sensorChart.series[2];
		var shift = series3.data.length > 20;
		series3.addPoint([ data.time, data.gas ], true, shift);
	};
	var ws4 = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/trackingsensor");
	ws4.onmessage = function(event) {
		var data = JSON.parse(event.data);
		console.log(data.color);
		if(data.color == "white"){
			$('#trackingsensor').css("background-color", "white");
		} else {
			$('#trackingsensor').css("background-color", "black");
		}	
	};
}
