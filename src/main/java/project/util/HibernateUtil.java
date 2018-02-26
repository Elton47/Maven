package project.util;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Session session;
    static {
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
        } catch(Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Session getSession() {
        return session;
    }
}
