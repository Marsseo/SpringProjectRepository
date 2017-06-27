function backtire(command, direction, speed){
	console.log("d: "+backtireStatus.direction);
	if(direction=="") {
		direction = backtireStatus.direction;
	} else {
		backtireStatus.direction = direction;
	}
	if(speed=""){
		speed=backtireStatus.speed;
	}
	else backtireStatus.speed = speed; 
	console.log("d: "+direction);
	console.log("s: "+speed);
	var json = {"command":command, "direction": direction, "speed":speed};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction="+data.direction+"; speed="+data.speed);
			}
		} 
	});

}
function accelerator(direction){
	for(i=100;i<4095;i+=5){
		
		var speed = String(i);
		
		var json = {"command":"change", "direction":direction, "speed":speed};
		$.ajax({
			url: "http://"+location.host+"/SpringWebProject/backtire",
			data: json,
			method: "post",
			success: function(data){
				if(data.result == "success"){
					$("#backtireStatus").html("direction="+data.direction+"; speed="+data.speed);
				}
			} 
		});
	}
}
function stop(){
	
	var speed = String(0);
	
	var json = {"command":"change", "direction": "forward", "speed":speed};
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction="+data.direction+"; speed="+data.speed);
			}
		} 
	});
}