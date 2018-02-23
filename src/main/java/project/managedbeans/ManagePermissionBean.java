package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.PermissionDao;
import project.entity.Permission;

@RequestScoped
@ManagedBean(name = "managePermissionBean")
public class ManagePermissionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionDao permissionDao = new PermissionDao();
	private Permission permission = new Permission();
	private List<Permission> permissions;
	private String name;
	@PostConstruct
	public void init() {
		permissions = permissionDao.getPermissions();
		name = null;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public void addPermission() {
		permissionDao.addPermission(name);
		init(); // Refresh.
	}
	public void removePermission(String name) {
		permissionDao.removePermission(name);
		init(); // Refresh.
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
