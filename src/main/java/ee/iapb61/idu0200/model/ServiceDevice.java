package ee.iapb61.idu0200.model;

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

@Entity
@Table(name="service_device")
public class ServiceDevice {

	private int id;
	
	private Device device;
	
	private ServiceDeviceStatusType serviceDeviceStatusType;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_device")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="device_fk")
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="service_device_status_type_fk")
	public ServiceDeviceStatusType getServiceDeviceStatusType() {
		return serviceDeviceStatusType;
	}

	public void setServiceDeviceStatusType(
			ServiceDeviceStatusType serviceDeviceStatusType) {
		this.serviceDeviceStatusType = serviceDeviceStatusType;
	}
}
