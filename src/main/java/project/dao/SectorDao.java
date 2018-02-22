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
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Sector> getSectors() {
		try {
			List<Sector> sectors = session.createQuery("from Sector where Validity = 1", Sector.class).getResultList();
			if(sectors.size() > 0)
				return sectors;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addSector(String code, String name, String department, String employee) {
		if(employee.length() > 0) {
			List<Department> departments = session.createQuery("from Department where Name = '" + department + "'", Department.class).getResultList();
			List<Employee> employees = session.createQuery("from Employees where `Full Name` = '" + employee + "'", Employee.class).getResultList();
			if((!departments.isEmpty() || departments != null) && (!employees.isEmpty() || employees != null))
				writeToDb("insert into Sector(Code, Name, Department_ID, Employee_ID, Validity)"
						+ "values('" + code + "', '" + name + "', " + departments.get(0).getId() + ", " + employees.get(0).getId() + ", 1)");
		}
		else { // Adding Sector without a Manager (Employee_ID)
			List<Department> departments = session.createQuery("from Department where Name = '" + department + "'", Department.class).getResultList();
			if(departments.size() > 0)
				writeToDb("insert into Sector(Code, Name, Department_ID, Employee_ID, Validity)"
						+ "values('" + code + "', '" + name + "', " + departments.get(0).getId() + ", null, 1)");
		}
	}
	public void removeSector(String code) {
		List<Sector> sectors = session.createQuery("from Sector where Code = '" + code + "'", Sector.class).getResultList();
		if(sectors.size() > 0) {
			writeToDb("update Employee set Sector_ID = null where Sector_ID = " + sectors.get(0).getId());
			writeToDb("update Sector set Validity = 0 where ID = " + sectors.get(0).getId());
		}
	}
}
