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
	private Department department = new Department(), departmentToEditOrRestore;
	private List<Department> departments;
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
		if(departmentDao.addDepartment(code, name, budget))
			init(); // Refresh.
		code = null;
		name = null;
		budget = null;
	}
	public void removeDepartment(Department department) {
		if(department != null) {
			departmentToEditOrRestore = department;
			if(departmentDao.removeDepartment(department))
				init();
		}
	}
	public void restoreDepartment() {
		if(departmentToEditOrRestore != null) {
			if(departmentDao.restoreDepartment(departmentToEditOrRestore))
				init();
			departmentToEditOrRestore = null;
		}
	}
	public void editDepartment() {
		if(departmentToEditOrRestore != null) {
			if(departmentDao.editDepartment(code, name, budget, departmentToEditOrRestore))
				init();
			code = null;
			name = null;
			budget = null;
			departmentToEditOrRestore = null;
		}
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
	public Department getDepartmentToEditOrRestore() {
		return departmentToEditOrRestore;
	}
	public void setDepartmentToEditOrRestore(Department departmentToEditOrRestore) {
		this.departmentToEditOrRestore = departmentToEditOrRestore;
	}
}