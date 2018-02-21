package project.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import project.entity.Country;
import project.entity.Employee;
import project.util.HibernateUtil;

public class CountryDao extends DbOps<Country> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Country> getCountries() {
		try {
			TypedQuery<Country> query = session.createQuery("from Country where Validity = 1", Country.class);
			List<Country> countries = query.getResultList();
			return countries;
		} catch(Exception e) {
			return null;
		}
	}
	public void addCountry(String name) {
		writeToDb("insert into Country(Name, Validity)"
				+ "values('" + name + "', 1)");
	}
	public void removeCountry(String name) {
		List<Country> countries = session.createQuery("from Country where Name = '" + name + "'", Country.class).getResultList();
		if(!countries.isEmpty() || countries != null) {
			writeToDb("update Country set Validity = 0 where ID = " + countries.get(0).getId());
			writeToDb("update Employee set Country_ID = null where Country_ID = " + countries.get(0).getId());
		}
	}
}
