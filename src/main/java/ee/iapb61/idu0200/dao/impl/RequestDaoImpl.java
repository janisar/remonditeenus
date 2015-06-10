package ee.iapb61.idu0200.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.iapb61.idu0200.dao.DaoConstants;
import ee.iapb61.idu0200.dao.HibernateUtil;
import ee.iapb61.idu0200.dao.RequestDao;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.user.Customer;

public class RequestDaoImpl implements RequestDao, DaoConstants {

	private SessionFactory sessionFactory;
	
	public RequestDaoImpl() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			System.err.println();
		} 
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceRequest> getAllRequests() {
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		serviceRequests = session.createQuery("from ServiceRequest as s").list();
		for (ServiceRequest sr : serviceRequests) {
		    Hibernate.initialize(sr.customer);
		    Hibernate.initialize(sr.serviceRequestStatusType);
		}
		
		session.close();
		if (serviceRequests.size() > 0) {
			return serviceRequests;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceRequestStatusType> getStatusTypes() {
		List<ServiceRequestStatusType> statusTypes = new ArrayList<ServiceRequestStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		statusTypes = session.createQuery("from ServiceRequestStatusType as s").list();
		
		session.close();
		if (statusTypes.size() > 0) {
			return statusTypes;
		} else {
			return null;
		}
	}

	@Override
	public void saveOrUpdateRequest(ServiceRequest serviceRequest) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(serviceRequest);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ServiceRequest getServiceRequestById(String requestId) {
		List<ServiceRequest> serviceRequest = new ArrayList<ServiceRequest>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceRequest WHERE service_request= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(requestId));
        serviceRequest = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceRequest.size() > 0) {
			return serviceRequest.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceRequest> getAllRequestsByUser(Customer person) {
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		String hql = "FROM ServiceRequest WHERE customer_fk= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(person.getId()));
		serviceRequests = query.list();
		for (ServiceRequest sr : serviceRequests) {
		    Hibernate.initialize(sr.customer);
		    Hibernate.initialize(sr.serviceRequestStatusType);
		}
		
		session.close();
		if (serviceRequests.size() > 0) {
			return serviceRequests;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceRequestStatusType getStatusTypeById(int i) {
		List<ServiceRequestStatusType> serviceRequestStatusTypes = new ArrayList<ServiceRequestStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		String hql = "FROM ServiceRequestStatusType WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", i);
        serviceRequestStatusTypes = query.list();
		
		session.close();
		if (serviceRequestStatusTypes.size() > 0) {
			return serviceRequestStatusTypes.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteRequest(ServiceRequest request) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE ServiceRequest WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(request.getId()));
        try {
        	query.executeUpdate();
        } catch (HibernateException e) {
        	return false;
        }
		session.close();
		
		return true;		
	}

}
