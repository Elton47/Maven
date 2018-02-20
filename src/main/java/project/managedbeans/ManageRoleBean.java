package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.RoleDao;
import project.entity.Role;

@ViewScoped
@ManagedBean(name = "manageRoleBean")
public class ManageRoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final RoleDao roleDao = new RoleDao();
	private Role role = new Role();
	private List<Role> roles;
	@PostConstruct
	public void init() {
		roles = roleDao.getRoles();
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
}
