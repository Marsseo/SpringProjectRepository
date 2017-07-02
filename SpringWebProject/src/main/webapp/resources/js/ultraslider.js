var ultraAngle= $("#ultraangle").val();
console.log(ultraAngle+"zzzz");
$("#ultrahandle").roundSlider({
    sliderType: "max-range",
    radius: 130,
    showTooltip: false,
    width: 16,
    value: ultraAngle,
    handleSize: 0,
    endAngle: "+180",
    max: "180",
    handleShape: "square",
    circleShape: "half-top",
    drag: function (args) {
    	 console.log(args.handle.angle);
    	 
         var angle= Math.round(args.handle.angle);
         console.log(angle);
         var json = {"command":"change", "angle":angle};
         $.ajax({
 			url:"http://" + location.host + "/SpringWebProject/ultrasonicsensor",
 			data: json,
 			method: "post",
 			success: function(data) {
 				if(data.result == "success") {
 					//$("#ultrasonicsensorStatus").html("angle"+data.angle+";"+"distance"+data.distance+";");
 				}
 			}
 		});
    
    },
    change: function (args) {
    	
        
        console.log(args.handle.angle);
        var angle= Math.round(args.handle.angle);
        console.log(angle);
        var json = {"command":"change", "angle":angle};
        $.ajax({
			url:"http://" + location.host + "/SpringWebProject/ultrasonicsensor",
			data: json,
			method: "post",
			success: function(data) {
				if(data.result == "success") {
					//$("#ultrasonicsensorStatus").html("angle"+data.angle+";"+"distance"+data.distance+";");
				}
			}
		});
    }
});