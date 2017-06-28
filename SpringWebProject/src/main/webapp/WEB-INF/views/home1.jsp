<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"	content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"rel="stylesheet" type="text/css" />
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"	type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/themes/gray.js"></script> 		
		<!-- 기존 센서 -->
		<%-- 
		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rgbled.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/laseremitter.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensor.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/fronttire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/backtire.js"></script>
		 --%>
		<!-- 기존 센서 차트-->
		<%-- 
		<script src="<%=application.getContextPath()%>/resources/js/thermistorsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/trackingsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/photoresistorsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/gassensorchart.js"></script>
		 --%>
		<!-- 추가 센서 차트-->
		<script src="<%=application.getContextPath()%>/resources/js/sensorchart.js"></script>
		<link href="<%= application.getContextPath() %>/resources/css/roundslider.min.css" rel="stylesheet" />
		<script src="<%= application.getContextPath() %>/resources/js/roundslider.min.js"></script>
		<style>@media screen and (min-width: 480px) { #title { height: 10px; } }</style>
		<script src="<%=application.getContextPath()%>/resources/js/backtire.js"></script>
		<link href="<%= application.getContextPath() %>/resources/css/frontcontrol.css" rel="stylesheet" />
		<script src="<%= application.getContextPath() %>/resources/js/speedgauge.js"></script>
	</head>

	<body style="background-color: black;">
		<h4>splinechart</h4>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div id="1ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
				<div id="6ChartContainer"
					style="height: 230px; margin-top: 20px; border: 1px solid white;">
					<div class="sliders yui3-skin-sam">
						<dl>
							<dt>
								R: <span id="r-val" class="val"></span>
							</dt>
							<dd id="r-slider"></dd>
							<dt>
								G: <span id="g-val" class="val"></span>
							</dt>
							<dd id="g-slider"></dd>
							<dt>
								B: <span id="b-val" class="val"></span>
							</dt>
							<dd id="b-slider"></dd>
						</dl>
					</div>
					<div class="color"></div>
					<div class="output">
						<dl>
							<dt>Hex:</dt>
							<dd id="hex"></dd>
							<dt>RGB:</dt>
							<dd id="rgb"></dd>
							<dt>HSL:</dt>
							<dd id="hsl"></dd>
						</dl>
					</div>
				</div>
			</div>
				<div class="col-md-4">
					<div id="3ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;">
						<div id="slider" class="rslider" style="text-align: center;"></div>
						<div id="speed" style="float:right;"></div>
						<span id="fronttireStatus">${fronttireAngle}</span><br/>
						<span id="backtireStatus">direction: ${backtireDirection}; speed: ${backtireSpeed}</span><br/>
						<button onmousedown="accelerator('forward')" onmouseup="stop()" style="color: black;">가속 패달</button>									
						<button onclick="stop()" style="color: black;">정지 패달</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div id="4ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="ultrasonicSensorContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="6ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div id="sensorChartContainer" style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>			
				</div>
			</div>	
		</div>
		<script src="<%=application.getContextPath()%>/resources/js/fronttire.js"></script>
	</body>
</html>