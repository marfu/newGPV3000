package ci.prosumagpv.web.mobile.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserMobile implements Serializable {
	
	private String username;
	
	private String pvtCode;
	
	private String logged;
	
	public UserMobile() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPvtCode() {
		return pvtCode;
	}

	public void setPvtCode(String pvtCode) {
		this.pvtCode = pvtCode;
	}

	public String getLogged() {
		return logged;
	}

	public void setLogged(String logged) {
		this.logged = logged;
	}
	
	

}
