package ee.iapb61.idu0200.model.bean;

import java.io.Serializable;
import java.util.List;

import ee.iapb61.idu0200.model.DeviceType;

public class DeviceTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3272957268247130011L;
	
	private List<DeviceType> deviceTypes;
	
	public List<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}
	
	public void setDeviceTypes(List<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
}
