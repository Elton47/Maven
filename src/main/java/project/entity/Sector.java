package project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "Sector")
public class Sector implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@NaturalId(mutable = true)
	@Column(name = "Code")
	private String code;
	@Column(name = "Name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;
	@OneToOne(fetch = FetchType.LAZY)
	private Employee employee;
	@Column(name = "Validity")
	private int validity;
	@Transient
	private boolean editable;
	public Sector () {validity = 1;}
	public Sector(String code, String name, Department department, Employee employee) {
		this.code = code;
		this.name = name;
		this.department = department;
		this.employee = employee;
		validity = 1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
