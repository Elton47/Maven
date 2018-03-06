package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.PermissionDao;
import project.entity.Permission;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "managePermissionBean")
public class ManagePermissionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionDao permissionDao = new PermissionDao();
	private Permission permissionToRestore, permission = new Permission(), searchPermission;
	private String sortBy = "";
	private boolean sortedASC = false, succeeded, editing;
	private List<String> roleNames;
	private List<Permission> permissions = permissionDao.getPermissions();
	
	public List<Permission> sort(String sortBy) {
		this.sortBy = sortBy;
		permissions = searchPermission != null ? permissionDao.getPermissions(searchPermission) : permissionDao.getPermissions();
		if(sortBy.equals("name"))
			permissions.sort(!sortedASC ? compareByName.reversed() : compareByName);
		sortedASC = !sortedASC;
		return permissions;
	}
	public boolean isSortedASC() { // Used for sort arrows at xhtml file.
		return sortedASC;
	}
	public String getSortBy() { // Also this one.
		return sortBy;
	}
	public List<Permission> getPermissions() {
		List<Permission> permissions = sort(sortBy);
		if(!editing) // In case permission is being edited but page got refreshed, reset editing to false;
			for(Permission s : permissions)
				if(s.isEditable())
					s.setEditable(false);
		sortedASC = !sortedASC; // To keep sorting order.
		return permissions;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	public boolean getSucceeded() { // For notifications.
		return succeeded;
	}
	public void addPermission() {
		succeeded = permissionDao.addPermission(permission, roleNames);
		permission = new Permission(); // Reset form if more sectors are added continuously.
		roleNames.clear();
	}
	public void searchPermission() {
		searchPermission = permission; // For later use (at sort or get method).
		permission = new Permission(); // Reset form.
	}
	public void removePermission() {
		permissionToRestore = permission;
		succeeded = permissionDao.removePermission(permission);
		cancelEditing();
	}
	public void restorePermission() {
		if(permissionToRestore != null) {
			succeeded = permissionDao.restorePermission(permissionToRestore);
			permissionToRestore = null;
		}
	}
	public void editPermission() {
		succeeded = permissionDao.editPermission(permission, roleNames);
		permission.setEditable(false);
		cancelEditing();
	}
	public void setEditing(Permission permission) {
		this.permission = permission;
		this.permission.setEditable(true);
		editing = true;
	}
	public void cancelEditing() {
		if(permission != null) {
			permission.setEditable(false);
			permission = new Permission();
		}
		editing = false;
		if(roleNames != null)
			roleNames.clear();
	}
	// Comparators (for sort).
    private static Comparator<Permission> compareByName = new Comparator<Permission>() {
        public int compare(Permission one, Permission other) {
            return one.getName().compareToIgnoreCase(other.getName());
        }
    };
}