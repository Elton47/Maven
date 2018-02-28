package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Department;
import project.entity.Employee;
import project.entity.Sector;
import project.util.HibernateUtil;

public class SectorDao extends DbOps<Sector> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Sector> getSectors() {
		return session.createQuery("from Sector where Validity = 1 order by ID DESC", Sector.class).getResultList();
	}
	public boolean addSector(String code, String name, String departmentName, String employeeFullName) {
		Employee employee;
		if(employeeFullName.equals(""))
			employee = new Employee();
		else
			employee = session.createNativeQuery("select * from Employee where `Full Name` = '" + employeeFullName + "'", Employee.class).getSingleResult();
		return save(new Sector(code, name, session.createQuery("from Department where Name = ?1", Department.class).setParameter(1, departmentName).getSingleResult(), employee));
	}
	public boolean removeSector(Sector sector) {
		sector.setValidity(0);
		return update(sector);
	}
	public boolean restoreSector(Sector sector) {
		sector.setValidity(1);
		return update(sector);
	}
	public boolean editSector(String code, String name, String departmentName, String employeeFullName, Sector sector) {
		sector.setCode(code);
		sector.setName(name);
		sector.setDepartment(session.createQuery("from Department where Name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		if(employeeFullName.equals(""))
			sector.setEmployee(null);
		else
			sector.setEmployee(session.createNativeQuery("select * from Employee where `Full Name` = '" + employeeFullName + "'", Employee.class).getSingleResult());
		sector.setValidity(1);
		return update(sector);
	}
}
