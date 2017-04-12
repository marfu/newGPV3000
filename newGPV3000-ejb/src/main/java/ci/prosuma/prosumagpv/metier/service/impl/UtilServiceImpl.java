package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IFournisseurDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtFourDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCategorieClientDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCodeAnalytiqueDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeDepotDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeModeReglementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSousFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxASDIDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IPromoService;
import ci.prosuma.prosumagpv.metier.service.IUtilService;

@Stateless
@Local(IUtilService.class)
public class UtilServiceImpl implements IUtilService, Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	public static final Logger logger = Logger
			.getLogger(UtilServiceImpl.class);

	@EJB
	public ITypeSecteurDAO secteurDAO;

	@EJB
	public ITypeRayonDAO rayonDAO;

	@EJB
	public ITypeFamilleDAO familleDAO;

	@EJB
	public ITypeSousFamilleDAO sousFamilleDAO;

	@EJB
	public ITypeCategorieClientDAO categorieClientDAO;

	@EJB
	public ITypeCodeAnalytiqueDAO codeAnalytiqueDAO;

	@EJB
	public ITypeDepotDAO typeDepotDAO;

	@EJB
	public ITypeModeReglementDAO modeReglementDAO;

	@EJB
	public ITypeMouvementStockDAO typeMouvDAO;

	@EJB
	public ITypeTauxASDIDAO tauxASDIDAO;

	@EJB
	public ITypeOrigineMouvementDAO origineMouvementDAO;

	@EJB
	public ITypeTauxTVADAO tauxTVADAO;

	@EJB
	public IPointDeVenteDAO pointDeVenteDAO;

	@EJB
	public IFournisseurDAO fournisseurDAO;

	@EJB
	public ILienArtFourDAO lientArtFourDAO;

	@EJB
	public IPromoDAO promoDAO;

	@EJB
	public IArticleDAO articleDAO;
	
	@EJB
	public IStockArticleDAO  stockDAO;
	
	@EJB
	public IPromoService promoService;
	
	@EJB
	public IJDBCConnectionMySQL  mySql;
	
	
	

	@SuppressWarnings("static-access")
	@Override
	public boolean sendMailCBMag(String codeArticle, String pvtCode) throws Exception {
		InitialContext  ctx  = new InitialContext();
		Session  mailSession = (Session) ctx.lookup("java:/Mail");
		MimeMessage  message = new MimeMessage(mailSession);
		message.setSubject("CODE BARRE CREER EN MAGASIN");
		message.setRecipients(RecipientType.TO, new javax.mail.internet.InternetAddress().parse("zakhdar@prosuma.ci", false));
		
		StringBuffer body = new StringBuffer();
		body.append("Merci d' affecter les code-barres ci dessous à l' article suivant : \n");
		
		Article a = articleDAO.getArticle(new ArticleMagRefPK(pvtCode, codeArticle));
		body.append("Article : "+codeArticle+" , "+a.getDesignation()+" \n");
		body.append("Magasin : "+pvtCode+" , "+pointDeVenteDAO.getPVT(pvtCode).getRaisonSocialFournisseur()+" \n");
//		for(GenCode gc : a.getListGenCode()){
//			body.append("cb : "+gc.getCode()+gc.getCaractereControle()+" Origine : "+gc.getOrigineGenCode().toString()+" \n");
//		}
		
		
		message.setText(body.toString());
		message.saveChanges();
		
		Transport  transport = mailSession.getTransport("smtp");
		try{
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
		}finally{
			transport.close();
		
		
		}
		
		return true;
		
	}

	@Override
	public void executeUpdateSequence(String libelle, int req) {
		mySql.executeUpdateSequence(libelle, req);
	}

	@Override
	public void executeUpdateSequence(String sql) {
		mySql.executeSql(sql);
	}

	@Override
	public String getSqlRequestByLibelle(String libelle) {
//		return mySql.getSqlRequestByLibelle(libelle);
return"";
	}
	
	
	@Override
	public String getAllNewGenCodeToDay(String codeMag, String lineSeparator) {
		return mySql.getAllNewGenCodeToDay(codeMag, lineSeparator);
	}

		
	}




