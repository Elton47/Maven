package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.SectorDao;
import project.entity.Department;
import project.entity.Employee;
import project.entity.Sector;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageSectorBean")
public class ManageSectorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final SectorDao sectorDao = new SectorDao();
	private Sector sectorToRestore, sector = new Sector(), searchSector;
	private String sortBy = "";
	private boolean sortedASC = false, succeeded, editing;
	private String departmentName, employeeFullName;
	private List<Sector> sectors = sectorDao.getSectors();
	
	public List<Sector> sort(String sortBy) {
		this.sortBy = sortBy;
		sectors = searchSector != null ? sectorDao.getSectors(searchSector) : sectorDao.getSectors();
		if(sortBy.equals("code"))
			sectors.sort(!sortedASC ? compareByCode.reversed() : compareByCode);
		else if(sortBy.equals("name"))
			sectors.sort(!sortedASC ? compareByName.reversed() : compareByName);
		else if(sortBy.equals("department"))
			sectors.sort(!sortedASC ? compareByDepartment.reversed() : compareByDepartment);
		else if(sortBy.equals("employee"))
			sectors.sort(!sortedASC ? compareByEmployee.reversed() : compareByEmployee);
		sortedASC = !sortedASC;
		return sectors;
	}
	public boolean isSortedASC() { // Used for sort arrows at xhtml file.
		return sortedASC;
	}
	public String getSortBy() { // Also this one.
		return sortBy;
	}
	public List<Sector> getSectors() {
		List<Sector> sectors = sort(sortBy);
		if(!editing) // In case sector is being edited but page got refreshed, reset editing to false;
			for(Sector s : sectors)
				if(s.isEditable())
					s.setEditable(false);
		sortedASC = !sortedASC; // To keep sorting order.
		return sectors;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
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
	public boolean getSucceeded() { // For notifications.
		return succeeded;
	}
	public void addSector() {
		succeeded = sectorDao.addSector(sector, departmentName, employeeFullName);
		sector = new Sector(); // Reset form if more sectors are added continuously.
		departmentName = "";
		employeeFullName = "";
	}
	public void searchSector() {
		searchSector = sector; // For later use (at sort or get method).
		if(searchSector.getDepartment() == null)
			searchSector.setDepartment(new Department());
		searchSector.getDepartment().setName(departmentName);
		if(searchSector.getEmployee() == null)
			searchSector.setEmployee(new Employee());
		searchSector.getEmployee().setFullName(employeeFullName);
		sector = new Sector(); // Reset form.
		departmentName = "";
		employeeFullName = "";
	}
	public void removeSector() {
		sectorToRestore = sector;
		succeeded = sectorDao.removeSector(sector);
		cancelEditing();
	}
	public void restoreSector() {
		if(sectorToRestore != null) {
			succeeded = sectorDao.restoreSector(sectorToRestore);
			sectorToRestore.setEditable(false);
			sectorToRestore = null;
		}
	}
	public void editSector() {
		succeeded = sectorDao.editSector(sector, departmentName, employeeFullName);
		sector.setEditable(false);
		cancelEditing();
	}
	public void setEditing(Sector sector) {
		this.sector = sector;
		this.sector.setEditable(true);
		editing = true;
	}
	public void cancelEditing() {
		if(sector != null) {
			sector.setEditable(false);
			sector = new Sector();
		}
		editing = false;
		departmentName = "";
		employeeFullName = "";
	}
	// Comparators (for sort).
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
    private static Comparator<Sector> compareByDepartment = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
            return one.getDepartment().getName().compareToIgnoreCase(other.getDepartment().getName());
        }
    };
    private static Comparator<Sector> compareByEmployee = new Comparator<Sector>() {
        public int compare(Sector one, Sector other) {
        	if(one.getEmployee() == null && other.getEmployee() == null)
        		return 0;
        	if(one.getEmployee() == null)
        		return "".compareToIgnoreCase(other.getEmployee().getFullName());
        	if(other.getEmployee() == null)
        		return one.getEmployee().getFullName().compareToIgnoreCase("");
            return one.getEmployee().getFullName().compareToIgnoreCase(other.getEmployee().getFullName());
        }
    };
}