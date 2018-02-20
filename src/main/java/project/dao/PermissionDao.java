package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Permission;
import project.util.HibernateUtil;

public class PermissionDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Permission> getPermissions() {
		try {
			TypedQuery<Permission> query = session.createQuery("from Permission", Permission.class);
			List<Permission> permissions = query.getResultList();
			return permissions;
		} catch(Exception e) {
			return null;
		}
	}
}
