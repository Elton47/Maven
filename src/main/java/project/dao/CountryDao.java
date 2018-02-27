package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Country;
import project.util.HibernateUtil;

public class CountryDao extends DbOps<Country> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();
	public List<Country> getCountries() {
		try {
			List<Country> countries = session.createQuery("from Country where Validity = 1 order by ID DESC", Country.class).getResultList();
			if(countries.size() > 0)
				return countries;
			else
				return null;
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
		if(countries.size() > 0) {			
			writeToDb("update Employee set Country_ID = null where Country_ID = " + countries.get(0).getId());
			writeToDb("update Country set Validity = 0 where ID = " + countries.get(0).getId());
		}
	}
}
