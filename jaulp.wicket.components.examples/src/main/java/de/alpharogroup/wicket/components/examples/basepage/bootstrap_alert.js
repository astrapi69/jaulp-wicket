function newAlert(type, message, delay) {
	$("#alert-area").append(
			$("<div class='alert-message " + type + " fade in' data-alert><p> "
					+ message + " </p></div>"));
	$(".alert-message").delay(delay).fadeOut("slow", function() {
		$(this).remove();
	});
}