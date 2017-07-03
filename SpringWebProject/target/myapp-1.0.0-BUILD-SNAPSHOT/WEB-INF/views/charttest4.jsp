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
		<script src="<%=application.getContextPath()%>/resources/js/photoresistorsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/gassensorchart.js"></script>
		 --%>
		 <%-- <script src="<%=application.getContextPath()%>/resources/js/trackingsensorchart.js"></script> --%>
		<!-- 추가 센서 차트-->
		<script src="<%=application.getContextPath()%>/resources/js/sensorchart.js"></script>
		
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
					<div id="2ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="3ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div id="4ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="5ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="6ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4" >
					<div class="row">
						<div class="col-md-6" >							
							<div style="height: 100px; margin-top: 20px; border: 1px solid white; " onmouseenter="thermistoronly()">						
								<div style="display: inline; width: 100px;"><span  id="thermistorvalue"  style="color: white; font-size: 30px; line-height: 2;">현재온도:  ${temperature} ºC</span></div>
								<div id="thermistorimg" style="display: inline">
									<img style='height: 100px;' src='/SpringWebProject/resources/image/temperature24.png'/>
								</div>
							</div>
						</div>
						<div class="col-md-6"  >
							<div >
								<div id="trackingsensor" style="height: 100px; margin-top: 20px; border: 1px solid white; text-align: center; line-height: 2; font-size: 30px; color: white;background-color: ${tracking}">TRACKING</div>
							</div>
						</div>
					</div>			
					<div class="row">
						<div class="col-md-6" >
							<div style="height: 100px; margin-top: 20px; border: 1px solid white;" onmouseenter="photoresistoronly()">
								<div style="display: inline; width: 100px"><span  id="photoresistorvalue"  style="color: white; font-size: 30px; line-height: 2;">현재밝기: ${photoresistorStr} (${photoresistor})</span></div>
								<div id="photoresistorimg" style="display: inline">
									<img style='height: 100px;' src='/SpringWebProject/resources/image/star100.png'/>
								</div>
							</div>
						</div>
						<div class="col-md-6" >
							<div style="height: 100px; margin-top: 20px; border: 1px solid white; " onmouseenter="gasonly()">
								<div style="display: inline; width: 100px"><span  id="gasvalue"  style="color: white; font-size: 30px; line-height: 2;">가스상태: ${gasStr} (${gas}) </span></div>
								<div id="gasimg" style="display: inline">
									<img style='height: 100px;' src='/SpringWebProject/resources/image/gas80.png'/>
								</div>
							</div>
						</div>
					</div>			
				</div>				
				<div class="col-md-8">
					<div id="sensorChartContainer" style="height: 230px; margin-top: 20px; border: 1px solid white;" onmouseenter="showall()"></div>			
				</div>
			</div>	
		</div>
	</body>
</html>