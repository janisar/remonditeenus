$("#repair-btn").click(function() {
	window.location.href="/repair";
});

function saveRequest(id) {
	$.ajax({
		method: "GET",
		url: "accept",
		data:{
			requestId: id
		}
	}).success(function(result) {
		//$("#newOrderModal .modal-body").html(result);
		//$("#newOrderModal").modal("show");
	});
}

function acceptRequest(id) {
	$("#newOrderModal").modal("show");
}