package ee.iapb61.idu0200.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ee.iapb61.idu0200.dao.impl.PersonDaoImpl;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.Person;
import ee.iapb61.idu0200.model.user.UserAccount;

public class PersonService {

	@Autowired
	private PersonDaoImpl personDao;
	
	public String getPerson() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return name;
	}

	public UserAccount getCurrentPerson() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		return personDao.getPersonByUserName(userName);
	}

	public Customer getCustomer(int id) {
		System.out.println("Kliendi id : " + id);
		Customer customer =  personDao.getCustomerById(id);
		System.out.println(customer.getId());
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		return personDao.getAllCustomers();
	}

	public Customer getCurrentCustomer(Person person) {
		return personDao.getCustomerById(person.getId());
	}
}
