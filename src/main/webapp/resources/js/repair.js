$(document).ready(function() {
	$(".request-row").click(function() {
		var id = $(this).find("td:first").before().html();
		
		$.ajax({
			method: "GET",
			url: "/Remonditeenus/getRequest",
			data: {
				requestId: id
			}
		}).success(function(data) {
			$('#container').html(data);
		});
	});
	$('#part_seria_nr').on('input', function() {
		var length = $('#part_seria_nr').val().length;
		if (length > 0) {
			$('#part_amount').val(1);
			$('#part_amount').prop('disabled', true);
		} else {
			$("#part_amount").prop('disabled', false);
		}
	});
});

function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
} 

function addDevice(id) {
	var csrfToken = $("meta[name='_csrf']").attr("content"); 
	var requestId = $("meta[name='requestId']").attr("content"); 
	console.log(id);
	
	
	CallHelper.doPost("addDevice", {requestId: requestId, deviceId: id, _csrf: csrfToken}, SearchDevice.addDeviceSuccess, SearchDevice.addDeviceFail);
	
}

var DeleteOrder = {
	initButton: function() {
		
		$('#deleteAll').click(DeleteOrder.onClickListener);
	},
	
	onClickListener: function() {
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var requestId = getUrlParameter("requestId");
		
		CallHelper.doPost("deleteOrder", {requestId: requestId, _csrf: csrfToken}, DeleteOrder.successCall, DeleteOrder.failedCall);
	},
	
	successCall: function(data) {
		if (data == "ok") {
			location.href = "requests";
		} else {
			alert("Ei saa kustutada");
		}
	}, 
	
	failedCall: function() {
		alert("Tellimust ei saa kustutada");
	}
};
var RequestStatus = {
	initField: function () {
		var status = $("meta[name='status']").attr("content"); 
		$('#requestStatus > option').each(function() {
			if ($(this).attr("id") == status) {
				$(this).attr("selected", "selected");
			}
		});
		
		$('#requestStatus').change(RequestStatus.onClickListener);
	},
	
	onClickListener: function () {
		var request = getUrlParameter("requestId");
		var id = $('#requestStatus').find('option:selected').attr('id');
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		CallHelper.doPost("updateRequestStatus", {requestId: request, status: parseInt(id), _csrf: csrfToken}, RequestStatus.successCall, RequestStatus.failCall);
	},
	
	successCall: function() {
		console.log("success");
	},
	
	failCall: function() {
		console.log("fail");
	}
}
var SearchDevice = {
	initButton: function() {
		$('#searchDevices').click(SearchDevice.onClickListener);
		$('#search-devices').click(SearchDevice.askDevicesFromServer);
	},
	
	askDevicesFromServer: function() {
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var device = {};
		device.id = $('#search-device-id').val();
		device.name = $('#search-device-name').val();
		device.model = $('#search-device-model').val();
		device.type = $('#search-device-type').val();
		
		var data = JSON.stringify(device);
		console.log(device);
		
		CallHelper.doPost("searchDevice", {id: device.id, name: device.name, 
			model: device.model, type: device.type, _csrf: csrfToken}, SearchDevice.searchSuccessResult, SearchDevice.searchFailCall);

	},
	
	onClickListener: function() {
		var requestId = getUrlParameter("requestId");
		location.href = "searchDevice?requestId=" + requestId;
	},
	
	searchSuccessResult: function(response) {
		$('#searchResult').html("");
		response.devices.forEach(function (device) {
			SearchDevice.addDeviceToView(device);
		});
		console.log(response);
	},
	
	addDeviceToView: function(device) {
		console.log(device);
		html = "<div>" + device.deviceId + ": "+ device.name + "  " + device.model + "<button onclick='addDevice(\"" + device.deviceId + "\")'>Lisa seade</button> </div>";
		$('#searchResult').append(html);
	},
	
	searchFailCall: function() {
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var requestId = getUrlParameter("requestId");
		location.href = "";
	},
	
	addDeviceSuccess: function() {
		var request = getUrlParameter("requestId");
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		location.href = "getRequest?requestId=" + request + "&_csrf=" + csrfToken;
	},
	
	addDeviceFail: function() {
		
	}
};

