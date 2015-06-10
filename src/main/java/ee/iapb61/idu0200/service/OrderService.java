package ee.iapb61.idu0200.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.dao.DeviceDao;
import ee.iapb61.idu0200.dao.OrderDao;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.ServiceAction;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceDeviceStatusType;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServicePart;
import ee.iapb61.idu0200.model.ServiceType;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.model.user.Person;

public class OrderService {

	@Autowired
	DeviceDao deviceDao;
	
	@Autowired 
	OrderDao orderDao;

	public List<ServiceDevice> getServiceDevices(
			ServiceOrderBean serviceOrderBean) {
		ServiceOrder serviceOrder = serviceOrderBean.getServiceOrder();
		if (serviceOrder == null) {
			serviceOrderBean.setServiceOrder(new ServiceOrder());
			serviceOrderBean.getServiceOrder().setServiceDevices(new ArrayList<ServiceDevice>());
		} 
		return serviceOrderBean.getServiceOrder().getServiceDevices();
	}

	public void addNewDevice(List<ServiceDevice> serviceDevices, Device device) {
		ServiceDevice serviceDevice = new ServiceDevice();
		serviceDevice.setDevice(device);
		serviceDevices.add(serviceDevice);
	}

	public ServiceOrder getOrderByRequestId(String requestId) {
		return orderDao.getOrderByReqyestId(requestId);
	}

	public int saveOrder(ServiceOrder serviceOrder) {
		return orderDao.saveOrUpdateOrder(serviceOrder);
	}

	public void saveOrderDevice(ServiceDevice serviceDevice) {
		orderDao.saveServiceDevice(serviceDevice);
	}

	public void updateOrder(ServiceOrder serviceOrder, Person updatedBy) {
		serviceOrder.setUpdatedDate(new Date());
//		if (serviceOrder.getUpdatedBy() == null || !serviceOrder.getUpdatedBy().equals(updatedBy))
//			serviceOrder.setUpdatedBy(updatedBy);
		orderDao.updateServiceOrder(serviceOrder);
	}

	public void deleteServiceDevice(ServiceDevice serviceDevice) {
		orderDao.deleteServiceDevice(serviceDevice);
	}

	public ServiceDevice getServiceDeviceById(Integer deviceId) {
		return orderDao.getOrderServiceDeviceByServiceDeviceId(deviceId);
	}

	public List<ServiceDeviceStatusType> getServiceDeviceClassifiers() {
		return orderDao.getServiceDeviceClassifiers();
	}

	public ServiceDeviceStatusType getServiceDeviceStatusTypeById(Integer status) {
		return orderDao.getServiceDeviceStatusTypeById(status);
	}

	public void updateServiceDevice(ServiceDevice serviceDevice) {
		orderDao.updateServiceDevice(serviceDevice);
	}

	public void saveServicePart(ServicePart servicePart) {
		orderDao.saveServicePart(servicePart);
	}
	
	public void updateServicePart(ServicePart servicePart) {
		orderDao.updateServicePart(servicePart);
	}

	public ServicePart getServicePartById(Integer servicePartId) {
		return orderDao.getServicePartById(servicePartId);
	}

	public void deleteServicePart(ServicePart servicePart) {
		orderDao.deleteServicePart(servicePart);
	}

	public ServiceType getServiceTypeById(Integer type) {
		return orderDao.getServiceTypeById(type);
	}

	public void saveServiceAction(ServiceAction serviceAction) {
		orderDao.saveServiceAction(serviceAction);
	}

	public ServiceAction getServiceActionById(Integer jobId) {
		return orderDao.getServiceActionById(jobId);
	}

	public void updateServiceAction(ServiceAction serviceAction) {
		orderDao.updateServiceAction(serviceAction);
	}

	public void deleteObject(ServiceAction serviceAction) {
		orderDao.deleteObject(serviceAction);
	}

	public String deleteServiceOrder(ServiceOrder order) {
		return orderDao.deleteOrder(order);
	}
}
