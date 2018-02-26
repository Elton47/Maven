package project.dao;

import java.util.List;
import project.entity.User;
import project.util.HibernateUtil;

public class UserLoginDao {
	public List<User> validity(String username, String password) {
		return HibernateUtil.getSession().createQuery("from User where Username = ?1 AND Password = ?2 AND Validity = 1", User.class).setParameter(1, username).setParameter(2, password).getResultList();
	}
}
