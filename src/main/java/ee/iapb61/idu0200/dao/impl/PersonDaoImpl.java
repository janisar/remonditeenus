package ee.iapb61.idu0200.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.iapb61.idu0200.dao.DaoConstants;
import ee.iapb61.idu0200.dao.HibernateUtil;
import ee.iapb61.idu0200.dao.Persondao;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.UserAccount;

public class PersonDaoImpl implements Persondao, DaoConstants {

	private SessionFactory sessionFactory;
	
	public PersonDaoImpl() {
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
	public UserAccount getPersonByUserName(String userName) {
		List<UserAccount> persons = new ArrayList<UserAccount>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM UserAccount WHERE userName= :username";
        Query query = session.createQuery(hql);
        query.setString("username", userName);
        session.beginTransaction();
        
		persons = query.list();
		
		session.close();
 
		if (persons.size() > 0) {
			System.out.println("USER:::" + persons.get(0).getUserName());
			return persons.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Customer getCustomerById(int id) {
		List<Customer> persons = new ArrayList<Customer>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Customer WHERE subject= :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        session.beginTransaction();
        
		persons = query.list();
		session.close();
 
		if (persons.size() > 0) {
			return persons.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		List<Customer> persons = new ArrayList<Customer>();
		 
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		Session session = sessionFactory.openSession();
		
		persons = session.createQuery("FROM Customer as c").list();
		
		session.close();
 
		if (persons.size() > 0) {
			return persons;
		} else {
			return null;
		}
	}

	public Customer getCurrentCustomer(int id) {
		return null;
	}
}
