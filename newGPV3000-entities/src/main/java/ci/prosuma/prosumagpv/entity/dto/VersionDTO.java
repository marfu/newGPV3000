package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VersionDTO implements Serializable {

	

	private String TYPE;

	private String line;
	
	private String pvt;

	public VersionDTO() {
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getPvt() {
		return pvt;
	}

	public void setPvt(String pvt) {
		this.pvt = pvt;
	}
	
	

	

}
