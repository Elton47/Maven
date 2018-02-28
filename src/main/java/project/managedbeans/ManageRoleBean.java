package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.RoleDao;
import project.entity.Permission;
import project.entity.Role;

@ViewScoped
@ManagedBean(name = "manageRoleBean")
public class ManageRoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final RoleDao roleDao = new RoleDao();
	private Role role = new Role(), roleToEditOrRestore;
	private List<Role> roles;
	private String name;
	private List<String> permissionNames;
	private Permission permissionToRestore;
	@PostConstruct
	public void init() {
		roles = roleDao.getRoles();
	}
	public void resetInputFields() {
		name = null;
		permissionNames = null;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void addRole() {
		if(roleDao.addRole(name, permissionNames))
			init(); // Refresh.
		resetInputFields();
	}
	public void removeRole(Role role) {
		if(role != null) {
			roleToEditOrRestore = role;
			if(roleDao.removeRole(role))
				init();
		}
	}
	public void restoreRole() {
		if(roleToEditOrRestore != null) {
			if(roleDao.restoreRole(roleToEditOrRestore))
				init();
			roleToEditOrRestore = null;
		}
	}
	public void removePermissionFromRole(Role role, Permission permission) {
		roleToEditOrRestore = role;
		permissionToRestore = permission;
		if(roleDao.removePermissionFromRole(role, permission))
			init();
	}
	public void restorePermissionFromRole() {
		if(roleToEditOrRestore != null && permissionToRestore != null) {
			if(roleDao.restorePermissionFromRole(roleToEditOrRestore, permissionToRestore))
				init();
			roleToEditOrRestore = null;
			permissionToRestore = null;
		}
	}
	public void editRole() {
		if(roleToEditOrRestore != null) {
			if(roleDao.editRole(name, permissionNames, roleToEditOrRestore))
				init();
		}
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