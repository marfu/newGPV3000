package ci.prosuma.metier.dataexchange.ldap;

import ci.prosuma.prosumagpv.entity.security.User;


public interface ILDAPUserDAO {
	
	
	
	public User connectByIdAndPassword(String login, String password)throws Exception;

}
