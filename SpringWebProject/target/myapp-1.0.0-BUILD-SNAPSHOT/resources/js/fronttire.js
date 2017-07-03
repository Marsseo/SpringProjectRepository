var currAngle = Number($('#angle').val())-60;

$("#slider").roundSlider({
	        handleShape: "round",
	        width: 16,
	        radius: 130,
	        value: currAngle,
	        keyboardAction: false,
	        mouseScrollAction: false,
	        circleShape: "half-top",
	        showTooltip: false,
	        max: "60",
	        
	        drag: function (values) {
	           
	            var intangle = 90 + (values.value-30);
	            var angle = String(intangle);
	            var json = {"command":"change", "angle": angle};
	        	
	        	$.ajax({
	        		url: "http://"+location.host+"/SpringWebProject/fronttire",
	        		data: json,
	        		method: "post",
	        		success: function(data){
	        			if(data.result == "success"){
	        				$("#fronttireStatus").html("angle="+data.angle);
	        				$("#angle").val(data.angle)
	        			}
	        		} 
	        	}); 
	        },
	        change: function (values) {
	        	
	            var intangle = 90 + (values.value-30);
	            var angle = String(intangle);
	            var json = {"command":"change", "angle": angle};
	        	
	        	$.ajax({
	        		url: "http://"+location.host+"/SpringWebProject/fronttire",
	        		data: json,
	        		method: "post",
	        		success: function(data){
	        			if(data.result == "success"){
	        				$("#fronttireStatus").html("angle="+data.angle);
	        				$("#angle").val(data.angle)
	        			}
	        		} 
	        	}); 
	        }
	    });