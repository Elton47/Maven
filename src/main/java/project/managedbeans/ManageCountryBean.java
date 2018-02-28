package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.CountryDao;
import project.entity.Country;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean(name = "manageCountryBean")
public class ManageCountryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final CountryDao countryDao = new CountryDao();
	private Country country = new Country();
	private List<Country> countries;
	private String name;
	@PostConstruct
	public void init() {
		countries = countryDao.getCountries();
		name = null;
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
	public void addCountry() {
		countryDao.addCountry(name);
		init(); // Refresh.
	}
	public void removeCountry(String name) {
		countryDao.removeCountry(name);
		init(); // Refresh.
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
