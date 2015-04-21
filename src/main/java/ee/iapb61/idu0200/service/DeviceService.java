package ee.iapb61.idu0200.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.dao.DeviceDao;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.bean.DeviceBean;

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
}
