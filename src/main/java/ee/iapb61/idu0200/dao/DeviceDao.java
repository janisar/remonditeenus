package ee.iapb61.idu0200.dao;

import java.util.List;

import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;

public interface DeviceDao {

	final String JDBC_DRIVER = "org.postgresql.Driver"; 
	
	public List<Device> list();
	
	Device findById(String id);

	public List<DeviceType> deviceTypelist();
}
