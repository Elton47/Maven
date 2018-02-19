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
}
