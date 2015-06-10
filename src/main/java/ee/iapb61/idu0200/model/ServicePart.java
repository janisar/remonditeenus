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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ee.iapb61.idu0200.model.user.Person;

@Entity
@Table(name="service_part")
public class ServicePart {

	private int id;
	private ServiceOrder serviceOrder;
	private ServiceDevice serviceDevice;
	
	private String name;
	
	private String serialNumber;
	
	private Integer partCount;
	
	private Double partPrice;
	private Date created;
	private Person createdBy;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_part")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, optional=true)
	@JoinColumn(name = "service_order_fk")
	@NotFound(action=NotFoundAction.IGNORE)
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name = "service_device_fk")
	@NotFound(action=NotFoundAction.IGNORE)
	public ServiceDevice getServiceDevice() {
		return serviceDevice;
	}
	
	public void setServiceDevice(ServiceDevice serviceDevice) {
		this.serviceDevice = serviceDevice;
	}
	
	@Column(name="part_name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="serial_no")
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Column(name="part_count")
	public Integer getPartCount() {
		return partCount;
	}
	
	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}
	
	@Column(name="part_price")
	public Double getPartPrice() {
		return partPrice;
	}
	
	public void setPartPrice(Double partPrice) {
		this.partPrice = partPrice;
	}
	
	@Column(name="created")
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional=true)
	@JoinColumn(name="created_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public Person getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}
}
