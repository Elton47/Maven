package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import project.dao.SectorDao;
import project.entity.Department;
import project.entity.Employee;
import project.entity.Sector;

@SessionScoped
@ManagedBean(name = "manageSectorBean")
public class ManageSectorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final SectorDao sectorDao = new SectorDao();
	private Sector sector = new Sector(), sectorToEditOrRestore;
	private List<Sector> sectors;
	private String code, name, departmentName, employeeFullName;
	@PostConstruct
	public void init() {
		sectors = sectorDao.getSectors();
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
		if(sectorDao.addSector(code, name, departmentName, employeeFullName))
			init(); // Refresh.
	}
	public void removeSector(Sector sector) {
		if(sector != null) {
			sectorToEditOrRestore = sector;
			if(sectorDao.removeSector(sector))
				init();
		}
	}
	public void restoreSector() {
		if(sectorToEditOrRestore != null) {
			if(sectorDao.restoreSector(sectorToEditOrRestore))
				init();
			sectorToEditOrRestore = null;
		}
	}
	public void editSector() {
		if(sectorToEditOrRestore != null) {
			if(sectorDao.editSector(code, name, departmentName, employeeFullName, sectorToEditOrRestore))
				init();
		}
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
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmployeeFullName() {
		return employeeFullName;
	}
	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}
	public Sector getSectorToEditOrRestore() {
		return sectorToEditOrRestore;
	}
	public void setSectorToEditOrRestore(Sector sectorToEditOrRestore) {
		this.sectorToEditOrRestore = sectorToEditOrRestore;
	}
}