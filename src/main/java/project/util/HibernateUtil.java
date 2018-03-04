package project.util;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Session SESSION = new Configuration().configure().buildSessionFactory().openSession();
    public static Session getSession() {
        return SESSION;
    }
}