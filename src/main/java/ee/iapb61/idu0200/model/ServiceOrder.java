package ee.iapb61.idu0200.model;

import java.math.BigDecimal;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	private BigDecimal totalPrice;
	
	private String note;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_order")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="so_status_type_fk")
	public ServiceOrderStatusType getServiceOrderStatusType() {
		return serviceOrderStatusType;
	}

	public void setServiceOrderStatusType(
			ServiceOrderStatusType serviceOrderStatusType) {
		this.serviceOrderStatusType = serviceOrderStatusType;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="created_by")
	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	@OneToOne
	@JoinColumn(name="service_request_fk")
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="updated_by")
	public Person getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Person updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="created")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="updated")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name="status_changed")
	public Date getStatusChangedDate() {
		return statusChangedDate;
	}

	public void setStatusChangedDate(Date statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="status_changed_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public Person getStatusChangedBy() {
		return statusChangedBy;
	}

	public void setStatusChangedBy(Person statusChangedBy) {
		this.statusChangedBy = statusChangedBy;
	}

	@Column(name="price_total")
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="service_note", joinColumns = {@JoinColumn(name="service_order_fk")})
	public List<ServiceDevice> getServiceDevices() {
		if (this.serviceDevices == null) {
			this.serviceDevices = new ArrayList<ServiceDevice>();
		}
		return serviceDevices;
	}
	
	public void setServiceDevices(List<ServiceDevice> serviceDevices) {
		this.serviceDevices = serviceDevices;
	}
}
