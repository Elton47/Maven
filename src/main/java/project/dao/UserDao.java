package project.dao;

import java.util.List;
import project.entity.Department;
import project.entity.Role;
import project.entity.User;

public class UserDao extends DbOps<User> {
	public List<User> getUsers() {
		return session.createQuery("from User where validity = 1 order by id desc", User.class).getResultList();
	}
	public List<User> getUsers(User user) {
		return session.createQuery("from User where validity = 1 and firstName like ?1 and lastName like ?2 and username like ?3", User.class).setParameter(1, user.getFirstName() + "%").setParameter(2, user.getLastName() + "%").setParameter(3, user.getUsername() + "%").getResultList();
	}
	public boolean addUser(User user, String departmentName, String roleName) {
		user.setDepartment(session.createQuery("from Department where name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		user.setRole(session.createQuery("from Role where name = ?1", Role.class).setParameter(1, roleName).getSingleResult());
		return save(user);
	}
	public boolean removeUser(User user) {
		user.setValidity(0);
		return update(user);
	}
	public boolean restoreUser(User user) {
		user.setValidity(1);
		return update(user);
	}
	public boolean editUser(User user, String departmentName, String roleName) {
		User backupUser = user; // In case editing fails.
		user.setDepartment(session.createQuery("from Department where name = ?1", Department.class).setParameter(1, departmentName).getSingleResult());
		user.setRole(session.createQuery("from Role where name = ?1", Role.class).setParameter(1, roleName).getSingleResult());
		boolean succeeded = update(user);
		if(!succeeded)
			update(backupUser);
		return succeeded;
	}
}