var currDirection = $('#direction').val();
var currSpeed = $('#speed').val();



//$('#accl').mousedown(function(){
//		for(i=205;i<4096;i+=10){
//			
//			var speed = String(i);
//			var direction = $('#direction').val();
//			
//			$('#accl').mouseup(function(){
//				for(j=i;j=0;j-=5){
//					
//					speed = String(i);
//					
//					var json = {"command":"change", "direction":direction, "speed":speed};
//					$.ajax({
//						url: "http://"+location.host+"/SpringWebProject/backtire",
//						data: json,
//						method: "post",
//						success: function(data){
//							if(data.result == "success"){
//								$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
//								$("#speed").val(data.speed);
//								$('#direction').val(data.direction);
//							}
//						} 
//					});
//				}
//			});
//			
//			var json = {"command":"change", "direction":direction, "speed":speed};
//			$.ajax({
//				url: "http://"+location.host+"/SpringWebProject/backtire",
//				data: json,
//				method: "post",
//				success: function(data){
//					if(data.result == "success"){
//						$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
//						$("#speed").val(data.speed);
//						$('#direction').val(data.direction);
//					}
//				} 
//			});
//		}
//});

function backtire(command, direction, speed){
	
	console.log("1d"+direction);
	console.log("cur"+currDirection);
	if(direction==""){
		direction = currDirection;
		console.log("2d"+direction);
	}else if(speed==""){
		speed = currSpeed;
		console.log("2s"+speed);
	}
	console.log("3d"+direction);
	console.log("3s"+speed);
	var json = {"command":command, "direction": direction, "speed":speed};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
				$("#speed").val(data.speed);
				$("#direction").val(data.direction);
			}
		} 
	});

}
//function accelerator(direction){
//	
//	for(i=205;i<4096;i+=10){
//		
//		var speed = String(i);
//		
//		if($('#speedButton').onmouseup){
//			decelerate(direction, i);
//			break;
//		}else if($('#stopButton').onclick){
//			stop(direction);
//			break;
//		}
//		
//		var json = {"command":"change", "direction":direction, "speed":speed};
//		$.ajax({
//			url: "http://"+location.host+"/SpringWebProject/backtire",
//			data: json,
//			method: "post",
//			success: function(data){
//				if(data.result == "success"){
//					$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
//					$("#speed").val(data.speed);
//					$("#direction").val(data.direction);
//				}
//			} 
//		});
//	}
//}
//
//function decelerate(direction, i){
//	for(j=i;j>=0;j-=5){
//		
//		var speed = String(j);
//		
//		var json = {"command":"change", "direction":direction, "speed":speed};
//		$.ajax({
//			url: "http://"+location.host+"/SpringWebProject/backtire",
//			data: json,
//			method: "post",
//			success: function(data){
//				if(data.result == "success"){
//					$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
//					$("#speed").val(data.speed);
//					$("#direction").val(data.direction);
//				}
//			} 
//		});
//	}
//}

function stop(direction){
	var speed=String(0);
	var json = {"command":"change", "direction":direction, "speed":speed};
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
				$("#speed").val(data.speed);
				$("#direction").val(data.direction);
			}
		} 
	});
}