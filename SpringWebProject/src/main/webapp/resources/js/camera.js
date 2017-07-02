var currLeftRight=$('#hiddenleftright').val();
var currUpDown=$('#hiddenupdown').val();

$(function(){
	
	$('#rangeslider0').rangeslider({
		polyfill:false,
		onInit:function(){
			
			$('.header .pull-right').text($('#hiddenleftright').val()+'º');
		},
		onSlide:function(position, value){
			console.log('onSlide');
//			console.log('position: ' + position, 'value: ' + value);
			currLeftRight=value;
			$('.header .pull-right').text(value+'º');
			cameraLeftRight(value);
//			console.log('leftRight:'+leftRight);
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
			$('#upDown').text($('#hiddenupdown').val()+'º');
		},
		onSlide:function(position, value){
			console.log('onSlide');
//			console.log('position: ' + position, 'value: ' + value);
			currUpDown=value;
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
  
    var json = {"command":"change", "leftright":value, "updown":$('#hiddenupdown').val()};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/camera",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$(".header .pull-right").html(data.leftright+"º");
				$("#hiddenleftright").val(data.leftright);
				
			}
		} 
	}); 
}
function cameraUpDown(value) {
    console.log("updown"+value);
  
    var json = {"command":"change", "leftright":$('#hiddenleftright').val(), "updown":value};
	
	$.ajax({
		url: "http://"+location.host+"/SpringWebProject/camera",
		data: json,
		method: "post",
		success: function(data){
			if(data.result == "success"){
				$("#upDown").html(data.updown+"º");
				$("#hiddenupdown").val(data.updown);
			}
		} 
	}); 
}