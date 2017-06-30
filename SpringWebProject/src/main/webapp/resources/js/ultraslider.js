$("#ultrahandle").roundSlider({
    sliderType: "max-range",
    radius: 130,
    showTooltip: false,
    width: 16,
    value: 88,
    handleSize: 0,
    handleShape: "square",
    circleShape: "half-top",

    drag: function (args) {
        console.log(args.handle.angle);
    },
    change: function (args) {
    	console.log(args.handle.angle);
    }
});