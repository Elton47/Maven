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
	private Session session = HibernateUtil.getSession();
	public List<Role> getRoles() {
		return session.createQuery("from Role where Validity = 1 order by ID DESC", Role.class).getResultList();
	}
	public boolean addRole(String name, List<String> permissionNames) {
		if(permissionNames.get(0).equals(""))
			return save(new Role(name, null));
		else {
			List<Permission> permissionsToAdd = new ArrayList<Permission>();
			for(int i = 0; i < permissionNames.size(); i++)
				permissionsToAdd.add(session.createQuery("from Permission where Name = ?1", Permission.class).setParameter(1, permissionNames.get(i)).getSingleResult());
			return save(new Role(name, permissionsToAdd));
		}
	}
	public boolean removeRole(Role role) {
		role.setValidity(0);
		return update(role);
	}
	public boolean restoreRole(Role role) {
		role.setValidity(1);
		return update(role);
	}
	public boolean removePermissionFromRole(Role role, Permission permission) {
		List<Permission> updatedPermissions = role.getPermissions();
		updatedPermissions.remove(permission);
		role.setPermissions(updatedPermissions);
		return update(role);
	}
	public boolean restorePermissionFromRole(Role role, Permission permission) {
		List<Permission> updatedPermissions = role.getPermissions();
		updatedPermissions.add(permission);
		role.setPermissions(updatedPermissions);
		return update(role);
	}
	public boolean editRole(String name, List<String> permissionNames, Role role) {
		if(permissionNames.get(0).equals(""))
			role.setPermissions(null);		
		else {
			List<Permission> permissionsToAdd = new ArrayList<Permission>();
			for(int i = 0; i < permissionNames.size(); i++)
				permissionsToAdd.add(session.createQuery("from Permission where Name = ?1", Permission.class).setParameter(1, permissionNames.get(i)).getSingleResult());
			role.setPermissions(permissionsToAdd);
		}
		role.setName(name);
		role.setValidity(1);
		return update(role);
	}
}
