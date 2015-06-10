package ee.iapb61.idu0200.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.iapb61.idu0200.dao.DaoConstants;
import ee.iapb61.idu0200.dao.DeviceDao;
import ee.iapb61.idu0200.dao.HibernateUtil;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.ServiceDevice;

public class DeviceDaoImpl implements DeviceDao, DaoConstants {

	private SessionFactory sessionFactory;
	
	public DeviceDaoImpl() {
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
	public List<Device> list() {
		List<Device> devices = new ArrayList<Device>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		devices = session.createQuery("from Device as d").list();
		
		session.close();
		if (devices.size() > 0) {
			return devices;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Device findById(String id) {
		List<Device> devices = new ArrayList<Device>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Device WHERE device= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", Integer.valueOf(id));
        devices = query.list();
        
		session.beginTransaction();
		session.close();
 
		if (devices.size() > 0) {
			return devices.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DeviceType> deviceTypelist() {
		List<DeviceType> deviceTypes = new ArrayList<DeviceType>();
		
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.clear();
		session.beginTransaction();
		deviceTypes = session.createQuery("from DeviceType as d").list();
		
		session.close();
		if (deviceTypes.size() > 0) {
			return deviceTypes;
		} else {
			return null;
		}
	}

	@Override
	public int saveDevice(Device device) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(device);
		session.getTransaction().commit();
		int id = device.getDeviceId();
		session.close();
		
		return id;
	}

	@Override
	public void saveServiceDevice(ServiceDevice sDevice) {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(sDevice);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Device> searchDevices(String queryString) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery(queryString);
		
		@SuppressWarnings("unchecked")
		List<Device> devices = query.list();
		session.close();
		
		return devices;
	}
	
}
