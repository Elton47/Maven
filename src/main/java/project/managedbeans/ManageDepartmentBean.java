package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.DepartmentDao;
import project.entity.Department;

@ViewScoped
@ManagedBean(name = "manageDepartmentBean")
public class ManageDepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final DepartmentDao departmentDao = new DepartmentDao();
	private Department department = new Department();
	private List<Department> departments;
	private boolean adding = false;
	private String code, name, budget;
	@PostConstruct
	public void init() {
		departments = departmentDao.getDepartments();
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void addDepartment() {
		if(!adding) // Add clicked.
			adding = true;
		else if(adding) { // Done clicked.
			adding = false;
			if(code != null && name != null && budget != null)
				departmentDao.addDepartment(code, name, budget);
		}
	}
	public void removeDepartment() {
		departmentDao.removeDepartment(code);
	}
	public void setAdding(boolean adding) {
		this.adding = adding;
	}
	public boolean getAdding() {
		return adding;
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
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
}