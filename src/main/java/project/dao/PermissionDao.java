package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.entity.Permission;
import project.entity.Role;

public class PermissionDao extends DbOps<Permission> {
	public List<Permission> getPermissions() {
		return session.createQuery("from Permission where validity = 1 order by id desc", Permission.class).getResultList();
	}
	public List<Permission> getPermissions(Permission permission) {
		return session.createQuery("from Permission where validity = 1 and name like ?1", Permission.class).setParameter(1, permission.getName() + "%").getResultList();
	}
	public boolean addPermission(Permission permission, List<String> roleNames) {
		List<Role> rolesToAdd = new ArrayList<Role>();
		for(String p : roleNames)
			rolesToAdd.add(session.createQuery("from Role where name = ?1", Role.class).setParameter(1, p).getSingleResult());
		permission.setRoles(rolesToAdd);
		return save(permission);
	}
	public boolean removePermission(Permission permission) {
		/*List<Role> rolesToRemovePermissionsFrom = permission.getRoles();
		for(Role r : rolesToRemovePermissionsFrom) {
			List<Permission> permissionsOfRole = r.getPermissions();
			for(Permission p : permissionsOfRole)
				if(p.equals(permission))
					r.getPermissions().remove(p);
			new RoleDao().update(r);
		}*/
		permission.setValidity(0);
		return update(permission);
	}
	public boolean restorePermission(Permission permission) {
		/*List<Role> rolesToRestorePermissionsFrom = permission.getRoles();
		for(Role r : rolesToRestorePermissionsFrom) {
			List<Permission> permissionsOfRole = r.getPermissions();
			for(Permission p : permissionsOfRole)
				if(p.equals(permission))
					r.getPermissions().add(p);
			new RoleDao().update(r);
		}*/
		permission.setValidity(1);
		return update(permission);
	}
	public boolean editPermission(Permission permission, List<String> roleNames) {
		Permission backupPermission = permission; // In case editing fails.
		List<Role> updatedPermissions = new ArrayList<Role>();
		for(String p : roleNames)
			updatedPermissions.add(session.createQuery("from Permission where name = ?1", Role.class).setParameter(1, p).getSingleResult());
		permission.setRoles(updatedPermissions);
		boolean succeeded = update(permission);
		if(!succeeded)
			update(backupPermission);
		return succeeded;
	}
}