package project.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import project.dao.UserLoginDao;
import project.entity.User;
import project.util.HibernateUtil;

@RequestScoped
@ManagedBean(name = "userLoginRequest")
public class UserLoginRequest {
	private Session session = HibernateUtil.getSession();
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
            session.beginTransaction();
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
