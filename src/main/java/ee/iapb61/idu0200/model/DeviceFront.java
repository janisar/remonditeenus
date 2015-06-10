package ee.iapb61.idu0200.model;


public class DeviceFront {

	
	private int deviceId;
	private String name;
	private String model;
	private String regNr;
	
	public DeviceFront(int deviceId, String name) {
		this.deviceId = deviceId;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getRegNr() {
		return regNr;
	}
	
	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}
}
