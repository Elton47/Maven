package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.UserDao;
import project.entity.User;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean(name = "manageUserBean")
public class ManageUserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao();
    private User user = new User();
    private List<User> users;
    private String firstName, middleName, lastName, username, password, department, role;
    @PostConstruct
    public void init() {
        users = userDao.getUsers();
        firstName = null;
        middleName = null;
        lastName = null;
        username = null;
        password = null;
        department = null;
        role = null;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void addUser() {
		userDao.addUser(firstName, middleName, lastName, username, password, department, role);
		init(); // Refresh.
    }
    public void removeUser(String username) {
    	userDao.removeUser(username);
    	init(); // Refresh.
    }
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
