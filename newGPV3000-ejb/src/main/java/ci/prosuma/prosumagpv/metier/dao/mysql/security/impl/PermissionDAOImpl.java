package ci.prosuma.prosumagpv.metier.dao.mysql.security.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IPermissionDAO;

@Stateless
@Local(IPermissionDAO.class)
public class PermissionDAOImpl implements IPermissionDAO {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Permission create(Permission entity) {
        Permission a = get(entity.getPermissionId());
        if (a != null) {

            em.merge(entity);
            return entity;

        } else {
            em.persist(entity);
            return entity;
        }
    }

    @Override
    public Permission get(long entityId) {
        try {
            Permission u = em.find(Permission.class, entityId);

            return u;

        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public boolean remove(Permission entity) {
        Permission a = get(entity.getPermissionId());
        if (null != a) {
            em.remove(a);
            return true;
        }
        return false;
    }

    @Override
    public Permission update(Permission entity) {
        Permission a = get(entity.getPermissionId());
        if (a != null) {
            em.merge(entity);
            return entity;

        } else {
            return create(entity);
        }
    }

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Permission> getAllPermissions() {
//		try {
//			return em.createNamedQuery("RolePermission.getAllPermissions")
//					.getResultList();
//		} catch (Exception e) {
//			return null;
//		}
//	}
    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getAllPermissions() {
        try {
            Query query = em.createQuery("SELECT p FROM Permission p");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Permission> getPermissionByRecipient(String recipient) {
        try {
            return em
                    .createNamedQuery("RolePermission.getPermissionByRecipiant")
                    .setParameter("recipient", recipient).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getPermissionByTarget(String target) {
        try {
            return em.createNamedQuery("RolePermission.getPermissionByTarget")
                    .setParameter("target", target).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getPermissionByAction(String action) {
        try {
            return em.createNamedQuery("RolePermission.getPermissionByAction")
                    .setParameter("action", action).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getPermissionsForRole(String roleId) {
        try {
            Query query = em
                    .createQuery("SELECT dt FROM Role a JOIN a.permission dt  WHERE a.designation=:designation ");
            query.setParameter("designation", roleId);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getAllParentsPermissions() {
        try {
            Query query = em.createQuery("SELECT p FROM Permission p where p.menuParent is null");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getAllChildsPermissions(long id) {
       //int i=1;
       
        try {
            Query query = em.createQuery("SELECT p FROM Permission p where p.menuParent="+id);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
