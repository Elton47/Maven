package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Employee;
import project.util.HibernateUtil;

public class EmployeeDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Employee> getEmployees() {
		try {
			TypedQuery<Employee> query = session.createQuery("from Employee", Employee.class);
			List<Employee> employees = query.getResultList();
			return employees;
		} catch(Exception e) {
			return null;
		}
	}
}