var JobPrice = {
	scope: {
	},
	
	initField: function() {
		$('.edit-job').click(JobPrice.onClickListener);
		$('.remove-job').click(JobPrice.deleteListener);
		$('#save-glyph').click(JobPrice.saveNewPrice);
	},
	
	deleteListener: function() {
		var id = $(this).parent().parent().children().text();
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var requestId = getUrlParameter("requestId");
		$(this).parent().parent().remove();
		CallHelper.doPost("deleteJob", {requestId: requestId, jobId: parseInt(id), _csrf: csrfToken}, 
				JobPrice.savePriceSuccess, JobPrice.savePriceFail);
	},
	
	saveNewPrice: function(id, price, amount) {
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var requestId = getUrlParameter("requestId");
		CallHelper.doPost("saveJobPrice", {requestId: requestId, jobId: parseInt(id), newPrice: parseFloat(price), newAmount: parseInt(amount), _csrf: csrfToken}, 
				JobPrice.savePriceSuccess, JobPrice.savePriceFail);
	},
	
	savePriceSuccess: function(newPrice) {
		$('#current-price').html(newPrice + " &euro;");
	},
	
	savePriceFail: function() {
		
	},
	
	onClickListener: function() {
		
		var id = $(this).parent().parent().children().text();
		var price = $(this).parent().parent().children().next().next().children().val();
		var amount = $(this).parent().parent().children().next().next().next().children().val();
		console.log(amount);
		
		if (!JobPrice.scope.editing) {
			$(this).removeClass("glyphicon-pencil");
			$(this).addClass("glyphicon-floppy-disk");
			$(this).parent().parent().children("td").each(function() {
				$(this).children("input").attr("readonly", false);
			});
			JobPrice.scope.editing = true;
		} else {
			$(this).removeClass("glyphicon-floppy-disk");
			$(this).addClass("glyphicon-pencil");
			$(this).parent().parent().children("td").each(function() {
				$(this).children("input").attr("readonly", true);
			});
			JobPrice.scope.editing = false;
			JobPrice.saveNewPrice(id, price, amount);
		}
//		var value = $(this).val();
//		
//		$(this).attr("readonly", false);
//		var html = "<span class='glyphicon glyphicon-floppy-disk save-glyphicon' id = 'save-glyph' aria-hidden='true'></span>";
//		if (!JobPrice.scope.hasIcon) {
//			$(this).after(html);
//		}
	}
};

var ServiceAction = {

	initButton: function() {
		$('#add-job').click(function() {
			var csrfToken = $("meta[name='_csrf']").attr("content"); 
			var requestId = getUrlParameter("requestId");
			var description = $('#job_description').val();
			var price = $('#job_price').val();
			var amount = parseInt($('#job_amount').val());
			var type = $('#job_type').children(":selected").attr("id");
			console.log(type);
			if ($.isNumeric(price) && $.isNumeric(amount)) {
				CallHelper.doPost("addServiceJob", {_csrf: csrfToken, requestId: requestId, description: description, price: price, amount: amount, type: type}, ServiceAction.successCall, ServiceAction.failCall);
			} else {
				var html = "";
				
				if (description == "" ) {
					html += "<p> Sisesta korrektne kirjeldus";
				} 
				if (!$.isNumeric(price)) {
					html += "<p> Sisesta korrektne hind";
				}
				if (!$.isNumeric(amount)) {
					html += "<p> Sisesta korrektne kogus";
				}
				$('#order-job-errors').html(html);
			}
		});
	},
	
	successCall: function(job) {
		var html = "<tr><td>" + job.id + "</td>" +
				"<td>" + job.description + "</td>" +
						"<td>" + job.price + "</td>" +
								"<td>" + job.amount + "</td>" +
										"<td></td><td></td></tr>";
		$('#current_jobs').append(html);
		$('#order-jobs').removeClass('hidden-section');
		$('#current-price').html(job.newPrice + " &euro;");
	},
	
	failCall: function() {
		
	}
};

