package ee.iapb61.idu0200.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ee.iapb61.idu0200.dao.RequestDao;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.Person;

public class RequestService {

	@Autowired
	private RequestDao requestDao;
	
	public List<ServiceRequest> getAllRequests() {
		return requestDao.getAllRequests();
	}
	
	public List<ServiceRequestStatusType> getStatusTypes() {
		return requestDao.getStatusTypes();
	}
	
	public void saveRequest(ServiceRequest serviceRequest) {
		requestDao.saveOrUpdateRequest(serviceRequest);
	}

	public void setRequestToAccepted(Person person, ServiceRequest serviceRequest) {
		ServiceRequestStatusType status = getAcceptedServiceRequestStatusType();
		serviceRequest.setServiceRequestStatusType(status);
		serviceRequest.setCreatedBy(person);
		requestDao.saveOrUpdateRequest(serviceRequest);
	}

	public void setRequestToDeclined(Person person, String requestId) {
		ServiceRequest serviceRequest = requestDao.getServiceRequestById(requestId);	
		ServiceRequestStatusType status = getDeclinedServiceRequestStatusType();
		serviceRequest.setServiceRequestStatusType(status);
		serviceRequest.setCreatedBy(person);
		requestDao.saveOrUpdateRequest(serviceRequest);
	}

	private ServiceRequestStatusType getDeclinedServiceRequestStatusType() {
		ServiceRequestStatusType status = new ServiceRequestStatusType();
		status.setId(2);
		status.setName("tagasi lykatud");
		return status;
	}

	private ServiceRequestStatusType getAcceptedServiceRequestStatusType() {
		ServiceRequestStatusType status = new ServiceRequestStatusType();
		status.setId(1);
		status.setName("registreeritud");
		return status;
	}

	public ServiceRequest getRequestById(String requestId) {
		return requestDao.getServiceRequestById(requestId);
	}

	public ServiceOrder generateOrder(Person person, ServiceRequest serviceRequest) {
		ServiceOrder serviceOrder = new ServiceOrder();
		serviceOrder.setCreatedBy(person);
		serviceOrder.setCreatedDate(new Date());
		return serviceOrder;
	}

	public List<ServiceRequest> getAllRequestsByUser(Customer person) {
		return requestDao.getAllRequestsByUser(person);
	}


	public ServiceRequestStatusType getStatusTypeById(int i) {
		return requestDao.getStatusTypeById(i);
	}
	
	public void updateServiceRequest(ServiceRequest serviceRequest) {
		requestDao.saveOrUpdateRequest(serviceRequest);
	}

	public boolean deleteRequest(ServiceRequest request) {
		return requestDao.deleteRequest(request);
	}
}
