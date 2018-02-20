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
}
