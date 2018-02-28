package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.EmployeeDao;
import project.entity.Employee;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean(name = "manageEmployeeBean")
public class ManageEmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final EmployeeDao employeeDao = new EmployeeDao();
	private Employee employee = new Employee();
	private List<Employee> employees;
	private String ssn, fullName, cellNo, wage, country, sector;
	@PostConstruct
	public void init() {
		employees = employeeDao.getEmployees();
		ssn = null;
		fullName = null;
		cellNo = null;
		wage = null;
		country = null;
		sector = null;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void addEmployee() {
		employeeDao.addEmployee(ssn, fullName, cellNo, wage, country, sector);
		init(); // Refresh.
	}
	public void removeEmployee(String ssn) {
		employeeDao.removeEmployee(ssn);
		init(); // Refresh.
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
	public String getWage() {
		return wage;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
}
