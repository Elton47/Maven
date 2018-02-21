package project.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;

import project.entity.Country;
import project.entity.Employee;
import project.entity.Sector;
import project.util.HibernateUtil;

public class EmployeeDao extends DbOps<Employee> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Employee> getEmployees() {
		try {
			TypedQuery<Employee> query = session.createQuery("from Employee where Validity = 1", Employee.class);
			List<Employee> employees = query.getResultList();
			return employees;
		} catch(Exception e) {
			return null;
		}
	}
	public void addEmployee(String ssn, String fullName, String cellNo, String wage, String country, String sector) {
		List<Country> countries = session.createQuery("from Country where Name = '" + country + "'", Country.class).getResultList();
		List<Sector> sectors = session.createQuery("from Sector where Name = '" + sector + "'", Sector.class).getResultList();
		if((!countries.isEmpty() || countries != null) && (!sectors.isEmpty() || sectors != null))
			writeToDb("insert into Employee(SSN, `Full Name`, CellNo, Wage, Country, Sector, Validity"
					+ "values('" + ssn + "', '" + fullName + "', '" + cellNo + "', " + wage + ", " + countries.get(0).getId() + ", " + sectors.get(0).getId() + ", 1)");
	}
	public void removeEmployee(String ssn) {
		List<Employee> employees = session.createQuery("from Employee where SSN = '" + ssn + "'", Employee.class).getResultList();
		if(!employees.isEmpty() || employees != null) {
			writeToDb("update Employee set Validity = 0 where ID = " + employees.get(0).getId() + "");
			writeToDb("update Child set Validity = 0 where Employee_ID = " + employees.get(0).getId());
			writeToDb("update Sector set Employee_ID = null where Employee_ID = " + employees.get(0).getId());
		}
	}
}
