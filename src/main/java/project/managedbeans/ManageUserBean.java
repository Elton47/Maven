package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import project.dao.UserDao;
import project.entity.User;

@ViewScoped
@ManagedBean(name = "manageUserBean")
public class ManageUserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao();
    private User user = new User();
    private List<User> users;
    @PostConstruct
    public void init() {
        users = userDao.getUsers();
    }
    public String validateLogin() {
        return userDao.validateUser(user.getUsername(), user.getPassword()) ? "private/user/index.xhtml?faces-redirect=true" : "";
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
    public void addUser(String firstName, String middleName, String lastName, String username, String password, String department, String role) {
    	if(firstName.length() > 0 && middleName.length() > 0 && lastName.length() > 0 && username.length() > 0 && password.length() > 0 && department.length() > 0 && role.length() > 0) {
    		userDao.addUser(firstName, middleName, lastName, username, password, department, role);
    		users = userDao.getUsers(); // Refresh.
    	}
    }
    public void removeUser(String username) {
    	userDao.removeUser(username);
    	users = userDao.getUsers(); // Refresh.
    }
}
