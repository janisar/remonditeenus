package ee.iapb61.idu0200.factory;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.service.RequestService;

public class RequestFactory {

	@Autowired
	private RequestService requestService;
	
	public ServiceRequest createNewRequest(String description, Customer customer, ServiceRequestStatusType serviceRequestStatusType) {
		ServiceRequest serviceRequest = new ServiceRequest();
		
		serviceRequest.setCreatedDate(new Date());
		serviceRequest.setServiceDescriptionByCustomer(description);
		serviceRequest.setCustomer(customer);
		serviceRequest.setServiceRequestStatusType(serviceRequestStatusType);
		
		return serviceRequest;
	}

//	private ServiceRequestStatusType getDefaultServiceRequestStatusType() {
//		ServiceRequestStatusType statusType = new ServiceRequestStatusType();
//		statusType.setId(1);
//		statusType.setName("registreeritud");
//		return statusType;
//	}
}
