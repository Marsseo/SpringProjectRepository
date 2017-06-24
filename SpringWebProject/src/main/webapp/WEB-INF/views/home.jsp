<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/js/jquery-1.11.3.min.js"></script>
		<link href="<%= application.getContextPath() %>/resources/css/roundslider.min.css" rel="stylesheet" />
		<script src="<%= application.getContextPath() %>/resources/js/roundslider.min.js"></script>
	</head>
	<body>
		<h3>집가고 싶다</h3><br/><br/>
		
    
    <div id="slider" class="rslider"></div>
    
    <script type="text/javascript">
    
	    $("#slider").roundSlider({
	        handleShape: "round",
	        width: 25,
	        radius: 100,
	        value: 30,
	        keyboardAction: false,
	        mouseScrollAction: true,
	        circleShape: "half-top",
	        handleSize: "+5",
	        editableTooltip: false,
	        showTooltip: false,
	        max: "60",
	        
	        drag: function (values) {
	           
	            console.log(values.value);
	        },
	        change: function (values) {
	        	
	            console.log(values.value);
	        }
	    });
    </script>
	</body>
</html>
