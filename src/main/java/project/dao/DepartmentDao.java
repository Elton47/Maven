package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Department;
import project.util.HibernateUtil;

public class DepartmentDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Department> getDepartments() {
		try {
			TypedQuery<Department> query = session.createQuery("from Department", Department.class);
			List<Department> departments = query.getResultList();
			return departments;
		} catch(Exception e) {
			return null;
		}
	}
}
