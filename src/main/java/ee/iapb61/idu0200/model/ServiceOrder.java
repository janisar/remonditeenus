package ee.iapb61.idu0200.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ee.iapb61.idu0200.model.user.Person;

@Entity
@Table(name="service_order")
public class ServiceOrder {

	private int id;
	
	private ServiceOrderStatusType serviceOrderStatusType;
	
	private Person createdBy;
	
	private ServiceRequest serviceRequest;
	
	private Person updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private Date statusChangedDate;
	
	private Person statusChangedBy;
	
	private List<ServiceDevice> serviceDevices;
	
	private List<ServicePart> serviceParts;
	
	private List<ServiceAction> serviceAction;
	
	private Double totalPrice;
	
	private String note;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_order")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="so_status_type_fk", nullable=true)
	public ServiceOrderStatusType getServiceOrderStatusType() {
		return serviceOrderStatusType;
	}

	public void setServiceOrderStatusType(
			ServiceOrderStatusType serviceOrderStatusType) {
		this.serviceOrderStatusType = serviceOrderStatusType;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="created_by")
	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	@OneToOne(optional=true)
	@JoinColumn(name="service_request_fk", unique=true)
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=true)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="updated_by")
	public Person getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Person updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="created",nullable=true)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="updated", nullable=true)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name="status_changed", nullable=true)
	public Date getStatusChangedDate() {
		return statusChangedDate;
	}

	public void setStatusChangedDate(Date statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=true)
	@JoinColumn(name="status_changed_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public Person getStatusChangedBy() {
		return statusChangedBy;
	}

	public void setStatusChangedBy(Person statusChangedBy) {
		this.statusChangedBy = statusChangedBy;
	}

	@Column(name="price_total", nullable=true)
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name="note", nullable=true)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="serviceOrder")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ServiceDevice> getServiceDevices() {
		if (this.serviceDevices == null) {
			this.serviceDevices = new ArrayList<ServiceDevice>();
		}
		return serviceDevices;
	}
	
	public void setServiceDevices(List<ServiceDevice> serviceDevices) {
		this.serviceDevices = serviceDevices;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="serviceOrder")
	@LazyCollection(LazyCollectionOption.FALSE)
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ServicePart> getServiceParts() {
		return serviceParts;
	}
	
	public void setServiceParts(List<ServicePart> serviceParts) {
		this.serviceParts = serviceParts;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="serviceOrder")
	@LazyCollection(LazyCollectionOption.FALSE)
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ServiceAction> getServiceAction() {
		return serviceAction;
	}
	
	public void setServiceAction(List<ServiceAction> serviceAction) {
		this.serviceAction = serviceAction;
	}
}
