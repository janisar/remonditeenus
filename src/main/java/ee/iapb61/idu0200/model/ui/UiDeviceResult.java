package ee.iapb61.idu0200.model.ui;

import java.util.List;

import ee.iapb61.idu0200.model.DeviceFront;

public class UiDeviceResult {

	private List<DeviceFront> devices;
	
	private List<String> messages;
	
	public List<DeviceFront> getDevices() {
		return devices;
	}
	
	public void setDevices(List<DeviceFront> devices) {
		this.devices = devices;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
