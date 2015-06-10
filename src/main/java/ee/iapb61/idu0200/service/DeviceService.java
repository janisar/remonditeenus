package ee.iapb61.idu0200.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.dao.DeviceDao;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;

public class DeviceService {

	@Autowired
	DeviceDao deviceDao;
	
	public void setAllDevices(DeviceBean deviceBean) {
		List<Device> devices = deviceDao.list();
		deviceBean.setAllDevices(devices);
	}
	
	public void setAllDeviceTypes(DeviceBean deviceBean) {
		List<DeviceType> deviceTypes = deviceDao.deviceTypelist();
		deviceBean.getDeviceTypeBean().setDeviceTypes(deviceTypes);
	}
	
	public Device getDeviceById(String id) {
		return deviceDao.findById(id);
	}

	public int saveDevice(Device device) {
		return deviceDao.saveDevice(device);
	}

	public DeviceType getDeviceTypeById(ServiceOrderBean serviceOrderBean,
			int deviceTypeId) {
		List<DeviceType> deviceTypes = serviceOrderBean.getDeviceBean().getDeviceTypeBean().getDeviceTypes();
		for (DeviceType deviceType : deviceTypes) {
			if (deviceType.getId() == deviceTypeId) {
				return deviceType;
			}
		}
		return null;
	}

	public void saveServiceDevice(ServiceDevice sDevice) {
		deviceDao.saveServiceDevice(sDevice);
	}

	public List<Device> searchAllDevices(Device device, Integer type) {
		String query = "from Device as d ";
                   
		query += "where d.deviceType.id="+type;
		if (device.getDeviceId() != 0) {
			query += " AND d.deviceId="+device.getDeviceId();
		}
		if (!StringUtils.isBlank(device.getName())) {
			query += " AND d.name='" + device.getName() + "'";
		} 
		if (!StringUtils.isBlank(device.getModel())) {
			query += " AND d.model = '" + device.getModel() + "'";
		}
		
		return deviceDao.searchDevices(query);
	}
}
