package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Department;
import project.entity.Sector;
import project.util.HibernateUtil;

public class DepartmentDao extends DbOps<Department> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Department> getDepartments() {
		return session.createQuery("from Department where Validity = 1 order by ID DESC", Department.class).getResultList();
	}
	public boolean addDepartment(String code, String name, String budget) {
		return save(new Department(code, name, Integer.parseInt(budget)));
	}
	public boolean removeDepartment(Department department) {
		/*List<Department> departments = session.createQuery("from Department where Code = '" + codeToDelete + "'", Department.class).getResultList();
		if(!departments.isEmpty()) {
			writeToDb("update User set Validity = 0 where Department_ID = " + departments.get(0).getId()); // Removing Department's User/s.
			List<Sector> sectors = session.createQuery("from Sector where Department_ID = " + departments.get(0).getId(), Sector.class).getResultList();
			if(sectors.size() > 0) // Removing Sectors of this Department.
				new SectorDao().removeSector(sectors.get(0).getCode());
			writeToDb("update Department set Validity = 0 where ID = " + departments.get(0).getId()); // Finally Removing Department.
			return true;
		}
		return false;*/
		//List<Department> departments = session.createQuery("from Department where Code = '" + codeToDelete + "'", Department.class).getResultList();
		department.setValidity(0);
		return update(department);
	}
	public boolean restoreDepartment(Department department) {
		/*List<Department> departments = session.createQuery("from Department where Code = '" + codeToRestore + "'", Department.class).getResultList();
		if(!departments.isEmpty()) {
			writeToDb("update User set Validity = 1 where Department_ID = " + departments.get(0).getId()); // Restoring Department's Users..
			List<Sector> sectors = session.createQuery("from Sector where Department_ID = " + departments.get(0).getId(), Sector.class).getResultList();
			if(!sectors.isEmpty()) // Restoring Sectors of this Department.
				new SectorDao().restoreSector(sectors.get(0).getCode());
			writeToDb("update Department set Validity = 1 where ID = " + departments.get(0).getId()); // Restoring Department.
		}
		return !session.createQuery("from Department where Code = '" + codeToRestore + "'", Department.class).getResultList().isEmpty();
		*/
		
		department.setValidity(1);
		return update(department);
	}
	public boolean editDepartment(String code, String name, String budget, Department department) {
		/*List<Department> departments = getDepartmentByCode(codeToEdit);
		Department department = (!departments.isEmpty()) ? departments.get(0) : null; 
		if(department != null) {
			department.setCode(code);
			department.setName(name);
			department.setBudget(budget);
			return update(department);
		}
		return false;*/
		department.setCode(code);
		department.setName(name);
		department.setBudget(Integer.parseInt(budget));
		department.setValidity(1);
		return update(department);
	}
}
