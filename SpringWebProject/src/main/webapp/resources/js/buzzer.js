function buzzer(command,status){

	var json ={"command":command,"status":status};
	$.ajax({
		url:"http://" + location.host + "/SpringWebProject/buzzer",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				//$("#buzzerStatus").html(data.status);
				
				if(status=='off'){
					$("#buzzerOn").attr("onclick","buzzer('change', 'on')");
					$("#buzzerOn").attr("src","/SpringWebProject/resources/image/laserOn.png");
				}else if(status=='on'){
					$("#buzzerOn").attr("onclick","buzzer('change', 'off')");
					$("#buzzerOn").attr("src","/SpringWebProject/resources/image/buzzerOn.png");
				}
			}
		}
	});
}