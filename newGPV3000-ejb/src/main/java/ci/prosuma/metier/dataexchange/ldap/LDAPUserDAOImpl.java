package ci.prosuma.metier.dataexchange.ldap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;

import ci.prosuma.prosumagpv.entity.security.User;



/**
 * @author Zoul
 * 
 */
@Stateless
@Local(ILDAPUserDAO.class)
public class LDAPUserDAOImpl implements ILDAPUserDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, List<User>> cachedUsers = new HashMap<String, List<User>>();

	private static String[] attsToLoad = { "givenName", "sn", "mail",
			"distinguishedName", "sAMAccountName", "memberOf" };

	@EJB
	private ILDAPtemplateDAO ldapTemplate;

	private String userRoot;

	private String ldapBase;

	public LDAPUserDAOImpl() {
	}

	@PostConstruct
	public void init() {

		@SuppressWarnings("unused")
		Context ctx;
		try {
			ctx = new InitialContext();
			ldapBase = System.getProperty("ldap.base");
			userRoot = System.getProperty("ldap.userroot");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	
	@SuppressWarnings("rawtypes")
	public User findByLogin(String login) {
		try {
			String filtre = "(sAMAccountName=" + login + ")";
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setCountLimit(1);
			NamingEnumeration enu = null;
			try {

				enu = ldapTemplate.getLdapContext().search(ldapBase, filtre,controls);
			} catch (Exception e) {
				e.printStackTrace();

			}
			if (enu != null) {
				NameClassPair pair = (NameClassPair) enu.next();

				Attributes atts = ldapTemplate.getLdapContext().getAttributes(pair.getNameInNamespace(), attsToLoad);
				return getUser(atts);
			}
		} catch (NamingException e) {

		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<User> findAll() {
		String key = "ALL";
		if (cachedUsers.get(key) == null) {
			try {
				List<User> users = new LinkedList<User>();
				String root = userRoot;

				NamingEnumeration namingEnu = ldapTemplate.getLdapContext()
						.list(root);
				while (namingEnu.hasMore()) {
					NameClassPair pair = (NameClassPair) namingEnu.next();
					Attributes atts = ldapTemplate.getLdapContext()
							.getAttributes(pair.getNameInNamespace());
					try {
						// check if it's a right user
						User lu = getUser(atts);
						if (lu.getUserName() != null
								&& lu.getUserName().length() > 0) {
							users.add(lu);
						}
					} catch (NamingException e) {
						// don't add it
					}
				}
				cachedUsers.put(key, users);
			} catch (NamingException e) {

				return new LinkedList<User>();
			}
		}

		return cachedUsers.get(key);
	}

	@SuppressWarnings("rawtypes")
	protected User getUser(Attributes atts) throws NamingException {
		User user = new User();

//		atts.get("cn");
//
//		try {
//			user.setFirstName(atts.get("givenName").get().toString());
//		} catch (NullPointerException e) {
//
//		}
//		try {
//			if (null != atts.get("sn").get().toString()) {
//				user.setFamilyName(atts.get("sn").get().toString());
//			}
//		} catch (NullPointerException e) {
//
//		}
//		try {
//			user.setEmail(atts.get("mail").get().toString());
//		} catch (NullPointerException e) {
//
//		}
//
//		// Locality and Promotion
//		try {
//			user.setDistinguishedName(atts.get("distinguishedName").get()
//					.toString());
//
//			String distingName = user.getDistinguishedName();
//			String[] ous = distingName.split(",");
//			for (int i = 0; i < ous.length; i++) {
//				String[] ouTmp = ous[i].split("=");
//				// promotion (after the CN attribute)
//				if ("cn".equalsIgnoreCase(ouTmp[0])) {
//					String[] departementOu = ous[i + 1].split("=");
//
//					if (departementOu[1].length() == 1) {
//						departementOu[1] = departementOu[1] + ", " + ous[2];
//					}
//
//					user.setDepartement(departementOu[1]);
//				}
//			}
//		} catch (NullPointerException e) {
//
//		}
//
//		try {
//			user.setUserName(atts.get("sAMAccountName").get().toString());
//		} catch (NullPointerException e) {
//
//		}
//
//		try {
//			NamingEnumeration namingEnumeration = atts.get("memberOf").getAll();
//
//			List<String> memberOf = new ArrayList<String>();
//
//			while (namingEnumeration.hasMore()) {
//				String temp = (String) namingEnumeration.next();
//				String temp2 = ((temp.substring(0, temp.indexOf(","))).replaceAll("CN=", "")).toLowerCase();
//				memberOf.add(temp2);
//			}
//			user.setMemberOf(memberOf);
//
//		} catch (NullPointerException e) {
//
//		}

		return user;
	}

	@Override
	@WebMethod
	public User connectByIdAndPassword(String login, String password)
			throws Exception {

		try {
			DirContext dir = ldapTemplate.getLdapContext(login, password);
			if (dir != null) {
				return findByLogin(login);
			}
		} catch (AuthenticationException e) {
			throw new AuthenticationException("Login : " + login + " password "
					+ password);

		}

		return null;
	}
}
