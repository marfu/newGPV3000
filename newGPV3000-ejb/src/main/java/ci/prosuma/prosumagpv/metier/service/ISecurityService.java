package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosuma.prosumagpv.entity.security.Role;
import ci.prosuma.prosumagpv.entity.security.User;

public interface ISecurityService {

    /**
     * USER METHODES *
     */
    public User createOrUpdateUser(User u);

    public User getUser(String userName);

    public boolean removeUser(User u);

    public List<User> getAllUsers();

    public User connectByIdAndPassword(String name, String password, String ipAddress);

    /**
     * ROLES METHODES *
     */
    public List<Role> getAllRoles();
    

    public Role getRoleById(String designation);

    public Role createRole(Role d);

    public void removeRole(Role d);

    public Role updateRole(Role d);

    /**
     * PERMISSIONS METHODES
     *
     * @return  *
     */
    public List<Permission> getAllPermissions();

    public Permission getPermissionsById(long permId);

    public Permission createPermission(Permission d);

    public boolean removePermission(Permission d);

    public Permission updatePermission(Permission d);

    public List<Permission> getPermissionByTarget(String target);

    public List<Permission> getPermissionByAction(String action);

    public List<Permission> getPermissionsForRole(String roleId);

    public List<Permission> getAllParentsPermissions();
    
     public List<Permission> getAllChildsPermissions(long id);
}
