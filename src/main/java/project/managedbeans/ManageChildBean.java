package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.ChildDao;
import project.entity.Child;

@ViewScoped
@ManagedBean(name = "manageChildBean")
public class ManageChildBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final ChildDao childDao = new ChildDao();
	private Child child = new Child();
	private List<Child> children;
	@PostConstruct
	public void init() {
		children = childDao.getChildren();
	}
	public List<Child> getChildren() {
		return children;
	}
	public void setChildren(List<Child> children) {
		this.children = children;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public void addChild(String fullName, String age, String parent) {
		if(fullName.length() > 0 && age.length() > 0 && parent.length() > 0) {
			childDao.addChild(fullName, age, parent);
			children = childDao.getChildren(); // Refresh.
		}
	}
	public void removeChild(String fullName) {
		childDao.removeChild(fullName);
		children = childDao.getChildren(); // Refresh.
	}
}
