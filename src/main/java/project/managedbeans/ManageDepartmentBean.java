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
	private String code, name, budget;
	@PostConstruct
	public void init() {
		departments = departmentDao.getDepartments();
		code = null;
		name = null;
		budget = null;
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
		if(code != null && name != null && budget != null)
			SessionScopedValuesBean.setSucceeded(departmentDao.addDepartment(code, name, budget));
		else
			SessionScopedValuesBean.setSucceeded(false);
		init(); // Refresh.
	}
	public void removeDepartment(String code) {
		SessionScopedValuesBean.setCodeToRestoreRecord(code);
		SessionScopedValuesBean.setSucceeded(departmentDao.removeDepartment(code));
		init(); // Refresh.
	}
	public void restoreDepartment() {
		SessionScopedValuesBean.setSucceeded(departmentDao.restoreDepartment(SessionScopedValuesBean.getCodeToRestoreRecord()));
		SessionScopedValuesBean.setCodeToRestoreRecord("");
		init(); // Refresh.
	}
	public void editDepartment() {
		if(code != null && name != null && budget != null)
			SessionScopedValuesBean.setSucceeded(departmentDao.editDepartment(code, name, budget, SessionScopedValuesBean.getCodeToEditRecord()));
		else
			SessionScopedValuesBean.setSucceeded(false);
		SessionScopedValuesBean.setCodeToEditRecord("");
		init(); // Refresh.
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