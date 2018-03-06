/*package project.managedbeans;

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
*/
package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.UserDao;
import project.entity.Department;
import project.entity.Role;
import project.entity.User;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageUserBean")
public class ManageUserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final UserDao userDao = new UserDao();
	private User userToRestore, user = new User(), searchUser;
	private String sortBy = "";
	private boolean sortedASC = false, succeeded, editing;
	private String departmentName, roleName;
	private List<User> users = userDao.getUsers();
	
	public List<User> sort(String sortBy) {
		this.sortBy = sortBy;
		users = searchUser != null ? userDao.getUsers(searchUser) : userDao.getUsers();
		if(sortBy.equals("firstName"))
			users.sort(!sortedASC ? compareByFirstName.reversed() : compareByFirstName);
		else if(sortBy.equals("middleName"))
			users.sort(!sortedASC ? compareByMiddleName.reversed() : compareByMiddleName);
		else if(sortBy.equals("lastName"))
			users.sort(!sortedASC ? compareByLastName.reversed() : compareByLastName);
		else if(sortBy.equals("username"))
			users.sort(!sortedASC ? compareByUsername.reversed() : compareByUsername);
		else if(sortBy.equals("department"))
			users.sort(!sortedASC ? compareByDepartment.reversed() : compareByDepartment);
		else if(sortBy.equals("role"))
			users.sort(!sortedASC ? compareByRole.reversed() : compareByRole);
		sortedASC = !sortedASC;
		return users;
	}
	public boolean isSortedASC() { // Used for sort arrows at xhtml file.
		return sortedASC;
	}
	public String getSortBy() { // Also this one.
		return sortBy;
	}
	public List<User> getUsers() {
		List<User> users = sort(sortBy);
		if(!editing) // In case user is being edited but page got refreshed, reset editing to false;
			for(User s : users)
				if(s.isEditable())
					s.setEditable(false);
		sortedASC = !sortedASC; // To keep sorting order.
		return users;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean getSucceeded() { // For notifications.
		return succeeded;
	}
	public void addUser() {
		succeeded = userDao.addUser(user, departmentName, roleName);
		user = new User(); // Reset form if more users are added continuously.
		departmentName = "";
		roleName = "";
	}
	public void searchUser() {
		searchUser = user; // For later use (at sort or get method).
		if(searchUser.getDepartment() == null)
			searchUser.setDepartment(new Department());
		searchUser.getDepartment().setName(departmentName);
		if(searchUser.getRole() == null)
			searchUser.setRole(new Role());
		searchUser.getRole().setName(roleName);
		user = new User(); // Reset form.
		departmentName = "";
		roleName = "";
	}
	public void removeUser() {
		userToRestore = user;
		succeeded = userDao.removeUser(user);
		cancelEditing();
	}
	public void restoreUser() {
		if(userToRestore != null) {
			succeeded = userDao.restoreUser(userToRestore);
			userToRestore.setEditable(false);
			userToRestore = null;
		}
	}
	public void editUser() {
		succeeded = userDao.editUser(user, departmentName, roleName);
		user.setEditable(false);
		cancelEditing();
	}
	public void setEditing(User user) {
		this.user = user;
		this.user.setEditable(true);
		editing = true;
	}
	public void cancelEditing() {
		if(user != null) {
			user.setEditable(false);
			user = new User();
		}
		editing = false;
		departmentName = "";
		roleName = "";
	}
	// Comparators (for sort).
	private static Comparator<User> compareByFirstName = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getFirstName().compareToIgnoreCase(other.getFirstName());
        }
    };
    private static Comparator<User> compareByMiddleName = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getMiddleName().compareToIgnoreCase(other.getMiddleName());
        }
    };
    private static Comparator<User> compareByLastName = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getLastName().compareToIgnoreCase(other.getLastName());
        }
    };
    private static Comparator<User> compareByUsername = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getUsername().compareToIgnoreCase(other.getUsername());
        }
    };
    private static Comparator<User> compareByDepartment = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getDepartment().getName().compareToIgnoreCase(other.getDepartment().getName());
        }
    };
    private static Comparator<User> compareByRole = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.getRole().getName().compareToIgnoreCase(other.getRole().getName());
        }
    };
}