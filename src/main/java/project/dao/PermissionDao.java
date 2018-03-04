package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.entity.Permission;
import project.entity.Role;

public class PermissionDao extends DbOps<Permission> {
	public List<Permission> getPermissions() {
		return session.createQuery("from Permission where Validity = 1 order by ID DESC", Permission.class).getResultList();
	}
	public boolean addPermission(String name, List<String> roleNames) {
		if(roleNames.get(0).equals(""))
			return save(new Permission(name, null));
		else {
			List<Role> rolesToAdd = new ArrayList<Role>();
			for(int i = 0; i < roleNames.size(); i++)
				rolesToAdd.add(session.createQuery("from Role where Name = ?1", Role.class).setParameter(1, roleNames.get(i)).getSingleResult());
			return save(new Permission(name, rolesToAdd));
		}
	}
	public boolean removePermission(Permission permission) {
		permission.setValidity(0);
		return update(permission);
	}
	public boolean restorePermission(Permission permission) {
		permission.setValidity(1);
		return update(permission);
	}
	public boolean removeRoleFromPermission(Permission permission, Role role) {
		List<Role> updatedRoles = permission.getRoles();
		updatedRoles.remove(role);
		permission.setRoles(updatedRoles);
		return update(permission);
	}
	public boolean restoreRoleFromPermission(Permission permission, Role role) {
		List<Role> updatedRoles = permission.getRoles();
		updatedRoles.add(role);
		permission.setRoles(updatedRoles);
		return update(permission);
	}
	public boolean editPermission(String name, List<String> roleNames, Permission permission) {
		if(roleNames.get(0).equals(""))
			permission.setRoles(null);		
		else {
			List<Role> rolesToAdd = new ArrayList<Role>();
			for(int i = 0; i < roleNames.size(); i++)
				rolesToAdd.add(session.createQuery("from Role where Name = ?1", Role.class).setParameter(1, roleNames.get(i)).getSingleResult());
			permission.setRoles(rolesToAdd);
		}
		permission.setName(name);
		permission.setValidity(1);
		return update(permission);
	}
}
