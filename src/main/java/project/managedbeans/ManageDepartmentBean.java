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
	private Department departmentToEditOrRestore;
	private String code, name, budget, sortBy = "";
	private boolean isSortedASC = false, succeeded;
	private void resetInputFields() {
		code = null;
		name = null;
		budget = null;
		departmentToEditOrRestore = null;
	}
	public List<Department> sort(String sortBy) {
		this.sortBy = sortBy;
		List<Department> departments = departmentDao.getDepartments();
		if(sortBy.equals("code"))
			departments.sort(!isSortedASC ? compareByCode.reversed() : compareByCode);
		else if(sortBy.equals("name"))
			departments.sort(!isSortedASC ? compareByName.reversed() : compareByName);
		else if(sortBy.equals("budget"))
			departments.sort(!isSortedASC ? compareByBudget.reversed() : compareByBudget);
		else
			departments.sort(compareById.reversed()); // Sort DESC by default (ID).
		isSortedASC = isSortedASC ? false : true;
		return departments;
	}
	public List<Department> getDepartments() {
		return sort(sortBy);
	}
	public boolean getSucceeded() {
		return succeeded;
	}
	public void addDepartment() {
		succeeded = !code.equals("") && !name.equals("") && !budget.equals("") ? departmentDao.addDepartment(code, name, budget) : false;
		resetInputFields();
	}
	public void removeDepartment() {
		if(departmentToEditOrRestore != null) // Redundant as departmentToEditOrRestore always gets reassigned from Remove Button onclick.
			departmentDao.removeDepartment(departmentToEditOrRestore);
	}
	public void restoreDepartment() {
		if(departmentToEditOrRestore != null) { // Same.
			departmentDao.restoreDepartment(departmentToEditOrRestore);
			resetInputFields();
		}
	}
	public void editDepartment() {
		succeeded = !code.equals("") && !name.equals("") && !budget.equals("") && departmentToEditOrRestore != null ? departmentDao.editDepartment(code, name, budget, departmentToEditOrRestore) : false;
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
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Department getDepartmentToEditOrRestore() {
		return departmentToEditOrRestore;
	}
	public void setDepartmentToEditOrRestore(Department departmentToEditOrRestore) {
		this.departmentToEditOrRestore = departmentToEditOrRestore;
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