var CallHelper = {
	doGet: function (path, params, success, error) {
		$.ajax({
			  type: "GET",
			  url: path,
			  data: params,
			  dataType: "json",
			  success: success,
			  error: error
		});
	},
	
	doPost: function (path, params, success, error) {
		$.ajax({
			  type: "POST",
			  url: path,
			  data: params,
			  dataType: "json",
			  success: success,
			  error: error
		});
	}
}
