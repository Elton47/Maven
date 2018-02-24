package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.SectorDao;
import project.entity.Sector;

@RequestScoped
@ManagedBean(name = "manageSectorBean")
public class ManageSectorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final SectorDao sectorDao = new SectorDao();
	private Sector sector = new Sector();
	private List<Sector> sectors;
	private String code, name, department, employee;
	@PostConstruct
	public void init() {
		sectors = sectorDao.getSectors();
		code = null;
		name = null;
		department = null;
		employee = null;
	}
	public List<Sector> getSectors() {
		return sectors;
	}
	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public void addSector() {
		if(code != null && name != null && department != null) // employee can be null.
			sectorDao.addSector(code, name, department, employee);
		init();
	}
	public void removeSector(String code) {
		sectorDao.removeSector(code);
		init();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
}
