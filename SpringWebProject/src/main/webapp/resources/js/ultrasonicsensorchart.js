var ultrasonicSensorChart;

$(function() {
	var gaugeOptions = {

		    chart: {
		        type: 'solidgauge',
		        events: {
					load: requestUltrasonicSensorChartData //함수이름(차트객체가 생성되고 나서 해당 함수를 실행시키는 코드)
				}
		    },

		    title: null,

		    pane: {
		        center: ['50%', '85%'],
		        size: '140%',
		        startAngle: -90,
		        endAngle: 90,
		        background: {
		            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
		            innerRadius: '60%',
		            outerRadius: '100%',
		            shape: 'arc'
		        }
		    },

		    tooltip: {
		        enabled: false
		    },

		    // the value axis
		    yAxis: {
		        stops: [
		            [0.1, '#55BF3B'], // green
		            [0.5, '#DDDF0D'], // yellow
		            [0.9, '#DF5353'] // red
		        ],
		        lineWidth: 0,
		        minorTickInterval: null,
		        tickAmount: 2,
		        title: {
		            y: -90    //거리 위치 올리기
		        },
		        labels: {
		            y: 16
		        }
		    },

		    plotOptions: {
		        solidgauge: {
		            dataLabels: {
		                y: 5,
		                borderWidth: 0,
		                useHTML: true
		            }
		        }
		    }
		};
	
	
	
	ultrasonicSensorChart=new Highcharts.chart('ultraSensorChartContainer', Highcharts.merge(gaugeOptions, {
	    yAxis: {
	        min: 0,
	        max: 200,
	        title: {
	            text: '장애물과의 거리'
	        }
	    },

	    credits: {
	        enabled: false
	    },

	    series: [{
	        name: 'Speed',
	        data: [0],
	        dataLabels: {
	            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
	                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'white') + '">{y}</span><br/>' +
	                   '<span style="font-size:12px;color:silver">cm</span></div>'
	        },
	        tooltip: {
	            valueSuffix: ' km/h'
	        }
	    }]

	}));
	
	/*
	setInterval(function () {
	    // Speed
	    var point,
	        newVal,
	        inc;

	    if (chartSpeed) {
	        point = chartSpeed.series[0].points[0];
	        inc = Math.round((Math.random() - 0.5) * 100);
	        newVal = point.y + inc;

	        if (newVal < 0 || newVal > 200) {
	            newVal = point.y - inc;
	        }

	        point.update(newVal);
	    }

	    // RPM
	    if (chartRpm) {
	        point = chartRpm.series[0].points[0];
	        inc = Math.random() - 0.5;
	        newVal = point.y + inc;

	        if (newVal < 0 || newVal > 5) {
	            newVal = point.y - inc;
	        }

	        point.update(newVal);
	    }
	}, 2000);
	
	*/
});






function requestUltrasonicSensorChartData(){
	console.log("시작스");
	var ws = new WebSocket("ws://"+location.host+"/SpringWebProject/websocket/ultrasonicsensor");
	ws.onmessage = function(event){
		var data= JSON.parse(event.data); //JSON.parse는 문자열로 되어있는 제이슨( '{"xxx":"value"}')을 javascript객체( {"xxx":"value"} )로 만들어주는 작업을함
		var point,value;
		value=data.distance;
	    if (ultrasonicSensorChart) {
	        point = ultrasonicSensorChart.series[0].points[0];
	      

	        if (value > 200) {
	            value=200;
	        }
	     

	        point.update(value);
	    }
		//		var series =ultrasonicSensorChart.series[0];
//		point.update(data.distance);
//		var shift= series.data.length > 20; //화면이 이동되는 순간은 20개 이상일때 true를 리턴, 이하일때는 false
//		series.addPoint([data.time,data.distance],true,shift); //[x축,y축]
	};
}


