package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.CountryDao;
import project.entity.Country;

@ViewScoped
@ManagedBean(name = "manageCountryBean")
public class ManageCountryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final CountryDao countryDao = new CountryDao();
	private Country country = new Country();
	private List<Country> countries;
	@PostConstruct
	public void init() {
		countries = countryDao.getCountries();
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public void addCountry(String name) {
		if(name.length() > 0) {
			countryDao.addCountry(name);
			countries = countryDao.getCountries(); // Refresh.
		}
	}
	public void removeCountry(String name) {
		countryDao.removeCountry(name);
		countries = countryDao.getCountries(); // Refresh.
	}
}
