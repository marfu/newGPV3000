package ci.prosumagpv.web.mobile.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.metier.service.IBonCommandeService;

/**
 * Servlet implementation class CommandeExport
 */
@WebServlet("/CommandeExport")
public class CommandeExport extends HttpServlet {

	@EJB
	private IBonCommandeService bonCommandeService;

	private static final long serialVersionUID = 1L;

	private static final int BYTES_DOWNLOAD = 1024;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandeExport() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		String pvtId = request.getParameter("pvtId");
		if (null != pvtId) {

			try {

				List<EnteteBonCommandeFournisseur> listFinalReassort = new ArrayList<EnteteBonCommandeFournisseur>();

				List<EnteteBonCommandeFournisseur> listEBCF = bonCommandeService.getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(pvtId, OrigineCommande.CENTRALE, EtatCommande.VALIDER);
				if (listEBCF != null) {
					listFinalReassort.addAll(listEBCF);
				}

				ArrayList<String> ligneComm = new ArrayList<String>();
				DecimalFormat df8 = new DecimalFormat("00000000");
				DecimalFormat df9 = new DecimalFormat("000000000");
				DecimalFormat df13 = new DecimalFormat("0000000000000");

				if (listFinalReassort.size() > 0) {
					ligneComm = new ArrayList<String>();
					for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
						for (DetailBonCommandeFournisseur a : bonCommandeService.getAllDSCforESC(ebcf.getId())) {
							String s = ebcf.getPvt().getPvtCode() + df9.format(Long.parseLong(a.getArticle().getCodeArticle())) + df13.format((a.getQteCommande() * 1000))
									+ df13.format((a.getPvUnitaireTTC() * 1000)) + df8.format(ebcf.getId());
							ligneComm.add(s);
						}
					}

				
					String name =  "CMD" + pvtId + "REA" + "-" + System.currentTimeMillis() + ".txt";

					File f = new File(name);
					if (!f.exists())
						f.createNewFile();

					FileOutputStream fos = new FileOutputStream(f);
					BufferedOutputStream bos = new BufferedOutputStream(fos);

					for (String s : ligneComm) {
						bos.write(s.getBytes());
						bos.write("\r\n".getBytes());
					}

					bos.flush();
					bos.close();
					fos.flush();
					fos.close();

					response.setContentType("text/plain");
					response.setHeader("Content-Disposition", "attachment;filename=downloadname.txt");

					@SuppressWarnings("resource")
					InputStream is = new FileInputStream(f);

					int read = 0;
					byte[] bytes = new byte[BYTES_DOWNLOAD];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();

					for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
						ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
						ebcf.setEtatCommande(EtatCommande.TRANSMIT);
						bonCommandeService.createOrUpdateEBCF(ebcf);
					}

				}
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
