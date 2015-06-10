package ee.iapb61.idu0200.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="device_type")
public class DeviceType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5171387248020534908L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "super_type_fk")
	@NotFound(action = NotFoundAction.IGNORE)
	private DeviceType superDevice;
	
	@Column(name="type_name")
	private String name;
	
	@Column(name="level")
	private int level;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="device_type")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "super_type_fk")
	@NotFound(action = NotFoundAction.IGNORE)
	public DeviceType getSuperDevice() {
		return superDevice;
	}
	
	public void setSuperDevice(DeviceType superDevice) {
		this.superDevice = superDevice;
	}
	
	@Column(name="type_name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="level")
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="device_type")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    public List<DeviceType> subFolders = new ArrayList<DeviceType>();
	
	@OneToMany(mappedBy="superDevice",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<DeviceType> getSubFolders() {
		return subFolders;
	}
	
	public void setSubFolders(List<DeviceType> subFolders) {
		this.subFolders = subFolders;
	}
}
