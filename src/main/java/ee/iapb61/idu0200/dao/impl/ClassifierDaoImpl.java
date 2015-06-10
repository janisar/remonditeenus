package ee.iapb61.idu0200.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.iapb61.idu0200.dao.ClassifierDao;
import ee.iapb61.idu0200.dao.DaoConstants;
import ee.iapb61.idu0200.dao.HibernateUtil;
import ee.iapb61.idu0200.model.ServiceActionStatusType;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.ServiceType;

public class ClassifierDaoImpl implements ClassifierDao, DaoConstants {

	private SessionFactory sessionFactory;
	
	public ClassifierDaoImpl() {
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
	public List<ServiceActionStatusType> getServiceActionStatusTypes() {
		List<ServiceActionStatusType> serviceDeviceStatusTypes = new ArrayList<ServiceActionStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceActionStatusType";
        Query query = session.createQuery(hql);
        serviceDeviceStatusTypes = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDeviceStatusTypes.size() > 0) {
			return serviceDeviceStatusTypes;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceType> getServiceTypes() {
		List<ServiceType> serviceDeviceStatusTypes = new ArrayList<ServiceType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceType";
        Query query = session.createQuery(hql);
        serviceDeviceStatusTypes = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDeviceStatusTypes.size() > 0) {
			return serviceDeviceStatusTypes;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceRequestStatusType> getServiceRequestStatusTypes() {
		List<ServiceRequestStatusType> serviceRequestStatusType= new ArrayList<ServiceRequestStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceRequestStatusType";
        Query query = session.createQuery(hql);
        serviceRequestStatusType = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceRequestStatusType.size() > 0) {
			return serviceRequestStatusType;
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ServiceRequestStatusType getRequestStatusTypeById(Integer status) {
		List<ServiceRequestStatusType> requestStatuses = new ArrayList<ServiceRequestStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceRequestStatusType WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(status));
        requestStatuses = query.list();
        
		session.beginTransaction();
		session.close();
		if (requestStatuses.size() > 0) {
			return requestStatuses.get(0);
		} else {
			return null;
		}
	}

}
