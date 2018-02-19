package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.User;
import project.util.HibernateUtil;

public class UserDao extends DbOps<User> {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    public List<User> getUsers() {
        try {
            TypedQuery<User> query = session.createQuery("from User", User.class);
            List<User> users = query.getResultList();
            return users;
        } catch(Exception e) {
            return null;
        }
    }
    public boolean validateUser(String username, String password) {
        try {
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery("from User where Username = '" + username + "' AND Password = '" + password + "'", User.class);
            List<User> list = query.getResultList();
            return list != null && list.size() > 0; // User Found.
        } catch(Exception e) {
            return false;
        }
    }
}
