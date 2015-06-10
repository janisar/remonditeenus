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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ee.iapb61.idu0200.model.user.Person;

@Entity
@Table(name="service_action")
public class ServiceAction {
	
	private int id;
	private ServiceActionStatusType serviceActionStatusType;
	private ServiceType serviceType;
	private ServiceDevice serviceDevice;
	private ServiceOrder serviceOrder;
	private Integer amount;
	private Double price;
	private Date priceUpdatedDate;
	private String description;
	private Date created;
	private Person createdBy;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_action")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="service_action_status_type_fk")
	@JsonIgnore
	public ServiceActionStatusType getServiceActionStatusType() {
		return serviceActionStatusType;
	}
	
	public void setServiceActionStatusType(
			ServiceActionStatusType serviceActionStatusType) {
		this.serviceActionStatusType = serviceActionStatusType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="service_type_fk")
	@JsonIgnore
	public ServiceType getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "service_device_fk")
	@NotFound(action=NotFoundAction.IGNORE)
	public ServiceDevice getServiceDevice() {
		return serviceDevice;
	}
	
	public void setServiceDevice(ServiceDevice serviceDevice) {
		this.serviceDevice = serviceDevice;
	}
	
	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "service_order_fk")
	@NotFound(action=NotFoundAction.IGNORE)
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Column(name="service_amount")
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="price_updated")
	public Date getPriceUpdatedDate() {
		return priceUpdatedDate;
	}
	
	public void setPriceUpdatedDate(Date priceUpdatedDate) {
		this.priceUpdatedDate = priceUpdatedDate;
	}
	
	@Column(name="action_description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="created")
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="created_by")
	public Person getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}
}
