package project.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import project.dao.UserLoginDao;
import project.entity.User;

@SuppressWarnings("deprecation")
@SessionScoped
@ManagedBean(name = "userLoginRequest")
public class UserLoginRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String validateLogin() {
        try {
            List<User> list = new UserLoginDao().validity(username, password);
            if(!list.isEmpty())
            	return "private/user/index.xhtml?faces-redirect=true";
            else
            	return "";
        } catch(Exception e) {
            return "";
        }
    }
    public String logout() {
    	new project.util.UserAuth().destroy();
    	return "/login.xhtml?faces-redirect=true";
    }
}
