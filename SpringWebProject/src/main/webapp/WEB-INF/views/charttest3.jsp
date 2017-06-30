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

	<body style="background-color: #2a2a2a;">
		<h4>splinechart</h4>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div id="1ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="2ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;">
						<img class="img-responsive" src="${cameraUrl}" style="width:100%; height:100%;"/>
						</div>
				</div>
				<div class="col-md-4">
					<div id="3ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;">
						</div>
						
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div id="4ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>
				</div>
				<div class="col-md-4">
					<div id="5ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;">	
						
						<div class="budget-wrap">
							<div class="budget">
								<div class="header">
									<div class="title clearfix">Camera Control <span class="pull-right"></span></div>
								</div>
								<div class="content" style="height: 10px;  width: 100px; ">
									<input type="range" min="10" max="170" value="45" data-rangeslider>
								</div>
								
							</div>
						</div>
					</div>
		
				</div>
				
				<div class="col-md-4">
					<div id="6ChartContainer"
						style="height: 230px; margin-top: 20px; border: 1px solid white;">
												<div class="sliders yui3-skin-sam">
						    <dl>
						        <dt>R: <span id="r-val" class="val" ></span></dt><dd id="r-slider"></dd>
						        <dt>G: <span id="g-val" class="val"></span></dt><dd id="g-slider"></dd>
						        <dt>B: <span id="b-val" class="val"></span></dt><dd id="b-slider"></dd>
						    </dl>
						</div>
						<div class="color"></div>
						<div class="output">
						    <dl>
						        <dt>Hex:</dt><dd id="hex"></dd>
						        <dt>RGB:</dt><dd id="rgb"></dd>
						        <dt>HSL:</dt><dd id="hsl"></dd>
						    </dl>
						</div>
						
						<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="rgbled('change', '255', '0', '0')">Red</button>
									<button type="button" class="btn btn-success" onclick="rgbled('change', '0', '255', '0')">Green</button>
									<button type="button" class="btn btn-primary" onclick="rgbled('change', '0', '0', '255')">Blue</button>
									<button type="button" class="btn btn-primary" onclick="rgbled('change', r, g, b)">Send RGB</button>
								</div>
						</div>
						

				</div>
			</div>
			<div class="row">
				<div class="col-md-4" >
					<div class="row">
						<div class="col-md-6" >
							<div id="thermistorimg" style="height: 100px; margin-top: 20px; border: 1px solid white;">	</div>
						</div>
						<div class="col-md-6" >
							<div id="trackingsensor" style="height: 100px; margin-top: 20px; border: 1px solid white;">TRACKING</div>
						</div>
					</div>			
					<div class="row">
						<div class="col-md-6" >
							<div id="photoresistorimg" style="height: 100px; margin-top: 20px; border: 1px solid white;">	</div>
						</div>
						<div class="col-md-6" >
							<div id="gasimg" style="height: 100px; margin-top: 20px; border: 1px solid white;">	</div>
						</div>
					</div>			
				</div>				
				<div class="col-md-8">
					<div id="sensorChartContainer" style="height: 230px; margin-top: 20px; border: 1px solid white;"></div>			
				</div>
			</div>	
		</div>
	</body>
</html>