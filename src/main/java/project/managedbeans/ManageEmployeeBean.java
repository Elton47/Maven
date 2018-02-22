package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.EmployeeDao;
import project.entity.Employee;

@ViewScoped
@ManagedBean(name = "manageEmployeeBean")
public class ManageEmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final EmployeeDao employeeDao = new EmployeeDao();
	private Employee employee = new Employee();
	private List<Employee> employees;
	@PostConstruct
	public void init() {
		employees = employeeDao.getEmployees();
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
	public void addEmployee(String ssn, String fullName, String cellNo, String wage, String country, String sector) {
		if(ssn.length() > 0 && fullName.length() > 0 && cellNo.length() > 0 && wage.length() > 0 && country.length() > 0 && sector.length() > 0) {
			employeeDao.addEmployee(ssn, fullName, cellNo, wage, country, sector);
			employees = employeeDao.getEmployees(); // Refresh.
		}
	}
	public void removeEmployee(String ssn) {
		employeeDao.removeEmployee(ssn);
		employees = employeeDao.getEmployees(); // Refresh.
	}
}
