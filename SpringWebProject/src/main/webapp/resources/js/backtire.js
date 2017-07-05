var currSpeed = $('#speed').val();
var currDirection = $('#direction').val();

//var acclspeed = document.getElementById('speed');
//
//$(docoument).ready(function(){
//	acclspeed.onmousedown(function(){
//		for(i=205;i<4096;i+=10){
//			
//			var speed = String(i);
//			var direction = $('#direction').val();
//			
//			acclspeed.onmouseup(function(){
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
//	});
//});

function backtire(command, direction, speed){
	
	if(direction=""){
		direction = currDirection;
	}
	if(speed=""){
		speed = currSpeed;
	}
	
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