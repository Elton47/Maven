package project.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "visibilityController")
public class VisibilityController implements Serializable {
	private static final long serialVersionUID = 1L;
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
