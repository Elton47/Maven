package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Department;
import project.util.HibernateUtil;

public class DepartmentDao extends DbOps<Department> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	private List<Department> departments = session.createQuery("from Department where Validity = 1", Department.class).getResultList();
	public List<Department> getDepartments() {
		return departments;
	}
	public boolean addDepartment(String code, String name, String budget) {
		Department department = new Department(code, name, Integer.parseInt(budget));
		boolean succeeded = save(department);
		if(succeeded)
			departments.add(department);
		return succeeded;
	}
	public boolean removeDepartment(Department department) {
		department.setValidity(0);
		boolean succeeded = update(department);
		if(succeeded)
			departments.remove(department);
		return succeeded;
	}
	public boolean restoreDepartment(Department department) {
		department.setValidity(1);
		boolean succeeded = update(department);
		if(succeeded)
			departments.add(department);
		return succeeded;
	}
	public boolean editDepartment(String code, String name, String budget, Department department) {
		Department backupDepartment = department; // In case editing fails.
		departments.remove(department); // Remove first.
		department.setCode(code);
		department.setName(name);
		department.setBudget(Integer.parseInt(budget));
		department.setValidity(1);
		boolean succeeded = update(department);
		departments.add(succeeded ? department : backupDepartment);
		return succeeded;
	}
}
// This way, the number of Database `SELECT` Queries is significantly decreased. - Elton47.