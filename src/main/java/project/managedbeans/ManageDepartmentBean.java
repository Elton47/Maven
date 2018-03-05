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
	private Department departmentToRestore, department = new Department();
	private String sortBy = "";
	private boolean sortedASC = false, succeeded;
	public List<Department> sort(String sortBy) {
		this.sortBy = sortBy;
		List<Department> departments = departmentDao.getDepartments();
		if(sortBy.equals("code"))
			departments.sort(!sortedASC ? compareByCode.reversed() : compareByCode);
		else if(sortBy.equals("name"))
			departments.sort(!sortedASC ? compareByName.reversed() : compareByName);
		else if(sortBy.equals("budget"))
			departments.sort(!sortedASC ? compareByBudget.reversed() : compareByBudget);
		else
			departments.sort(compareById.reversed()); // Sort DESC by default (ID).
		sortedASC = sortedASC ? false : true;
		return departments;
	}
	public boolean isSortedASC() {
		return sortedASC;
	}
	public String getSortBy() {
		return sortBy;
	}
	public List<Department> getDepartments() {
		return sort(sortBy);
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public boolean getSucceeded() {
		return succeeded;
	}
	public void addDepartment() {
		succeeded = departmentDao.addDepartment(department);
		department = new Department(); // If more deparments are added continuously.
	}
	public void removeDepartment() {
		departmentToRestore = department;
		succeeded = departmentDao.removeDepartment(department);
		cancelEditing();
	}
	public void restoreDepartment() {
		succeeded = departmentDao.restoreDepartment(departmentToRestore);
		departmentToRestore.setEditable(false);
		departmentToRestore = null;
	}
	public void editDepartment() {
		succeeded = departmentDao.editDepartment(department);
		department.setEditable(false);
		cancelEditing();
	}
	public Department getDepartmentToEditOrRestore() {
		return departmentToRestore;
	}
	public void setEditing(Department department) {
		this.department = department;
		this.department.setEditable(true);
	}
	public void cancelEditing() {
		if(department != null) {
			department.setEditable(false);
			department = new Department();
		}
	}
	// Comparators.
	private static Comparator<Department> compareById = new Comparator<Department>() {
        public int compare(Department one, Department other) {
            return Integer.valueOf(one.getId()).compareTo(Integer.valueOf(other.getId()));
        }
    };
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