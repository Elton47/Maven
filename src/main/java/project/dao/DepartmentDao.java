package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Department;
import project.util.HibernateUtil;

public class DepartmentDao extends DbOps<Department> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Department> getDepartments() {
		return session.createQuery("from Department where Validity = 1", Department.class).getResultList();
	}
	public boolean addDepartment(String code, String name, String budget) {
		return (!code.isEmpty() && !name.isEmpty() && !budget.isEmpty()) ? save(new Department(code, name, Integer.parseInt(budget))) : false;
	}
	public boolean removeDepartment(Department department) {
		department.setValidity(0);
		return update(department);
	}
	public boolean restoreDepartment(Department department) {
		department.setValidity(1);
		return update(department);
	}
	public boolean editDepartment(String code, String name, String budget, Department department) {
		department.setCode(code);
		department.setName(name);
		department.setBudget(Integer.parseInt(budget));
		department.setValidity(1);
		return update(department);
	}
}
