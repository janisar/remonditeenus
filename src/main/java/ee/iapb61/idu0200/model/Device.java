package ee.iapb61.idu0200.model;

import java.io.Serializable;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="device")
public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5957791844738014993L;
	
	@Id
	private int deviceId;
	
	@NotNull(message="Nimi ei tohi olla tühi")
	@Pattern(regexp="[A-Za-z]{2,12}$", message="Nimi koosneb tähtedest ning on maksimaalselt 12 tähemärki")
	private String name;
	private DeviceType deviceType;
	
	@NotNull(message="Registreerimisnumber ei tohi olla tühi")
	@Pattern(regexp="[A-Za-z0-9\\s]{2,6}$", message="Registreerimisnumber peab olema 2 kuni 6 tähemärki pikk")
	private String registrationNumber;
	
	@NotNull(message="Kirjeldus ei tohi olla tühi")
	@Pattern(regexp="[A-Za-z0-9\\s].*{2,100}$", message="Kirjeldus peab olema 2 kuni 100 tähemärki pikk")
	private String description;
	
	@NotNull(message="mudel ei tohi olla tühi")
	@Pattern(regexp="[A-Za-z0-9\\s]{2,6}$", message="Mudel peab olema 2 kuni 6 tähemärki pikk")
	private String model;
	
	@NotNull(message="Tootja ei tohi olla tühi")
	@Pattern(regexp="[A-Za-z0-9\\s]{2,12}$", message="Tootja peab olema 2 kuni 12 tähemärki pikk")
	private String manufacturer;
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="device", nullable=false)
	public int getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name="reg_no")
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="model")
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name="manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="device_type_fk")
	public DeviceType getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	
}
