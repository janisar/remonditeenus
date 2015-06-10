package ee.iapb61.idu0200.dao;

import java.util.List;

import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.user.Customer;

public interface RequestDao {

	public List<ServiceRequest> getAllRequests();

	public List<ServiceRequestStatusType> getStatusTypes();

	public void saveOrUpdateRequest(ServiceRequest serviceRequest);

	public ServiceRequest getServiceRequestById(String requestId);

	public List<ServiceRequest> getAllRequestsByUser(Customer person);


	public ServiceRequestStatusType getStatusTypeById(int i);

	public boolean deleteRequest(ServiceRequest request);

}
