package ci.prosuma.metier.dataexchange.smb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.jboss.logging.Logger;

import ci.prosuma.metier.dataexchange.util.SmbConstants;
import ci.prosuma.metier.dataexchange.util.SystemPropertiesUtils;

@Stateless
@Local(ISMBConnection.class)
public class SMBConnectionImpl implements ISMBConnection, Serializable {

	private static final Logger logger = Logger
			.getLogger(SMBConnectionImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SmbFile[] listFile(String path) {

		try {
			SmbFile file = new SmbFile(getUrl(path));
			if (file != null && file.isDirectory())
				return file.listFiles();
		} catch (Exception e) {
			logger.info("impossible de lister le dossier");
		}

		return null;
	}

	@Override
	public List<SmbFile> listFile(String path, String filter) {

		logger.info("listFile with filter " + path + " " + filter);
		try {
			SmbFile file = new SmbFile(getUrl(path));
			List<SmbFile> result = new ArrayList<SmbFile>();
			logger.info("result : " + result.size());
			if (file != null && file.isDirectory()) {
				for (SmbFile f : file.listFiles()) {
					if (f.getName().startsWith(filter))
						result.add(f);
				}
				return result;
			}
			return null;
		} catch (Exception e) {
			logger.info("impossible de lister le dossier");
		}

		return null;
	}

	private String getUrl(String path) {
		String url = SmbConstants.SMB_URL_PREFIX
				+ SystemPropertiesUtils.ldapDomain
				+ SmbConstants.SMB_URL_DOM_SEPARATOR
				+ SystemPropertiesUtils.ldapUser
				+ SmbConstants.SMB_URL_UP_SEPARATOR
				+ SystemPropertiesUtils.ldapPassword
				+ SmbConstants.SMB_URL_IP_PREFIX
				+ SystemPropertiesUtils.smbServeurIp
				+ SmbConstants.REMOTE_FILE_SEPARATOR + path
				+ SmbConstants.REMOTE_FILE_SEPARATOR;
		return url;
	}

	@Override
	public void deleteFile(String path, String fileName) {
		try {
			SmbFile file = new SmbFile(getUrl(path)
					+ SmbConstants.REMOTE_FILE_SEPARATOR + fileName);
			if (file != null && !file.isDirectory())
				file.delete();
		} catch (Exception e) {
			logger.info("impossible de lister le dossier");
		}
	}

	@SuppressWarnings("resource")
	@Override
	public String addFile(ArrayList<String> listTemp, String type,
			String codeMag) {

		SmbFileOutputStream out = null;
		BufferedOutputStream bufferedOutput = null;

		try {
			String name = "";
			if (type.equals("CENTRALE")) {
				name = "CMD" + codeMag + "REA" + "-"+ System.currentTimeMillis() + ".txt";
			}
			if (type.equals("LD")) {
				name = "CMD" + codeMag + "LD" + "-"+ System.currentTimeMillis() + ".txt";
			}

			System.out.println("URL : "+ getUrl(SystemPropertiesUtils.commandeServeurPath) + name);

			SmbFile file = new SmbFile(getUrl(SystemPropertiesUtils.commandeServeurPath) + name);
			out = new SmbFileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(out);

			for (String s : listTemp) {
				bufferedOutput.write(s.getBytes());
				bufferedOutput.write("\r\n".getBytes());
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedOutput != null) {
					bufferedOutput.flush();
					bufferedOutput.close();
					return "SUCCESS";
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return "FAILURE";
			}

		}
		return "FAILURE";

	}

	@Override
	public void copyFileBySMB(String fileName) {
		System.out.println("Debut copy fichier par SMB");
		String urlToDestFile = "smb://10.0.13.77/GPV3000SA/tomajcai.fic";
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("prosuma.lan", "etud_dev", "etud_dev14");
		SmbFile dest;
		try {
			dest = new SmbFile(urlToDestFile, auth);
			SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(dest);
			FileInputStream fileInputStream = new FileInputStream(new File("tomajcai.fic"));
			final byte[] buf = new byte[16 * 1024 * 1024];
			  int len;
			  while ((len = fileInputStream.read(buf)) > 0) {
			   smbFileOutputStream.write(buf, 0, len);
			  }
			  fileInputStream.close();
			  smbFileOutputStream.close();
			 
//			SmbFile dir = new SmbFile("file:///Users/tagsergi/Documents/jboss-6.0.0.Final/bin/"+fileName);
//			dest.createNewFile();
//			dir.copyTo(dest);
			System.out.println("Fin copy fichier par SMB");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SmbException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

}