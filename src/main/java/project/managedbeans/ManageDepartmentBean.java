package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.DepartmentDao;
import project.entity.Department;

@RequestScoped
@ManagedBean(name = "manageDepartmentBean")
public class ManageDepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final DepartmentDao departmentDao = new DepartmentDao();
	private Department department = new Department();
	private List<Department> departments;
	private String code, name, budget, codeToEdit;
	@PostConstruct
	public void init() {
		departments = departmentDao.getDepartments();
		code = null;
		name = null;
		budget = null;
		codeToEdit = null;
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
	public boolean addDepartment() {
		boolean isAdded = departmentDao.addDepartment(code, name, budget);
		init(); // Refresh.
		return isAdded;
	}
	public boolean removeDepartment(String code) {
		boolean isRemoved = departmentDao.removeDepartment(code);
		init(); // Refresh.
		return isRemoved;
	}
	public boolean editDepartment() {
		boolean isEdited = departmentDao.editDepartment(code, name, budget, codeToEdit);
		init(); // Refresh.
		return isEdited;
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
	public String getCodeToEdit() {
		return codeToEdit;
	}
	public void setCodeToEdit(String codeToEdit) {
		this.codeToEdit = codeToEdit;
	}
}