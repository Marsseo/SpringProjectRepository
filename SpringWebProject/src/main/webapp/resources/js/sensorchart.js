var sensorchart;
var temperaturevalue;
var gasvalue;
var photoresistorvalue;
var ws1;
var ws2;
var ws3;
var ws4;
var series1;
var series2;
var series3;


function thermistoronly() {
	series1.show();
	series2.hide();
	series3.hide();
	
}

function photoresistoronly() {
	series1.hide();
	series2.show();
	series3.hide();
}

function gasonly() {
	series1.hide();
	series2.hide();
	series3.show();
}

function showall() {
	series1.show();
	series2.show();
	series3.show();
}

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
		yAxis : {
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
			// type : 'column',
			data : []
		}, {
			name : "가스",
			data : []
		} ]
	});
});

function requestSensorData() {
<<<<<<< HEAD
	ws1 = new WebSocket("ws://" + location.host
			+ "/SpringWebProject/websocket/thermistorsensor");
	ws1.onmessage = function(event) {
		var data = JSON.parse(event.data);

		if (data.temperature != temperaturevalue) {
			$('#thermistorvalue').html("현재온도: " + data.temperature + " ºC");
=======
	var ws = new WebSocket("ws://" + location.host + "/SpringWebProject/websocket/thermistorsensor");

	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		if( data.temperature > 25) {
			$('#thermistorimg').html("<img style='height: 100px;' src='/SpringWebProject/resources/image/hot.jpg'/>");
		} else {
			$('#thermistorimg').html("<img style='height: 100px;' />");
>>>>>>> origin/master
		}
		temperaturevalue = data.temperature;

		// if( data.temperature > 25) {
		// $('#thermistorimg').html("<img style='height: 100px;'
		// src='/SpringWebProject/resources/image/hot.jpg'/>");
		// } else {
		// $('#thermistorimg').html("<img style='height: 100px;' />");
		// }

		series1 = sensorChart.series[0];
		var shift = series1.data.length > 20;
		series1.addPoint([ data.time, data.temperature ], true, shift);
<<<<<<< HEAD
=======

>>>>>>> origin/master
	};
	ws2 = new WebSocket("ws://" + location.host
			+ "/SpringWebProject/websocket/photoresistorsensor");
	ws2.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var strValue;
		if (data.photoresistor < 20) {
			strValue = "아주밝음";
		} else if (data.photoresistor < 100) {
			strValue = "밝음";
		} else if (data.photoresistor < 150) {
			strValue = "보통";
		} else {
			strValue = "어두움";
		}

		if (data.photoresistor != photoresistorvalue) {
			$('#photoresistorvalue').html(
					"현재밝기: " + strValue + " (" + data.photoresistor + ")");
		}
		photoresistorvalue = data.photoresistor;

		series2 = sensorChart.series[1];
		var shift = series2.data.length > 20;
		series2.addPoint([ data.time, data.photoresistor ], true, shift);
	};
	ws3 = new WebSocket("ws://" + location.host
			+ "/SpringWebProject/websocket/gassensor");
	ws3.onmessage = function(event) {
		var data = JSON.parse(event.data);

		var strValue;
		if (data.gas < 40) {
			strValue = "아주좋음";
		} else if (data.gas < 80) {
			strValue = "보통";
		} else if (data.gas < 150) {
			strValue = "가스검출";
		} else {
			strValue = "가스심각";
		}

		if (data.gas != gasvalue) {
			$('#gasvalue').html("가스상태: " + strValue + " (" + data.gas + ")");
		}
		gasvalue = data.gas;

		series3 = sensorChart.series[2];
		var shift = series3.data.length > 20;
		series3.addPoint([ data.time, data.gas ], true, shift);
	};

	ws4 = new WebSocket("ws://" + location.host
			+ "/SpringWebProject/websocket/trackingsensor");
	ws4.onmessage = function(event) {
		var data = JSON.parse(event.data);
		if (data.tracking == "white") {
			$('#trackingsensor').css("background-color", "white");
			$('#trackingsensor').css("color", "black");
		} else {
			$('#trackingsensor').css("background-color", "black");
			$('#trackingsensor').css("color", "white");
		}
	};
}
