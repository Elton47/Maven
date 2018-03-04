package project.dao;

public class UserLoginDao {
	public java.util.List<project.entity.User> validity(String username, String password) {
		return project.util.HibernateUtil.getSession().createQuery("from User where Username = ?1 AND Password = ?2 AND Validity = 1", project.entity.User.class).setParameter(1, username).setParameter(2, password).getResultList();
	}
}
