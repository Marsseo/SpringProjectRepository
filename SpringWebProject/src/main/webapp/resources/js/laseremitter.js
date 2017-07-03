function laseremitter(command,status){
	console.log(status);
	
	var json ={"command":command,"status":status};
	
	
	
	$.ajax({
		url:"http://" + location.host + "/SpringWebProject/laseremitter",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
			//	$("#laseremitterStatus").html(data.status);
				if(status=='off'){
					$("#laserOn").attr("onclick","laseremitter('change', 'on')");
					$("#laserOn").attr("src","/SpringWebProject/resources/image/laser.png");
				}else if(status=='on'){
					$("#laserOn").attr("onclick","laseremitter('change', 'off')");
					$("#laserOn").attr("src","/SpringWebProject/resources/image/laserOn.PNG");
				}
			}
		}
	});
}