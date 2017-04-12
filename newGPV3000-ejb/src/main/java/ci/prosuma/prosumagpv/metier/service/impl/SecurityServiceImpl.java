package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import ci.prosuma.metier.dataexchange.ldap.ILDAPUserDAO;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosuma.prosumagpv.entity.security.Role;
import ci.prosuma.prosumagpv.entity.security.User;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IMySQLUserDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IPermissionDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.security.IRoleDAO;
import ci.prosuma.prosumagpv.metier.service.ISecurityService;

@SuppressWarnings("serial")
@Stateless
@Local(ISecurityService.class)
public class SecurityServiceImpl implements ISecurityService, Serializable {

    private static Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    @EJB
    private ILDAPUserDAO service;

    @EJB
    private IRoleDAO role;

    @EJB
    private IMySQLUserDAO userDAO;

    @EJB
    private IPointDeVenteDAO pointDeVenteDAO;

    @EJB
    private IPermissionDAO permission;

    @Override
    public User createOrUpdateUser(User u) {
        logger.log(Level.INFO, "createOrUpdateUser");
        return userDAO.createOrUpdateUser(u);
    }

    @Override
    public User getUser(String userName) {
        logger.log(Level.INFO, "getUser");
        return userDAO.getUser(userName);
    }

    @Override
    public boolean removeUser(User u) {
        logger.log(Level.INFO, "removeUser");
        return userDAO.removeUser(u);
    }

    @Override
    public List<User> getAllUsers() {
        logger.log(Level.INFO, "getAllUsers");
        return userDAO.getAllUsers();
    }

    @Override
    public User connectByIdAndPassword(String name, String password, String ipAddress) {
        try {

            logger.info("CONNECTBYIDANDPASSWORD = > " + name + " " + password + " " + ipAddress);
            String encretedPassword = org.apache.commons.codec.digest.DigestUtils.md5Hex(password);
            logger.info("encretedPassword : " + encretedPassword);
            User userMySql = userDAO.getUserByNameAndPassword(name, encretedPassword);
            User finaluser = null;
            if (userMySql == null) {
                logger.info("user not finded in database");
                return null;
//				User userLdap = service.connectByIdAndPassword(name, password);
//				if (userLdap == null) {
//					logger.info("user not finded in LDAP => exit");
//					return null;
//				} else {
//					logger.info("user  finded in ldap");
//					userLdap.setDateCreation(new Date(System.currentTimeMillis()));
//					userLdap.setEnable(true);
//					userLdap.setDateDerniereConnexion(new Date(System.currentTimeMillis()));
//					userLdap.setPassword(password);
//					userLdap = assignRight(userLdap);
//					userLdap = userDAO.createOrUpdateUser(userLdap);
//					userLdap = assignMag(userLdap);
//					finaluser = userDAO.createOrUpdateUser(userLdap);
//					System.out.println("userLDAP CRRE "+finaluser);
//					return finaluser;
//					
//				}
            } else {
                System.out.println("existe dans Mysql");
                if (!userMySql.isEnable()) {
                    System.out.println("not enable");
                    return null;
                } else {
                    System.out.println("enable");
                    userMySql.setDateDerniereConnexion(new Date(System.currentTimeMillis()));
                    return userDAO.createOrUpdateUser(userMySql);
                }
            }
        } catch (Exception e) {
            return null;
        }

    }

    private User assignRight(User userLdap) {
//		if(userLdap.getMemberOf() != null){
//			for(String s : userLdap.getMemberOf()){
//				if(s.equals("administrateur_gpv3000"))
//					userLdap.setRole(role.get("ADMINISTRATEUR"));
//				if(s.equals("chef_departement_gpv3000"))
//					userLdap.setRole(role.get("CHEF_DEPARTEMENT"));
//				if(s.equals("chef_rayon_gpv3000"))
//					userLdap.setRole(role.get("CHEF_RAYON"));
//				if(s.equals("directeur_magasin_gpv3000"))
//					userLdap.setRole(role.get("DIRECTEUR_MAG"));
//				if(s.equals("operateur_saisie_gpv3000"))
//					userLdap.setRole(role.get("OPERATEUR_SAISIE"));
//				if(s.equals("raf_gpv3000"))
//					userLdap.setRole(role.get("RAF"));
//				if(s.equals("reception_gpv3000"))
//					userLdap.setRole(role.get("RECEPTIONNAIRE"));
//				if(s.equals("rayonniste_gpv3000"))
//					userLdap.setRole(role.get("RAYONNISTE"));
//			}
//		}
//		return userLdap;
        return null;
    }

//    private User assignMag(User u) {
//        List<PointDeVente> pvt = new ArrayList<PointDeVente>();
//        if (u.hasRole("ADMINISTRATEUR")) {
//            pvt.addAll(pointDeVenteDAO.getAllPVTActive());
////            u.setPvt(pvt);
//            return userDAO.createOrUpdateUser(u);
//        } else {
//            pvt.add(pointDeVenteDAO.getPvtByLdapOU(u.getDepartement()));
////            u.setPvt(pvt);
//            return userDAO.createOrUpdateUser(u);
//        }
//    }

    @Override
    public List<Role> getAllRoles() {
        return role.getAllRoles();
    }

    @Override
    public Role getRoleById(String roleID) {
        return role.get(roleID);
    }

    @Override
    public Role createRole(Role d) {
        return role.create(d);
    }

    @Override
    public void removeRole(Role d) {
        role.remove(d);

    }

    @Override
    public Role updateRole(Role d) {
        return role.update(d);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permission.getAllPermissions();
    }

    @Override
    public Permission getPermissionsById(long permId) {
        return permission.get(permId);
    }

    @Override
    public Permission createPermission(Permission d) {
        return permission.create(d);
    }

    @Override
    public boolean removePermission(Permission d) {
        return permission.remove(d);

    }

    @Override
    public Permission updatePermission(Permission d) {
        return permission.update(d);
    }

    @Override
    public List<Permission> getPermissionByTarget(String target) {
        return permission.getPermissionByTarget(target);
    }

    @Override
    public List<Permission> getPermissionByAction(String action) {
        return permission.getPermissionByAction(action);
    }

    @Override
    public List<Permission> getPermissionsForRole(String roleId) {
        return permission.getPermissionsForRole(roleId);
    }

    @Override
    public List<Permission> getAllParentsPermissions() {
        return permission.getAllParentsPermissions();
    }

    @Override
    public List<Permission> getAllChildsPermissions(long id) {
        return permission.getAllChildsPermissions(id);
    }

    

        
}
