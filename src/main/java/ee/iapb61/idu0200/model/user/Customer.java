package ee.iapb61.idu0200.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {


	private Person subject;
	
	private SubjectType subjectType;
	
	private int id;
	
	@ManyToOne
	@JoinColumn(name="subject_type_fk")
	public SubjectType getSubjectType() {
		return subjectType;
	}
	
	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}
	
	@OneToOne
	@JoinColumn(name = "subject_fk")
	public Person getSubject() {
		return subject;
	}
	
	public void setSubject(Person subject) {
		this.subject = subject;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer", nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
