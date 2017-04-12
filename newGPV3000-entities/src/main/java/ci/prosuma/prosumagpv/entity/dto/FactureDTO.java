package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FactureDTO implements Serializable {
	
	private String  codeMag;
	
	private String user;
	
	private ArrayList<String> lineList ;
	
	public FactureDTO() {
	}

	public String getCodeMag() {
		return codeMag;
	}

	public void setCodeMag(String codeMag) {
		this.codeMag = codeMag;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ArrayList<String> getLineList() {
		return lineList;
	}

	public void setLineList(ArrayList<String> lineList) {
		this.lineList = lineList;
	}

	
	
	

}
