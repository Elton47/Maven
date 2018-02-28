package project.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import project.dao.UserLoginDao;
import project.entity.User;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean(name = "userLoginRequest")
public class UserLoginRequest {
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
}
