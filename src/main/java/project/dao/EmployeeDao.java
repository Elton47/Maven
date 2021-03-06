package project.dao;

import java.util.List;
import project.entity.Country;
import project.entity.Employee;
import project.entity.Sector;

public class EmployeeDao extends DbOps<Employee> {
	public List<Employee> getEmployees() {
		try {
			List<Employee> employees = session.createQuery("from Employee where Validity = 1 order by ID DESC", Employee.class).getResultList();
			if(employees.size() > 0)
				return employees;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addEmployee(String ssn, String fullName, String cellNo, String wage, String country, String sector) {
		List<Country> countries = session.createQuery("from Country where Name = '" + country + "'", Country.class).getResultList();
		List<Sector> sectors = session.createQuery("from Sector where Name = '" + sector + "'", Sector.class).getResultList();
		if(countries.size() > 0 && sectors.size() > 0)
			writeToDb("insert into Employee(SSN, `Full Name`, CellNo, Wage, Country_ID, Sector_ID, Validity)"
					+ "values('" + ssn + "', '" + fullName + "', '" + cellNo + "', " + wage + ", " + countries.get(0).getId() + ", " + sectors.get(0).getId() + ", 1)");
	}
	public void removeEmployee(String ssn) {
		List<Employee> employees = session.createQuery("from Employee where SSN = '" + ssn + "'", Employee.class).getResultList();
		if(employees.size() > 0) {
			writeToDb("update Child set Validity = 0 where Employee_ID = " + employees.get(0).getId());
			writeToDb("update Sector set Employee_ID = null where Employee_ID = " + employees.get(0).getId());
			writeToDb("update Employee set Validity = 0 where ID = " + employees.get(0).getId());
		}
	}
}
