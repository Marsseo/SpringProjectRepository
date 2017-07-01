var leftRight=90;
var upDown=10;
$(function(){
	$('#rangeslider0').rangeslider({
		polyfill:false,
		onInit:function(){
			$('.header .pull-right').text($('input[type="range"]').val()+'º');
		},
		onSlide:function(position, value){
			console.log('onSlide');
//			console.log('position: ' + position, 'value: ' + value);
			leftRight=value;
			$('.header .pull-right').text(value+'º');
			cameraLeftRight(value);
			console.log('leftRight:'+leftRight);
		},
		onSlideEnd:function(position, value){
//			console.log('onSlideEnd');
			//console.log('position: ' + position, 'value: ' + value);
		}
	});
});

$(function(){
	$('#rangeslider1').rangeslider({
		polyfill:false,
		onInit:function(){
			$('#upDown').text($('input[type="range"]').val()+'º');
		},
		onSlide:function(position, value){
			console.log('onSlide');
//			console.log('position: ' + position, 'value: ' + value);
			upDown=value;
			$('#upDown').text(value+'º');
			cameraUpDown(value);
			console.log('upDown:'+upDown);
		},
		onSlideEnd:function(position, value){
//			console.log('onSlideEnd');
			//console.log('position: ' + position, 'value: ' + value);
		}
	});
});

function cameraLeftRight(value) {
    console.log(value);
  
    var json = {"command":"change", "leftright":value, "updown":upDown};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/camera",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#fronttireStatus").html("angle="+data.angle);
			}
		} 
	}); 
}
function cameraUpDown(value) {
    console.log("updown"+value);
  
    var json = {"command":"change", "leftright":leftRight, "updown":value};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/camera",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#fronttireStatus").html("angle="+data.angle);
			}
		} 
	}); 
}