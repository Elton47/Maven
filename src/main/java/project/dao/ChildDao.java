package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Child;
import project.util.HibernateUtil;

public class ChildDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Child> getChildren() {
		try {
			TypedQuery<Child> query = session.createQuery("from Child", Child.class);
			List<Child> children = query.getResultList();
			return children;
		} catch(Exception e) {
			return null;
		}
	}
}
