package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.SectorDao;
import project.entity.Sector;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageSectorBean")
public class ManageSectorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final SectorDao sectorDao = new SectorDao();
	private Sector sectorToEditOrRestore;
	private String code, name, departmentName, employeeFullName;
	public void resetInputFields() {
		code = null;
		name = null;
		departmentName = null;
		employeeFullName = null;
	}
	public List<Sector> getSectors() {
		return sectorDao.getSectors();
	}
	public void addSector() {
		sectorDao.addSector(code, name, departmentName, employeeFullName);
		resetInputFields();
	}
	public void removeSector(Sector sector) {
		if(sector != null) {
			sectorToEditOrRestore = sector;
			sectorDao.removeSector(sector);
		}
	}
	public void restoreSector() {
		if(sectorToEditOrRestore != null) {
			sectorDao.restoreSector(sectorToEditOrRestore);
			sectorToEditOrRestore = null;
		}
	}
	public void editSector() {
		if(sectorToEditOrRestore != null)
			sectorDao.editSector(code, name, departmentName, employeeFullName, sectorToEditOrRestore);
		resetInputFields();
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