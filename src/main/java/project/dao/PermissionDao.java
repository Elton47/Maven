package project.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Permission;
import project.util.HibernateUtil;

public class PermissionDao extends DbOps<Permission> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Permission> getPermissions() {
		try {
			TypedQuery<Permission> query = session.createQuery("from Permission where Validity = 1", Permission.class);
			List<Permission> permissions = query.getResultList();
			return permissions;
		} catch(Exception e) {
			return null;
		}
	}
	public void addPermission(String name) {
		writeToDb("insert into Permission(Name, Validity)"
				+ "values('" + name + "', 1)");
	}
	public void removePermission(String name) {
		List<Permission> permissions = session.createQuery("from Permission where Name = '" + name + "'", Permission.class).getResultList();
		if(!permissions.isEmpty() || permissions != null)
			writeToDb("update Permission set Validity = 0 where Name = '" + name + "'");
	}
}
