package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.DepartmentDao;
import project.entity.Department;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageDepartmentBean")
public class ManageDepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final DepartmentDao departmentDao = new DepartmentDao();
	private Department departmentToEditOrRestore;
	private String code, name, budget;
	public void resetInputFields() {
		code = null;
		name = null;
		budget = null;
	}
	public List<Department> getDepartments() {
		return departmentDao.getDepartments();
	}
	public void addDepartment() {
		departmentDao.addDepartment(code, name, budget);
		resetInputFields();
	}
	public void removeDepartment(Department department) {
		if(department != null) {
			departmentToEditOrRestore = department;
			departmentDao.removeDepartment(department);
		}
	}
	public void restoreDepartment() {
		if(departmentToEditOrRestore != null) {
			departmentDao.restoreDepartment(departmentToEditOrRestore);
			departmentToEditOrRestore = null;
		}
	}
	public void editDepartment() {
		if(departmentToEditOrRestore != null)
			departmentDao.editDepartment(code, name, budget, departmentToEditOrRestore);
		resetInputFields();
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