var ServicePart = {
		
		initButton: function() {
			$('#add-part').click(function() {
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var name = $('#part_name').val();
				var price = $('#part_price').val();
				var amount = $('#part_amount').val();
				var seriaNr = $('#part_seria_nr').val();
				var requestId = getUrlParameter("requestId");
				
				if ($.isNumeric(amount) && $.isNumeric(price) && name != "" && seriaNr != "") {
					CallHelper.doPost("addServicePart", 
							{name: name, price: price, amount: amount, seriaNr: seriaNr, requestId: requestId, _csrf: csrfToken}, 
							ServicePart.addPartSuccess, 
							ServicePart.addPartFail);
				} else {
					html = "";
					if (!$.isNumeric(amount)) {
						html += "<p>Sisesta korrektne kogus";
					}
					if (!$.isNumeric(price)) {
						html += "<p>Sisesta korrektne hind";
					}
					
					if (name == "") {
						html += "<p>Sisesta korrektne nimi";
					}
					if (seriaNr == "") {
						html += "<p>Sisesta korrektne seeria number";
					}
					$("#service-part-error").html(html);
					console.log("Sisesta korrektsed numbrid");
				}
			});
			ServicePart.initDeleteButton;
		},
		
		initDeleteButton: function() {
			$('.remove-part').click(function() {
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var id = parseInt($(this).text());
				var requestId = getUrlParameter("requestId");
				CallHelper.doPost("deleteServicePart", 
						{servicePartId: id, requestId: requestId, _csrf: csrfToken}, 
						ServicePart.deleteDeviceSuccess, 
						DeleteDevice.deleteDeviceFail);
				$(this).parent().parent().remove();
			});
		},
		
		deleteDeviceSuccess: function(newPrice) {
			$('#current-price').html(newPrice + " &euro;");
		},
		
		addPartSuccess: function(servicePart) {
			$('#current-price').html(servicePart.totalPrice + " &euro;");
			var html = "<tr><td>" + servicePart.id + "</td>" +
					"<td>" + servicePart.name + "</td>" +
							"<td>" + servicePart.price + "</td>" +
									"<td>" + servicePart.count + "</td>" + "<td></td></tr>";
			$('#order-parts').removeClass('hidden-section');
			$('#current_parts').append(html);
		},
		
		addPartFail: function() {
			console.log("fail");
			alert("Juhtus midagi halba");
		},
		
		populateSelectors: function() {
			$('.selector').each(function(index) {
				var html = "<option></option>";
				OrderService.scope.devices.forEach(function (device) {
					html += "<option id = '" + device.deviceId + "'>" + device.name + "</option>"
				});
				$(this).html(html);
			});
			
			$('.currentDeviceSelector').on('change', function() {
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var name = $(this).val();
				var deviceId = parseInt($(this).children(":selected").attr("id"));
				var partId = parseInt($(this).parent().parent().children("td:first").text());
				
				CallHelper.doPost("updateServicePart", 
						{deviceId: deviceId, partId: partId, _csrf: csrfToken}, 
						ServicePart.updateStatusSuccess, 
						ServicePart.updateStatusFail);
			});
			$('.currentJobSelector').on('change', function() {
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var name = $(this).val();
				var deviceId = parseInt($(this).children(":selected").attr("id"));
				var jobId = parseInt($(this).parent().parent().children("td:first").text());
				
				CallHelper.doPost("updateServiceJob", 
						{deviceId: deviceId, jobId: jobId, _csrf: csrfToken}, 
						ServiceAction.updateStatusSuccess, 
						ServiceAction.updateStatusFail);
			});
		},
		
		updateStatusSuccess: function() {
			
		},
		updateStatusFail: function() {
			
		}
}

