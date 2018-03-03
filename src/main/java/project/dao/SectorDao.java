package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import project.entity.Department;
import project.entity.Employee;
import project.entity.Sector;
import project.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class SectorDao extends DbOps<Sector> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	private List<Sector> sectors = session.createQuery("from Sector where Validity = 1", Sector.class).getResultList();
	public List<Sector> getSectors() {
		return sectors;
	}
	public boolean addSector(String code, String name, String departmentName, String employeeFullName) {
		Sector sector = new Sector(code, name, session.createQuery("from Department where Name = ?1", Department.class).setParameter(1, departmentName).getSingleResult(), 
				employeeFullName == null || employeeFullName.equals("") ? null : (Employee) session.createCriteria(Employee.class, "e").add(Restrictions.eq("fullName", employeeFullName)).list().get(0));
		boolean succeeded = save(sector);
		if(succeeded)
			sectors.add(sector);
		return succeeded;
	}
	public boolean removeSector(Sector sector) {
		sector.setValidity(0);
		boolean succeeded = update(sector);
		if(succeeded)
			sectors.remove(sector);
		return succeeded;
	}
	public boolean restoreSector(Sector sector) {
		sector.setValidity(1);
		boolean succeeded = update(sector);
		if(succeeded)
			sectors.add(sector);
		return succeeded;
	}
	public boolean editSector(String code, String name, String departmentName, String employeeFullName, Sector sector) {
		Sector backupSector = sector; // In case editing fails.
		sectors.remove(sector); // Remove first.
		sector.setCode(code);
		sector.setName(name);
		sector.setDepartment(session.createQuery("from Department where Name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		sector.setEmployee(employeeFullName == null || employeeFullName.equals("") ? null : (Employee) session.createCriteria(Employee.class, "e").add(Restrictions.eq("fullName", employeeFullName)).list().get(0));
		sector.setValidity(1);
		boolean succeeded = update(sector);
		sectors.add(succeeded ? sector : backupSector);
		return succeeded;
	}
}
// This way, the number of Database `SELECT` Queries is significantly decreased. - Elton47.