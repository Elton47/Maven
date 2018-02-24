package project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import project.entity.Permission;
import project.entity.Role;
import project.util.HibernateUtil;

public class RoleDao extends DbOps<Role> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Role> getRoles() {
		try {
			List<Role> roles = session.createQuery("from Role where Validity = 1", Role.class).getResultList();
			if(!roles.isEmpty())
				return roles;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addRole(String name, List<String> permissions) {
		writeToDb("insert into Role(Name, Validity)"
				+ "values('" + name + "', 1)");
		List<Role> roles = session.createQuery("from Role where Name = '" + name + "'", Role.class).getResultList();
		if(!roles.isEmpty()) {
			if(!permissions.isEmpty()) {
				for(int i = 0; i < permissions.size(); i++) {
					List<Permission> permissionsOfRole = session.createQuery("from Permission where Name = '" + permissions.get(i) + "'", Permission.class).getResultList();
					if(!permissionsOfRole.isEmpty()) {
						writeToDb("insert into role_permission(Role_ID, permissions_id)"
								+ "values(" + roles.get(0).getId() + ", " + permissionsOfRole.get(0).getId() + ")");
					}
				}
			}
			// else: No Permissions.
		}
	}
	public void removeRole(String name) {
		List<Role> roles = session.createQuery("from Role where Name = '" + name + "'", Role.class).getResultList();
		if(!roles.isEmpty()) {
			writeToDb("update User set Validity = 0 where Role_ID = " + roles.get(0).getId());
			writeToDb("delete from Role_has_Permission where Role_ID = " + roles.get(0).getId());
			writeToDb("update Role set Validity = 0 where ID = " + roles.get(0).getId());
		}
	}
}
