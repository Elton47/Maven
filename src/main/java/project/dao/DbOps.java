package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.util.HibernateUtil;

public abstract class DbOps<T> {
    Session session = HibernateUtil.getSessionFactory().openSession();
    public void save(T t) {
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch(RuntimeException e) {
            if(trans != null)
                trans.rollback();
        }
    }
    public boolean delete(T t) {
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch(RuntimeException e) {
            if(trans != null)
                trans.rollback();
        }
        return false;
    }
    public boolean update(T t) {
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        } catch(RuntimeException e) {
            if(trans != null)
                trans.rollback();
        }
        return false;
    }
    public void writeToDb(String statement) {
		session.getTransaction().begin();
	    session.createNativeQuery(statement).executeUpdate();
	    session.getTransaction().commit();
    }
}
