package project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

@Entity
@Table
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@NaturalId(mutable = true)
	@Column(name = "SSN")
	private String ssn;
	@Column(name = "`Full Name`")
	private String fullName;
	@Column(name = "CellNo")
	private String cellNo;
	@Column(name = "Wage")
	private int wage;
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;
	@ManyToOne(fetch = FetchType.LAZY)
	private Sector sector;
	@Column(name = "Validity")
	private int validity;
	@Transient
	private boolean editable;
	public Employee() {validity = 1;}
	public Employee(String ssn, String fullName, String cellNo, int wage, Country country, Sector sector) {
		this.ssn = ssn;
		this.fullName = fullName;
		this.cellNo = cellNo;
		this.wage = wage;
		this.country = country;
		this.sector = sector;
		validity = 1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
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
