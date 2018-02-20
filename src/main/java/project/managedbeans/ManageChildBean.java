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
	private final ChildDao countryDao = new ChildDao();
	private Child country = new Child();
	private List<Child> children;
	@PostConstruct
	public void init() {
		children = countryDao.getChildren();
	}
	public List<Child> getChildren() {
		return children;
	}
	public void setChildren(List<Child> children) {
		this.children = children;
	}
	public Child getChild() {
		return country;
	}
	public void setChild(Child country) {
		this.country = country;
	}
}
