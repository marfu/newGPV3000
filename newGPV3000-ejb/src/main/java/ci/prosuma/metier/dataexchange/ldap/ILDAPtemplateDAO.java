package ci.prosuma.metier.dataexchange.ldap;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public interface ILDAPtemplateDAO {

	public DirContext getLdapContext(String login1, String password1)throws AuthenticationException;
	
	public DirContext getLdapContext() throws NamingException;
	
}
