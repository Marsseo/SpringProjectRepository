var currSpeed = $('#speed').val();
var currDirection = $('#direction').val();

//$(docoument).ready(function(){
//	$('#accl').mousedown(function(){
//		for(i=205;i<4096;i+=10){
//			
//			var speed = String(i);
//			var direction = $('#direction').val();
//			
//			$('#accl').on('mouseup',function(){
//				for(j=i;j=0;j-=5){
//					
//					var speed = String(i);
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
	
	var json = {"command":"change", "direction": direction, "speed":speed};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
				$("#speed").val(data.speed);
				$('#direction').val(data.direction);
			}
		} 
	});

}
function accelerator(direction){
	
	for(i=200;i<4096;i+=5){
		
		var speed = String(i);
		
		var json = {"command":"change", "direction":direction, "speed":speed};
		$.ajax({
			url: "http://"+location.host+"/SpringWebProject/backtire",
			data: json,
			method: "post",
			success: function(data){
				if(data.result == "success"){
					$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
					$("#speed").val(data.speed);
					$('#direction').val(data.direction);
				}
			} 
		});
	}
}
function stop(){
	var speed=String(0);
	var json = {"command":"change", "direction":"forward", "speed":speed};
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/backtire",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#backtireStatus").html("direction : "+data.direction+" | speed : "+data.speed);
				$("#speed").val(data.speed);
			}
		} 
	});
}