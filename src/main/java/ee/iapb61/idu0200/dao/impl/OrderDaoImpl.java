package ee.iapb61.idu0200.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.iapb61.idu0200.dao.DaoConstants;
import ee.iapb61.idu0200.dao.HibernateUtil;
import ee.iapb61.idu0200.dao.OrderDao;
import ee.iapb61.idu0200.model.ServiceAction;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceDeviceStatusType;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServicePart;
import ee.iapb61.idu0200.model.ServiceType;

public class OrderDaoImpl implements OrderDao, DaoConstants {

private SessionFactory sessionFactory;
	
	public OrderDaoImpl() {
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
	public ServiceOrder getOrderByReqyestId(String requestId) {
		List<ServiceOrder> serviceOrder = new ArrayList<ServiceOrder>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceOrder WHERE serviceRequest.id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(requestId));
        serviceOrder = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceOrder.size() > 0) {
			return serviceOrder.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int saveOrUpdateOrder(ServiceOrder serviceOrder) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(serviceOrder);
		session.getTransaction().commit();
		int id = serviceOrder.getId();
		session.close();
		return id;
	}

	@Override
	public void saveServiceDevice(ServiceDevice serviceDevice) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(serviceDevice);
		session.getTransaction().commit();
		//int id = serviceDevice.getId();
		session.close();
	}

	@Override
	public void updateServiceOrder(ServiceOrder serviceOrder) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(serviceOrder);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteServiceDevice(ServiceDevice serviceDevice) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE ServiceDevice WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(serviceDevice.getId()));
		query.executeUpdate();
//		session.delete(serviceDevice);
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceDevice getOrderServiceDeviceByDeviceId(Integer deviceId) {
		List<ServiceDevice> serviceDevices = new ArrayList<ServiceDevice>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceDevice WHERE device.deviceId= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(deviceId));
        serviceDevices = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDevices.size() > 0) {
			return serviceDevices.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceDeviceStatusType> getServiceDeviceClassifiers() {
		List<ServiceDeviceStatusType> serviceDeviceStatusTypes = new ArrayList<ServiceDeviceStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceDeviceStatusType";
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
	public ServiceDeviceStatusType getServiceDeviceStatusTypeById(Integer status) {
		List<ServiceDeviceStatusType> serviceDeviceStatusType = new ArrayList<ServiceDeviceStatusType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceDeviceStatusType WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(status));
        serviceDeviceStatusType = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDeviceStatusType.size() > 0) {
			return serviceDeviceStatusType.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateServiceDevice(ServiceDevice serviceDevice) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(serviceDevice);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void saveServicePart(ServicePart servicePart) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(servicePart);
		session.getTransaction().commit();
		session.close();
	}
	@Override
	public void updateServicePart(ServicePart servicePart) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(servicePart);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServicePart getServicePartById(Integer servicePartId) {
		List<ServicePart> serviceParts = new ArrayList<ServicePart>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServicePart WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(servicePartId));
        serviceParts = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceParts.size() > 0) {
			return serviceParts.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteServicePart(ServicePart servicePart) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE ServicePart WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(servicePart.getId()));
		query.executeUpdate();
//		session.delete(serviceDevice);
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ServiceDevice getOrderServiceDeviceByServiceDeviceId(Integer deviceId) {
		List<ServiceDevice> serviceDevices = new ArrayList<ServiceDevice>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceDevice WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(deviceId));
        serviceDevices = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDevices.size() > 0) {
			return serviceDevices.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceType getServiceTypeById(Integer type) {
		List<ServiceType> serviceDevices = new ArrayList<ServiceType>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceType WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(type));
        serviceDevices = query.list();
        
		session.beginTransaction();
		session.close();
		if (serviceDevices.size() > 0) {
			return serviceDevices.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void saveServiceAction(ServiceAction serviceAction) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(serviceAction);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceAction getServiceActionById(Integer jobId) {
		List<ServiceAction> list = new ArrayList<ServiceAction>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM ServiceAction WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(jobId));
        list = query.list();
        
		session.beginTransaction();
		session.close();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateServiceAction(ServiceAction serviceAction) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(serviceAction);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteObject(ServiceAction serviceAction) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE ServiceAction WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(serviceAction.getId()));
		query.executeUpdate();
//		session.delete(serviceDevice);
		session.close();
	}

	@Override
	public String deleteOrder(ServiceOrder order) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "DELETE ServiceOrder WHERE id= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(order.getId()));
        try {
        	query.executeUpdate();
        } catch (HibernateException e) {
        	return "fail";
        }
		session.close();
		
		return "ok";
	}
}
