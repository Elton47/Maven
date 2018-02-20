package project.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "visibilityController")
public class VisibilityController {
	private String show;
	public void setShow(String show) {
		if(this.show == null)
			this.show = show;
		else if(!this.show.equals(show))
			this.show = show;
		else
			this.show = "";
	}
	public String getShow() {
		return show;
	}
}
