package ee.iapb61.idu0200.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_device_status_type")
public class ServiceDeviceStatusType {

	private int id;
	
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_device_status_type")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="type_name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
