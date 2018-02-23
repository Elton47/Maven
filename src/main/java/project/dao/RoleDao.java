package project.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import project.entity.Role;
import project.util.HibernateUtil;

public class RoleDao extends DbOps<Role> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	public List<Role> getRoles() {
		try {
			List<Role> roles = session.createQuery("from Role where Validity = 1", Role.class).getResultList();
			if(roles.size() > 0)
				return roles;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	public void addRole(String name) {
		writeToDb("insert into Role(Name, Validity)"
				+ "values('" + name + "', 1)");
	}
	public void removeRole(String name) {
		List<Role> roles = session.createQuery("from Role where Name = '" + name + "'", Role.class).getResultList();
		if(roles.size() > 0) {
			writeToDb("update User set Validity = 0 where Role_ID = " + roles.get(0).getId());
			writeToDb("update Role set Validity = 0 where ID = " + roles.get(0).getId());
		}
	}
}
