package project.dao;

import java.util.List;
import project.entity.Department;

public class DepartmentDao extends DbOps<Department> {
	public List<Department> getDepartments() {
		return session.createQuery("from Department where validity = 1", Department.class).getResultList();
	}
	public List<Department> getDepartments(Department department) {
		String code = department.getCode() != null ? department.getCode() : "";
		String name = department.getName() != null ? department.getName() : "";
		int budget = department.getBudget();
		if(code.length() == 3)
			code = code.substring(0, 2);
		else if(code.length() == 2)
			code = code.substring(0, 1);
		else if(code.length() == 1)
			code = code.substring(0, 1);
		if(name.length() == 3)
			name = name.substring(0, 2);
		else if(name.length() == 2)
			name = name.substring(0, 1);
		else if(name.length() == 1)
			name = name.substring(0, 1);
		if(budget != 0)
			return session.createQuery("from Department where validity = 1 and code like ?1 and name like ?2 and budget like ?3", Department.class).setParameter(1, "%" + code + "%").setParameter(2, "%" + name + "%").setParameter(3, budget).getResultList();
		else
			return session.createQuery("from Department where validity = 1 and code like ?1 and name like ?2", Department.class).setParameter(1, "%" + code + "%").setParameter(2, "%" + name + "%").getResultList();
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