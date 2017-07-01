function lcd(command) {
	var line0 = $("#lcdline0").val();
	var line1 = $("#lcdline1").val();
	var json = {"command":command, "line0":line0, "line1":line1 };

	$.ajax({
		url:"http://" + location.host + "/SpringWebProject/lcd",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#lcdStatus").html("<br/>▶ " + data.line0 + "<br/>▶ " + data.line1);
			}
		}
	});
}