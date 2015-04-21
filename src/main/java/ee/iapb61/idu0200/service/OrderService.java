package ee.iapb61.idu0200.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.dao.DeviceDao;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;

public class OrderService {

	@Autowired
	DeviceDao deviceDao;

	public List<ServiceDevice> getServiceDevices(
			ServiceOrderBean serviceOrderBean) {
		ServiceOrder serviceOrder = serviceOrderBean.getServiceOrder();
		if (serviceOrder == null) {
			serviceOrderBean.setServiceOrder(new ServiceOrder());
			serviceOrderBean.getServiceOrder().setServiceDevices(new ArrayList<ServiceDevice>());
		} 
		return serviceOrderBean.getServiceOrder().getServiceDevices();
	}

	public void addNewDevice(List<ServiceDevice> serviceDevices, String id) {
		Device device = deviceDao.findById(id);
		System.out.println("!!!!!!!" + device.getName());
		ServiceDevice serviceDevice = new ServiceDevice();
		serviceDevice.setDevice(device);
		serviceDevices.add(serviceDevice);
	}
}
