package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Child;
import project.entity.Employee;
import project.util.HibernateUtil;

public class ChildDao extends DbOps<Child> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Child> getChildren() {
		try {
			List<Child> children = session.createQuery("from Child where Validity = 1 order by ID DESC", Child.class).getResultList();
			if(children.size() > 0)
				return children;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addChild(String fullName, String age, String parent) {
		List<Employee> employees = session.createNativeQuery("select * from Employee where `Full Name` = '" + parent + "'", Employee.class).getResultList();
		if(!employees.isEmpty())
			writeToDb("insert into Child(`Full Name`, Age, Employee_ID, Validity)"
					+ "values('" + fullName + "', " + age + ", " + employees.get(0).getId() + ", 1)");
	}
	public void removeChild(String fullName) {
		List<Child> children = session.createNativeQuery("from Child where `Full Name` = '" + fullName + "'", Child.class).getResultList();
		if(children.size() > 0)
			writeToDb("update Child set Validity = 0 where ID = " + children.get(0).getId());
	}
}
