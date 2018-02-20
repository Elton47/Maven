package project.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Country;
import project.util.HibernateUtil;

public class CountryDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Country> getCountries() {
		try {
			TypedQuery<Country> query = session.createQuery("from Country", Country.class);
			List<Country> countries = query.getResultList();
			return countries;
		} catch(Exception e) {
			return null;
		}
	}
}
