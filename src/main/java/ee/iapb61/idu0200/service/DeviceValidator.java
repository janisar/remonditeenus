package ee.iapb61.idu0200.service;


import org.apache.commons.lang3.StringUtils;

import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.ErrorList;

public class DeviceValidator {

	public ErrorList validate(Device device) {
		ErrorList errorList = new ErrorList();
		
		if (StringUtils.isEmpty(device.getName())) {
			errorList.getErrors().add("Seadme nimi ei tohi olla tühi");
		}
		if (StringUtils.isEmpty(device.getRegistrationNumber())) {
			errorList.getErrors().add("Seadme registreerimisnumber ei tohi olla tühi");
		}
		return errorList;
	}
}
