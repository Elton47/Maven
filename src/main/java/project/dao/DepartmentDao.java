package project.dao;

import java.util.List;
import project.entity.Department;

public class DepartmentDao extends DbOps<Department> {
	public List<Department> getDepartments() {
		return session.createQuery("from Department where validity = 1 order by id desc", Department.class).getResultList();
	}
	public List<Department> getDepartments(Department department) {
		if(department.getBudget() != 0)
			return session.createQuery("from Department where validity = 1 and code like ?1 and name like ?2 and budget like ?3", Department.class).setParameter(1, department.getCode() + "%").setParameter(2, department.getName() + "%").setParameter(3, department.getBudget()).getResultList();
		else
			return session.createQuery("from Department where validity = 1 and code like ?1 and name like ?2", Department.class).setParameter(1, department.getCode() + "%").setParameter(2, department.getName() + "%").getResultList();
	}
	public boolean addDepartment(Department department) {
		return save(department);
	}
	public boolean removeDepartment(Department department) {
		department.setValidity(0);
		return update(department);
	}
	public boolean restoreDepartment(Department department) {
		department.setValidity(1);
		return update(department);
	}
	public boolean editDepartment(Department department) {
		Department backupDepartment = department; // In case editing fails.
		boolean succeeded = update(department);
		if(!succeeded)
			update(backupDepartment);
		return succeeded;
	}
}