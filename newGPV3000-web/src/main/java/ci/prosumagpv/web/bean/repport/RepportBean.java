package ci.prosumagpv.web.bean.repport;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.metier.dao.jdbc.IJDBCGPV3000MySQLConnection;
import ci.prosumagpv.web.bean.security.SecurityBean;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("repportBean")
@SessionScoped
public class RepportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @EJB(name = "GPV3000Ear/ClassificationServiceImpl/local")
	// private IClassificationService classificationService;

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

	@ManagedProperty(value = "#{param.repportId}")
	private String repportId;

	@EJB
	private IJDBCGPV3000MySQLConnection mySql;

	public RepportBean() {
	}

	private String getRequestParam(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);

	}

	public String viewReportPDF() throws SQLException, JRException, IOException {

		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));

		String id = getRequestParam("repportId");
		if (!checkNull(id)) {

			HashMap<String, Object> param = new HashMap<String, Object>();
			for (Entry<String, String> e : FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap().entrySet()) {
				String key = e.getKey();
				String value = (String) e.getValue();
				
				if (key.startsWith("repPar"))
					System.out.println("PARAM : " + key + " |  " + value);
				param.put(key.replaceAll("repPar", ""), value);
			}

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					new FileInputStream(new File(file, id + ".jasper")), param,
					connection);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			FacesContext context = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"attachment;filename=rapport.pdf");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.setContentType("application/pdf");
			context.responseComplete();

			return "";

		}

		return "";

	}
        
        public byte[] downloadPDF(String id, HashMap<String, Object> param)throws SQLException, JRException, IOException{
            String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//File file = new File(System.getProperty("rapport.path"));
		File file = new File(System.getProperty("rapport.path"));
		//System.out.println("REPPORT BEAN ID :" + id);

		System.out.println("file path :" + file.getAbsolutePath());
		if (!checkNull(id)) {
                    System.out.println("checkNull :" + id);
			JasperPrint jasperPrint = JasperFillManager.fillReport(new FileInputStream(new File(file, id + ".jasper")), param,connection);
			return  JasperExportManager.exportReportToPdf(jasperPrint);

		}else{
                    System.out.println("Nous Sommes ici ");
                    return null;
                }
        }

	public void viewReportPDFObject(String id, HashMap<String, Object> param)throws SQLException, JRException, IOException {
		//
		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//File file = new File(System.getProperty("rapport.path"));
		File file = new File(System.getProperty("rapport.path"));
		//System.out.println("REPPORT BEAN ID :" + id);

		System.out.println("file path :" + file.getAbsolutePath());
		if (!checkNull(id)) {
                    System.out.println("checkNull :" + id);
			JasperPrint jasperPrint = JasperFillManager.fillReport(new FileInputStream(new File(file, id + ".jasper")), param,connection);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename="+ id + ".pdf");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.setContentType("application/pdf");
			context.responseComplete();

		}else{
                    System.out.println("Nous Sommes ici ");
                }

	}

	/* PROPHYL.COM */
	public void viewReportXLSObject(String id, HashMap<String, Object> param)
			throws SQLException, JRException, IOException {
		//
		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;
		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));
		System.out.println("REPPORT BEAN ID :" + id);
		if (!checkNull(id)) {
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					new FileInputStream(new File(file, id + ".jasper")), param,
					connection);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + id + ".xlsx");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JRXlsxExporter xlsxExporter = new JRXlsxExporter();
			xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					servletOutputStream);

			if (id.startsWith("gpv3000_mouvement_excel") || id.startsWith("gpv3000_mouvements_excel") || id.startsWith("gpv3000_mouvements_promo")) {
				xlsxExporter.setParameter(
						JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
						Boolean.TRUE);
			}
			xlsxExporter.exportReport();
			FacesContext.getCurrentInstance().responseComplete();
		}
	}
        
        public byte[] downloadXLSFile(String id, HashMap<String, Object> param)throws SQLException, JRException, IOException{
            String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
                File file = new File(System.getProperty("rapport.path"));
		System.out.println("REPPORT BEAN ID :" + id);
		if (!checkNull(id)) {
			JasperPrint jasperPrint = JasperFillManager.fillReport(new FileInputStream(new File(file, id + ".jasper")), param,connection);
			ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();
			JRXlsxExporter xlsxExporter = new JRXlsxExporter();
			xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,OutputStream);

			if (id.startsWith("gpv3000_mouvement_excel") || id.startsWith("gpv3000_mouvements_excel") || id.startsWith("gpv3000_mouvements_promo")) {
				xlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
			}
			xlsxExporter.exportReport();
			FacesContext.getCurrentInstance().responseComplete();
                        return OutputStream.toByteArray();
		}else{
                    System.out.println("Nous Sommes ici ");
                    return null;
                }
        }

	public void viewReportPDFObjectList(String reportName, List<String> listId,
			HashMap<String, Object> param) throws SQLException, JRException,
			IOException {
		//
		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));
		List<JasperPrint> jprintlist = new ArrayList<>();
		for (String id : listId) {
			JasperPrint jasperPrint ;
			jasperPrint = JasperFillManager.fillReport(new FileInputStream(new File(file, id + ".jasper")), param, connection);
			jprintlist.add(jasperPrint);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST,jprintlist);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();
		if (bytes != null && bytes.length > 0) {
			response.addHeader("Content-disposition", "attachment;filename="+ reportName + ".pdf");
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			context.responseComplete();
		} else {
			System.out.println("EMPTY REPONSE");
		}
	}

	public void viewReportPDFObjectCBList(List<GenCode> list, String type)
			throws SQLException, JRException, IOException {
		//
		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));
		List<JasperPrint> jprintlist = new ArrayList<>();
		
		for (GenCode gc : list) {
			JasperPrint jasperPrint = null;
			HashMap<String, Object> hash = new HashMap<>();
			hash.put("CODEMAG", getSecurityBean().getMag());
			hash.put("CODEARTICLE", gc.getArticle().getCodeArticle());			
			hash.put("GENCODE_CODE", gc.getCode());	
			
			DecimalFormat format = new DecimalFormat("###,###.###"); 
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator(' ');
			format.setDecimalFormatSymbols(s);			
		        
	        String prix_vte = format.format(gc.getPrixVenteTTCEnCours());       
			
			if (type.equals("1")) {
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_cb.jasper")), hash,
						connection);
			}

			if (type.equals("2")) {
				
				//hash.put("PRIX", gc.getPrixVenteTTCEnCours() + "");
				hash.put("PRIX",prix_vte + "");
				
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_cbP.jasper")), hash,
						connection);
			}

			if (type.equals("3")) {
				hash.put("PRIX",prix_vte + "");
				// if(gc.getColisage() != 0)
				// hash.put("COLIS", Integer.toString(gc.getColisage()));
				// else
				hash.put("COLIS",
						gc.getArticle().getColisage().replaceFirst("^0*", ""));
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_gondole.jasper")),
						hash, connection);
			}

			for (int i = 0; i < gc.getUtilVar(); i++) {
				jprintlist.add(jasperPrint);
			}

		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST,
				jprintlist);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();
		if (bytes != null && bytes.length > 0) {

			response.addHeader("Content-disposition",
					"attachment;filename=gencode.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			context.responseComplete();
		} else {
			System.out.println("EMPTY REPONSE");
		}

	}

	public void viewReportPDFObjectWinstore(String ip, String id,
			HashMap<String, Object> param) throws SQLException, JRException,
			IOException {
		//
		String url = mySql.getSqlRequestByLibelle("mssql.url");
		url = url.replaceAll("ipServeurCaisse", ip);
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));
		//System.out.println("REPPORT BEAN ID :" + id);
                System.out.println("file path :" + file.getAbsolutePath());
		if (!checkNull(id)) {
                    System.out.println("checkNull :" + id);
			JasperPrint jasperPrint = JasperFillManager.fillReport(new FileInputStream(new File(file, id + ".jasper")), param,connection);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename="+ id + ".pdf");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.setContentType("application/pdf");
			context.responseComplete();

		}else{
                    System.out.println("Nous Sommes ici ");
                }

	}

	private boolean checkNull(String s) {
		if (s == null) {
			return true;
		}
		return s.trim().equals("");
	}

	public SecurityBean getSecurityBean() {
		if (securityBean == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			securityBean = (SecurityBean) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(context.getELContext(), null, "securityBean");
		}
		return securityBean;
	}

	public void setSecurityBean(SecurityBean securityBean) {
		this.securityBean = securityBean;
	}

	public String getRepportId() {
		return repportId;
	}

	public void setRepportId(String repportId) {
		this.repportId = repportId;
	}

	public void viewReportPDFObjectCBListDirect(final List<GenCode> list,
			final String type) throws SQLException, JRException, IOException,
			PrinterException {
		String url = mySql.getSqlRequestByLibelle("mysql.url");
		String login = mySql.getSqlRequestByLibelle("mysql.user");
		String password = mySql.getSqlRequestByLibelle("mysql.password");
		Connection connection = null;

		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(System.getProperty("rapport.path"));
		List<JasperPrint> jprintlist = new ArrayList<>();
		for (GenCode gc : list) {
			JasperPrint jasperPrint = null;
			HashMap<String, Object> hash = new HashMap<>();
			hash.put("CODEMAG", getSecurityBean().getMag());
			hash.put("CODEARTICLE", gc.getArticle().getCodeArticle());
			hash.put("GENCODE_CODE", gc.getCode());
			if (type.equals("1")) {
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_cb.jasper")), hash,
						connection);
			}

			if (type.equals("2")) {
				hash.put("PRIX", gc.getPrixVenteTTCEnCours() + "");
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_cbP.jasper")), hash,
						connection);
			}

			if (type.equals("3")) {
				hash.put("PRIX", gc.getPrixVenteTTCEnCours() + "");
				// if(gc.getColisage() != 0)
				// hash.put("COLIS", Integer.toString(gc.getColisage()));
				// else
				hash.put("COLIS",
						gc.getArticle().getColisage().replaceFirst("^0*", ""));
				jasperPrint = JasperFillManager.fillReport(new FileInputStream(
						new File(file, "gpv3000_edition_gondole.jasper")),
						hash, connection);
			}

			for (int i = 0; i < gc.getUtilVar(); i++) {
				jprintlist.add(jasperPrint);
			}

		}

		// JasperPrint print = JasperFillManager.fillReport(
		// this.class.getResource("/classpath/yourReport.jasper").getPath(), new
		// HashMap(), new yourReportDataSource());
		PrinterJob job = PrinterJob.getPrinterJob();
		/* Create an array of PrintServices */
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null,
				null);
		int selectedService = 0;
		/* Scan found services to see if anyone suits our needs */
		for (int i = 0; i < services.length; i++) {
			if (services[i].getName().toUpperCase().contains("spooler")) {
				/* If the service is named as what we are querying we select it */
				selectedService = i;
			}
		}
		job.setPrintService(services[selectedService]);
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		MediaSizeName mediaSizeName = MediaSize.findMedia(4, 4,
				MediaPrintableArea.INCH);
		printRequestAttributeSet.add(mediaSizeName);
		printRequestAttributeSet.add(new Copies(1));
		JRPrintServiceExporter exporter;
		exporter = new JRPrintServiceExporter();
		// exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				jprintlist.get(0));
		/* We set the selected service and pass it as a paramenter */
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE,
				services[selectedService]);
		exporter.setParameter(
				JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
				services[selectedService].getAttributes());
		exporter.setParameter(
				JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
				printRequestAttributeSet);
		exporter.setParameter(
				JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
				Boolean.FALSE);
		exporter.setParameter(
				JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
				Boolean.TRUE);
		exporter.exportReport();
	}
}
