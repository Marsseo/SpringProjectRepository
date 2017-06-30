var ultrasonicsensorchart;

$(function() {
	 //차트객체 만드는 코드
	ultrasonicSensorChart= new Highcharts.chart('container', {

		    chart: {
		        polar: true,
		        events: {
					load: requestUltrasonicSensorChartData //함수이름(차트객체가 생성되고 나서 해당 함수를 실행시키는 코드)
				}
		    },

		    title: {
		        text: 'UltraSonic Chart'
		    },

		    pane: {
		        startAngle: 0,
		        endAngle: 360
		    },

		    xAxis: {
		        tickInterval: 45,
		        min: 0,
		        max: 360,
		        labels: {
		            formatter: function () {
		                return this.value + '°';
		            }
		        }
		    },

		    yAxis: {
		        min: 0
		    },

		    plotOptions: {
		        series: {
		            pointStart: 0,
		            pointInterval: 45
		        },
		        column: {
		            pointPadding: 0,
		            groupPadding: 0
		        }
		    },

		    series: [{
		        type: 'column',
		        name: 'Column',
		        data: [],
		        pointPlacement: 'between'
		    }, {
		        type: 'line',
		        name: 'Line',
		        data: [1, 2, 3, 4, 5, 6, 7, 8]
		    }, {
		        type: 'area',
		        name: 'Area',
		        data: [1, 8, 2, 7, 3, 6, 4, 5]
		    }]
		});

});



function requestUltrasonicSensorChartData(){
	console.log("시작스");
	var ws = new WebSocket("ws://"+location.host+"/SpringWebProject/websocket/ultrasonicsensor");
	ws.onmessage = function(event){
		var data= JSON.parse(event.data); //JSON.parse는 문자열로 되어있는 제이슨( '{"xxx":"value"}')을 javascript객체( {"xxx":"value"} )로 만들어주는 작업을함
		var series =ultrasonicSensorChart.series[0];
		var shift= series.data.length > 20; //화면이 이동되는 순간은 20개 이상일때 true를 리턴, 이하일때는 false
		series.addPoint([data.time,data.distance],true,shift); //[x축,y축]
	};
}