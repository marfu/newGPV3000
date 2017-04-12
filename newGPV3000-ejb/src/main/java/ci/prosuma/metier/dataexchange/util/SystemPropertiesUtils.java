package ci.prosuma.metier.dataexchange.util;



public interface SystemPropertiesUtils {
	
	public static String ldapDomain = System.getProperty("ldap.domain");
	public static String ldapUser = System.getProperty("ldap.login");
	public static String ldapPassword = System.getProperty("ldap.password");
	public static String smbServeurIp = System.getProperty("smb.server");
	public static String commandeServeurPath = System.getProperty("smb.directory.commande");
	public static String versionServeurPath = System.getProperty("smb.directory.version");
	
	public static final String fileNameVersion = System.getProperty("version.filename");
	public static final String processTable = System.getProperty("process.table");
	
	public static String as400ServeurIp = System.getProperty("as400.ip");
	public static String as400ServeurUser = System.getProperty("as400.user");
	public static String as400ServeurPassword = System.getProperty("as400.password");
	public static String as400FactureDirectory = System.getProperty("as400.facture.directory");
	public static String as400VersionDirectory = System.getProperty("as400.version.directory");
		
}
