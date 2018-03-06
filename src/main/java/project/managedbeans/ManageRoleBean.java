package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.RoleDao;
import project.entity.Role;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageRoleBean")
public class ManageRoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final RoleDao roleDao = new RoleDao();
	private Role roleToRestore, role = new Role(), searchRole;
	private String sortBy = "";
	private boolean sortedASC = false, succeeded, editing;
	private List<String> permissionNames;
	private List<Role> roles = roleDao.getRoles();
	
	public List<Role> sort(String sortBy) {
		this.sortBy = sortBy;
		roles = searchRole != null ? roleDao.getRoles(searchRole) : roleDao.getRoles();
		if(sortBy.equals("name"))
			roles.sort(!sortedASC ? compareByName.reversed() : compareByName);
		sortedASC = !sortedASC;
		return roles;
	}
	public boolean isSortedASC() { // Used for sort arrows at xhtml file.
		return sortedASC;
	}
	public String getSortBy() { // Also this one.
		return sortBy;
	}
	public List<Role> getRoles() {
		List<Role> roles = sort(sortBy);
		if(!editing) // In case role is being edited but page got refreshed, reset editing to false;
			for(Role s : roles)
				if(s.isEditable())
					s.setEditable(false);
		sortedASC = !sortedASC; // To keep sorting order.
		return roles;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<String> getPermissionNames() {
		return permissionNames;
	}
	public void setPermissionNames(List<String> permissionNames) {
		this.permissionNames = permissionNames;
	}
	public boolean getSucceeded() { // For notifications.
		return succeeded;
	}
	public void addRole() {
		succeeded = roleDao.addRole(role, permissionNames);
		role = new Role(); // Reset form if more sectors are added continuously.
		permissionNames.clear();
	}
	public void searchRole() {
		searchRole = role; // For later use (at sort or get method).
		role = new Role(); // Reset form.
	}
	public void removeRole() {
		roleToRestore = role;
		succeeded = roleDao.removeRole(role);
		cancelEditing();
	}
	public void restoreRole() {
		if(roleToRestore != null) {
			succeeded = roleDao.restoreRole(roleToRestore);
			roleToRestore = null;
		}
	}
	public void editRole() {
		succeeded = roleDao.editRole(role, permissionNames);
		role.setEditable(false);
		cancelEditing();
	}
	public void setEditing(Role role) {
		this.role = role;
		this.role.setEditable(true);
		editing = true;
	}
	public void cancelEditing() {
		if(role != null) {
			role.setEditable(false);
			role = new Role();
		}
		editing = false;
		if(permissionNames != null)
			permissionNames.clear();
	}
	// Comparators (for sort).
    private static Comparator<Role> compareByName = new Comparator<Role>() {
        public int compare(Role one, Role other) {
            return one.getName().compareToIgnoreCase(other.getName());
        }
    };
}