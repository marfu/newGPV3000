package ci.prosuma.metier.dataexchange.util;
public interface SmbConstants {

    /**
     * File separator.
     */
    char REMOTE_FILE_SEPARATOR = '/';

    /**
     * Prefix of Samba connection URL.
     */
    String SMB_URL_PREFIX = "smb://";

    /**
     * Separating character of domain name and user name in Samba connection
     * URL.
     */
    String SMB_URL_DOM_SEPARATOR = ";";

    /**
     * Separating character of user name and password in Samba connection URL.
     */
    String SMB_URL_UP_SEPARATOR = ":";

    /**
     * Prefix of IP address in Samba connection URL.
     */
    String SMB_URL_IP_PREFIX = "@";

    /**
     * Default buffer size (byte) to use in <code>SmbFileInputStream</code>,
     * <code>SmbFileOutputStream</code>.
     */
    int DEFAULT_BUFFER_SIZE = 3072;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that connection destination cannot be found.
     */
    int ERR_UNKNOWN_HOST = 2001;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that connection destination URL is improper.
     */
    int ERR_MALFORMED_URL = 2002;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that specified file or folder cannot be found.
     */
    int ERR_FILE_NOT_FOUND = 2003;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that access failure to file in local or on server has occurred.
     */
    int ERR_IO_ERROR = 2004;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that login to the destination failed.
     */
    int ERR_LOGON_FAILURE = 2005;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that access was denied at the folder or such in destination.
     */
    int ERR_ACCESS_DENIED = 2006;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that <code>SmbException</code> other than login error or
     * access error has occurred.
     */
    int ERR_SMB_EXCEPTION = 2007;

    /**
     * Error code when error occurs in Samba connection process. <br>
     * <br>
     * Indicates that unknown error of separation unavailable has occurred.
     */
    int ERR_UNKNOWN_ERROR = 2008;
}