package ee.iapb61.idu0200.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_request_status_type")
public class ServiceRequestStatusType {

	private int id;
	
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_request_status_type")
	public int getId() {
		return id;
	}
	
	@Column(name="type_name")
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
