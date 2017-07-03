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
		<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts-more.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/modules/exporting.js"></script>
	<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/modules/solid-gauge.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ultrasonicsensorchart.js"></script>
	<link
	href="<%=application.getContextPath()%>/resources/css/roundslider.min.css"
	rel="stylesheet" />
<script
	src="<%=application.getContextPath()%>/resources/js/roundslider.min.js"></script>
<link
	href="<%=application.getContextPath()%>/resources/css/ultraslider.css"
	rel="stylesheet"></link>
<script
	src="<%=application.getContextPath()%>/resources/js/laseremitter.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
		<style>@media screen and (min-width: 480px) { #title { height: 10px; } }</style>
		<script src="<%=application.getContextPath()%>/resources/js/backtire.js"></script>
		<link href="<%= application.getContextPath() %>/resources/css/frontcontrol.css" rel="stylesheet" />
		<!-- 추가 센서 차트-->
		<link	href="<%=application.getContextPath()%>/resources/css/project.css" rel="stylesheet" type="text/css" />		
		<script src="<%=application.getContextPath()%>/resources/js/sensorchart.js"></script>	
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>			
		
		<script src="<%=application.getContextPath()%>/resources/js/rgbled.js"></script>
		<link href="<%= application.getContextPath()%>/resources/css/rgbslider.css" rel="stylesheet"/> 
		<script src="http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js"></script> 
		
		
		<script src="<%=application.getContextPath()%>/resources/js/rangeslider.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rangeslider.min.js"></script>
		<link href="<%= application.getContextPath()%>/resources/css/rangeslider.css" rel="stylesheet"/> 
		<link href="<%= application.getContextPath()%>/resources/css/camera.css" rel="stylesheet"  /> 
		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script>
       YUI().use('slider', 'color', function(Y){
                // sliders
    var rSlider = new Y.Slider({ min: 0, max: 255, value: 255 }),
        gSlider = new Y.Slider({ min: 0, max: 255, value: 0 }),
        bSlider = new Y.Slider({ min: 0, max: 255, value: 0 }),

        // slider values
        rVal = Y.one('#r-val'),
        gVal = Y.one('#g-val'),
        bVal = Y.one('#b-val'),

        // color strings
        hex = Y.one('#hex'),
        rgb = Y.one('#rgb'),
        hsl = Y.one('#hsl'),

        // color chip
        color = Y.one('.color');

    // render sliders
    rSlider.render('#r-slider');
    gSlider.render('#g-slider');
    bSlider.render('#b-slider');


                // register update events
    rSlider.after('thumbMove', function(e) {
        rVal.set('text', rSlider.get('value'));
        updateColors();
    });
    gSlider.after('thumbMove', function(e) {
        gVal.set('text', gSlider.get('value'));
        updateColors();
    });
    bSlider.after('thumbMove', function(e) {
        bVal.set('text', bSlider.get('value'));
        updateColors();
    });

    // update the colors strings
    function updateColors() {
          r = rSlider.get('value'),
            g = gSlider.get('value'),
            b = bSlider.get('value'),
            rgbStr = Y.Color.fromArray([r,g,b], Y.Color.TYPES.RGB);

        color.setStyle('backgroundColor', rgbStr);

        rgb.set('text', rgbStr);

        hex.set('text', Y.Color.toHex(rgbStr));
        hsl.set('text', Y.Color.toHSL(rgbStr));
    }


            
    rVal.set('text', rSlider.get('value'));
    gVal.set('text', gSlider.get('value'));
    bVal.set('text', bSlider.get('value'));
    updateColors();

            });
        </script> 
	
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
				<!-- <article>
					<section>
						<a href="#" class="btn btn-primary ">버튼1</a>
						<a href="#" class="btn btn-primary ">버튼1</a>
						<a href="#" class="btn btn-primary ">버튼1</a>
					</section>
				</article>-->
				<article>
					<section>
						<div class="container-fluid" style="float: left">
							<div class="row">
							<div class="col-md-4">
								<div id="4ChartContainer" class="box-design2">
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
											<!-- <dt>HSL:</dt><dd id="hsl"></dd> -->
										</dl>
									</div>

									<div style="text-align: center;">
										<button type="button" class="btn btn-danger"
											onclick="rgbled('change', '255', '0', '0')">Red</button>
										<button type="button" class="btn btn-success"
											onclick="rgbled('change', '0', '255', '0')">Green</button>
										<button type="button" class="btn btn-primary"
											onclick="rgbled('change', '0', '0', '255')">Blue</button>
										<button type="button" class="btn btn-primary"
											onclick="rgbled('change', r, g, b)">Send RGB</button>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div id="5ChartContainer" class="box-design2">
								
								<img class="img-responsive" src="${cameraUrl}"  style="width:100%; height:100%;"/>
								
									
								</div>
							</div>
							<div class="col-md-4">
								<div id="6ChartContainer" class="box-design2">
															<div class="budget-wrap" >
							<div class="budget"  >
								<div class="header" >
								<input type="hidden" id="hiddenleftright" value="${leftright}"/>
							<input type="hidden" id="hiddenupdown" value="${updown}"/>
						
									<div class="title clearfix" style=" display:inline-block:">
									<span>Camera Control  </span>
									
									<span style="float:right;">좌우 :
									<span class="pull-right" > </span> 
									</span> <span style=" float:right;">상하 :
									<span  id="upDown" style=" float:right; "> </span>  
									</span> 
									&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
								
									
										<!-- <span id="leftRightStatus">좌우:$"{leftright}"</span> -->
									</div>
								</div>
								<div class="content" display:inline;  ">
							<input id="rangeslider1" type="range" min="10" max="100" value="45" data-rangeslider >
								
								</br></br>
									<input id="rangeslider0" type="range" min="10" max="170" value="90" data-rangeslider>
									
								</div>
								
							</div>
						</div>
								</div>
							</div>
							</div>
							<div class="row">
								<div class="col-md-4">
								<input type="hidden" id="ultraangle" value=" ${angle}"/>
									<div id="ultraSensorChartContainer" class="box-design1">
									
									</div>
								</div>
								<div class="col-md-4">
									<div id="contatin" class="box-design2">
									<div class="budget-wrap">
										<div class="budget">
											<div class="header">
												<div class="title clearfix" style="float: up">
													<span>UltraSensor Control </span>
									<div  id="ultrahandle"></div>
									</div>
									</div>
									</div>
									</div>
									</div>
								</div>
								<div class="col-md-4">
									<div id="6c"
					style="height: 260px; margin-top: 20px; border: 1px solid white; background-color: black;">
					
					<input style="float: left; height: 220px; width: 180px;" id="laserOn" <c:if test="${laseremitterStatus=='off'}">onclick="laseremitter('change','on')" </c:if>
					<c:if test="${laseremitterStatus=='on'}">onclick="laseremitter('change','off')" </c:if> <c:if test="${laseremitterStatus=='off'}">type="image" src="<%=application.getContextPath()%>/resources/image/laser.png"</c:if> <c:if test="${laseremitterStatus=='on'}">type="image" src="<%=application.getContextPath()%>/resources/image/laserOn.PNG"</c:if>/>
					<input style="float: right; height: 220px; width: 180px;" id="buzzerOn" <c:if test="${buzzerStatus=='off'}">onclick="buzzer('change','on')" </c:if>
					<c:if test="${buzzerStatus=='on'}">onclick="buzzer('change','off')" </c:if> 
					<c:if test="${buzzerStatus=='off'}">type="image" src="<%=application.getContextPath()%>/resources/image/buzzer.png"</c:if> <c:if test="${buzzerStatus=='on'}">type="image" src="<%=application.getContextPath()%>/resources/image/buzzerOn.png"</c:if>/>
					
				</div>
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
									<div id="2ChartContainer"  class="box-design1">
									<div id="slider" class="rslider" style="float:left;background-color: white"></div>
    					<br/>
						<div style="float:left;background-color: white">
							<input type="hidden" id="angle" value="${fronttireAngle}"/>
							<input type="hidden" id="speed" value="${backtireSpeed}"/>
							<input type="hidden" id="direction" value="${backtireDirection}"/>
							<button onclick="backtire('change', '${backtireDirection}','1000')" style="color: black;">1</button>
							<button onclick="backtire('change', '${backtireDirection}','1500')" style="color: black;">2</button>
							<button onclick="backtire('change', '${backtireDirection}','2200')" style="color: black;">3</button>
							<button onclick="backtire('change', '${backtireDirection}','2800')" style="color: black;">4</button>
							<button onclick="backtire('change', '${backtireDirection}','3500')" style="color: black;">5</button>
							<button onclick="backtire('change', '${backtireDirection}','4090')" style="color: black;">6</button><br/>								
							<span id="fronttireStatus">Angle : ${fronttireAngle}</span><br/>
							<span id="backtireStatus">direction : ${backtireDirection} | speed : ${backtireSpeed}</span><br/>
						</div><br/>
						<button type="button" class="btn btn-warning" onclick="backtire('change', 'forward','${backtireSpeed}')" style="margin-bottom: 5px;">전진</button>
						<button type="button" class="btn btn-info" onclick="backtire('change', 'backward','${backtireSpeed}')" style="margin-bottom: 5px;">후진</button><br/>	
						<button onmousedown="accelerator('${backtireDirection}')" onmouseup="decelerate('${backtireDirection}','${backtireSpeed}')" style="color: black;">
						<!-- <button id="accl" style="color: black;">-->가속 패달</button>									
						<button onclick="stop('${backtireDirection}')" style="color: black;">정지 패달</button>
									</div>
								</div>
								<div class="col-md-4">
									<div id="container-speed" class="box-design1">
									
									</div>
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
		
		
		<script
		src="<%=application.getContextPath()%>/resources/js/ultraslider.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/fronttire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/speedgauge.js"></script>
	</body>
</html>