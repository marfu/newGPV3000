package ci.prosuma.prosumagpv.metier.dao.mysql.security;

import java.util.List;

import ci.prosuma.prosumagpv.entity.security.Role;

public interface IRoleDAO {
	
	public Role create(Role entity);

	public Role get(String entityId) ;

	public boolean remove(Role entity) ;

	public Role update(Role entity) ;

	public List<Role> getAllRoles() ;



}
