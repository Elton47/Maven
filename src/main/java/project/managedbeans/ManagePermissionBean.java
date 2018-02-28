package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.PermissionDao;
import project.entity.Permission;
import project.entity.Role;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "managePermissionBean")
public class ManagePermissionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionDao permissionDao = new PermissionDao();
	private Permission permissionToEditOrRestore;
	private String name;
	private List<String> roleNames;
	private Role roleToRestore;
	private void resetInputFields() {
		name = null;
		roleNames = null;
	}
	public List<Permission> getPermissions() {
		return permissionDao.getPermissions();
	}
	public void addPermission() {
		permissionDao.addPermission(name, roleNames);
		resetInputFields();
	}
	public void removePermission(Permission permission) {
		if(permission != null) {
			permissionToEditOrRestore = permission;
			permissionDao.removePermission(permission);
		}
	}
	public void restorePermission() {
		if(permissionToEditOrRestore != null) {
			permissionDao.restorePermission(permissionToEditOrRestore);
			permissionToEditOrRestore = null;
		}
	}
	public void removeRoleFromPermission(Permission permission, Role role) {
		permissionToEditOrRestore = permission;
		roleToRestore = role;
		permissionDao.removeRoleFromPermission(permission, role);
	}
	public void restoreRoleFromPermission() {
		if(permissionToEditOrRestore != null && roleToRestore != null) {
			permissionDao.restoreRoleFromPermission(permissionToEditOrRestore, roleToRestore);
			permissionToEditOrRestore = null;
			roleToRestore = null;
		}
	}
	public void editPermission() {
		if(permissionToEditOrRestore != null)
			permissionDao.editPermission(name, roleNames, permissionToEditOrRestore);
		resetInputFields();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	public Permission getPermissionToEditOrRestore() {
		return permissionToEditOrRestore;
	}
	public void setPermissionToEditOrRestore(Permission permissionToEditOrRestore) {
		this.permissionToEditOrRestore = permissionToEditOrRestore;
	}
}
