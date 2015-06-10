$(document).ready(function() {
	$('.request-row').click(function() {
		var id = $(this).children("td:first").html();
		console.log(id);
		$("#requestId").val(id);
		$("#getRequest").submit();
	});
});
var row = {
	initRowButtons: function() {
		
	}
}