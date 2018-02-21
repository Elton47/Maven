package project.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Department;
import project.entity.Sector;
import project.util.HibernateUtil;

public class DepartmentDao extends DbOps<Department> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Department> getDepartments() {
		try {
			TypedQuery<Department> query = session.createQuery("from Department where Validity = 1", Department.class);
			List<Department> departments = query.getResultList();
			return departments;
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
		if(!departments.isEmpty() || departments != null) {
			writeToDb("update Department set Validity = 0 where ID = " + departments.get(0).getId());
			writeToDb("update User set Validity = 0 where Department_ID = " + departments.get(0).getId()); // Removing Department's User/s.
			List<Sector> sectors = session.createQuery("from Sector where Department_ID = " + departments.get(0).getId(), Sector.class).getResultList();
			if(!sectors.isEmpty() || sectors != null) // Removing Sectors of this Department.
				new SectorDao().removeSector(sectors.get(0).getCode());
		}
	}
}