var ServiceDeviceStatus = {
		initSelector: function() {
			$('#serviceDeviceStatus').change(function() {
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var serviceDeviceId = $(this).parent().parent().children(".orderDeviceIds").text();
				var newDeviceStatusId = parseInt($(this).val()); 
				
				CallHelper.doPost("updateServiceDeviceStatus", 
						{deviceId: serviceDeviceId, status: newDeviceStatusId, _csrf: csrfToken}, 
						ServiceDeviceStatus.updateStatusSuccess, 
						ServiceDeviceStatus.updateStatusFail);
			});
		},
		
		updateStatusSuccess: function() {
			console.log("success");
		},
		
		updateStatusFail: function() {
			console.log("fail");
		}
}

var DeleteDevice = {
	scope: {
	},
		
	initButtons: function() {
//		$('.remove-device').click(DeleteDevice.onClickListener);
		$('.remove-device').click(function() {
			DeleteDevice.scope.elementId = $(this).parent().parent().attr('id');
			var csrfToken = $("meta[name='_csrf']").attr("content"); 
			var id = parseInt($(this).text());
			var requestId = getUrlParameter("requestId");
			CallHelper.doPost("deleteServiceDevice", 
					{deviceId: id, requestId: requestId, _csrf: csrfToken}, 
					DeleteDevice.deleteDeviceSuccess, 
					DeleteDevice.deleteDeviceFail);
			
		});
	},
	
	deleteDeviceSuccess: function(data) {
		
		if (data == "fail") {
			alert("Seadet ei saa kustutada, seade on seotud varuosadega!");
		}
		console.log(data);
	},
	
	deleteDeviceFail: function(data) {
		if (data.responseText != "fail") {
			$("#" + DeleteDevice.scope.elementId).remove();
		}
		else {
			alert("Seadet ei saa kustutada, seade on seotud varuosadega!");
		}
		console.log(data);
	},
	
	onClickListener: function() {
		//alert("Hello");
	}
};

var SaveOrder = {
		initButton: function() {
			$('#saveOrder').click(SaveOrder.onClickListener);
		},
		
		onClickListener: function(event) {
			event.preventDefault();
			
			var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        	var csrfToken = $("meta[name='_csrf']").attr("content"); 
        	var csrfHeader = $("meta[name='_csrf_header']").attr("content");

			var devices = OrderService.getDevices();
			var description = $('#description').val();
			var requestId = getUrlParameter("requestId");
			var initialCall = $('#initialCall').text();
			console.log(initialCall);
			CallHelper.doPost("saveOrder", 
					{devices: JSON.stringify(devices), description: description, requestId: requestId, initialCall: initialCall, _csrf: csrfToken}, 
					SaveOrder.saveOrderSuccess, 
					SaveOrder.saveOrderFail)
		},
		
		saveOrderSuccess: function(data) {
			console.log(data);
		},
		
		saveOrderFail: function() {
			console.log("fail");
		}
};

var OrderService = {
		scope: {
		},
		
		populateArray: function() {
			OrderService.scope.devices = [];
			$('.orderDeviceIds').each(function(index) {
				var id = parseInt($(this).text());
				var name = $(this).next().text();
				var device = {};
				device.deviceId = id;
				device.name = name;
				OrderService.scope.devices.push(device);
			});
		},
		
		getDevices: function() {
			var data = [];
			
			OrderService.scope.list.forEach(function (device) {
				data.push(parseInt(device));
			});
			return data;
		},
		
		checkIfDeviceIsInOrder: function(device) {
			var currentId = device.deviceId;
			var result = false;
			$('.orderDeviceIds').each(function(index) {
				console.log(parseInt($(this).text()) + " " + parseInt(currentId))
				if (parseInt($(this).text()) === parseInt(currentId)) {
					result = true;
				}
			});
			return result;
		}
}

