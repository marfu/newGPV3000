package ci.prosuma.prosumagpv.metier.dao.mysql.security;

import java.util.List;

import ci.prosuma.prosumagpv.entity.security.Permission;

public interface IPermissionDAO {
	
	public Permission create(Permission entity);
	
	public Permission get(long entityId) ;

	public boolean remove(Permission entity) ;

	public Permission update(Permission entity) ;

	public List<Permission> getAllPermissions() ;

	public List<Permission> getPermissionByTarget(String target) ;

	public List<Permission> getPermissionByAction(String action) ;

	public List<Permission> getPermissionsForRole(String roleId) ;
        
        public List<Permission> getAllParentsPermissions() ;
        
        public List<Permission> getAllChildsPermissions(long id) ;


}
