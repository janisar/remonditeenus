package ee.iapb61.idu0200.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.user.Person;

@Component
public class ServiceOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261694677576404298L;

	private DeviceBean deviceBean;
	
	private ServiceOrder serviceOrder;
	private List<ServiceDevice> devices;
	private Person createdBy;
	private int serviceOrderStatus;
	
	public ServiceOrderBean() {
		deviceBean = new DeviceBean();
	}
	
	public ServiceOrder getServiceOrder() {
		if (serviceOrder != null) {
			this.serviceOrder = new ServiceOrder();
		}
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	public List<ServiceDevice> getDevices() {
		if (this.devices == null) {
			this.devices = new ArrayList<ServiceDevice>();
		}
		return devices;
	}
	
	public void setDevices(List<ServiceDevice> devices) {
		
		this.devices = devices;
	}
	
	public DeviceBean getDeviceBean() {
		return deviceBean;
	}
	
	public void setDeviceBean(DeviceBean deviceBean) {
		this.deviceBean = deviceBean;
	}
	
	public Person getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}
	
	public int getServiceOrderStatus() {
		return serviceOrderStatus;
	}
	
	public void setServiceOrderStatus(int serviceOrderStatus) {
		this.serviceOrderStatus = serviceOrderStatus;
	}
}
