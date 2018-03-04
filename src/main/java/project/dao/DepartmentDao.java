package project.dao;

import java.util.List;
import project.entity.Department;

public class DepartmentDao extends DbOps<Department> {
	private List<Department> departments = setDepartments(); // set their `editable` to false.
	private List<Department> setDepartments() {
	 	List<Department> departments = session.createQuery("from Department where Validity = 1", Department.class).getResultList();
		for(Department department : departments)
			if(department.isEditable())
				department.setEditable(false);
		return departments;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public boolean addDepartment(Department department) {
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
	public boolean editDepartment(Department department) {
		Department backupDepartment = department; // In case editing fails.
		departments.remove(department); // Remove first.
		boolean succeeded = update(department);
		departments.add(succeeded ? department : backupDepartment);
		return succeeded;
	}
}
// This way, the number of Database `SELECT` Queries is significantly decreased. - Elton47.