package ee.iapb61.idu0200.model.bean;

import java.io.Serializable;
import java.util.List;

import ee.iapb61.idu0200.model.Device;

public class DeviceBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DeviceTypeBean deviceTypeBean;
	
	private List<Device> allDevices;
	
	public DeviceBean() {
		deviceTypeBean = new DeviceTypeBean();
	}
	
	public List<Device> getAllDevices() {
		return allDevices;
	}
	
	public void setAllDevices(List<Device> allDevices) {
		this.allDevices = allDevices;
	}
	
	public DeviceTypeBean getDeviceTypeBean() {
		return deviceTypeBean;
	}
}
