package ee.iapb61.idu0200.model.user;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="person")
public class Person {

	private String firstName;
	private String lastName;
	private String identityCode;
	private Date birthDate;
	private Person createdBy;
	private Person updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private int id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="person")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="identity_code")
	public String getIdentityCode() {
		return identityCode;
	}
	
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	
	@ManyToOne
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
}
