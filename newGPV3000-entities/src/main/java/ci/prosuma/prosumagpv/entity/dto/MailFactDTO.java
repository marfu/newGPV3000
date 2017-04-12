package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class MailFactDTO implements Serializable {
	
	private Date date;
	
	private String object;
	
	private String fichier;
	
	public MailFactDTO() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	
	

}
