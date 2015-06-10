package ee.iapb61.idu0200.factory;

import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;

public class ServiceFactory {

	public ServiceOrder convertFrom(ServiceOrderBean serviceOrderBean) {
		ServiceOrder serviceOrder = new ServiceOrder();
		
		serviceOrder.setCreatedBy(serviceOrderBean.getCreatedBy());
	
		return null;
	}

}
