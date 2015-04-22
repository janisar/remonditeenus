var serviceDevice = {
	scope: {
	},
	
	initField: function() {
		$('#add_device_to_order').click(serviceDevice.addDeviceToOrder);
	},
	
	addDeviceToOrder: function() {
		var device = $('#device-selecter').val();
		var crsfName = $('#csrf').prop("tagName");
		var crsfValue = $('#csrf').val();
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content"); 
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
		var data = {};
		var headers = {};
		
		data["deviceId"] = device;
		data[csrfParameter] = csrfToken;
		headers[csrfHeader] = csrfToken;
		
		$.ajax({
            type: "POST",
            url: "addDevice",
            data: data,
            headers: headers,
            dataType: 'json',
            success: function (msg) {
            	var device = jQuery.parseJSON(JSON.stringify(msg));
            	serviceDevice.processDevice(device);
            },
            error: function (errormessage) {
                //do something else
            }
        });
		//$('#newDeviceSubmitForm').submit();
	},
	
	processDevice: function(device) {
		var html = "<tr>" +
						"<td>" + device.deviceId + "</td>" +
						"<td>" + device.name + "</td>" +				
						"<td>" + device.model + "</td>" +
						"<td>" + device.manufacturer + "</td>" +
						"<td></td>" +
				"<tr>";
		$('#current_devices').append(html);
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
		}
	},
	
	showNewDeviceForm: function() {
		repairDeviceSelector.scope.showNewDevice = true;
		$('#newDeviceForm').css('opacity', '1');
		$('#device-selecter').val(-1);
	}
};