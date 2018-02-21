package project.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Child;
import project.entity.Employee;
import project.util.HibernateUtil;

public class ChildDao extends DbOps<Child> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Child> getChildren() {
		try {
			TypedQuery<Child> query = session.createQuery("from Child where Validity = 1", Child.class);
			List<Child> children = query.getResultList();
			return children;
		} catch(Exception e) {
			return null;
		}
	}
	public void addChild(String fullName, String age, String parent) {
		List<Employee> employees = session.createQuery("from Employee where `Full Name` = '" + parent + "'", Employee.class).getResultList();
		if(!employees.isEmpty() || employees != null)
			writeToDb("insert into Child(`Full Name`, Age, Employee_ID, Validity)"
					+ "values('" + fullName + "', " + age + ", " + employees.get(0).getId() + ", 1)");
	}
	public void removeChild(String fullName) {
		List<Child> children = session.createQuery("from Child where `Full Name` = '" + fullName + "'", Child.class).getResultList();
		if(!children.isEmpty() || children != null)
			writeToDb("update Child set Validity = 0 where ID = " + children.get(0).getId());
	}
}
