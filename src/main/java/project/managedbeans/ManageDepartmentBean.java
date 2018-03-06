package project.managedbeans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.DepartmentDao;
import project.entity.Department;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "manageDepartmentBean")
public class ManageDepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final DepartmentDao departmentDao = new DepartmentDao();
	private Department departmentToRestore, department = new Department(), searchDepartment;
	private String sortBy = "";
	private boolean sortedASC = false, succeeded, editing;
	private List<Department> departments = departmentDao.getDepartments();
	
	public List<Department> sort(String sortBy) {
		this.sortBy = sortBy;
		departments = searchDepartment != null ? departmentDao.getDepartments(searchDepartment) : departmentDao.getDepartments();
		if(sortBy.equals("code"))
			departments.sort(!sortedASC ? compareByCode.reversed() : compareByCode);
		else if(sortBy.equals("name"))
			departments.sort(!sortedASC ? compareByName.reversed() : compareByName);
		else if(sortBy.equals("budget"))
			departments.sort(!sortedASC ? compareByBudget.reversed() : compareByBudget);
		sortedASC = !sortedASC;
		return departments;
	}
	public boolean isSortedASC() { // Used for sort arrows at xhtml file.
		return sortedASC;
	}
	public String getSortBy() { // Also this one.
		return sortBy;
	}
	public List<Department> getDepartments() {
		List<Department> departments = sort(sortBy);
		if(!editing) // In case department is being edited but page got refreshed, reset editing to false;
			for(Department d : departments)
				if(d.isEditable())
					d.setEditable(false);
		sortedASC = !sortedASC; // To keep sorting order.
		return departments;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public boolean getSucceeded() { // For notifications.
		return succeeded;
	}
	public void addDepartment() {
		succeeded = departmentDao.addDepartment(department);
		department = new Department(); // Reset form if more deparments are added continuously.
	}
	public void searchDepartment() {
		searchDepartment = department; // For later use (at sort or get method).
		department = new Department(); // Reset form.
	}
	public void removeDepartment() {
		departmentToRestore = department;
		succeeded = departmentDao.removeDepartment(department);
		cancelEditing();
	}
	public void restoreDepartment() {
		if(departmentToRestore != null) {
			succeeded = departmentDao.restoreDepartment(departmentToRestore);
			departmentToRestore.setEditable(false);
			departmentToRestore = null;
		}
	}
	public void editDepartment() {
		succeeded = departmentDao.editDepartment(department);
		department.setEditable(false);
		cancelEditing();
	}
	public void setEditing(Department department) {
		this.department = department;
		this.department.setEditable(true);
		editing = true;
	}
	public void cancelEditing() {
		if(department != null) {
			department.setEditable(false);
			department = new Department();
		}
		editing = false;
	}
	// Comparators (for sort).
	private static Comparator<Department> compareByCode = new Comparator<Department>() {
        public int compare(Department one, Department other) {
            return one.getCode().compareToIgnoreCase(other.getCode());
        }
    };
    private static Comparator<Department> compareByName = new Comparator<Department>() {
        public int compare(Department one, Department other) {
            return one.getName().compareToIgnoreCase(other.getName());
        }
    };
    private static Comparator<Department> compareByBudget = new Comparator<Department>() {
        public int compare(Department one, Department other) {
            return Integer.valueOf(one.getBudget()).compareTo(Integer.valueOf(other.getBudget()));
        }
    };
}