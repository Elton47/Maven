package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.ChildDao;
import project.entity.Child;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean(name = "manageChildBean")
public class ManageChildBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final ChildDao childDao = new ChildDao();
	private Child child = new Child();
	private List<Child> children;
	private String fullName, age, parent;
	@PostConstruct
	public void init() {
		children = childDao.getChildren();
		fullName = null;
		age = null;
		parent = null;
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
	public void addChild() {
		childDao.addChild(fullName, age, parent);
		init(); // Refresh.
	}
	public void removeChild(String fullName) {
		childDao.removeChild(fullName);
		init(); // Refresh.
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
}
