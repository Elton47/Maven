package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.RoleDao;
import project.entity.Permission;
import project.entity.Role;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageRoleBean")
public class ManageRoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final RoleDao roleDao = new RoleDao();
	private Role roleToEditOrRestore;
	private String name;
	private List<String> permissionNames;
	private Permission permissionToRestore;
	public void resetInputFields() {
		name = null;
		permissionNames = null;
	}
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
	public void addRole() {
		roleDao.addRole(name, permissionNames);
		resetInputFields();
	}
	public void removeRole(Role role) {
		if(role != null) {
			roleToEditOrRestore = role;
			roleDao.removeRole(role);
		}
	}
	public void restoreRole() {
		if(roleToEditOrRestore != null) {
			roleDao.restoreRole(roleToEditOrRestore);
			roleToEditOrRestore = null;
		}
	}
	public void removePermissionFromRole(Role role, Permission permission) {
		roleToEditOrRestore = role;
		permissionToRestore = permission;
		roleDao.removePermissionFromRole(role, permission);
	}
	public void restorePermissionFromRole() {
		if(roleToEditOrRestore != null && permissionToRestore != null) {
			roleDao.restorePermissionFromRole(roleToEditOrRestore, permissionToRestore);
			roleToEditOrRestore = null;
			permissionToRestore = null;
		}
	}
	public void editRole() {
		if(roleToEditOrRestore != null)
			roleDao.editRole(name, permissionNames, roleToEditOrRestore);
		resetInputFields();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPermissionNames() {
		return permissionNames;
	}
	public void setPermissionNames(List<String> permissionNames) {
		this.permissionNames = permissionNames;
	}
	public Role getRoleToEditOrRestore() {
		return roleToEditOrRestore;
	}
	public void setRoleToEditOrRestore(Role roleToEditOrRestore) {
		this.roleToEditOrRestore = roleToEditOrRestore;
	}
}