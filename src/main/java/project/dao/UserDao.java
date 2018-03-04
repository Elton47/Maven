package project.dao;

import java.util.List;
import project.entity.Department;
import project.entity.Role;
import project.entity.User;

public class UserDao extends DbOps<User> {
    public List<User> getUsers() {
        try {
            List<User> users = session.createQuery("from User where Validity = 1 order by ID DESC", User.class).getResultList();
            if(!users.isEmpty())
            	return users;
            else
            	return null;
        } catch(Exception e) {
            return null;
        }
    }
    public void addUser(String firstName, String middleName, String lastName, String username, String password, String department, String role) {
    	List<Department> departments = session.createQuery("from Department where Name = '" + department + "'", Department.class).getResultList();
    	List<Role> roles = session.createQuery("from Role where Name = '" + role + "'", Role.class).getResultList();
    	if(departments.size() > 0 && roles.size() > 0)
    		writeToDb("insert into User(`First Name`, `Middle Name`, `Last Name`, Username, Password, Department_ID, Role_ID, Validity)"
    				+ "values('" + firstName + "', '" + middleName + "', '" + lastName + "', '" + username + "', '" + password + "', " + departments.get(0).getId() + ", " + roles.get(0).getId() + ", 1)");
    }
    public void removeUser(String username) {
    	List<User> users = session.createQuery("from User where Username = '" + username + "'", User.class).getResultList();
    	if(users.size() > 0)
    		writeToDb("update User set Validity = 0 where Username = '" + username + "'");
    }
}
