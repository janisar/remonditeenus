package ee.iapb61.idu0200.model.bean;

import java.io.Serializable;

import ee.iapb61.idu0200.model.ServiceOrder;

public class ServiceOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261694677576404298L;

	String hello;
	
	private ServiceOrder serviceOrder;
	
	public ServiceOrder getServiceOrder() {
		if (serviceOrder != null) {
			this.serviceOrder = new ServiceOrder();
		}
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	public String getHello() {
		return hello;
	}
	
	public void setHello(String hello) {
		this.hello = "Hello";
	}
}
