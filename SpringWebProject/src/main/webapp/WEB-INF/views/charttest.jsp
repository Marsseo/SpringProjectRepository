<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"	content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"	rel="stylesheet" type="text/css" />
		<script	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"	type="text/javascript"></script>
		<script	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"	type="text/javascript"></script>
		<script	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
		<script	src="<%=application.getContextPath()%>/resources/highcharts/code/themes/gray.js"></script>
		<!-- 기존 센서 -->
		<%-- 
		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rgbled.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/laseremitter.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensor.js"></script>		
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
		<link	href="<%=application.getContextPath()%>/resources/css/project.css" rel="stylesheet" type="text/css" />		
		<script src="<%=application.getContextPath()%>/resources/js/sensorchart.js"></script>	
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>			
	</head>

	<body >
		<header>
			<hgroup class="clearfix">
				<h1>SensingCar Web Controller</h1>				
				<h2>IoT Engineer TEAM 1</h2>
			</hgroup>
		</header>
		<div id="main" class="clearfix">	
			<div id="content">
				<article>
					<section>
						<a href="#" class="btn btn-primary ">버튼1</a>
						<a href="#" class="btn btn-primary ">버튼1</a>
						<a href="#" class="btn btn-primary ">버튼1</a>
					</section>
				</article>
				<article>
					<section>
						<div class="container-fluid" style="float: left">
							<div class="row">
								<div class="col-md-4">
									<div id="4ChartContainer" class="box-design2"></div>
								</div>
								<div class="col-md-4">
									<div id="5ChartContainer" class="box-design2"></div>
								</div>
								<div class="col-md-4">
									<div id="6ChartContainer" class="box-design2"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div id="4ChartContainer" class="box-design1"></div>
								</div>
								<div class="col-md-4">
									<div id="5ChartContainer" class="box-design1"></div>
								</div>
								<div class="col-md-4">
									<div id="6ChartContainer" class="box-design1"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">																	
									<div id="1ChartContainer"  class="box-design1" align="center">
										<div align="center" align="center" 
										style="padding: 30px; background: url('/SpringWebProject/resources/image/display2.png'); 
												background-repeat:no-repeat; background-position:center;  text-align: center; height: 250px; width: 300px;">
											<div style="text-align: left; margin-left: 30px; font-size: 20px; font-style: italic; font-weight: bold;">LCD Display</div>
											<div style="text-align: left; margin-left: 30px;">
												<span id="lcdStatus" style="text-align: left; color: #3ADF00; font-weight: bold; font-size: 17px;">
													<br/>▶ ${lcdline0}
													<br/>▶  ${lcdline1}
												</span>
											</div>		<br/>
											<div style="text-align: left; height: 100px; padding: 20px; border: 10px solid blue; background-color: white;">
												<span id="lcdStatus" style="text-align: left; ">
													▷ <input id="lcdline0" type="text" maxlength="16" value="Spring" style="color: black; width:120px; border: 0px; border-bottom: 1px solid #2E64FE"/>
													<br/>											
													▷ <input id="lcdline1" type="text" maxlength="16" value="Web Project" style="color: black; width:120px;border: 0px;border-bottom: 1px solid #2E64FE"/>
													<span style="font-size: 20px">		
														<span class="glyphicon glyphicon-ok" onclick="lcd('change')"></span>
													</span>																						
												</span>
											</div>		
										</div>	
									</div>									
								</div>
								<div class="col-md-4">
									<div id="2ChartContainer"  class="box-design1"></div>
								</div>
								<div class="col-md-4">
									<div id="3ChartContainer" class="box-design1"></div>
								</div>
							</div>							
							<div class="row">
								<div class="col-md-4" >
									<div class="row" >
										<div class="col-md-12"  >
											<div class="box-design3" >
												<c:if test="${tracking == 'white' }">	
													<div id="trackingsensor" style="height: 107px; border: 1px solid white; text-align: center; line-height: 3; font-size: 30px; color: #000000; background-color: white; background: linear-gradient(#FFFFFF, #F4E6FA)">TRACKING</div>
												</c:if>
												<c:if test="${tracking == 'black' }">	
													<div id="trackingsensor" style="height: 107px; border: 1px solid white; text-align: center; line-height: 3; font-size: 30px; color: #FFFFFF; background-color: #000000; background: linear-gradient(#575151, #000000)">TRACKING</div>
												</c:if>
											</div>
										</div>
										<div class="col-md-12"  >							
											<div class="box-design3" style="background-color: #F78181; background: linear-gradient(#F6CECE, #F78181)" onmouseenter="thermistoronly()">						
												<div style="display: inline; ">
													<div  id="thermistorvalue" class="box-design4" >▶현재온도◀<br/>${temperature} ºC</div>		
												</div>																					
												<div id="thermistorimg" class="box-design-img" >
													<c:if test="${temperature < 24 }">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/temperature24.png'/>
													</c:if>
													<c:if test="${temperature < 27 && temperature >= 24}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/temperature27.png'/>
													</c:if>
													<c:if test="${temperature < 30 && temperature >= 27}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/temperature30.png'/>
													</c:if>
													<c:if test="${temperature >= 30}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/temperature33.png'/>
													</c:if>
												</div>	
											</div>
										</div>										
									</div>			
									<div class="row">
										<div class="col-md-12" >
											<div class="box-design3" style="background-color: #F3F781; background: linear-gradient(#F5F6CE, #F3F781)" onmouseenter="photoresistoronly()">
												<div style="display: inline; width: 100px">
													<span  id="photoresistorvalue" class="box-design4">▶현재밝기◀<br/>${photoresistorStr} (${photoresistor})</span>
												</div>
												<div id="photoresistorimg" class="box-design-img">
													<c:if test="${photoresistor < 20 }">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/star20.png'/>
													</c:if>
													<c:if test="${photoresistor < 100 && photoresistor >= 20}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/star100.png'/>
													</c:if>
													<c:if test="${photoresistor < 150 && photoresistor >= 100}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/star150.png'/>
													</c:if>
													<c:if test="${photoresistor >= 150}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/star151.png'/>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-12" >
											<div class="box-design3" style="background-color: #BE81F7; background: linear-gradient(#E3CEF6, #BE81F7)" onmouseenter="gasonly()">
												<div style="display: inline; width: 100px">
													<span  id="gasvalue" class="box-design4">▶가스오염◀<br/>${gasStr} (${gas}) </span>
												</div>
												<div id="gasimg" class="box-design-img">
													<c:if test="${gas < 40}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/gas40.png'/>
													</c:if>
													<c:if test="${gas < 120 && gas >= 40}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/gas80.png'/>
													</c:if>
													<c:if test="${gas < 200 && gas >= 120}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/gas150.png'/>
													</c:if>
													<c:if test="${gas >= 200}">	
														<img class="box-design-img2" src='/SpringWebProject/resources/image/gas151.png'/>
													</c:if>													
												</div>
											</div>
										</div>
									</div>			
								</div>				
								<div class="col-md-8">
									<div id="sensorChartContainer" class="box-design5" onmouseenter="showall()"></div>			
								</div>
							</div>
						</div>		
					</section>		
 		
					<footer>	
					　　
					</footer>
				</article>
				<article>
					<section>
						한국소프트웨어산업협회 2017 IoT 엔지니어 양성과정
					</section>
				</article>				
			</div>
		</div>
	</body>
</html>