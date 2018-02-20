package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Role;
import project.util.HibernateUtil;

public class RoleDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Role> getRoles() {
		try {
			TypedQuery<Role> query = session.createQuery("from Role", Role.class);
			List<Role> roles = query.getResultList();
			return roles;
		} catch(Exception e) {
			return null;
		}
	}
}
