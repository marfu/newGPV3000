package ci.prosuma.metier.dataexchange.smb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jcifs.smb.SmbFile;

public interface ISMBConnection {
	
	public SmbFile[] listFile(String path);
	
	public List<SmbFile> listFile(String path , String filter);
	
	public void deleteFile(String path,String fileName);
	
	

	public String addFile(ArrayList<String> listTemp, String type, String codeMag);
	
	public void copyFileBySMB(String fileName);

}
