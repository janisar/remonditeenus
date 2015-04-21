package ee.iapb61.idu0200.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.Person;

@Entity
@Table(name="service_request")
public class ServiceRequest {
	
	private int id;
	
	private Customer customer;
	
	private Person createdBy;
	
	private Date createdDate;
	
	private String serviceDescriptionByCustomer;
	
	private String serviceDescriptionByEmployee;
	
	
	private ServiceRequestStatusType serviceRequestStatusType;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_request")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="customer_fk")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="created_by")
	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="service_desc_by_customer")
	public String getServiceDescriptionByCustomer() {
		return serviceDescriptionByCustomer;
	}

	public void setServiceDescriptionByCustomer(String serviceDescriptionByCustomer) {
		this.serviceDescriptionByCustomer = serviceDescriptionByCustomer;
	}

	@Column(name="service_desc_by_employee")
	public String getServiceDescriptionByEmployee() {
		return serviceDescriptionByEmployee;
	}

	public void setServiceDescriptionByEmployee(String serviceDescriptionByEmployee) {
		this.serviceDescriptionByEmployee = serviceDescriptionByEmployee;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="service_request_status_type_fk")
	public ServiceRequestStatusType getServiceRequestStatusType() {
		return serviceRequestStatusType;
	}

	public void setServiceRequestStatusType(
			ServiceRequestStatusType serviceRequestStatusType) {
		this.serviceRequestStatusType = serviceRequestStatusType;
	}
}