var postNewDevice = {
		initField: function() {
			$("#saveNewDevice").click(postNewDevice.onClickListener);
		},
		
		onClickListener: function (event) {
			event.preventDefault();
			
			var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        	var csrfToken = $("meta[name='_csrf']").attr("content"); 
        	var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        	var requestId = getUrlParameter("requestId");
            var data = {};
            var headers = [];

            var newDevice = {};
            var deviceType = $('#type').val();
            data[csrfParameter] = csrfToken;
            var description = $('#text').val();
            data["requestId"] = requestId;
            data["newDevice.name"] = $('#name').val();
            data["newDevice.registrationNumber"] = $('#regNr').val();
            data["newDevice.model"] = $('#model').val();
            data["newDevice.manufacturer"] = $('#manufacturer').val();
            data["newDevice.description"] = description
            data["deviceId"] = deviceType;
            
            headers[csrfHeader] = csrfToken; 
        	
            $.ajax({
                type: "POST",
                async: false,
                url: 'saveDevice',
                headers: headers,    
                data: data,
                success: function (device) {
                	serviceDevice.processDevice(device);
                }
            }); 
		}
};

var postOrder = {
	initField: function() {
		$('#postOrder').click(postOrder.doPost);
	},
	
	doPost: function() {
		var description = $('#desc').val();
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		
		CallHelper.doPost("saveRequest", {description: description, _csrf: csrfToken}, postOrder.successCall, postOrder.failCall);
	},
	
	successCall: function() {
		location.href="myRequests";
	},
	
	failCall: function() {
		
	}
};

var serviceDevice = {
	scope: {
	},
	
	initField: function() {
		$('#add_device_to_order').click(serviceDevice.addDeviceToOrder);
		OrderService.scope.list = [];
	},
	
	addDeviceToOrder: function() {
		var id = $('#device-selecter').val();
		console.log(id);
		
		var device = {};
		device.deviceId = id;
		device.name = $('#device-selecter option[value="' + id + '"]').text();
		if (!OrderService.checkIfDeviceIsInOrder(device)) {
			var csrfToken = $("meta[name='_csrf']").attr("content"); 
			var requestId = getUrlParameter("requestId");
			serviceDevice.processDevice(device);
			OrderService.scope.list.push(device.deviceId);
			OrderService.scope.devices.push(device);
			CallHelper.doPost("addDevice", {requestId: requestId, deviceId: device.deviceId, _csrf: csrfToken}, serviceDevice.successCall, serviceDevice.failCall);
		} else {
			console.log("Seade on juba tellimusel")
		}
		console.log(OrderService.scope.list);
		
		$("#addDeviceToOrder").submit(function(){
		    $.post($(this).attr('action'), $(this).serialize(), function(json) {
		        alert(json);
		      }, 'json');
		});
	},
	
	successCall: function() {
		
	},
	
	failCall: function() {
		
	},
	
	processDevice: function(device) {
		var html = "<tr>" +
						"<td class = 'orderDeviceIds'>" + device.deviceId + "</td>" +
						"<td>" + device.name + "</td>" +	
						"<td></td>" +
//						"<td><span class='glyphicon glyphicon-remove remove-device' aria-hidden='true'><a style='display:none;'>" + device.deviceId + "</a></span></td>" + 
				"<tr>";
		$('#current_devices').append(html);
		$('#order-devices').removeClass('hidden-section');
		ServicePart.populateSelectors();
	}
};

var repairDeviceSelector = {
	
	scope: {
	},
	
	initField: function() {
		$('#device-selecter').change(repairDeviceSelector.optionOnChangeListener);
		$('#addNewDevice').click(repairDeviceSelector.showNewDeviceForm);
		serviceDevice.initField();
		repairDeviceSelector.scope.showNewDevice = false;
	},
	
	optionOnChangeListener: function() {
		var id = $('#device-selecter').val();
		if (id == -1) {
			if (!repairDeviceSelector.scope.showNewDevice) {
				repairDeviceSelector.showNewDeviceForm();
			} 
		} else {
			repairDeviceSelector.scope.showNewDevice = false;
			$('#newDeviceForm').css('opacity', '0');
			$('#newDeviceForm').css("height", "0");
		}
	},
	
	showNewDeviceForm: function() {
		var req = getUrlParameter("requestId");
		location.href = "addDevice?requestId=" + req;
//		repairDeviceSelector.scope.showNewDevice = true;
//		$('#newDeviceForm').css('opacity', '1');
//		$('#newDeviceForm').css('height', '100%');
//		$('#device-selecter').val(-1);
	}
};