package project.dao;

import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import project.entity.Department;
import project.entity.Sector;
import project.util.HibernateUtil;

public class DepartmentDao extends DbOps<Department> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Department> getDepartments() {
		try {
			List<Department> departments = session.createQuery("from Department where Validity = 1", Department.class).getResultList();
			if(departments.size() > 0)
				return departments;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addDepartment(String code, String name, String budget) {
			writeToDb("insert into Department(Code, Name, Budget, Validity)"
				    + "values('" + code + "', '" + name + "', " + budget + ", 1)");
	}
	public void removeDepartment(String code) {
		List<Department> departments = session.createQuery("from Department where Code = '" + code + "'", Department.class).getResultList();
		if(departments.size() > 0) {
			writeToDb("update User set Validity = 0 where Department_ID = " + departments.get(0).getId()); // Removing Department's User/s.
			List<Sector> sectors = session.createQuery("from Sector where Department_ID = " + departments.get(0).getId(), Sector.class).getResultList();
			if(sectors.size() > 0) // Removing Sectors of this Department.
				new SectorDao().removeSector(sectors.get(0).getCode());
			writeToDb("update Department set Validity = 0 where ID = " + departments.get(0).getId()); // Finally Removing Department.
		}
	}
}
