package ee.iapb61.idu0200.dao;

import java.util.List;

import ee.iapb61.idu0200.model.ServiceActionStatusType;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.ServiceType;

public interface ClassifierDao {

	List<ServiceActionStatusType> getServiceActionStatusTypes();

	List<ServiceType> getServiceTypes();

	List<ServiceRequestStatusType> getServiceRequestStatusTypes();

	ServiceRequestStatusType getRequestStatusTypeById(Integer status);
}
