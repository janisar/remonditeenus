var serviceDevice = {
	scope: {
	},
	
	initField: function() {
		$('#add_device_to_order').click(serviceDevice.addDeviceToOrder);
	},
	
	addDeviceToOrder: function() {
		var device = $('#device-selecter').val();
		$.ajax({
            type: "POST",
            url: "addDevice",
            data: {
            	deviceId: device
            },
            success: function (msg) {
               //do something
            },
            error: function (errormessage) {

                //do something else

            }
        });
		//$('#newDeviceSubmitForm').submit();
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