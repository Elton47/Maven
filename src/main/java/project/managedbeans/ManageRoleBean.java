package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.RoleDao;
import project.entity.Permission;
import project.entity.Role;

@RequestScoped
@ManagedBean(name = "manageRoleBean")
public class ManageRoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final RoleDao roleDao = new RoleDao();
	private Role role = new Role();
	private List<Role> roles;
	private String name;
	private List<String> permissions;
	@PostConstruct
	public void init() {
		roles = roleDao.getRoles();
		name = null;
		permissions = null;
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
		roleDao.addRole(name, permissions);
		init(); // Refresh.
	}
	public void removeRole(String name) {
		roleDao.removeRole(name);
		init(); // Refresh.
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
