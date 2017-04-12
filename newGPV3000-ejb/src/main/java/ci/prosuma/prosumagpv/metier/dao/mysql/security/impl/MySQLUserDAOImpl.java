package ci.prosuma.prosumagpv.metier.dao.mysql.security.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import ci.prosuma.prosumagpv.entity.security.User;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IMySQLUserDAO;

@Stateless
@Local(IMySQLUserDAO.class)
public class MySQLUserDAOImpl implements IMySQLUserDAO, Serializable {

	private static Logger logger = Logger.getLogger(MySQLUserDAOImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public User createOrUpdateUser(User u) {
		User temp = getUser(u.getUserName());
		if (temp != null) {
			em.merge(u);
			em.flush();
			return u;
		} else {
			em.persist(u);
			return u;
		}
	}

	@Override
	public User getUser(String userName) {
		try {
			User u = em.find(User.class, userName);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeUser(User u) {
		User a = getUser(u.getUserName());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Query query = em.createQuery("SELECT u FROM User u  ");
		return query.getResultList();
	}

	@Override
	public User getUserByNameAndPassword(String userName, String password) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password");
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			User u = (User) query.getSingleResult();
			return u;
		} catch (Exception e) {
			logger.error("IN GETUSERBYNAMEANDHASH =>" + e.getMessage());
			return null;
		}

	}
}
