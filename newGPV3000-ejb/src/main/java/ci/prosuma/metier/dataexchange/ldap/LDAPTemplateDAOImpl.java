package ci.prosuma.metier.dataexchange.ldap;

import java.io.Serializable;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


@Stateless
@Local(ILDAPtemplateDAO.class)
public class LDAPTemplateDAOImpl  implements ILDAPtemplateDAO , Serializable{


	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9128966159739469123L;

	private DirContext ldapContext;

	private String ldapUrl;

	private String ldapBase;

	private String domain;

	private String login;

	private String password;

	@PostConstruct
	public void init() {
		@SuppressWarnings("unused")
		Context ctx;
		try {
			ctx = new InitialContext();
			ldapUrl = System.getProperty("ldap.url");
			ldapBase = System.getProperty("ldap.base");
			domain = System.getProperty("ldap.domain");
			login = System.getProperty("ldap.login");
			password = System.getProperty("ldap.password");
		} catch (NamingException e) {
		}
	}

	/**
	 * @return Returns the ldapContext.
	 */
	@Override
	public DirContext getLdapContext() throws NamingException {
		// test connexion
		if (ldapContext != null) {
			try {
				ldapContext.getAttributes(ldapBase);
			} catch (Exception e) {
				ldapContext = null;
			}
		}

		if (ldapContext == null) {
			Hashtable<String, String> conf = new Hashtable<String, String>();

			conf.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			conf.put(Context.PROVIDER_URL, ldapUrl);
			conf.put(Context.SECURITY_AUTHENTICATION, "simple");
			conf.put(Context.SECURITY_PRINCIPAL, login + "@" + domain);
			conf.put(Context.SECURITY_CREDENTIALS, password);
			conf.put(Context.REFERRAL, "follow");

			try {
				ldapContext = new InitialDirContext(conf);
			} catch (Exception e) {
				throw new AuthenticationException(e.getMessage());
			}
		}
		return ldapContext;
	}

	/**
	 * @return Returns the ldapContext.
	 */
	@Override
	public DirContext getLdapContext(String login1, String password1)
			throws AuthenticationException {
		Hashtable<String, String> conf = new Hashtable<String, String>();
		if (password1.equals("")) {
			password1 = null;
		}
		conf.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		conf.put(Context.PROVIDER_URL, ldapUrl);
		conf.put(Context.SECURITY_AUTHENTICATION, "simple");
		conf.put(Context.SECURITY_PRINCIPAL, login1 + "@" + domain);
		conf.put(Context.SECURITY_CREDENTIALS, password1);
		conf.put(Context.REFERRAL, "follow");
		System.out.println(conf);

		try {
			ldapContext = new InitialDirContext(conf);
		} catch (Exception e) {
			throw new AuthenticationException(e.getMessage());
		}
		return ldapContext;
	}

	public String getLdapBase() {
		return ldapBase;
	}
}
