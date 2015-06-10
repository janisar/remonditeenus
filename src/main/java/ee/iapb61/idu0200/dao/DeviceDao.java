package ee.iapb61.idu0200.dao;

import java.util.List;

import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.ServiceDevice;

public interface DeviceDao {

	public List<Device> list();
	
	Device findById(String id);

	public List<DeviceType> deviceTypelist();

	public int saveDevice(Device device);

	public void saveServiceDevice(ServiceDevice sDevice);

	public List<Device> searchDevices(String query);
}
