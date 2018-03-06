package project.dao;

import java.util.List;
import project.entity.Department;
import project.entity.Employee;
import project.entity.Sector;

public class SectorDao extends DbOps<Sector> {
	public List<Sector> getSectors() {
		return session.createQuery("from Sector where validity = 1 order by id desc", Sector.class).getResultList();
	}
	public List<Sector> getSectors(Sector sector) {
		if(!sector.getEmployee().getFullName().equals(""))
			return session.createQuery("from Sector where validity = 1 and code like ?1 and name like ?2 and department.name like ?3 and employee.fullName like ?4", Sector.class).setParameter(1, sector.getCode() + "%").setParameter(2, sector.getName() + "%").setParameter(3, sector.getDepartment().getName() + "%").setParameter(4, sector.getEmployee().getFullName() + "%").getResultList();
		return session.createQuery("from Sector where validity = 1 and code like ?1 and name like ?2 and department.name like ?3", Sector.class).setParameter(1, sector.getCode() + "%").setParameter(2, sector.getName() + "%").setParameter(3, sector.getDepartment().getName() + "%").getResultList();
	}
	public boolean addSector(Sector sector, String departmentName, String employeeFullName) {
		sector.setDepartment(session.createQuery("from Department where name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		if(employeeFullName != null) {
			if(!employeeFullName.equals(""))
				sector.setEmployee(session.createQuery("from Employee where fullName = ?1", Employee.class).setParameter(1, employeeFullName).getSingleResult());
		}
		else
			sector.setEmployee(null);
		return save(sector);
	}
	public boolean removeSector(Sector sector) {
		sector.setValidity(0);
		return update(sector);
	}
	public boolean restoreSector(Sector sector) {
		sector.setValidity(1);
		return update(sector);
	}
	public boolean editSector(Sector sector, String departmentName, String employeeFullName) {
		Sector backupSector = sector; // In case editing fails.
		sector.setDepartment(session.createQuery("from Department where name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		sector.setEmployee(employeeFullName != null && !employeeFullName.equals("") ? session.createQuery("from Employee where fullName = ?1", Employee.class).setParameter(1, employeeFullName).getSingleResult() : null);
		boolean succeeded = update(sector);
		if(!succeeded)
			update(backupSector);
		return succeeded;
	}
}