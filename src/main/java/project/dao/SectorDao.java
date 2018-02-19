package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Sector;
import project.util.HibernateUtil;

public class SectorDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Sector> getSectors() {
		try {
			TypedQuery<Sector> query = session.createQuery("from Sector", Sector.class);
			List<Sector> sectors = query.getResultList();
			return sectors;
		} catch(Exception e) {
			return null;
		}
	}
}
