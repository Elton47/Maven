package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Permission;
import project.util.HibernateUtil;

public class PermissionDao extends DbOps<Permission> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Permission> getPermissions() {
		try {
			List<Permission> permissions = session.createQuery("from Permission where Validity = 1 order by ID DESC", Permission.class).getResultList();
			if(permissions.size() > 0)
				return permissions;
			else
				return null;
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
		if(permissions.size() > 0)
			writeToDb("update Permission set Validity = 0 where Name = '" + name + "'");
	}
}
