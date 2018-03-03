package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
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
	private String code, name, departmentName, employeeFullName, sortBy = "";
	private boolean isSortedASC = false, succeeded;
	private void resetInputFields() {
		code = null;
		name = null;
		departmentName = null;
		employeeFullName = null;
		sectorToEditOrRestore = null;
	}
	public List<Sector> sort(String sortBy) {
		this.sortBy = sortBy;
		List<Sector> sectors = sectorDao.getSectors();
		if(sortBy.equals("code"))
			sectors.sort(!isSortedASC ? compareByCode.reversed() : compareByCode);
		else if(sortBy.equals("name"))
			sectors.sort(!isSortedASC ? compareByName.reversed() : compareByName);
		else if(sortBy.equals("departmentName"))
			sectors.sort(!isSortedASC ? compareByDepartmentName.reversed() : compareByDepartmentName);
		else if(sortBy.equals("employeeFullName"))
			sectors.sort(!isSortedASC ? compareByEmployeeFullName.reversed() : compareByEmployeeFullName);
		else
			sectors.sort(compareById.reversed()); // Sort DESC by default (ID).
		isSortedASC = isSortedASC ? false : true;
		return sectors;
	}
	public List<Sector> getSectors() {
		return sort(sortBy);
	}
	public boolean getSucceeded() {
		return succeeded;
	}
	public void addSector() {
		succeeded = code != null && name != null && departmentName != null ? sectorDao.addSector(code, name, departmentName, employeeFullName) : false;
		resetInputFields();
	}
	public void removeSector() {
		if(sectorToEditOrRestore != null) // Redundant as sectorToEditOrRestore always gets reassigned from Remove Button onclick.
			sectorDao.removeSector(sectorToEditOrRestore);
	}
	public void restoreSector() {
		if(sectorToEditOrRestore != null) { // Same.
			sectorDao.restoreSector(sectorToEditOrRestore);
			resetInputFields();
		}
	}
	public void editSector() {
		succeeded = code != null && name != null && departmentName != null && sectorToEditOrRestore != null ? sectorDao.editSector(code, name, departmentName, employeeFullName, sectorToEditOrRestore) : false;
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
	// Comparators.
	private static Comparator<Sector> compareById = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
            return Integer.valueOf(one.getId()).compareTo(Integer.valueOf(other.getId()));
        }
    };
	private static Comparator<Sector> compareByCode = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
            return one.getCode().compareToIgnoreCase(other.getCode());
        }
    };
    private static Comparator<Sector> compareByName = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
            return one.getName().compareToIgnoreCase(other.getName());
        }
    };
    private static Comparator<Sector> compareByDepartmentName = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
            return one.getDepartment().getName().compareToIgnoreCase(other.getDepartment().getName());
        }
    };
    private static Comparator<Sector> compareByEmployeeFullName = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
        	if(one.getEmployee() != null) {
        		if(other.getEmployee() != null)
        			return one.getEmployee().getFullName().compareToIgnoreCase(other.getEmployee().getFullName());
        		else
        			return one.getEmployee().getFullName().compareToIgnoreCase("");
        	}
        	else {
        		if(other.getEmployee() != null)
        			return "".compareToIgnoreCase(other.getEmployee().getFullName());
        		else
        			return "".compareToIgnoreCase(""); // or return 0;
        	}
        }
    };
}