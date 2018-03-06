package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.entity.Permission;
import project.entity.Role;

public class RoleDao extends DbOps<Role> {
	public List<Role> getRoles() {
		return session.createQuery("from Role where validity = 1 order by id desc", Role.class).getResultList();
	}
	public List<Role> getRoles(Role role) {
		return session.createQuery("from Role where validity = 1 and name like ?1", Role.class).setParameter(1, role.getName() + "%").getResultList();
	}
	public boolean addRole(Role role, List<String> permissionNames) {
		List<Permission> permissionsToAdd = new ArrayList<Permission>();
		for(String p : permissionNames)
			permissionsToAdd.add(session.createQuery("from Permission where name = ?1", Permission.class).setParameter(1, p).getSingleResult());
		role.setPermissions(permissionsToAdd);
		return save(role);
	}
	public boolean removeRole(Role role) {
		role.setValidity(0);
		return update(role);
	}
	public boolean restoreRole(Role role) {
		role.setValidity(1);
		return update(role);
	}
	public boolean editRole(Role role, List<String> permissionNames) {
		Role backupRole = role; // In case editing fails.
		List<Permission> updatedPermissions = new ArrayList<Permission>();
		for(String p : permissionNames)
			updatedPermissions.add(session.createQuery("from Permission where name = ?1", Permission.class).setParameter(1, p).getSingleResult());
		role.setPermissions(updatedPermissions);
		boolean succeeded = update(role);
		if(!succeeded)
			update(backupRole);
		return succeeded;
	}
}