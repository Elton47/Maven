package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.PermissionDao;
import project.entity.Permission;

@ViewScoped
@ManagedBean(name = "managePermissionBean")
public class ManagePermissionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionDao permissionDao = new PermissionDao();
	private Permission permission = new Permission();
	private List<Permission> permissions;
	@PostConstruct
	public void init() {
		permissions = permissionDao.getPermissions();
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
}
