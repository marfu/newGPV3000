package ci.prosuma.prosumagpv.metier.dao.mysql.security.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.security.Role;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IRoleDAO;

@Stateless
@Local(IRoleDAO.class)
public class RoleDAOImpl implements IRoleDAO{

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Role create(Role entity) {
		Role a = get(entity.getDesignation());
		if (a != null) {

			em.merge(entity);
			return entity;

		} else {
			em.persist(entity);
			return entity;
		}
	}

	@Override
	public Role get(String entityId) {
		try {
			Role u = em.find(Role.class, entityId);
			if (null != u) {
				if (null != u.getPermission()) {
					u.getPermission().size();

				}
			}

			return u;

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public boolean remove(Role entity) {
		Role a = get(entity.getDesignation());
		if (null != a) {
			em.remove(a);
			return true;
		}
		return false;
	}

	@Override
	public Role update(Role entity) {
		Role a = get(entity.getDesignation());
		if (a != null) {
			em.merge(entity);
			return entity;

		} else {
			return create(entity);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		try {
			List<Role> lis = em.createQuery("SELECT r FROM Role r").getResultList();
			for (Role u : lis) {
				if (u.getPermission() != null) {
					u.getPermission().size();
				}
			}
			return lis;
		} catch (Exception e) {
			return null;
		}

	}



}
