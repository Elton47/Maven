package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
		Employee employee = session.createNativeQuery("select * from Employee where `Full Name` = '" + employeeFullName + "'", Employee.class).getSingleResult();
		if(employee == null)
			employee = new Employee();
		return save(new Sector(code, name, session.createNativeQuery("select * from Department where Name = '" + departmentName + "'", Department.class).getSingleResult(), employee));
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
		sector.setDepartment(session.createNativeQuery("select * from Department where Name = '" + departmentName + "'", Department.class).getSingleResult());
		Employee employee = session.createNativeQuery("select * from Employee where `Full Name` = '" + employeeFullName + "'", Employee.class).getSingleResult();
		if(employee == null)
			employee = new Employee(); // Selected None for manager.
		sector.setEmployee(employee);
		sector.setValidity(1);
		return update(sector);
	}
}
